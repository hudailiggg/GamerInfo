package com.android20150831.uplooking.gamerinfo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/12/20.
 */
public class LeftMenuFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("test", "LeftMenuFragment : onCreateView");
        View view = inflater.inflate(R.layout.layout_slidemenu, null);
        setViewClickListener(view);
        return view;
    }

    private void setViewClickListener(View view) {
        view.findViewById(R.id.rl_drawer_login).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_reserver).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_search).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_summary).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_management).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_night).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_pattern).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_cleancache).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_feedback).setOnClickListener(this);
        view.findViewById(R.id.rl_drawer_settings).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_drawer_login:
                login();
                break;
            case R.id.rl_drawer_reserver:
                break;
            case R.id.rl_drawer_search:
                break;
            case R.id.rl_drawer_summary:
                break;
            case R.id.rl_drawer_management:
                break;
            case R.id.rl_drawer_night:
                break;
            case R.id.rl_drawer_pattern:
                break;
            case R.id.rl_drawer_cleancache:
                break;
            case R.id.rl_drawer_feedback:
                break;
            case R.id.rl_drawer_settings:
                break;
        }
    }

    private void login() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }
}
