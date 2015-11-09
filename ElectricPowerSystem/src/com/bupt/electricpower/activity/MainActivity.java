package com.bupt.electricpower.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bupt.electricpower.R;
import com.bupt.electricpower.adapter.WithIconBaseAdatper;

public class MainActivity extends AbstractBaseActivity {
    private ListView list;
    private Context mContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	mContext=MainActivity.this;
    	list=(ListView) findViewById(R.id.base_list);
    	//list.setSelector(getResources().getColor(R.color.black));
    	//String[] data={"审批","图表","行踪管理"};
    	List<String> data=new ArrayList<String>();
    	data.add("审批");
    	data.add("图表");
    	data.add("行踪管理");
    	//data.add(getResources().getString(R.string.app_name));
    	int[] resIds={R.drawable.apply,R.drawable.charts,R.drawable.traces};
    	//SimpleAdapter adapter=new SimpleAdapter(mContext, data, resource, from, to)
    	list.setAdapter(new WithIconBaseAdatper(data, resIds, mContext));
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				if(position==0){
					intent.setClass(mContext, ApplyListActivity.class);
				}else if (position==1) {
					intent.setClass(mContext, ChartListActivity.class);
				}else if(position==2){
					intent.setClass(mContext, TracesManagementActivity.class);
				}
				startActivity(intent);
			}
		});
    }
	@Override
	protected boolean isNeedInitBack() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
       setContentView(R.layout.base_list_with_title_bar);
	}

}
