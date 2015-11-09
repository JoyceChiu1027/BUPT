package com.bupt.electricpower.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class AppUtil {
	private static final String TAG = "AppUtil";

	public static void initTab(TabWidget tabWidget, TabHost tabHost,
			boolean isTv) {

		if (isTv) {
			for (int i = 0; i < tabWidget.getChildCount(); i++) {
				View view = tabWidget.getChildTabViewAt(i);
				view.getLayoutParams().height = 70;
				TextView tv = (TextView) view.findViewById(android.R.id.title);
				tv.setTextSize(20);
				tv.setTextColor(Color.BLACK);
				if (tabHost.getCurrentTab() == i) {
					tabWidget.getChildAt(i).setBackgroundColor(
							Color.argb(110, 255, 255, 255));
				} else {
					tabWidget.getChildAt(i).setBackgroundColor(
							Color.argb(80, 255, 255, 255));
				}
			}
		} else {
			tabWidget.setBackgroundColor(Color.rgb(10, 130, 188));
			for (int i = 0; i < tabWidget.getChildCount(); i++) {
				View view = tabWidget.getChildTabViewAt(i);
				view.getLayoutParams().height = 70;
				TextView tv = (TextView) view.findViewById(android.R.id.title);
				tv.setTextSize(20);
				tv.setTextColor(Color.WHITE);
				if (tabHost.getCurrentTab() == i) {
					tabWidget.getChildAt(i).setBackgroundColor(
							Color.rgb(15, 151, 215));
				} else {
					tabWidget.getChildAt(i).setBackgroundColor(
							Color.rgb(10, 130, 188));
				}
			}
		}
	}

	public static void resetPwd(SharedPreferences sp) {
		Editor editor = sp.edit();
		editor.putString(CodeConstants.USER_NAME, "");
		editor.putString(CodeConstants.CUST_ID, "");
		editor.putString(CodeConstants.PASSWORD, "");
		editor.commit();
	}

	public static SharedPreferences getDefaultPreferences(Context context) {
		SharedPreferences sp = context.getSharedPreferences(
				CodeConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		return sp;

	}

	public static String getFromPref(String name, Context context) {
		SharedPreferences sp = context.getSharedPreferences(
				CodeConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		return sp.getString(name, "");
	}

	/*
	 * public static boolean getFromPref(String name, Context context){
	 * SharedPreferences sp = context.getSharedPreferences(
	 * CodeConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE); return
	 * sp.getBoolean(name, ""); }
	 */
	public static void setToPref(String name, String value, Context context) {
		SharedPreferences sp = context.getSharedPreferences(
				CodeConstants.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(name, value);
		editor.commit();
	}

	public static boolean hasPrice(String str) {
		Pattern pattern = Pattern
				.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
		Matcher match = pattern.matcher(str);
		if (match.matches()) {
			if (str.endsWith(".") || str.equals("0.0") || str.equals("0.00")) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public static boolean isValidEmailAddress(String email) {
		if (null == email || "".equals(email))
			return false;
		// Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //��ƥ��
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");// ����ƥ��
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static boolean isTV(Activity a) {
		DisplayMetrics metrics = new DisplayMetrics();
		a.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int screenWidth = metrics.widthPixels; // ��Ļ���
		int screenHeight = metrics.heightPixels; // ��Ļ�߶�
		return screenWidth > 1200 && screenHeight > 600;
	}

	public static boolean isValidPhoneNumber(String phoneNumber) {
		if (Pattern.compile("^[1][358][0-9]{9}$").matcher(phoneNumber)
				.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * public static Bitmap decodeFile(File f){ try { //Decode image size
	 * BitmapFactory.Options o = new BitmapFactory.Options();
	 * o.inJustDecodeBounds = true; BitmapFactory.decodeStream(new
	 * FileInputStream(f),null,o);
	 * 
	 * //The new size we want to scale to final int REQUIRED_SIZE=70;
	 * 
	 * //Find the correct scale value. It should be the power of 2. int scale=1;
	 * while(o.outWidth/scale/2>=REQUIRED_SIZE &&
	 * o.outHeight/scale/2>=REQUIRED_SIZE) scale*=2;
	 * 
	 * //Decode with inSampleSize BitmapFactory.Options o2 = new
	 * BitmapFactory.Options(); o2.inSampleSize=scale; return
	 * BitmapFactory.decodeStream(new FileInputStream(f), null, o2); } catch
	 * (FileNotFoundException e) {} return null; }
	 */

	public static Bitmap getSmallBitmap(String filePath, int wide, int height) {

		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, wide, height);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		Bitmap bm = BitmapFactory.decodeFile(filePath, options);
		if (bm == null) {
			return null;
		}
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 30, baos);

		} finally {
			try {
				if (baos != null)
					baos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bm;
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			// Calculate ratios of height and width to requested height and
			// width
			final int heightRatio = Math.round((float) height
					/ (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will
			// guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
		}

		return inSampleSize;
	}

	public static boolean isLogined(Context context) {
		String previousUser = getFromPref(CodeConstants.USER_NAME, context)
				.trim();
		String previousPwd = getFromPref(CodeConstants.PASSWORD, context)
				.trim();
		if (previousUser == null || previousUser.isEmpty()
				|| previousUser.equals("null") || previousPwd == null
				|| previousPwd.isEmpty() || previousPwd.equals("null")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean doLogin(Context context) {
		String previousUser = getFromPref(CodeConstants.USER_NAME, context);
		String previousPwd = getFromPref(CodeConstants.PASSWORD, context);
		if (previousUser == null || previousUser.isEmpty()
				|| previousPwd == null || previousPwd.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager
					.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isConnected();
			}
		}
		return false;
	}

	public static void popupDialog(String title, String message, Context a) {
		AlertDialog dlg = new AlertDialog.Builder(a)
				.setTitle(title)
				.setMessage(message)
				.setPositiveButton("ȷ��",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int i) {
								return;
							}
						}).create();
		dlg.show();
	}

	public static void popupDialogWithTwoButton(String title, String message,
			String positiveButtonVal,
			DialogInterface.OnClickListener positiveButtonAction,
			String negativaButtonVal,
			DialogInterface.OnClickListener negativeButtonAction, Context a) {
		new AlertDialog.Builder(a).setTitle(title).setMessage(message)
				.setPositiveButton(positiveButtonVal, positiveButtonAction)
				.setNegativeButton(negativaButtonVal, negativeButtonAction)
				.create().show();
	}

	public static File getFileFromServer(String path, ProgressDialog pd)
			throws Exception {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			URL url = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			pd.setMax(conn.getContentLength());
			InputStream is = conn.getInputStream();
			File file = new File(Environment.getExternalStorageDirectory(),
					"updata.apk");
			FileOutputStream fos = new FileOutputStream(file);
			BufferedInputStream bis = new BufferedInputStream(is);
			byte[] buffer = new byte[1024];
			int len;
			int total = 0;
			while ((len = bis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
				total += len;
				pd.setProgress(total);
			}
			fos.close();
			bis.close();
			is.close();
			return file;
		} else {
			return null;
		}
	}

	public static double parseStringToDouble(String value) {

		if (value == null) {
			return 0;
		} else if (value.equals("null")) {
			return 0;
		} else {
			return Double.parseDouble(value);
		}
		/*
		 * try { double dValue=Double.parseDouble(value);
		 * 
		 * return dValue; } catch (NumberFormatException e) { // TODO: handle
		 * exception e.printStackTrace(); return 0; }
		 */
	}

	public static double getMax(List<double[]> data) {
		double max = 0;
		double[] maxes = new double[4];
		for (int i = 0; i < data.size(); i++) {
			double[] values = data.get(i);
			max = values[0];
			for (int j = 1; j < values.length; j++) {
				if (values[j] > max) {
					max = values[j];
				}
			}
			maxes[i] = max;
			Log.d(TAG, "初步排序后getMAX/maxes[" + i + "]=" + maxes[i]);
		}
		max = maxes[0];
		for (int i = 1; i < maxes.length; i++) {
			if (maxes[i] > max) {
				max = maxes[i];
			}
		}
		return max;
	}
	
	public static  double bubbleSort(double[] values) {
		double max = 0.0;
		double[] temps = new double[values.length];
		for (int i = 0; i < temps.length; i++) {
			temps[i] = values[i];
		}
		for (int i = 0; i < temps.length; i++) {
			for (int j = 0; j < temps.length - i - 1; j++) {
				if (temps[j] < temps[j + 1]) {
					double temp = temps[j + 1];
					temps[j + 1] = temps[j];
					temps[j] = temp;
				}
			}
		}
		max = temps[0];
		return max;
	}
	public static double bubbleSort(List<double[]> data){
		for (int i = 0; i < data.size(); i++) {
			Log.d(TAG, "getMax/data.get("+i+").length="+data.get(i).length);
			for (int j = 0; j < data.get(i).length; j++) {
				Log.d(TAG, "data.get("+i+").["+j+"]="+data.get(i)[j]);
			}
		}
		double max=0;
		double[] maxes=new double[4];
		for (int i = 0; i < data.size(); i++) {
			double[] values=data.get(i);
			for (int j = 0; j < values.length-1; j++) {
				 for (int k = 0; k < values.length-j-1; k++) {
					if(values[k]<values[k+1]){
						double temp=values[k];
						values[k]=values[k+1];
						values[k+1]=temp;
					}
				}
			}
			maxes[i]=values[0];
			Log.d(TAG, "maxes["+i+"]="+maxes[i]);
		}
		for (int i = 0; i < maxes.length; i++) {
			for (int j = 0; j < maxes.length-i-1; j++) {
				if (maxes[j]<maxes[j+1]) {
				   double temp=maxes[j];
				   maxes[j]=maxes[j+1];
				   maxes[j+1]=temp;
				}
			}
		}
		for (int i = 0; i < maxes.length; i++) {
			Log.d(TAG, "排序后的maxes["+i+"]="+maxes[i]);
		}
		max=maxes[0];
		return max;
	}

}
