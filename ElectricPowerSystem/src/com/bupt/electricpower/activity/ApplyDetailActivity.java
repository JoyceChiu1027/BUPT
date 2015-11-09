package com.bupt.electricpower.activity;

import java.security.PublicKey;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.model.CommonBaseInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.WebServiceUtil;

public class ApplyDetailActivity extends AbstractBaseActivity implements
		OnClickListener {
	private TextView busiTypeTv;
	private TextView requestUserTv;
	private TextView requestDateTv;
	private TextView requestDescTv;
	private TextView keyInfoTv;
	private TextView cbmAccountTv;
	private EditText tempEt;
	private Button agreeBtn;
	private Button refuseBtn;
	private Button backBtn;
	private Context mContext;
	private String busiType;
	private String requestUser;
	private String requestDate;
	private String requestDesc;
	private String keyInfo;
	private String cbmAccount;
	private String isReturnShow;
	private String flag;
	private String jobLineNo;
	private String jobTipsId;
	private String desc;
	private CommonBaseInfo response;
	private static final String APPROVE = "APPROVE";
	private static final String REJECT = "REJECT";
	private static final String RETURN = "RETURN";
	private static final String TAG = "ApplyDetailActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = ApplyDetailActivity.this;
		busiType = getIntent().getStringExtra(CodeConstants.BUSI_TYPE);
		requestUser = getIntent().getStringExtra(CodeConstants.REQUEST_USER);
		requestDate = getIntent().getStringExtra(CodeConstants.REQUEST_DATE);
		requestDesc = getIntent().getStringExtra(CodeConstants.REQUEST_DESC);
		keyInfo = getIntent().getStringExtra(CodeConstants.KEY_INFO);
		cbmAccount = getIntent().getStringExtra(CodeConstants.CBM_ACCOUNT);
		isReturnShow = getIntent().getStringExtra(CodeConstants.IS_RETURN_SHOW);
		jobLineNo = getIntent().getStringExtra(CodeConstants.JOB_LINE_NO);
		jobTipsId = getIntent().getStringExtra(CodeConstants.JOB_TIPS_ID);
		Log.d(TAG, "requestUser=" + requestUser);
		Log.d(TAG, "keyInfo=" + keyInfo);
		initView();
		initListener();
		busiTypeTv.setText(busiType);
		requestDateTv.setText(requestDate);
		requestUserTv.setText(requestUser);
		requestDescTv.setText(requestDesc);
		keyInfoTv.setText(keyInfo);
		cbmAccountTv.setText(cbmAccount);

		if (isReturnShow.equals("true") || isReturnShow.equals("TRUE")) {
			backBtn.setVisibility(View.VISIBLE);
		} /*
		 * else if (isReturnShow.equals("false")) {
		 * backBtn.setVisibility(View.GONE); }
		 */else {
			backBtn.setVisibility(View.GONE);
		}
		/* new android.util.LruCache<K, V>(maxSize) */
	}

	private void initView() {
		busiTypeTv = (TextView) findViewById(R.id.busi_type_tv);
		requestDateTv = (TextView) findViewById(R.id.request_date_tv);
		requestUserTv = (TextView) findViewById(R.id.request_user_tv);
		requestDescTv = (TextView) findViewById(R.id.request_desc_tv);
		keyInfoTv = (TextView) findViewById(R.id.key_info_tv);
		cbmAccountTv = (TextView) findViewById(R.id.cbm_account_tv);
		tempEt = (EditText) findViewById(R.id.temp_et);
		agreeBtn = (Button) findViewById(R.id.bt_agree);
		refuseBtn = (Button) findViewById(R.id.bt_refuse);
		backBtn = (Button) findViewById(R.id.bt_back);

	}

	private void initListener() {
		agreeBtn.setOnClickListener(this);
		refuseBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
	}

	@Override
	protected boolean isNeedInitBack() {
		return true;
	}

	@Override
	protected void setLayout() {
		setContentView(R.layout.apply_detail_activity);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		if (R.id.bt_agree == id) {
			flag = APPROVE;
		} else if (R.id.bt_refuse == id) {
			flag = REJECT;
		} else if (R.id.bt_back == id) {
			flag = RETURN;
		}
		// runHandler();
		String username = AppUtil
				.getFromPref(CodeConstants.USER_NAME, mContext);
		String userpwd = AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		desc = tempEt.getText().toString().trim();
		// pd=new ProgressDialog(mContext);
		MyAsyncTask myTask = new MyAsyncTask(mContext);
		myTask.execute(username, userpwd, flag, jobLineNo, jobTipsId, desc);
	}

	@Override
	protected void doForeGround() throws Exception {
		if (response != null) {
			if (response.getReturnCode().equals(CodeConstants.RETURN_SUCCESS)) {
				showAlertDialog("提交成功！", 1);
			} else {
				showAlertDialog(response.getReturnMsg(), 2);
			}
		} else {
			showAlertDialog("网络连接异常！", 2);
		}
	}

	@Override
	protected void doBackGround() throws Exception {
		String username = AppUtil
				.getFromPref(CodeConstants.USER_NAME, mContext);
		String userpwd = AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		desc = tempEt.getText().toString().trim();
		Log.d(TAG, "flag=" + flag);
		response = WebServiceUtil.subMitJobTips(username, userpwd, flag,
				jobLineNo, jobTipsId, desc);
	}

	private void showAlertDialog(String message, final int flag) {
		new AlertDialog.Builder(mContext).setTitle("提示").setMessage(message)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (flag == 1) {
							dialog.dismiss();
							ApplyDetailActivity.this.finish();
						} else {
							dialog.dismiss();
						}
					}
				}).show();
	}

	class MyAsyncTask extends AsyncTask<String, Integer, CommonBaseInfo> {
		private ProgressDialog pd;
		private Context context;

		MyAsyncTask(Context context) {
			this.context = context;
			pd = new ProgressDialog(this.context);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			showProgressDialog();
		}

		@Override
		protected CommonBaseInfo doInBackground(String... params) {
			// TODO Auto-generated method stub
			/*
			 * int progress=1; while(progress<101){ SystemClock.sleep(1000);
			 * publishProgress(progress); progress++; }
			 */
			try {
				response = WebServiceUtil.subMitJobTips(params[0], params[1],
						params[2], params[3], params[4], params[5]);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
			pd.cancel();
			pd.dismiss();
		}

		@Override
		protected void onPostExecute(CommonBaseInfo result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
			if (result != null) {
				if (result.getReturnCode().equals(CodeConstants.RETURN_SUCCESS)) {
					showAlertDialog(result.getReturnMsg(), 1);
				} else {
					showAlertDialog(result.getReturnMsg(), 2);
				}
			} else {
				showAlertDialog("网络连接异常", 2);
			}

		}

		private void showProgressDialog() {
			pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			pd.setMessage("网络连接中，请稍后！");
			pd.show();

		}

	}
}
