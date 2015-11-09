package com.bupt.electricpower.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.chartutil.ChartViewUtil;
import com.bupt.electricpower.model.ResponseElecGeneration;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.ToastUtil;
import com.bupt.electricpower.util.WebServiceUtil;

public class ElectricityGenerationInfoActivity extends AbstractBaseActivity {
	private TextView popShowTv;
	private PopupWindow popupWindow;
	private LinearLayout parent;
	private RadioButton monthRadioBtn;
	private RadioGroup radioGroupDate;
	private RadioGroup radioGroupConsume;
	private TextView dateShowTv;
	private Button queryBtn;
	LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.MATCH_PARENT,
			LinearLayout.LayoutParams.MATCH_PARENT);
	private Context mContext;
	private GraphicalView mChartView;
	private XYMultipleSeriesDataset mDataset = new XYMultipleSeriesDataset();
	/** The main renderer that includes all the renderers customizing a chart. */
	private XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	/** The most recently added series. */
	/** The most recently created renderer, customizing the current series. */
	private int year;
	private int month;
	private int day;
	private int checkDateType;
	// private double[] value;
	/*
	 * private double[] xValues = new double[30]; private double[] yValues;
	 */
	private List<double[]> xValues = new ArrayList<double[]>();
	private List<double[]> yValues = new ArrayList<double[]>();
	SimpleDateFormat df;
	SimpleDateFormat df1;
	Date nowDate;
	Date selectDate;

	private List<ResponseElecGeneration> returnList = new ArrayList<ResponseElecGeneration>();
	private static final String TAG = "ElectricityGenerationInfoActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = ElectricityGenerationInfoActivity.this;
		df = new SimpleDateFormat("yyyy-MM", Locale.CHINA);
		df1 = new SimpleDateFormat("yyyy", Locale.CHINA);
		initView();
		initChart();
		initPopWindow();
		runHandler();

	}

	private void initView() {

		parent = (LinearLayout) findViewById(R.id.chart_parent);
		popShowTv = (TextView) findViewById(R.id.popwindow_show_tv);
		popShowTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int[] location = new int[2];
				v.getLocationOnScreen(location);
				showPop(popShowTv, location[0],
						location[1] - popupWindow.getHeight());
			}
		});

	}

	private void initChart() {
		initRenderer("Day", 0, 4, 0, 0, 5, 6, 1);
		initDataset();
		mChartView = ChartFactory.getBarChartView(mContext, mDataset,
				mRenderer, Type.DEFAULT);
		parent.addView(mChartView, lp);

	}

	private void initRenderer(String xTitle, double xMin, double xMax,
			double yMin, double yMax, int xLabelsCount, int yLabelsCount,
			int checkType) {
		Log.e(TAG, "initRenderer() is called");

		int[] colors = { 0xfff74461, 0xff576069, 0xffb9e3d9, 0xffadc3c0 };
		// mCurrentRenderer=initRenderer(colors[0]);
		mRenderer.removeAllRenderers();
		for (int i = 0; i < colors.length; i++) {
			SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();
			renderer.setColor(colors[i]);
			renderer.setDisplayChartValues(true);
			// renderer.setDisplayBoundingPoints(true);
			renderer.setChartValuesTextAlign(Align.CENTER);
			renderer.setShowLegendItem(true);
			mRenderer.addSeriesRenderer(renderer);
		}

		ChartViewUtil.initXYMultipleSeriesRenderer(mContext, mRenderer, xTitle,
				"发电量", xMin, xMax, yMin, yMax, xLabelsCount, yLabelsCount,
				Color.BLACK, Color.BLACK);
	}

	private void initDataset() {

		Log.e(TAG, "initDataset() is called");
		String[] titles = { "5号机", "6号机", "7号机", "全厂" };
		for (int i = 0; i < titles.length; i++) {
			XYSeries series = new XYSeries(titles[i]);
			mDataset.addSeries(series);
		}
	}

	private void initBar(XYSeries series, double[] xValue, double[] yValue) {
		series.clear();
		for (int i = 0; i < yValue.length; i++) {
			series.add(xValue[i], yValue[i]);
		}
	}

	private void initMultipleBar(List<double[]> xValues, List<double[]> yValues) {
		for (int i = 0; i < 4; i++) {
			XYSeries series = mDataset.getSeriesAt(i);
			double[] xValue = xValues.get(i);
			double[] yValue = yValues.get(i);
			initBar(series, xValue, yValue);
		}

	}

	@Override
	protected boolean isNeedInitBack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.base_chart_layout_activity);
	}

	@Override
	protected void doForeGround() throws Exception {

		if (returnList != null) {

			if (returnList.size() > 0) {
				getValues(returnList);
			} else {
				for (int i = 0; i < 4; i++) {
					xValues.add(new double[] { 1, 2, 3, 4, 5, 6 });
					yValues.add(new double[] { 0, 0, 0, 0, 0, 0 });
				}
				Log.e(TAG, "returnList.size()=0");
				ToastUtil.show(mContext, "无数据！");
				return;
			}
		} else {
			for (int i = 0; i < 4; i++) {
				xValues.add(new double[] { 1, 2, 3, 4, 5, 6 });
				yValues.add(new double[] { 0, 0, 0, 0, 0, 0 });
			}
			Log.e(TAG, "returnList==null");
			ToastUtil.show(mContext, "网络连接异常！");
			return;
		}
		double max = AppUtil.getMax(yValues);
		double xLimits = returnList.size();
		// double max=getMAX(yValues);
		Log.d(TAG, "max=" + max);
		double maxPlus = max * 0.1;
		mRenderer.setYAxisMax(max + maxPlus);
		mRenderer.setPanLimits(new double[] { 0, xLimits + 0.5, 0,
				mRenderer.getYAxisMax() });
		Log.e(TAG, "mRenderer.isClickEnabled()=" + mRenderer.isClickEnabled());
		initMultipleBar(xValues, yValues);
		mChartView.repaint();
		mChartView.invalidate();

		Log.d(TAG, "mRenderer.getYAxisMax()=" + mRenderer.getYAxisMax());

	}

	private void getValues(List<ResponseElecGeneration> returnList) {
		int count = returnList.size();
		xValues.clear();
		yValues.clear();
		for (int i = 0; i < 4; i++) {
			double[] x = new double[count];
			for (int j = 0; j < count; j++) {
				x[j] = j + 1;
			}
			xValues.add(x);
		}
		double[] y1 = new double[count];
		double[] y2 = new double[count];
		double[] y3 = new double[count];
		double[] y4 = new double[count];

		if (checkDateType == 1) {// 返回年的请求
			initRenderer("Day", 0.5, 3, 0, 0, 4, 6, checkDateType);
			for (int i = 0; i < count; i++) {
				y1[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb51());
				y2[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb61());
				y3[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb71());
				y4[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb81());
			}

		} else if (checkDateType == 0) {
			initRenderer("Month", 0.5, 3, 0, 0, 4, 6, checkDateType);
			for (int i = 0; i < count; i++) {
				y1[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb52());
				y2[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb62());
				y3[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb72());
				y4[i] = AppUtil
						.parseStringToDouble(returnList.get(i).getZb82());
			}
		}
		for (int i = 0; i < count; i++) {
			Log.d(TAG, "y1[" + i + "]" + y1[i]);
			Log.d(TAG, "y2[" + i + "]" + y2[i]);
			Log.d(TAG, "y3[" + i + "]" + y3[i]);
			Log.d(TAG, "y4[" + i + "]" + y4[i]);
		}
		yValues.add(y1);
		yValues.add(y2);
		yValues.add(y3);
		yValues.add(y4);

	}

	@Override
	protected void doBackGround() throws Exception {
		/*
		 * String userName = "ifsapp"; String userPwd = "ifsppa";
		 */
		String userName = AppUtil
				.getFromPref(CodeConstants.USER_NAME, mContext);
		String userPwd = AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		String requestDate;
		String unit;
		if (checkDateType == 0) {
			unit = "y";
			requestDate = df1.format(selectDate);
			initRenderer("Month", 0, 4, 0, 0, 5, 6, checkDateType);
		} else {
			unit = "m";
			requestDate = df.format(selectDate);
			initRenderer("Day", 0, 4, 0, 0, 5, 6, checkDateType);
		}
		/*
		 * returnList = WebServiceUtil.getElecgeneration(userName, userPwd,
		 * requestDate, unit);
		 */
		returnList.clear();
		String[] zb51s = { "1000.1", "2000.12", "2600.12", "2600.12",
				"4600.12", "1600.12", "2200.12", "3200.12", "3400.45",
				"4047.56", "2333.67", "3444.67", "3333.65", "3222.78",
				"1500.89" };

		String[] zb61s = { "1000.1", "2600.12", "2333.67", "3444.67",
				"4333.65", "3222.78", "1500.89", "2600.12", "2600.12",
				"3200.12", "3222.78", "4400.12", "1600.12", "2200.12",
				"1500.89" };
		String[] zb71s = { "1100.1", "4200.12", "4000.12", "2200.12",
				"3200.12", "3400.45", "4647.56", "2333.67", "2600.12",
				"4500.12", "1600.12", "2200.12", "3200.12", "3222.78",
				"4647.56" };
		String[] zb81s = { "3500.1", "4600.12", "3200.12", "3200.12",
				"3222.78", "5647.56", "2333.67", "2600.12", "4600.12",
				"1600.12", "1000.1", "2600.12", "2333.67", "3444.67", "4333.65" };
		String[] zb52s = { "10000.1", "20000.12", "33333.33", "34003.56",
				"20000.32", "28394.33", "32345.93", "23553.88", "10328.98",
				"29034.33", "37848.44", "13947.33" };
		String[] zb62s = { "12000.1", "30030.12", "23553.88", "10328.98",
				"29034.33", "37848.44", "13947.33", "34003.56", "20000.32",
				"28394.33", "32345.93", "23553.88" };
		String[] zb72s = { "30000.15", "38900.12", "13947.33", "34003.56",
				"20000.32", "12000.1", "30030.12", "23553.88", "10328.98",
				"23553.88", "28394.33", "32345.93" };
		String[] zb82s = { "25000.1", "24000.12", "29034.33", "37848.44",
				"13947.33", "10328.98", "23553.88", "28394.33", "32345.93",
				"20000.32", "12000.1", "30030.12" };
		if (checkDateType == 1) {
			for (int i = 0; i < zb51s.length; i++) {
				ResponseElecGeneration response = new ResponseElecGeneration();
				response.setZb51(zb51s[i]);
				response.setZb61(zb61s[i]);
				response.setZb71(zb71s[i]);
				response.setZb81(zb81s[i]);
				returnList.add(response);
			}
		}else if (checkDateType==0) {
            for (int i = 0; i < zb52s.length; i++) {
            	ResponseElecGeneration response = new ResponseElecGeneration();
            	response.setZb52(zb52s[i]);
				response.setZb62(zb62s[i]);
				response.setZb72(zb72s[i]);
				response.setZb82(zb82s[i]);
				returnList.add(response);
			}
		}
		
		/*for (int i = 0; i < zb51s.length; i++) {
			ResponseElecGeneration response = new ResponseElecGeneration();
			if (checkDateType == 0) {
				response.setZb51(zb51s[i]);
				response.setZb61(zb61s[i]);
				response.setZb71(zb71s[i]);
				response.setZb81(zb81s[i]);

			} else if (checkDateType == 1) {
				response.setZb52(zb52s[i]);
				response.setZb62(zb62s[i]);
				response.setZb72(zb72s[i]);
				response.setZb82(zb82s[i]);
			}

			returnList.add(response);
		}*/

	}

	private DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
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
				month = monthOfYear + 1;
				year = myyear;

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

				if (checkDateType == 1) {

					updateDate(df.format(selectDate));

				} else if (checkDateType == 0) {
					updateDate(df1.format(selectDate));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		private void updateDate(String date) {
			dateShowTv.setText(date);

		}
	};

	private DatePicker findDatePicker(ViewGroup group) {
		if (group != null) {
			for (int i = 0, j = group.getChildCount(); i < j; i++) {
				View child = group.getChildAt(i);
				if (child instanceof DatePicker) {
					return (DatePicker) child;
				} else if (child instanceof ViewGroup) {
					DatePicker result = findDatePicker((ViewGroup) child);
					if (result != null)
						return result;
				}
			}
		}
		return null;
	}

	private void initPopWindow() {
		LayoutInflater inflater = LayoutInflater.from(mContext);
		View popView = inflater.inflate(R.layout.select_content_view, null);
		popupWindow = new PopupWindow(popView, LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		popupWindow.setBackgroundDrawable(new ColorDrawable(Color.rgb(243, 244,
				246)));
		// android:windowEnterAnimation表示进入窗口动画
		// android:windowExitAnimation表示窗口退出动画
		// 设置popwindow出现和消失动画
		popupWindow.setAnimationStyle(R.style.PopupAnimation);
		radioGroupConsume = (RadioGroup) popView
				.findViewById(R.id.consume_check_group);
		radioGroupConsume.setVisibility(View.GONE);
		radioGroupDate = (RadioGroup) popView
				.findViewById(R.id.date_check_group);
		monthRadioBtn = (RadioButton) popView.findViewById(R.id.month_check);
		dateShowTv = (TextView) popView.findViewById(R.id.date_show_tv);
		queryBtn = (Button) popView.findViewById(R.id.query_btn);
		monthRadioBtn.setChecked(true);
		checkDateType = 1;
		initDate();
		initListener();

	}

	private void initListener() {
		dateShowTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				DatePickerDialog dpd = new DatePickerDialog(mContext,
						dateListener, year, month - 1, day);
				dpd.show();
				DatePicker dp = findDatePicker((ViewGroup) dpd.getWindow()
						.getDecorView());
				if (dp != null) {
					Log.d(TAG, "checkType=" + checkDateType);
					if (checkDateType == 1) {
						((ViewGroup) ((ViewGroup) dp.getChildAt(0))
								.getChildAt(0)).getChildAt(2).setVisibility(
								View.GONE);
					} else if (checkDateType == 0) {
						((ViewGroup) ((ViewGroup) dp.getChildAt(0))
								.getChildAt(0)).getChildAt(1).setVisibility(
								View.GONE);
						((ViewGroup) ((ViewGroup) dp.getChildAt(0))
								.getChildAt(0)).getChildAt(2).setVisibility(
								View.GONE);
					}
				}

			}
		});
		queryBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popupWindow.dismiss();
				runHandler();

			}
		});
		radioGroupDate
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						Log.d(TAG, "checkedId=" + checkedId);
						if (checkedId == R.id.year_check) {
							checkDateType = 0;
							dateShowTv.setText(df1.format(selectDate));
						} else if (checkedId == R.id.month_check) {
							checkDateType = 1;
							dateShowTv.setText(df.format(selectDate));

						}
					}
				});
	}

	private void initDate() {
		Calendar mycalendar = Calendar.getInstance(Locale.CHINA);
		Date mydate = new Date();
		mycalendar.setTime(mydate);
		year = mycalendar.get(Calendar.YEAR);
		month = mycalendar.get(Calendar.MONTH) + 1;
		day = mycalendar.get(Calendar.DAY_OF_MONTH);
		String dateStr = df.format(mydate);
		dateShowTv.setText(dateStr);
		try {
			selectDate = df.parse(year + "-" + month + "-" + day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d(TAG, "myDate=" + dateStr);
		Log.d(TAG, "year=" + year);
		Log.d(TAG, "month=" + month);
	}

	public void showPop(View parent, int x, int y) {
		// 设置popwindow显示位置
		popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, x, y);
		// 获取popwindow焦点
		popupWindow.setFocusable(true);
		// 设置popwindow如果点击外面区域，便关闭。
		popupWindow.setOutsideTouchable(true);
		popupWindow.update();
		if (popupWindow.isShowing()) {

		}

	}
}
