package com.gemapps.rxpicapp.ui.butter;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by edu on 4/12/17.
 */

public class ButterFragment extends Fragment {

    protected View onCreateView(LayoutInflater inflater, ViewGroup container, int layoutRes){
        View rootView = inflater.inflate(layoutRes, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
