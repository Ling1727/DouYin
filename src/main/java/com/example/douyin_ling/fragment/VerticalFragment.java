package com.example.douyin_ling.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.douyin_ling.R;
import com.example.douyin_ling.adapter.RecyclerViewAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2019/4/10.
 */

public class VerticalFragment extends Fragment {
    private View view;
    private RecyclerView recyclerView;
    private List<Map<String,Object>> mapList;
    private RecyclerView.LayoutManager layoutManager;
    private PagerSnapHelper mPagerSnapHelper;
    private RecyclerViewAdapter recyclerViewAdapter;
    private int[] ids=new int[]{R.raw.avi,R.raw.av,R.raw.emmm};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_vertical,container,false);
        initView();
        initData();
        initControl();
        return view;
    }

    private void initView() {
        recyclerView=view.findViewById(R.id.recyclerView);
    }

    private void initData() {
        mapList=new ArrayList();
        for(int i=0;i<10;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("path","android.resource://"+getActivity().getPackageName()+"/"+ids[i%3]);
            mapList.add(map);
        }
        recyclerViewAdapter=new RecyclerViewAdapter(getActivity(),mapList);
    }

    private void initControl() {
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        mPagerSnapHelper=new PagerSnapHelper();
        mPagerSnapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                View view = mPagerSnapHelper.findSnapView(layoutManager);
                RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        if (viewHolder != null && viewHolder instanceof RecyclerViewAdapter.VH) {
                            if(!((RecyclerViewAdapter.VH)viewHolder).video.isPlaying()){
                                ((RecyclerViewAdapter.VH)viewHolder).video.start();
                                recyclerViewAdapter.setPause(((RecyclerViewAdapter.VH)viewHolder));
                            }
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        /*if (viewHolder != null && viewHolder instanceof RecyclerViewAdapter.VH) {
                            ((RecyclerViewAdapter.VH)viewHolder).video.pause();
                        }*/
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

}
