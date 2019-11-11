package com.borisovskiy.collectionsmaps.ui.base;


public class BaseMvpFragmentContract {

    public interface IPresenter<T extends BaseMvpFragmentContract.IView> {

        void onAttachUI(T view);

        void onDetachUI();
    }

    public interface IView {

    }

    public interface IHost {

    }
}