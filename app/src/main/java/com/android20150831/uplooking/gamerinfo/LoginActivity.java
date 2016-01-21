package com.android20150831.uplooking.gamerinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comm.CommUtils;
import com.comm.LoginRequest;
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
 * Created by Administrator on 2015/12/22.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private HttpUtils conn;
    private String userName = "";
    private String passwd = "";

    private EditText etUserName;
    private EditText etPasswd;

    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    private void initView() {
        conn = new HttpUtils(5000);
        gson = new Gson();
        ((Button) findViewById(R.id.bt_login_regist)).setOnClickListener(this);
        ((Button) findViewById(R.id.bt_login_login)).setOnClickListener(this);
        ((EditText) findViewById(R.id.et_username)).addTextChangedListener(new MyUserNameWatcher(this));
        ((EditText) findViewById(R.id.et_passwd)).addTextChangedListener(new MyPassWordWatcher(this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Bundle bundle = data.getExtras();
        //Log.i("test", bundle.getString("username") + "  " + bundle.getString("passwd"));
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.bt_login_regist:
                    registNewUser();
                    break;
                case R.id.bt_login_login:
                    login();
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {

        }


    }

    private void registNewUser() {
        Intent intent = new Intent(this, RegistActivity.class);
        startActivityForResult(intent, CommUtils.REGIST_REQUEST);
    }

    private void login() throws UnsupportedEncodingException {
        if (userName.equals("") || passwd.equals("")) {
            Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_LONG).show();
            return;
        }

        LoginRequest request = new LoginRequest(userName, passwd);
        RequestParams params = new RequestParams();
        params.setBodyEntity(new StringEntity(gson.toJson(request), "UTF-8"));
        conn.send(HttpRequest.HttpMethod.POST, CommUtils.LOGIN_URL, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.i("test", responseInfo.result);
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(LoginActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                Log.i("test", s);
            }
        });
    }
}
