package com.satish.leboo;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.satish.leboo.db.DatabaseManager;
import com.satish.leboo.db.Transaction;


public class BorrowedItemActivity extends Activity {

	EditText borrowedDate, dueDate;
	Button updateBorrowedItemButton;
	static final int LENT_DATE_ID = 998, DUE_DATE_ID = 999;
	private int year, month, day;
	private int selectedDatePicker = 0; // 0 - none, 1 - lent date, 2 - due date

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.borrowed_item_relative);
		final Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		addOnFocusListener();
		addDatePickerListeners();
		addListenerOnButton();
	}


	private void addOnFocusListener() {
		borrowedDate = (EditText) findViewById(R.id.borrowed_date);


		borrowedDate.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) {
					showDialog(LENT_DATE_ID); 
				}else{
					//Hide your calender here
				}
			}
		});



		dueDate = (EditText) findViewById(R.id.due_date_borrowed);


		dueDate.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus) {
					showDialog(DUE_DATE_ID); 
				}else{
					//Hide your calender here
				}
			}
		});
	}


	public void addDatePickerListeners() {

		borrowedDate = (EditText) findViewById(R.id.borrowed_date);

		borrowedDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				showDialog(LENT_DATE_ID);

			}

		});


		dueDate = (EditText) findViewById(R.id.due_date_borrowed);

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

		updateBorrowedItemButton = (Button) findViewById(R.id.updateBorrowedItemButton);

		updateBorrowedItemButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Transaction l = new Transaction(1, 
						((EditText)findViewById(R.id.borrowed_item)).getText().toString(),
						1, 
						((EditText)findViewById(R.id.borrowed_from_person)).getText().toString(),
						false, 
						((EditText)findViewById(R.id.borrowed_date)).getText().toString().replace('/', '-'),
						((EditText)findViewById(R.id.due_date_borrowed)).getText().toString().replace('/', '-'),
						false);
				DatabaseManager.getInstance().addWishList(l);
				LebooActivity.transactions.add(0, l);
				Simple.listAdapter.notifyDataSetChanged();
				LebooActivity.mTabHst.setCurrentTab(0);
				((EditText)findViewById(R.id.borrowed_item)).setText("");
				((EditText)findViewById(R.id.borrowed_from_person)).setText("");
				((EditText)findViewById(R.id.borrowed_date)).setText("");
				((EditText)findViewById(R.id.due_date_borrowed)).setText("");
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
			borrowedDate.setText(day + "/" + (month + 1) + "/" + year);
		if (selectedDatePicker == 2) 
			dueDate.setText(day + "/" + (month + 1) + "/" + year);
	}
}