package com.bupt.electricpower.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bupt.electricpower.R;

public class ApplyActivity extends AbstractBaseActivity {

	private ListView listView;
	private List<String> itemNames = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listView = (ListView) findViewById(R.id.lv_list);
		itemNames = initBusList();
		listView.setAdapter(new ListAdapter(ApplyActivity.this));
		listView.setOnItemClickListener(new ListOnItemClickListener());
	}
	
	private List<String> initBusList() {
			itemNames.add("");
			itemNames.add("");
			itemNames.add("");
	
		return itemNames;
	}
	
	@Override
	protected boolean isNeedInitBack() {
		return true;
	}
	
	@Override
	protected void setLayout() {
		setContentView(R.layout.apply);
	}
	
	class ListOnItemClickListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int position,
				long arg3) {
			Intent in = new Intent();
			in.setClass(ApplyActivity.this, ApplyListActivity.class);
			startActivity(in);
		}

	}
	
	class ListAdapter extends BaseAdapter {
		private LayoutInflater inflater;

		public ListAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		@Override
		public int getCount() {
			return itemNames.size();
		}

		@Override
		public Object getItem(int position) {
			return itemNames.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup AdapterView) {
			view = inflater.inflate(R.layout.list_item, null);

			TextView busiNameTextView = (TextView) view
					.findViewById(R.id.item_text);
			busiNameTextView.setText(itemNames.get(position));
			return view;
		}
	}

}
