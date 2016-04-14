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

public class Dairy extends Activity{

	private ArrayList <StoreItem> myList;
	private Button shoppingList;
	private ImageView butter;
	private ImageView cheese;
	private ImageView icecream;
	private ImageView milk;
	private ImageView yoghurt;
	private int points;
	
	private int butterCount = 0;
	private int cheeseCount = 0;
	private int icecreamCount = 0;
	private int milkCount = 0;
	private int yoghurtCount = 0;
	private StoreItem butterItem;
	private StoreItem cheeseItem;
	private StoreItem icecreamItem;
	private StoreItem milkItem;
	private StoreItem yoghurtItem;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.dairy);
		
		 shoppingList = (Button) findViewById(R.id.buttonList);
		
		 myList = getIntent().getParcelableArrayListExtra ("list");
	
		 butter = (ImageView) findViewById(R.id.ImageView08);		 
		 cheese = (ImageView) findViewById(R.id.ImageView03);
		 icecream = (ImageView) findViewById(R.id.imageView02);
		 milk = (ImageView) findViewById(R.id.ImageView04);
		 yoghurt = (ImageView) findViewById(R.id.ImageView02);

		

	
		 
		 yoghurt.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				yoghurtCount++;
	    		  		yoghurtItem = new StoreItem(R.drawable.yougurt, "Yoghurts", 60, yoghurtCount, "");	
	    		  		Toast.makeText(Dairy.this, "Yoghurt added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 milk.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				milkCount++;
	    		  		milkItem = new StoreItem(R.drawable.milk, "Milk", 59, milkCount, "");	
	    		  		Toast.makeText(Dairy.this, "Milk added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 icecream.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				icecreamCount++;
	    		  		icecreamItem = new StoreItem(R.drawable.icecream, "Ice Cream", 58, icecreamCount, "");	
	    		  		Toast.makeText(Dairy.this, "Ice cream added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 cheese.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				cheeseCount++;
	    		  		cheeseItem = new StoreItem(R.drawable.cheese, "Cheese", 57, cheeseCount, "");	
	    		  		Toast.makeText(Dairy.this, "Cheese added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		   butter.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    				butterCount++;
	    		  		butterItem = new StoreItem(R.drawable.butter, "Butter", 56, butterCount, "");	
	    		  		Toast.makeText(Dairy.this, "Butter added to shopping list", Toast.LENGTH_SHORT).show();
	    		}
	     });
		 
		 
		 

		 
		 
		 shoppingList.setOnClickListener(new OnClickListener() 
	        {
	    		@Override
	    		public void onClick(View v)
	    		{	
	    			
	    			if(butterCount!=0){	 
	    				 myList.add(butterItem);
	    			 }
	    			if(icecreamCount!=0){	 
	    				 myList.add(icecreamItem);
	    			 }
	    			if(cheeseCount!=0){	 
	    				 myList.add(cheeseItem);
	    			 }
	    			if(milkCount!=0){	 
	    				 myList.add(milkItem);
	    			 }
	    			if(yoghurtCount!=0){	 
	    				 myList.add(yoghurtItem);
	    			 }
	    			
	    			
	    			
	    			Intent in = new Intent(Dairy.this, ShoppingList.class);
	    			
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
