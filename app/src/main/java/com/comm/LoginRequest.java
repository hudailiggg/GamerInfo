package com.comm;

import java.util.List;

public class LoginRequest {
    private String os;
    private String osVersion;
    private String deviceType;
    private String appVersion;
    private String deviceId;
    private LoginInformation request;

    public LoginRequest() {
        os = "android";
        osVersion = "";
        appVersion = "";
        deviceId = "";
        deviceType = "";
        request = new LoginInformation();
    }

    public LoginRequest(String userName, String passwd) {
        this();
        request.setuserName(userName);
        request.setpassWord(passwd);
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

    public LoginInformation getrequest() {
        return request;
    }

    public void setrequest(LoginInformation request) {
        this.request = request;
    }
}
