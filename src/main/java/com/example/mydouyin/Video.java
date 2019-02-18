package com.example.mydouyin;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by hasee on 2018/12/6.
 */

public class Video extends VideoView {
    private PlayPauseListener mListener;

    public Video(Context context) {
        super(context);
    }

    public Video(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Video(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPlayPauseListener(PlayPauseListener listener) {
        mListener = listener;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);//得到默认的大小（0，宽度测量规范）
        int height = getDefaultSize(0, heightMeasureSpec);//得到默认的大小（0，高度度测量规范）
        setMeasuredDimension(width, height); //设置测量尺寸,将高和宽放进去
    }

    @Override
    public void pause() {
        super.pause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    public void start() {
        super.start();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    interface PlayPauseListener {
        void onPause();
        void onStart();
    }

    }
