package com.bupt.electricpower.activity;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.bupt.electricpower.R;
import com.bupt.electricpower.model.CommonBaseInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.ToastUtil;
import com.bupt.electricpower.util.WebServiceUtil;

public class LoginActivity extends AbstractBaseActivity {
	private String custlName;
	private String custlPwd;
	private EditText nameEt;
	private EditText pwdEt;
	private Context mContext;
	private CommonBaseInfo response;
	
	/*private static Handler handler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			
		};
	};*/
   //private Handler mHandler=new ha
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = LoginActivity.this;
		nameEt = (EditText) findViewById(R.id.ed_name);
		pwdEt = (EditText) findViewById(R.id.ed_password);
		Button loginBtn = (Button) findViewById(R.id.loginButton);
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				custlName = nameEt.getText().toString().trim();
				custlPwd = pwdEt.getText().toString().trim();
				if (custlName.equals("") || custlName == null) {
					ToastUtil.show(mContext, "用户名不能为空！");
					return;
				}
				if (custlPwd.equals("") || custlPwd == null) {
					ToastUtil.show(mContext, "密码不能为空！");
					return;
				}
				runHandler();

				/*Intent intent = new Intent();
				intent.setClass(mContext, ApplyListActivity.class);
				startActivity(intent);*/
				/*String[] params={custlName,custlPwd};
				LoginTask loginTask=new LoginTask();
				loginTask.execute(params);
				loginTask.executeOnExecutor(Executors.newFixedThreadPool(10), custlName,custlPwd);*/
			}
		});
		Button cancleBtn = (Button) findViewById(R.id.cancelButton);
		cancleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		
	}

	@Override
	protected boolean isNeedInitBack() {
		return false;
	}

	@Override
	protected void setLayout() {
		setContentView(R.layout.login);
	}

	@Override
	protected void doForeGround() throws Exception {
		if (response != null) {
			if (response.getReturnCode().equals(CodeConstants.RETURN_SUCCESS)) {
				Editor editor=AppUtil.getDefaultPreferences(mContext).edit();
				editor.putString(CodeConstants.USER_NAME,custlName);
				editor.putString(CodeConstants.PASSWORD, custlPwd);
				editor.commit();			
				Intent intent = new Intent();
				intent.setClass(mContext, MainActivity.class);
				startActivity(intent);
				finish();
			}else{
				showDialog(response.getReturnMsg());
			}
		}else{
			showDialog("网络连接异常！");
		}
	}

	@Override
	protected void doBackGround() throws Exception {
		response = WebServiceUtil.getLoginInfo(custlName, custlPwd);
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
	class LoginTask extends AsyncTask<String, Integer, CommonBaseInfo>{
		private ProgressDialog pd;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			Log.d("LoginTask", "onPreExecute is called");
			super.onPreExecute();
			//showProgressDialog();
		}

		@Override
		protected CommonBaseInfo doInBackground(String... params) {
			// TODO Auto-generated method stub
			Log.d("LoginTask", "doInBackground is called");
			try {
				CommonBaseInfo r=WebServiceUtil.getLoginInfo(params[0], params[1]);
				return r;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return null;
			
		}

     

		@Override
		protected void onPostExecute(CommonBaseInfo result) {
			// TODO Auto-generated method stub
			Log.d("LoginTask", "onPostExecute is called");
			pd.dismiss();
			super.onPostExecute(result);
			if(result!=null){
				showDialog(result.getReturnMsg());
			}else{
				showDialog("网络连接异常！！！");
			}
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
		}
		
		@Override
		protected void onCancelled() {
			Log.d("LoginTask", "");
			// TODO Auto-generated method stub
			super.onCancelled();
			pd.dismiss();
		};
	/*	private void showProgressDialog(){
			pd=new ProgressDialog(mContext);
			//pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			pd.setMessage("正在向服务器提交请求");
			pd.setTitle("请稍候");
			pd.show();
			//SeekBar sb=new SeekBar(mContext);
			
		}*/
	}
}
