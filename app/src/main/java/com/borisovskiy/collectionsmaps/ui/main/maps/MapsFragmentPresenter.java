package com.borisovskiy.collectionsmaps.ui.main.maps;

import android.os.Handler;
import android.text.TextUtils;

import com.borisovskiy.collectionsmaps.model.IModel;
import com.borisovskiy.collectionsmaps.ui.base.BaseMvpFragmentPresenter;
import com.borisovskiy.collectionsmaps.utils.DataUtility;
import com.borisovskiy.collectionsmaps.utils.MapsTimeCalculator;

import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class MapsFragmentPresenter extends BaseMvpFragmentPresenter<MapsFragmentContract.IView>
        implements MapsFragmentContract.IPresenter {

    private final IModel model;
    private ThreadPoolExecutor threadPoolExecutor;

    public MapsFragmentPresenter(IModel model) {
        this.model = model;
    }

    @Override
    public void onAttachUI(MapsFragmentContract.IView view) {
        super.onAttachUI(view);
        view.setData(model.getMapsItems());
    }

    @Override
    public void stopCalculation() {
        stopExecutor();
        if (view == null) return;
        view.hideProgressBar();
        view.setStateOfButton(false);
    }

    private void stopExecutor() {
        if (threadPoolExecutor == null) return;
        threadPoolExecutor.shutdownNow();
        threadPoolExecutor = null;
    }

    @Override
    public void startCalculation(String operations, String threads) {
        if (TextUtils.isEmpty(operations)) {
            view.setOperationsValid(false);
            return;
        } else view.setOperationsValid(true);

        final Handler handlerCalculated = new Handler();
        final List<String> tags = model.getMapsTags();

        view.showProgressBar();
        view.showToast("Calculation started");

        threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(Integer.valueOf(threads));
        threadPoolExecutor.execute(() -> {
            DataUtility.fillMap(new TreeMap<>(), Integer.valueOf(operations));
            DataUtility.fillMap(new HashMap<>(), Integer.valueOf(operations));

            for (String tag : tags) {
                if (Thread.interrupted()) {
                    handlerCalculated.post(() -> {
                        stopCalculation();
                        view.showToast("Calculation stopped");
                    });
                    return;
                }
                try {
                    Thread.sleep(1000L);
                } catch (IllegalArgumentException | InterruptedException e) {
                    e.printStackTrace();
                    handlerCalculated.post(() -> {
                        stopCalculation();
                        view.showToast("Calculation stopped");
                    });
                    return;
                }
                handlerCalculated.post(() -> {
                    if (view == null) {
                        stopExecutor();
                        return;
                    }
                    view.updateItem(tag, MapsTimeCalculator.calculateTime(tag));
                    view.hideProgressBar(tag);
                });
            }
            handlerCalculated.post(() -> {
                stopCalculation();
                view.showToast("Calculation completed");
            });
        });
    }

    @Override
    public void onDetachUI() {
        super.onDetachUI();
        stopExecutor();
    }
}
