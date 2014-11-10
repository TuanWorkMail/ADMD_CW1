package com.gre.admd_cw1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreatePet extends Activity {
	
	protected DatabaseHelper databaseHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_pet);

		// avoid nullpointerexception
		databaseHelper = new DatabaseHelper(this);
	}
	
	String petName;
	String petType;
	String gender;
	String ownerName;
	String address;
	String phone;
	String services;
	String startDate;
	String endDate;
	String comments;
	String email;
	String emergency;
	
	public void displayConfirmDialog(View v) {
		
		int checkEmpty = 	checkEmpty(R.id.petName) + 
							checkEmpty(R.id.ownerName) + 
							checkEmpty(R.id.address) + 
							checkEmpty(R.id.phone);
		if(checkEmpty>0) return;
		
		// Get what the user entered
		petName = ( (EditText) findViewById(R.id.petName) ).getText().toString();
		petType = ( (Spinner) findViewById(R.id.petType) ).getSelectedItem().toString();
		gender = ( (Spinner) findViewById(R.id.gender) ).getSelectedItem().toString();
		ownerName = ( (EditText) findViewById(R.id.ownerName) ).getText().toString();
		address = ( (EditText) findViewById(R.id.address) ).getText().toString();
		phone = ( (EditText) findViewById(R.id.phone) ).getText().toString();
		services = ( (EditText) findViewById(R.id.services) ).getText().toString();
		startDate = getDateFromDatePicker( (DatePicker) findViewById(R.id.startDate) );
		endDate = getDateFromDatePicker( (DatePicker) findViewById(R.id.endDate) );
		comments = ( (EditText) findViewById(R.id.comments) ).getText().toString();
		email = ( (EditText) findViewById(R.id.email) ).getText().toString();
		emergency = ( (EditText) findViewById(R.id.emergency) ).getText().toString();
	
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
								"comments: " + comments + "\n" +
								"email: " + email + "\n" +
								"emergency: " + emergency)
				.setNeutralButton("Change",
						new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog,	int which) {}})
				.setPositiveButton("Create",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,	int which) {

								insertDB();
							}
						}).show();
	}

	protected void insertDB() {
			
		databaseHelper.insertDetails(petName, petType, gender, ownerName, address, phone, services, 
								startDate, endDate, comments, email, emergency);
		
		Toast.makeText(getApplicationContext(), "Pet created successfully", Toast.LENGTH_LONG).show();
		

	}
	
	protected int checkEmpty(int id) {
		EditText editText = (EditText) findViewById(id);
		String string = editText.getText().toString();
		
		if (string != null && string.length() > 0) {
			return 0;
		}
		editText.setError("Must not be blank!");
		return 1;
	}
	
	protected String getDateFromDatePicker(DatePicker datePicker) {
		return datePicker.getYear() + "/" + (datePicker.getMonth()+1) + "/" + datePicker.getDayOfMonth();
	}
}
