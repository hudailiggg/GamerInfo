package com.android20150831.uplooking.gamerinfo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.comm.CommUtils;

/**
 * 新闻内容布局中ViewPager设置适配器的Handler
 * 因为新闻频道下载线程和新闻页面适配器设置线程需要做线程同步
 * 即下载完成后才能设置适配器，该信号表示下载已完成，可以进行适配器设置
 * <p/>
 * 注意：ViewPager设置适配器只能在主线程中进行，所以必须使用Handler机制
 */
public class NewsPagerAdapterHandler extends Handler {
    private Context context;

    public NewsPagerAdapterHandler(Context context) {
        this.context = context;
    }

    /**
     * 设置适配器并设置监听器
     *
     * @param msg
     */
    @Override
    public void handleMessage(Message msg) {
        MainActivity mainActivity = (MainActivity) context;
        NewsContentFragment contentFragment = (NewsContentFragment) mainActivity.getNavFragmentContainer().get(CommUtils.NEWS_INDEX).get("center");
        NewsTopFragment topFragment = (NewsTopFragment) mainActivity.getNavFragmentContainer().get(CommUtils.NEWS_INDEX).get("top");

        contentFragment.getViewPager().setAdapter(new NewsFragmentPagerAdapter(mainActivity.getFragmentManager(), topFragment.getRg()));
        contentFragment.getViewPager().addOnPageChangeListener(new NewsOnPageChangeListener(context));

        topFragment.getRg().setOnCheckedChangeListener(new NewsTopItemClickChangeListener(context));
    }
}
