package com.comm;

public class AllChannelResponeseInfo extends ResponseRootClass {
    private int nodeId;
    private String nodeName;
    private boolean isTop;

    public AllChannelResponeseInfo() {
        nodeId = 0;
        nodeName = "";
        isTop = false;
    }

    public int getnodeId() {
        return nodeId;
    }

    public void setnodeId(int nodeId) {
        this.nodeId = nodeId;
    }

    public String getnodeName() {
        if (nodeName == null) {
            return "";
        } else {
            return nodeName;
        }
    }

    public void setnodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public boolean getisTop() {
        return isTop;
    }

    public void setisTop(boolean isTop) {
        this.isTop = isTop;
    }
}
