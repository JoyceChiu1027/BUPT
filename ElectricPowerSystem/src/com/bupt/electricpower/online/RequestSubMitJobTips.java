package com.bupt.electricpower.online;

import java.util.Hashtable;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import com.bupt.electricpower.util.CodeConstants;

public class RequestSubMitJobTips extends SoapObject{
    private String userName;
    private String userPwd;
    private String flag;
    private String jobLineNo;
    private String jobTipsId;
    private String desc;
	public RequestSubMitJobTips() {
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getJobLineNo() {
		return jobLineNo;
	}
	public void setJobLineNo(String jobLineNo) {
		this.jobLineNo = jobLineNo;
	}
	public String getJobTipsId() {
		return jobTipsId;
	}
	public void setJobTipsId(String jobTipsId) {
		this.jobTipsId = jobTipsId;
	}
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getPropertyCount() {
		return 6;
	}

	public Object getProperty(int index) {
		switch (index) {
		case 0:
			return userName;
		case 1:
			return userPwd;
		case 2:
			return jobLineNo;
		case 3:
			return jobTipsId;
		case 4:
			return desc;
		case 5:
			return flag;
		}
		return null;
	}

	public void setProperty(int index, Object obj) {
		switch (index) {
		case 0:
			userName = (java.lang.String) obj;
			break;
		case 1:
			userPwd = (java.lang.String) obj;
			break;
		case 2:
			jobLineNo=(java.lang.String) obj;
			break;
		case 3:
			jobTipsId=(java.lang.String) obj;
			break;
		case 4:
			desc=(java.lang.String) obj;
			break;
		case 5:
			flag=(java.lang.String) obj;
			break;
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
			info.name = "jobLineNo";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 3:
			info.name = "jobTipsId";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 4:
			info.name = "desc";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		case 5:
			info.name = "flag";
			info.namespace = CodeConstants.NAMESPACE_IN_GENERATED_REQUEST;
			info.type = java.lang.String.class;
			break;
		}
	}
}
