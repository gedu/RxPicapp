package com.gemapps.rxpicapp.ui.home;

import android.os.Bundle;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.ui.butter.ButterActivity;

public class HomePicturesActivity extends ButterActivity {

    private HomePicturePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pictures);

        HomePictureFragment homeFragment = setupFragment();
        setupPresenter(homeFragment);
    }

    private HomePictureFragment setupFragment() {
        HomePictureFragment homeFragment = (HomePictureFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if(homeFragment == null){
            homeFragment = HomePictureFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction().add(R.id.content_frame, homeFragment)
                    .commit();
        }

        return homeFragment;
    }

    private void setupPresenter(HomePictureFragment homeFragment) {
        mPresenter = new HomePicturePresenter(homeFragment,
                new HomePictureRepository(NetInjector.getHomePictureRequester()));
    }


}
