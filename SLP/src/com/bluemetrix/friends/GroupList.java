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

public class GroupList extends SherlockActivity
{
	private ArrayList<String> groupNames;
	private ListView list;
	private SimpleAdapter adapt;
	private String[] myList;
	private Button cancel;
	private Button addGroup;
	private String groupName;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_list);

		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		groupName = b.getString("groupName");
		System.out.println("list " + groupName);
	 
		//get persons groups from server
		groupNames = new ArrayList<String>();
		groupNames.add("Work Mates");
		groupNames.add("Family");
		groupNames.add("College Friends");
	 
		if(!groupName.equals(""))
		{ 
			groupNames.add(groupName);//add new group name to list
		}
	 
		System.out.println("size: " + groupNames.size());
		myList = new String[groupNames.size()];
     
		for(int i = 0; i<groupNames.size(); i++)
		{
			myList[i] = groupNames.get(i);
		}
	 
		list = (ListView) findViewById(R.id.listViewMessages);
		adapt = new SimpleAdapter(this, myList);
		list.setAdapter(adapt);
	 
		list.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
			{
				//	Intent intent = new Intent(GroupList.this, FriendsMenu.class);
				//        startActivity(intent);
			}
        });
    
		//Inflate the custom view
		View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_groups, null);
	     
	    cancel = (Button) customNav.findViewById(R.id.button2);
	    addGroup = (Button) customNav.findViewById(R.id.button3);
	 
	    cancel.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	 		public void onClick(View v) 
	 		{
	    		//back to home page			
	 			Intent in = new Intent(GroupList.this, FriendsMenu.class);
	         	startActivity(in);    			
	 		}
	  	});
	     
	    addGroup.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
		 	public void onClick(View v) 
	    	{				
	    		Friend f1 = new Friend(1, "John Smith", 100, 11);
		        Friend f2 = new Friend(2, "Amy Pond", 120, 9);
		        Friend f3 = new Friend(3, "Rory Williams", 130, 7);
		        	 
		        ArrayList<String> myList = new ArrayList<String>();
		        myList.add(f1.getFriendName());
		        myList.add(f2.getFriendName());
		        myList.add(f3.getFriendName());
		        	 
		        Intent in = new Intent(GroupList.this, CreateGroup.class);
		        in.putExtra("list", myList);
		        startActivity(in);    			
		 	}
	    });

	    //Attach to the action bar
	    getSupportActionBar().setCustomView(customNav);
	    getSupportActionBar().setDisplayShowCustomEnabled(true); 
	}	
}