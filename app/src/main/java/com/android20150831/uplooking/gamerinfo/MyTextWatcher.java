package com.android20150831.uplooking.gamerinfo;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;


public class MyTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}

class MyUserNameWatcher extends MyTextWatcher {
    private Context context;

    public MyUserNameWatcher(Context context) {
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (context instanceof RegistActivity) {
            ((RegistActivity) context).setUsername(s.toString().toLowerCase());
        } else if (context instanceof LoginActivity) {
            ((LoginActivity) context).setUserName(s.toString().toLowerCase());
        }

    }
}

class MyPassWordWatcher extends MyTextWatcher {
    private Context context;

    public MyPassWordWatcher(Context context) {
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (context instanceof RegistActivity) {
            ((RegistActivity) context).setPasswd(s.toString());
        } else if (context instanceof LoginActivity) {
            ((LoginActivity) context).setPasswd(s.toString());
            if (((LoginActivity) context).getPasswd().equals("")) {
                ((Button) ((LoginActivity) context).
                        findViewById(R.id.bt_login_login)).setEnabled(false);
            } else {
                ((Button) ((LoginActivity) context).
                        findViewById(R.id.bt_login_login)).setEnabled(true);
            }
        }

    }
}

class MyPhoneNumberWatcher extends MyTextWatcher {
    private Context context;

    public MyPhoneNumberWatcher(Context context) {
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        ((RegistActivity) context).setPhoneNumber(s.toString());

        if (((RegistActivity) context).getPhoneNumber().equals("")) {
            ((RegistActivity) context).getBtVerify().setEnabled(false);
        } else {
            ((RegistActivity) context).getBtVerify().setEnabled(true);
        }
    }
}

class MyVerifyCodeWatcher extends MyTextWatcher {
    private Context context;

    public MyVerifyCodeWatcher(Context context) {
        this.context = context;
    }

    @Override
    public void afterTextChanged(Editable s) {
        ((RegistActivity) context).setVerifyCode(s.toString());
        if (((RegistActivity) context).getVerifyCode().equals("")) {
            ((RegistActivity) context).getBtRegist().setEnabled(false);
        } else {
            ((RegistActivity) context).getBtRegist().setEnabled(true);
        }
    }
}