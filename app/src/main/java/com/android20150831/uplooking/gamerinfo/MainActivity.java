package com.android20150831.uplooking.gamerinfo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.comm.CommUtils;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu slidingMenuInstance;
    private FragmentManager fm;
    private ArrayList<HashMap<String, Fragment>> navFragmentContainer;
    private int previousIndex = -1;
    private boolean channelDownloadComplete = false;
    private NewsPagerAdapterHandler newsPagerAdapterHandler;
    private NewsTopFragment newsTopFragment;

    public NewsPagerAdapterHandler getNewsPagerAdapterHandler() {
        return newsPagerAdapterHandler;
    }

    public boolean isChannelDownloadComplete() {
        return channelDownloadComplete;
    }

    public void setChannelDownloadComplete(boolean channelDownloadComplete) {
        this.channelDownloadComplete = channelDownloadComplete;
    }

    public ArrayList<HashMap<String, Fragment>> getNavFragmentContainer() {
        return navFragmentContainer;
    }

    public SlidingMenu getSlidingMenuInstance() {
        return slidingMenuInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("test", "MainActivity: " + "--onCreate--");

        /**
         * 写死竖屏
         */
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        /**
         * 初始化侧滑菜单，菜单使用fragment
         */
        slidingMenuWithFragment();
        /**
         * 初始化顶部导航片段，中部内容片段和底部导航片段，并将顶部导航片段和中部内容片段放到
         * 容器中映射起来
         */
        initNavFragment();
        /**
         * 初始化首页面布局
         */
        initMainContent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("test", "MainActivity: " + "--onStart--");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("test", "MainActivity: " + "--onResume--");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("test", "MainActivity: " + "--onPause--");
    }

    /**
     * Activity处于后台的时候根据当前设备内存使用状态会被不定时回收，
     * 但是依附在它上面的Fragment不会被回收，所以会导致Fragment重新
     * 加载的时候访问到
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
        Log.i("test", "MainActivity: " + "onSaveInstanceState");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("test", "MainActivity: " + "--onRestart--");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("test", "MainActivity: " + "--onStop--");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("test", "MainActivity: " + "--onDestroy--");
    }

    private void initNavFragment() {
        navFragmentContainer = new ArrayList<>();

        /*****************底部导航先创建，使用同一个底部导航**********************/
        BottomNavFragment bottomNavFragment = new BottomNavFragment();

        /****************新闻顶部导航和内容区******************/
        newsTopFragment = new NewsTopFragment();
        NewsContentFragment newsContentFragment = new NewsContentFragment();
        HashMap<String, Fragment> newsFragmentsMap = new HashMap<>();
        newsFragmentsMap.put("top", newsTopFragment);
        newsFragmentsMap.put("center", newsContentFragment);
        newsFragmentsMap.put("bottom", bottomNavFragment);
        navFragmentContainer.add(newsFragmentsMap);

        /****************攻略顶部导航和内容区******************/
        GonglueContentFragment gonglueContentFragment = new GonglueContentFragment();
        GonglueTopFragment gonglueTopFragment = new GonglueTopFragment();
        HashMap<String, Fragment> gonglueFragmentsMap = new HashMap<>();
        gonglueFragmentsMap.put("top", gonglueTopFragment);
        gonglueFragmentsMap.put("center", gonglueContentFragment);
        gonglueFragmentsMap.put("bottom", bottomNavFragment);
        navFragmentContainer.add(gonglueFragmentsMap);

        /****************手游顶部导航和内容区******************/
        ShouyouContentFragment shouyouContentFragment = new ShouyouContentFragment();
        ShouyouTopFragment shouyouTopFragment = new ShouyouTopFragment();
        HashMap<String, Fragment> shouyouFragmentsMap = new HashMap<>();
        shouyouFragmentsMap.put("top", shouyouTopFragment);
        shouyouFragmentsMap.put("center", shouyouContentFragment);
        shouyouFragmentsMap.put("bottom", bottomNavFragment);
        navFragmentContainer.add(shouyouFragmentsMap);

        /****************订阅顶部导航和内容区******************/
        DingyueContentFragment dingyueContentFragment = new DingyueContentFragment();
        DingyueTopFragment dingyueTopFragment = new DingyueTopFragment();
        HashMap<String, Fragment> dingyueFragmentsMap = new HashMap<>();
        dingyueFragmentsMap.put("top", dingyueTopFragment);
        dingyueFragmentsMap.put("center", dingyueContentFragment);
        dingyueFragmentsMap.put("bottom", bottomNavFragment);
        navFragmentContainer.add(dingyueFragmentsMap);
    }

    /**
     * 初始化首页面显示内容，使用新闻顶部导航，内容区域和底部导航
     */
    private void initMainContent() {
        fm = getFragmentManager();
        newsPagerAdapterHandler = new NewsPagerAdapterHandler(this);

        addFragment(CommUtils.NEWS_INDEX, "news");
        addFragment(CommUtils.GONGLUE_INDEX, "gonglue");
        addFragment(CommUtils.SHOUYOU_INDEX, "shouyou");
        addFragment(CommUtils.DINGYUE_INDEX, "dingyue");
        addBottomNavFragment();
        showFragments(CommUtils.NEWS_INDEX);

    }


    private void showFragments(int index) {
        hideAllFragments();
        Fragment topFragment = navFragmentContainer.get(index).get("top");
        Fragment contentFragment = navFragmentContainer.get(index).get("center");

        FragmentTransaction ft = fm.beginTransaction();
        ft.show(topFragment);
        ft.show(contentFragment);
        ft.commit();
    }

    /**
     * 隐藏所有Fragment，在显示某一特定Fragment之前调用
     */
    private void hideAllFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (HashMap<String, Fragment> map : navFragmentContainer) {
            ft.hide(map.get("top"));
            ft.hide(map.get("center"));
        }
        ft.commit();
    }

    /**
     * 添加Fragment到布局中，添加到布局中的上部和中部
     * @param index F
     * @param tag
     */
    private void addFragment(int index, String tag) {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_main_top,
                navFragmentContainer.get(index).get("top"),
                tag + "_top");
        ft.add(R.id.fl_main_center,
                navFragmentContainer.get(index).get("center"),
                tag + "_center");
        ft.commit();
    }

    private void addBottomNavFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.fl_main_bottom,
                navFragmentContainer.get(CommUtils.NEWS_INDEX).get("bottom"),
                "nav_bottom");
        ft.commit();
    }

    /**
     * 底部导航按钮点击切换、点击当前被选择按钮不做响应
     * @param index
     */
    public void switchNavigation(int index) {
        if (previousIndex == index) {
            return;
        }
        FragmentTransaction ft = fm.beginTransaction();
        showFragments(index);
        previousIndex = index;
        ft.commit();
    }

    /**
     * 初始化侧滑菜单
     */
    private void slidingMenuWithFragment() {
        setBehindContentView(R.layout.menu_frame);
        LeftMenuFragment fragment = new LeftMenuFragment();
        //这里必须用R.id.xxx形式, 不能用R.layout.xxx形式
        getFragmentManager().beginTransaction().replace(R.id.fl_menu, fragment).commit();
        slidingMenuInstance = getSlidingMenu();
        slidingMenuInstance.setMode(SlidingMenu.LEFT);
        slidingMenuInstance.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        slidingMenuInstance.setShadowWidthRes(R.dimen.slidemenu_prishadow_width);
        slidingMenuInstance.setShadowDrawable(R.drawable.shadow_left);
        slidingMenuInstance.setBehindOffsetRes(R.dimen.slidemenu_width);
        slidingMenuInstance.setFadeDegree(0.2f);
    }

}
