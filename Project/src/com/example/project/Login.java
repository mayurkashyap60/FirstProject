package com.example.project;


import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Login extends Activity implements OnClickListener
{
	Button btn;
	EditText ed1;
	EditText ed2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		ed1 = (EditText) findViewById(R.id.namelog);
		
		ed2 = (EditText) findViewById(R.id.contactlog);
		
		btn = (Button) findViewById(R.id.login);
		btn.setOnClickListener(this);
		
		
		
	}
	SQLiteDatabase sqldb;

	
	@Override
	public void onClick(View v) {
		 sqldb = openOrCreateDatabase("MyData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
		 DataHelper dbHelper = new DataHelper(this);
		 
		 try
		 {
		 final String name = ed1.getText().toString();
		 final String contact = ed2.getText().toString();
		 
     	SQLiteStatement st;
     	String str = "select * from MyData Where Name = '"+name+"' and Contact = '"+contact+"' ";
		 st = sqldb.compileStatement(str);
		 long i = st.simpleQueryForLong();
		 
	        if(i>0)
	        {
	        	Intent i1 = new Intent(v.getContext(), Operation.class);
	        	startActivityForResult(i1, 0);
	        }
	        else
	        {
	        	Toast.makeText(Login.this, "Not Authorised", Toast.LENGTH_LONG).show();
	        }
		 }
		 catch(Exception ex)
		 {
			 Toast.makeText(Login.this, "Not Authorised", Toast.LENGTH_LONG).show();
			 Log.e("Something Error...", ex.toString());
		 }
	}
}
