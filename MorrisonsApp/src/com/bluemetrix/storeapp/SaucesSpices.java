package com.bluemetrix.storeapp;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SaucesSpices extends Activity{

	private ArrayList <StoreItem> myList;
	private Button shoppingList;
	private ImageView pastasauce;
	private ImageView ketchup;
	private ImageView mayo;
	private ImageView relish;
	private int points;
	
	private int pastaSauceCount = 0;
	private int ketchupCount = 0;
	private int mayoCount = 0;
	private int relishCount = 0;
	private StoreItem pastaSauceItem;
	private StoreItem ketchupItem;
	private StoreItem mayoItem;
	private StoreItem relishItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.sauces_spices);
		
		 shoppingList = (Button) findViewById(R.id.buttonList);
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
		 points = getIntent().getIntExtra("points", 0);
	
		 pastasauce = (ImageView) findViewById(R.id.ImageView08);		 
		 ketchup = (ImageView) findViewById(R.id.ImageView03);
		 mayo = (ImageView) findViewById(R.id.imageView02);
		 relish = (ImageView) findViewById(R.id.ImageView04);

	


		 relish.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				relishCount++;
	    		  		relishItem = new StoreItem(R.drawable.relish, "Relish", 83, relishCount,"");	
	    		 		Toast.makeText(SaucesSpices.this, "Relish added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 mayo.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				mayoCount++;
	    		  		mayoItem = new StoreItem(R.drawable.mayo, "Mayonnaise", 82, mayoCount,"");	
	    		  		Toast.makeText(SaucesSpices.this, "Mayonnaise added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 ketchup.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				ketchupCount++;
	    		  		ketchupItem = new StoreItem(R.drawable.ketchup, "Ketchup", 81, ketchupCount,"");	
	    		  		Toast.makeText(SaucesSpices.this, "Ketchup added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		   pastasauce.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				pastaSauceCount++;
	    		  		pastaSauceItem = new StoreItem(R.drawable.pastasauce, "Pasta Sauce", 80, pastaSauceCount, "");	
	    		  		Toast.makeText(SaucesSpices.this, "Pasta sauce added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
	 
		 shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			
	    			if(pastaSauceCount!=0){	 
	    				 myList.add(pastaSauceItem);
	    			 }
	    			if(ketchupCount!=0){	 
	    				 myList.add(ketchupItem);
	    			 }
	    			if(mayoCount!=0){	 
	    				 myList.add(mayoItem);
	    			 }
	    			if(relishCount!=0){	 
	    				 myList.add(relishItem);
	    			 }
	    			
	    			
	    			Intent in = new Intent(SaucesSpices.this, ShoppingList.class);
	    			
	    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
	    			for(int i=0; i<myList.size(); i++){
	    				sendList.add(myList.get(i));
	    			}
	    			 if (sendList.size()==0){
	    				 in.putExtra("option", 1);
	    				 in.putExtra("points", points);
	    			 }
	    			 else{
	    			in.putExtra("option", 3);
	    			in.putExtra("points", points);
	    			 in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
	    			 }
	    		   
	            	startActivity(in);    			
	    		}
	     });
		 
	
	
	}
}
