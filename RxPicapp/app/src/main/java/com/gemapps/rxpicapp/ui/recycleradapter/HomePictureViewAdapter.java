package com.gemapps.rxpicapp.ui.recycleradapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemapps.rxpicapp.R;
import com.gemapps.rxpicapp.model.Picture;
import com.gemapps.rxpicapp.ui.butter.ButterViewHolder;
import com.gemapps.rxpicapp.ui.widget.BaseRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Created by edu on 4/27/17.
 */
public class HomePictureViewAdapter
        extends BaseRecyclerViewAdapter<ButterViewHolder> {
    private static final String TAG = "HomePictureViewAdapter";

    public interface PictureAdapterListener {
        void onClick(Picture picture);
    }

    public static final int VIEW_PICTURE_TYPE = 1;
    private final Context mContext;
    private List<Picture> mItems;
    private PictureAdapterListener mListener;

    public HomePictureViewAdapter(Context context, List<Picture> items,
                                  PictureAdapterListener listener) {
        mContext = context;
        mItems = items;
        mListener = listener;
    }

    @Override
    public ButterViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        if(!isBottomProgressType(viewType)) return onCreatePictureHolder(parent);
        else return onCreateBottomProgressBar(parent);
    }

    private ButterViewHolder onCreatePictureHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_picture_item_list, parent, false);
        return new HomePictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ButterViewHolder holder, int position) {
        if(!isBottomProgressHolder(holder)) {
            HomePictureViewHolder pictureHolder = (HomePictureViewHolder) holder;
            Picture item = mItems.get(position);
            if(item.getAuthor() != null) Log.d(TAG, "onBindViewHolder: "+ item.getAuthor().getIconUrl());
            pictureHolder.mAuthorName.setText(item.getOwnerName());
            pictureHolder.mPicTitle.setText(item.getTitle());
            Picasso.with(mContext).load(item.getUrl()).into(pictureHolder.mPicImage);
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItems.get(position) == null ? VIEW_LOADING_TYPE: VIEW_PICTURE_TYPE;
    }

    @Override
    public void addBottomProgress() {
        mItems.add(null);
        notifyItemInserted(mItems.size());
    }

    @Override
    public void removeBottomProgress() {
        mItems.remove(null);
        notifyItemRemoved(mItems.size() + 1);
    }

    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    public void addPictures(List<Picture> pictures) {
        int currentAmount = mItems.size();
        mItems.addAll(currentAmount, pictures);
        notifyItemRangeInserted(currentAmount, pictures.size());
    }

    public class HomePictureViewHolder extends ButterViewHolder
            implements View.OnClickListener {

        @BindView(R.id.author_name)
        TextView mAuthorName;

        @BindView(R.id.pic_title_text)
        TextView mPicTitle;

        @BindView(R.id.pic_image)
        ImageView mPicImage;

        public HomePictureViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null) mListener.onClick(mItems.get(getAdapterPosition()));
        }
    }
}