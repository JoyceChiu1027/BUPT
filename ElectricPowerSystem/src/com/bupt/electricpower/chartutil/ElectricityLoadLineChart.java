package com.bupt.electricpower.chartutil;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.tools.PanListener;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ElectricityLoadLineChart extends AbstractDemoChart {
	private double yMax;

	public ElectricityLoadLineChart(double yMax) {
		this.yMax = yMax;
	}

	@Override
	GraphicalView getChartView(Context context) {
		String[] titles = {};
		List<double[]> xValues = new ArrayList<double[]>();
		List<double[]> yValues = new ArrayList<double[]>();
		// String[] xValues={};
		// String[] yValues={};
		int[] colors = {};
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		XYMultipleSeriesDataset dataset = buildDataset(titles, xValues, yValues);
		// TODO Auto-generated method stub
		GraphicalView view = ChartFactory.getLineChartView(context, dataset,
				renderer);
		return view;
	}

	public GraphicalView getChartView(final Context context, String[] titles,
			List<double[]> xValues, List<double[]> yValues) {
		int[] colors = { Color.MAGENTA };
		PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE };

		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		Log.d("getChartView",
				"renderer.getSeriesRendererCount()="
						+ renderer.getSeriesRendererCount());
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setLineWidth(3);// 设置线的粗细
			renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
		}
		renderer.setZoomEnabled(true, false);
		renderer.setPanEnabled(true, false);
		renderer.setPanLimits(new double[] { 0, 24.5, 0, yMax+yMax*0.2 });
		// renderer.setScale(8f);//It works on some charts like pie, doughnut,
		// dial.
		renderer.setXLabels(13);
		renderer.setXAxisMin(1);
		renderer.setXAxisMax(12);
		renderer.setYAxisMax(yMax + yMax*0.1, 0);
		renderer.setXLabelsColor(Color.BLACK);
		// renderer.initAxesRange(1);
		/*
		 * for (int i = 0; i < xValues.get(0).length; i++) {
		 * 
		 * renderer.initAxesRangeForScale(i); }
		 */
		// renderer.initAxesRangeForScale(5);
		// renderer.initAxesRange(1);//length=6
		// renderer.initAxesRangeForScale(0);//index=5
		// renderer.setXAxisMax(24);
		// renderer.setXLabelsAngle(-60f);
		// renderer.setYAxisMin(0);
		// renderer.setYAxisMax(65);
		// renderer.setYLabels(10);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setAxesColor(Color.BLACK);

		renderer.setMarginsColor(0x007fff00);
		// XYMultipleSeriesDataset dataset=buildDataset(titles, xValues,
		// yValues);
		XYMultipleSeriesDataset dataset = buildDataset(titles, xValues,
				yValues, 0);
		GraphicalView view = ChartFactory.getLineChartView(context, dataset,
				renderer);

		return view;
	}
}
