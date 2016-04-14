package com.bluemetrix.lesson;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class WelcomeScreen extends Activity
{	
	TextView home;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome_view);
		
		// setContentView(R.layout.menu_view);
		new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run() 
			{
				// Create an intent that will start the main activity.
				Intent mainIntent = new Intent(WelcomeScreen.this, MenuScreen.class);
				WelcomeScreen.this.startActivity(mainIntent);

				// Apply splash exit (fade out) and main entry (fade in) animation transitions.
				overridePendingTransition(R.anim.mainfadein, R.anim.splashfadeout);
			}
		}, 8000);
	}
	
	public void goHome() //go to Splash Screen
	{
		Intent in = new Intent(this, SplashScreen.class);
		startActivity(in);
	}
}