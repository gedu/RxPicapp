package com.gemapps.rxpicapp.ui.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.ui.butter.ButterFragment;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePictureFragment extends ButterFragment implements HomePictureContract.View {

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    public HomePictureFragment() {
        // Required empty public constructor
    }

    public static HomePictureFragment newInstance() {
        return new HomePictureFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return onCreateView(inflater, container, R.layout.fragment_home_picture);
    }

    @Override
    public void setPresenter(HomePictureContract.Presenter presenter) {

    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }
}
