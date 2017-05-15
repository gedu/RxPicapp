package com.gemapps.rxpicapp.ui.home;

import android.support.v4.app.Fragment;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.BasePresenter;

import java.util.List;

/**
 * Created by edu on 4/12/17.
 */

public interface HomePictureContract {

    interface View extends BaseContractView<Presenter> {

        static Fragment newInstance(){
            return null;
        }

        void showProgressBar();

        void hideProgress();

        void addPictures(List<Picture> pictures);
    }

    interface Presenter extends BasePresenter {

        void loadPictures();
    }
}
