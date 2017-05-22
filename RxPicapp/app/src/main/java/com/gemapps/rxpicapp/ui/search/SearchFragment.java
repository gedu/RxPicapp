package com.gemapps.rxpicapp.ui.search;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageButton;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.PictureLoadMoreListFragment;
import com.gemapps.rxpicapp.ui.widget.LinearToGridRecycler;
import com.gemapps.rxpicapp.util.AnimUtil;
import com.gemapps.rxpicapp.util.ImmUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.SEARCH_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends PictureLoadMoreListFragment
        implements SearchContract.View {

    private static final String TAG = "SearchFragment";
    private static final String IS_LINEAR_KEY = "rxpicapp.IS_LINEAR_KEY";
    @BindView(R.id.search_background)
    View mSearchBackground;
    @BindView(R.id.back_button)
    ImageButton mBackButton;
    @BindView(R.id.search_view)
    SearchView mSearchView;
    @BindView(R.id.picture_recycler)
    LinearToGridRecycler mRecycler;

    private boolean mIsLinear;
    private SearchContract.Presenter mPresenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance(boolean isLinearLayout) {
        SearchFragment fragment = new SearchFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_LINEAR_KEY, isLinearLayout);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            mIsLinear = getArguments().getBoolean(IS_LINEAR_KEY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSearchView();
        setupRecycler();
        AnimUtil.fadeAnimation(mSearchBackground).start();
        AnimUtil.fadeAnimation(mSearchView).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mSearchView.requestFocus();
                ImmUtil.showIme(mSearchView);
            }
        }).start();
        AnimUtil.fadeAnimation(mBackButton).start();
    }

    private void setupRecycler() {
        if(!mIsLinear) mRecycler.swapListStyle();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.load();
    }

    @Override
    public void onPause() {
        mPresenter.dispose();
        super.onPause();
    }

    @OnClick(R.id.back_button)
    public void onBackClick() {
        getActivity().onBackPressed();
    }

    private void setupSearchView() {
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        // hint, inputType & ime options seem to be ignored from XML! Set in code
        mSearchView.setQueryHint(getString(R.string.search_hint));
        mSearchView.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        mSearchView.setImeOptions(mSearchView.getImeOptions() | EditorInfo.IME_ACTION_SEARCH |
                EditorInfo.IME_FLAG_NO_EXTRACT_UI | EditorInfo.IME_FLAG_NO_FULLSCREEN);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setIconified(false);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showProgressBar();
                mPresenter.searchFor(query);
                ImmUtil.hideIme(mSearchView);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (TextUtils.isEmpty(query)) {
                    clearResults();
                }
                return true;
            }
        });
    }

    private void clearResults() {
        super.clear();
        hideProgressBar();
        mPictureRecycler.setVisibility(View.GONE);
    }

    public void onNewSearch(String query){
        mSearchView.setQuery(query, false);
        mPresenter.searchFor(query);
        ImmUtil.hideIme(mSearchView);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void addPictures(List<Picture> pictures) {
        mPictureRecycler.setVisibility(View.VISIBLE);
        addItems(pictures);
    }

    @Override
    public void showPictureDetail(Intent intent) {
        startActivity(intent);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_search;
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        mPresenter.loadMore();
    }

    @Override
    protected void onLoadMoreError() {

    }

    @Override
    protected void onClickPicture(Picture picture) {
        mPresenter.onClickPicture(picture);
    }
}
