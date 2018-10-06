package com.example.project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Showdata extends Activity implements OnClickListener{

	
	Button btn;
	EditText ed;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showdata);
		
		ed = (EditText) findViewById(R.id.sname);
		
		btn = (Button) findViewById(R.id.sdone);
		btn.setOnClickListener(this);
	}

	SQLiteDatabase db;
	@Override
	public void onClick(View v) {
		
		db = openOrCreateDatabase("MyData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		DataHelper dbHelper = new DataHelper(this);
		try
		{
		final String id = ed.getText().toString();
		
		
			TextView tv = (TextView) findViewById(R.id.stv);
			tv.setText("");
			
			Cursor cr=db.rawQuery("select * from MyData where ID='"+id+"'", null);
			if (cr.moveToFirst()) {

                           while (cr.isAfterLast() == false) 
                           {
                        	   tv.append("\n ID:-" + cr.getString(0) + "\n Name: " + cr.getString(1) + "\n Address: " + cr.getString(2) + "\n Contact :" + cr.getString(3) + "\n Gender :" + cr.getString(4) + "\n Course :" + cr.getString(5) );
                        	   cr.moveToNext();
                           }

            }

            if ((cr.isClosed() == false) && (cr != null)) {

                           cr.close();
            }
			
		}
		
		catch(Exception ex)
		{
			Log.e("Something error...", ex.toString());
			Toast.makeText(Showdata.this, "Not In Database", Toast.LENGTH_LONG).show();
		}
	}
}
