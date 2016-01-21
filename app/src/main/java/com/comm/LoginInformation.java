package com.comm;

import java.util.List;

public class LoginInformation {
    private String userName;
    private String passWord;

    public LoginInformation() {
        userName = "";
        passWord = "";
    }

    public String getuserName() {
        if (userName == null) {
            return "";
        } else {
            return userName;
        }
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public String getpassWord() {
        if (passWord == null) {
            return "";
        } else {
            return passWord;
        }
    }

    public void setpassWord(String passWord) {
        this.passWord = passWord;
    }
}
