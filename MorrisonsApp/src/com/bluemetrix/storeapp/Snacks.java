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

public class Snacks extends Activity{

	private ArrayList <StoreItem> myList;
	private Button shoppingList;
	private ImageView chocolate;
	private ImageView crisps;
	private ImageView popcorn;
	private ImageView nachoes;
	private ImageView sweets;
	private ImageView jellies;
	private int points;
	
	private int chocolateCount = 0;
	private int crispsCount = 0;
	private int popcornCount = 0;
	private int nachoesCount = 0;
	private int sweetsCount = 0;
	private int jelliesCount = 0;
	private StoreItem chocolateItem;
	private StoreItem crispsItem;
	private StoreItem popcornItem;
	private StoreItem nachoesItem;
	private StoreItem sweetsItem;
	private StoreItem jelliesItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.sweets_snacks);
		
		 shoppingList = (Button) findViewById(R.id.buttonList);
		
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
		 points = getIntent().getIntExtra("points", 0);
		 
		 chocolate = (ImageView) findViewById(R.id.ImageView08);		 
		 crisps = (ImageView) findViewById(R.id.ImageView03);
		 popcorn = (ImageView) findViewById(R.id.imageView02);
		 nachoes = (ImageView) findViewById(R.id.ImageView04);
		 sweets = (ImageView) findViewById(R.id.ImageView02);
		 jellies = (ImageView) findViewById(R.id.ImageView07);
	
		
		 jellies.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				jelliesCount++;
	    		  		jelliesItem = new StoreItem(R.drawable.jellies, "Jellies", 92, jelliesCount,"");	
	    		  		Toast.makeText(Snacks.this, "Jellies added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 sweets.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				sweetsCount++;
	    		  		sweetsItem = new StoreItem(R.drawable.sweeties, "Sweets", 91, sweetsCount,"");	
	    		  		Toast.makeText(Snacks.this, "Sweets added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 nachoes.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				nachoesCount++;
	    		  		nachoesItem = new StoreItem(R.drawable.nachoes, "Nachos", 90, nachoesCount,"");	
	    		  		Toast.makeText(Snacks.this, "Nachos added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 popcorn.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				popcornCount++;
	    		  		popcornItem = new StoreItem(R.drawable.popcorn, "Popcorn", 89, popcornCount,"");	
	    		  		Toast.makeText(Snacks.this, "Popcorn added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 crisps.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				crispsCount++;
	    		  		crispsItem = new StoreItem(R.drawable.crisps, "Crisps", 88, crispsCount,"");	
	    		  		Toast.makeText(Snacks.this, "Crisps added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		   chocolate.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				chocolateCount++;
	    		  		chocolateItem = new StoreItem(R.drawable.chocolate, "Chocolate", 87, chocolateCount,"");	
	    		  		Toast.makeText(Snacks.this, "Chocolate added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
	 
		 shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			if(chocolateCount!=0){	 
	    				 myList.add(chocolateItem);
	    			 }
	    			if(crispsCount!=0){	 
	    				 myList.add(crispsItem);
	    			 }
	    			if(popcornCount!=0){	 
	    				 myList.add(popcornItem);
	    			 }
	    			if(jelliesCount!=0){	 
	    				 myList.add(jelliesItem);
	    			 }
	    			if(nachoesCount!=0){	 
	    				 myList.add(nachoesItem);
	    			 }
	    			if(sweetsCount!=0){	 
	    				 myList.add(sweetsItem);
	    			 }
	    			
	    			
	    			Intent in = new Intent(Snacks.this, ShoppingList.class);
	    			
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
