package com.gemapps.rxpicapp.ui.home;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.ui.BasePresenter;

/**
 * Created by edu on 4/12/17.
 */

public interface HomePictureContract {

    interface View extends BaseContractView<Presenter> {

        void showProgressBar();
    }

    interface Presenter extends BasePresenter {

        void loadPictures();
    }
}
