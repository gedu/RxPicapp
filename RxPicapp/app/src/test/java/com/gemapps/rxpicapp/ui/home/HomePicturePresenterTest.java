package com.gemapps.rxpicapp.ui.home;

import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.util.PicturePager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by edu on 4/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomePicturePresenterTest {

    @Mock
    private HomePictureContract.View mView;

    @Mock
    private HomePictureRepository mRepository;

    @Mock
    private PicturePager mPager;

    private HomePicturePresenter mPresenter;

    @Before
    public void setup(){
        mPresenter = new HomePicturePresenter(mView, mRepository, mPager);
    }

    @Test
    public void presenterSetupItselfToView(){
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void showProgressBarAtStart(){
        mPresenter.load();
        verify(mView).showProgressBar();
    }
}