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

public class SeaFood extends Activity{

	private ArrayList <StoreItem> myList;
	private Button shoppingList;
	private ImageView cod;
	private ImageView haddock;
	private ImageView mackerel;
	private ImageView salmon;
	private int points;

	private int codCount = 0;
	private int haddockCount = 0;
	private int mackerelCount = 0;
	private int salmonCount = 0;
	private StoreItem codItem;
	private StoreItem haddockItem;
	private StoreItem mackerelItem;
	private StoreItem salmonItem;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.seafood);
		
		 shoppingList = (Button) findViewById(R.id.buttonList);
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
		 points = getIntent().getIntExtra("points", 0);
		 
		 cod = (ImageView) findViewById(R.id.ImageView08);		 
		 haddock = (ImageView) findViewById(R.id.ImageView03);
		 mackerel = (ImageView) findViewById(R.id.imageView02);
		 salmon = (ImageView) findViewById(R.id.ImageView04);
		

		 
		 salmon.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				salmonCount++;
	    		  		salmonItem = new StoreItem(R.drawable.salmon, "Salmon", 55, salmonCount,"");	
	    		  		Toast.makeText(SeaFood.this, "Salmon added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 mackerel.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				mackerelCount++;
	    		  		mackerelItem = new StoreItem(R.drawable.mackeral, "Mackerel", 54, mackerelCount,"");	
	    		  		Toast.makeText(SeaFood.this, "Mackerel added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 haddock.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				haddockCount++;
	    		  		haddockItem = new StoreItem(R.drawable.haddock, "Haddock", 53, haddockCount,"");	
	    		  		Toast.makeText(SeaFood.this, "Haddock added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		   cod.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				codCount++;
	    		  		codItem = new StoreItem(R.drawable.cod, "Cod", 52, codCount,"");	
	    		  		Toast.makeText(SeaFood.this, "Cod added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
	
		 
		 
		 
		 shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			if(codCount!=0){	 
	    				 myList.add(codItem);
	    			 }
	    			if(mackerelCount!=0){	 
	    				 myList.add(mackerelItem);
	    			 }
	    			if(haddockCount!=0){	 
	    				 myList.add(haddockItem);
	    			 }
	    			if(salmonCount!=0){	 
	    				 myList.add(salmonItem);
	    			 }
	
	    			
	    			Intent in = new Intent(SeaFood.this, ShoppingList.class);
	    			
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
