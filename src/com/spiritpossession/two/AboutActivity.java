package com.spiritpossession.two;

import com.google.analytics.tracking.android.EasyTracker;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;


public class AboutActivity extends Activity {

	public static String PACKAGE_NAME;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        PACKAGE_NAME = getApplicationContext().getPackageName();
        
        TextView RateSmall = (TextView) findViewById(R.id.RateSmall);
        RateSmall.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				LunchAppRate();	
			}}); 
        
        TextView Rate = (TextView) findViewById(R.id.Rate);
        Rate.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				LunchAppRate();	
			}
		});
}
    
    
   


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    private void LunchAppRate(){
    	
    	Uri uri = Uri.parse("market://details?id=" + PACKAGE_NAME);
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
		try {
		  startActivity(goToMarket);
		} catch (ActivityNotFoundException e) {
		  Toast.makeText(getApplicationContext(), "Couldn't launch the market", Toast.LENGTH_LONG).show();
		}	
    	
    }
    
    
    @Override
    public void onStart() {
      super.onStart();
      // The rest of your onStart() code.
      EasyTracker.getInstance(this).activityStart(this);  // Add this method.
    }

    @Override
    public void onStop() {
      super.onStop();
      // The rest of your onStop() code.
      EasyTracker.getInstance(this).activityStop(this);  // Add this method.
    }
}
