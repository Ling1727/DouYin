package com.example.douyin_ling.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * Created by hasee on 2018/12/6.
 */

public class Video extends VideoView {
    private OnVideoStateChangeListener onVideoStateChangeListener;
    private static final int VIDEO_START=0;
    private static final int VIDEO_PAUSE=1;

    public Video(Context context) {
        super(context);
    }

    public Video(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Video(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getDefaultSize(0, widthMeasureSpec);//得到默认的大小（0，宽度测量规范）
        int height = getDefaultSize(0, heightMeasureSpec);//得到默认的大小（0，高度度测量规范）
        setMeasuredDimension(width, height); //设置测量尺寸,将高和宽放进去
    }

    @Override
    public void start() {
        super.start();
        onVideoStateChangeListener.videoStateChange(VIDEO_START);
    }

    @Override
    public void pause() {
        super.pause();
        onVideoStateChangeListener.videoStateChange(VIDEO_PAUSE);
    }

    public interface OnVideoStateChangeListener{
        void videoStateChange(int state);
    }

    public void setOnVideoStateChangeListener(OnVideoStateChangeListener onVideoStateChangeListener){
        this.onVideoStateChangeListener=onVideoStateChangeListener;
    }

}
