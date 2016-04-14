package com.bluemetrix.storeapp;

import java.util.ArrayList;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class StoreCategories extends SherlockActivity {
	
	private ArrayList <StoreItem> myList;
	private ImageView item1;
	private ImageView item2;
	private ImageView item3;
	private ImageView item4;
	private ImageView item5;
	private ImageView item6;
	private ImageView item7;
	private ImageView item8;
	private ImageView item9;
	private ImageView item10;
	private ImageView item11;
	private ImageView item12;
	private Button shoppingList;
	private Button store;
	private int points;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.store_items);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
		 points = getIntent().getIntExtra("points", 0);
		 
		 item1 = (ImageView) findViewById(R.id.ImageView08);	//bakery	 
		 item2 = (ImageView) findViewById(R.id.ImageView03);	//fruit
		 item3 = (ImageView) findViewById(R.id.imageView02);	//vegetables
		 item4 = (ImageView) findViewById(R.id.ImageView04);	//meat
		 item5 = (ImageView) findViewById(R.id.ImageView02);	//seafood
		 item6 = (ImageView) findViewById(R.id.ImageView07);	//dairy
		 item7 = (ImageView) findViewById(R.id.ImageView06);	//pantry	 
		 item8 = (ImageView) findViewById(R.id.ImageView01);	//drinks		 
		 item9 = (ImageView) findViewById(R.id.ImageView17);	//beer	 
		 item10 = (ImageView) findViewById(R.id.ImageView10);	//sauces		 
		 item11 = (ImageView) findViewById(R.id.ImageView11);	//dry foods
		 
		 item12 = (ImageView) findViewById(R.id.ImageView05);	//sweets
		 
		 shoppingList = (Button) findViewById(R.id.buttonList);
		 store = (Button) findViewById(R.id.buttonStores);
		 
		 
		 
		//sweets
		   item12.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, Snacks.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//dry foods
		   item11.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, DryFoods.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//sauce
		   item10.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, SaucesSpices.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//beer
		   item9.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, BeerWine.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//drinks
		   item8.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, Drinks.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//pantry
		   item7.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, Pantry.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//dairy
		   item6.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, Dairy.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//fish
		   item5.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, SeaFood.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		//meat
		   item4.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, Butcher.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 
		 //open new bakery page
		   item1.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			Intent in = new Intent(StoreCategories.this, Bakery.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	 
	    		}
	     });
		 

		   //fruit
		   item2.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	

	    			Intent in = new Intent(StoreCategories.this, Fruit.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    		}
	     });

		   
		   
		   
		   
		   //vegetables
		   item3.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				Intent in = new Intent(StoreCategories.this, GreenGrocer.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			in.putExtra("points", points);
	    			in.putExtra("option", 2);
	    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	            	startActivity(in);    
	    			
	    			
	    			
	    		}
	     });
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		   
		 
		 
		 
		 
		   shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			Intent in = new Intent(StoreCategories.this, ShoppingList.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			 if (sendList.size()==0){
	    				 in.putExtra("option", 1);
	    			 }
	    			 else{
	    			in.putExtra("option", 3);
	    			 in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	    			 }
	    		   
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
