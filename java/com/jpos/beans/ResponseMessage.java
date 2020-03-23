package com.jpos.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class ResponseMessage {

	//String MTI;
    //String ISOHeader;
    //HashMap<String, String> fields = new HashMap<String, String>();
    List<Map<String, String>> fields = new ArrayList<Map<String, String>>();
/*
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

}
