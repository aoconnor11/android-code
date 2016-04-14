package com.bluemetrix.storeapp;

import java.util.ArrayList;
import java.util.HashSet;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class PickUp extends SherlockActivity{

	private Button order;
	private ArrayList<StoreDetails> basket;
	private ArrayList<StoreItem> shopList;
	private ArrayList<StoreItem> newShopList;
	private ArrayList<StoreItem> sortedbasket;
	private ListView list;
	private LazyAdapter lazy;
	private int option;
	private int userID;
	private int points;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.pickup);
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	
	    order = (Button) findViewById(R.id.buttonClearList);
	
	    Intent in = getIntent();
        option = in.getIntExtra("option", 1);
        userID = in.getIntExtra("user", 2);
        
        shopList = getIntent().getParcelableArrayListExtra ("list");
        points = getIntent().getIntExtra("points", 0);
	    
        list = (ListView) findViewById(R.id.listViewCheckoutItems);
	    
        sortedbasket = new ArrayList<StoreItem>(new HashSet<StoreItem>(shopList));
        
      	 
        lazy=new LazyAdapter(sortedbasket, this, 1);           
        list.setAdapter(lazy); 
	    
	    
        order.setOnClickListener(new OnClickListener() 
        {
    		@Override
    		public void onClick(View v)
    		{	
    			Intent in = new Intent(PickUp.this, StoreList.class);
    			
    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
    			for(int i=0; i<shopList.size(); i++){
    				sendList.add(shopList.get(i));
    			}
    			
    			in.putExtra("option", 5);//5 - pickup
    			in.putExtra("points", points);//send points to list
    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    	
    	return true;
    	
    default:
        return super.onOptionsItemSelected(item);	
   
    }
}
}
