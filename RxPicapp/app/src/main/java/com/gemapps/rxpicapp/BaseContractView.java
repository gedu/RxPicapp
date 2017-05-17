package com.gemapps.rxpicapp;

import android.content.Context;

import com.gemapps.rxpicapp.ui.BasePresenter;

/**
 * Created by edu on 4/12/17.
 */
public interface BaseContractView<T extends BasePresenter> {

    void setPresenter(T presenter);

    Context getContext();
}
