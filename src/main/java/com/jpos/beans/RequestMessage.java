package com.jpos.beans;

public class RequestMessage {

	String MTI;
	String refId;
	String reqTime;
	String idNo;
	
	public String getMTI() {
		return MTI;
	}
	public void setMTI(String mTI) {
		MTI = mTI;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getReqTime() {
		return reqTime;
	}
	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
}
