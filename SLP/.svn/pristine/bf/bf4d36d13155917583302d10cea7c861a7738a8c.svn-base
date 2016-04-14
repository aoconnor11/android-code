package com.bluemetrix.game.nodes;


import com.bluemetrix.R;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuestionNode extends Activity
{
	private int id;
	private String scoreString2;
	
	public QuestionNode(int id)
	{
		this.id = id;
	}
	
	//Add View Details - for each Question View
	public void addNodeDetails(int id, TextView questionText, RelativeLayout relQuestion, ImageView avatar, int score, TextView scoreText, String scoreString, Button button1, Button button2, Button button3, TextView questionTextAnswerA, TextView questionTextAnswerB, TextView questionTextAnswerC)
	{
		avatar.setImageResource(R.drawable.avatar);	//the same for all views
		
		if(id == 1)
		{		
			questionText.setText(R.string.questionView);
			questionTextAnswerA.setText(R.string.questionViewA);
			questionTextAnswerB.setText(R.string.questionViewB);
			questionTextAnswerC.setText("");
			relQuestion.setBackgroundResource(R.drawable.scene3);
		}
		if(id == 2)
		{		
			questionText.setText(R.string.busQuestion);
			questionTextAnswerA.setText(R.string.busQuestionA);
			questionTextAnswerB.setText(R.string.busQuestionB);
			questionTextAnswerC.setText("");
			relQuestion.setBackgroundResource(R.drawable.scene4);
		}
		if(id == 3)
		{		
			questionText.setText(R.string.carQuestion);
			questionTextAnswerA.setText(R.string.carQuestionA);
			questionTextAnswerB.setText(R.string.carQuestionB);
			relQuestion.setBackgroundResource(R.drawable.scene6);
			questionTextAnswerC.setText("");
		}
		if(id == 4)
		{		
			questionText.setText(R.string.busQuestion2);
			questionTextAnswerA.setText(R.string.busQuestion2A);
			questionTextAnswerB.setText(R.string.busQuestion2B);
			questionTextAnswerC.setText(R.string.busQuestion2C);
			relQuestion.setBackgroundResource(R.drawable.scene5);
		}
		if(id == 5)
		{		
			questionText.setText(R.string.carQuestion2);
			questionTextAnswerA.setText(R.string.carQuestion2A);
			questionTextAnswerB.setText(R.string.carQuestion2B);
			questionTextAnswerC.setText("");
			relQuestion.setBackgroundResource(R.drawable.scene7);
		}
		if(id == 6)
		{		
			questionText.setText(R.string.capitalTurkeyQ);
			questionTextAnswerA.setText(R.string.capitalTurkeyQA);
			questionTextAnswerB.setText(R.string.capitalTurkeyQB);
			questionTextAnswerC.setText(R.string.capitalTurkeyQC);
			relQuestion.setBackgroundResource(R.drawable.q1);
		}
		if(id == 7)
		{		
			questionText.setText(R.string.populationTurkeyQ);
			questionTextAnswerA.setText(R.string.populationTurkeyQA);
			questionTextAnswerB.setText(R.string.populationTurkeyQB);
			questionTextAnswerC.setText(R.string.populationTurkeyQC);
			relQuestion.setBackgroundResource(R.drawable.q2);
		}
		if(id == 8)
		{		
			questionText.setText(R.string.ottomanEmpireQ);
			questionTextAnswerA.setText(R.string.ottomanEmpireQA);
			questionTextAnswerB.setText(R.string.ottomanEmpireQB);
			questionTextAnswerC.setText(R.string.ottomanEmpireQC);
			relQuestion.setBackgroundResource(R.drawable.q3);
		}
		if(id == 9)
		{		
			questionText.setText(R.string.provincesTurkeyQ);
			questionTextAnswerA.setText(R.string.provincesTurkeyQA);
			questionTextAnswerB.setText(R.string.provincesTurkeyQB);
			questionTextAnswerC.setText(R.string.provincesTurkeyQC);
			relQuestion.setBackgroundResource(R.drawable.q4);
		}
		if(id == 10)
		{		
			questionText.setText(R.string.biggestCityTurkeyQ);
			questionTextAnswerA.setText(R.string.biggestCityTurkeyQA);
			questionTextAnswerB.setText(R.string.biggestCityTurkeyQB);
			questionTextAnswerC.setText(R.string.biggestCityTurkeyQC);
			relQuestion.setBackgroundResource(R.drawable.q5);
		}
		if(id == 11)
		{		
			questionText.setText(R.string.topkapiQ);
			questionTextAnswerA.setText(R.string.topkapiQA);
			questionTextAnswerB.setText(R.string.topkapiQB);
			questionTextAnswerC.setText(R.string.topkapiQC);
			relQuestion.setBackgroundResource(R.drawable.topkapi);
		}
		if(id == 12)
		{		
			questionText.setText(R.string.populationIstanbulQ);
			questionTextAnswerA.setText(R.string.populationIstanbulQA);
			questionTextAnswerB.setText(R.string.populationIstanbulQB);
			questionTextAnswerC.setText(R.string.populationIstanbulQC);
			relQuestion.setBackgroundResource(R.drawable.q6);
		}
		if(id == 13)
		{		
			button1.setBackgroundResource(R.drawable.button_small);
			questionTextAnswerA.setText(R.string.secondSportTurkeyQA);
			questionTextAnswerB.setText(R.string.secondSportTurkeyQB);
			questionTextAnswerC.setText(R.string.secondSportTurkeyQC);
			questionText.setText(R.string.secondSportTurkeyQ);
			relQuestion.setBackgroundResource(R.drawable.q7);
		}
	}
	
	//Add score details
	public void addScore(int id, int score,TextView scoreText, String scoreString)
	{
		scoreString2 = (scoreString + " " + score);
		scoreText.setText(scoreString2);
	}	
}