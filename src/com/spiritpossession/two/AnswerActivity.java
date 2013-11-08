package com.spiritpossession.two;

import java.util.Locale;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.R.string;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
/*import android.widget.LinearLayout;*/
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.analytics.tracking.android.EasyTracker;


public class AnswerActivity extends Activity implements  OnInitListener{

	private String spiritsWords;
	private AdView adView;
	/*private LinearLayout lytMain;*/
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        
        //-------TTS-----------------------------------------------------------------------
        Intent checkTTSIntent = new Intent();
        checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        //-------TTS-----------------------------------------------------------------------
               
        /*lytMain = (LinearLayout) findViewById(R.id.lytMain);*/
        adView = new AdView(this, AdSize.BANNER, "a151786603974bf");
       /* lytMain.addView(adView);*/
        adView.bringToFront();
        adView.loadAd(new AdRequest());
        
       final TextView answer = (TextView)findViewById(R.id.answer);
        Bundle bundle = getIntent().getExtras();
       answer.setText(bundle.getString("answer"));
       
       
       
       String aux = bundle.getString("flagAnswer");
       if(aux.compareTo("false") == 0){
    	   RelativeLayout rLayout = (RelativeLayout)findViewById(R.id.backgroun);
    	   rLayout.setBackgroundResource(R.drawable.answer_angry);
    	   String[] arrStr = new String[8];
    	   arrStr[0] = getString(R.string.arr0);
    	   arrStr[1] = getString(R.string.arr0);
    	   arrStr[2] = getString(R.string.arr1);
    	   arrStr[3] = getString(R.string.arr2);
    	   arrStr[4] = getString(R.string.arr0);
    	   arrStr[5] = getString(R.string.arr1);
    	   arrStr[6] = getString(R.string.arr2);
    	   arrStr[7] = getString(R.string.arr0);
    	   int rand = (int) Math.floor(Math.random()*4 + 1);
    	   answer.setText(arrStr[rand]);
    	   Toast.makeText(getApplicationContext(), (String)arrStr[rand], Toast.LENGTH_LONG).show();
    	   spiritsWords = (String)arrStr[rand];
    	   //speakWords((String)arrStr[rand]);
    	   
       }else {
    	   RelativeLayout rLayout = (RelativeLayout)findViewById(R.id.backgroun);
    	   rLayout.setBackgroundResource(R.drawable.good_answer); 
    	   Toast.makeText(getApplicationContext(), (String)bundle.getString("answer"), Toast.LENGTH_LONG).show();
    	   
    	   spiritsWords = (String)bundle.getString("answer");
    	   //speakWords((String)bundle.getString("answer"));
       }
       
       Button Back = (Button) findViewById(R.id.Back);
        Back.setOnClickListener(
        		new OnClickListener() {
        			
        			@Override
        			public void onClick(View v) {
        				Intent inten = new Intent(AnswerActivity.this, QuestionActivity.class);
       				 	startActivity(inten);
        		            
        			}
        		}
        		);
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
    
    //-----------------------------------------TTS--------------------------
    
    //TTS object
   private TextToSpeech myTTS;
       //status check code
   private int MY_DATA_CHECK_CODE = 0;
       //create the Activity
    
   
   void speakWords(String speech) {
       //speak straight away
       myTTS.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
}
   //act on result of TTS data check
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   if (requestCode == MY_DATA_CHECK_CODE) {
       if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
           //the user has the necessary data - create the TTS
       myTTS = new TextToSpeech(this,  (OnInitListener) this);
       }
       else {
               //no data - install it now
           Intent installTTSIntent = new Intent();
           installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
           startActivity(installTTSIntent);
       }
   }
}
   //setup TTS
public void onInit(int initStatus) {
       //check for successful instantiation
   if (initStatus == TextToSpeech.SUCCESS) {
       if(myTTS.isLanguageAvailable(Locale.US)==TextToSpeech.LANG_AVAILABLE)
           myTTS.setLanguage(Locale.US);
     speakWords(spiritsWords);
   }
   else if (initStatus == TextToSpeech.ERROR) {
       Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
   }
   
   
}
   
   
   
   
   
   
   
   
   
   
	
    
 
}