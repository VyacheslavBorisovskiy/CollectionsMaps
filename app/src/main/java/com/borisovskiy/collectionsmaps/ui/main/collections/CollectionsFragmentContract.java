package com.borisovskiy.collectionsmaps.ui.main.collections;

import com.borisovskiy.collectionsmaps.dto.CalculationDTO;
import com.borisovskiy.collectionsmaps.ui.base.BaseMvpFragmentContract;

import java.util.List;

public class CollectionsFragmentContract {

    public interface IPresenter extends BaseMvpFragmentContract.IPresenter<CollectionsFragmentContract.IView> {

        void startCalculation(String operations, String threads);

        void stopCalculation(boolean forced);

        void showError();
    }

    public interface IView extends BaseMvpFragmentContract.IView {
        boolean hasData();

        void setData(List<CalculationDTO> items);

        void updateItem(String tag, long time);

        void setStateOfButton(boolean state);

        void showProgressBar();

        void hideProgressBar(String tag);

        void hideProgressBar();

        void showToast(String message);

        void showError();
    }

    public interface IHost extends BaseMvpFragmentContract.IHost {

    }
}