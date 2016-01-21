package com.android20150831.uplooking.gamerinfo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.comm.AllChannelListRequest;
import com.comm.AllChannelListResponse;
import com.comm.AllChannelListResponseInfo;
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

/**
 * 新闻内容页片段，当ViewPager滑动时动态创建该片段实例，
 * 并通过传递不同的请求ID来获取不同新闻频道的网络数据
 */
public class NewsContentPageFragment extends Fragment {
    private String pageName;
    private HttpUtils conn;
    private View listParentView;
    private View pagerParentView;
    private ViewPager viewPager;
    private ListView listView;
    private Context context;

    public static NewsContentPageFragment newInstance(String pageName, int requestId) {
        Bundle args = new Bundle();
        args.putInt("requestid", requestId);
        args.putString("pagename", pageName);

        /*Log.i("test", "requestid:" + requestId);
        Log.i("test", "pagename:" + pageName);*/

        NewsContentPageFragment fragment = new NewsContentPageFragment();

        /**相当于设置成员变量*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.pageName = getArguments().getString("pagename");
        //Log.i("test", pageName + " : " + "onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        conn = new HttpUtils(2000);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Log.i("test", pageName + " : " + "onCreateView");
        listParentView = inflater.inflate(R.layout.layout_news_page, null);
        pagerParentView = inflater.inflate(R.layout.layout_item_huandeng, null);
        viewPager = (ViewPager) pagerParentView.findViewById(R.id.vp_news_item);
        listView = (ListView) listParentView.findViewById(R.id.lv_news_page);
        try {
            allChannelListRequest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return listParentView;
    }

    private void allChannelListRequest() throws UnsupportedEncodingException {
        AllChannelListRequest request = new AllChannelListRequest();
        setRequestEntity(request);
        final RequestParams params = new RequestParams();
        params.setBodyEntity(new StringEntity(new Gson().toJson(request), "UTF-8"));
        conn.send(HttpRequest.HttpMethod.POST, CommUtils.ALLCHANNELLIST_URL, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("test", responseInfo.result);
                AllChannelListResponse response = new Gson().fromJson(responseInfo.result,
                        AllChannelListResponse.class);
                AllChannelListResponseInfo headerItem = response.getResult().remove(0);
                setItemHeaderAdapter(headerItem);
                listView.addHeaderView(pagerParentView);
                listView.setAdapter(new NewsListContentAdapter(context, response));
            }

            private void setDefaultTitle(View parentView, AllChannelListResponseInfo headerItem) {
                TextView tvTitle = (TextView) parentView.findViewById(R.id.tv_huandeng_title);
                tvTitle.setText(headerItem.getchildElements().get(0).gettitle());
            }

            private void setItemHeaderAdapter(AllChannelListResponseInfo headerItem) {
                viewPager.setAdapter(new NewsItemHeaderPagerAdapter(context, headerItem));
                viewPager.setCurrentItem(headerItem.getchildElements().size() * 20 / 2);
                setDefaultTitle(pagerParentView, headerItem);
                viewPager.addOnPageChangeListener(new NewsItemHeaderOnPageChangeListener(pagerParentView, headerItem));
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(context, "网络状况不佳，请检查网络", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void setRequestEntity(AllChannelListRequest request) {
        request.setOs("android");
        request.getrequest().setparentNodeId(CommUtils.NEWS_PARENT_ID);
        request.getrequest().setpageIndex(CommUtils.NEWS_PAGE_INDEX);
        request.getrequest().setnodeIds(((int) getArguments().get("requestid")) + "");
        request.getrequest().setelementsCountPerPage(CommUtils.ITEM_COUNT);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("test", pageName + " : " + "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Log.i("test", pageName + " : " + "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        //Log.i("test", pageName + " : " + "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        //Log.i("test", pageName + " : " + "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.i("test", pageName + " : " + "onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.i("test", pageName + " : " + "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.i("test", pageName + " : " + "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Log.i("test", pageName + " : " + "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Log.i("test", pageName + " : " + "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Log.i("test", pageName + " : " + "onDetach");
    }
}
