package com.bluemetrix.lesson;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.bluemetrix.R;
import com.bluemetrix.lesson.SimpleGestureFilter.SimpleGestureListener;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AudioScreen extends Activity implements SimpleGestureListener 
{
	TextView completed;
	TextView counterText;
	TextView audioText;
	TextView transcriptText;
	MediaPlayer media;
	RelativeLayout relLayout;
	RelativeLayout relPause;
	RelativeLayout trans;
	LinearLayout linLayout;
	MyCount counter;
	int option;
	private SimpleGestureFilter detector;
	private String audioPath;
	boolean isComplete = false;
	private Lesson myLesson;
	private String lessonID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.audio_view);

		Intent i = getIntent();
		option = i.getIntExtra("option", -1);
		myLesson = (Lesson)i.getExtras().getSerializable("lesson");
		
		transcriptText = (TextView) findViewById(R.id.textViewTranscript);
		
		audioPath = myLesson.getAudioPath();
		lessonID = myLesson.getId();
		transcriptText.setText(myLesson.getTranscript());
		
		String myPath = downloadAudio(audioPath, lessonID);
	
		media = new MediaPlayer();
		
		try 
		{   
			media.setDataSource(myPath);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
		detector = new SimpleGestureFilter(this, this);

		media.setOnCompletionListener(new MediaPlayer.OnCompletionListener() 
		{
			public void onCompletion(MediaPlayer mp) 
			{
				finish(); // finish current screen
			}
		});

		linLayout = (LinearLayout) findViewById(R.id.linearLayoutTrans);
		completed = (TextView) findViewById(R.id.textViewAudio2);
		counterText = (TextView) findViewById(R.id.textViewCount);
		audioText = (TextView) findViewById(R.id.textViewAudio);
		relLayout = (RelativeLayout) findViewById(R.id.relLayout);
		relPause = (RelativeLayout) findViewById(R.id.relLayoutPause);
		trans = (RelativeLayout) findViewById(R.id.relLayoutPauseTransript);

		if (option == 1 || option == 2 || option == 7) //audio screens
		{
			trans.setVisibility(View.GONE);// hide until transcript screen
			linLayout.setVisibility(View.GONE);
			
		} 
		else// Transcript screens
		{
			
		}

		completed.setVisibility(View.GONE);
		// text is invisible until audio is completed 

		counter = new MyCount(4000, 1000);
		
		if (option == 4 || option == 6) 
		{
			completed.setVisibility(View.VISIBLE);
		}

		/*
		 * else
		 * //assume counter starts automatically - if not see below! 
		 * {
		 * counter.start(); 
		 * }
		 */

		if (option == 1 || option == 2 || option == 3 || option == 5 || option == 7) 
		{
			 relPause.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View view) 
				{
					if (media.isPlaying() && isComplete == false) 
					{
						media.pause();
					}
					else if (media.isPlaying() == false && isComplete == false) 
					{
						counter.start(); // if you have to tap on screen to start audio
					}
					else 
					{
						media.start();
					}
				}
			});
		}
	}

	public void finish() 
	{
		media.stop();
		media.reset();
		media.release();
		isComplete = true;
		completed.setVisibility(View.VISIBLE);
		// relPause.setOnClickListener(null);
		// disable on click listener so it doesn't interfere with swipe
		relPause.setOnClickListener(null);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) 
	{
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) 
	{
		switch (direction) 
		{
			case SimpleGestureFilter.SWIPE_RIGHT:
				// do nothing
				break;

			case SimpleGestureFilter.SWIPE_LEFT:
		
				if (option == 1 && isComplete == true) 
				{
					Toast.makeText(this, "Option One-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, AudioScreen.class);
					mainIntent.putExtra("option", 2);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to audio page again
				}
				else if (option == 2 && isComplete == true) 
				{
					Toast.makeText(this, "Option Two-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, AudioScreen.class);
					mainIntent.putExtra("option", 3);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to transcript page
				}
				else if (option == 3 && isComplete == true) 
				{
					Toast.makeText(this, "Option Three-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, AudioScreen.class);
					mainIntent.putExtra("option", 4);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to transcript page - no audio
				}
				else if (option == 4) 
				{
					Toast.makeText(this, "Option Four-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, AudioScreen.class);
					mainIntent.putExtra("option", 5);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to transcript page - with audio
				}
				else if (option == 5 && isComplete == true) 
				{
					Toast.makeText(this, "Option Five-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, AudioScreen.class);
					mainIntent.putExtra("option", 6);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to transcript page - no audio
				}
				else if (option == 6) 
				{
					Toast.makeText(this, "Option Six-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, AudioScreen.class);
					mainIntent.putExtra("option", 7);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to first audio screen
				}
				else if (option == 7)
				{
					Toast.makeText(this, "Option Seven-left", Toast.LENGTH_SHORT).show();
					Intent mainIntent = new Intent(AudioScreen.this, LessonQuestions.class);
					mainIntent.putExtra("option", 1);
					mainIntent.putExtra("lesson", myLesson);
					AudioScreen.this.startActivity(mainIntent);
					// go to first question on the lesson
				}
			
				break;

			case SimpleGestureFilter.SWIPE_DOWN: // do nothing
				break;

			case SimpleGestureFilter.SWIPE_UP: // do nothing
				break;
		}
	}

	@Override
	public void onDoubleTap()
	{
		Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
	}

	// countdowntimer is an abstract class, so extend it and fill in methods
	public class MyCount extends CountDownTimer 
	{
		public MyCount(long millisInFuture, long countDownInterval) 
		{
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() 
		{
			counterText.setText(getString(R.string.count0));
			// play audio
			media.start();
		}

		@Override
		public void onTick(long millisUntilFinished) 
		{
			long sec = (millisUntilFinished / 1000);
			
			if (sec == 3) 
			{
				counterText.setText(getString(R.string.count3));
			} 
			else if (sec == 2) 
			{
				counterText.setText(getString(R.string.count2));
			} 
			else if (sec == 1) 
			{
				counterText.setText(getString(R.string.count1));
			}
		}
	}	
	
	public String downloadAudio(String path, String id)
	{	
		String filepath=null;
	    try 
	    {
	    	//set the download URL, a url that points to a file on the internet
	    	//this is the file to be downloaded
	    	URL url = new URL(path);
	    	//create the new connection
	    	HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	      
	    	//set up some things on the connection
	    	urlConnection.setRequestMethod("GET");
	    	urlConnection.setDoOutput(true); 
	    	//and connect!
	    	urlConnection.connect();
	    	//set the path where we want to save the file
	    	//in this case, going to save it on the root directory of the
	    	//sd card.
	    	File SDCardRoot = Environment.getExternalStorageDirectory();
	    	//create a new file, specifying the path, and the filename
	    	//which we want to save the file as.
	      
	    	//is audio in MP3 format????
	      
	    	String filename= (id + ".mp3");   
	    	// you can download to any type of file ex:.jpeg (image) ,.txt(text file),.mp3 (audio file)
	    	Log.i("Local filename:",""+filename);
	    	File file = new File(SDCardRoot,filename);
	    	if(file.createNewFile())
	    	{
	    		file.createNewFile();
	    	}
	      
	    	//this will be used to write the downloaded data into the file we created
	    	FileOutputStream fileOutput = new FileOutputStream(file);

	    	//this will be used in reading the data from the internet
	    	InputStream inputStream = urlConnection.getInputStream();

	    	//this is the total size of the file
	    	int totalSize = urlConnection.getContentLength();
	    	//variable to store total downloaded bytes
	    	int downloadedSize = 0;

	    	//create a buffer...
	    	byte[] buffer = new byte[4 * 1024];
	    	int bufferLength = 0; //used to store a temporary size of the buffer

	    	//now, read through the input buffer and write the contents to the file
	    	while ( (bufferLength = inputStream.read(buffer)) > 0 )
	    	{
	    		//add the data in the buffer to the file in the file output stream (the file on the sd card
	    		fileOutput.write(buffer, 0, bufferLength);
	    		//add up the size so we know how much is downloaded
	    		downloadedSize += bufferLength;
	    		//this is where you would do something to report the prgress, like this maybe
	    		Log.i("Progress:","downloadedSize:"+downloadedSize+"totalSize:"+ totalSize) ;
	    	}
	    	//close the output stream when done
	    	fileOutput.close();
	    	if(downloadedSize==totalSize)   filepath=file.getPath();
	      
	    	//catch some possible errors...
	     	} 
	    	catch (MalformedURLException e) 
	    	{
	    		e.printStackTrace();
	    	} 
	    	catch (IOException e) 
	    	{
	    		filepath=null;
	    		e.printStackTrace();
	    	}
	     	Log.i("filepath:"," "+filepath) ;

	     	return filepath;
	}	
}