package com.bluemetrix.lesson;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ExtrasScreen extends Activity
{
	RelativeLayout continueOption;
	int score;
	private Lesson myLesson;
	private String lessonText;
	private TextView extraText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.extras_view);
		
		Intent i = getIntent();
		score = i.getIntExtra("score", 0);
		myLesson = (Lesson)i.getExtras().getSerializable("lesson");
		extraText = (TextView) findViewById(R.id.textViewExtras);
		lessonText = myLesson.getExtras();
		
		extraText.setText(lessonText);//set extra text from lesson
		
		continueOption = (RelativeLayout) findViewById(R.id.relLayoutContinue);	
		continueOption.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				finishLesson();
			}
		});	
	}
	
	public void finishLesson() 
	{
		Intent in = new Intent(this, FinishLesson.class);
		in.putExtra("score", score);
		in.putExtra("lesson", myLesson);
		startActivity(in);
	}		
}