package com.bluemetrix.avatar;

import com.bluemetrix.R;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class PurchaseView extends Activity
{
	private LinearLayout tryPurchases;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.checkout_purchase_made);
        setTitle("Purchase Made");
                     
        tryPurchases = (LinearLayout) findViewById(R.id.linearLayoutTryPurchase);
           
        tryPurchases.setOnClickListener(new OnClickListener() 
        {
        	@Override
        	public void onClick(View v) 
          	{
        		Intent in = new Intent(PurchaseView.this, FragmentChangeActivity.class);
          		in.putExtra("option", 1);
                startActivity(in);    			
          	}
        });   
	}
}