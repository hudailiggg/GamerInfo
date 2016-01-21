package com.comm;

import java.util.List;

public class AllChannelInformation {
    private String type;

    public AllChannelInformation() {
        type = "";
    }

    public String gettype() {
        if (type == null) {
            return "";
        } else {
            return type;
        }
    }

    public void settype(String type) {
        this.type = type;
    }
}
