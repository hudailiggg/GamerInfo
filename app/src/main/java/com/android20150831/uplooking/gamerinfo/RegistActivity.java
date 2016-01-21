package com.android20150831.uplooking.gamerinfo;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.comm.CommUtils;
import com.comm.GetVerifyCodeRequest;
import com.comm.RegistInformation;
import com.comm.RegistRequest;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

import org.apache.http.entity.StringEntity;
import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/12/22.
 */
public class RegistActivity extends Activity implements View.OnClickListener {
    private String phoneNumber = "";
    private String verifyCode = "";
    private String username = "";
    private String passwd = "";

    private Button btVerify;
    private Button btRegist;

    private EditText etUsername;
    private EditText etPasswd;
    private EditText etPhoneNumber;
    private EditText etVerifyCode;
    private HttpUtils conn;

    public static final int TYPECODE_MESSAGE = 1;
    private Gson gson;

    public Button getBtVerify() {
        return btVerify;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        initView();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("test", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    public Button getBtRegist() {
        return btRegist;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public String getPasswd() {
        return passwd;
    }

    private void initView() {
        conn = new HttpUtils(5000);
        gson = new Gson();
        btVerify = (Button) findViewById(R.id.bt_regist_getverify);
        btRegist = (Button) findViewById(R.id.bt_regist_regist);
        btVerify.setOnClickListener(this);
        btRegist.setOnClickListener(this);
        /**
         * EditText控件设置内容观察者(观察者设计模式)，当用户名，密码，手机号输入框不为空时开启验证码按钮
         * 当验证码输入框不为空时开启注册按钮
         */
        etUsername = ((EditText) findViewById(R.id.et_regist_username));
        etUsername.addTextChangedListener(new MyUserNameWatcher(this));

        etPasswd = ((EditText) findViewById(R.id.et_regist_passwd));
        etPasswd.addTextChangedListener(new MyPassWordWatcher(this));

        etPhoneNumber = ((EditText) findViewById(R.id.et_regist_phonenumber));
        etPhoneNumber.addTextChangedListener(new MyPhoneNumberWatcher(this));

        etVerifyCode = ((EditText) findViewById(R.id.et_regist_verifycode));
        etVerifyCode.addTextChangedListener(new MyVerifyCodeWatcher(this));
    }


    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.bt_regist_getverify:
                    getVerifyCodeByMessage();
                    break;
                case R.id.bt_regist_regist:
                    registByMessage();

                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void setResultForGoback() {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        bundle.putString("passwd", passwd);
        intent.putExtras(bundle);
        setResult(CommUtils.REGIST_RESPONSE, intent);
    }

    private void registByMessage() throws UnsupportedEncodingException {
        if (username.equals("") || passwd.equals("")
                || phoneNumber.equals("")
                || verifyCode.equals("")) {
            Toast.makeText(this, "请完善相关信息", Toast.LENGTH_LONG).show();
            return;
        }
        RegistRequest requestObj = new RegistRequest(username, passwd, phoneNumber, verifyCode);
        RequestParams params = new RequestParams();
        params.setBodyEntity(new StringEntity(gson.toJson(requestObj), "UTF-8"));
        conn.send(HttpRequest.HttpMethod.POST, CommUtils.REGIST_URL, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(RegistActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                setResultForGoback();
                finish();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(RegistActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getVerifyCodeByMessage() throws UnsupportedEncodingException {
        if (username.equals("") || phoneNumber.equals("")) {
            Toast.makeText(this, "请输入用户名和手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        GetVerifyCodeRequest requestObj =
                new GetVerifyCodeRequest(phoneNumber, username, TYPECODE_MESSAGE);
        RequestParams params = new RequestParams();
        params.setBodyEntity(new StringEntity(gson.toJson(requestObj), "UTF-8"));

        conn.send(HttpRequest.HttpMethod.POST, CommUtils.GET_VERIFYCODE_URL, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Toast.makeText(RegistActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                btCountdown();
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(RegistActivity.this, "发送失败 : " + s, Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 创建扩展自系统类的倒计时类，该类目前的功能仅仅是让获取验证码按钮具备倒计时功能
     */
    private void btCountdown() {
        CommUtils.MyCountdownTimer myCountdownTimer =
                new CommUtils.MyCountdownTimer(60000, 1000, this);
        myCountdownTimer.start();
    }
}
