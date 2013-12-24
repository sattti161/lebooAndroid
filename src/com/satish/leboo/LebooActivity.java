package com.satish.leboo;

import hello.tab.tabhello.R;

import java.util.List;

import com.satish.leboo.db.DatabaseManager;
import com.satish.leboo.db.Transaction;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class LebooActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setUpDb();
        
        Resources res = getResources();
        Intent i = new Intent(this,Simple.class);
        Intent datePickerActivity = new Intent(this, DatePickerActivity.class);
        Intent lentItemActivity = new Intent(this, LentItemActivity.class);
        Intent borrowedItemActivity = new Intent(this, BorrowedItemActivity.class);
        TabHost mTabHst = getTabHost();
        
        mTabHst.addTab(mTabHst.newTabSpec("tab_test1").setIndicator("Home",res.getDrawable(
        		R.drawable.transfer_down_up)).setContent(i));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test2").setIndicator("I Lent",res.getDrawable(
        		R.drawable.arrow_up)).setContent(lentItemActivity));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test3").setIndicator("I Borrowed",res.getDrawable(
        		R.drawable.arrow_down)).setContent(borrowedItemActivity));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test4").setIndicator("Gen",res.getDrawable(
        		R.drawable.three)).setContent(datePickerActivity));
        
        mTabHst.setCurrentTab(0);
        
        
    }
    
    public static List <Transaction> transactions;
    private void setUpDb() {
    	DatabaseManager.init(this);
    	createNewWishList();
    	transactions = DatabaseManager.getInstance().getAllWishLists();
    	//final List <Transaction> wishLists = DatabaseManager.getInstance().getAllWishLists();
    }
    
    private void createNewWishList() {
    	Transaction l = new Transaction(1, "Book", 1, "Satish Kumar", false, "2013-12-20", "2013-12-20", false);
        DatabaseManager.getInstance().addWishList(l);
        l = new Transaction(1, "Laptop Charger", 1, "Lakavath Satish Kumar", false, "2013-12-20", "2013-12-20", false);
        DatabaseManager.getInstance().addWishList(l);
        l = new Transaction(1, "Money", 200, "Satish Kumar Lakavath", false, "2013-12-20", "2013-12-20", false);
        DatabaseManager.getInstance().addWishList(l);
    }
}