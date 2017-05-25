package com.gemapps.rxpicapp.ui.home;

import android.content.Intent;
import android.view.MenuItem;

import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.BaseContractView;
import com.gemapps.rxpicapp.ui.BasePresenter;

import java.util.List;

/**
 * Created by edu on 4/12/17.
 */

public interface HomePictureContract {

    interface View extends BaseContractView<Presenter> {

        void showProgressBar();

        void hideProgress();

        void addPictures(List<Picture> pictures);

        void showPictureDetail(Intent intent);

        boolean isLoadingMore();

        void swapPicturesListView(MenuItem item);

        boolean isLinearLayout();

        void startSearch(Intent intent);

        void showPictureError();

        void showConnectionError();

        void hideErrorView();
    }

    interface Presenter extends BasePresenter {

        void loadPictures();

        void onClickPicture(Picture picture);

        boolean optionSelected(MenuItem item);
    }
}
