package com.bluemetrix.avatar;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class CheckoutView extends SherlockActivity
{
	private ListView list;
	private LazyAdapter lazy;
	private ArrayList<CheckoutItem> basket;
	private ArrayList<Integer> torsoCartImages;
	private TextView havePoints;
	private TextView totalPoints;
	private int points;
	private int total;
	private LinearLayout purchase;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
           super.onCreate(savedInstanceState);
           
           Intent in = getIntent();
           torsoCartImages = in.getIntegerArrayListExtra("cart");
           points = in.getIntExtra("points", 100);
           total = in.getIntExtra("totalpoints", 0);

           setContentView(R.layout.checkout_view);

           havePoints = (TextView) findViewById(R.id.textViewWelcomePoints);
           totalPoints = (TextView) findViewById(R.id.textViewCheckoutTotal);
           purchase = (LinearLayout) findViewById(R.id.linearLayoutPurchase);
           
           String credits = ("You have " + points + " credits");
           String tot = ("Total - " + total);
           
           havePoints.setText(credits);
           totalPoints.setText(tot);
           
           basket = new ArrayList<CheckoutItem>();
           list = (ListView) findViewById(R.id.listViewCheckoutItems);
           
           for(int i = 0; i<torsoCartImages.size(); i++)
           {   	   
        	   int image = torsoCartImages.get(i);
        	   int p = ((i+1)*10);
        	   String point = Integer.toString(p);
        	   CheckoutItem item = new CheckoutItem(image, "item " + (i+1), point);
        	   basket.add(item);   
           }
           
           //to test listview
           CheckoutItem item1 = new CheckoutItem(R.drawable.storetorso1, "item 1", "10");
           CheckoutItem item2 = new CheckoutItem(R.drawable.storetorso2, "item 2", "20");
           CheckoutItem item3 = new CheckoutItem(R.drawable.storetorso3, "item 3", "30");

           lazy=new LazyAdapter(basket, this);           
           list.setAdapter(lazy);       
           
           purchase.setOnClickListener(new OnClickListener() 
           {
       		@Override
       		public void onClick(View v)
       		{
       			//add garments to persons wardrobe 
       			//deduct points from persons total
       			
       			Intent in = new Intent(CheckoutView.this, PurchaseView.class);
               	startActivity(in);    			
       		}
        });
                
           //Inflate the custom view
           View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_checkout, null);

      
           //Attach to the action bar
           getSupportActionBar().setCustomView(customNav);
           getSupportActionBar().setDisplayShowCustomEnabled(true);
   }
}
