package com.comm;


import java.util.ArrayList;
import java.util.List;

public class AllChannelResponese extends ResponseRootClass {
    private ArrayList<AllChannelResponeseInfo> result;

    public AllChannelResponese() {
        this.result = new ArrayList<>();
    }

    public ArrayList<AllChannelResponeseInfo> getResult() {
        return result;
    }

    public void setResult(ArrayList<AllChannelResponeseInfo> result) {
        this.result = result;
    }
}
