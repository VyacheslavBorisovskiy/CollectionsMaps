package com.borisovskiy.collectionsmaps.ui.base;


public class BaseMvpFragmentPresenter<T extends BaseMvpFragmentContract.IView>
        implements BaseMvpFragmentContract.IPresenter<T> {

    protected T view;

    @Override
    public void onAttachUI(T view) {
        this.view = view;
    }

    @Override
    public void onDetachUI() {
        view = null;
    }
}