package com.insta.annuaire;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.R.array;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "contact";

	// Login table name
	private static final String TABLE_USER = "user";

	// Login Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_LASTNAME = "nom";
	private static final String KEY_FIRSTNAME = "prenom";
	private static final String KEY_PHONE = "telephone";
	private static final String KEY_EMAIL = "mail";
	private static final String KEY_PROFIL = "profil";
	private static final String KEY_PROMO = "promo";
	private static final String KEY_PHOTO = "photo";

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
				+ KEY_ID + " INTEGER," 
				+ KEY_LASTNAME + " TEXT,"
				+ KEY_FIRSTNAME + " TEXT,"
				+ KEY_PROFIL + " TEXT,"
				+ KEY_PROMO + " TEXT,"
				+ KEY_PHOTO + " TEXT" + ")";
		db.execSQL(CREATE_USER_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in database
	 * */
	public void addUser(int id,String nom, String prenom, String profil, String promo, String photo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, id);
		values.put(KEY_LASTNAME, nom); // Name
		values.put(KEY_FIRSTNAME, prenom); // Email
		values.put(KEY_PROFIL, profil); // Email
		values.put(KEY_PROMO, promo); // Created At
		values.put(KEY_PHOTO, photo); // Created At
		
		// Inserting Row
		db.insert(TABLE_USER, null, values);
		db.close(); // Closing database connection
	}


	/**
	 * Getting user login status
	 * return true if rows are there in table
	 * */
	public int getRowCount() {
		String countQuery = "SELECT  * FROM " + TABLE_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();
		
		// return row count
		return rowCount;
	}
	
	/**
	 * Getting user login status
	 * return true if rows are there in table
	 * */
	public ArrayList<Contact> getUser() 
	{
		String Query = "SELECT  * FROM " + TABLE_USER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(Query, null);
		
//		if (cursor != null) {
//			cursor.moveToFirst();
//        }
		
		ArrayList<Contact> array = new ArrayList<Contact>();
		
		while(cursor.moveToNext())
		{
			int id =  cursor.getInt(cursor.getColumnIndex(KEY_ID));
		    String nom = cursor.getString(cursor.getColumnIndex(KEY_LASTNAME));
		    String prenom = cursor.getString(cursor.getColumnIndex(KEY_FIRSTNAME));
		    String profil = cursor.getString(cursor.getColumnIndex(KEY_PROFIL));
		    String promo = cursor.getString(cursor.getColumnIndex(KEY_PROMO));
		    String photo = cursor.getString(cursor.getColumnIndex(KEY_PHOTO));
		    
		    Contact user = new Contact( id, nom, prenom, profil, promo, photo );
		    
		    array.add(user);
		}
		
		db.close();
		cursor.close();
		
		// return row count
		return array;
	}
	
	/**
	 * Getting user login status
	 * return true if rows are there in table
	 * */
	public void setUser(Contact object) 
	{
		String Query = "INSERT INTO " + TABLE_USER + " VALUES ("
				+ object.getNom()+ ", "
				+ object.getPrenom() + ", "
				+ object.getPromo() + ", "
				+ object.getProfil() + ", "
				+ object.getPhoto() + ", "
				+ null + " );";
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(Query, null);
		
		db.close();
		cursor.close();
		
	}
	
	/**
	 * Re crate database
	 * Delete all tables and create them again
	 * */
	public void resetTables(){
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_USER, null, null);
		db.close();
	}

}
