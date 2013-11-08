package com.spiritpossession.two;

import com.google.ads.AdRequest;

import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import com.google.analytics.tracking.android.EasyTracker;

public class QuestionActivity extends Activity {

	private AdView adView;
	/*private LinearLayout lytMain;*/
    private String str = "";  //"espiritu por favor responde";
    private String userAnswerText = "";
    private boolean userAnswerFlag = false;
    private boolean flagAnswer = false;
    private int countAux = 0;
    private int countEnter = 0;
    private boolean nonRepeatFlag = false; 
    private String aux = ""; 

    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);
        str = getString(R.string.SpiritStr);
        
        /*lytMain = (LinearLayout) findViewById(R.id.lytMain);*/
        adView = new AdView(this, AdSize.BANNER, "a151786603974bf");
        /*lytMain.addView(adView);*/
        adView.bringToFront();
        adView.loadAd(new AdRequest());
        
        final EditText  petition = (EditText)findViewById(R.id.editPetition);
        petition.requestFocus();
 
        petition.setTextColor(0xffffffff);
        
        
        petition.addTextChangedListener(new TextWatcher()
        {
                public void  afterTextChanged (Editable s){ 
                       
                } 
                public void  beforeTextChanged(CharSequence s, int start, int count, int after)
                { 
                   
                } 
                public void  onTextChanged(CharSequence s, int start, int before, int count) 
                { 
                       
   
                    
               	 if ((countAux < str.length()) && (s.length() > 0))
                    {
               		 
                    String lastChart = s.toString().substring(s.toString().length()-1, s.toString().length());
                     
                    if ((userAnswerFlag == true) && ( lastChart.compareTo(".")!= 0)) //si se activo el flag de guardar la respuesta...
                        {
                           
                        	if(nonRepeatFlag ==false){
                        		nonRepeatFlag = true;
                        		aux = aux + str.charAt(countAux);
                            petition.setText(aux) ;
                        	
                        	
                            petition.requestFocus();
                            petition.setSelection(petition.getText().length());
                            userAnswerText= userAnswerText + lastChart;
                            countAux++;
                            nonRepeatFlag = false;
                        	}
                        }

                        if (countEnter == 1)
                        {
                            if ( lastChart.compareTo(".") == 0 ) //si es la segunda vez que se preciona el enter...
                            {
                            	if(nonRepeatFlag ==false){
                            		nonRepeatFlag = true;
                            		aux = aux + str.charAt(countAux); 
                            		
                            		Log.v("pepe", userAnswerText);
                           	 petition.setText(aux);
                           	  
                           	 	petition.requestFocus();
                           	 	petition.setSelection(petition.getText().length());
                                userAnswerFlag = false;//desactivado el flag de guardar la respuesta
                                countAux++;
                                countEnter++;
                                flagAnswer = true;
                                nonRepeatFlag = false;
                               
                            	}
                           	
                            }

                        }

                        if (countAux == 0)
                        { //si es la promera letra que se entra...
                        	
                        	//Log.v("pepe", "a" + s.toString() + "a"); 
                        	
                            if (s.toString().compareTo(".")== 0) //si la primera letra es un punto...
                            {
                                
                            	if(nonRepeatFlag ==false){
                            		nonRepeatFlag = true;
                            		aux = aux + str.charAt(0);
                            		petition.setText(aux);
                           	 
                            		petition.requestFocus();
                            		petition.setSelection(petition.getText().length());
                           	 
                                userAnswerFlag = true;//activado el flag de guardar la respuesta
                                countAux++;
                                countEnter++;
                                nonRepeatFlag = false;
                            
                            	}
                            	
                          
                            }
                            
                        }

                    }

                       
                     
                }
        });
        
      final  EditText question = (EditText)findViewById(R.id.editQuestion);
      question.setTextColor(0xffffffff);
        Button btnAnswer = (Button)findViewById(R.id.btnAnswer);
        btnAnswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent inten = new Intent(QuestionActivity.this, AnswerActivity.class);
				 inten.putExtra("answer", userAnswerText );
				 inten.putExtra("flagAnswer", (flagAnswer== false)? "false" : "true" );
				startActivity(inten);
			}
		});
        
        Button btnReset = (Button)findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 petition.setText("");
		            question.setText("");

		            userAnswerText = "";
		            userAnswerFlag = false;
		            flagAnswer = false;
		            countAux = 0;
		            countEnter = 0;
		            nonRepeatFlag = false; 
		            aux = ""; 
		            
			}
		});

    }
//-------------------------------------------------------------------------------------
   
  
    
    
    
//-----------------------------------------------------------------------------------
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