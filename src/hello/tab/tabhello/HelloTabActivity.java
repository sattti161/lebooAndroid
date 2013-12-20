package hello.tab.tabhello;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class HelloTabActivity extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources();
        Intent i = new Intent(this,Simple.class);
        Intent lentItemActivity = new Intent(this,LentItemActivity.class);
        TabHost mTabHst = getTabHost();
        
        mTabHst.addTab(mTabHst.newTabSpec("tab_test1").setIndicator("One",res.getDrawable(
        		R.drawable.one)).setContent(i));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test2").setIndicator("Two",res.getDrawable(
        		R.drawable.two)).setContent(lentItemActivity));
        mTabHst.addTab(mTabHst.newTabSpec("tab_test3").setIndicator("Three",res.getDrawable(
        		R.drawable.three)).setContent(i));
        
        mTabHst.setCurrentTab(0);
        
    }
}