package com.gre.admd_cw1;

import java.security.PublicKey;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.DialogFragment;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class CreatePet extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_pet);
	}
	
	public void displayConfirmDialog(View v) {
		// Get what the user entered
		String petName = ( (EditText) findViewById(R.id.petName) ).getText().toString();
		String petType = ( (Spinner) findViewById(R.id.petType) ).getSelectedItem().toString();
		String gender = ( (Spinner) findViewById(R.id.gender) ).getSelectedItem().toString();
		String ownerName = ( (EditText) findViewById(R.id.ownerName) ).getText().toString();
		String address = ( (EditText) findViewById(R.id.address) ).getText().toString();
		String phone = ( (EditText) findViewById(R.id.phone) ).getText().toString();
		String services = ( (EditText) findViewById(R.id.services) ).getText().toString();
		String startDate = getDateFromDatePicker( (DatePicker) findViewById(R.id.startDate) );
		String endDate = getDateFromDatePicker( (DatePicker) findViewById(R.id.endDate) );
		String comments = ( (EditText) findViewById(R.id.comments) ).getText().toString();
		String whatever = ( (EditText) findViewById(R.id.whatever) ).getText().toString();

		// source : android persist sample in lecture
		// Create and display the Alert dialog
		new AlertDialog.Builder(this)
				.setTitle("Confirm details")
				.setMessage(	"Pet name: " + petName + "\n" +
								"Pet type: " + petType + "\n" +
								"gender: " + gender + "\n" +
								"Owner's name: " + ownerName + "\n" +
								"address: " + address + "\n" +
								"phone: " + phone + "\n" +
								"services: " + services + "\n" +
								"startDate: " + startDate + "\n" +
								"endDate: " + endDate + "\n" +
								"gender: " + comments + "\n" +
								"gender: " + whatever + "\n" )
				.setNeutralButton("Back",
						new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog,	int which) {}})
				.setPositiveButton("Save",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,	int which) {
								// save the details entered if "Save" button clicked
								//saveDetails(strName, strDOB, strEmail);
							}
						}).show();
	}
	
	protected String getDateFromDatePicker(DatePicker datePicker) {
		return datePicker.getYear() + "/" + (datePicker.getMonth()+1) + "/" + datePicker.getDayOfMonth() + "/";
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_pet, menu);
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
