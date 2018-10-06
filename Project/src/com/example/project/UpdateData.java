package com.example.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class UpdateData extends Activity implements OnClickListener{
	Button btn;
	EditText et1;
	EditText et2;
	EditText et3;
	EditText et4;
	RadioGroup rg;
	RadioButton rb1;
	RadioButton rb2;
	EditText et5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_data);
		
		et1 = (EditText) findViewById(R.id.uname);
		
		et2 = (EditText) findViewById(R.id.uaddress);
		
		et3 = (EditText) findViewById(R.id.ucontact);
		
		rg = (RadioGroup) findViewById(R.id.ugender);
		
		rb1 = (RadioButton) findViewById(R.id.umale);
		
		rb2 = (RadioButton) findViewById(R.id.ufemale);
		
		et4 = (EditText) findViewById(R.id.ucourse);
		
		et5 = (EditText) findViewById(R.id.uid);
		
		DataHelper dbHelper = new DataHelper(this);
		
		btn = (Button) findViewById(R.id.usubmit);
		btn.setOnClickListener(new OnClickListener() {
			
			SQLiteDatabase sqldb;
			
			@Override
			public void onClick(View v) {
				
				sqldb = openOrCreateDatabase("MyData.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
				AlertDialog.Builder build = new AlertDialog.Builder(UpdateData.this);
				build.setTitle("This is an Alert Dialog");
				build.setMessage("Do you want to Update Data?");
				
				build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(UpdateData.this, "Update Cancel", Toast.LENGTH_LONG).show();
						
					}
				});
				
				build.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						final String name = et1.getText().toString();
						final String address = et2.getText().toString();
						final String contact = et3.getText().toString();
						final String course = et4.getText().toString();
						final String id = et5.getText().toString();
						String gender = "";
						
						
						if(rb1.isChecked())
						{
							gender = "Male";
						}
						else
						{
							gender = "Female";
						}
						
						String sql = "update MyData set Name='"+name+"',Address='"+address+"',Contact='"+contact+"',Gender='"+gender+"',Course='"+course+"' where ID='"+id+"' ";
						SQLiteStatement st;
						st = sqldb.compileStatement(sql);
						long l = st.executeUpdateDelete();
						
						try
						{
							if(l>0)
							{
							Toast.makeText(UpdateData.this, "Update Successful", Toast.LENGTH_LONG).show();
							System.out.println("name :" + name + "\n address :" + address + "\n contact :" + contact + "\n gender :" + gender + "\n course :" + course );
						}
							else
							{
								Toast.makeText(UpdateData.this, "Update Unsuccessful", Toast.LENGTH_LONG).show();
							}
						}
						
						catch(Exception ex)
						{
							
							Log.e("Something Error", ex.toString());
						}
						
					}
				});
				
				build.show();
			}
		});
	}

	@Override
	public void onClick(View v) {
		
		
	}
}
