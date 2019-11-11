package com.borisovskiy.collectionsmaps.ui.main.collections;

import android.os.Handler;
import android.util.Log;

import com.borisovskiy.collectionsmaps.model.IModel;
import com.borisovskiy.collectionsmaps.ui.base.BaseMvpFragmentPresenter;
import com.borisovskiy.collectionsmaps.utils.CollectionsTimeCalculator;
import com.borisovskiy.collectionsmaps.utils.DataUtility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class CollectionsFragmentPresenter extends BaseMvpFragmentPresenter<CollectionsFragmentContract.IView>
        implements CollectionsFragmentContract.IPresenter {

    private final IModel model;
    private ThreadPoolExecutor threadPoolExecutor;

    public CollectionsFragmentPresenter(IModel model) {
        this.model = model;
    }

    @Override
    public void onAttachUI(CollectionsFragmentContract.IView view) {
        super.onAttachUI(view);
        if(view.hasData()) return;
        view.setData(model.getCollectionsItems());
    }

    @Override
    public void stopCalculation(boolean forced) {
        Log.d("CollectionFragment", "stopCalculation isForced " + forced + ", calculation running " + (threadPoolExecutor != null));
        if (threadPoolExecutor == null) return;
        stopExecutor();
        if (view == null) return;
        view.hideProgressBar();
        view.setStateOfButton(false);
        view.showToast(forced ? "Calculation stopped" : "Calculation completed");
    }

    @Override
    public void showError() {
        view.showError();
    }

    private void stopExecutor() {
        if (threadPoolExecutor == null) return;
        threadPoolExecutor.shutdownNow();
        threadPoolExecutor = null;
    }

    @Override
    public void startCalculation(String operations, String threads) {
        if(view != null) {
            view.showProgressBar();
            view.showToast("Calculation started");
        }
        final Handler handlerCalculated = new Handler();
        final List<String> tags = model.getCollectionsTags();
        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer.valueOf(threads));
        threadPoolExecutor.execute(() -> {
            DataUtility.fillCollection(new ArrayList<>(), Integer.valueOf(operations));
            DataUtility.fillCollection(new LinkedList<>(), Integer.valueOf(operations));
            DataUtility.fillCollection(new CopyOnWriteArrayList<>(), Integer.valueOf(operations));

            for (String tag : tags) {
                if (Thread.interrupted()) {
                    Log.d("CalculationsFragment", "interrupt - Thread.interrupted() in for");
                    return;
                }

                try {
                    Thread.sleep(500L);
                } catch (IllegalArgumentException | InterruptedException e) {
                    e.printStackTrace();
                    Log.d("CollectionFragment", "interrupted sleep");
                    return;
                }
                handlerCalculated.post(() -> {
                    if (view == null) return;
                    view.updateItem(tag, CollectionsTimeCalculator.calculateTime(tag));
                    view.hideProgressBar(tag);
                });
                Log.d("CollectionFragment", "interrupt - update");
            }
            if (Thread.interrupted()) {
                Log.d("CollectionFragment", "interrupt - Thread.interrupted() after for");
                return;
            }

            handlerCalculated.post(() -> stopCalculation(false));
            Log.d("CollectionFragment", "interrupt - stopCalculation(false) after for");
        });
    }

    @Override
    public void onDetachUI() {
        super.onDetachUI();
        stopExecutor();
    }
}