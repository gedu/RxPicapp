package com.gemapps.rxpicapp.ui.home;

/**
 * Created by edu on 4/12/17.
 */

public class HomePicturePresenter implements HomePictureContract.Presenter {

    private HomePictureContract.View mView;

    public HomePicturePresenter(HomePictureContract.View homeView) {
        mView = homeView;

        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.showProgressBar();
    }
}
