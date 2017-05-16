package com.gemapps.rxpicapp.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.data.searchsource.SearchRepository;
import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.ui.butter.ButterActivity;
import com.gemapps.rxpicapp.util.PicturePager;

public class SearchActivity extends ButterActivity {

    private SearchFragment mSearchFragment;

    public static Intent newInstance(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mSearchFragment = (SearchFragment) setupFragment();
        setupPresenter(mSearchFragment);
    }

    @Override
    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if(intent.hasExtra(SearchManager.QUERY)) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (!TextUtils.isEmpty(query)) {
                mSearchFragment.onNewSearch(query);
            }
        }
    }

    @Override
    protected Fragment getFragment() {
        return SearchFragment.newInstance();
    }

    @Override
    protected void setupPresenter(BaseContractView view) {

        new SearchPresenter(
                new SearchRepository(NetInjector.getSearchRequester()),
                (SearchContract.View) view,
                new PicturePager());
    }
}
