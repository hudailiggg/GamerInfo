package com.comm;

/**
 * Created by Administrator on 2016/1/4.
 */
public class RequestRootClass {
    private String os;
    private String osVersion;
    private String deviceType;
    private String appVersion;
    private String deviceId;

    public RequestRootClass() {
        os = "";
        osVersion = "";
        deviceType = "";
        appVersion = "";
        deviceId = "";
    }

    public RequestRootClass(String os, String osVersion, String deviceType,
                            String appVersion, String deviceId) {
        this.os = os;
        this.osVersion = osVersion;
        this.deviceType = deviceType;
        this.appVersion = appVersion;
        this.deviceId = deviceId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
