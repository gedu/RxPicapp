package com.gemapps.rxpicapp.ui.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.data.detailsource.DetailRepository;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.ui.butter.ButterActivity;

public class DetailActivity extends ButterActivity {

    public static final String PICTURE = "rxpicapp.PICTURE";

    public static Intent newInstance(Context context, Picture picture) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(PICTURE, picture);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailFragment detailFragment = (DetailFragment) setupFragment();
        setupPresenter(detailFragment);
    }

    private Picture getPictureFrom() {
        if(getIntent().hasExtra(PICTURE))
            return getIntent().getParcelableExtra(PICTURE);

        return null;
    }

    @Override
    protected Fragment getFragment() {
        return DetailFragment.newInstance();
    }

    @Override
    protected void setupPresenter(BaseContractView view) {
        new DetailPresenter((DetailContract.View) view,
                new DetailRepository(NetInjector.getCommentRequester()),
                getPictureFrom());
    }
}
