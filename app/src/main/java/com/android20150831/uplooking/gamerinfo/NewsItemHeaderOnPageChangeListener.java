package com.android20150831.uplooking.gamerinfo;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.comm.AllChannelListResponseInfo;

import java.util.ArrayList;

/**
 * 新闻列表幻灯片ViewPager滑动监听器
 */
public class NewsItemHeaderOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private AllChannelListResponseInfo data;
    private TextView tvTitle;
    private ArrayList<ImageView> roundArrayList;

    NewsItemHeaderOnPageChangeListener(View parentView, AllChannelListResponseInfo data) {
        this.data = data;
        tvTitle = (TextView) parentView.findViewById(R.id.tv_huandeng_title);
        roundArrayList = new ArrayList<>();
        roundArrayList.add((ImageView) parentView.findViewById(R.id.iv_round_one));
        roundArrayList.add((ImageView) parentView.findViewById(R.id.iv_round_two));
        roundArrayList.add((ImageView) parentView.findViewById(R.id.iv_round_three));
        roundArrayList.add((ImageView) parentView.findViewById(R.id.iv_round_four));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.i("test", "onPageSelected:" + position);
        int loopPos = position % data.getchildElements().size();
        tvTitle.setText(data.getchildElements().get(loopPos).gettitle());
        changeRoundPointer(loopPos);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void changeRoundPointer(int position) {
        int loopPos = position % data.getchildElements().size();
        recoveryDefaultRoundPointer();
        roundArrayList.get(loopPos).setImageResource(R.drawable.roundred);
    }

    private void recoveryDefaultRoundPointer() {
        for (ImageView iv : roundArrayList) {
            iv.setImageResource(R.drawable.roundgray);
        }
    }
}
