package com.bluemetrix.game.nodes;


import com.bluemetrix.R;

import android.app.Activity;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SceneSelectionNode extends Activity
{
	private Button select;
	private Button home;
	private RelativeLayout rel;
	private int id;
	
	public SceneSelectionNode(int id)
	{
		this.id = id;
	}
	
	//Add View Details
	public void addNodeDetails(int id, Button select, RelativeLayout rel)
	{
		if(id == 1)//Only one Scene Selection View at the moment
		{		
			select.setText(R.string.buttonAtAirport);
			rel.setBackgroundResource(R.drawable.background);
		}
		else
		{
			//no other scene selection view at present
		}
	}	
}