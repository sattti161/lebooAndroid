package com.satish.leboo;

import hello.tab.tabhello.R;

import java.util.Calendar;

import com.satish.leboo.db.DatabaseManager;
import com.satish.leboo.db.Transaction;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;


public class LentItemActivity extends Activity {
	
	EditText lentDate, dueDate;
	Button updateLentItemButton;
	static final int LENT_DATE_ID = 998, DUE_DATE_ID = 999;
	private int year, month, day;
	private int selectedDatePicker = 0; // 0 - none, 1 - lent date, 2 - due date
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lent_item_relative);
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		addDatePickerListeners();
		addListenerOnButton();
	}
	
	public void addDatePickerListeners() {

		lentDate = (EditText) findViewById(R.id.lent_date);

		lentDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(LENT_DATE_ID);

			}

		});

		
		dueDate = (EditText) findViewById(R.id.due_date);

		dueDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(DUE_DATE_ID);

			}

		});

	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case LENT_DATE_ID:
			selectedDatePicker = 1;
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		case DUE_DATE_ID:
			selectedDatePicker = 2;
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, year, month,
					day);
		}
		return null;
	}

	
	public void addListenerOnButton() {

		updateLentItemButton = (Button) findViewById(R.id.updateLentItemButton);

		updateLentItemButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Transaction l = new Transaction(1, 
						((EditText)findViewById(R.id.lent_item)).getText().toString(),
						1, 
						((EditText)findViewById(R.id.lent_to_person)).getText().toString(),
						false, 
						((EditText)findViewById(R.id.lent_date)).getText().toString().replace('/', '-'),
						((EditText)findViewById(R.id.due_date)).getText().toString().replace('/', '-'),
						false);
		        DatabaseManager.getInstance().addWishList(l);
		        onCreate(null);
			}

		});

	}

	
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			year = selectedYear;
			month = selectedMonth;
			day = selectedDay;
			populateDateField();
		}
	};
	
	
	private void populateDateField() {
		if (selectedDatePicker == 1) 
			lentDate.setText(day + "/" + (month + 1) + "/" + year);
		if (selectedDatePicker == 2) 
			dueDate.setText(day + "/" + (month + 1) + "/" + year);
	}
}