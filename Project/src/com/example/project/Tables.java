package com.example.project;


import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Tables extends Activity implements OnClickListener{

	Button btn;
	TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tables);
		
		btn = (Button) findViewById(R.id.show);
		btn.setOnClickListener(this);
		
		DataHelper dbHelper = new DataHelper(this);
	}

	SQLiteDatabase db;
	@Override
	public void onClick(View v) {
		
db = openOrCreateDatabase("MyData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		
		tv = (TextView) findViewById(R.id.textview);
		tv.setText("");
Cursor cr = db.query("MyData", null, null, null, null, null, null);
		
		if (cr.moveToFirst()) {

            while (cr.isAfterLast() == false) {

            	tv.append("\n ID: " + cr.getString(0) + "\n Name:" + cr.getString(1) + "\n Address: " + cr.getString(2) + "\n Contact: " + cr.getString(3) + "\n Gender: " + cr.getString(4) + "\n Course: " + cr.getString(5));
            	cr.moveToNext();
            	System.out.println(cr);

                                             }
	}
		if ((cr.isClosed() == false) && (cr != null)) {

            cr.close();

}
		
	}
}
