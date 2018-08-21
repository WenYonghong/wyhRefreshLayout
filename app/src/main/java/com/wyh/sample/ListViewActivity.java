package com.wyh.sample;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.wyh.refreshlayout.view.WyhRefreshLayout;

public class ListViewActivity extends AppCompatActivity {

    private WyhRefreshLayout dwRefreshLayout;

    private ListView lv;
    private int x = 10;
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        dwRefreshLayout = (WyhRefreshLayout) findViewById(R.id.dwRefreshLayout);
        lv = (ListView) findViewById(R.id.lv);
        final LvAdapter lvAdapter = new LvAdapter();
        lv.setAdapter(lvAdapter);


        //开启禁止加载更多
//        dwRefreshLayout.lockLoadMore(true);
        dwRefreshLayout.setOnRefreshListener(new WyhRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        x = 10;
                        lvAdapter.notifyDataSetChanged();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        x = x + 5;
                        lvAdapter.notifyDataSetChanged();
                        dwRefreshLayout.setRefresh(false);
                    }
                }, 2000);
            }
        });
    }


    class LvAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return x;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(ListViewActivity.this, R.layout.item_rv, null);
                viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.tv.setText("第" + position + "条");

            return convertView;
        }

        class ViewHolder {
            TextView tv;
        }
    }
}
