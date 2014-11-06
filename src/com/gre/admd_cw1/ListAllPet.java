package com.gre.admd_cw1;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.SimpleCursorAdapter;

public class ListAllPet extends ListActivity {

	private DatabaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_all_pet);

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
}
