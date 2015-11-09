package com.bupt.electricpower.util;

public class CodeConstants {

	public static final String NAMESPACE_IN_GENERATED_REQUEST = "http://online.ws.uboss.bupt.com";
	public static final String NAMESPACE_IN_ARRAY_REQUEST = "http://bossVo.ossonline.ws.uboss.bupt.com";
//  public static final String URL_PREFIX = "http://buptguoan.gicp.net:7000";
//	public static final String URL_PREFIX="http://210.77.96.169:22000";
//	public static final String URL_PREFIX = "http://210.77.126.52:21580";
//	public static final String URL_PREFIX = "http://192.168.10.242:7000/ubossPhone/services/External2OssOnlineService";
//	public static final String URL_PREFIX = "http://192.168.10.242:7000";
	//public static final String URL_PREFIX="http://10.212.182.59:8080";
	//public static final String URL_PREFIX="http://10.212.182.59:8080";
	public static final String URL_PREFIX="http://192.168.8.175:8080";
	public static final String WSDL_URL = URL_PREFIX + "/ERPService/services/External2OssOnlineService";
	public static final String PIC_URL=URL_PREFIX+"/ERPService";

	// private static final String URL =
	// "http://online.wjcatv.com/online4ad/services/External2OssOnlineService";
	// private static final String URL =
	// "http://10.242.0.2:8080/online4ad/services/External2OssOnlineService";
	// private static final String URL =
	// "http://192.168.20.198:8080/online4ad/services/External2OssOnlineService";
	public static final String SOAP_ACTION = "";
	public static final boolean WEBSERVICE_CALL_PRINT = true;
	
	public static final String SHARED_PREFERENCE_NAME = "userInfo";
	public static final String CUST_ID = "custId";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String BACK_DATE="backDate";
	public static final String LEAVE_DATE="leaveDate";
	public static final String CAUSE="cause";
	public static final String PERSON_ID="personId";
	public static final String TRACK="track";
	public static final String BUSI_TYPE="busiType";
	public static final String REQUEST_USER="requestUser";
	public static final String REQUEST_DATE="requestDate";
	public static final String REQUEST_DESC="requestDesc";
	public static final String CBM_ACCOUNT="cmbAccount";
	public static final String KEY_INFO="keyInfo";
	public static final String IS_RETURN_SHOW="isReturnShow";
	public static final String JOB_LINE_NO="jobLineNo";
	public static final String JOB_TIPS_ID="jobTipsId";
	public static final String SERVICE_NAME = "ndtvService";
	public static final String PHONENO = "phoneNo";
	public static final String APK_VERSION = "apkVersion";
	public static final String APK_VERSION_CODE="apkVersionCode";
	public static final String APK_URL = "apkUrl";
	public static final long FRONTPAGE_SHOW_TIME = 1000*8;
	public static final long FRONTPAGE_INTERVAL = 1000*20;
	public static final long ROLL_PIC_INTERVAL = 1000*20;
	public static final long NOTI_INTERVAL = 1000*60*5;
	public static final long NEWAPK_INTERVAL = 1000*20;
	public static final int ROLL_PIC_WIDTH = 600;
	public static final int ROLL_PIC_HEIGHT = 420;
	public static final int BUTTON_COLOR_COVER = 100;
	public static final String RETURN_SUCCESS = "0";
}
