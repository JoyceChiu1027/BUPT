package com.bupt.electricpower.adapter;

import java.util.List;

import com.bupt.electricpower.R;
import com.bupt.electricpower.model.ResponseJobTipInfo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ApplyListAdapter extends MyBaseAdapter<ResponseJobTipInfo>{
 
	public ApplyListAdapter(List<ResponseJobTipInfo> data, Context context) {
		super(data, context);	
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("ApplyListAdapter", "position="+position);
		View view=convertView;
		ViewHolder holder;
		if(view==null){
			Log.d("ApplyListAdapter", "convertView==null");
			
			view=LayoutInflater.from(context).inflate(R.layout.apply_list_item, null);
			holder=new ViewHolder();
			holder.nameTv=(TextView) view.findViewById(R.id.proposer_name);
			holder.typeTv=(TextView) view.findViewById(R.id.apply_type);
			holder.timeTv=(TextView) view.findViewById(R.id.apply_time);
			view.setTag(holder);
		}else{
			Log.d("ApplyListAdapter", "convertView!=null");
			holder=(ViewHolder) view.getTag();
		}
		
		holder.nameTv.setText(data.get(position).getRequestUser());
		holder.timeTv.setText(data.get(position).getRequestDate());
		holder.typeTv.setText(data.get(position).getJobTipsDesc());
		return view;
	}
class ViewHolder{
	TextView nameTv;
	TextView typeTv;
	TextView timeTv;
}
}
