package com.gre.admd_cw1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class CreateAndShowReport extends ListActivity {

	private DatabaseHelper databaseHelper;
	
	long petId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_and_show_report);
		
	    databaseHelper = new DatabaseHelper(this);
		
		// get petId from listAllPet
	    Intent intent = getIntent();
	    petId = intent.getLongExtra(ListAllPet.PET_ID, 9999);
	    
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
 		
 		// make the listView inside scrollView scrollable
 		// http://stackoverflow.com/questions/6210895/listview-inside-scrollview-is-not-scrolling-on-android
 		ListView lv = (ListView)findViewById(android.R.id.list);  // your listview inside scrollview
 		lv.setOnTouchListener(new ListView.OnTouchListener() {
 		        @Override
 		        public boolean onTouch(View v, MotionEvent event) {
 		            int action = event.getAction();
 		            switch (action) {
 		            case MotionEvent.ACTION_DOWN:
 		                // Disallow ScrollView to intercept touch events.
 		                v.getParent().requestDisallowInterceptTouchEvent(true);
 		                break;

 		            case MotionEvent.ACTION_UP:
 		                // Allow ScrollView to intercept touch events.
 		                v.getParent().requestDisallowInterceptTouchEvent(false);
 		                break;
 		            }

 		            // Handle ListView touch events.
 		            v.onTouchEvent(event);
 		            return true;
 		        }
 		    });
	}
	
	public void createReport(View v) {
		
		DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
		String date = datePicker.getYear() + "/" + (datePicker.getMonth()+1) + "/" + datePicker.getDayOfMonth();
		
		TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
		String time = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
		
		String notes = ( (EditText) findViewById(R.id.editTextNotes) ).getText().toString();
		String name = ( (EditText) findViewById(R.id.editTextName) ).getText().toString();
		
		databaseHelper.insertReport(String.valueOf(petId), date, time, notes, name);

		Toast.makeText(getApplicationContext(), "Report created successfully", Toast.LENGTH_LONG).show();
		
		finish();
		startActivity(getIntent());
	}
}
