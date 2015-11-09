package com.bupt.electricpower.util;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;

import com.bupt.electricpower.model.CommonBaseInfo;
import com.bupt.electricpower.model.ResponseCustLoginInfo;
import com.bupt.electricpower.model.ResponseElecGeneration;
import com.bupt.electricpower.model.ResponseInCoalCount;
import com.bupt.electricpower.model.ResponseJobTipInfo;
import com.bupt.electricpower.model.ResponseLoadInfo;
import com.bupt.electricpower.model.ResponseTracesInfo;
import com.bupt.electricpower.online.RequestCustLoginInfo;
import com.bupt.electricpower.online.RequestElecInfo;
import com.bupt.electricpower.online.RequestSubMitJobTips;
import com.bupt.electricpower.online.RequestTracesInfo;

public class WebServiceUtil {
	private static final String TAG = "WebServiceUtil";

	// 登录
	public static CommonBaseInfo getLoginInfo(String custlName, String custlPwd)
			throws Exception {
		Log.d(TAG, "getLoginInfo is called");
		CommonBaseInfo response = new CommonBaseInfo();
		RequestCustLoginInfo request = new RequestCustLoginInfo();
		request.setCustlName(custlName);
		request.setCustlpwd(custlPwd);
		SoapObject detail = doCommon("getLoginInfo", "getLoginInfoRequest",
				"RequestCustLoginInfo", request, RequestCustLoginInfo.class);
		response.setReturnCode(detail.getProperty("returnCode") + "");
		response.setReturnMsg(detail.getProperty("returnMsg") + "");
		return response;
	}

	public static List<ResponseJobTipInfo> getJobTipsInfo(String custlName,
			String custlpwd) throws Exception {
		List<ResponseJobTipInfo> returnList = new ArrayList<ResponseJobTipInfo>();
		RequestCustLoginInfo request = new RequestCustLoginInfo();
		request.setCustlName(custlName);
		request.setCustlpwd(custlpwd);
		SoapObject detail = doCommon("getJobTipsInfo", "getJobTipsInfoRequest",
				"ResponseJobTipInfo", request, ResponseJobTipInfo.class);
		int count = detail.getPropertyCount();
		for (int i = 0; i < count; i++) {
			SoapObject pi = (SoapObject) detail.getProperty(i);
			ResponseJobTipInfo response = new ResponseJobTipInfo();
			response.setJobLineNo(pi.getProperty("jobLineNo") + "");
			response.setJobTipsId(pi.getProperty("jobTipsId") + "");
			response.setJobTipsDesc(pi.getProperty("jobTipsDesc") + "");
			response.setJobTipsUser(pi.getProperty("jobTipsUser") + "");
			response.setRequestDate(pi.getProperty("requestDate") + "");
			response.setRequestDesc(pi.getProperty("requestDesc") + "");
			response.setRequestUser(pi.getProperty("requestUser") + "");
			response.setKeyInfo(pi.getProperty("keyInfo") + "");
			response.setCbmAccount(pi.getProperty("cbmAccount") + "");
			response.setReturnMsg(pi.getProperty("returnMsg") + "");
			response.setTemp(pi.getProperty("temp") + "");
			returnList.add(response);

		}
		return returnList;
	}

	public static CommonBaseInfo subMitJobTips(String username, String userpwd,
			String flag, String jobLineNo, String jobTipsId, String desc)
			throws Exception {
		CommonBaseInfo response = new CommonBaseInfo();
		RequestSubMitJobTips request = new RequestSubMitJobTips();
		request.setDesc(desc);
		request.setFlag(flag);
		request.setJobLineNo(jobLineNo);
		request.setJobTipsId(jobTipsId);
		request.setUserName(username);
		request.setUserPwd(userpwd);
		SoapObject detail = doCommon("subMitJobTips", "subMitJobTipsRequest",
				"RequestSubMitJobTips", request, RequestSubMitJobTips.class);
		response.setReturnCode(detail.getProperty("returnCode") + "");
		response.setReturnMsg(detail.getProperty("returnMsg") + "");
		return response;
	}

	public static ResponseLoadInfo getLoadInfo(String userName, String userPwd)
			throws Exception {
		ResponseLoadInfo response = new ResponseLoadInfo();
		RequestElecInfo request = new RequestElecInfo();
		request.setUserName(userName);
		request.setUserPwd(userPwd);
		// SoapObject request=new
		// SoapObject(CodeConstants.NAMESPACE_IN_GENERATED_REQUEST,
		// "getLoadInfo");
		// SoapObject detail=doCommonBase(request);
		SoapObject detail = doCommon("getLoadInfo", "getLoadInfoRequest",
				"RequestElecInfo", request, RequestElecInfo.class);
		response.setBm(detail.getProperty("bm") + "");
		response.setCol01(detail.getProperty("col01") + "");
		response.setCol03(detail.getProperty("col03") + "");
		response.setCol04(detail.getProperty("col04") + "");
		response.setLoad(detail.getProperty("load") + "");
		response.setNt01(detail.getProperty("nt01") + "");
		response.setNt02(detail.getProperty("nt02") + "");
		response.setNt03(detail.getProperty("nt03") + "");
		response.setNt04(detail.getProperty("nt04") + "");
		response.setNt05(detail.getProperty("nt05") + "");
		response.setNt06(detail.getProperty("nt06") + "");
		response.setNt07(detail.getProperty("nt07") + "");
		response.setNt08(detail.getProperty("nt08") + "");
		response.setNt09(detail.getProperty("nt09") + "");
		response.setNt10(detail.getProperty("nt10") + "");
		response.setNt11(detail.getProperty("nt11") + "");
		response.setNt12(detail.getProperty("nt12") + "");
		response.setNt13(detail.getProperty("nt13") + "");
		response.setNt14(detail.getProperty("nt14") + "");
		response.setNt15(detail.getProperty("nt15") + "");
		response.setNt16(detail.getProperty("nt16") + "");
		response.setNt17(detail.getProperty("nt17") + "");
		response.setNt18(detail.getProperty("nt18") + "");
		response.setNt19(detail.getProperty("nt19") + "");
		response.setNt20(detail.getProperty("nt20") + "");
		response.setNt21(detail.getProperty("nt21") + "");
		response.setNt22(detail.getProperty("nt22") + "");
		response.setNt23(detail.getProperty("nt23") + "");
		response.setNt24(detail.getProperty("nt24") + "");
		response.setRquestDate(detail.getProperty("rquestDate") + "");
		response.setReturnCode(detail.getProperty("returnCode") + "");
		response.setReturnMsg(detail.getProperty("returnMsg") + "");
		return response;
	}

	public static List<ResponseElecGeneration> getElecgeneration(
			String userName, String userPwd, String requestDate, String unit)
			throws Exception {
		List<ResponseElecGeneration> returnList = new ArrayList<ResponseElecGeneration>();
		RequestElecInfo request = new RequestElecInfo();
		request.setUserName(userName);
		request.setUserPwd(userPwd);
		request.setRequestDate(requestDate);
		request.setUnit(unit);
		SoapObject detail = doCommon("getElecgeneration",
				"getElecgenerationRequest", "RequestElecInfo", request,
				RequestElecInfo.class);
		int count = detail.getPropertyCount();
		Log.d(TAG, "getElecgeneration/count=" + count);
		for (int i = 0; i < count; i++) {
			SoapObject pii = (SoapObject) detail.getProperty(i);
			ResponseElecGeneration response = new ResponseElecGeneration();
			response.setRequestDate(pii.getProperty("requestDate") + "");
			response.setZbmc(pii.getProperty("zbmc") + "");
			response.setZbdw(pii.getProperty("zbdw") + "");
			response.setZb21(pii.getProperty("zb21") + "");
			response.setZb22(pii.getProperty("zb22") + "");
			response.setZb31(pii.getProperty("zb31") + "");
			response.setZb32(pii.getProperty("zb32") + "");
			response.setZb51(pii.getProperty("zb51") + "");
			response.setZb52(pii.getProperty("zb52") + "");
			response.setZb61(pii.getProperty("zb61") + "");
			response.setZb62(pii.getProperty("zb62") + "");
			response.setZb71(pii.getProperty("zb71") + "");
			response.setZb72(pii.getProperty("zb72") + "");
			response.setZb81(pii.getProperty("zb81") + "");
			response.setZb82(pii.getProperty("zb82") + "");
			response.setZb91(pii.getProperty("zb91") + "");
			response.setZb92(pii.getProperty("zb92") + "");
			returnList.add(response);
		}
		return returnList;
	}

	public static List<ResponseElecGeneration> getConsume(String userName,
			String userPwd, String requestDate, String unit, String checkType)
			throws Exception {
		List<ResponseElecGeneration> returnList = new ArrayList<ResponseElecGeneration>();
		RequestElecInfo request = new RequestElecInfo();
		request.setCheckType(checkType);
		request.setRequestDate(requestDate);
		request.setUnit(unit);
		request.setUserName(userName);
		request.setUserPwd(userPwd);
		SoapObject detail = doCommon("getConsume", "getConsumeRequest",
				"RequestElecInfo", request, RequestElecInfo.class);
		int count = detail.getPropertyCount();
		Log.d(TAG, "getElecgeneration/count=" + count);
		for (int i = 0; i < count; i++) {
			SoapObject pii = (SoapObject) detail.getProperty(i);
			ResponseElecGeneration response = new ResponseElecGeneration();
			response.setRequestDate(pii.getProperty("requestDate") + "");
			response.setZbmc(pii.getProperty("zbmc") + "");
			response.setZbdw(pii.getProperty("zbdw") + "");
			response.setZb21(pii.getProperty("zb21") + "");
			response.setZb22(pii.getProperty("zb22") + "");
			response.setZb31(pii.getProperty("zb31") + "");
			response.setZb32(pii.getProperty("zb32") + "");
			response.setZb51(pii.getProperty("zb51") + "");
			response.setZb52(pii.getProperty("zb52") + "");
			response.setZb61(pii.getProperty("zb61") + "");
			response.setZb62(pii.getProperty("zb62") + "");
			response.setZb71(pii.getProperty("zb71") + "");
			response.setZb72(pii.getProperty("zb72") + "");
			response.setZb81(pii.getProperty("zb81") + "");
			response.setZb82(pii.getProperty("zb82") + "");
			response.setZb91(pii.getProperty("zb91") + "");
			response.setZb92(pii.getProperty("zb92") + "");
			returnList.add(response);
		}
		return returnList;
	}

	public static List<ResponseInCoalCount> getCoalNo(String userName,
			String userPwd, String requestDate, String unit) throws Exception {
		List<ResponseInCoalCount> returnList = new ArrayList<ResponseInCoalCount>();
		RequestElecInfo request = new RequestElecInfo();
		request.setUserName(userName);
		request.setUserPwd(userPwd);
		request.setUnit(unit);
		request.setRequestDate(requestDate);
		SoapObject detail = doCommon("getCoalNo", "getCoalNoRequest",
				"RequestElecInfo", request, RequestElecInfo.class);
		int count = detail.getPropertyCount();
		for (int i = 0; i < count; i++) {
			SoapObject pii = (SoapObject) detail.getProperty(i);
			ResponseInCoalCount response = new ResponseInCoalCount();
			response.setCoalNo(pii.getProperty("coalNo") + "");
			response.setRqDate(pii.getProperty("rqDate") + "");
			returnList.add(response);
		}
		return returnList;
	}

	public static ResponseTracesInfo getTracesInfo(String custlName,
			String custlpwd) throws Exception {
		ResponseTracesInfo response = new ResponseTracesInfo();
		RequestCustLoginInfo request = new RequestCustLoginInfo();
		request.setCustlName(custlName);
		request.setCustlpwd(custlpwd);
		SoapObject detail = doCommon("getTracesInfo", "getTracesInfoRequest",
				"RequestCustLoginInfo", request, RequestCustLoginInfo.class);
		response.setBackDate(detail.getProperty("backDate") + "");
		response.setCause(detail.getProperty("cause") + "");
		response.setLeaveDate(detail.getProperty("leaveDate") + "");
		response.setPersonId(detail.getProperty("personId") + "");
		response.setTrack(detail.getProperty("track") + "");

		return response;
	}

	public static List<ResponseTracesInfo> getHistoryTraces(String custlName,
			String custlpwd) throws Exception {
		RequestCustLoginInfo request = new RequestCustLoginInfo();
		request.setCustlName(custlName);
		request.setCustlpwd(custlpwd);
		SoapObject detail = doCommon("getHistoryTraces",
				"getHistoryTracesRequest", "RequestCustLoginInfo", request,
				RequestCustLoginInfo.class);
		List<ResponseTracesInfo> returnList = new ArrayList<ResponseTracesInfo>();
		int count = detail.getPropertyCount();
		for (int i = 0; i < count; i++) {
			SoapObject pii = (SoapObject) detail.getProperty(i);
			ResponseTracesInfo response = new ResponseTracesInfo();
			response.setBackDate(pii.getProperty("backDate") + "");
			response.setCause(pii.getProperty("cause") + "");
			response.setLeaveDate(pii.getProperty("leaveDate") + "");
			response.setPersonId(pii.getProperty("personId") + "");
			response.setTrack(pii.getProperty("track") + "");
			returnList.add(response);
		}
		return returnList;
	}

	public static CommonBaseInfo SubmitTrackInfo(String userName,
			String userPwd, String personId, String track, String leaveDate,
			String backDate, String cause) throws Exception {
		RequestTracesInfo request = new RequestTracesInfo();
		request.setBackDate(backDate);
		request.setCause(cause);
		request.setLeaveDate(leaveDate);
		request.setPersonId(personId);
		request.setTrack(track);
		request.setUserName(userName);
		request.setUserPwd(userPwd);
		SoapObject detail = doCommon("SubmitTrackInfo",
				"SubmitTrackInfoRequest", "RequestTracesInfo", request,
				RequestTracesInfo.class);
		CommonBaseInfo response = new CommonBaseInfo();
		response.setReturnCode(detail.getProperty("returnCode")+"");
		response.setReturnMsg(detail.getProperty("returnMsg")+"");
		return response;
	}

	private static SoapObject doCommon(String methodName, String requestName1,
			String requestName2, SoapObject request, Class<?> requestClass)
			throws Exception {
		SoapObject rpc = new SoapObject(
				CodeConstants.NAMESPACE_IN_GENERATED_REQUEST, methodName);
		PropertyInfo argument = new PropertyInfo();
		argument.setName(requestName1);
		argument.setValue(request);
		argument.setNamespace(CodeConstants.NAMESPACE_IN_GENERATED_REQUEST);
		argument.setType(requestClass);
		rpc.addProperty(argument);

		ExtendedSoapSerializationEnvelope envelope = newEnvelope(rpc);
		envelope.addMapping(CodeConstants.NAMESPACE_IN_GENERATED_REQUEST,
				requestName2, requestClass);

		return call(envelope);
	}

	private static SoapObject doCommonBase(SoapObject request) throws Exception {
		ExtendedSoapSerializationEnvelope envelope = newEnvelope(request);
		return call(envelope);
	}

	private static ExtendedSoapSerializationEnvelope newEnvelope(
			SoapObject request) {
		ExtendedSoapSerializationEnvelope envelope = new ExtendedSoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.bodyOut = request;
		envelope.setOutputSoapObject(request);
		envelope.dotNet = true;
		MarshalDouble md = new MarshalDouble();
		md.register(envelope);
		return envelope;
	}

	private static SoapObject call(ExtendedSoapSerializationEnvelope envelope)
			throws Exception {
		HttpTransportSE ht = new HttpTransportSE(CodeConstants.WSDL_URL);
		ht.debug = true;
		SoapObject detail = null;
		try {
			ht.call(CodeConstants.SOAP_ACTION, envelope);
			detail = (SoapObject) envelope.getResponse();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("网络或服务器故障,无响应");
		}
		if (CodeConstants.WEBSERVICE_CALL_PRINT) {
			System.out.println(ht.requestDump);
			System.out
					.println("==============================================================================");
			System.out.println(ht.responseDump);
			System.out
					.println("==============================================================================");
		}
		return detail;
	}
}
