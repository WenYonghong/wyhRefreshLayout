package com.wyh.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wyh.refreshlayout.view.WyhRefreshLayout;
import com.wyh.sample.widget.SimpleFootView;

public class CustomFootViewActivity extends AppCompatActivity {
    Handler mHandler = new Handler(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_foot_view);

        final WyhRefreshLayout dwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
        //自定义一个最简单的刷新头
        dwRefreshLayout.setFootView(new SimpleFootView(this));
        dwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CustomFootViewActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CustomFootViewActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 2000);
            }
        });
    }
}
