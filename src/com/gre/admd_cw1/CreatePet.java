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
	
	private void displayConfirmDialog(View v) {
		// Get what the user entered
		String petName = ( (EditText) findViewById(R.id.petName) ).getText().toString();
		DatePicker dobInput = (DatePicker) findViewById(R.id.petName);
		EditText emailInput = (EditText) findViewById(R.id.petName);

		// final so we can reference them in the anonymous inner class below

		final String strName = emailInput.getText().toString();
		final String strDOB = dobInput.getDayOfMonth() + "/"
				+ (dobInput.getMonth() + 1) + "/" + dobInput.getYear();
		final String strEmail = emailInput.getText().toString();

		// Create and display the Alert dialog
		new AlertDialog.Builder(this)
				.setTitle("   Details entered")
				.setMessage(
						" Details entered:\n " + strName + "\n " + strDOB
								+ "\n " + strEmail)
				.setNeutralButton("Back",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,	int which) {
								// do nothing - it will just close when clicked
							}
						})
				.setPositiveButton("Save",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,	int which) {
								// save the details entered if "Save" button clicked
								//saveDetails(strName, strDOB, strEmail);
							}
						}).show();
	}
	
//	// copy from [http://developer.android.com/guide/topics/ui/controls/pickers.html]
//	public static class DatePickerFragment extends DialogFragment
//	implements DatePickerDialog.OnDateSetListener {
//
//		protected String whichDate;
//		
//		public DatePickerFragment(String _whichDate) {
//			whichDate = _whichDate;
//		}
//		
//		@Override
//		public Dialog onCreateDialog(Bundle savedInstanceState) {
//			// Use the current date as the default date in the picker
//			final Calendar c = Calendar.getInstance();
//			int year = c.get(Calendar.YEAR);
//			int month = c.get(Calendar.MONTH);
//			int day = c.get(Calendar.DAY_OF_MONTH);
//
//			// Create a new instance of DatePickerDialog and return it
//			return new DatePickerDialog(getActivity(), this, year, month, day);
//		}
//
//		public void onDateSet(DatePicker view, int year, int month, int day) {
//			// Do something with the date chosen by the user
//			switch (whichDate) {
//			case "start":
//				
//				break;
//			case "end":
//				
//				break;
//
//			default:
//				break;
//			}
//		}
//	}	
//	public void showDatePickerDialog(View v, String whichDate) {
//	    DialogFragment newFragment = new DatePickerFragment(whichDate);
//	    newFragment.show(getSupportFragmentManager(), "datePicker");
//	}
//	
//	// handle start/end date button onClick event
//	public void getStartDate(View v){
//		showDatePickerDialog(v, "start");
//	}
//	public void getEndDate(View v){
//		showDatePickerDialog(v, "end");
//	}
	
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
