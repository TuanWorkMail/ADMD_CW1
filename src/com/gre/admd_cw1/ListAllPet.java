package com.gre.admd_cw1;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ListAllPet extends ListActivity {

    public final static String PET_ID = "aloha ha ha ha";

	private DatabaseHelper dbHelper;
	
	ListAllPet that = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_all_pet);

		// source: android persist from lecture lecture
		// display all pets in DB
		dbHelper = new DatabaseHelper(this);
		Cursor results = dbHelper.getAllRecords();

		String[] columnNames = { "petName",	"petType" ,"gender","ownerName","phone","address",
				"services",	"startDate","endDate","comments","email","emergency" };
		
		int[] displayNames = { R.id.petName, R.id.petType, R.id.gender, R.id.ownerName, 
				R.id.phone, R.id.address, R.id.services, R.id.startDate, R.id.endDate, 
				R.id.comments, R.id.email, R.id.emergency };

		final SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				this, R.layout.custom_listview, results, columnNames, displayNames);
		setListAdapter(simpleCursorAdapter);

		simpleCursorAdapter.setFilterQueryProvider(new FilterQueryProvider() {

			// search pet
			public Cursor runQuery(CharSequence constraint) {
				Cursor cursor = dbHelper.searchPet(constraint.toString());
				return cursor;
			}
		});

		EditText txtInput = (EditText) findViewById(R.id.searchPet);
		txtInput.addTextChangedListener(new TextWatcher() {

			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				simpleCursorAdapter.getFilter().filter(s);
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				simpleCursorAdapter.getFilter().filter(s.toString());

				simpleCursorAdapter.notifyDataSetChanged();
			}
		});
		
		ListView list = (ListView) findViewById(android.R.id.list);
		
		list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
//                Toast.makeText(getBaseContext(), "position: "+position+",id: "+id, Toast.LENGTH_SHORT).show();
                
                Intent intent = new Intent(that, CreateAndShowReport.class);
                intent.putExtra(PET_ID, id);
                startActivity(intent);
            }
        });
	}
}
