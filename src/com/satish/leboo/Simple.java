package com.satish.leboo;



import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class Simple extends ListActivity implements OnItemSelectedListener {

	public static ListAdapterTransaction allTransactions;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_layout);
		initDragDownList();
		allTransactions = new ListAdapterTransaction(getApplicationContext());
		setListAdapter(allTransactions);
		//setListAdapter(new ListAdapterBrandImageName(getActivity()));
	}
	

	Spinner spinner;
	private static final String[] paths = { "All Items", "Borrowed Items", "Lent Items", "Pending Items", "Returned Items"};

	private void initDragDownList() {

		spinner = (Spinner) findViewById(R.id.spinner);


		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Simple.this,
				android.R.layout.simple_spinner_item, paths);

		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
	}

	public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
		switch (position) {
		case 0:
			// What ever you want to happen when item selected
			break;
		case 1:
			// What ever you want to happen when item selected
			break;
		case 2:
			// What ever you want to happen when item selected
			break;

		}   
	}


	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}