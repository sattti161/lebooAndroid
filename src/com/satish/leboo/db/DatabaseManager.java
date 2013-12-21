package com.satish.leboo.db;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;

import com.satish.leboo.db.Transaction;

public class DatabaseManager {

	static private DatabaseManager instance;

	static public void init(Context ctx) {
		if (null == instance) {
			instance = new DatabaseManager(ctx);
		}
	}

	static public DatabaseManager getInstance() {
		return instance;
	}

	private DbHelper helper;
	private DatabaseManager(Context ctx) {
		helper = new DbHelper(ctx);
	}

	private DbHelper getHelper() {
		return helper;
	}

	public List<Transaction> getAllWishLists() {
		List<Transaction> wishLists = null;
		try {
			wishLists = getHelper().getWishListDao().queryForAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishLists;
	}
	
	public void addWishList(Transaction l) {
        try {
            getHelper().getWishListDao().create(l);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
