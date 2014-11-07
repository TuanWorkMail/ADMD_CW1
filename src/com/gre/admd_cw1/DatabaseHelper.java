package com.gre.admd_cw1;

// source : android persist sample in lecture

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "ADMD_CW1";
	private SQLiteDatabase database;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1); // 1 is database version number

		// get a reference to our database which we'll use later to insert
		database = getWritableDatabase();
	}

	@Override
	// Automatically called if the database needs creating
	public void onCreate(SQLiteDatabase db) {
	
		db.execSQL("CREATE TABLE pets (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + 	"petName TEXT," +
																					"petType TEXT," +
																					"gender TEXT," +
																					"ownerName TEXT," +
																					"phone INTEGER," +
																					"address TEXT," +
																					"services TEXT," +
																					"startDate TEXT," +
																					"endDate TEXT," +
																					"comments TEXT," +
																					"email TEXT," +
																					"emergency TEXT);");
		
		db.execSQL("CREATE TABLE skills (_id INTEGER PRIMARY KEY AUTOINCREMENT, person_id INTEGER, skill TEXT);");

		db.execSQL("INSERT INTO pets (petName,petType,gender,ownerName,phone,address,services,startDate,endDate,comments,email,emergency)" +
				"VALUES ('Lucky','Cat','Male pet','John Smith','11222333444','1 Manhattan Ave','Feed','2014/11/06','2014/11/07','aware biting','john@gmail.com','phone');");
		db.execSQL("INSERT INTO pets (petName,petType,gender,ownerName,phone,address,services,startDate,endDate,comments,email,emergency)" +
				"VALUES ('Fluffy','Dog','Female pet','Troy Allens','99888777666','1 5th Ave','Exercise','2014/11/05','2014/11/08','also feed','troy@yahoo.com','email');");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.e("database version changed:", DATABASE_NAME + " new version:" + newVersion	+ ". Delete all tables");
		db.execSQL("DROP TABLE IF EXISTS pets");
		db.execSQL("DROP TABLE IF EXISTS skills");
		onCreate(db);
	}

	public long insertDetails(String petName, String petType, String gender, String ownerName, 
			String address, String phone, String services, String startDate, String endDate, 
			String comments, String email, String emergency) {
		
		ContentValues rowValues = new ContentValues();

		// Assemble row of data in the ContentValues object
		rowValues.put("petName", petName);
		rowValues.put("petType", petType);
		rowValues.put("gender", gender);
		rowValues.put("ownerName", ownerName);
		rowValues.put("address", address);
		rowValues.put("phone", phone);
		rowValues.put("services", services);
		rowValues.put("startDate", startDate);
		rowValues.put("endDate", endDate);
		rowValues.put("comments", comments);
		rowValues.put("email", email);
		rowValues.put("emergency", emergency);

		// save the id of the person just inserted
		long personId = database.insertOrThrow("pets", null, rowValues);

		return personId;

	}

	public Cursor getAllRecords() {
		return database.query("pets", null, null, null, null, null, null);
	}

	public Cursor searchPet(String searchKeyword) {
		
		return database.query("pets", null, "petName like ?",
				new String[] { searchKeyword + "%" }, null, null, "petName");

	}

	// delete all the rows of the details table
	public void deleteAllRecords() {
		database.delete("skills", null, null);
		database.delete("pets", null, null);
	}
}
