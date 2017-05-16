package com.gemapps.rxpicapp.ui.recycleradapter.comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Comment;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;

import java.util.List;

/**
 * Created by edu on 5/16/17.
 */

public class LoadingCommentAdapter extends BaseCommentAdapter {

    public LoadingCommentAdapter(Context context, Picture picture, List<Comment> items) {
        super(context, picture, items);
        mItems.add(new Comment());
    }

    @Override
    protected ButterViewHolder getCommentViewHolder(ViewGroup parent, int viewType) {

        return new LoadingViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.progress_item_list, parent, false));
    }

    @Override
    protected void bindCommentViewHolder(RecyclerView.ViewHolder holder, int position) {}


    private class LoadingViewHolder extends ButterViewHolder {

        public LoadingViewHolder(View itemView) {
            super(itemView);
        }
    }
}
