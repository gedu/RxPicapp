package com.gemapps.rxpicapp;

import com.gemapps.rxpicapp.ui.BasePresenter;

/**
 * Created by edu on 4/12/17.
 */
public interface BaseContractView<T extends BasePresenter> {

    void setPresenter(T presenter);
}
