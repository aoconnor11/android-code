package com.bluemetrix.friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;

public class LeagueTable  extends SherlockActivity
{	
	private Button cancel;
	private ArrayList<String> names;
	private ArrayList<Integer> ranks;
	private ListView list;
	private SimpleAdapter adapt;
	private String[] myList;
	private int[] myRank;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_list);

	    names = new ArrayList<String>();
	    ranks = new ArrayList<Integer>();
	    Intent intent = getIntent();
	    Bundle b = intent.getExtras();
	    names = b.getStringArrayList("list");
	    ranks = b.getIntegerArrayList("rank");
	     
	    System.out.println("size: " + names.size());
	    System.out.println("name " + names.get(0) + " rank " + ranks.get(0));
	    myList = new String[names.size()];
	    myRank = new int[ranks.size()];
	     
	    for(int i = 0; i<names.size(); i++)
	    {
	    	System.out.println("names " + names.get(i));
	    	myList[i] = names.get(i);
	    	myRank[i] = ranks.get(i);
	    }
	
	    list = (ListView) findViewById(R.id.listViewMessages);
	    adapt = new SimpleAdapter(this, myList, myRank, 1); 
	    list.setAdapter(adapt);
	
	    //Inflate the custom view
	    View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_league_table, null); 
	    cancel = (Button) customNav.findViewById(R.id.button2);

	    cancel.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	 		public void onClick(View v) 
	    	{
	    		//back to home page			
	 			Intent in = new Intent(LeagueTable.this, FriendsMenu.class);
	         	startActivity(in);    			
	 		}
	  	});
  
	     //Attach to the action bar
	     getSupportActionBar().setCustomView(customNav);
	     getSupportActionBar().setDisplayShowCustomEnabled(true);
	}
}