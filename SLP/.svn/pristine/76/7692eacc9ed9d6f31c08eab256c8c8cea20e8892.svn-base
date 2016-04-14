package com.slidingmenu.example.fragments;


import com.bluemetrix.R;
import com.bluemetrix.game.activities.SceneSelectionView;
import com.bluemetrix.login.SettingsFragment;
import com.bluemetrix.messages.MessageList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;



public class ColorMenuFragment extends ListFragment 
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);
		String[] colors = getResources().getStringArray(R.array.menu_items);
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, colors);
		setListAdapter(colorAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) 
	{
		Fragment newContent = null;
		switch (position) {
		case 0:
			//go to home
			option("zero");
			newContent = new ColorFragment(R.color.red, -1);
			break;
		case 1:
			//go to avatar
			option("one");
			newContent = new ColorFragment(R.color.green, 1);
			break;
		case 2:
			//go to lesson
			option("two");
			newContent = new ColorFragment(R.color.blue, 2);
			break;
		case 3:
			//go to game
			option("three");
			newContent = new ColorFragment(R.color.blue, 4);
			break;
		case 4://option 6
			//go to messages
			option("four");
			newContent = new ColorFragment(android.R.color.black, 6);
			break;
		case 5://option 7
			//purchases
			option("five");
			newContent = new ColorFragment(android.R.color.black, 7);
			break;
		case 6://option 8
			//privacy
			option("six");
			newContent = new ColorFragment(android.R.color.black, 8);
			break;
		case 7://option 9
			//settings
			option("seven");
			newContent = new SettingsFragment(1);
			break;	
		case 8://option 10
			//latest content
			option("eight");
			newContent = new ColorFragment(android.R.color.black, 10);
			break;	
		case 9://option 11
			//contact us
			option("nine");
			newContent = new ColorFragment(android.R.color.black, 11);
			break;		
		}
		if (newContent != null)
			switchFragment(newContent);
	}


	
	private void option(String option)
	{		
		if(option.equals("zero"))
		{	
			//go home
			Activity activity = getActivity();
			Intent intent = new Intent(activity,FragmentChangeActivity.class);	
			startActivity(intent);
		}
		if(option.equals("one"))
		{	
			//avatar			
			Activity activity = getActivity();
			activity.setTitle("Avatar Editor");
		}
		if(option.equals("two"))
		{	
			//lesson
			Activity activity = getActivity();
			activity.setTitle("Lesson");
		}
		if(option.equals("three"))
		{	
			//game	
			Activity activity = getActivity();
			activity.setTitle("Game");
		}
		if(option.equals("four"))
		{
			//go to messages
			Activity activity = getActivity();
			activity.setTitle("Messages");
		}
		if(option.equals("five"))
		{
			//go to purchases
			Activity activity = getActivity();
			activity.setTitle("Purchases");
		}	
		if(option.equals("six"))
		{
			//go to privacy
			Activity activity = getActivity();
			activity.setTitle("Privacy");
		}
		if(option.equals("seven"))
		{
			//go to settings
			Activity activity = getActivity();
			activity.setTitle("Settings");
		}
		if(option.equals("eight"))
		{
			//go to latest content
			Activity activity = getActivity();
			activity.setTitle("Latest Updates");//how - list of updates?? or what?
		}
		if(option.equals("nine"))
		{
			//go to contact us
			Activity activity = getActivity();
			activity.setTitle("Contact Us");//how - by email??? or form??
		}	
	}
	

	private void switchFragment(Fragment fragment) 
	{
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof FragmentChangeActivity) {
			FragmentChangeActivity fca = (FragmentChangeActivity) getActivity();
			fca.switchContent(fragment);
		} else if (getActivity() instanceof ResponsiveUIActivity) {
			ResponsiveUIActivity ra = (ResponsiveUIActivity) getActivity();
			ra.switchContent(fragment);
		}
	}	
}
