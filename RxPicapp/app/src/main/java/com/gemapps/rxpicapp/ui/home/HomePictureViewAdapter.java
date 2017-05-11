package com.gemapps.rxpicapp.ui.home;

import android.content.Context;
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
        extends BaseRecyclerViewAdapter<HomePictureViewAdapter.HomePictureViewHolder> {
    private static final String TAG = "HomePictureViewAdapter";
    private final Context mContext;
    private List<Picture> mItems;

    public HomePictureViewAdapter(Context context, List<Picture> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public HomePictureViewHolder onCreateViewHolder(ViewGroup parent,
                                                    int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_picture_item_list, parent, false);
        return new HomePictureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HomePictureViewHolder holder, int position) {
        Picture item = mItems.get(position);

        holder.mAuthorName.setText(item.getOwnerName());
        holder.mPicTitle.setText(item.getTitle());
        Picasso.with(mContext).load(item.getUrl()).into(holder.mPicImage);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    public void addPictures(List<Picture> pictures) {
        int currentAmount = mItems.size();
        mItems.addAll(currentAmount, pictures);
        notifyItemRangeInserted(currentAmount, pictures.size());
    }

    public class HomePictureViewHolder extends ButterViewHolder {

        @BindView(R.id.author_name)
        TextView mAuthorName;

        @BindView(R.id.pic_title_text)
        TextView mPicTitle;

        @BindView(R.id.pic_image)
        ImageView mPicImage;

        public HomePictureViewHolder(View view) {
            super(view);
        }
    }
}