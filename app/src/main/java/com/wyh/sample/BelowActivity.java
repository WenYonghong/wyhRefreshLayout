package com.wyh.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wyh.refreshlayout.view.WyhRefreshLayout;

public class BelowActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_below);

        final WyhRefreshLayout dwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
        dwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BelowActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(BelowActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 3000);
            }
        });
    }
}
