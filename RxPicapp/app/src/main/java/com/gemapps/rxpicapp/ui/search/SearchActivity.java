package com.gemapps.rxpicapp.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.data.searchsource.SearchRepository;
import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.ui.BaseContractView;
import com.gemapps.rxpicapp.ui.butter.ButterActivity;
import com.gemapps.rxpicapp.util.pager.PicturePagerFactory;

public class SearchActivity extends ButterActivity {

    public static final int PAGER_ID = 2;
    private static final String IS_LINEAR_KEY = "rxpicapp.IS_LINEAR";
    private SearchFragment mSearchFragment;
    private boolean mIsLinearLayout = true;

    public static Intent newInstance(Context context, boolean isLinear) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(IS_LINEAR_KEY, isLinear);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setIntentParams();
        mSearchFragment = (SearchFragment) setupFragment();
        setupPresenter(mSearchFragment);
    }

    private void setIntentParams() {
        if (getIntent().hasExtra(IS_LINEAR_KEY)) {
            mIsLinearLayout = getIntent().getBooleanExtra(IS_LINEAR_KEY, true);
        }
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
        return SearchFragment.newInstance(mIsLinearLayout);
    }

    @Override
    protected void setupPresenter(BaseContractView view) {

        new SearchPresenter(
                new SearchRepository(NetInjector.getSearchRequester()),
                (SearchContract.View) view,
                PicturePagerFactory.getPager(PAGER_ID));
    }
}
