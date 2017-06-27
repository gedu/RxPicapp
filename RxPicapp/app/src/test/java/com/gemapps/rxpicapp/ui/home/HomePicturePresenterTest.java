package com.gemapps.rxpicapp.ui.home;

import android.util.Log;

import com.gemapps.rxpicapp.data.homesource.HomePictureRepository;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.networking.NetInjector;
import com.gemapps.rxpicapp.util.pager.PicturePager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subscribers.TestSubscriber;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by edu on 4/13/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class HomePicturePresenterTest {
    private static final String TAG = "HomePicturePresenterTes";

    @Mock
    private HomePictureContract.View mView;

    @Mock
    private PicturePager mPager;

    private HomePictureRepository mRepository;
    private HomePicturePresenter mPresenter;

    @Before
    public void setup(){
        mRepository = spy(new HomePictureRepository(NetInjector.getHomePictureRequester()));//mock(HomePictureRepository.class);
//        mRepository.setRemoteDataSource(NetInjector.getHomePictureRequester());
        mPresenter = new HomePicturePresenter(mView, mRepository, mPager);
    }

    @Test
    public void presenterSetupItselfToView(){
        verify(mView).setPresenter(mPresenter);
    }

    @Test
    public void showProgressBarAtStart() {
        mPresenter.load();
        verify(mView).showProgressBar();
    }

    @Test
    public void loadPictures() {

//        when(mRepository.getPictures(mPager.getCurrentPage())).thenReturn(NetInjector.getHomePictureRequester().getPictures(1));
        mPresenter.loadPictures();
//        mRepository.getPictures(1).test().assertValueCount(5);
//        TestSubscriber<List<Picture>> testSubscriber = new TestSubscriber<>();
//        ConnectableObservable c = mRepository.getPictures(1);
//        c.subscribe(testSubscriber);
//        c.connect();
//        testSubscriber.assertNoErrors();
//        verify(mView).addPictures(Collections.emptyList());
        verify(mView, never()).hideProgress();
        verify(mView, never()).hideErrorView();
    }
}