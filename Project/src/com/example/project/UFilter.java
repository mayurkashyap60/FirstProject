package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class UFilter extends Activity implements OnClickListener{

	Button btn;
	EditText ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ufilter);
		
		ed = (EditText) findViewById(R.id.uid);
		btn = (Button) findViewById(R.id.btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			SQLiteDatabase db;
			@Override
			public void onClick(View v) {
				
			db = openOrCreateDatabase("MyData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
			
			final String id = ed.getText().toString();
			try
			{
				SQLiteStatement st;
				String str = "select * from MyData where ID = '"+id+"' ";
				st = db.compileStatement(str);
				
				long l = st.simpleQueryForLong();
				
				if(l>0)
				{
					Intent i = new Intent(v.getContext(), UpdateData.class);
					startActivityForResult(i, 0);
					
				}
				else
				{
					Toast.makeText(UFilter.this, "Not Registered", Toast.LENGTH_LONG).show();
				}
			}
			catch(Exception ex)
			{
				Log.e("Something error...", ex.toString());
				Toast.makeText(UFilter.this, "Not Registered", Toast.LENGTH_LONG).show();
			}
			}
		});
	}

	@Override
	public void onClick(View v) {
		
		
	}
}
