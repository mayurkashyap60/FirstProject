package com.example.project;



import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import android.widget.TabHost.TabSpec;

public class MainActivity extends TabActivity {

	TabHost tabHost;
	TabSpec spec1, spec2;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		spec1 = tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		
		spec1.setIndicator("Login", getResources().getDrawable(R.drawable.ic_launcher));
		Intent i1 = new Intent(this, Login.class);
		spec1.setContent(i1);
		
		spec2 = tabHost.newTabSpec("Tab 2");
		spec2.setContent(R.id.tab2);
		
		spec2.setIndicator("Register", getResources().getDrawable(R.drawable.ic_launcher));
		Intent i2 = new Intent(this, Register.class);
		spec2.setContent(i2);
		
		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
	}
}
