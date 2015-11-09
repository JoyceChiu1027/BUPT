package com.bupt.electricpower.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.util.ActivityManager;

public abstract class AbstractBaseActivity extends Activity {

	private ActivityManager activityManager = null;
	private ProgressDialog progressDialog;
	protected String message;
	protected String title = "操作结果";

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);*/
		activityManager = ActivityManager.getScreenManager();
		activityManager.pushActivity(this);
		setLayout();
		if (getTopicOfTitleBar() != null && !getTopicOfTitleBar().isEmpty()) {
			TextView titleTextView = (TextView) findViewById(R.id.top_title);
			titleTextView.setText(getTopicOfTitleBar());
		}

		if (isNeedInitBack()) {
			ImageButton returnButton = (ImageButton) findViewById(R.id.baseBack);
			returnButton.setVisibility(View.VISIBLE);
			returnButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					AbstractBaseActivity.this.finish();
				}
			});
		}
	}

	abstract protected boolean isNeedInitBack();

	abstract protected void setLayout();

	protected String getTopicOfTitleBar() {
		return null;
	}

	@SuppressLint("HandlerLeak")
	protected void runHandler() {
		progressDialog = new ProgressDialog(AbstractBaseActivity.this);
		progressDialog.setMessage("正在向服务器提交请求");
		progressDialog.setTitle("请稍候");
		progressDialog.show();

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					progressDialog.dismiss();
					try {
						doForeGround();
					} catch (Exception e) {
						handleExceptionOfThread(e);
						e.printStackTrace();
					}
				} else if (msg.what == 1) {
					AlertDialog dlg = new AlertDialog.Builder(
							AbstractBaseActivity.this)
							.setTitle("出错了")
							.setMessage(message)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int 

i) {
											return;
										}
									}).create();
					dlg.show();
				}
			}
		};
		Runnable r = new ConnectThread(handler);
		new Thread(r).start();
	}

	protected void runHandler2() {
		progressDialog = new ProgressDialog(AbstractBaseActivity.this);
		progressDialog.setMessage("正在向服务器提交请求");
		progressDialog.setTitle("请稍候");
		progressDialog.show();

		Handler handler = new Handler() {
			@SuppressLint("HandlerLeak")
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					progressDialog.dismiss();
					try {
						doForeGround2();
					} catch (Exception e) {
						handleExceptionOfThread(e);
					}
				} else if (msg.what == 1) {
					AlertDialog dlg = new AlertDialog.Builder(
							AbstractBaseActivity.this)
							.setTitle("出错了")
							.setMessage(message)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int 

i) {
											return;
										}
									}).create();
					dlg.show();
				}
				try {
					postHandle2();
				} catch (Exception e) {
					handleExceptionOfThread(e);
				}
			}
		};
		Runnable r = new ConnectThread2(handler);
		new Thread(r).start();
	}

	protected void runHandler3() {
		progressDialog = new ProgressDialog(AbstractBaseActivity.this);
		progressDialog.setMessage("正在向服务器提交请求");
		progressDialog.setTitle("请稍候");
		progressDialog.show();

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					progressDialog.dismiss();
					try {
						doForeGround3();
					} catch (Exception e) {
						handleExceptionOfThread(e);
					}
				} else if (msg.what == 1) {
					AlertDialog dlg = new AlertDialog.Builder(
							AbstractBaseActivity.this)
							.setTitle("出错了")
							.setMessage(message)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int 

i) {
											return;
										}
									}).create();
					dlg.show();
				}
				try {
					postHandle3();
				} catch (Exception e) {
					handleExceptionOfThread(e);
				}
			}
		};
		Runnable r = new ConnectThread3(handler);
		new Thread(r).start();
	}

	protected void runHandler4() {
		progressDialog = new ProgressDialog(AbstractBaseActivity.this);
		progressDialog.setMessage("正在向服务器提交请求");
		progressDialog.setTitle("请稍候");
		progressDialog.show();

		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 0) {
					progressDialog.dismiss();
					try {
						doForeGround4();
					} catch (Exception e) {
						handleExceptionOfThread(e);
					}
				} else if (msg.what == 1) {
					AlertDialog dlg = new AlertDialog.Builder(
							AbstractBaseActivity.this)
							.setTitle("出错了")
							.setMessage(message)
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {
										@Override
										public void onClick(
												DialogInterface dialog, int 

i) {
											return;
										}
									}).create();
					dlg.show();
				}
				try {
					postHandle4();
				} catch (Exception e) {
					handleExceptionOfThread(e);
				}
			}
		};
		Runnable r = new ConnectThread4(handler);
		new Thread(r).start();
	}

	protected void doForeGround() throws Exception {
	}

	protected void doForeGroundFail() {
	}

	protected void doBackGround() throws Exception {
	}

	protected void doForeGround2() throws Exception {
	}

	protected void doBackGround2() throws Exception {
	}

	protected void postHandle2() throws Exception {
	}

	protected void doForeGround3() throws Exception {
	}

	protected void doBackGround3() throws Exception {
	}

	protected void doForeGround4() throws Exception {
	}

	protected void doBackGround4() throws Exception {
	}

	protected void postHandle3() throws Exception {
	}

	protected void postHandle4() throws Exception {
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		activityManager.popActivity(this);
	}

	public ActivityManager getActivityManager() {
		return activityManager;
	}

	private class ConnectThread extends Thread {

		Handler h;

		public ConnectThread(Handler h) {
			this.h = h;
		}

		@Override
		public void run() {
			try {
				doBackGround();
			} catch (Exception e) {
				handleExceptionOfThread(e);
			}
			h.sendEmptyMessage(0);
		}
	}

	private class ConnectThread2 extends Thread {

		Handler h;

		public ConnectThread2(Handler h) {
			this.h = h;
		}

		@Override
		public void run() {
			try {
				doBackGround2();
			} catch (Exception e) {
				handleExceptionOfThread(e);
			}
			h.sendEmptyMessage(0);
		}
	}

	private class ConnectThread3 extends Thread {

		Handler h;

		public ConnectThread3(Handler h) {
			this.h = h;
		}

		@Override
		public void run() {
			try {
				doBackGround3();
			} catch (Exception e) {
				handleExceptionOfThread(e);
			}
			h.sendEmptyMessage(0);
		}
	}

	private class ConnectThread4 extends Thread {

		Handler h;

		public ConnectThread4(Handler h) {
			this.h = h;
		}

		@Override
		public void run() {
			try {
				doBackGround4();
			} catch (Exception e) {
				handleExceptionOfThread(e);
			}
			h.sendEmptyMessage(0);
		}
	}

	private void handleExceptionOfThread(Exception e) {
	}

	protected void handleExceptionOfNonThread(Exception e) {
	}

}

