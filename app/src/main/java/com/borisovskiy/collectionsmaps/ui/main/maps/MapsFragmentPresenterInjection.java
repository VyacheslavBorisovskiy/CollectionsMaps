package com.borisovskiy.collectionsmaps.ui.main.maps;

import com.borisovskiy.collectionsmaps.model.Model;

public class MapsFragmentPresenterInjection {

    public static MapsFragmentContract.IPresenter getPresenter() {
        return new MapsFragmentPresenter(new Model());
    }
}