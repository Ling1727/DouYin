package com.example.mydouyin;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hasee on 2018/12/7.
 */

public class VideoFragment extends Fragment {
    Video videoView;
    View rootView;
    ImageView mImage;
    int path;
    int judge=0,isStart=0;
    Timer timer;
    TextView tvName,tvText,tvMusic;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        path=getArguments().getInt("path");
        rootView=inflater.inflate(R.layout.activity_video_fragment,container,false);
        mImage=rootView.findViewById(R.id.mImage);
        tvName=rootView.findViewById(R.id.tvName);
        tvText=rootView.findViewById(R.id.tvText);
        tvMusic=rootView.findViewById(R.id.tvMusic);
        tvName.setText("@"+getArguments().getString("name"));
        tvText.setText(getArguments().getString("text"));
        tvMusic.setText("@"+getArguments().getString("music"));
        video();
        initImage();
        return rootView;
    }
    public void video(){
        videoView=rootView.findViewById(R.id.video);
        videoView.setVideoURI(Uri.parse("android.resource://com.example.mydouyin/" +path));
        final android.widget.MediaController mp=new android.widget.MediaController(getActivity());
        mp.setVisibility(View.INVISIBLE);
        videoView.setMediaController(mp);
        videoView.setClickable(false);
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.setVideoURI(Uri.parse("android.resource://com.example.mydouyin/" +path));
                videoView.start();
            }
        });
        videoView.setPlayPauseListener(new Video.PlayPauseListener(){
            @Override
            public void onPause() {
                initImage();
            }

            @Override
            public void onStart() {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        mImage.setVisibility(View.INVISIBLE);
                    }
                },700);
            }
        });
        isStart=getArguments().getInt("isStart");
        Log.d("test",isStart+"");
        if(isStart==1){
            if(!videoView.isPlaying()){
                videoView.start();
            }
            isStart=0;
        }
    }
    private void initImage() {
        mImage.setVisibility(View.VISIBLE);

        /**
         * MediaMetadataRetriever class provides a unified interface for retrieving
         * frame and meta data from an input media file.
         */
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(getContext(),Uri.parse("android.resource://com.example.mydouyin/" +path));

        //Bitmap bitmap = mmr.getFrameAtTime();//获取第一帧图片

        int currentPosition = videoView.getCurrentPosition(); //in millisecond
        Bitmap bitmap = mmr.getFrameAtTime(currentPosition * 1000);    //获取当前帧

        mImage.setImageBitmap(bitmap);
        mmr.release();//释放资源
    }
}
