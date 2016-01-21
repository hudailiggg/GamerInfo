package com.comm;

import java.util.List;

public class AllChannelListRequestInfo {
	private String parentNodeId;
	private String nodeIds;
	private String pageIndex;
	private int elementsCountPerPage;

	public AllChannelListRequestInfo() {
		this.parentNodeId = "";
		this.nodeIds = "";
		this.pageIndex = "";
		this.elementsCountPerPage = 0;
	}

	public String getparentNodeId() {
              if (parentNodeId==null) {
                  return "";
              }
              else{
                  return parentNodeId;
              }
	}

	public void setparentNodeId(String parentNodeId) {
		this.parentNodeId = parentNodeId;
	}

	public String getnodeIds() {
              if (nodeIds==null) {
                  return "";
              }
              else{
                  return nodeIds;
              }
	}

	public void setnodeIds(String nodeIds) {
		this.nodeIds = nodeIds;
	}

	public String getpageIndex() {
              if (pageIndex==null) {
                  return "";
              }
              else{
                  return pageIndex;
              }
	}

	public void setpageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getelementsCountPerPage() {
		return elementsCountPerPage;
	}

	public void setelementsCountPerPage(int elementsCountPerPage) {
		this.elementsCountPerPage = elementsCountPerPage;
	}
}
