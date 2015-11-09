package com.bupt.electricpower.chartutil;

import java.util.ArrayList;
import java.util.List;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.achartengine.tools.Zoom;
import org.achartengine.tools.ZoomEvent;
import org.achartengine.tools.ZoomListener;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.provider.SyncStateContract.Columns;


public class ElectricityGenerationStackChart extends AbstractDemoChart{

	@Override
	public GraphicalView getChartView(Context context) {
		// TODO Auto-generated method stub
		String[] titles={"月发电量"};
		List<double[]> values=new ArrayList<double[]>();
		values.add(new double[] { 12230, 13300, 17240, 15244, 18900, 20000, 23000, 21000, 19500, 15500,
		        18600, 14000 });
	    //int[] xLaybles={1,2,3,4,5,6,7,8,9,10,11,12};
		int[] colors = new int[] {Color.BLUE };
		XYMultipleSeriesDataset dataset=buildBarDataset(titles, values);
		XYMultipleSeriesRenderer renderer =buildBarRenderer(colors);
		setChartSettings(renderer, "", "月份","发电量", 0, 12.5, 0, 28000, Color.BLACK, Color.BLACK);
		//renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        //  renderer.setShowAxes(false); 
	    // 设置条形图之间的距离
	    renderer.setBarSpacing(0.5f);
	    renderer.setInScroll(false);
	    renderer.setPanEnabled(false, false);
	    renderer.setClickEnabled(true);//设置为true 图表不能缩放也不能移动 setZoomEnabled(true) 无效
	    //renderer.setZoomEnabled(false);
	    //renderer.setExternalZoomEnabled(false);
	   
	    //设置x轴和y轴标签的颜色
	    renderer.setXLabelsColor(Color.RED);
	    renderer.setYLabelsColor(0,Color.RED);
	    // renderer.setAxesColor(Color.BLACK);
	    //设置图标的标题
	    renderer.setChartTitle("发电量");
	   // renderer.setLabelsColor(Color.RED);
       // renderer.setXAxisMin(0);
       // renderer.setXAxisMax(12.5f);
        //renderer.setYAxisMax(30000f);
        //renderer.setYAxisMin(0);
	    //设置图例的字体大小
	    renderer.setLegendTextSize(18);
	    //设置x轴和y轴的最大最小值
	   /* renderer.setRange( new double[] {0.5,
	            12.5, 0, 10000 });*/
	    renderer.setXLabels(13);
	    renderer.setYLabels(8);
	   // renderer.setBackgroundColor(Color.rgb(255, 105, 180));
	   // renderer.setApplyBackgroundColor(true);//设置成true，设置的背景颜色方可显示
	   // renderer.setMarginsColor(Color.rgb(225, 230, 246));
	    renderer.setMarginsColor(0x007fff00);
		GraphicalView view=ChartFactory.getBarChartView(context, dataset, renderer, Type.DEFAULT);
		
		return view;
	}

}
