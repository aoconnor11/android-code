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

public class GreenGrocer extends Activity{

	private ArrayList <StoreItem> myList;
	private Button shoppingList;
	private ImageView broccoli;
	private ImageView cabbage;
	private ImageView carrots;
	private ImageView cucumber;
	private ImageView greenbeans;
	private ImageView lettuce;
	private ImageView mushrooms;
	private ImageView potatoe;
	private ImageView onion;
	private ImageView tomato;
	private ImageView spinnach;
	private int points;
	
	private int broccoliCount = 0;
	private int cabbageCount = 0;
	private int carrotsCount = 0;
	private int cucumberCount = 0;
	private int greenBeansCount = 0;
	private int lettuceCount = 0;
	private int mushroomsCount = 0;
	private int potatoCount = 0;
	private int onionCount = 0;
	private int tomatoCount = 0;
	private int spinnachCount = 0;
	
	private StoreItem broccoliItem;
	private StoreItem cabbageItem;
	private StoreItem carrotsItem;
	private StoreItem cucumberItem;
	private StoreItem greenBeansItem;
	private StoreItem lettuceItem;
	private StoreItem mushroomsItem;
	private StoreItem potatoItem;
	private StoreItem onionItem;
	private StoreItem tomatoItem;
	private StoreItem spinnachItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.vegetables);
		
		 shoppingList = (Button) findViewById(R.id.buttonList);
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
		 points = getIntent().getIntExtra("points", 0);
	
		 broccoli = (ImageView) findViewById(R.id.ImageView08);		 
		 cabbage = (ImageView) findViewById(R.id.ImageView03);
		 carrots = (ImageView) findViewById(R.id.imageView02);
		 cucumber = (ImageView) findViewById(R.id.ImageView04);
		 greenbeans = (ImageView) findViewById(R.id.ImageView02);
		 lettuce = (ImageView) findViewById(R.id.ImageView07);
		mushrooms = (ImageView) findViewById(R.id.ImageView06);
		potatoe = (ImageView) findViewById(R.id.ImageView17);
		onion = (ImageView) findViewById(R.id.ImageView01);
		tomato = (ImageView) findViewById(R.id.ImageView11);
		spinnach = (ImageView) findViewById(R.id.ImageView10);
		
		 tomato.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				tomatoCount++;
	    		  		tomatoItem = new StoreItem(R.drawable.tomatoe, "Tomatoes", 43,tomatoCount, "");	
	    		  		Toast.makeText(GreenGrocer.this, "Tomatoes added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		
		 spinnach.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				spinnachCount++;
	    		  		spinnachItem = new StoreItem(R.drawable.spinnach, "Spinach", 42,spinnachCount, "");	
	    		  		Toast.makeText(GreenGrocer.this, "Spinach added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		
		 potatoe.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				potatoCount++;
	    		  		potatoItem = new StoreItem(R.drawable.potatoe, "Potatoes", 41, potatoCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Potatoes added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 onion.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				onionCount++;
	    		  		onionItem = new StoreItem(R.drawable.onion, "Onions", 40, onionCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Onions added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 mushrooms.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				mushroomsCount++;
	    		  		mushroomsItem = new StoreItem(R.drawable.mushrooms, "Mushrooms", 39, mushroomsCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Mushrooms added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		
		 lettuce.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				lettuceCount++;
	    		  		lettuceItem = new StoreItem(R.drawable.lettuce, "Lettuce", 38, lettuceCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Lettuce added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 greenbeans.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				greenBeansCount++;
	    		  		greenBeansItem = new StoreItem(R.drawable.greenbeans, "Green Beans", 37, greenBeansCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Green beans added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 cucumber.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				cucumberCount++;
	    		  		cucumberItem = new StoreItem(R.drawable.cucumber, "Cucumber", 36, cucumberCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Cucumber added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 carrots.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				carrotsCount++;	
	    		  		carrotsItem = new StoreItem(R.drawable.carrots, "Carrots", 35, carrotsCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Carrots added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 cabbage.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				cabbageCount++;
	    		  		cabbageItem = new StoreItem(R.drawable.cabbage, "Cabbage", 34, cabbageCount,"");	
	    		  		Toast.makeText(GreenGrocer.this, "Cabbage added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		   broccoli.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				broccoliCount++;
	    		  		broccoliItem = new StoreItem(R.drawable.broccoli, "Broccoli", 33, broccoliCount, "");	
	    		  		Toast.makeText(GreenGrocer.this, "Broccoli added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 

		 
		 shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			if(broccoliCount!=0){	 
	    				 myList.add(broccoliItem);
	    			 }
	    			if(cabbageCount!=0){	 
	    				 myList.add(cabbageItem);
	    			 }
	    			if(carrotsCount!=0){	 
	    				 myList.add(carrotsItem);
	    			 }
	    			if(cucumberCount!=0){	 
	    				 myList.add(cucumberItem);
	    			 }
	    			if(greenBeansCount!=0){	 
	    				 myList.add(greenBeansItem);
	    			 }
	    			if(lettuceCount!=0){	 
	    				 myList.add(lettuceItem);
	    			 }
	    			if(mushroomsCount!=0){	 
	    				 myList.add(mushroomsItem);
	    			 }
	    			if(onionCount!=0){	 
	    				 myList.add(onionItem);
	    			 }
	    			if(potatoCount!=0){	 
	    				 myList.add(potatoItem);
	    			 }
	    			if(spinnachCount!=0){	 
	    				 myList.add(spinnachItem);
	    			 }
	    			if(tomatoCount!=0){	 
	    				 myList.add(tomatoItem);
	    			 }
	    			
		
	    			
	    			Intent in = new Intent(GreenGrocer.this, ShoppingList.class);
	    			
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
