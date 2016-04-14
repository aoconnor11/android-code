package com.bluemetrix.friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;
import com.bluemetrix.login.LoginView;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

public class AddFriends extends SherlockActivity 
{
	private Button cancel;
	private LinearLayout buttonUsername;
	private LinearLayout buttonEmail;
	private EditText messageEdit;
	private EditText emailEdit;
	private CharSequence mess;
	private CharSequence email;
	private String messString;
	private String emailString;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
	 super.onCreate(savedInstanceState);
     setContentView(R.layout.add_friends_layout);
     
     buttonUsername = (LinearLayout) findViewById(R.id.linearLayoutFindUsername);
     buttonEmail = (LinearLayout) findViewById(R.id.linearLayoutFindEmail);
     messageEdit = (EditText) findViewById(R.id.editTextMessage);
     emailEdit = (EditText) findViewById(R.id.editTextEmail);
 
     messageEdit.setOnKeyListener(new OnKeyListener() 
     {
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			mess = messageEdit.getText();
			messString = mess.toString();
			return false;
		}
	});
     
     emailEdit.setOnKeyListener(new OnKeyListener() 
     {
		public boolean onKey(View v, int keyCode, KeyEvent event) 
		{
			email = emailEdit.getText();
			emailString = email.toString();
			return false;
		}
     });
     
     buttonUsername.setOnClickListener(new View.OnClickListener() 
     {
    	 public void onClick(View view) 
         {  
    		 //check email against database	
    		 //check username against database	 - return friendID & friendName
    		 Friend f1 = new Friend(1, "John Smith", 100, 11);
    		 Friend f2 = new Friend(2, "Amy Pond", 120, 9);
    		 Friend f3 = new Friend(3, "Rory Williams", 130, 7);
     	 
    		 ArrayList<String> myList = new ArrayList<String>();
    		 myList.add(f1.getFriendName());
    		 myList.add(f2.getFriendName());
    		 myList.add(f3.getFriendName());
     	 
    		 Intent in = new Intent(AddFriends.this, FindFriendsList.class);
      		 in.putExtra("list", myList);
			 startActivity(in);
         }
     });
     
     buttonEmail.setOnClickListener(new View.OnClickListener() 
     {
    	 public void onClick(View view) 
         {  
    		 //check email against database	
        	 //check username against database	 - return friendID & friendName
        	 Friend f1 = new Friend(1, "John Smith", 100, 11);
        	 Friend f2 = new Friend(2, "Amy Pond", 120, 9);
        	 Friend f3 = new Friend(3, "Rory Williams", 130, 7);
        	 
        	 ArrayList<String> myList = new ArrayList<String>();
        	 myList.add(f1.getFriendName());
        	 myList.add(f2.getFriendName());
        	 myList.add(f3.getFriendName());
        	 
         	 Intent in = new Intent(AddFriends.this, FindFriendsList.class);
         	 in.putExtra("list", myList);
			 startActivity(in);
        	}
     });
    
     //Inflate the custom view
     View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_add_friends, null);
     
     cancel = (Button) customNav.findViewById(R.id.buttonCancel);
     
     cancel.setOnClickListener(new OnClickListener() 
     {
    	 @Override
  		 public void onClick(View v) 
    	 {
    		 //back to home page			
  			 Intent in = new Intent(AddFriends.this, FriendsMenu.class);
          	 startActivity(in);    			
  		 }
     });

     //Attach to the action bar
     getSupportActionBar().setCustomView(customNav);
     getSupportActionBar().setDisplayShowCustomEnabled(true); 
	}	
}
