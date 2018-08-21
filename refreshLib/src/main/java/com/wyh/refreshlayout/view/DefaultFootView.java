package com.wyh.refreshlayout.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wyh.refreshlayout.R;
import com.wyh.refreshlayout.view.interf.ILoadMoreFoot;
import com.wyh.refreshlayout.view.utils.DensityUtils;
import com.zyao89.view.zloading.ZLoadingView;

/**
 * Created by: wyh
 * <p>
 * on: 2018/2/17.
 * <p>
 * 描述：默认的加载更多脚布局View
 */

public class DefaultFootView extends FrameLayout implements ILoadMoreFoot {

    private TextView mTvStatus;
    private ZLoadingView pbHead;
    private ImageView ivHead;
    private boolean p_d = false;
    private boolean p_d_b = false;
    private LayoutParams mLayoutParams =
            new LayoutParams(LayoutParams.MATCH_PARENT, (int) DensityUtils.dipToPx(getContext(), 60));
    private int viewHeight;

    public DefaultFootView(Context context) {
        this(context, null);
    }

    public DefaultFootView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultFootView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.default_headview, null);
        mTvStatus = (TextView) view.findViewById(R.id.tv_status);
        pbHead = (ZLoadingView) view.findViewById(R.id.pb_head);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        viewHeight = (int) DensityUtils.dipToPx(getContext(), 60);
        this.addView(view, mLayoutParams);
    }

    @Override
    public void onStart() {
        p_d = false;
        Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(0);
        animation.setFillAfter(true);
        ivHead.startAnimation(animation);
        ivHead.setBackgroundResource(R.drawable.jiantou);
        pbHead.setVisibility(INVISIBLE);
        mTvStatus.setText("上拉加载更多");
    }

    @Override
    public void onPullUp(int distance) {

        p_d_b = p_d;
        if (distance > viewHeight) {
            p_d = true;
        } else {
            p_d = false;
        }

        if ((p_d_b != p_d) && (p_d == true)) {
            mTvStatus.setText("释放加载更多");
            Animation animation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(250);
            animation.setFillAfter(true);
            ivHead.startAnimation(animation);
        }

        if ((p_d_b != p_d) && (p_d == false)) {
            mTvStatus.setText("上拉加载更多");
            Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(250);
            animation.setFillAfter(true);
            ivHead.startAnimation(animation);
        }

    }


    @Override
    public void onBound() {


    }

    @Override
    public void onFingerUp(int distance) {
        if (distance > viewHeight) {
            mTvStatus.setText("加载更多...");
            ivHead.setBackgroundColor(Color.parseColor("#00ffffff"));
            pbHead.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public int footViewHeight() {
        return (int) DensityUtils.dipToPx(getContext(), 60);
    }
}
