package com.gemapps.rxpicapp.ui.search;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.BasePresenter;

import java.util.List;

/**
 * Created by edu on 5/14/17.
 */

public interface SearchContract {

    interface View extends BaseContractView<Presenter> {

        void showProgressBar();

        void hideProgressBar();

        void addPictures(List<Picture> pictures);
    }

    interface Presenter extends BasePresenter {
        void searchFor(String query);
    }
}
