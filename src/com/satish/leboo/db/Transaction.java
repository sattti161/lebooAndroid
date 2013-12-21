package com.satish.leboo.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.misc.BaseDaoEnabled;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "transactions")
public class Transaction extends BaseDaoEnabled<Transaction, String> {
	
	/**@DatabaseField(id = true, columnName = "id")
	public String id;**/
	
	@DatabaseField(columnName = "user_id")
	public int userId;
	
	@DatabaseField(columnName = "item")
	public String item;
	
	@DatabaseField(columnName = "quantity")
	public int quantity;
	
	@DatabaseField(columnName = "lent_borrowed_flag")
	public boolean lentBorrowedFlag;
	
	@DatabaseField(columnName = "person")
	public String person;
	
	@DatabaseField(columnName = "date_init")
	public String dateInit;
	
	@DatabaseField(columnName = "date_due")
	public String dateDue;
	
	@DatabaseField(columnName = "status")
	public boolean status;

	public Transaction(int userId, String item, int quantity, String person, boolean flag, String initDate,
			String dueDate, boolean status) {
		this.userId = userId;
		this.item = item;
		this.person = person;
		this.quantity = quantity;
		this.lentBorrowedFlag = flag;
		this.dateInit = initDate;
		this.dateDue = dueDate;
		this.status = status;
	}
	
	public Transaction() {
		
	}

}
