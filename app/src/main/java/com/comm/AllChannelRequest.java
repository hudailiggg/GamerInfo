package com.comm;

import java.util.List;

public class AllChannelRequest extends RequestRootClass{
    private AllChannelInformation request;

    public AllChannelRequest() {
        request = new AllChannelInformation();
    }

    public AllChannelInformation getRequest() {
        return request;
    }

    public void setRequest(AllChannelInformation request) {
        this.request = request;
    }
}
