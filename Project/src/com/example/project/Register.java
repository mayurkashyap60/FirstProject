package com.example.project;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Register extends Activity implements OnClickListener
{
	Button btn;
	TextView tv;
	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	RadioButton r1;
	RadioButton r2;
	RadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		tv = (TextView) findViewById(R.id.tv);
		et1 = (EditText) findViewById(R.id.name);
		et2 = (EditText) findViewById(R.id.address);
		et3 = (EditText) findViewById(R.id.contact);
		et4 = (EditText) findViewById(R.id.course);
		r1 = (RadioButton) findViewById(R.id.male);
		r2 = (RadioButton) findViewById(R.id.female);
		rg = (RadioGroup) findViewById(R.id.gender);
		btn = (Button) findViewById(R.id.submit);
		btn.setOnClickListener(this);
		
		
	}

	SQLiteDatabase db;
	@Override
	public void onClick(View v) {
	db = openOrCreateDatabase("MyData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
	DataHelper dbHelper = new DataHelper(this);
	
	//Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_LONG).show();
		/*tv = (TextView) findViewById(R.id.tv);
		tv.setText("Table Created");*/
		
		try{
		String gender = "";
		if(r1.isChecked())
		{
			gender = "Male";
		}
		else
		{
			gender = "Female";
		}
		
		final String name = et1.getText().toString();
		final String address = et2.getText().toString();
		final String contact = et3.getText().toString();
		final String course = et4.getText().toString();
	
		String str = "insert into MyData(Name,Address,Contact,Gender,Course)values(?,?,?,?,?)";
		SQLiteStatement st = db.compileStatement(str);
		
		st.bindString(1, name);
		st.bindString(2, address);
		st.bindString(3, contact);
		st.bindString(4, gender);
		st.bindString(5, course);
		
		long l = st.executeInsert();
		if(l>0)
		{
		
		Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_LONG).show();
		System.out.println("Name  " + name + "\n Address  " + address + "\n Contact  " + contact + "\n Gender  " + gender + "\n Course  " + course);
		Cursor cr=db.rawQuery("select * from MyData where Name='"+name+"'", null);
		if (cr.moveToFirst()) {

                       while (cr.isAfterLast() == false) 
                       {
                    	   tv.append("Note : Please Note Your ID :- " + cr.getString(0));
                    	   cr.moveToNext();
                       }

        }

        if ((cr.isClosed() == false) && (cr != null)) {

                       cr.close();
        }
		}
		else
		{
			System.out.println("Error... Something if else");
		}
		}
		catch (Exception ex)
		{
			System.out.println("Error... Something");
		}
		
	}
}
