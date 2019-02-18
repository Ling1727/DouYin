package com.example.mydouyin;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvHome,tvFollow,tvNews,tvMe;
    ImageView imageView;
    LinearLayout llContainer,linearLayout;
    int width;
    View.OnClickListener listener;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        setContentView(R.layout.activity_main);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        width=getResources().getDimensionPixelSize(getResources().getIdentifier("status_bar_height", "dimen", "android"));
        //Fragment
        getHome();

        linearLayout=findViewById(R.id.linearLayout);
        llContainer=findViewById(R.id.llContainer);
        tvHome=findViewById(R.id.tvHome);
        tvFollow=findViewById(R.id.tvFollow);
        imageView=findViewById(R.id.imageView);


        tvNews=findViewById(R.id.tvNews);
        tvMe=findViewById(R.id.tvMe);
        listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.tvHome:
                            linearLayout.setBackgroundColor(Color.alpha(0));
                          getHome();
                    break;
                    case R.id.tvFollow:
                        linearLayout.setBackgroundColor(Color.BLACK);
                         getFollow();
                        break;
                    case R.id.imageView:
                        break;
                    case R.id.tvNews:
                        linearLayout.setBackgroundColor(Color.BLACK);
                        getNews();
                        break;
                    case R.id.tvMe:
                        linearLayout.setBackgroundColor(Color.BLACK);
                        getMe();
                        break;
                }
            }
        };
        tvHome.setOnClickListener(listener);
        tvFollow.setOnClickListener(listener);
        imageView.setOnClickListener(listener);
        tvNews.setOnClickListener(listener);
        tvMe.setOnClickListener(listener);
    }

    public void getHome(){
        HomeFragment homeFragment=new HomeFragment();
        Bundle date = new Bundle();
        date.putInt("width", width);
        homeFragment.setArguments(date);        //传送数据
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.llContainer,homeFragment);        //使用videoFragment替换llVideo容器的内容（可能为空也可能是别的碎片）
        fragmentTransaction.commit();
    }

    public void getFollow(){
        FollowFragment followFragment=new FollowFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.llContainer,followFragment);        //使用videoFragment替换llVideo容器的内容（可能为空也可能是别的碎片）
        fragmentTransaction.commit();
    }

    public void getNews(){
        NewsFragment newsFragment=new NewsFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.llContainer,newsFragment);        //使用videoFragment替换llVideo容器的内容（可能为空也可能是别的碎片）
        fragmentTransaction.commit();
    }

    public void getMe(){
        MeFragment meFragment=new MeFragment();
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.llContainer,meFragment);        //使用videoFragment替换llVideo容器的内容（可能为空也可能是别的碎片）
        fragmentTransaction.commit();
    }
}
