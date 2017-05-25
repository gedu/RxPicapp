package com.gemapps.rxpicapp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.gemapps.rxpicapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by edu on 5/25/17.
 */

public class ErrorFullView extends ConstraintLayout {

    @BindView(R.id.error_image)
    ImageView mErrorImage;

    @BindView(R.id.error_message)
    TextView mErrorMessage;

    private Subject<Boolean> mOnClick;

    public ErrorFullView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ErrorFullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(R.layout.error_full_view, this);
        ButterKnife.bind(this);
        final TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ErrorFullView, defStyleAttr, 0);

        String message = "";
        Drawable errorIcon;

        try {
            message = typedArray.getString(R.styleable.ErrorFullView_message);
            errorIcon = typedArray.getDrawable(R.styleable.ErrorFullView_imageSrc);
        } finally {
            typedArray.recycle();
        }

        if(isInEditMode() && mErrorMessage == null) return;

        mOnClick = PublishSubject.create();

        if(message == null || message.length() == 0) {
            message = context.getText(R.string.connection_error_message)
                    .toString();
        }
        mErrorMessage.setText(message);

        if(errorIcon != null) {
            mErrorImage.setImageDrawable(errorIcon);
            invalidate();
        }
    }

    public Observable<Boolean> listenClick() {
        return mOnClick.observeOn(AndroidSchedulers.mainThread());
    }

    public void setErrorMessage(@StringRes int stringRes) {
        mErrorMessage.setText(stringRes);
    }

    public void setErrorIcon(@DrawableRes int drawableRes) {
        mErrorImage.setImageDrawable(getResources().getDrawable(drawableRes));
    }

    @OnClick(R.id.try_again_button)
    public void onTryAgainClick() {
        mOnClick.onNext(true);
    }

    @Override
    protected void onDetachedFromWindow() {
        mOnClick.onComplete();
        super.onDetachedFromWindow();
    }
}
