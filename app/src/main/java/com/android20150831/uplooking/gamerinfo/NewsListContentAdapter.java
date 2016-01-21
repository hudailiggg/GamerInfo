package com.android20150831.uplooking.gamerinfo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android20150831.uplooking.gamerinfo.viewholder.DefaultViewHolder;
import com.android20150831.uplooking.gamerinfo.viewholder.HengtuViewHolder;
import com.android20150831.uplooking.gamerinfo.viewholder.SantuViewHolder;
import com.android20150831.uplooking.gamerinfo.viewholder.XinwenViewHolder;
import com.comm.AllChannelListResponse;
import com.comm.AllChannelListResponseInfo;
import com.comm.CommUtils;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 新闻内容
 */
public class NewsListContentAdapter extends BaseAdapter {
    private AllChannelListResponse data;
    private LayoutInflater inflater;
    private BitmapUtils bitmapUtils;

    public NewsListContentAdapter(Context context, AllChannelListResponse data) {
        this.data = data;
        inflater = LayoutInflater.from(context);
        bitmapUtils = new BitmapUtils(context);
    }

    @Override
    public int getViewTypeCount() {
        return CommUtils.TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        String type = data.getResult().get(position).gettype();
        switch (type) {
            case "xinwen":
                return CommUtils.TYPE_XINWEN;
            case "zhuanti":
                return CommUtils.TYPE_ZHUANTI;
            case "santu":
                return CommUtils.TYPE_SANTU;
            case "hengtu":
                return CommUtils.TYPE_HENGTU;
        }
        return CommUtils.TYPE_ERROR;
    }

    @Override
    public int getCount() {
        //Log.i("test", "getCount:" + data.getResult().size());
        return data.getResult().size();
    }

    @Override
    public Object getItem(int position) {
        //Log.i("test", "NewsListContentAdapter getItem:" + position);
        return data.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return data.getResult().get(position).getcontentId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Log.i("test", "getView:" + position);
        int type = getItemViewType(position);

        if (convertView == null) {
            //Log.i("test", "convertView is null");
            convertView = inflateViewByType(type);
        }

        if (convertView.getTag() instanceof XinwenViewHolder) {
            //Log.i("test", "convertView holder type is xinwen");
            XinwenViewHolder holder = (XinwenViewHolder) convertView.getTag();
            holder.tvTitle.setText(data.getResult().get(position).gettitle());
            String tvComments = data.getResult().get(position).getcommentsCount() + "";
            holder.tvComments.setText(tvComments);
            bitmapUtils.display(holder.iv, (String) ((ArrayList) data.getResult().get(position).getthumbnailURLs()).get(0));
        } else if (convertView.getTag() instanceof SantuViewHolder) {
            //Log.i("test", "convertView holder type is santu");
            SantuViewHolder holder = (SantuViewHolder) convertView.getTag();
            holder.tvTitle.setText(data.getResult().get(position).gettitle());
            String tvComments = data.getResult().get(position).getcommentsCount() + "";
            holder.tvComments.setText(tvComments);
            bitmapUtils.display(holder.ivLeft, (String) ((ArrayList) data.getResult().get(position).getthumbnailURLs()).get(0));
            bitmapUtils.display(holder.ivCenter, (String) ((ArrayList) data.getResult().get(position).getthumbnailURLs()).get(1));
            bitmapUtils.display(holder.ivRight, (String) ((ArrayList) data.getResult().get(position).getthumbnailURLs()).get(2));
        } else if (convertView.getTag() instanceof HengtuViewHolder) {
            //Log.i("test", "convertView holder type is hengtu");
            HengtuViewHolder holder = (HengtuViewHolder) convertView.getTag();
            holder.tvTitle.setText(data.getResult().get(position).gettitle());
            holder.tvAuthor.setText((String) data.getResult().get(position).getauthorName());
            String comments = data.getResult().get(position).getcommentsCount() + "";
            holder.tvComments.setText(comments);
            bitmapUtils.display(holder.ivContent, (String) ((ArrayList) data.getResult().get(position).getthumbnailURLs()).get(0));
            bitmapUtils.display(holder.ivAuthor, (String)data.getResult().get(position).getauthorHeadImageURL());
        } else {
            //Log.i("test", "convertView holder type is default");
        }
        return convertView;
    }

    private View inflateViewByType(int type) {
        switch (type) {
            case CommUtils.TYPE_XINWEN:
                XinwenViewHolder xinwenVH = new XinwenViewHolder();
                View xinwenView = inflater.inflate(R.layout.layout_item_xinwen, null);
                xinwenVH.iv = (ImageView) xinwenView.findViewById(R.id.iv_item_news);
                xinwenVH.tvTitle = (TextView) xinwenView.findViewById(R.id.tv_item_news_title);
                xinwenVH.tvComments = (TextView) xinwenView.findViewById(R.id.tv_item_news_comments);
                xinwenView.setTag(xinwenVH);
                return xinwenView;
            case CommUtils.TYPE_SANTU:
                SantuViewHolder santuVH = new SantuViewHolder();
                View santuView = inflater.inflate(R.layout.layout_item_santu, null);
                santuVH.tvTitle = (TextView) santuView.findViewById(R.id.tv_item_santu_title);
                santuVH.ivLeft = (ImageView) santuView.findViewById(R.id.iv_item_santu_left);
                santuVH.ivCenter = (ImageView) santuView.findViewById(R.id.iv_item_santu_center);
                santuVH.ivRight = (ImageView) santuView.findViewById(R.id.iv_item_santu_right);
                santuVH.tvComments = (TextView) santuView.findViewById(R.id.tv_item_santu_comments);
                santuView.setTag(santuVH);
                return santuView;
            case CommUtils.TYPE_ZHUANTI:
                break;
            case CommUtils.TYPE_HENGTU:
                HengtuViewHolder hengtuHV = new HengtuViewHolder();
                View hengtuView = inflater.inflate(R.layout.layout_item_hengtu, null);
                hengtuHV.tvTitle = (TextView) hengtuView.findViewById(R.id.tv_item_hengtu_title);
                hengtuHV.tvAuthor = (TextView) hengtuView.findViewById(R.id.tv_item_hengtu_author);
                hengtuHV.tvComments = (TextView) hengtuView.findViewById(R.id.tv_item_hengtu_comments);
                hengtuHV.ivContent = (ImageView) hengtuView.findViewById(R.id.iv_item_hengtu);
                hengtuHV.ivAuthor = (CircleImageView) hengtuView.findViewById(R.id.iv_item_hengtu_author);
                hengtuView.setTag(hengtuHV);
                return hengtuView;
        }
        DefaultViewHolder defaultVH = new DefaultViewHolder();
        View defaultView = inflater.inflate(R.layout.layout_item_default, null);
        defaultVH.tv = (TextView) defaultView.findViewById(R.id.tv_item_default);
        defaultView.setTag(defaultVH);
        return defaultView;
    }
}
