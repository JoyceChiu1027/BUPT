package com.bupt.electricpower.activity;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.chartutil.ElectricityLoadLineChart;
import com.bupt.electricpower.model.ResponseLoadInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.WebServiceUtil;

public class ElectricityLoadChartActivity extends AbstractBaseActivity {
	private LinearLayout parent;
	private TextView currentTv;
	private Context mContext;
	
	
	private ResponseLoadInfo response;
	private LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			600);
	private List<double[]> xValues;
	private List<double[]> yValues;
	private static final String TAG = "ElectricityLoadChartActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = ElectricityLoadChartActivity.this;
		parent = (LinearLayout) findViewById(R.id.chart_parent);
		currentTv=(TextView) findViewById(R.id.current_genernation);
		xValues = new ArrayList<double[]>();
		xValues.add(new double[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 });
		yValues = new ArrayList<double[]>();
		runHandler();
		// lp.gravity=Gravity.CENTER;
		// lp.setMargins(20, 20, 20, 20);

	}

	@Override
	protected boolean isNeedInitBack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.electricity_load_chart_activity);
	}

	@Override
	protected void doForeGround() throws Exception {
		// TODO Auto-generated method stub
		super.doForeGround();
		if (response != null) {
			Log.d(TAG, "response.getNt01()=" + response.getNt01());
			Log.d(TAG, "response.getNt02()=" + response.getNt02());
			Log.d(TAG, "response.getNt03()=" + response.getNt03());
			Log.d(TAG, "response.getNt04()=" + response.getNt04());
			Log.d(TAG, "response.getNt05()=" + response.getNt05());
			Log.d(TAG, "response.getNt06()=" + response.getNt06());
			Log.d(TAG, "response.getNt07()=" + response.getNt07());
			Log.d(TAG, "response.getNt08()=" + response.getNt08());
			Log.d(TAG, "response.getNt09()=" + response.getNt09());
			Log.d(TAG, "response.getNt10()=" + response.getNt10());
			Log.d(TAG, "response.getNt11()=" + response.getNt11());
			Log.d(TAG, "response.getNt12()=" + response.getNt12());
			Log.d(TAG, "response.getNt13()=" + response.getNt13());
			Log.d(TAG, "response.getNt14()=" + response.getNt14());
			Log.d(TAG, "response.getNt15()=" + response.getNt15());
			Log.d(TAG, "response.getNt16()=" + response.getNt16());
			Log.d(TAG, "response.getNt17()=" + response.getNt17());
			Log.d(TAG, "response.getNt18()=" + response.getNt18());
			Log.d(TAG, "response.getNt19()=" + response.getNt19());
			Log.d(TAG, "response.getNt20()=" + response.getNt20());
			Log.d(TAG, "response.getNt21()=" + response.getNt21());
			Log.d(TAG, "response.getNt22()=" + response.getNt22());
			Log.d(TAG, "response.getNt23()=" + response.getNt23());
			Log.d(TAG, "response.getNt24()=" + response.getNt24());
			yValues.clear();
			yValues.add(new double[] {
					AppUtil.parseStringToDouble(response.getNt01()),
					AppUtil.parseStringToDouble(response.getNt02()),
					AppUtil.parseStringToDouble(response.getNt03()),
					AppUtil.parseStringToDouble(response.getNt04()),
					AppUtil.parseStringToDouble(response.getNt05()),
					AppUtil.parseStringToDouble(response.getNt06()),
					AppUtil.parseStringToDouble(response.getNt07()),
					AppUtil.parseStringToDouble(response.getNt08()),
					AppUtil.parseStringToDouble(response.getNt09()),
					AppUtil.parseStringToDouble(response.getNt10()),
					AppUtil.parseStringToDouble(response.getNt11()),
					AppUtil.parseStringToDouble(response.getNt12()),
					AppUtil.parseStringToDouble(response.getNt13()),
					AppUtil.parseStringToDouble(response.getNt14()),
					AppUtil.parseStringToDouble(response.getNt15()),
					AppUtil.parseStringToDouble(response.getNt16()),
					AppUtil.parseStringToDouble(response.getNt17()),
					AppUtil.parseStringToDouble(response.getNt18()),
					AppUtil.parseStringToDouble(response.getNt19()),
					AppUtil.parseStringToDouble(response.getNt20()),
					AppUtil.parseStringToDouble(response.getNt21()),
					AppUtil.parseStringToDouble(response.getNt22()),
					AppUtil.parseStringToDouble(response.getNt23()),
					AppUtil.parseStringToDouble(response.getNt24()) });
			/*
			 * new double[] { 10, 6.7, 5.3, 5, 4.6, 4.3, 4.5, 10.6, 22, 19, 15,
			 * 9, 6, 9.5, 10, 11, 15, 19, 23.6, 26, 24.3, 22.4, 26.8, 25 }
			 */
			String[] titles = { "负荷曲线" };
			double max=AppUtil.getMax(yValues);
			parent.addView(new ElectricityLoadLineChart(max).getChartView(
					mContext, titles, xValues, yValues), lp);
			currentTv.setVisibility(View.VISIBLE);
		} else {
			currentTv.setVisibility(View.GONE);
		}

	}

	@Override
	protected void doBackGround() throws Exception {
		// TODO Auto-generated method stub
		// super.doBackGround();
		/*String userName = "ifsapp";
		String userPwd = "ifsppa";*/
		String userName = AppUtil.getFromPref(CodeConstants.USER_NAME, mContext);
		String userPwd = AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		response = WebServiceUtil.getLoadInfo(userName, userPwd);

	}
}
