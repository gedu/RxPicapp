package com.gemapps.rxpicapp.ui.home;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.PictureLoadMoreListFragment;
import com.gemapps.rxpicapp.ui.search.SearchActivity;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return onCreateView(inflater, container, R.layout.fragment_home_picture);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected void onLoadMore() {
        Log.d(TAG, "onNext() called");
        super.onLoadMore();
        mPresenter.loadPictures();
    }

    @Override
    protected void onLoadMoreError() {
        Toast.makeText(getActivity(), "Failed to laod more", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadPictures();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.home_picture_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())  {
            case R.id.action_swap_list:
                swapPicturesListView(item);
                return true;
            case R.id.action_search:
                startActivity(SearchActivity.newInstance(getContext()));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
}
