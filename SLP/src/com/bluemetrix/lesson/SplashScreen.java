package com.bluemetrix.lesson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

public class SplashScreen extends Activity
{
	RelativeLayout optionOne;
	RelativeLayout optionTwo;
	RelativeLayout optionThree;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.splash_screen);

		//	AssetManager assetManager = getAssets();
		optionOne = (RelativeLayout) findViewById(R.id.relLayoutSplash1);
		optionTwo = (RelativeLayout) findViewById(R.id.relLayoutSplash2);
		optionThree = (RelativeLayout) findViewById(R.id.relLayoutSplash3);
		


		
		optionOne.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				doOptionOne();//temp link to lesson questions - to test lesson questions
			}
		});
		
		optionTwo.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				doOptionTwo();
			}
		});
		
		optionThree.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
			//	doOptionThree();
			}
		});		
	}
	
	
	private void copyAssets() 
	{	
		String newFolder = "/lesson";
	    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
	    File myNewFolder = new File(extStorageDirectory + newFolder);
	    myNewFolder.mkdir();

		File file = new File(Environment.getExternalStorageDirectory(), "/mnt/sdcard/lesson/");
		if (!file.exists()) 
		{
			if (!file.mkdirs()) 
			{
				Log.e("TravellerLog :: ", "Problem creating Image folder");		           
		    }
		}
	
		        
		File direct = new File(Environment.getExternalStorageDirectory().getPath() + "/lesson");
		if(!direct.exists())
		{
			if(direct.mkdir()) 
			{
				//directory is created;
		    }
		}
		
	    AssetManager assetManager = getAssets();
	    String[] files = null;
	    try 
	    {
	    	files = assetManager.list("");
	    } 
	    catch (IOException e) 
	    {
	        Log.e("tag", "Failed to get asset file list.", e);
	    }
	    for(String filename : files) 
	    {
	        InputStream in = null;
	        OutputStream out = null;
	        try 
	        {
	        	in = assetManager.open(filename);
	        	out = new FileOutputStream(myNewFolder + filename);
	        	copyFile(in, out);
	        	in.close();
	        	in = null;
	        	out.flush();
	        	out.close();
	        	out = null;
	        } 
	        catch(IOException e) 
	        {
	            Log.e("tag", "Failed to copy asset file: " + filename, e);
	        }       
	    }
	}
	
	private void copyFile(InputStream in, OutputStream out) throws IOException 
	{
	    byte[] buffer = new byte[1024];
	    int read;
	    while((read = in.read(buffer)) != -1)
	    {
	    	out.write(buffer, 0, read);
	    }
	}
		
	public void doOptionTwo() //Lesson?
	{
		Intent in = new Intent(this, WelcomeScreen.class);
		startActivity(in);
	}
	
	public void doOptionOne() //Lesson questions
	{
		Intent in = new Intent(this, LessonQuestions.class);
		startActivity(in);
	}	
}