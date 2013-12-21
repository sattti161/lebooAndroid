package com.satish.leboo;

import hello.tab.tabhello.R;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapterTransaction extends BaseAdapter {	
	//private static ArrayList <BrandData> dataHolders = new ArrayList <BrandData> ();
	private LayoutInflater l_Inflater;


	public ListAdapterTransaction(Context context) {//, ArrayList <BrandData> data_holders) {
		//dataHolders = data_holders;
		l_Inflater = LayoutInflater.from(context);
		//DataHolder.initBrandImageMap();
	}


	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = l_Inflater.inflate(R.layout.my_list_item_layout, null);
			//convertView = l_Inflater.inflate(null, null);
			holder = new ViewHolder();
			holder.item = (TextView) convertView.findViewById(R.id.item_name);
			holder.person = (TextView) convertView.findViewById(R.id.person_name);
			holder.dateInit = (TextView) convertView.findViewById(R.id.date_init);
			holder.dateDue = (TextView) convertView.findViewById(R.id.date_due);

			convertView.setTag(holder);
		} 
		else
			holder = (ViewHolder) convertView.getTag();
		
		if (position % 3 == 0)
			convertView.setBackgroundColor(Color.parseColor("#FBBBB9"));
		else if (position % 3 == 1)
			convertView.setBackgroundColor(Color.parseColor("#82CAFF"));
		else 
			convertView.setBackgroundColor(Color.parseColor("#4CC417"));
		
		holder.item.setText("Item : " + HelloTabActivity.transactions.get(position).item);
		holder.person.setText("Lent To : " + HelloTabActivity.transactions.get(position).person);
		holder.dateInit.setText("Lent On : " + HelloTabActivity.transactions.get(position).dateInit);
		holder.dateDue.setText("  Due Date : " + HelloTabActivity.transactions.get(position).dateDue);
		
		return convertView;
	}

	static class ViewHolder {
		TextView item;
		TextView person;
		TextView dateInit;
		TextView dateDue;
	}



	@Override
	public int getCount() {
		return HelloTabActivity.transactions.size();
	}

	@Override
	public Object getItem(int position) {
		return HelloTabActivity.transactions.get(position);
		// TODO Auto-generated method stub
		/**Iterator it = DataHolder.BRAND_IMAGE_MAP.entrySet().iterator(); 
		for (int i = 0; i < position - 1; i++) it.next();
		return it.next(); **/
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

}
