package com.bluemetrix.friends;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;

public class CreateGroup extends SherlockActivity
{	
	private ArrayList<String> friendNames;
	private EditText nameInput;
	private CharSequence groupName;
	private String groupNameString = "";
	private ListView list;
	private SimpleAdapter adapt;
	private String[] myList;
	private Button cancel;
	private Button done;
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.groups_list);

		nameInput = (EditText) findViewById(R.id.editTextGroupName);
	  
		friendNames = new ArrayList<String>();
		
		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		friendNames = b.getStringArrayList("list");
 
		System.out.println("size: " + friendNames.size());
		myList = new String[friendNames.size()];
     
		for(int i = 0; i<friendNames.size(); i++)
		{
			myList[i] = friendNames.get(i); 
		}
 
		list = (ListView) findViewById(R.id.listViewGroups);

		adapt = new SimpleAdapter(this, myList);
		list.setAdapter(adapt);
 
		list.setOnItemClickListener(new OnItemClickListener() 
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) 
			{
				RelativeLayout newView = (RelativeLayout) view;
				
				//add person to group list
				TextView myName = (TextView) newView.findViewById(R.id.name);
				myName.setTextColor(getResources().getColor(R.color.red));
			}
		});
   
		nameInput.setOnKeyListener(new OnKeyListener() 
		{
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
			// register the text when "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN))
					 
					{
					// grab the text for use in the activity
				
						groupName = nameInput.getText();
						groupNameString = groupName.toString();
						System.out.println("string " + groupName);
						return true;
					}
				return false;
			}
		});  
     
		//Inflate the custom view
		View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_new_group, null);
     
		cancel = (Button) customNav.findViewById(R.id.button2);
		done = (Button) customNav.findViewById(R.id.button3);
 
		cancel.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				//back to home page			
				Intent in = new Intent(CreateGroup.this, GroupList.class);
				startActivity(in);    			
			}
		});

		done.setOnClickListener(new OnClickListener() 
		{
	 		@Override
	 		public void onClick(View v) 
	 		{			
	 			//save new group to server - with list of friends & group name
	        	//add new group name to list
	 			Intent in = new Intent(CreateGroup.this, GroupList.class);
	 			
	 			if(!groupNameString.equals(""))//if group name is not empty
	 			{
	 				in.putExtra("groupName", groupNameString);
	 				System.out.println("done " + groupNameString);
	 				startActivity(in);
	 			}
	 			else
	 			{
	 				Toast.makeText(CreateGroup.this, "Please enter a group name", Toast.LENGTH_LONG).show();
	 			}	    			
	 		}
	  	});
 
		//Attach to the action bar
		getSupportActionBar().setCustomView(customNav);
		getSupportActionBar().setDisplayShowCustomEnabled(true);
	}	
}
