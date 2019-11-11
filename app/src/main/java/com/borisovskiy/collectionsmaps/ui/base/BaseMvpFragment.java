package com.borisovskiy.collectionsmaps.ui.base;

import android.content.Context;

import androidx.fragment.app.Fragment;

public abstract class BaseMvpFragment<T extends BaseMvpFragmentContract.IPresenter, H extends BaseMvpFragmentContract.IHost>
        extends Fragment implements BaseMvpFragmentContract.IView {

    protected H host;
    protected T presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        host = (H) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        host = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onAttachUI(this);
    }

    @Override
    public void onStop() {
        presenter.onDetachUI();
        super.onStop();
    }
}