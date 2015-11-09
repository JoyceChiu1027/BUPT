package com.bupt.electricpower.activity;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.bupt.electricpower.R;
import com.bupt.electricpower.adapter.WithIconBaseAdatper;

public class ChartListActivity extends AbstractBaseActivity {
	private ListView list;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext=ChartListActivity.this;
		list=(ListView) findViewById(R.id.base_list);
    	//String[] data={"机组负荷","发电量","耗煤量","进煤量"};
    	List<String> data=new ArrayList<String>();
    	data.add("机组负荷");
    	data.add("发电量");
    	data.add("耗煤量");
    	data.add("进煤量");
    	int[] resIds={R.drawable.e_loads,R.drawable.e_generation,R.drawable.coal_consumption,R.drawable.coal_stock};
    	list.setAdapter(new WithIconBaseAdatper(data, resIds, mContext));
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				if(position==0){
					intent.setClass(mContext, ElectricityLoadChartActivity.class);
				}else if (position==1) {
					intent.setClass(mContext, ElectricityGenerationInfoActivity.class);
				}else if(position==2){
					intent.setClass(mContext, CoalConsumeChartActivity.class);
				}else if(position==3){
					intent.setClass(mContext, CoalStockChartActivity.class);
				}
				startActivity(intent);
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
		setContentView(R.layout.base_list_with_title_bar);
	}

}
