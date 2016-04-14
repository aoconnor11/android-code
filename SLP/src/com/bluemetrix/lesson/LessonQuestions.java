package com.bluemetrix.lesson;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LessonQuestions extends Activity 
{
	private TextView questionText;
	private TextView answerText;
	private int score;
	private Button answerA;
	private Button answerB;
	private Button answerC;
	private int option;
	private Lesson myLesson;
	private String answer1;
	private String answer2;
	private String answer3;
	private String option1A;
	private String option1B;
	private String option1C;
	private String option1;	
	private String option2A;
	private String option2B;
	private String option2C;
	private String option2;
	private String option3A;
	private String option3B;
	private String option3C;
	private String option3;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_view_lesson);		

		//Get intent from previous activity
		Intent i = getIntent();
		option = i.getIntExtra("option", 1);
		score = i.getIntExtra("score", 0);//default is zero - initial score
		myLesson = (Lesson)i.getExtras().getSerializable("lesson");

		answer1 = myLesson.getAnswer1();
		answer2 = myLesson.getAnswer2();
		answer3 = myLesson.getAnswer3();

		//Setting Question View text 
		questionText = (TextView) findViewById(R.id.textViewQuestion);

		//Setting Answer View text 
		answerText = (TextView) findViewById(R.id.textViewAnswer);

		//set up buttons & action listeners
		answerA = (Button) findViewById(R.id.buttonAnswerA);
		answerA.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				if(answer1.equals("A") && option == 1)
				{
					//correct answer - first question
					score = (score + 20);
					answerA(score);	 		
				}
				else if(answer2.equals("A") && option == 2)
				{
					//correct answer - second question
					score = (score + 20);
					answerA(score);	
				}
				else if(answer3.equals("A") && option == 3)
				{
					//correct answer - third question
					score = (score + 20);
					answerA(score);	
				}
				else
				{
					//incorrect answer
					score = (score + (-10));
					answerA(score);
				}    	

			}
		}); 

		answerB = (Button) findViewById(R.id.buttonAnswerB);
		answerB.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{        	
				if(answer1.equals("B") && option == 1)
				{
					//correct answer - first question
					score = (score + 20);
					answerB(score);	          		
				}
				else if(answer2.equals("B") && option == 2)
				{
					//correct answer - second question
					score = (score + 20);
					answerB(score);	
				}
				else if(answer3.equals("B") && option == 3)
				{
					//correct answer - third question
					score = (score + 20);
					answerB(score);	
				}    	
				else
				{
					//incorrect answer
					score = (score + (-10));
					answerB(score);
				}	            	

			}
		}); 

		answerC = (Button) findViewById(R.id.buttonAnswerC);	
		answerC.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				if(answer1.equals("C") && option == 1)
				{
					//correct answer - first question
					score = (score + 20);
					answerC(score);	            		
				}
				else if(answer2.equals("C") && option == 2)
				{
					//correct answer - second question
					score = (score + 20);
					answerC(score);	
				}
				else if(answer3.equals("C") && option == 3)
				{
					//correct answer - third question
					score = (score + 20);
					answerC(score);	
				}
				else
				{
					//incorrect answer
					score = (score + (-10));
					answerC(score);
				}




			}
		}); 

		LessonQuestionNode node = new LessonQuestionNode(option);			
		node.addNodeDetails(option, questionText, answerText, myLesson);	
	}	


	//Intents to lead to Question View and pointer
	private void answerA(int newScore)
	{	
		if(option == 1)
		{
			Intent in = new Intent(this, LessonQuestions.class);
			in.putExtra("option", 2);//pointer to next view
			in.putExtra("score", newScore);//brings current score to next view
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}
		else if(option == 2)
		{
			Intent in = new Intent(this, LessonQuestions.class);
			in.putExtra("option", 3);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}
		else if(option == 3)
		{
			Intent in = new Intent(this, ExtrasScreen.class);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}		
	}

	private void answerB(int newScore)	
	{
		if(option == 1)
		{
			Intent in = new Intent(this, LessonQuestions.class);
			in.putExtra("option", 2);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}
		else if(option == 2)
		{
			Intent in = new Intent(this, LessonQuestions.class);
			in.putExtra("option", 3);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}
		else if(option == 3)
		{
			Intent in = new Intent(this, ExtrasScreen.class);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}			
	}

	private void answerC(int newScore)
	{
		if(option == 1)
		{
			Intent in = new Intent(this, LessonQuestions.class);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			in.putExtra("option", 2);
			startActivity(in);
		}
		else if(option == 2)
		{
			Intent in = new Intent(this, LessonQuestions.class);
			in.putExtra("option", 3);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}
		else if(option == 3)
		{
			Intent in = new Intent(this, ExtrasScreen.class);
			in.putExtra("score", newScore);
			in.putExtra("lesson", myLesson);
			startActivity(in);
		}
	}		
}