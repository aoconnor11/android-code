package com.bluemetrix.help;

import com.bluemetrix.R;
import com.bluemetrix.avatar.CoverFlowExample;
import com.bluemetrix.avatar.MainView;
import com.bluemetrix.avatar.MoodView;
import com.bluemetrix.avatar.ShopsView;
import com.bluemetrix.login.LoginView;
import com.slidingmenu.example.fragments.ColorFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LearnMoreHelp extends Activity
{
	private TextView exit;
	private int option;
	private int legs;
	private int torso;
	private int hair;
	private int eyes;
	private int nose;
	private int mouth;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		Intent intent = getIntent();
	    Bundle b = intent.getExtras();
	    option = b.getInt("option",0);
		
		if(option==1)
		{
			setContentView(R.layout.info_layout);
		}
		else if(option==2)
		{
			setContentView(R.layout.help_avatar_mood);
		}
		else if(option==3)
		{
			setContentView(R.layout.help_avatar_swipe);
			torso = b.getInt("torsoID", -1);
			hair = b.getInt("hairID", -1);
			legs = b.getInt("legsID", -1);
			eyes = b.getInt("eyesID", -1);
			mouth = b.getInt("mouthID", -1);
			nose = b.getInt("noseID", -1);
		}
		else if(option==4)
		{
			setContentView(R.layout.help_avatar_store);
			eyes = b.getInt("eyesID", -1);
			mouth = b.getInt("mouthID", -1);
			nose = b.getInt("noseID", -1);
		}
		else if(option==5)
		{
			setContentView(R.layout.help_avatar_coverflow);
			eyes = b.getInt("eyesID", -1);
			mouth = b.getInt("mouthID", -1);
			nose = b.getInt("noseID", -1);
		}
		else if(option==6)
		{
			setContentView(R.layout.help_avatar_changing_room);
			torso = b.getInt("torsoID", -1);
			hair = b.getInt("hairID", -1);
			legs = b.getInt("legsID", -1);
			eyes = b.getInt("eyesID", -1);
			mouth = b.getInt("mouthID", -1);
			nose = b.getInt("noseID", -1);
		}
	     
		exit = (TextView) findViewById(R.id.textViewExit);
		
		exit.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            { 
            	if(option==1)
            	{
            		Intent in = new Intent(LearnMoreHelp.this, LoginView.class);            	
            		startActivity(in);
            		overridePendingTransition(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
            	}
            	else if(option==2)
            	{
            		Intent in = new Intent(LearnMoreHelp.this, MoodView.class);            	
            		startActivity(in);
            		overridePendingTransition(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
            	}
            	else if(option==3)
            	{
            		Intent in = new Intent(LearnMoreHelp.this, MainView.class);    
            		in.putExtra("legsID", legs);
            		in.putExtra("torsoID", torso);
            		in.putExtra("hairID", hair);
            		in.putExtra("eyesID", eyes);
            		in.putExtra("noseID", nose);
            		in.putExtra("mouthID", mouth);
            		startActivity(in);
            		overridePendingTransition(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
            	}
            	else if(option==4)
            	{
            		Intent in = new Intent(LearnMoreHelp.this, ShopsView.class);    
            		in.putExtra("eyesID", eyes);
            		in.putExtra("noseID", nose);
            		in.putExtra("mouthID", mouth);
            		startActivity(in);
            		overridePendingTransition(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
            	}
            	else if(option==5)
            	{
            		Intent in = new Intent(LearnMoreHelp.this, CoverFlowExample.class);    
            		in.putExtra("eyesID", eyes);
            		in.putExtra("noseID", nose);
            		in.putExtra("mouthID", mouth);	   
            		startActivity(in);
            		overridePendingTransition(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
            	}      
            	else if(option==6)
            	{
            		Intent in = new Intent(LearnMoreHelp.this, MainView.class);    
            		in.putExtra("legsID", legs);
            		in.putExtra("torsoID", torso);
            		in.putExtra("hairID", hair);
            		in.putExtra("eyesID", eyes);
            		in.putExtra("noseID", nose);
            		in.putExtra("mouthID", mouth);
            		startActivity(in);
            		overridePendingTransition(R.anim.bottom_slide_out, R.anim.bottom_slide_in);
            	}
           	}
        });		
	}	
}