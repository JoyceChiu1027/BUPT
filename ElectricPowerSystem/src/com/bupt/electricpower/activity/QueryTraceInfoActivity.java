package com.bupt.electricpower.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.bupt.electricpower.R;
import com.bupt.electricpower.util.CodeConstants;

public class QueryTraceInfoActivity extends AbstractBaseActivity{
    private TextView trackStateTv;
    private TextView leaveDateTv;
    private TextView backDateTv;
    private TextView  causeTv;
    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onCreate(savedInstanceState);
    	initView();
    	String track=getIntent().getStringExtra(CodeConstants.TRACK);
    	String leaveDate=getIntent().getStringExtra(CodeConstants.LEAVE_DATE);
    	String backDate=getIntent().getStringExtra(CodeConstants.BACK_DATE);
    	String cause=getIntent().getStringExtra(CodeConstants.CAUSE);
    	trackStateTv.setText(track);
    	leaveDateTv.setText(leaveDate);
    	backDateTv.setText(backDate);
    	causeTv.setText(cause);
      }
    private void initView(){
    	trackStateTv=(TextView) findViewById(R.id.track_state);
    	leaveDateTv=(TextView) findViewById(R.id.leave_date);
    	backDateTv=(TextView) findViewById(R.id.back_date);
    	causeTv=(TextView) findViewById(R.id.cause);
    }
	@Override
	protected boolean isNeedInitBack() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	protected void setLayout() {
		// TODO Auto-generated method stub
		setContentView(R.layout.query_trace_info_activity);
	}

}
