package com.bupt.electricpower.chartutil;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.bupt.electricpower.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;

public class ChartViewUtil extends BaseChartUtil{
	public GraphicalView getElectricityBarChartView(Context context,String[] titles,List<double[]> xValues,List<double[]> yValues){
		List<double[]> values=new ArrayList<double[]>();
		values.add(new double[] { 12230, 13300, 17240, 15244, 18900, 20000, 23000, 21000, 19500, 15500,
		        18600, 14000 });
		int[] colors={Color.BLUE};
		XYMultipleSeriesRenderer renderer=buildBarRenderer(colors);
		XYMultipleSeriesDataset dataset=buildBarDataset(titles, yValues);
		GraphicalView view =ChartFactory.getBarChartView(context, dataset, renderer, Type.DEFAULT);
	    return view;
	}
	public void setRenderer(XYMultipleSeriesRenderer renderer){
		
	}
	public static void initXYMultipleSeriesRenderer(Context context,XYMultipleSeriesRenderer renderer, String xTitle,
			String yTitle, double xMin, double xMax, double yMin, double yMax,
			int xLabelsCount, int yLabelsCount, int axesColor, int labelsColor){
		renderer.setChartTitle("");
		renderer.setInScroll(true);
		renderer.setAxisTitleTextSize(30);
		//renderer.setChartTitleTextSize(20);
		//renderer.setLabelsTextSize(15);
		renderer.setLabelsTextSize(context.getResources().getDimension(R.dimen.legend_text_size));
		renderer.setLegendTextSize(context.getResources().getDimension(R.dimen.legend_text_size));
		//renderer.setLegendHeight((int)context.getResources().getDimension(R.dimen.legend_height));
		renderer.setAxisTitleTextSize(context.getResources().getDimension(R.dimen.x_axis_title_size));
		int top=(int)context.getResources().getDimension(R.dimen.chart_margin_top);
		int bottom=(int)context.getResources().getDimension(R.dimen.chart_margin_bottom);
		int left=(int)context.getResources().getDimension(R.dimen.chart_margin_left);
		int right=(int)context.getResources().getDimension(R.dimen.chart_margin_right);
		renderer.setMargins(new int[]{top,left,bottom,right});//仅指图表的边距 1:上2:左 3：下 4：右
        renderer.setFitLegend(true);
		renderer.setAxesColor(axesColor);
		renderer.setLabelsColor(labelsColor);
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setXTitle(xTitle);
		renderer.setYTitle(yTitle);
		renderer.setXAxisMin(xMin);
		renderer.setXAxisMax(xMax);
		renderer.setYAxisMin(yMin);
		renderer.setYAxisMax(yMax);
		renderer.setChartTitleTextSize(16);
		renderer.setXLabels(xLabelsCount);
		renderer.setYLabels(yLabelsCount);
		renderer.setXLabelsAlign(Align.LEFT);
		renderer.setYLabelsAlign(Align.RIGHT);
		// renderer.setPointSize((float) 5);
		renderer.setShowLegend(true);
		//enderer.setBarSpacing(45f);
		renderer.setZoomRate(1.0f);
	    renderer.setBarSpacing(0.5f);
	    renderer.setZoomInLimitX(2.5f);
	    renderer.setPanEnabled(true, false);
		renderer.setZoomEnabled(true, false);
		//renderer.setClickEnabled(true);
		renderer.setMarginsColor(0xffffff);
	}
}
