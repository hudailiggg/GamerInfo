package com.android20150831.uplooking.gamerinfo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.comm.CommUtils;

/**
 * 新闻内容ViewPager滑动监听器
 */
public class NewsOnPageChangeListener implements ViewPager.OnPageChangeListener {
    private Context context;
    private HorizontalScrollView hsv;
    private RadioGroup channelRg;
    private int offsetDimension = 0;
    private int currentPos = 0;
    private FrameLayout.LayoutParams lp;
    private int tmpPos = 0;

    public NewsOnPageChangeListener(Context context) {
        this.context = context;
        hsv = ((NewsTopFragment) ((MainActivity) context).
                getNavFragmentContainer().
                get(CommUtils.NEWS_INDEX).get("top")).getHsv();
        channelRg = (RadioGroup) hsv.findViewById(R.id.rg_news_top_nav);
        offsetDimension = (int) context.getResources().getDimension(R.dimen.channel_item_margin)
                + channelRg.getChildAt(0).getWidth();
        lp = (FrameLayout.LayoutParams) hsv.findViewById(R.id.tv_sliding_block).getLayoutParams();
    }


    /**
     * positionOffset不为0表示正在滑动，为0表示画面静止或者滑动结束
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        /*Log.i("test", "onPageSrolled:"
                + " position:" + position
                + " currentPos:" + currentPos
                + " positionOffset:" + positionOffset);*/

        if (positionOffset != 0) {
            judgeForScorlling(position, positionOffset);
        } else {
            judgeForScorlling(position, positionOffset);
            currentPos = position;
            channelRg.check(channelRg.getChildAt(position).getId());
        }
    }

    private void judgeForScorlling(int position, float positionOffset) {
        if (currentPos == position) {
            scrollToRight(position, positionOffset);
        } else {
            scrollToLeft(position, positionOffset);
        }
    }

    /**
     * 向右移动,根据ViewPager滑动的百分比计算移动的长度
     * 每次滑动从保存的起始位置开始
     *
     * @param positionOffset 滑动百分比
     * @param position       当前所在的位置
     */
    private void scrollToRight(int position, float positionOffset) {
        //Log.i("test", "向右滑动");
        int onceOffset = (int) (positionOffset * offsetDimension);
        //Log.i("test", "scrollToRight base:" + (position * offsetDimension));
        //Log.i("test", "onceOffset:" + onceOffset);
        lp.setMargins(position * offsetDimension + onceOffset, 0, 0, 0);
        hsv.findViewById(R.id.tv_sliding_block).requestLayout();
        scrollTheHsv(position * offsetDimension + onceOffset, 0);
    }

    /**
     * 滚动Hsv
     */
    private void scrollTheHsv(int absOffsetX, int absOffsetY) {
        //Log.i("test", "scrollTheHsv to: " + absOffsetX);
        hsv.smoothScrollTo(absOffsetX, absOffsetY);
    }


    private void scrollToLeft(int position, float positionOffset) {
        //Log.i("test", "向左滑动");
        int onceOffset = (int) (positionOffset * offsetDimension);
        //Log.i("test", "scrollToLeft base:" + (position + 1) * offsetDimension);
        //Log.i("test", "onceOffset:" + onceOffset);
        lp.setMargins((position + 1) * offsetDimension - (offsetDimension - onceOffset), 0, 0, 0);
        hsv.findViewById(R.id.tv_sliding_block).requestLayout();
        scrollTheHsv((position + 1) * offsetDimension - (offsetDimension - onceOffset), 0);
    }


    @Override
    public void onPageSelected(int position) {
       // Log.i("aa", "#######onPageSelected:" + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_DRAGGING) {
            //Log.i("test", "开始滑动");
        } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
            //Log.i("test", "进行画面稳定工作");
        } else {
            //Log.i("test", "滑动结束");
        }
    }
}
