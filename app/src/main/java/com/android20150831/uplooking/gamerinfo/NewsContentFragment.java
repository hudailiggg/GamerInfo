package com.android20150831.uplooking.gamerinfo;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.comm.CommUtils;

/**
 * 新闻内容片段，该片段布局是一个ViewPager容器，里面存放不同的新闻页片段
 */
public class NewsContentFragment extends Fragment {
    private View view;
    private ViewPager viewPager;
    private MainActivity mainActivityInstance;
    private Context context;
    private NewsFragmentPagerAdapter pagerAdapter;

    public NewsFragmentPagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("test", "NewsContentFragment onCreateView");
        view = inflater.inflate(R.layout.layout_news_content, null);
        viewPager = (ViewPager) view.findViewById(R.id.vp_news_content);
        context = getActivity();
        mainActivityInstance = (MainActivity) context;
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (mainActivityInstance) {
                    if (!mainActivityInstance.isChannelDownloadComplete()) {
                        try {
                            mainActivityInstance.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Log.i("test", "即将把下载之后的频道条目添加到内容页面适配器");
                    mainActivityInstance.getNewsPagerAdapterHandler().sendMessage(Message.obtain());
                    mainActivityInstance.setChannelDownloadComplete(false);
                }
            }
        }).start();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        Log.i("test", "NewsContentFragment onAttach");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("test", "NewsContentFragment onCreate");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("test", "NewsContentFragment onActivityCreated");
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.i("test", "NewsContentFragment onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("test", "NewsContentFragment onSaveInstanceState");

    }

    @Override
    public void onStart() {
        Log.i("test", "NewsContentFragment onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i("test", "NewsContentFragment onResume");
        super.onResume();

    }

    @Override
    public void onPause() {
        Log.i("test", "NewsContentFragment onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i("test", "NewsContentFragment onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i("test", "NewsContentFragment onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i("test", "NewsContentFragment onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i("test", "NewsContentFragment onDetach");
        super.onDetach();
    }
}
