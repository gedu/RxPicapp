package com.gemapps.rxpicapp.util.pager;

import android.util.Log;
import android.util.SparseArray;

/**
 * Created by edu on 5/23/17.
 * Fast hack to preserve the state of the Pagers onConfigurationChange
 */
public class PicturePagerFactory {

    private static final String TAG = "PicturePagerFactory";
    private static SparseArray<PicturePager> mPagers = new SparseArray<PicturePager>();

    public synchronized static PicturePager getPager(int id) {
        PicturePager pager = mPagers.get(id);
        if(pager == null) return buildPager(id);

        return pager;
    }

    private static PicturePager buildPager(int id) {

        Log.d(TAG, "buildPager: ");
        PicturePager pager = new PicturePager();
        mPagers.append(id, pager);
        return pager;
    }
}
