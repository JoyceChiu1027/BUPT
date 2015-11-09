package com.bupt.electricpower.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.bupt.electricpower.R;
import com.bupt.electricpower.model.CommonBaseInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.DateTimePickDialogUtil;
import com.bupt.electricpower.util.ToastUtil;
import com.bupt.electricpower.util.WebServiceUtil;

public class AddTraceInfoActivity extends AbstractBaseActivity implements OnClickListener{
	private EditText causeEt;
	private TextView backDateTv;
	private TextView leaveDateTv;
	private Spinner trackStateSelect;
	private Button submitBtn;
	private String trackState = "在厂";
	private String leaveDate = "";
	private String backDate = "";
	private String cause = "";
    
	Date nowDate;
	Date selectDate;
	SimpleDateFormat df;
	private CommonBaseInfo response;
	
	private Context mContext;
	private static final String TAG = "AddTraceInfoActivity";

	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = AddTraceInfoActivity.this;
		 df = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
		 //df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
		initView();
		initListener();
		//leaveDateTv.setText(df.format(new Date()));

	}

	private void initView() {
		causeEt = (EditText) findViewById(R.id.cause_et);
		backDateTv = (TextView) findViewById(R.id.back_date);
		leaveDateTv = (TextView) findViewById(R.id.leave_date);
		trackStateSelect = (Spinner) findViewById(R.id.track_state_spinner);
		submitBtn = (Button) findViewById(R.id.submit_btn);
	}

	private void initListener() {
		backDateTv.setOnClickListener(this);
		leaveDateTv.setOnClickListener(this);
		submitBtn.setOnClickListener(this);
		trackStateSelect
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
						trackState = trackStateSelect.getSelectedItem()
								.toString().trim();
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});

	}

	@Override
	protected boolean isNeedInitBack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.edit_trace_info_activity);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		
		//int year = mycalendar.get(Calendar.YEAR);
		//int month = mycalendar.get(Calendar.MONTH) + 1;
		//int day = mycalendar.get(Calendar.DAY_OF_MONTH);
		//String  initEndDateTime=df.format(mydate);
		if (R.id.leave_date == id || R.id.back_date == id) {
			showDatePicker(v);
		} else if (R.id.submit_btn == id) {
			submit();
		}

	}

	private void submit() {
		Log.d(TAG, "submit is called");
		leaveDate = leaveDateTv.getText().toString().trim();
		backDate = backDateTv.getText().toString().trim();
		cause=causeEt.getText().toString().trim();
		Log.d(TAG, "leaveDate="+leaveDate);
		Log.d(TAG, "backDate="+backDate);
		if (leaveDate.equals("") || leaveDate.equals("null")) {
			ToastUtil.show(mContext, "请选择离厂时间！");
			return;
		}
		if (backDate.equals("") || backDate.equals("null")) {
			ToastUtil.show(mContext, "请选择返回时间！");
			return;
		}
		if (cause.equals("") || cause.equals("null")) {
			ToastUtil.show(mContext, "事由不能为空");
			return;
		}
		if (checkSubmit()) {
			runHandler();
		} else {
			ToastUtil.show(mContext, "返回时间必须的大于等于离开的时间");
		}

	}

	private boolean checkSubmit() {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(df.parse(leaveDate));
			Long time1 = calendar.getTimeInMillis();
			calendar.setTime(df.parse(backDate));
			Long time2 = calendar.getTimeInMillis();
			Log.d(TAG, "time1=" + time1);
			Log.d(TAG, "time2=" + time2);
			Long betweenDays = (time2 - time1) / (1000 * 60 * 60 * 24);
			int time = Integer.parseInt(String.valueOf(betweenDays));
			Log.d(TAG, "leaveDate=" + leaveDate);
			Log.d(TAG, "backDate=" + backDate);
			Log.d(TAG, "betweenDays=" + betweenDays);
			
			Log.d(TAG, "betweenDays=" + betweenDays);
			Log.d(TAG, "time=" + time);
			if (time >= 0) {
				return true;
			} else {
				return false;
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	private void showDatePicker(View v) {
		
		//Calendar mycalendar = Calendar.getInstance(Locale.CHINA);
		Date mydate = new Date();
		//mycalendar.setTime(mydate);
		//int year = mycalendar.get(Calendar.YEAR);
		//int month = mycalendar.get(Calendar.MONTH) + 1;
		//int day = mycalendar.get(Calendar.DAY_OF_MONTH);
		//int hour=mycalendar.get(Calendar.HOUR_OF_DAY);
		//int minute=mycalendar.get(Calendar.MINUTE);
		String dateStr = df.format(mydate);
		Log.d(TAG, "dateStr="+dateStr);
		//Log.d(TAG, "hour="+hour);
		DateTimePickDialogUtil dateTimePicKDialog=null ;
		//String initStartDateTime = "2013-9-3 14:44";
		//String initEndDateTime = "2014-8-23 17:44";
		if (R.id.leave_date == v.getId()) {
			dateTimePicKDialog= new DateTimePickDialogUtil(  
	                AddTraceInfoActivity.this, mydate);  
	        dateTimePicKDialog.dateTimePicKDialog(leaveDateTv); 
			
		} else if (R.id.back_date == v.getId()) {
			dateTimePicKDialog= new DateTimePickDialogUtil(  
	                AddTraceInfoActivity.this, mydate);  
	        dateTimePicKDialog.dateTimePicKDialog(backDateTv); 
		}
		/*DatePickerDialog dpd;
		dpd = new DatePickerDialog(mContext, dateListener1, year,
				month - 1, day);
		dpd.show();*/

	}

	private DatePickerDialog.OnDateSetListener dateListener1 = new DatePickerDialog.OnDateSetListener() {
		/**
		 * params：view：该事件关联的组件 params：myyear：当前选择的年 params：monthOfYear：当前选择的月
		 * params：dayOfMonth：当前选择的日
		 */

		@Override
		public void onDateSet(DatePicker view, int myyear, int monthOfYear,
				int dayOfMonth) {
			Log.d(TAG, "myyear=" + myyear);
			Log.d(TAG, "monthOfYear=" + monthOfYear);
			Log.d(TAG, "dayOfMonth=" + dayOfMonth);
			try {
				int month = monthOfYear + 1;
				// year = myyear;
				nowDate = df.parse(df.format(new Date()));
				selectDate = df.parse(myyear + "-" + month + "-" + dayOfMonth);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(nowDate);
				Long time1 = calendar.getTimeInMillis();
				calendar.setTime(selectDate);
				Long time2 = calendar.getTimeInMillis();
				Long betweenDays = (time2 - time1) / (1000 * 60 * 60 * 24);
				int time = Integer.parseInt(String.valueOf(betweenDays));
				Log.d(TAG, "nowDate=" + df.format(nowDate));
				Log.d(TAG, "selectDate=" + df.format(selectDate));
				Log.d(TAG, "betweenDays=" + betweenDays);
				Log.d(TAG, "time1=" + time1);
				Log.d(TAG, "time2=" + time2);
				Log.d(TAG, "betweenDays=" + betweenDays);
				Log.d(TAG, "time=" + time);

				updateDate(df.format(selectDate));

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		private void updateDate(String date) {
			leaveDateTv.setText(date);
		}

	};
	private DatePickerDialog.OnDateSetListener dateListener2 = new DatePickerDialog.OnDateSetListener() {
		/**
		 * params：view：该事件关联的组件 params：myyear：当前选择的年 params：monthOfYear：当前选择的月
		 * params：dayOfMonth：当前选择的日
		 */

		@Override
		public void onDateSet(DatePicker view, int myyear, int monthOfYear,
				int dayOfMonth) {
			Log.d(TAG, "myyear=" + myyear);
			Log.d(TAG, "monthOfYear=" + monthOfYear);
			Log.d(TAG, "dayOfMonth=" + dayOfMonth);
			try {
				int month = monthOfYear + 1;
				// year = myyear;
				nowDate = df.parse(df.format(new Date()));
				selectDate = df.parse(myyear + "-" + month + "-" + dayOfMonth);
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(nowDate);
				Long time1 = calendar.getTimeInMillis();
				calendar.setTime(selectDate);
				Long time2 = calendar.getTimeInMillis();
				Long betweenDays = (time2 - time1) / (1000 * 60 * 60 * 24);
				int time = Integer.parseInt(String.valueOf(betweenDays));
				Log.d(TAG, "nowDate=" + df.format(nowDate));
				Log.d(TAG, "selectDate=" + df.format(selectDate));
				Log.d(TAG, "betweenDays=" + betweenDays);
				Log.d(TAG, "time1=" + time1);
				Log.d(TAG, "time2=" + time2);
				Log.d(TAG, "betweenDays=" + betweenDays);
				Log.d(TAG, "time=" + time);

				updateDate(df.format(selectDate));

			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		private void updateDate(String date) {
			backDateTv.setText(date);
		}

	};
	protected void doForeGround() throws Exception {
		if (response!=null) {
			if (response.getReturnCode().equals(CodeConstants.RETURN_SUCCESS)) {
				showDialog("成功", 1);
			}else{
				showDialog(response.getReturnMsg(), 1);
				//ToastUtil.show(mContext, response.getReturnMsg());
			}
		}else{
			ToastUtil.show(mContext, "网络连接异常！");
		}
	}

	protected void doBackGround() throws Exception {
		
		String userName=AppUtil.getFromPref(CodeConstants.USER_NAME, mContext);
		String userPwd=AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		response=WebServiceUtil.SubmitTrackInfo(userName, userPwd, userName, trackState, leaveDate, backDate, cause);
	}
	private void showDialog(String message ,int type){
		new AlertDialog.Builder(mContext).setTitle("提示").setMessage(message).setNegativeButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		}).setPositiveButton("退出", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();
				AddTraceInfoActivity.this.finish();
			}
		}).show();
	}

}
