package com.example.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataHelper {
	private static final String DB_NAME = "MyData.db";
	private static final int DB_VERSION = 1;
	private static final String TABLE_NAME = "MyData";
	
	private Context context;  // object
	
	private SQLiteDatabase sqlDB;
	public DataHelper(Context context) // constructor
	{	
	this.context = context;	// Accepts the context of the invoking class
	
	OpenHelper openHelper = new OpenHelper(this.context);	// Create an instance of the OpenHelper class
	
	this.sqlDB = openHelper.getWritableDatabase();
	
	}
	
	private static class OpenHelper extends SQLiteOpenHelper	// Create an OpenHelper class by extending the SQLiteOpenHelper class
	{
	OpenHelper(Context context) //constructor
	{
	super(context, DB_NAME, null, DB_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER Primary Key, Name TEXT, Address TEXT, Contact TEXT, Gender STRING, Course TEXT)");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	onCreate(db);
	}
	}
}
