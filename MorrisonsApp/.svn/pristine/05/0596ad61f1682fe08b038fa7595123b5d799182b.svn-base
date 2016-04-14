package com.bluemetrix.storeapp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

public class PickUpConfirmation extends SherlockActivity{

	private Button thankYou;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.pickup_confirmation);
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	
	    thankYou = (Button) findViewById(R.id.buttonThankYou);
	    
	    
	    
	    
        thankYou.setOnClickListener(new OnClickListener() 
        {
    		@Override
    		public void onClick(View v)
    		{	
    			// Do whatever you want, e.g. finish()
    	    	Intent intent = new Intent(PickUpConfirmation.this, HomePage.class);
    	       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	        startActivity(intent);
    	    		
    		}
     });
	    
	    
	    
	    
	    
	
	}
	
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case android.R.id.home:
	    	
	         // Do whatever you want, e.g. finish()
	    	Intent intent = new Intent(this, HomePage.class);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent);
	    	
	    	return true;
	    	
	    default:
	        return super.onOptionsItemSelected(item);	
	   
	    }
	}
}
