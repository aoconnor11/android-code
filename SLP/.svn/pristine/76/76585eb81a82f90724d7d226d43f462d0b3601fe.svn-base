package com.bluemetrix.friends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;
import com.bluemetrix.login.LoginView;
import com.bluemetrix.login.SignUpView;
import com.slidingmenu.example.fragments.FragmentChangeActivity;


public class FriendsMenu extends SherlockActivity
{
	private LinearLayout friendsRequests;
	private LinearLayout friendsList;
	private LinearLayout leagueTable;
	private LinearLayout manageGroups;
	private ArrayList <Friend> myFriends;
	private Button bDone;
	private Button bAdd;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
     
		setContentView(R.layout.friends_layout);
	
		friendsRequests = (LinearLayout) findViewById(R.id.linearLayoutFriendRequests);
		friendsList = (LinearLayout) findViewById(R.id.LinearLayoutFriendsList);
		leagueTable = (LinearLayout) findViewById(R.id.LinearLayoutLeagueTable);
		manageGroups = (LinearLayout) findViewById(R.id.LinearLayoutManageGroups);
  
		friendsRequests.setOnClickListener(new OnClickListener() 
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
     	 
				Intent in = new Intent(FriendsMenu.this, FriendsRequests.class);
				in.putExtra("list", myList);
				startActivity(in);			
			}
    	});   
      
		friendsList.setOnClickListener(new OnClickListener() 
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
        	 
				Intent in = new Intent(FriendsMenu.this, FriendsList.class);
         		in.putExtra("list", myList);
   				startActivity(in);		   			
			}
    	});
        
		leagueTable.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Friend f1 = new Friend(1, "John Smith", 100, 11);
				Friend f2 = new Friend(2, "Amy Pond", 120, 9);
				Friend f3 = new Friend(3, "Rory Williams", 130, 7);
				myFriends = new ArrayList();
				myFriends.add(f1);
				myFriends.add(f2);
				myFriends.add(f3);
       	 
				Collections.sort(myFriends, new FriendSortByRank());
		
				ArrayList<String> myList = new ArrayList<String>();
				ArrayList<Integer> myRank = new ArrayList<Integer>();
				for(int i = 0; i<myFriends.size(); i++)
				{
					String name = myFriends.get(i).getFriendName();
					int rank = myFriends.get(i).getFriendRank();
					myList.add(name);
					myRank.add(rank);
				}

				Intent in = new Intent(FriendsMenu.this, LeagueTable.class);
        		in.putExtra("list", myList);
        		in.putExtra("rank", myRank);
        		startActivity(in);		   			
			}
    	});
      
		manageGroups.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				//back to add friend page		
				Intent in = new Intent(FriendsMenu.this, GroupList.class);
				in.putExtra("groupName", "");
				startActivity(in);    			
			}
    	});
 
		//Inflate the custom view
		View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_friends, null);
     
		bDone = (Button) customNav.findViewById(R.id.buttonDone);
		bAdd = (Button) customNav.findViewById(R.id.buttonAdd);

		bDone.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				//back to home page			
				Intent in = new Intent(FriendsMenu.this, FragmentChangeActivity.class);
				startActivity(in);    			
			}
		});
		
		bAdd.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				//back to add friend page		
				Intent in = new Intent(FriendsMenu.this, AddFriends.class);
				startActivity(in);    			
			}
		});

		//Attach to the action bar
		getSupportActionBar().setCustomView(customNav);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
	}
}
