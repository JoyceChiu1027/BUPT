package com.bupt.electricpower.adapter;

import java.util.List;
import com.bupt.electricpower.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WithIconBaseAdatper extends MyBaseAdapter<String>{
    private int[] resIds;
	public WithIconBaseAdatper(List<String> data,int[] resIds, Context context) {
		super(data, context);
		this.resIds=resIds;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		//convertView
		Log.d("WithIconBaseAdatper", "getView() is called");
		if (convertView==null) {
			convertView=LayoutInflater.from(context).inflate(R.layout.base_list_item_with_icon, null);
			
		}
		TextView text=(TextView) convertView.findViewById(R.id.list_item_text);
		ImageView icon=(ImageView) convertView.findViewById(R.id.list_item_icon);
		text.setText(data.get(position));
		icon.setImageResource(resIds[position]);
		return convertView;	
	}
	

}
