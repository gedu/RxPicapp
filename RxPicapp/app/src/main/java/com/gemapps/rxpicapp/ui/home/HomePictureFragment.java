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
import com.gemapps.rxpicapp.ui.widget.ErrorFullView;

import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePictureFragment extends PictureLoadMoreListFragment
        implements HomePictureContract.View {

    private static final String TAG = "HomePictureFragment";
    @BindView(R.id.error_view)
    ErrorFullView mErrorView;

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

        //Need to do it in the next frame to let the views can be fully restored
        view.post(() -> mPresenter.onViewCreated(savedInstanceState));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
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
        Toast.makeText(getActivity(), R.string.load_more_error, Toast.LENGTH_SHORT).show();
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

    @Override
    public void showPictureError() {
        showErrorView(R.drawable.ic_sad_cloud,
                R.string.picture_error_message);
    }

    @Override
    public void showConnectionError() {
        showErrorView(R.drawable.ic_error_cloud,
                R.string.connection_error_message);
    }

    @Override
    public void hideErrorView() {
        mErrorView.setVisibility(View.GONE);
    }

    private void showErrorView(int iconDrawable, int message) {
        mErrorView.setErrorIcon(iconDrawable);
        mErrorView.setErrorMessage(message);
        mErrorView.setVisibility(View.VISIBLE);
    }
}
