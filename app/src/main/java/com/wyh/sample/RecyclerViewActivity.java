package com.wyh.sample;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.wyh.refreshlayout.view.WyhRefreshLayout;
import com.wyh.sample.adapter.RecyclerViewAdapter;

public class RecyclerViewActivity extends AppCompatActivity {

    Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_REFRESH:
                    mAdapter.setCount(100);
                    mAdapter.notifyDataSetChanged();
                    mDwRefreshLayout.setRefresh(false);
                    break;
                case MSG_LOAD_MORE:
                    mAdapter.setCount(mAdapter.getItemCount() + 20);
                    mAdapter.notifyDataSetChanged();
                    mDwRefreshLayout.setRefresh(false);
                    break;
            }
        }
    };
    private RecyclerViewAdapter mAdapter;
    public static final int MSG_REFRESH = 1;
    public static final int MSG_LOAD_MORE = 2;
    private WyhRefreshLayout mDwRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        //item click
        mAdapter = new RecyclerViewAdapter(this, new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //item click
                Toast.makeText(RecyclerViewActivity.this, " " + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(mAdapter);

        mDwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
      //  mDwRefreshLayout.setHeadView(new MaterialHeadView(this));
        //开启禁止加载更多
//        mDwRefreshLayout.lockLoadMore(true);
        mDwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.sendEmptyMessageDelayed(MSG_REFRESH, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.sendEmptyMessageDelayed(MSG_LOAD_MORE, 2000);
            }
        });
    }
}
