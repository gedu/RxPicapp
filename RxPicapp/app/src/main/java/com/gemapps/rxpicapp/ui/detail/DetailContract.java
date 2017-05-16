package com.gemapps.rxpicapp.ui.detail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.ui.BasePresenter;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;

/**
 * Created by edu on 5/16/17.
 */

public interface DetailContract {

    interface View extends BaseContractView<Presenter> {

        void loadPicture(String url);

        Context getContext();

        void setAdapter(RecyclerView.Adapter<ButterViewHolder> adapter);
    }

    interface Presenter extends BasePresenter {

    }
}
