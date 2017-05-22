package com.gemapps.rxpicapp.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.PictureLoadMoreListFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePictureFragment extends PictureLoadMoreListFragment
        implements HomePictureContract.View {

    private static final String TAG = "HomePictureFragment";

    private HomePictureContract.Presenter mPresenter;

    public HomePictureFragment() {
        // Required empty public constructor
    }

    public static HomePictureFragment newInstance() {
        return new HomePictureFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.onViewCreated(savedInstanceState);
    }

    @Override
    public void onPause() {
        mPresenter.dispose();
        super.onPause();
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mPresenter.loadPictures();
    }

    @Override
    protected void onLoadMoreError() {
        Toast.makeText(getActivity(), "Failed to loaind more", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onClickPicture(Picture picture) {
        mPresenter.onClickPicture(picture);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_picture_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mPresenter.optionSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(HomePictureContract.Presenter presenter) {
        mPresenter = presenter;
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
        addItems(pictures);
    }

    @Override
    public void showPictureDetail(Intent intent) {
        startActivity(intent);
    }

    @Override
    public boolean isLoadingMore() {
        return mPictureRecycler.isLoadingMore();
    }

    @Override
    public void swapPicturesListView(MenuItem item) {
        super.swapPicturesListView(item);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home_picture;
    }

    @Override
    public boolean isLinearLayout() {
        return mPictureRecycler.isLinearLayout();
    }

    @Override
    public void startSearch(Intent intent) {
        startActivity(intent);
    }
}
