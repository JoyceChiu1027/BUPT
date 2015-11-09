package com.bupt.electricpower.online;

import java.util.Hashtable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import com.bupt.electricpower.util.CodeConstants;

public class RequestElecInfo extends SoapObject {
	private String userName;
	private String userPwd;
	private String requestDate;
	private String unit;
	private String checkType;

	public RequestElecInfo() {
		super("", "");
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCheckType() {
		return checkType;
	}

	public void setCheckType(String checkType) {
		this.checkType = checkType;
	}

	public int getPropertyCount() {
		return 5;
	}

	@Override
	public Object getProperty(int index) {
		// TODO Auto-generated method stub
		switch (index) {
		case 0:
			return userName;
		case 1:
			return userPwd;
		case 2:
			return requestDate;
		case 3:
			return unit;
		case 4:
			return checkType;
		}
		return null;
	}

	@Override
	public void setProperty(int index, Object obj) {
		switch (index) {
		case 0:
			userName = (java.lang.String) obj;
			break;
		case 1:
			userPwd = (java.lang.String) obj;
			break;
		case 2:
			requestDate=(java.lang.String) obj;
			break;
		case 3:
			unit=(java.lang.String) obj;
			break;
		case 4:
			checkType=(java.lang.String) obj;
		}
	}

	@Override
	public void getPropertyInfo(int index, Hashtable properties,
			PropertyInfo info) {
		switch (index) {
		case 0:
			info.name = "userName";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 1:
			info.name = "userPwd";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 2:
			info.name = "requestDate";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 3:
			info.name = "unit";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 4:
			info.name="checkType";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		}
	}
}
