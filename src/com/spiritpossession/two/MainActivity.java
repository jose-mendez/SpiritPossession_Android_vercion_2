package com.spiritpossession.two;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.analytics.tracking.android.EasyTracker;

public class MainActivity extends Activity {

	
	   /*     Button continuar = (Button)findViewById(R.id.continuar);
	      final  TextView tv = (TextView)findViewById(R.id.texto_intro);
	        continuar.setOnClickListener(new OnClickListener()
	        {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					tv.setText("pepe");
					
					Intent myIntent = new Intent(getApplicationContext(), AboutActivity.class);
					myIntent.putExtra("key", "este es mi parametro"); //Optional parameters
					MainActivity.this.startActivity(myIntent);
				}}
	        );*/

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----------------------------------------------------------------------------------------
        Button btnAbout = (Button)findViewById(R.id.btnAbout);
        btnAbout.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				Intent inten = new Intent(MainActivity.this, AboutActivity.class);
				startActivity(inten);
			}});
        //----------------------------------------------------------------------------------------
        
        Button btnContinue = (Button)findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				
				Intent inten = new Intent(MainActivity.this, QuestionActivity.class);
				startActivity(inten);
			}});
        
        //------------------------------------------------------------------------------------------------
        
        
       /* TextView TitleText = (TextView)findViewById(R.id.textView1);
        TitleText.setTextColor(Color.WHITE);
        
        TextView Text1 = (TextView)findViewById(R.id.textView2);
        Text1.setTextColor(Color.WHITE);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
