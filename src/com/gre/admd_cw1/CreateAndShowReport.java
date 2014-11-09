package com.gre.admd_cw1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAndShowReport extends ListActivity {

	private DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_and_show_report);
		
	    databaseHelper = new DatabaseHelper(this);
		
		// get petId from listAllPet
	    Intent intent = getIntent();
	    long petId = intent.getLongExtra(ListAllPet.PET_ID, 9999);
	    
	    // display pet's name
	    Cursor cursor = databaseHelper.getPetName(petId);
	    TextView petName = (TextView) findViewById(R.id.petName);
	    while (cursor.moveToNext()) {
	    	petName.append(cursor.getString(0)); // 0 is the first column 
	    }
	    
	    // display all pet's report 
	    // remember to use ListActivity need to change listView id to @android:id/list, if have 2 activity use it the 2nd will not throw exception
 		Cursor results = databaseHelper.getPetReports(petId);

 		String[] columnNames = { "date","time","notes","sitterName" };
 		
 		int[] displayNames = { R.id.date, R.id.time, R.id.notes, R.id.name };

 		final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
 				this, R.layout.custom_listview_reports, results, columnNames, displayNames);
 		setListAdapter(simpleCursorAdapter);
 		
	}
}
