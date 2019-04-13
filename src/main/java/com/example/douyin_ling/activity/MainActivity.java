package com.example.douyin_ling.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.example.douyin_ling.R;
import com.example.douyin_ling.fragment.AddFragment;
import com.example.douyin_ling.fragment.AttentionFragment;
import com.example.douyin_ling.fragment.HomeFragment;
import com.example.douyin_ling.fragment.InformationFragment;
import com.example.douyin_ling.fragment.MeFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Fragment currentFragment=null;
    private Fragment addFragment,attentionFragment,homeFragment,informationFragment,meFragment;
    private View view0,view1,view2,view3,view4;
    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        setState();
        addFragment=new AddFragment();
        attentionFragment=new AttentionFragment();
        homeFragment=new HomeFragment();
        informationFragment=new InformationFragment();
        meFragment=new MeFragment();
        view0=findViewById(R.id.view0);
        view1=findViewById(R.id.view1);
        view2=findViewById(R.id.view2);
        view3=findViewById(R.id.view3);
        view4=findViewById(R.id.view4);
        viewList=new ArrayList<>();
        viewList.add(view0);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.rlReplace,homeFragment).show(homeFragment).commit();
        currentFragment=homeFragment;
    }

    public void home(View view){
        replaceFragment(homeFragment,viewList.get(0));
    }

    public void attention(View view){
        replaceFragment(attentionFragment,viewList.get(1));
    }

    public void iv(View view){
        replaceFragment(addFragment,viewList.get(2));
    }

    public void information(View view){
        replaceFragment(informationFragment,viewList.get(3));
    }

    public void me(View view){
        replaceFragment(meFragment,viewList.get(4));
    }

    public void replaceFragment(Fragment fragment,View view){
        if(fragment!=homeFragment){
            //getSharedPreferences("set",0).getBoolean("isRegister",false)
            if(true){
                setView(view);
                if(fragment!=currentFragment){
                    FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                    transaction.hide(currentFragment);
                    currentFragment=fragment;
                    if(fragment.isAdded()){
                        transaction.show(fragment).commit();
                    }else{
                        transaction.add(R.id.rlReplace,fragment).show(fragment).commit();
                    }
                }
            }else{
                startActivity(new Intent("android.intent.action.Load"));
            }
        }else{
            setView(view);
            if(fragment!=currentFragment){
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                if(currentFragment!=null){
                    transaction.hide(currentFragment);
                }
                currentFragment=fragment;
                if(fragment.isAdded()){
                    transaction.show(fragment).commit();
                }else{
                    transaction.add(R.id.rlReplace,fragment).show(fragment).commit();
                }
            }
        }
    }

    public void setView(View view){
        for(int i=0;i<viewList.size();i++){
            if(viewList.get(i)==view){
                viewList.get(i).setVisibility(View.VISIBLE);
            }else{
                viewList.get(i).setVisibility(View.INVISIBLE);
            }
        }
    }

    //取消actionbar并状态栏设为透明留出空位
    public void setState(){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        /*ActionBar actionBar = getSupportActionBar();
        actionBar.hide();*/
    }

}
