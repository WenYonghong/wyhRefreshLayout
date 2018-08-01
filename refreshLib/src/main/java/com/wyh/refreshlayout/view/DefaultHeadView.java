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
import com.wyh.refreshlayout.view.interf.IRefreshHead;
import com.wyh.refreshlayout.view.utils.DensityUtils;

/**
 * Created by: wyh
 * <p>
 * on: 2018/2/2.
 * <p>
 * 描述：默认的样式的刷新头View
 */

public class DefaultHeadView extends FrameLayout implements IRefreshHead {
    private int i = 0;

    private TextView mTvStatus;
    private ProgressBar pbHead;
    private ImageView ivHead;
    private boolean p_d = false;
    private boolean p_d_b = false;
    private boolean refreshBool = false;
    private LayoutParams mLayoutParams =
            new LayoutParams(LayoutParams.MATCH_PARENT, (int) DensityUtils.dipToPx(getContext(), 60));
    private int viewHeight;

    public DefaultHeadView(Context context) {
        this(context, null);
    }

    public DefaultHeadView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DefaultHeadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.default_headview, null);
        mTvStatus = (TextView) view.findViewById(R.id.tv_status);
        pbHead = (ProgressBar) view.findViewById(R.id.pb_head);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        viewHeight = (int) DensityUtils.dipToPx(getContext(), 60);
        this.addView(view, mLayoutParams);
    }

    @Override
    public void onStart() {
        refreshBool = false;
        ivHead.setBackgroundResource(R.drawable.jiantou);
        pbHead.setVisibility(INVISIBLE);
        mTvStatus.setText("下拉刷新");
    }

    @Override
    public void onPullDown(int distance) {

        p_d_b = p_d;

        if (distance > viewHeight) {
            p_d = true;
        } else {
            p_d = false;
        }

        if ((p_d_b != p_d) && (p_d == true)) {
            mTvStatus.setText("释放刷新");
            Animation animation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(250);
            animation.setFillAfter(true);
            ivHead.startAnimation(animation);

        }

        if ((p_d_b != p_d) && (p_d == false)) {
            mTvStatus.setText("下拉刷新");
            Animation animation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
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
        if (!refreshBool) {
            refreshBool = true;
            mTvStatus.setText("刷新中...");
            ivHead.setBackgroundColor(Color.parseColor("#00ffffff"));
            Animation animation = new RotateAnimation(180, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            animation.setDuration(1);
            animation.setFillAfter(true);//设置为true，动画转化结束后被应用
            ivHead.startAnimation(animation);//开始动画
            pbHead.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onStop() {

    }

    @Override
    public int headViewHeight() {
        return (int) DensityUtils.dipToPx(getContext(), 60);
    }
}
