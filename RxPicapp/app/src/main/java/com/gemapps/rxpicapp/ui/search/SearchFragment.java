package com.gemapps.rxpicapp.ui.search;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.PictureLoadMoreListFragment;
import com.gemapps.rxpicapp.util.AnimUtil;

import java.util.List;

import butterknife.BindView;

import static android.content.Context.SEARCH_SERVICE;
import static android.view.inputmethod.InputMethodManager.SHOW_FORCED;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends PictureLoadMoreListFragment
        implements SearchContract.View {

    private static final String TAG = "SearchFragment";
    @BindView(R.id.search_background)
    View mSearchBackground;
    @BindView(R.id.back_button)
    ImageButton mBackButton;
    @BindView(R.id.search_view)
    SearchView mSearchView;

    private SearchContract.Presenter mPresenter;

    public SearchFragment() {
        // Required empty public constructor
    }

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return onCreateView(inflater, container, R.layout.fragment_search);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSearchView();
        AnimUtil.fadeAnimation(mSearchBackground).start();
        AnimUtil.fadeAnimation(mSearchView).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mSearchView.requestFocus();
                InputMethodManager imm = (InputMethodManager) mSearchView.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mSearchView, SHOW_FORCED);
            }
        }).start();
        AnimUtil.fadeAnimation(mBackButton).start();
    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.subscribe();
    }

    @Override
    public void onPause() {
        mPresenter.unSubscribe();
        super.onPause();
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
    }

    @Override
    public void showProgressBar() {
        Log.d(TAG, "showProgressBar: ");
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        Log.d(TAG, "hideProgressBar: ");
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void addPictures(List<Picture> pictures) {
        Log.d(TAG, "addPictures: ");
        mPictureRecycler.setVisibility(View.VISIBLE);
        addItems(pictures);
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
