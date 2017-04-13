package com.gemapps.rxpicapp.ui.home;

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

    private HomePicturePresenter mPresenter;

    @Before
    public void setup(){
        mPresenter = new HomePicturePresenter(mView);
    }

    @Test
    public void presenterSetupItselfToView(){
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void showProgressBarAtStart(){
        mPresenter.start();
        verify(mView).showProgressBar();
    }
}