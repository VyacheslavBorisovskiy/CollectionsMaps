package com.borisovskiy.collectionsmaps.ui.main.collections;

import com.borisovskiy.collectionsmaps.model.Model;

class CollectionFragmentPresenterInjection {

    public static CollectionsFragmentContract.IPresenter getPresenter() {
        return new CollectionsFragmentPresenter(new Model());
    }
}