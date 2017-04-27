package com.gemapps.rxpicapp.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.ButterFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePictureFragment extends ButterFragment
        implements HomePictureContract.View {

    private static final String TAG = "HomePictureFragment";

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.home_picture_recycler)
    RecyclerView mPictureRecycler;

    private HomePictureContract.Presenter mPresenter;
    private HomePictureViewAdapter mAdapter;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mAdapter = new HomePictureViewAdapter(getActivity(), new ArrayList<Picture>());
        mPictureRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 2,
                LinearLayoutManager.VERTICAL, false));
        mPictureRecycler.setAdapter(mAdapter);
    }

    @Override
    public void setPresenter(HomePictureContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPictures();
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void addPictures(List<Picture> pictures) {
        Log.d(TAG, "addPictures() called with: pictures = <" + pictures.size() + ">");
        mAdapter.addPictures(pictures);
    }
}
