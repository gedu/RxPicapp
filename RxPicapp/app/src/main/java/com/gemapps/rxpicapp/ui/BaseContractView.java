package com.gemapps.rxpicapp.ui;

import android.content.Context;

/**
 * Created by edu on 4/12/17.
 */
public interface BaseContractView<T extends com.gemapps.rxpicapp.ui.BasePresenter> {

    void setPresenter(T presenter);

    Context getContext();
}
