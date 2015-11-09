package com.bupt.electricpower.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.model.ResponseTracesInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.ToastUtil;
import com.bupt.electricpower.util.WebServiceUtil;

public class TracesManagementActivity extends AbstractBaseActivity implements
		OnClickListener {
	private TextView trackStateTv;
	private TextView leaveDateTv;
	private TextView backDateTv;
	private TextView causeTv;
	private Button queryBtn;
	private Button editBtn;
	private Button addBtn;

	private ResponseTracesInfo response;

	private Context mContext;
	private static final String TAG = "TracesManagementActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = TracesManagementActivity.this;
		initView();
		initListener();
		runHandler();
	}

	private void initView() {
		trackStateTv=(TextView) findViewById(R.id.track_state);
		leaveDateTv=(TextView) findViewById(R.id.leave_date);
		backDateTv=(TextView) findViewById(R.id.back_date);
		causeTv=(TextView) findViewById(R.id.cause);
		queryBtn = (Button) findViewById(R.id.query_btn);
		editBtn = (Button) findViewById(R.id.edit_btn);
		addBtn = (Button) findViewById(R.id.add_btn);
		// iv=(ImageView) findViewById(R.id.show_image);
	}

	private void initListener() {
		queryBtn.setOnClickListener(this);
		editBtn.setOnClickListener(this);
		addBtn.setOnClickListener(this);
	}

	@Override
	protected boolean isNeedInitBack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.traces_management_activity);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent intent = new Intent();
		if (R.id.query_btn == id) {
			intent.setClass(mContext, QueryTracesListActivity.class);
			startActivity(intent);
		} else if (R.id.edit_btn == id) {
			intent.setClass(mContext, EditTraceInfoActivity.class);
			intent.putExtra(CodeConstants.TRACK, response.getTrack());
			intent.putExtra(CodeConstants.PERSON_ID,response.getPersonId());
			intent.putExtra(CodeConstants.LEAVE_DATE, response.getLeaveDate());
			intent.putExtra(CodeConstants.BACK_DATE, response.getBackDate());
			intent.putExtra(CodeConstants.CAUSE, response.getCause());
			startActivityForResult(intent, 1);
		} else if (R.id.add_btn == id) {
			intent.setClass(mContext, AddTraceInfoActivity.class);
			startActivity(intent);
		}
		
	}

	@Override
	protected void doForeGround() throws Exception {
		if (response!=null) {
			
			if (response.getTrack()!=null&&!response.getTrack().equals("null")) {
				trackStateTv.setText(response.getTrack());
			}else{
				trackStateTv.setText("");
			}
			if (response.getLeaveDate()!=null&&!response.getLeaveDate().equals("null")) {
				leaveDateTv.setText(response.getLeaveDate());
			}else{
				leaveDateTv.setText("");
			}
			if (response.getBackDate()!=null&&!response.getBackDate().equals("null")) {
				backDateTv.setText(response.getBackDate());
			}else{
				backDateTv.setText("");
			}
			if (response.getCause()!=null&&!response.getCause().equals("null")) {
				causeTv.setText(response.getCause());
			}else{
				causeTv.setText("");
			}
			if (response.getPersonId().equals("")||response.getPersonId().equals("null")) {
				/*editBtn.setVisibility(View.GONE);*/
				return;
			}
		}else{
			ToastUtil.show(mContext, "网络连接异常");
			/*editBtn.setVisibility(View.GONE);*/
		}
	}

	@Override
	protected void doBackGround() throws Exception {
		String custlName = AppUtil.getFromPref(CodeConstants.USER_NAME,
				mContext);
		String custlpwd = AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		response = WebServiceUtil.getTracesInfo(custlName, custlpwd);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode==1) {
			if (resultCode==RESULT_OK) {
				trackStateTv.setText(data.getStringExtra(CodeConstants.TRACK));
				leaveDateTv.setText(data.getStringExtra(CodeConstants.LEAVE_DATE));
				backDateTv.setText(data.getStringExtra(CodeConstants.BACK_DATE));
				causeTv.setText(data.getStringExtra(CodeConstants.CAUSE));
			}else if (resultCode==RESULT_CANCELED) {
				//do nothing
			}else if (resultCode==RESULT_FIRST_USER) {
				//do nothing
			}
		}
		
	}
	
}
