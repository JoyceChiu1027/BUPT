package com.bupt.electricpower.online;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import com.bupt.electricpower.util.CodeConstants;

public class RequestTracesInfo extends SoapObject {
	private String userName;
	private String userPwd;
	private String personId;
	private String track;
	private String leaveDate;
	private String backDate;
	private String cause;

	public RequestTracesInfo() {
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

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getTrack() {
		return track;
	}

	public void setTrack(String track) {
		this.track = track;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getBackDate() {
		return backDate;
	}

	public void setBackDate(String backDate) {
		this.backDate = backDate;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}
	
	@Override
	public int getPropertyCount() {
		// TODO Auto-generated method stub
		return 7;
	}
	@Override
	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return userName;
		case 1:
			return userPwd;
		case 2:
			return personId;
		case 3:
			return track;
		case 4:
			return leaveDate;
		case 5:
			return backDate;
		case 6:
			return cause;
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
			personId=(java.lang.String) obj;
			break;
		case 3:
			track=(java.lang.String) obj;
			break;
		case 4:
			leaveDate=(java.lang.String) obj;
			break;
		case 5:
			backDate=(java.lang.String) obj;
			break;
		case 6:
			cause=(java.lang.String) obj;
			break;
		}
	}

	@Override
	public void getPropertyInfo(int index, PropertyInfo info) {
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
			info.name = "personId";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 3:
			info.name = "track";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 4:
			info.name = "leaveDate";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 5:
			info.name = "backDate";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 6:
			info.name = "cause";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		}
	}
}
