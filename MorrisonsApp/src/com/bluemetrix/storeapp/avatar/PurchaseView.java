package com.bluemetrix.storeapp.avatar;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.bluemetrix.storeapp.HomePage;
import com.bluemetrix.storeapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PurchaseView extends SherlockActivity
{
	private LinearLayout tryPurchases;
	private int points;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSupportActionBar();
   	    actionBar.setDisplayHomeAsUpEnabled(true);
           
		setContentView(R.layout.checkout_purchase_made);
        setTitle("Purchase Made");
        Intent in = getIntent();
        points = in.getIntExtra("points", 0);
                     
        tryPurchases = (LinearLayout) findViewById(R.id.linearLayoutTryPurchase);
           
        tryPurchases.setOnClickListener(new OnClickListener() 
        {
        	@Override
        	public void onClick(View v) 
          	{
        		Intent in = new Intent(PurchaseView.this, AvatarHomePage.class);
          		in.putExtra("option", 1);
          		in.putExtra("points", points);
                startActivity(in);   			
          	}
        });   
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {

	    case android.R.id.home:
	    	
	         // Do whatever you want, e.g. finish()
	    	Intent intent = new Intent(this, HomePage.class);
	    	intent.putExtra("points", points);
	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent);
	    	
	    	return true;
	    	
	    default:
	        return super.onOptionsItemSelected(item);	
	   
	    }
	}
}