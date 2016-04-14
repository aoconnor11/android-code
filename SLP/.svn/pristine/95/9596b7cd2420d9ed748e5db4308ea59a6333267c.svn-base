package com.bluemetrix.friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;

public class FriendsList extends SherlockActivity
{
	private Button cancel;
	private ArrayList<String> names;
	private ListView list;
	private SimpleAdapter adapt;
	private String[] myList;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_list);
		setTitle("");
	
		names = new ArrayList<String>();
	    Intent intent = getIntent();
	    Bundle b = intent.getExtras();
	    names = b.getStringArrayList("list");
	 
	    System.out.println("size: " + names.size());
	    myList = new String[names.size()];
	     
	     for(int i = 0; i<names.size(); i++)
	     {
	    	 myList[i] = names.get(i);
	     }
		 
	     list = (ListView) findViewById(R.id.listViewMessages);
	     adapt = new SimpleAdapter(this, myList);
	     list.setAdapter(adapt);

	     list.setOnItemClickListener(new OnItemClickListener() 
	     {
	    	 @Override
	    	 public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
	    	 {
	    		 //add person to friends list
	    		 //go to message page
	    		 Intent intent = new Intent(FriendsList.this, FriendsMenu.class);
	    		 startActivity(intent);
	    	 }
	     });
	     
	     //Inflate the custom view
	     View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_friends_list, null);
	     cancel = (Button) customNav.findViewById(R.id.button2);

	     cancel.setOnClickListener(new OnClickListener() 
	     {
	    	 @Override
	 		 public void onClick(View v) 
	    	 {
	    		 //back to home page			
	 			 Intent in = new Intent(FriendsList.this, FriendsMenu.class);
	         	 startActivity(in);    			
	 		 }
	  	 });
     
	     //Attach to the action bar
	     getSupportActionBar().setCustomView(customNav);
	     getSupportActionBar().setDisplayShowCustomEnabled(true);     
	}	
}
