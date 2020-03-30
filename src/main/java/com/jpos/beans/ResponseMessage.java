package com.jpos.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ResponseMessage {

	//String MTI;
    //String ISOHeader;
    //HashMap<String, String> fields = new HashMap<String, String>();
	String version;
    List<Map<String, String>> fields = new ArrayList<Map<String, String>>();
/* not used
    public String getMTI() {
	return MTI;
    }

    public void setMTI(String mTI) {
	MTI = mTI;
    }

    public String getISOHeader() {
	return ISOHeader;
    }

    public void setISOHeader(String iSOHeader) {
	ISOHeader = iSOHeader;
    }

    public HashMap<String, String> getFields() {
	return fields;
    }

    public void setFields(HashMap<String, String> fields) {
	this.fields = fields;
    }
*/

	public List<Map<String, String>> getFields() {
		return fields;
	}

	public void setFields(List<Map<String, String>> listOfFields) {
		this.fields = listOfFields;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

/* Testing new structure	
	String refId;
	String reqTime;
	String rplTime;
	EpfProfile epfProfile;
	EpfAccount epfAccount;	
	
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
	public String getRplTime() {
		return rplTime;
	}
	public void setRplTime(String rplTime) {
		this.rplTime = rplTime;
	}
	public EpfProfile getEpfProfile() {
		return epfProfile;
	}
	public void setEpfProfile(EpfProfile epfProfile) {
		this.epfProfile = epfProfile;
	}
	public EpfAccount getEpfAccount() {
		return epfAccount;
	}
	public void setEpfAccount(EpfAccount epfAccount) {
		this.epfAccount = epfAccount;
	}
*/

}

/*
class EpfProfile {
	String memberName;
	String epfNo;
	String idNo;
	String passportNo;
    String epfMembershipDate;
    String iAkaunMembershipDate;
    String iAkaunStatus;
}
class EpfAccount {
	float totalBalance;
	float account1Balance;
	float account2Balance;
}
*/	