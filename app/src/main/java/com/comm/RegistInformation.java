package com.comm;

import java.util.List;

public class RegistInformation {
    private String phoneNumber;
    private String email;
    private String userName;
    private String password;
    private String question;
    private String answer;
    private String phoneVerificationCode;

    public RegistInformation() {
        phoneNumber = "";
        email = "";
        userName = "";
        password = "";
        question = "";
        answer = "";
        phoneVerificationCode = "";
    }

    public String getphoneNumber() {
        if (phoneNumber == null) {
            return "";
        } else {
            return phoneNumber;
        }
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getemail() {
        if (email == null) {
            return "";
        } else {
            return email;
        }
    }

    public void setemail(String email) {
        this.email = email;
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

    public String getpassword() {
        if (password == null) {
            return "";
        } else {
            return password;
        }
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public String getquestion() {
        if (question == null) {
            return "";
        } else {
            return question;
        }
    }

    public void setquestion(String question) {
        this.question = question;
    }

    public String getanswer() {
        if (answer == null) {
            return "";
        } else {
            return answer;
        }
    }

    public void setanswer(String answer) {
        this.answer = answer;
    }

    public String getphoneVerificationCode() {
        if (phoneVerificationCode == null) {
            return "";
        } else {
            return phoneVerificationCode;
        }
    }

    public void setphoneVerificationCode(String phoneVerificationCode) {
        this.phoneVerificationCode = phoneVerificationCode;
    }
}
