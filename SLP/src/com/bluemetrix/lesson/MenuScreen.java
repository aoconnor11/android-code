package com.bluemetrix.lesson;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuScreen extends Activity
{
	RelativeLayout beginOption;
	RelativeLayout interOption;
	TextView home;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_view);

		beginOption = (RelativeLayout) findViewById(R.id.relLayoutBeg);
		interOption = (RelativeLayout) findViewById(R.id.relLayoutInter);
		
		beginOption.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				launchLesson("beginner");
			}
		});

		interOption.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				launchLesson("intermediate");
			}
		});
	}
	
	public void launchLesson(String level) 
	{
		Intent in = new Intent(this, LessonList.class);
		in.putExtra("level", level);
		startActivity(in);
	}
}