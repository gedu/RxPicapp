package com.gemapps.rxpicapp.ui;

import android.os.Bundle;

/**
 * Created by edu on 4/12/17.
 */

public interface BasePresenter {

    void load();
    void onViewCreated(Bundle savedState);
    void dispose();
}
