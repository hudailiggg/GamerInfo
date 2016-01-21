package com.comm;

import java.util.List;

public class GetVerifyCodeRequest {
    private String os;
    private String osVersion;
    private String app;
    private String appVersion;
    private String deviceId;
    private String deviceType;
    private VerifyCodeRequest request;

    public GetVerifyCodeRequest() {
        os = "android";
        osVersion = "";
        app = "";
        appVersion = "";
        deviceId = "";
        deviceType = "";
        request = new VerifyCodeRequest();
    }

    public GetVerifyCodeRequest(String phoneNumber, String userName, int codetype) {
        this();
        request.setusername(userName);
        request.setphoneNumber(phoneNumber);
        request.setcodetype(codetype);
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

    public VerifyCodeRequest getrequest() {
        return request;
    }

    public void setrequest(VerifyCodeRequest request) {
        this.request = request;
    }
}
