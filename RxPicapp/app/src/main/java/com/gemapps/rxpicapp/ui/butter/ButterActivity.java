package com.gemapps.rxpicapp.ui.butter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.gemapps.rxpicapp.BaseContractView;
import com.gemapps.rxpicapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by edu on 4/12/17.
 */

public abstract class ButterActivity extends AppCompatActivity {

    @Nullable @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        if(mToolbar != null){
            setSupportActionBar(mToolbar);
        }
    }

    protected Fragment setupFragment() {
        Fragment fragment = getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if(fragment == null){
            fragment = getFragment();
            getSupportFragmentManager()
                    .beginTransaction().add(R.id.content_frame, fragment)
                    .commit();
        }

        return fragment;
    }

    protected abstract Fragment getFragment();
    protected abstract void setupPresenter(BaseContractView view);

}
