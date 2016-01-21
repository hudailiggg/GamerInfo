package com.comm;

import java.util.List;

public class RegistRequest {
    private String os;
    private String osVersion;
    private String app;
    private String appVersion;
    private String deviceId;
    private String deviceType;
    private RegistInformation request;

    public RegistRequest() {
        os = "android";
        osVersion = "";
        app = "";
        appVersion = "";
        deviceId = "";
        deviceType = "";
        request = new RegistInformation();
    }

    public RegistRequest(String userName, String passwd, String phoneNumber, String phoneVerificationCode) {
        this();
        request.setuserName(userName);
        request.setpassword(passwd);
        request.setphoneNumber(phoneNumber);
        request.setphoneVerificationCode(phoneVerificationCode);
    }

    public String getos() {
        if (os == null) {
            return "";
        } else {
            return os;
        }
    }

    public void setos(String os) {
        this.os = os;
    }

    public String getosVersion() {
        if (osVersion == null) {
            return "";
        } else {
            return osVersion;
        }
    }

    public void setosVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getapp() {
        if (app == null) {
            return "";
        } else {
            return app;
        }
    }

    public void setapp(String app) {
        this.app = app;
    }

    public String getappVersion() {
        if (appVersion == null) {
            return "";
        } else {
            return appVersion;
        }
    }

    public void setappVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getdeviceId() {
        if (deviceId == null) {
            return "";
        } else {
            return deviceId;
        }
    }

    public void setdeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getdeviceType() {
        if (deviceType == null) {
            return "";
        } else {
            return deviceType;
        }
    }

    public void setdeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public RegistInformation getrequest() {
        return request;
    }

    public void setrequest(RegistInformation request) {
        this.request = request;
    }
}
