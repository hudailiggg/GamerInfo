package com.android20150831.uplooking.gamerinfo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.comm.CommUtils;

/**
 * Created by Administrator on 2016/1/20.
 */
public class NewsFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private RadioGroup rg;

    public NewsFragmentPagerAdapter(FragmentManager fm, RadioGroup rg) {
        super(fm);
        this.rg = rg;
        Log.i("test", "rg has:" + rg.getChildCount() + "child");
    }

    @Override
    public Fragment getItem(int position) {
        Log.i("test", "getItem:" + position);
        Log.i("test", "pageName:" + ((RadioButton) rg.getChildAt(position)).getText().toString());
        return NewsContentPageFragment.newInstance(((RadioButton) rg.getChildAt(position)).getText().toString(),
                ((CommUtils.ItemInfo) rg.getChildAt(position).getTag()).requestId);
    }

    @Override
    public int getCount() {
        return rg.getChildCount();
    }
}
