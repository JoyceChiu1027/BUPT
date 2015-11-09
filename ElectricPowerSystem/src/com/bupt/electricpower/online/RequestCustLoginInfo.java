package com.bupt.electricpower.online;

import java.util.Hashtable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import com.bupt.electricpower.util.CodeConstants;

public class RequestCustLoginInfo extends SoapObject {

	private String custlName;
	private String custlpwd;

	public RequestCustLoginInfo() {
		super("", "");
		// TODO Auto-generated constructor stub
	}

	public String getCustlName() {
		return custlName;
	}

	public void setCustlName(String custlName) {
		this.custlName = custlName;
	}

	public String getCustlpwd() {
		return custlpwd;
	}

	public void setCustlpwd(String custlpwd) {
		this.custlpwd = custlpwd;
	}
	public int getPropertyCount() {
		return 2;
	}

	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return custlName;
		case 1:
			return custlpwd;
		}
		return null;
	}

	public void setProperty(int index, Object obj) {
		switch (index) {
		case 0:
			custlName = (java.lang.String) obj;
			break;
		case 1:
			custlpwd = (java.lang.String) obj;
			break;

		}
	}

	@Override
	public void getPropertyInfo(int index, Hashtable properties,
			PropertyInfo info) {
		switch (index) {
		case 0:
			info.name = "custlName";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 1:
			info.name = "custlpwd";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		}
	}

}
