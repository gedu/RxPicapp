package com.gemapps.rxpicapp.ui.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.ui.BaseContractView;
import com.gemapps.rxpicapp.ui.butter.ButterActivity;
import com.gemapps.rxpicapp.util.pager.PicturePagerFactory;

public class HomePicturesActivity extends ButterActivity {

    public static final int PAGER_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pictures);

        HomePictureFragment homeFragment = (HomePictureFragment) setupFragment();
        setupPresenter(homeFragment);
    }

    @Override
    protected Fragment getFragment() {
        return HomePictureFragment.newInstance();
    }

    @Override
    protected void setupPresenter(BaseContractView view) {
        new HomePicturePresenter((HomePictureContract.View) view,
                new HomePictureRepository(NetInjector.getHomePictureRequester()),
                PicturePagerFactory.getPager(PAGER_ID));
    }
}
