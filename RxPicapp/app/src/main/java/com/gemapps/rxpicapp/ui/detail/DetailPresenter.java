package com.gemapps.rxpicapp.ui.detail;

import android.support.v7.widget.RecyclerView;

import com.gemapps.rxpicapp.data.detailsource.DetailRepository;
import com.gemapps.rxpicapp.model.Comment;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;
import com.gemapps.rxpicapp.ui.recycleradapter.comment.CommentAdapter;
import com.gemapps.rxpicapp.ui.recycleradapter.comment.LoadingCommentAdapter;
import com.gemapps.rxpicapp.ui.recycleradapter.comment.NoCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by edu on 5/16/17.
 */

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;
    private DetailRepository mRepository;
    private Picture mPicture;
    private Disposable mSubscriber;

    public DetailPresenter(DetailContract.View view, DetailRepository repository,
                           Picture picture) {
        mView = view;
        mRepository = repository;
        mPicture = picture;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {
        mView.loadPicture(mPicture.getUrl());

        if (mPicture.getCountComments() > 0) loadComments();
        else mView.setAdapter(getNoCommentsAdapter());
    }

    private void loadComments() {
        mView.setAdapter(getLoadingAdapter());

        mSubscriber = mRepository.getComments()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Comment>>() {
                    @Override
                    public void onNext(List<Comment> comments) {
                        mView.setAdapter(getCommentAdapter(comments));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private RecyclerView.Adapter<ButterViewHolder> getNoCommentsAdapter() {
        return new NoCommentAdapter(mView.getContext(), mPicture, new ArrayList<>());
    }

    private RecyclerView.Adapter<ButterViewHolder> getLoadingAdapter() {
        return new LoadingCommentAdapter( mView.getContext(), mPicture, new ArrayList<>());
    }

    private RecyclerView.Adapter<ButterViewHolder> getCommentAdapter(List<Comment> comments) {
        return new CommentAdapter(mView.getContext(), mPicture, comments);
    }

    private void clearSubscription() {
        if(mSubscriber != null) mSubscriber.dispose();
    }

    @Override
    public void unSubscribe() {
        clearSubscription();
    }
}
