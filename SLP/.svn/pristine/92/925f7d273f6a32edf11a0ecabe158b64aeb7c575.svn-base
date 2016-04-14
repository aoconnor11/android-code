package com.bluemetrix.game.activities;



import java.util.Timer;
import java.util.TimerTask;

import com.bluemetrix.R;
import com.bluemetrix.game.nodes.FinalNode;
import com.bluemetrix.game.activities.FinalView;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FinalView extends Activity
{
	private TextView text2;
	private RelativeLayout relFinal;
	private int option;
	private int score;
	private String scoreString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_view);	
	
		Intent i = getIntent();
		option = i.getIntExtra("option", -1);
		score = i.getIntExtra("score", 0);
		
		//Setting Final View text
		text2 = (TextView) findViewById(R.id.textViewFinalView);
		
		//Setting background picture
		relFinal = (RelativeLayout) findViewById(R.id.relLayoutFinalView);

		scoreString = getResources().getString(R.string.score);
		
		//Customise view with text/background picture
		FinalNode node = new FinalNode(option);
		node.addNodeDetails(option, text2, relFinal);	
		node.addScore(option, score, text2, scoreString);
		
		if(option==1)
		{
			new Timer().schedule(new TimerTask()//display final view after score
			{
				public void run() 
				{ 
					Intent intent = new Intent(FinalView.this, FinalView.class);
					intent.putExtra("option", 2);
					startActivity(intent);
				}
			}, 2000);		//300 - 5 seconds
		}	
		
		if(option==2)
		{
			new Timer().schedule(new TimerTask()//display final view after score
			{
				public void run() 
				{ 
					Intent in = new Intent(FinalView.this, FragmentChangeActivity.class);
					in.putExtra("option", 4);	
					startActivity(in);
				}
			}, 2000);		//300 - 5 seconds
		}		
	}
}