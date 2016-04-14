package com.bluemetrix.game.activities;

import com.bluemetrix.R;
import com.bluemetrix.game.nodes.BranchNode;
import com.bluemetrix.game.activities.QuestionView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BranchView extends Activity
{
	private TextView branchText;
	private RelativeLayout relBranch;
	private ImageView avatar;
	private Button optionA;
	private Button optionB;
	private Button optionC;
	private int option;
	private int score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.branch_view);
		
		Intent i = getIntent();
		option = i.getIntExtra("option", 1);
		score = i.getIntExtra("score", 0);
		
		//Setting Branch View text
		branchText = (TextView) findViewById(R.id.textViewBranchView);
				
		//Setting background picture
		relBranch = (RelativeLayout) findViewById(R.id.relLayoutBranch);

		//Setting avatar 
		avatar = (ImageView) findViewById(R.id.avatar);
		
		//Set up on click listeners on buttons
		optionA = (Button) findViewById(R.id.buttonOptionA);
		optionA.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				viewQuestionA();
		    }
		}); 
				
		optionB = (Button) findViewById(R.id.buttonOptionB);
		optionB.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
		    {
				viewQuestionB();
		    }
		}); 
		
		optionC = (Button) findViewById(R.id.buttonOptionC);
		if(option==1)
		{
			optionC.setVisibility(View.VISIBLE);	
		}
		else
		{
			optionC.setVisibility(View.GONE);//hide button for two option branches
		}	
			optionC.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View view) 
				{ 
					if(option==1)
					{
						viewQuestionC();
					}
				}
			}); 

			//Customise view with text/avatar/background picture
			BranchNode node = new BranchNode(option);
			node.addNodeDetails(option, branchText, avatar, relBranch);
	}
	
	//Intents to lead to Question View and pointer
	private void viewQuestionA()
	{
		if(option==1)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 1);
			startActivity(in);
		}
		if(option==2)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 6);
			in.putExtra("score", score);
			startActivity(in);
		}
	}
		
	private void viewQuestionB()
	{
		if(option==1)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 2);
			startActivity(in);
		}
		if(option==2)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 7);
			in.putExtra("score", score);
			startActivity(in);
		}
	}
		
	private void viewQuestionC()
	{
		if(option==1)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 3);
			startActivity(in);
		}
	}	
}