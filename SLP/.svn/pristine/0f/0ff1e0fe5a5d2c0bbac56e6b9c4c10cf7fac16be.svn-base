package com.bluemetrix.avatar;

import java.util.ArrayList;

import com.bluemetrix.R;
import com.bluemetrix.help.LearnMoreHelp;
import com.bluemetrix.login.LoginView;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MoodView extends Activity
{
	ImageView eyes;
	ImageView nose;
	ImageView mouth;
	ImageView help;
	int eyesID;
	int noseID;
	int mouthID;
	int eyesI = 0;
	int noseI = 0;
	int mouthI = 0;
	Button doneButton;
	RelativeLayout mouthRel;
	RelativeLayout eyesRel;
	RelativeLayout noseRel;
	
	int eyes1 = (R.drawable.eyes1);
	int eyes2 = (R.drawable.eyes2);
	int eyes3 = (R.drawable.eyes3);
	
	int mouth1 = (R.drawable.mouth1);
	int mouth2 = (R.drawable.mouth2);
	int mouth3 = (R.drawable.mouth3);
	
	int nose1 = (R.drawable.nose1);
	
	ArrayList<Integer> eyesImages;
	ArrayList<Integer> noseImages;
	ArrayList<Integer> mouthImages;

	TranslateAnimation moveLefttoRight;
	TranslateAnimation moveRighttoLeft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.avatar_mood_view);
		
		eyesImages = new ArrayList<Integer>();
		mouthImages = new ArrayList<Integer>();
		noseImages = new ArrayList<Integer>();
		
		Intent i = getIntent();
		eyesID = i.getIntExtra("eyesID", -1);
		mouthID = i.getIntExtra("mouthID", -1);
		noseID = i.getIntExtra("noseID", -1);
		
		checkEyes(eyesID);
		checkMouth(mouthID);	
		
		noseImages.add(nose1);
		
		mouthRel = (RelativeLayout) findViewById(R.id.relLayoutMouth);
		noseRel = (RelativeLayout) findViewById(R.id.relLayoutNose);
		eyesRel = (RelativeLayout) findViewById(R.id.relLayoutEyes);
		
		mouth = (ImageView) findViewById(R.id.imageViewAvatarMood7);
		eyes = (ImageView) findViewById(R.id.imageViewAvatarMood5);
		nose = (ImageView) findViewById(R.id.imageViewAvatarMood6);
		doneButton = (Button) findViewById(R.id.buttonAvatarMoodDone);
		help = (ImageView) findViewById(R.id.imageView1);
		
		System.out.println("id " + eyesID + mouthID + noseID);
		System.out.println("array " + eyesImages.get(2));
	
		mouth.setImageResource(mouthImages.get(0));
		eyes.setImageResource(eyesImages.get(0));
		nose.setImageResource(noseImages.get(0));
		
		//eyes
		eyesRel.setOnTouchListener(new OnSwipeTouchListener() 
		{
			public boolean onSwipeTop() 
			{//do nothing
	            Toast.makeText(MoodView.this, "top", Toast.LENGTH_SHORT).show();
	            System.out.println("swipe top");
	            return true;
	        }
	        public boolean onSwipeRight() 
	        {
	            Toast.makeText(MoodView.this, "right", Toast.LENGTH_SHORT).show();
	            System.out.println("swipe right eyes");
	            
	            int j = eyesImages.size();

	            if(eyesI==0)
	            {
	            	eyesI= j-1;	
	            }
	            else
	            {
	            	eyesI = (eyesI-1);
	            }

	            moveRighttoLeft = new TranslateAnimation(0,1000,0,0);
	            moveRighttoLeft.setDuration(250);//decrease duration - make quicker!
	            moveRighttoLeft.setFillAfter(true);
	            eyes.startAnimation(moveRighttoLeft);
			    moveEyesRight(eyesI);
	            return true;
	        }
	        public boolean onSwipeLeft() 
	        {
	            Toast.makeText(MoodView.this, "left", Toast.LENGTH_SHORT).show();
	            System.out.println("swipe left eyes");
	            int j = eyesImages.size();
	            
	            if(eyesI==j-1)
	            {	
	            	eyesI= 0;	
	            }
	            else
	            {
	            	eyesI = (eyesI+1);
	            }

	            moveLefttoRight = new TranslateAnimation(0, -1000, 0, 0);
			    moveLefttoRight.setDuration(250);
			    moveLefttoRight.setFillAfter(true);
			    eyes.startAnimation(moveLefttoRight);
			    moveEyes(eyesI);
	            return true;
	        }
	        public boolean onSwipeBottom() 
	        {//do nothing
	            Toast.makeText(MoodView.this, "bottom", Toast.LENGTH_SHORT).show();
	            System.out.println("swipe bottom");
	            return true;
	        }
	    });
		
		eyesRel.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View arg0) 
	        {
	        	System.out.println("click eyes");
	        }
	    });

		//mouth
		mouthRel.setOnTouchListener(new OnSwipeTouchListener() 
		{
			public boolean onSwipeTop() 
			{//do nothing
				Toast.makeText(MoodView.this, "top", Toast.LENGTH_SHORT).show();
				System.out.println("swipe top");
				return true;
			}
			public boolean onSwipeRight() 
			{
				Toast.makeText(MoodView.this, "right", Toast.LENGTH_SHORT).show();
				System.out.println("swipe right mouth");
			            
				int j = mouthImages.size();

				if(mouthI==0)
				{        	
					mouthI= j-1;    	
			    }
			    else
			    {        
			    	mouthI = (mouthI-1);
			    }

			    moveRighttoLeft = new TranslateAnimation(0,1000,0,0);
			    moveRighttoLeft.setDuration(250);//decrease duration - make quicker!
			    moveRighttoLeft.setFillAfter(true);
			    mouth.startAnimation(moveRighttoLeft);
			    moveMouthRight(mouthI);
			    return true;
			}
			public boolean onSwipeLeft() 
			{
				Toast.makeText(MoodView.this, "left", Toast.LENGTH_SHORT).show();
				System.out.println("swipe left mouth");  

				int j = mouthImages.size();
			            
				if(mouthI==j-1)
				{       	
					mouthI= 0;    	
			    }
			    else
			    {
			    	mouthI = (mouthI+1);
			    }
				moveLefttoRight = new TranslateAnimation(0, -1000, 0, 0);
				moveLefttoRight.setDuration(250);
				moveLefttoRight.setFillAfter(true);
				mouth.startAnimation(moveLefttoRight);
				moveMouth(mouthI);
			    return true;
			}
			public boolean onSwipeBottom() 
			{//do nothing
				Toast.makeText(MoodView.this, "bottom", Toast.LENGTH_SHORT).show();
				System.out.println("swipe bottom");
				return true;
			}
		});
				
		mouthRel.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				System.out.println("click mouth");
			}
		});

		//nose
		noseRel.setOnTouchListener(new OnSwipeTouchListener() 
		{
			public boolean onSwipeTop() 
			{//do nothing
				Toast.makeText(MoodView.this, "top", Toast.LENGTH_SHORT).show();
			    System.out.println("swipe top");
			    return true;
			}
			public boolean onSwipeRight() 
			{
				Toast.makeText(MoodView.this, "right", Toast.LENGTH_SHORT).show();
				System.out.println("swipe right nose");
			            
				int j = noseImages.size();

				if(noseI==0)
				{        	
					noseI= j-1;    	
				}
				else
				{
					noseI = (noseI-1);
				}
  
			    moveRighttoLeft = new TranslateAnimation(0,1000,0,0);
			    moveRighttoLeft.setDuration(250);//decrease duration - make quicker!
			    moveRighttoLeft.setFillAfter(true); 
			    nose.startAnimation(moveRighttoLeft);
				moveNoseRight(noseI);
			    return true;
			}
			public boolean onSwipeLeft() 
			{        	
				Toast.makeText(MoodView.this, "left", Toast.LENGTH_SHORT).show();
			    System.out.println("swipe left nose");
			    int j = noseImages.size();
			            
			    if(noseI==j-1)
			    {        	
			    	noseI= 0;
			    }
			    else
			    {        
			    	noseI = (noseI+1);
			    }
			    moveLefttoRight = new TranslateAnimation(0, -1000, 0, 0);
			    moveLefttoRight.setDuration(250);
				moveLefttoRight.setFillAfter(true);
				nose.startAnimation(moveLefttoRight);
				moveNose(noseI); 
			    return true;
			}
			public boolean onSwipeBottom() 
			{//do nothing
				Toast.makeText(MoodView.this, "bottom", Toast.LENGTH_SHORT).show();
			    System.out.println("swipe bottom");
			    return true;
			}
		});
		noseRel.setOnClickListener(new View.OnClickListener() 
		{
			@Override
			public void onClick(View arg0) 
			{
				System.out.println("click nose");
			}
		});
		

		doneButton.setOnClickListener(new View.OnClickListener() 
		{
			@Override
	        public void onClick(View arg0) 
			{
	        	int eyes = eyesImages.get(eyesI);
	        	int mouth = mouthImages.get(mouthI);
	        	int nose = noseImages.get(noseI);
	
	        	Intent in = new Intent(MoodView.this, FragmentChangeActivity.class);
				in.putExtra("option", 1);	
				in.putExtra("eyesID", eyes);
				in.putExtra("mouthID", mouth);
				in.putExtra("noseID", nose);
				startActivity(in);	
	        }
	    });	
		
		
		help.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View arg0) 
	        {	
	        	Intent in = new Intent(MoodView.this, LearnMoreHelp.class);
	        	in.putExtra("option", 2);
				startActivity(in);		
				overridePendingTransition(R.anim.bottom_slide_in, R.anim.bottom_slide_out);
	        }
	    });
	}
	

	
	private void checkMouth(int mouthID)
	{
		if(mouthID==mouth1)
		{
			mouthImages.add(mouth1);
			mouthImages.add(mouth2);
			mouthImages.add(mouth3);
		}
		else if(mouthID==mouth2)
		{
			mouthImages.add(mouth2);
			mouthImages.add(mouth3);
			mouthImages.add(mouth1);
		}
		else
		{
			mouthImages.add(mouth3);
			mouthImages.add(mouth1);
			mouthImages.add(mouth2);
		}
	}
	
	private void checkEyes(int eyesID)
	{
		if(eyesID==eyes1)
		{
			eyesImages.add(eyes1);
			eyesImages.add(eyes2);
			eyesImages.add(eyes3);
		}
		else if(eyesID==eyes2)
		{
			eyesImages.add(eyes2);
			eyesImages.add(eyes3);
			eyesImages.add(eyes1);
		}
		else{
			eyesImages.add(eyes3);
			eyesImages.add(eyes1);
			eyesImages.add(eyes2);
		}
	}
	
	private void moveEyesRight(final int i)
	{
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() 
	    { 
	         public void run() 
	         { 	 
	        	 moveLefttoRight = new TranslateAnimation(-1000,0,0,0);
	        	 moveLefttoRight.setDuration(250);
	        	 moveLefttoRight.setFillAfter(true);
	     	     eyes.setImageResource(eyesImages.get(i));
	     	     eyes.startAnimation(moveLefttoRight);
	         } 
	    }, 500);
	}
	
	private void moveEyes(final int i)
	{
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() 
	    { 
	    	public void run() 
	    	{ 
	    		moveRighttoLeft = new TranslateAnimation(1000, 0, 0, 0);
	     	    moveRighttoLeft.setDuration(250);
	     	    moveRighttoLeft.setFillAfter(true);
	     	    eyes.setImageResource(eyesImages.get(i));
	     	    eyes.startAnimation(moveRighttoLeft);
	         } 
	    }, 500);
	}

	private void moveNoseRight(final int i)
	{
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() 
	    { 
	    	public void run() 
	    	{ 
	    		moveLefttoRight = new TranslateAnimation(-1000,0,0,0);
	        	moveLefttoRight.setDuration(250);
	        	moveLefttoRight.setFillAfter(true);
	     	    nose.setImageResource(noseImages.get(i));
	     	    nose.startAnimation(moveLefttoRight);
	         } 
	    }, 500);
	}
	
	private void moveNose(final int i)
	{
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() 
	    { 
	    	public void run() 
	    	{ 
	    		moveRighttoLeft = new TranslateAnimation(1000, 0, 0, 0);
	     	    moveRighttoLeft.setDuration(250);
	     	    moveRighttoLeft.setFillAfter(true);
	     	    nose.setImageResource(noseImages.get(i));
	     	    nose.startAnimation(moveRighttoLeft);
	         } 
	    }, 500);
	}

	private void moveMouthRight(final int i)
	{
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() 
	    { 
	    	public void run() 
	    	{ 
	    		moveLefttoRight = new TranslateAnimation(-1000,0,0,0);
	        	moveLefttoRight.setDuration(250);
	        	moveLefttoRight.setFillAfter(true);
	     	    mouth.setImageResource(mouthImages.get(i));
	     	    mouth.startAnimation(moveLefttoRight);
	         } 
	    }, 500);
	}
	
	private void moveMouth(final int i)
	{
		Handler handler = new Handler(); 
	    handler.postDelayed(new Runnable() 
	    { 
	    	public void run() 
	    	{ 
	    		moveRighttoLeft = new TranslateAnimation(1000, 0, 0, 0);
	     	    moveRighttoLeft.setDuration(250);
	     	    moveRighttoLeft.setFillAfter(true);
	     	    mouth.setImageResource(mouthImages.get(i));
	     	    mouth.startAnimation(moveRighttoLeft);
	         } 
	    }, 500);
	}
}
