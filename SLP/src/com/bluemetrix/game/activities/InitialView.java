package com.bluemetrix.game.activities;

import java.util.Timer;
import java.util.TimerTask;

import com.bluemetrix.R;
import com.bluemetrix.game.activities.BranchView;
import com.bluemetrix.game.activities.InitialView;
import com.bluemetrix.game.nodes.InitialNode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InitialView extends Activity
{	
	private TextView text1;
	private RelativeLayout relInitial;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.initial_view);		
		
		//Setting Initial View Text
		text1 = (TextView) findViewById(R.id.textViewInitialView);

		//Setting Background Picture
		relInitial = (RelativeLayout) findViewById(R.id.relLayoutInitialView);

		//Customise view with text/background picture
		InitialNode node = new InitialNode(1);
		node.addNodeDetails(1, text1, relInitial);
		
		//Timer to automatically start Branch View Activity
		new Timer().schedule(new TimerTask()
		{
		    public void run() 
		    { 
		    	Intent intent = new Intent(InitialView.this,BranchView.class);
                startActivity(intent);
                finish();
		    }
		}, 2000);
	}	
}
