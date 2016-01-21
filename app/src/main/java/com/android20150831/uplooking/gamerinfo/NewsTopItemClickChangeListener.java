package com.android20150831.uplooking.gamerinfo;


import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.RadioGroup;

import com.comm.CommUtils;

/**
 * 新闻顶部导航条目点击状态发生改变时需要做监听事件
 */
public class NewsTopItemClickChangeListener implements RadioGroup.OnCheckedChangeListener {
    private NewsTopFragment topFragment;
    private NewsContentFragment contentFragment;
    private ViewPager contentPager;

    private FrameLayout.LayoutParams slindingBlockLp;
    private HorizontalScrollView hsv;
    private View slidingBlock;
    private Context context;

    public NewsTopItemClickChangeListener(Context context) {
        Log.i("test", "创建频道条目监听器");
        this.context = context;
        this.topFragment = (NewsTopFragment) ((MainActivity) context).getNavFragmentContainer().get(CommUtils.NEWS_INDEX).get("top");
        this.contentFragment = (NewsContentFragment) ((MainActivity) context).getNavFragmentContainer().get(CommUtils.NEWS_INDEX).get("center");
        contentPager = contentFragment.getViewPager();

        hsv = topFragment.getHsv();
        slindingBlockLp = (FrameLayout.LayoutParams) hsv.findViewById(R.id.tv_sliding_block).getLayoutParams();
        slidingBlock = hsv.findViewById(R.id.tv_sliding_block);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        //Log.i("test", "onCheckedChanged:" + checkedId);
        CommUtils.ItemInfo itemInfo =
                (CommUtils.ItemInfo) group.findViewById(checkedId).getTag();

        contentPager.setCurrentItem(itemInfo.index, false);
    }
}
