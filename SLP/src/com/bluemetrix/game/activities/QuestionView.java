package com.bluemetrix.game.activities;

import com.bluemetrix.R;
import com.bluemetrix.game.nodes.QuestionNode;
import com.bluemetrix.game.activities.BranchView;
import com.bluemetrix.game.activities.FinalView;
import com.bluemetrix.game.activities.QuestionView;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionView extends Activity
{
	private TextView questionText;
	private TextView questionTextAnswerA;
	private TextView questionTextAnswerB;
	private TextView questionTextAnswerC;
	private TextView scoreText;
	private String scoreString;
	private int score;
	private RelativeLayout relQuestion;
	private ImageView avatar;
	private Button answerA;
	private Button answerB;
	private Button answerC;
	private int option;
	private LinearLayout lin1;
	private LinearLayout lin2;
	private LinearLayout lin3;
	private LinearLayout lin4;
	private RelativeLayout relAudio;
	private AlphaAnimation animation1;
	private ImageView audioIcon;
	private MediaPlayer media;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question_view);	
		
		media = new MediaPlayer();
				
		relAudio = (RelativeLayout) findViewById(R.id.relLayoutQ);
		lin1 = (LinearLayout) findViewById(R.id.linearLayoutQ1);
		lin2 = (LinearLayout) findViewById(R.id.linearLayoutQ2);
		lin3 = (LinearLayout) findViewById(R.id.linearLayoutQ3);
		lin4 = (LinearLayout) findViewById(R.id.linearLayoutQ4);
		audioIcon = (ImageView) findViewById(R.id.audioIconGame);
		
		animation1 = new AlphaAnimation(0.0f, 1.0f);
		animation1.setDuration(1000);
		animation1.setStartOffset(3000);

		relAudio.startAnimation(animation1);
		lin1.startAnimation(animation1);
		lin2.startAnimation(animation1);
		lin3.startAnimation(animation1);
		lin4.startAnimation(animation1);
		
		//Get intent from previous activity
		Intent i = getIntent();
		option = i.getIntExtra("option", -1);
		score = i.getIntExtra("score", 0);//default is zero - initial score
				
		//Setting Question View text 
		questionText = (TextView) findViewById(R.id.textViewQuestionView);
		questionTextAnswerA = (TextView) findViewById(R.id.textViewQuestionAnswerA);
		questionTextAnswerB = (TextView) findViewById(R.id.textViewQuestionAnswerB);
		questionTextAnswerC = (TextView) findViewById(R.id.textViewQuestionAnswerC);		
				
		//Setting background picture 
		relQuestion = (RelativeLayout) findViewById(R.id.relLayoutQuestion);

		//Setting avatar 
		avatar = (ImageView) findViewById(R.id.avatarQuestion);
				
		//Setting score text
		scoreString = getResources().getString(R.string.score);
			
		
		scoreText = (TextView) findViewById(R.id.textViewScoreView);
				
		media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
		{
			public void onCompletion(MediaPlayer mp) 
			{
				finish(); // finish current screen
			}
		});		
		
		//set up buttons & action listeners
		audioIcon.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view)
			{
				if(option ==1||option==2||option==3||option==6||option==10||option==11)
				{
					//play audio
		            try 
		            {   
		            	//media.setDataSource(audioPath);
		            }
		            catch(Exception e)
		            {
		            	e.printStackTrace();
		            }	
		            media.start();
		            			
		            score = (score + 20);
		            answerA(score);	
				}
		        else if(option==4)
		        {
		        	score = (score + 20);
		            answerA(score);	          		
		        }
		        else if(option==5||option==7||option==8||option==9||option==12||option==13)
		        {
		        	score = (score + (-10));
		            answerA(score);
		        }		
			}
		});

		answerA = (Button) findViewById(R.id.buttonAnswerA);
		answerA.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				if(option ==1||option==2||option==3||option==6||option==10||option==11)
				{
					questionTextAnswerA.setTextColor(getResources().getColor(R.color.green));
		            score = (score + 20);
		            answerA(score);	
		        }
		        else if(option==4)
		        {
		            questionTextAnswerA.setTextColor(getResources().getColor(R.color.green));
		            score = (score + 20);
		            answerA(score);	          		
		        }
		        else if(option==5||option==7||option==8||option==9||option==12||option==13)
		        {
		            questionTextAnswerA.setTextColor(getResources().getColor(R.color.red));
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
				if(option ==1||option==2||option==3||option==4||option==6||option==7||option==9||option==10||option==11||option==13)
				{
					questionTextAnswerB.setTextColor(getResources().getColor(R.color.red));
		            score = (score + (-10));
		            answerB(score);	
		        }
				else if(option==5||option==8||option==12)
		        {
		            questionTextAnswerB.setTextColor(getResources().getColor(R.color.green));
		            score = (score + 20);
		            answerB(score);	          		
		        }
			}
		}); 
				
		answerC = (Button) findViewById(R.id.buttonAnswerC);
		if(option==4||option==6||option==7||option==8||option==9||option==10||option==11||option==12||option==13)
		{
			answerC.setVisibility(View.VISIBLE);	
		}
		else
		{
			answerC.setVisibility(View.GONE);//hide button for two option questions
		}	
		
		answerC.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {
            	if(option==4||option==6||option==8||option==10||option==11||option==12)
            	{
            		questionTextAnswerC.setTextColor(getResources().getColor(R.color.red));
            		score = (score + (-10));
            		answerC(score);
            	}
            	else if(option==7||option==9||option==13)
            	{
            		questionTextAnswerC.setTextColor(getResources().getColor(R.color.green));
            		score = (score + 20);
            		answerC(score);	          		
            	}
            }
        }); 
		
	
		//Customise view with text/background picture/avatar/score
		QuestionNode node = new QuestionNode(option);
		node.addNodeDetails(option, questionText, relQuestion, avatar, score, scoreText, scoreString, answerA, answerB, answerC, questionTextAnswerA, questionTextAnswerB, questionTextAnswerC);
		node.addScore(option, score, scoreText, scoreString);	
	}
	
	
	public void finish() 
	{
		media.stop();
		media.reset();
		media.release();
	}
	
	//Intents to lead to Question View and pointer
	private void answerA(int newScore)
	{	
		if(option==2)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 4);//pointer to next view
			in.putExtra("score", newScore);//brings current score to next view
			startActivity(in);
		}
		else if(option==3)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 5);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==6)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 8);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==7)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 9);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==8)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 10);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==9)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 11);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==10)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 12);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==11)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 13);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==12||option==13)
		{
			Intent in = new Intent(this, FinalView.class);
			in.putExtra("option", 1);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else
		{//branch view
			Intent in = new Intent(this, BranchView.class);
			in.putExtra("score", newScore);
			in.putExtra("option", 2);
			startActivity(in);
		}
	}
		
	private void answerB(int newScore)
	{
		if(option==2)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 4);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==3)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 5);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==6)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 8);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==7)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 9);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==8)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 10);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==9)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 11);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==10)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 12);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==11)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 13);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==12||option==13)
		{
			Intent in = new Intent(this, FinalView.class);
			in.putExtra("option", 1);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else
		{//branch view
			Intent in = new Intent(this, BranchView.class);
			in.putExtra("score", newScore);
			in.putExtra("option", 2);
			startActivity(in);
		}
	}
		
	private void answerC(int newScore)
	{
		if(option==4)
		{//branch view
			Intent in = new Intent(this, BranchView.class);
			in.putExtra("score", newScore);
			in.putExtra("option", 2);
			startActivity(in);
		}
		else if(option==6)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 8);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==7)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 9);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==8)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 10);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==9)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 11);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==10)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 12);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==11)
		{
			Intent in = new Intent(this, QuestionView.class);
			in.putExtra("option", 13);
			in.putExtra("score", newScore);
			startActivity(in);
		}
		else if(option==12||option==13)
		{
			Intent in = new Intent(this, FinalView.class);
			in.putExtra("option", 1);
			in.putExtra("score", newScore);
			startActivity(in);
		}
	}
}