package com.comm;

import java.util.List;

public class AllChannelListRequest extends RequestRootClass{
	private AllChannelListRequestInfo request;

	public AllChannelListRequest() {
		this.request = new AllChannelListRequestInfo();
	}

	public AllChannelListRequestInfo getrequest() {
		return request;
	}

	public void setrequest(AllChannelListRequestInfo request) {
		this.request = request;
	}
}
