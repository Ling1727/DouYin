package com.example.mydouyin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hasee on 2018/12/8.
 */

public class HomeFragment extends Fragment {
    TextView tv0;
    ViewPager horizontalViewPager,verticalViewPager;
    List<View> views;
    List<Fragment> fragments;
    int page=0,page1=0;
    float offset=0;
    int width;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.activity_home,container,false);
        horizontalViewPager=rootView.findViewById(R.id.horizontalViewPager);
        inView();
        Myadapter myadapter=new Myadapter(views);
        horizontalViewPager.setAdapter(myadapter);
        horizontalViewPager.setCurrentItem (1);
        horizontalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return rootView;
    }

    public void inView() {
        int[] paths=new int[]{R.raw.av,R.raw.avi,R.raw.emmm,R.raw.tow_b};
        views = new ArrayList<View>();
        LayoutInflater inflater = getLayoutInflater().from(getActivity());
        views.add(inflater.inflate(R.layout.view_pager_seacher_page, null));
        views.add(inflater.inflate(R.layout.page_video, null));
        views.add(inflater.inflate(R.layout.view_pager_user_page, null));

        tv0=views.get(1).findViewById(R.id.tv0);
        width=getArguments().getInt("width");
        tv0.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,width));

        verticalViewPager = views.get(1).findViewById(R.id.verticalViewPager);
        fragments = new ArrayList<>();
        VideoFragment videoFragment1 = new VideoFragment();
        Bundle date1 = new Bundle();
        date1.putInt("isStart",1);
        date1.putInt("path",paths[0]);
        date1.putString("name","name0");
        date1.putString("text","啦啦啦啦啦啦啦");
        date1.putString("music","啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊！");
        videoFragment1.setArguments(date1);        //传送数据
        fragments.add(videoFragment1);
        for (int i = 1; i < 4; i++) {
            VideoFragment videoFragment = new VideoFragment();
            Bundle date = new Bundle();
            date.putInt("isStart", 0);
            date.putInt("path",paths[i]);
            date.putString("name","name"+i);
            date.putString("text","啦啦啦啦啦啦啦");
            date.putString("music","啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊！");
            videoFragment.setArguments(date);  //传送数据
            fragments.add(videoFragment);
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        FragmentAdapter fragmentAdapter=new FragmentAdapter(childFragmentManager,fragments);
        verticalViewPager.setAdapter(fragmentAdapter);
        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            VideoView videoView,videoView1;
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.d("test",position+"哇"+positionOffset+"哇"+positionOffsetPixels);
                if(page1!=position&&offset!=positionOffset&&positionOffset!=0){
                    if(videoView1==null){
                        videoView1=fragments.get(position+1).getView().findViewById(R.id.video);
                    }
                    offset=positionOffset;
                    if(videoView1.isPlaying()){
                        videoView1.pause();
                    }
                }else if(page1==position&&offset!=positionOffset&&positionOffset!=0){
                    if(videoView1==null){
                        videoView1=fragments.get(position).getView().findViewById(R.id.video);
                    }
                    offset=positionOffset;
                    if(videoView1.isPlaying()){
                        videoView1.pause();
                    }
                }else if(positionOffset==0){
                    videoView1=fragments.get(position).getView().findViewById(R.id.video);
                    if(!videoView1.isPlaying()){
                        videoView1.start();
                    }
                    videoView1=null;
                    page1=position;
                }
            }

            @Override
            public void onPageSelected(int position) {
                videoView=fragments.get(position).getView().findViewById(R.id.video);
                videoView.start();
                if(page!=position){
                    VideoView videoViewPrevious=fragments.get(page).getView().findViewById(R.id.video);
                    videoViewPrevious.pause();
                    page=position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class Myadapter extends PagerAdapter {
        List<View> views;
        public Myadapter(List<View> views) {
            this.views=views;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position),0);
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments;
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

    }
}
