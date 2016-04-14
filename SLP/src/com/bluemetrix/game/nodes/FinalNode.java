package com.bluemetrix.game.nodes;


import com.bluemetrix.R;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FinalNode extends Activity 
{
	private int id;
	private String scoreString2;
	
	public FinalNode(int id)
	{
		this.id = id;
	}
	
	//Add View Details
	public void addNodeDetails(int id, TextView text2, RelativeLayout relFinal)
	{
		if(id == 1)
		{	
			relFinal.setBackgroundResource(R.drawable.grand_bazaar);
		}
		if(id == 2)
		{	
			text2.setText(R.string.finalScene);
			relFinal.setBackgroundResource(R.drawable.grand_bazaar);
		}
	}
	
	//Add score details
	public void addScore(int id, int score, TextView scoreText, String scoreString)
	{
		if(id==1)
		{
		scoreString2 = (scoreString + " " + score);
		scoreText.setText(scoreString2);
		}
	}
}