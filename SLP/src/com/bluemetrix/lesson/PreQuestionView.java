package com.bluemetrix.lesson;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class PreQuestionView extends Activity
{
	private Lesson myLesson;
	private TextView preText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pre_question_view);

		Intent i = getIntent();//get intent from previous activity
		myLesson = (Lesson)i.getExtras().getSerializable("lesson");
		
		preText = (TextView) findViewById(R.id.textViewPreEng);
		preText.setText(myLesson.getPreQuestion());
		
		new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run() 
			{
				// Create an intent that will start the main activity.
				Intent mainIntent = new Intent(PreQuestionView.this, AudioScreen.class);
				mainIntent.putExtra("option", 1);
				mainIntent.putExtra("lesson", myLesson);
				PreQuestionView.this.startActivity(mainIntent);

				// Apply splash exit (fade out) and main entry (fade in) animation transitions.
				overridePendingTransition(R.anim.mainfadein, R.anim.splashfadeout);
			}
		}, 8000);
	}	
}