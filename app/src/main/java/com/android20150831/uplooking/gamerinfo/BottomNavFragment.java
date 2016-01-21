package com.android20150831.uplooking.gamerinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.comm.CommUtils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 底部导航片段，用于几个大类的内容切换
 * 大类有：新闻、攻略、手游、订阅
 */
public class BottomNavFragment extends Fragment implements View.OnClickListener {
    private View rootLayout;
    private RelativeLayout newsRl;
    private RelativeLayout gonglueRl;
    private RelativeLayout shouyouRl;
    private RelativeLayout dingyueRl;
    private ArrayList<HashMap<String, View>> navArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootLayout == null) {
            rootLayout = inflater.inflate(R.layout.layout_bottom_navs, null);
            initView();
        }

        return rootLayout;
    }

    private void initView() {
        navArray = new ArrayList<>();
        newsRl = ((RelativeLayout) rootLayout.findViewById(R.id.rl_news_nav));
        newsRl.setOnClickListener(this);
        HashMap<String, View> hashMapNews = new HashMap<>();
        hashMapNews.put("iv", newsRl.findViewById(R.id.iv_xinwen));
        hashMapNews.put("tv", newsRl.findViewById(R.id.tv_xinwen));
        navArray.add(hashMapNews);

        gonglueRl = (RelativeLayout) rootLayout.findViewById(R.id.rl_gonglue_nav);
        gonglueRl.setOnClickListener(this);
        HashMap<String, View> hashMapGonglue = new HashMap<>();
        hashMapGonglue.put("iv", gonglueRl.findViewById(R.id.iv_gonglue));
        hashMapGonglue.put("tv", gonglueRl.findViewById(R.id.tv_gonglue));
        navArray.add(hashMapGonglue);

        shouyouRl = (RelativeLayout) rootLayout.findViewById(R.id.rl_shouyou_nav);
        shouyouRl.setOnClickListener(this);
        HashMap<String, View> hashMapShouyou = new HashMap<>();
        hashMapShouyou.put("iv", shouyouRl.findViewById(R.id.iv_shouyou));
        hashMapShouyou.put("tv", shouyouRl.findViewById(R.id.tv_shouyou));
        navArray.add(hashMapShouyou);

        dingyueRl = (RelativeLayout) rootLayout.findViewById(R.id.rl_dingyue_nav);
        dingyueRl.setOnClickListener(this);
        HashMap<String, View> hashMapDingyue = new HashMap<>();
        hashMapDingyue.put("iv", dingyueRl.findViewById(R.id.iv_dingyue));
        hashMapDingyue.put("tv", dingyueRl.findViewById(R.id.tv_dingyue));
        navArray.add(hashMapDingyue);

        cleanNavstate();
        setNavChecked(newsRl, R.id.iv_xinwen, R.id.tv_xinwen);
    }

    private void setNavChecked(RelativeLayout rl, int ivId, int tvId) {
        ((ImageView) rl.findViewById(ivId)).setSelected(true);
        ((TextView) rl.findViewById(tvId)).setSelected(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_news_nav:
                cleanNavstate();
                newsNavPressed();
                break;
            case R.id.rl_gonglue_nav:
                cleanNavstate();
                gonglueNavPressed();
                break;
            case R.id.rl_shouyou_nav:
                cleanNavstate();
                shouyouNavPressed();
                break;
            case R.id.rl_dingyue_nav:
                cleanNavstate();
                dingyueNavPressed();
                break;
        }
    }

    private HashMap<String, Fragment> getFragmentMap(int index) {
        return ((MainActivity) getActivity()).getNavFragmentContainer().get(index);
    }

    private void dingyueNavPressed() {
        setNavChecked(dingyueRl, R.id.iv_dingyue, R.id.tv_dingyue);
        ((MainActivity) getActivity()).switchNavigation(CommUtils.DINGYUE_INDEX);
    }

    private void shouyouNavPressed() {
        setNavChecked(shouyouRl, R.id.iv_shouyou, R.id.tv_shouyou);
        ((MainActivity) getActivity()).switchNavigation(CommUtils.SHOUYOU_INDEX);
    }

    private void gonglueNavPressed() {
        setNavChecked(gonglueRl, R.id.iv_gonglue, R.id.tv_gonglue);
        ((MainActivity) getActivity()).switchNavigation(CommUtils.GONGLUE_INDEX);
    }

    private void newsNavPressed() {
        setNavChecked(newsRl, R.id.iv_xinwen, R.id.tv_xinwen);
        ((MainActivity) getActivity()).switchNavigation(CommUtils.NEWS_INDEX);
    }

    private void cleanNavstate() {
        for (int i = 0; i < navArray.size(); i++) {
            HashMap<String, View> hashMap = navArray.get(i);
            hashMap.get("iv").setSelected(false);
            hashMap.get("tv").setSelected(false);
        }
    }
}
