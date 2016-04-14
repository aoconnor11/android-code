package com.bluemetrix.game.nodes;


import com.bluemetrix.R;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InitialNode extends Activity
{
	private int id;
	private int pointer;
	private TextView text;
	private RelativeLayout relInitial;
	private LinearLayout linInitial;

	public InitialNode(int id) 
	{
		this.id = id;
	}

	//Add View Details
	public void addNodeDetails(int id, TextView text, RelativeLayout relInitial)
	{
		if(id == 1)//Only one Initial View at the moment
		{
			text.setText(R.string.initialScene);
			relInitial.setBackgroundResource(R.drawable.scene1);
		}
	}	
}