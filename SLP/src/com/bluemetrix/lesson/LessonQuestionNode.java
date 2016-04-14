package com.bluemetrix.lesson;


import android.app.Activity;
import android.widget.TextView;

public class LessonQuestionNode extends Activity
{
	private int id;
	private String question1;
	private String question2;
	private String question3;
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
	
	public LessonQuestionNode(int id)
	{
		this.id = id;
	}
	
	public void addNodeDetails(int id, TextView questionText, TextView answerText, Lesson myLesson)
	{	
		option1A = myLesson.getOption1A();
		option1B = myLesson.getOption1B();
		option1C = myLesson.getOption1C();
		option1 = (option1A + "\n" + option1B + "\n" + option1C);//options for Question One
		question1 = myLesson.getQuestion1();
			
		option2A = myLesson.getOption1A();
		option2B = myLesson.getOption1B();
		option2C = myLesson.getOption1C();
		option2 = (option2A + "\n" + option2B + "\n" + option2C);//options for Question Two
		question2 = myLesson.getQuestion2();
			
		option3A = myLesson.getOption1A();
		option3B = myLesson.getOption1B();
		option3C = myLesson.getOption1C();
		option3 = (option3A + "\n" + option3B + "\n" + option3C);//options for Question Three
		question3 = myLesson.getQuestion3();		
			
		if(id == 1)
		{		
			questionText.setText(question1);
			answerText.setText(option1);				
		}
		if(id == 2)
		{	
			questionText.setText(question2);
			answerText.setText(option2);
		}
		if(id == 3)
		{		
			questionText.setText(question3);
			answerText.setText(option3);
		}
	}
}