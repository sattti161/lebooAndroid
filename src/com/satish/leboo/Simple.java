package com.satish.leboo;



import android.R;
import android.app.ListActivity;
import android.os.Bundle;

public class Simple extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.sea);
		ListAdapterTransaction list = new ListAdapterTransaction(getApplicationContext());
		setListAdapter(list);
		//setListAdapter(new ListAdapterBrandImageName(getActivity()));
	}
}