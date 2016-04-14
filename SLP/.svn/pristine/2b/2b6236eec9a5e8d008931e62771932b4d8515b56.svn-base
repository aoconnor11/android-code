package com.bluemetrix.help;

import com.bluemetrix.R;
import com.bluemetrix.lesson.Lesson;
import com.slidingmenu.example.fragments.ColorFragment;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
@SuppressLint("ValidFragment")
public class AppHelpFragment extends Fragment
{
	private int option;
	private TextView exit;
	View myFragmentView; 
	
	public AppHelpFragment()
	{
	//empty constructor	
	}
	
	public AppHelpFragment(int option)
	{
		this.option = option;
		setRetainInstance(true);
	}	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		if(option==1)
		{//Home Page
			myFragmentView = inflater.inflate(R.layout.help_profile, container, false); 
			exit = (TextView) myFragmentView.findViewById(R.id.textViewExit);			

			exit.setOnClickListener(new View.OnClickListener() 
			{
	            public void onClick(View view) 
	            {  
	            	FragmentTransaction ft = getFragmentManager().beginTransaction();	           
	            	ft.setCustomAnimations(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
	             	ColorFragment newFragment = new ColorFragment(R.color.blue, 20);//Home Screen
	            	ft.replace(R.id.content_frame, newFragment, "homeFragment");
	            	// Start the animated transition.
	            	ft.commit();
	           	}
	        });
			
		}
		else if(option==2)
		{//Avatar Home Page
			myFragmentView = inflater.inflate(R.layout.help_avatar, container, false); 
			exit = (TextView) myFragmentView.findViewById(R.id.textViewExit);
			
			exit.setOnClickListener(new View.OnClickListener() 
			{
	            public void onClick(View view) 
	            {  
	            	FragmentTransaction ft = getFragmentManager().beginTransaction();
	            	ft.setCustomAnimations(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
	             	ColorFragment newFragment = new ColorFragment(R.color.blue, 1);//Home Screen
	            	ft.replace(R.id.content_frame, newFragment, "homeFragment");
	            	// Start the animated transition.
	            	ft.commit();   	
	           	}
	        });	
		}
		return myFragmentView; 
	}
}