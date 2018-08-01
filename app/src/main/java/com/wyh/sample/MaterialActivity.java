package com.wyh.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wyh.refreshlayout.view.WyhRefreshLayout;
import com.wyh.refreshlayout.view.MaterialHeadView;

public class MaterialActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

        final WyhRefreshLayout dwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
        //设置Material 刷新头 ( 注意要在布局文件设置这个属性 app:refresh_style="style_material")
        MaterialHeadView materialHeadView = new MaterialHeadView(this);
        materialHeadView.setColorSchemeColors(Color.BLACK);
        dwRefreshLayout.setHeadView(materialHeadView);
        dwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MaterialActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MaterialActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 3000);
            }
        });
    }
}
