package com.android20150831.uplooking.gamerinfo;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.comm.AllChannelListResponseInfo;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/1/12.
 */
public class NewsItemHeaderPagerAdapter extends PagerAdapter {
    private AllChannelListResponseInfo data;
    private LayoutInflater inflater;
    private BitmapUtils bitmapUtils;
    private ArrayList<ImageView> ivArray;

    public NewsItemHeaderPagerAdapter(Context context, AllChannelListResponseInfo data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(context);
        bitmapUtils.configDefaultLoadFailedImage(R.drawable.icon_default_plugin);
        ivArray = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.getchildElements().size() * 20;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.i("test", "destroyItem:" + position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 滑动幻灯页面处理，循环利用容器中的4个ImageView
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        int loopPos = position % data.getchildElements().size();
        /**
         * 初次加载时容器为空，需要填充4个ImageView到容器
         */
        if (ivArray.isEmpty()) {
            for (int i = 0; i < data.getchildElements().size(); i++) {
                ivArray.add((ImageView) inflater.inflate(R.layout.layout_item_huandeng_page, null));
            }
        }

        ImageView imageView = ivArray.get(loopPos);

        bitmapUtils.display(imageView, data.getchildElements().get(loopPos).getthumbnailURLs().get(0));
        /**
         * 如果该ImageView已经有父节点（addView会让ImageView具有父节点）
         * 需要解除之前的父子关系，重新再设置父子关系
         */
        if (ivArray.get(loopPos).getParent() != null) {
            container.removeView(ivArray.get(loopPos));
        }
        container.addView(imageView);
        return imageView;
    }


}
