package com.gemapps.rxpicapp.ui.recycleradapter.comment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Comment;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Created by edu on 5/16/17.
 */

public class CommentAdapter extends BaseCommentAdapter {

    public CommentAdapter(Context context, Picture picture, List<Comment> items) {
        super(context, picture, items);
    }

    @Override
    protected ButterViewHolder getCommentViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item_list, parent, false);
        return new CommentViewHolder(v);
    }

    @Override
    protected void bindCommentViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comment item = mItems.get(position);
        CommentViewHolder cHolder = (CommentViewHolder) holder;

        cHolder.mAuthorName.setText(item.getAuthorName());
        cHolder.mTime.setText(item.getDateCreated() == null ? "" :
                DateUtils.getRelativeTimeSpanString(item.getPicDateFormatted().getTime(),
                        System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS));
        cHolder.mMsg.setText(Html.fromHtml(item.getMsg()));

        Picasso.with(mContext)
                .load(item.getIconUrl())
                .error(R.drawable.ic_face_black_48px)
                .placeholder(R.drawable.ic_face_black_48px)
                .into(cHolder.mUserIcon);
    }

    public class CommentViewHolder extends ButterViewHolder {

        @BindView(R.id.user_icon_image)
        ImageView mUserIcon;
        @BindView(R.id.author_name)
        TextView mAuthorName;
        @BindView(R.id.comment_time)
        TextView mTime;
        @BindView(R.id.comment_text)
        TextView mMsg;

        public CommentViewHolder(View view) {
            super(view);
            mMsg.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }
}
