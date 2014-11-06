package com.gre.admd_cw1;

import android.support.v7.app.ActionBarActivity;
import android.app.ListActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;

public class ShowAllPets extends ActionBarActivity {

	private DatabaseHelper dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_all_pets);
		
		// source: android persist from lecture lecture
		// display all pets in DB
		dbHelper = new DatabaseHelper(this);
		Cursor results = dbHelper.getAllRecords();

		String[] columnNames = { "petName",	"petType" ,"gender","ownerName","phone","address" +
				"services",	"startDate","endDate","comments","email","emergency" };
		int[] displayNames = { R.id.petName, R.id.petType, R.id.gender, R.id.ownerName, 
				R.id.phone, R.id.address, R.id.services, R.id.startDate, R.id.endDate, 
				R.id.comments, R.id.email, R.id.emergency };

		final SimpleCursorAdapter records = new SimpleCursorAdapter(this,
				R.layout.custom_listview, results, columnNames, displayNames);
		setListAdapter(records);
		// displayData();

		records.setFilterQueryProvider(new FilterQueryProvider() {

			public Cursor runQuery(CharSequence constraint) {
				Log.d("xxxx", "runQuery constraint:" + constraint);
				// uri, projection, and sortOrder might be the same as previous
				// but you might want a new selection, based on your filter
				// content (constraint)
				Cursor cur = dbHelper.getFilteredRecords(constraint.toString());
				return cur; // now your adapter will have the new filtered
							// content
			}
		});

		EditText txtInput = (EditText) findViewById(R.id.editTextFilter);
		txtInput.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				records.getFilter().filter(s);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				records.getFilter().filter(s.toString());

				records.notifyDataSetChanged();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.show_all_pets, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
