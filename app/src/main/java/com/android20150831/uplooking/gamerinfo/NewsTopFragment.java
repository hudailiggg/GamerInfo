package com.android20150831.uplooking.gamerinfo;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.comm.AllChannelRequest;
import com.comm.AllChannelResponese;
import com.comm.AllChannelResponeseInfo;
import com.comm.CommUtils;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 新闻顶部导航片段，用于存放不同频道的条目，频道列表通过网络请求获取
 */
public class NewsTopFragment extends Fragment implements View.OnClickListener {
    private View rootLayout;
    private ImageView ivOpenSlidingMenu;
    private ImageView ivSortItem;
    private HorizontalScrollView hsv;
    private Gson gson = new Gson();
    private HttpUtils conn;
    private AllChannelResponese responese;
    private LayoutInflater inflater;
    private RadioGroup rg;
    private MainActivity mainActivityInstance;

    public HorizontalScrollView getHsv() {
        return hsv;
    }

    public RadioGroup getRg() {
        return rg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("test", "NewsTopFragment onCreateView");
        this.inflater = inflater;
        mainActivityInstance = (MainActivity) getActivity();
        try {
            rootLayout = inflater.inflate(R.layout.layout_news_top, null);
            initView();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return rootLayout;
    }

    private void initView() throws UnsupportedEncodingException, InterruptedException {
        ivOpenSlidingMenu = (ImageView) rootLayout.findViewById(R.id.iv_open_slidingmenu);
        ivOpenSlidingMenu.setOnClickListener(this);
        ivSortItem = (ImageView) rootLayout.findViewById(R.id.iv_sort_navitem);
        ivSortItem.setOnClickListener(this);
        hsv = (HorizontalScrollView) rootLayout.findViewById(R.id.hsv_news_top_nav);
        rg = (RadioGroup) hsv.findViewById(R.id.rg_news_top_nav);
        Log.i("test", rg + "");
        conn = new HttpUtils(2000);
        allChannelRequest();
    }

    private void allChannelRequest() throws UnsupportedEncodingException {
        responese = new AllChannelResponese();
        AllChannelRequest request = new AllChannelRequest();
        request.setOs("android");
        request.getRequest().settype("0");
        RequestParams params = new RequestParams();
        params.setBodyEntity(new StringEntity(gson.toJson(request), "UTF-8"));
        conn.send(HttpRequest.HttpMethod.POST, CommUtils.ALLCHANNEL_URL, params, new RequestCallBack<String>() {
            @Override
            public void onLoading(long total, long current, boolean isUploading) {

            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("test", responseInfo.result);
                synchronized (mainActivityInstance) {
                    if (mainActivityInstance.isChannelDownloadComplete()) {
                        try {
                            mainActivityInstance.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("test", "即将下载并创建频道条目");
                    responese = gson.fromJson(responseInfo.result, AllChannelResponese.class);
                    createNavItem(responese);
                    mainActivityInstance.setChannelDownloadComplete(true);
                    mainActivityInstance.notify();
                }
                Log.i("test", "频道创建完毕");
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(mainActivityInstance, "网络状况不佳,请检查网络", Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 创建频道条目，除了第一个头条条目之外，其余条目都动态创建
     *
     * @param responese
     */
    private void createNavItem(AllChannelResponese responese) {
        ArrayList<AllChannelResponeseInfo> arrayList = responese.getResult();

        RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        int marginLeft = (int) getResources().getDimension(R.dimen.channel_item_margin);
        layoutParams.setMargins(marginLeft, 0, 0, 0);


        int itemOffset = marginLeft + rg.findViewById(R.id.rb_toutiao).getWidth();

        CommUtils.TOUTIAO_ID = R.id.rb_toutiao;
        rg.findViewById(R.id.rb_toutiao).setOnClickListener(this);
        rg.findViewById(R.id.rb_toutiao).setTag(new CommUtils.ItemInfo(0, 0, 0));

        for (int i = 0; i < arrayList.size(); i++) {
            RadioButton radioButton = (RadioButton) inflater.inflate(R.layout.radiobutton_temp, null);
            int tmpId = radioButton.getId() + arrayList.get(i).getnodeId();
            radioButton.setId(tmpId);
            radioButton.setText(arrayList.get(i).getnodeName());
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            saveChannelItemId(arrayList.get(i).getnodeName(), tmpId);
            radioButton.setOnClickListener(this);
            radioButton.setTag(new CommUtils.ItemInfo(i + 1
                    , (i + 1) * itemOffset
                    , arrayList.get(i).getnodeId()));
            rg.addView(radioButton, layoutParams);
        }

    }

    private void saveChannelItemId(String itemName, int tmpId) {
        switch (itemName) {
            case "游戏":
                CommUtils.YOUXI_ID = tmpId;
                break;
            case "趣味":
                CommUtils.QUWEI_ID = tmpId;
                break;
            case "福利":
                CommUtils.FULI_ID = tmpId;
                break;
            case "手游":
                CommUtils.SHOUYOU_ID = tmpId;
                break;
            case "影视":
                CommUtils.YINGSHI_ID = tmpId;
                break;
            case "科技":
                CommUtils.KEJI_ID = tmpId;
                break;
            case "动漫":
                CommUtils.DONGMAN_ID = tmpId;
                break;
            case "点评":
                CommUtils.DIANPING_ID = tmpId;
                break;
            case "产业":
                CommUtils.CHANYE_ID = tmpId;
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_open_slidingmenu:
                showSlidingMenu();
                break;
            case R.id.iv_sort_navitem:
                break;
        }
    }

    private void showSlidingMenu() {
        ((MainActivity) getActivity()).getSlidingMenuInstance().showMenu(true);
    }
}
