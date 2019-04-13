package com.example.douyin_ling.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.example.douyin_ling.R;
import com.example.douyin_ling.view.Video;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2019/4/12.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.VH> {
    //MediaController mediaController;
    List<Map<String,Object>> list;
    private Context context;
    private String path;
    private Video video;
    private int index;
    private List<VH> holderList=new ArrayList<>();

    public RecyclerViewAdapter(Context context,List<Map<String,Object>> list){
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.video_item,parent,false);
        return new VH(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        path=(String) list.get(position).get("path");
        video=holder.video;
        index=position;
        holder.video.setVideoURI(Uri.parse(path));
        holder.ivVideo.setImageBitmap(initImage());
        if(!holderList.contains(holder)){
            holderList.add(holder);
        }
        holder.video.setOnVideoStateChangeListener(new Video.OnVideoStateChangeListener() {
            @Override
            public void videoStateChange(int state) {
                switch (state){
                    case 0:
                        holder.ivPause.setVisibility(View.INVISIBLE);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                //隐藏预览图片,这里延时100ms消失是防止页面过渡时闪屏
                                holder.ivVideo.setVisibility(View.INVISIBLE);
                            }
                        }, 250);

                       // Log.d("test0","INVISIBLE");
                        break;
                    case 1:
                        holder.ivPause.setVisibility(View.VISIBLE);
                        //Log.d("test0","VISIBLE");
                        break;
                }
            }
        });

        holder.video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        holder.video.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_UP:
                        if(holder.video.isPlaying()){
                            holder.video.pause();
                        }else{
                            holder.video.start();
                        }
                        break;
                }
                return true;
            }
        });
        if(position==0){
            holder.video.start();
        }
    }

    public void setPause(VH holder){
        for(int i=0;i<holderList.size();i++){
            if(holderList.get(i)!=holder){
                holderList.get(i).video.pause();
                holderList.get(i).ivVideo.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private Bitmap initImage() {
        /**
         * MediaMetadataRetriever class provides a unified interface for retrieving
         * frame and meta data from an input media file.
         */
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(context,Uri.parse(path));
        //Bitmap bitmap = mmr.getFrameAtTime();//获取第一帧图片
        int currentPosition = video.getCurrentPosition(); //in millisecond
        Bitmap bitmap = mmr.getFrameAtTime(currentPosition * 1000);    //获取当前帧
        mmr.release();//释放资源
        return bitmap;
    }



    public class VH extends RecyclerView.ViewHolder{
        public Video video;
        public ImageView ivVideo,ivPause;
        public VH(View itemView) {
            super(itemView);
            //mediaController=new MediaController(context);
            video=itemView.findViewById(R.id.video);
            ivVideo=itemView.findViewById(R.id.ivVideo);
            ivPause=itemView.findViewById(R.id.ivPause);
            //videoView.setMediaController(mediaController);
            //mediaController.setMediaPlayer(videoView);
        }
    }
}
