package com.gre.admd_cw1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	DatabaseHelper databaseHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		databaseHelper = new DatabaseHelper(this);
	}

	public void displayCreatePet(View v) {
		startActivity(new Intent(this, CreatePet.class));
	}

	public void searchPet(View v) {
		startActivity(new Intent(this, ListAllPet.class));
	}
	
	public void deleteAll(View v) {
		
		new AlertDialog.Builder(this)
		.setTitle("Confirm delete")
		.setMessage("Are you sure? This will delete everything")
		.setNeutralButton("No",
				new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog,	int which) {}})
				.setPositiveButton("Delete",
						new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog,	int which) {

						deleteAllDatabase();
					}
				}).show();
	}
	
	protected void deleteAllDatabase() {
		
		databaseHelper.deleteAllRecords();

		Toast.makeText(getApplicationContext(), "Delete all reports and pets successful", Toast.LENGTH_LONG).show();
	}
}
