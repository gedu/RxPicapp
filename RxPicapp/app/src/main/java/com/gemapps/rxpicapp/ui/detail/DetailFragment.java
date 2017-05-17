package com.gemapps.rxpicapp.ui.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.ui.butter.ButterFragment;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends ButterFragment
    implements DetailContract.View {

    @BindView(R.id.picture_image)
    ImageView mPictureView;

    @BindView(R.id.recycler_view)
    RecyclerView mCommentsView;

    private DetailContract.Presenter mPresenter;

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return onCreateView(inflater, container, R.layout.fragment_detail);
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.subscribe();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        mCommentsView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onPause() {
        mPresenter.unSubscribe();
        super.onPause();
    }

    @OnClick(R.id.back_button)
    public void onBackClick() {
        getActivity().onBackPressed();
    }

    @Override
    public void loadPicture(String url) {
        Picasso.with(getContext())
                .load(url)
                .into(mPictureView);
    }

    @Override
    public void setAdapter(RecyclerView.Adapter<ButterViewHolder> adapter) {
        mCommentsView.setAdapter(adapter);
    }
}
