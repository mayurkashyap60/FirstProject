package com.example.project;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Operation extends TabActivity{

	TabHost tabHost;
	TabSpec spec1,spec2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operation);
		
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		spec1 = tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		
		spec1.setIndicator("Show Data", getResources().getDrawable(R.drawable.ic_launcher));
		Intent i1 = new Intent(this, Showdata.class);
		spec1.setContent(i1);
		
		spec2 = tabHost.newTabSpec("Tab 2");
		spec2.setContent(R.id.tab2);
		
		spec2.setIndicator("Update Data", getResources().getDrawable(R.drawable.ic_launcher));
		Intent i2 = new Intent(this, UpdateData.class);
		spec2.setContent(i2);
		
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
	}
}
