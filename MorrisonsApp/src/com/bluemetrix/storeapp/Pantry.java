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

public class Pantry extends Activity{

	private ArrayList <StoreItem> myList;
	private Button shoppingList;
	private ImageView curry;
	private ImageView jam;
	private ImageView pepper;
	private ImageView soup;
	private ImageView sugar;
	private ImageView salt;
	private ImageView flour;
	private int points;

	private int curryCount = 0;
	private int jamCount = 0;
	private int pepperCount = 0;
	private int soupCount = 0;
	private int sugarCount = 0;
	private int saltCount = 0;
	private int flourCount = 0;
	private StoreItem curryItem;
	private StoreItem jamItem;
	private StoreItem pepperItem;
	private StoreItem soupItem;
	private StoreItem sugarItem;
	private StoreItem saltItem;
	private StoreItem flourItem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.pantry);
		
		 shoppingList = (Button) findViewById(R.id.buttonList);
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
		 points = getIntent().getIntExtra("points", 0);
	
		 curry = (ImageView) findViewById(R.id.ImageView08);		 
		 jam = (ImageView) findViewById(R.id.ImageView03);
		 pepper = (ImageView) findViewById(R.id.imageView02);
		 soup = (ImageView) findViewById(R.id.ImageView04);
		 sugar = (ImageView) findViewById(R.id.ImageView02);
		 salt = (ImageView) findViewById(R.id.ImageView07);
		flour = (ImageView) findViewById(R.id.ImageView06);

		

		 
		 flour.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				flourCount++;
	    		  		flourItem = new StoreItem(R.drawable.flour, "Flour", 68,flourCount, "");	
	    		  		Toast.makeText(Pantry.this, "Flour added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		
		 salt.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				saltCount++;
	    		  		saltItem = new StoreItem(R.drawable.salt, "Salt", 66, saltCount,"");	
	    		 		Toast.makeText(Pantry.this, "Salt added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 sugar.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				sugarCount++;
	    		  		sugarItem = new StoreItem(R.drawable.sugar, "Sugar", 65, sugarCount,"");	
	    		 		Toast.makeText(Pantry.this, "Sugar added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 soup.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				soupCount++;
	    		  		soupItem = new StoreItem(R.drawable.soup, "Soup", 64, soupCount,"");	
	    		 		Toast.makeText(Pantry.this, "Soup added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 pepper.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				pepperCount++;
	    		  		pepperItem = new StoreItem(R.drawable.pepper, "Pepper", 63, pepperCount,"");	
	    		 		Toast.makeText(Pantry.this, "Pepper added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 jam.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				jamCount++;
	    		  		jamItem = new StoreItem(R.drawable.jam, "Jam", 62, jamCount,"");	
	    		 		Toast.makeText(Pantry.this, "Jam added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		   curry.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				curryCount++;
	    		  		curryItem = new StoreItem(R.drawable.curry, "Curry", 61, curryCount,"");	
	    		 		Toast.makeText(Pantry.this, "Curry added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 

		 
		 shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			if(curryCount!=0){	 
	    				 myList.add(curryItem);
	    			 }
	    			if(jamCount!=0){	 
	    				 myList.add(jamItem);
	    			 }
	    			if(pepperCount!=0){	 
	    				 myList.add(pepperItem);
	    			 }
	    			if(saltCount!=0){	 
	    				 myList.add(saltItem);
	    			 }
	    			if(sugarCount!=0){	 
	    				 myList.add(sugarItem);
	    			 }
	    			if(soupCount!=0){	 
	    				 myList.add(soupItem);
	    			 }
	    			if(flourCount!=0){	 
	    				 myList.add(flourItem);
	    			 }
	    			

	    			
	    			Intent in = new Intent(Pantry.this, ShoppingList.class);
	    			
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