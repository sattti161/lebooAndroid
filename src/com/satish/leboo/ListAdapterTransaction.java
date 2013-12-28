package com.satish.leboo;

import com.satish.leboo.db.DatabaseManager;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
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
			holder.dates = (TextView) convertView.findViewById(R.id.dates);
			holder.updateButton = (Button) convertView.findViewById(R.id.modify_button);
			//holder.dateDue = (TextView) convertView.findViewById(R.id.date_due);

			convertView.setTag(holder);
		} 
		else
			holder = (ViewHolder) convertView.getTag();
		
		if (position % 2 == 0)
			convertView.setBackgroundColor(Color.parseColor("#ADD8E6"));
		else
			convertView.setBackgroundColor(Color.parseColor("#FBBBB9"));

		if (LebooActivity.transactions.get(position).lentBorrowedFlag) {
			holder.item.setText("Lent : " + LebooActivity.transactions.get(position).item);
			holder.person.setText("To : " + LebooActivity.transactions.get(position).person);
			//holder.updateButton.setText("I Got Back");
		} else {
			holder.item.setText("Borrowed : " + LebooActivity.transactions.get(position).item);
			holder.person.setText("From : " + LebooActivity.transactions.get(position).person);
			//holder.updateButton.setText("I Gave Back");
		}
		
		if (LebooActivity.transactions.get(position).status)
			holder.updateButton.setText("Due");
		else holder.updateButton.setText("Returned");
			
		holder.updateButton.setTag(new Integer(position));
		initUpdateButton(holder);
		holder.dates.setText("On : " + LebooActivity.transactions.get(position).dateInit +
				"   Due : " + LebooActivity.transactions.get(position).dateDue);



		//holder.dateDue.setText("  Due Date : " + LebooActivity.transactions.get(position).dateDue);
		//convertView.setTag(new Integer(position));
		return convertView;
	}

	
	private void initUpdateButton(ViewHolder holder) {
		
		holder.updateButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				LebooActivity.transactions.get((Integer) v.getTag()).status = !LebooActivity.transactions.get(
						(Integer) v.getTag()).status;
				DatabaseManager.getInstance().updateEntry(LebooActivity.transactions.get((Integer) v.getTag()));
				Simple.listAdapter.notifyDataSetChanged();
			}
		});
	}
	
	static class ViewHolder {
		TextView item;
		TextView person;
		TextView dates;
		Button updateButton;
		int position;
		//TextView dateDue;
	}



	@Override
	public int getCount() {
		return LebooActivity.transactions.size();
	}

	@Override
	public Object getItem(int position) {
		return LebooActivity.transactions.get(position);
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
