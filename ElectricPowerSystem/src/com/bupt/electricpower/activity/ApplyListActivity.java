package com.bupt.electricpower.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.adapter.ApplyListAdapter;
import com.bupt.electricpower.model.ResponseJobTipInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.WebServiceUtil;

public class ApplyListActivity extends AbstractBaseActivity{
    private Context mContext;
	private ListView listView;
	private List<ResponseJobTipInfo> returnList;
	private String custlName, custlpwd;
    private static final String TAG="ApplyListActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext=ApplyListActivity.this;
		custlName=AppUtil.getFromPref(CodeConstants.USER_NAME, mContext);
		custlpwd=AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		listView = (ListView) findViewById(R.id.lv_apply);
		//itemNames = initBusList();
		//returnList=initData;
		
		//listView.setAdapter(new ApplyListAdapter(initData(),mContext));
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				
				Intent intent = new Intent();
				intent.putExtra(CodeConstants.BUSI_TYPE, returnList.get(position).getJobTipsDesc());
				intent.putExtra(CodeConstants.REQUEST_USER, returnList.get(position).getRequestUser());
				intent.putExtra(CodeConstants.REQUEST_DATE, returnList.get(position).getRequestDate());
				intent.putExtra(CodeConstants.REQUEST_DESC, returnList.get(position).getRequestDesc());
				intent.putExtra(CodeConstants.KEY_INFO, returnList.get(position).getKeyInfo());
				intent.putExtra(CodeConstants.CBM_ACCOUNT, returnList.get(position).getCbmAccount());
				intent.putExtra(CodeConstants.IS_RETURN_SHOW, returnList.get(position).getReturnMsg());
				intent.putExtra(CodeConstants.JOB_LINE_NO,returnList.get(position).getJobLineNo());
				intent.putExtra(CodeConstants.JOB_TIPS_ID, returnList.get(position).getJobTipsId());
				intent.setClass(mContext, ApplyDetailActivity.class);
				startActivity(intent);
			}
		});
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		runHandler();
	}
	private List<ResponseJobTipInfo> initData(){
		
		returnList=new ArrayList<ResponseJobTipInfo>();

        String[] names={"祁伟","徐建虎","刘丽君","于云海","刘备","马超 ","小红","小明","小兰","小花"};
		String[] types={"车辆申请","休假申请","休假申请","出差申请","出差申请","车辆申请","休假申请","休假申请","车辆审批","出差申请"};
		String[] times={"2015-09-01","2015-09-01","2015-09-07","2015-09-02","2015-09-06","2015-09-14","2015-09-15","2015-09-16","2015-09-17","2015-09-18"};
		for (int i = 0; i < names.length; i++) {
			ResponseJobTipInfo response=new ResponseJobTipInfo();
			response.setRequestUser(names[i]);
			response.setRequestDate(times[i]);
			response.setJobTipsDesc(types[i]);
			returnList.add(response);
		}
		return returnList;
	}
	
	@Override
	protected boolean isNeedInitBack() {
		return true;
	}
	@Override
	protected void setLayout() {
		setContentView(R.layout.apply_list);
	}
	@Override
	protected void doForeGround() throws Exception {
		if(returnList!=null){
			Log.d(TAG, "returnList.size()="+returnList.size());
			if(returnList.size()>0){
				listView.setAdapter(new ApplyListAdapter(returnList, mContext));
			}else{
				showDialog("无数据！");
			}
		}else{
			showDialog("网络连接异常！");
		}
	}
	@Override
	protected void doBackGround() throws Exception {
		returnList=WebServiceUtil.getJobTipsInfo(custlName, custlpwd);
	}
	private void showDialog(String message){
		new AlertDialog.Builder(mContext).setTitle("提示").setMessage(message).setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).show();
	}
}
