package com.comm;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android20150831.uplooking.gamerinfo.R;
import com.android20150831.uplooking.gamerinfo.RegistActivity;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/12/23.
 */
public class CommUtils {
    /**************
     * 网络请求URL
     *****************/
    public static final String GET_VERIFYCODE_URL = "http://appapi2.gamersky.com/v2/GetVerificationCode";
    public static final String REGIST_URL = "http://appapi2.gamersky.com/v2/SubmitRegistrationInfo";
    public static final String LOGIN_URL = "http://appapi2.gamersky.com/v2/login";
    public static final String ALLCHANNEL_URL = "http://appapi2.gamersky.com/v2/AllChannel";
    public static final String ALLCHANNELLIST_URL = "http://appapi2.gamersky.com/v2/AllChannelList";

    /**************
     * 注册请求和响应码
     *****************/
    public static final int REGIST_REQUEST = 10;
    public static final int REGIST_RESPONSE = 11;

    /**************
     * 片段在容器中的索引
     *****************/
    public static final int NEWS_INDEX = 0;
    public static final int GONGLUE_INDEX = 1;
    public static final int SHOUYOU_INDEX = 2;
    public static final int DINGYUE_INDEX = 3;

    /**************
     * 频道按钮ID
     *****************/
    public static int TOUTIAO_ID;
    public static int YOUXI_ID;
    public static int QUWEI_ID;
    public static int FULI_ID;
    public static int SHOUYOU_ID;
    public static int YINGSHI_ID;
    public static int KEJI_ID;
    public static int DONGMAN_ID;
    public static int DIANPING_ID;
    public static int CHANYE_ID;

    /**************
     * 新闻 parent Node ID
     *****************/
    public static final String NEWS_PARENT_ID = "news";
    /**************
     * 新闻 pageIndex
     *****************/
    public static final String NEWS_PAGE_INDEX = "1";
    /**************
     * 新闻 每一个页面默认的条目请求数
     *****************/
    public static final int ITEM_COUNT = 20;

    /**************
     * 新闻每个条目的类型ID, TYPE_COUNT类型个数，类型ID只能小于TYPE_COUNT
     *****************/
    public static final int TYPE_ERROR = -1;
    public static final int TYPE_XINWEN = 0;
    public static final int TYPE_SANTU = 1;
    public static final int TYPE_ZHUANTI = 2;
    public static final int TYPE_HENGTU = 3;
    public static final int TYPE_COUNT = 4;

    public static HttpUtils conn = new HttpUtils(5000);
    public static Gson gson = new Gson();

    /**
     * 频道信息
     */
    public static class ItemInfo {
        public int index;
        public int itemOffset;
        public int requestId;

        public ItemInfo(int index, int itemOffset, int requestId) {
            this.index = index;
            this.itemOffset = itemOffset;
            this.requestId = requestId;
        }
    }


    /**
     * 尺寸转换工具类
     */
    public static class DensityUtil {
        /**
         * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
         */
        public static int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        /**
         * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
         */
        public static int px2dip(Context context, float pxValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        }
    }

    /**
     * 倒计时工具类
     */
    public static class MyCountdownTimer extends CountDownTimer {
        private Context context;

        public MyCountdownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        public MyCountdownTimer(long millisInFuture, long countDownInterval, Context context) {
            super(millisInFuture, countDownInterval);
            this.context = context;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            if (context instanceof RegistActivity) {
                if (((RegistActivity) context).findViewById(R.id.bt_regist_getverify).isEnabled()) {
                    ((RegistActivity) context).findViewById(R.id.bt_regist_getverify).setEnabled(false);
                }
                ((Button) ((RegistActivity) context).
                        findViewById(R.id.bt_regist_getverify)).
                        setText((millisUntilFinished / 1000) + "秒");
            }
        }

        @Override
        public void onFinish() {
            if (context instanceof RegistActivity) {
                if (!((RegistActivity) context).findViewById(R.id.bt_regist_getverify).isEnabled()) {
                    ((RegistActivity) context).findViewById(R.id.bt_regist_getverify).setEnabled(true);
                }
                ((Button) ((RegistActivity) context).
                        findViewById(R.id.bt_regist_getverify)).setText("重新获取");

            }
        }
    }
}



