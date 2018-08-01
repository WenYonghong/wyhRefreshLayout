package com.wyh.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wyh.refreshlayout.view.WyhRefreshLayout;
import com.wyh.refreshlayout.view.EmptyFootView;
import com.wyh.refreshlayout.view.EmptyHeadView;

public class EmptyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        final WyhRefreshLayout dwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
        dwRefreshLayout.setHeadView(new EmptyHeadView(this));
        dwRefreshLayout.setFootView(new EmptyFootView(this));
        dwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dwRefreshLayout.setRefresh(false);
            }

            @Override
            public void onLoadMore() {
                dwRefreshLayout.setRefresh(false);
            }
        });
    }
}
