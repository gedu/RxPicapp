package com.gemapps.rxpicapp.ui.recycleradapter.comment;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Comment;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by edu on 5/16/17.
 */
public abstract class BaseCommentAdapter extends RecyclerView.Adapter<ButterViewHolder> {

    public interface BaseCommentListener {
        void onCommentClick(ImageView imageView);
    }

    public static final int HEADER_VIEW_TYPE = 0;
    public static final int COMMENT_VIEW_TYPE = 1;

    protected final Context mContext;
    protected List<Comment> mItems;
    private Picture mPicture;
    private Resources mResources;
    private BaseCommentListener mListener;
    private HeaderViewHolder mHeaderHolder;

    public BaseCommentAdapter(Context context, Picture picture, List<Comment> items) {
        mContext = context;
        mItems = items;
        mPicture = picture;
        mResources = context.getResources();

        //Adding the header element
        this.mItems.add(0, new Comment());
    }

    public void setListener(BaseCommentListener listener){
        mListener = listener;
    }

    @Override
    public ButterViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        if(viewType == HEADER_VIEW_TYPE) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.picture_item_list_header, parent, false);
            return new BaseCommentAdapter.HeaderViewHolder(v);

        }else {
            return getCommentViewHolder(parent, viewType);
        }
//        View v = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.comment_item_list, parent, false);
//        return new CommentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ButterViewHolder holder, int position) {

        if(position == 0){

            mHeaderHolder = (HeaderViewHolder) holder;

            final int commentCount = mPicture.getCountComments();
            final int favesCount = mPicture.getCountFaves();

            mHeaderHolder.mUsernameView.setText(mPicture.getOwnerName());
            mHeaderHolder.mPicTakenDateView.setText(mPicture.getDateTaken());
            mHeaderHolder.mTitleView.setText(mPicture.getTitle());
            mHeaderHolder.mCommentView.setText(mResources.getQuantityString(R.plurals.comments,
                    commentCount, commentCount));
            mHeaderHolder.mFavesView.setText(mResources.getQuantityString(R.plurals.faves, favesCount,
                    favesCount));

//            if(mUserItem != null) {
//                Picasso.with(mContext)
//                        .load(mUserItem.getIconUrl())
//                        .placeholder(R.drawable.ic_buddy)
//                        .error(R.drawable.ic_buddy)
//                        .transform(new CircleTransform()).into(mHeaderHolder.mIconView);
//            }

        }else{

            bindCommentViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER_VIEW_TYPE : COMMENT_VIEW_TYPE;
    }

    protected abstract ButterViewHolder getCommentViewHolder(ViewGroup parent, int viewType);

    protected abstract void bindCommentViewHolder(RecyclerView.ViewHolder holder, int position);

    public class HeaderViewHolder extends ButterViewHolder {

        @BindView(R.id.author_container) View mContainer;
        @BindView(R.id.user_icon_image) ImageView mIconView;
        @BindView(R.id.user_name_text)
        TextView mUsernameView;
        @BindView(R.id.pic_title_text) TextView mTitleView;
        @BindView(R.id.pic_comments) TextView mCommentView;
        @BindView(R.id.pic_faves) TextView mFavesView;
        @BindView(R.id.pic_date_taken) TextView mPicTakenDateView;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @OnClick(R.id.user_icon_image)
        public void onPlayerClicked(ImageView view){

//            if (mListener != null)mListener.onPlayerClicked(view);
        }

        @OnClick(R.id.author_container)
        public void onContainerClicked(){

//            if (mListener != null)mListener.onPlayerClicked(mIconView);
        }

    }
}