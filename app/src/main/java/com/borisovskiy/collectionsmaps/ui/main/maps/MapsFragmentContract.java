package com.borisovskiy.collectionsmaps.ui.main.maps;

import com.borisovskiy.collectionsmaps.dto.CalculationDTO;
import com.borisovskiy.collectionsmaps.ui.base.BaseMvpFragmentContract;

import java.util.List;

public class MapsFragmentContract {

    public interface IPresenter extends BaseMvpFragmentContract.IPresenter<MapsFragmentContract.IView> {

        void startCalculation(String operations, String threads);

        void stopCalculation();
    }

    public interface IView extends BaseMvpFragmentContract.IView {

        void setData(List<CalculationDTO> items);

        void updateItem(String tag, long time);

        void setStateOfButton(boolean state);

        void showProgressBar();

        void hideProgressBar(String tag);

        void hideProgressBar();

        void showToast(String message);

        void setOperationsValid(boolean isValid);
    }

    public interface IHost extends BaseMvpFragmentContract.IHost {

    }
}