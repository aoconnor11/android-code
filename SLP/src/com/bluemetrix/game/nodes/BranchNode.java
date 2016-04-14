package com.bluemetrix.game.nodes;


import com.bluemetrix.R;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BranchNode extends Activity
{
	private int id;
	
	public BranchNode(int id)
	{
		this.id = id;
	}
	
	//Add View Details
	public void addNodeDetails(int id, TextView branchText, ImageView avatar, RelativeLayout relBranch)
	{
		if(id == 1)
		{			
			branchText.setText(R.string.branchView);
			relBranch.setBackgroundResource(R.drawable.scene2);
			avatar.setImageResource(R.drawable.avatar);	
		}		
		if(id == 2)
		{
			branchText.setText(R.string.sightsOptions);
			relBranch.setBackgroundResource(R.drawable.scene8);
			avatar.setImageResource(R.drawable.avatar);	
		}
		else
		{
			branchText.setText(R.string.branchView);
			relBranch.setBackgroundResource(R.drawable.scene2);
			avatar.setImageResource(R.drawable.avatar);		
		}
	}	
}