package com.comm;

import java.util.ArrayList;
import java.util.List;

public class AllChannelListResponse extends ResponseRootClass {
    private List<AllChannelListResponseInfo> result;

    public AllChannelListResponse() {
        result = new ArrayList<AllChannelListResponseInfo>();
    }

    public List<AllChannelListResponseInfo> getResult() {
        return result;
    }

    public void setResult(List<AllChannelListResponseInfo> result) {
        this.result = result;
    }
}
