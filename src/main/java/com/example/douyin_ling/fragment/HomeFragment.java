package com.example.douyin_ling.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.douyin_ling.R;
import com.example.douyin_ling.adapter.HomeFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2019/4/10.
 */

public class HomeFragment extends Fragment {
    private View view;
    private ViewPager vpHome;
    private List<Fragment> fragmentList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_home,container,false);
        initView();
        initData();
        return view;
    }

    private void initView() {
        vpHome=view.findViewById(R.id.vpHome);
    }

    private void initData() {
        fragmentList=new ArrayList<>();
        fragmentList.add(new VerticalFragment());
        //fragmentList.add(new PersonageFragment());
        FragmentManager fm=getChildFragmentManager();
        vpHome.setAdapter(new HomeFragmentPagerAdapter(fm,fragmentList));
    }

}
