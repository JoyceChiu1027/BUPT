package com.bupt.electricpower.activity;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bupt.electricpower.R;
import com.bupt.electricpower.model.ResponseTracesInfo;
import com.bupt.electricpower.util.AppUtil;
import com.bupt.electricpower.util.CodeConstants;
import com.bupt.electricpower.util.HLog;
import com.bupt.electricpower.util.ToastUtil;
import com.bupt.electricpower.util.WebServiceUtil;

public class QueryTracesListActivity extends AbstractBaseActivity {
	private ListView listView;
	private List<ResponseTracesInfo> returnList;
	private ArrayAdapter<String> adapter;
	private Context mContext;
	private static final String TAG = "QueryTracesListActivity";
   
	@Override
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mContext = QueryTracesListActivity.this;
		listView = (ListView) findViewById(R.id.base_list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(mContext, QueryTraceInfoActivity.class);
				intent.putExtra(CodeConstants.BACK_DATE, returnList.get(position).getBackDate());
			    intent.putExtra(CodeConstants.LEAVE_DATE, returnList.get(position).getLeaveDate());
			    intent.putExtra(CodeConstants.PERSON_ID, returnList.get(position).getPersonId());
			    intent.putExtra(CodeConstants.TRACK, returnList.get(position).getTrack());
			    intent.putExtra(CodeConstants.CAUSE, returnList.get(position).getCause());
			    startActivity(intent);
			}
		});
		runHandler();
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

	@Override
	protected void doForeGround() throws Exception {
		if (returnList!=null) {
			if(returnList.size()>0){
				HLog.d(TAG, "returnList.size()="+returnList.size());
				String[] data=new String[returnList.size()];
				for (int i = 0; i < returnList.size(); i++) {
					data[i]=returnList.get(i).getPersonId();
				}
				adapter=null;
				adapter=new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, data);
				listView.setAdapter(adapter);
			}else{
				ToastUtil.show(mContext, "无数据！");
			}
				
		}else{
			ToastUtil.show(mContext, "网络连接异常！");
		}
	}

	@Override
	protected void doBackGround() throws Exception {
		String custlName = AppUtil.getFromPref(CodeConstants.USER_NAME,
				mContext);
		String custlpwd = AppUtil.getFromPref(CodeConstants.PASSWORD, mContext);
		returnList = WebServiceUtil.getHistoryTraces(custlName, custlpwd);

	}
}
