package com.wyh.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;

import com.wyh.refreshlayout.view.WyhRefreshLayout;
import com.wyh.sample.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class ScrollViewActivity extends AppCompatActivity {
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        List<String> images=new ArrayList<>();
        images.add("http://pic.58pic.com/58pic/13/83/02/81y58PIC7dD_1024.jpg");
        images.add("http://www.qiwen007.com/images/image/2017/0701/6363452873514728124647977.jpg");
        images.add("http://pic.58pic.com/58pic/13/83/02/81y58PIC7dD_1024.jpg");
        images.add("http://www.qiwen007.com/images/image/2017/0701/6363452873514728124647977.jpg");


        final WyhRefreshLayout dwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
        Banner banner = (Banner) findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        dwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 2000);
            }
        });
    }
}