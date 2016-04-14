package com.bluemetrix.lesson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import com.bluemetrix.R;
import com.slidingmenu.example.fragments.FragmentChangeActivity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FinishLesson extends Activity
{
	RelativeLayout likeOption;
	RelativeLayout nextOption;
	int score;
	private TextView home;
	String scoreString;
	String scoreString2;
	TextView text2;
	JSONObject myObject;
	Lesson myLesson;
	String lessonID;
	int userID;
	String status;
	boolean networkStatus;
	private String url = "http://wiki.bmmetrix.com:9474/lessons/notifyLessonComplete/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.final_view_lesson);
		
		//Get info from previous activity
		Intent i = getIntent();
		score = i.getIntExtra("score", 0);
		myLesson = (Lesson)i.getExtras().getSerializable("lesson");
		lessonID = myLesson.getId();		
		userID = 34; //change to real user ID
		
		text2 = (TextView) findViewById(R.id.textViewScore);
		scoreString = getResources().getString(R.string.scoreLesson);
		scoreString2 = (score + " " + scoreString);
		text2.setText(scoreString2);			
		
		networkStatus = isNetworkAvailable();					
		if(networkStatus==false)
		{			
			Toast.makeText(FinishLesson.this, "Internet Connectivity has been lost. Please check your internet connection", Toast.LENGTH_LONG).show();			
		}		
		else
		{		
			try 
			{
				status = new UploadLessonStatus().execute().get();
				System.out.println("Status: " + status);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			} 
			catch (ExecutionException e) 
			{
				e.printStackTrace();
			}
						
			
			home = (TextView) findViewById(R.id.textViewHomeFinal);		
			home.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View view) 
				{
					goHome();
				}
			});
			
			likeOption = (RelativeLayout) findViewById(R.id.relLayoutLike);
			nextOption = (RelativeLayout) findViewById(R.id.relLayoutNext);
			
			likeOption.setOnClickListener(new View.OnClickListener()//like option no longer included?
			{
				public void onClick(View view) 
				{
					//likeLesson();
				}
			});
			
			nextOption.setOnClickListener(new View.OnClickListener()
			{
				public void onClick(View view) 
				{
					finishLesson();
				}
			});		
		}	
	}
	
	public void finishLesson() 
	{	
		Intent in = new Intent(this, FragmentChangeActivity.class);
		in.putExtra("option", 2);	
		startActivity(in);
	}
	
	public void goHome() 
	{
		Intent in = new Intent(this, FragmentChangeActivity.class);
		startActivity(in);
	}	
	

			
	//check if network is there before downloading
	public boolean isNetworkAvailable() 
	{
		ConnectivityManager cm = (ConnectivityManager) 
		getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		// if no network is available networkInfo will be null
		// otherwise check if we are connected
		if (networkInfo != null && networkInfo.isConnected()) 
		{
			return true;
		}
		return false;
	} 
			
		
	private class UploadLessonStatus extends AsyncTask<String, Void, String>
	{	
		String lessonStatus = "";
		String line;
		HashMap<String, String> mapBegin = new HashMap<String, String>();
		HashMap<String, String> mapInter = new HashMap<String, String>();
		HashMap<String, String> mapAdvan = new HashMap<String, String>();
						
		@Override
		protected String doInBackground(String... level) 
		{
			System.out.print("start upload");			 
			JSONObject object = new JSONObject();
			try 
			{
				object.put("lessonid", lessonID);
				object.put("userid", userID);
				object.put("mcqscore", new Integer(score));//persons id as well - to identify whose scores it is??
			} 	
			catch (JSONException e) 
			{
				e.printStackTrace();
				System.out.println("json exception");
			}				 
				System.out.println("JSON object: " + object); 
				StringEntity se = null;
				DefaultHttpClient httpclient = new DefaultHttpClient();
				HttpPost httppostreq = new HttpPost(url);
				try 
				{
					se = new StringEntity(object.toString());
				} 
				catch (UnsupportedEncodingException e) 
				{
					System.out.println("encoding exception");
					e.printStackTrace();
				}
				se.setContentType("application/json;charset=UTF-8");
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
				httppostreq.setEntity(se);
				try 
				{
					HttpResponse httpresponse = httpclient.execute(httppostreq);						
					/*Checking response */
			        if(httpresponse!=null)
			        {
			        	InputStream in = httpresponse.getEntity().getContent(); //Get the data in the entity
			        	//  lessonStatus = in.toString();
			        	BufferedReader r = new BufferedReader(new InputStreamReader(in));
			        	while ((line = r.readLine()) != null) 
			        	{
			            	System.out.println(line);
			            }
			            r.close();  
			            //    StringBuilder total = new StringBuilder();
			            // String line = "";
			            //   while ((line = r.readLine()) != null) {
			            //      total.append(line);
			            //  }    
			        }	
			        lessonStatus = line;
		            System.out.println("Status of http response: " + line);
				} 
				catch (ClientProtocolException e) 
				{
					System.out.println("client exception");
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
					System.out.println("IO exception");
				}
				return lessonStatus;
		}
			
		protected void onPostExecute(String result) 
		{	
			//list is downloaded here!!
		    // mImageView.setImageBitmap(result);
			lessonStatus =	result; 
			//	Toast.makeText(LessonList.this, "download List " + lessonMap.toString(), Toast.LENGTH_LONG).show();
			System.out.print("test status" + lessonStatus);     
		}
	}		
}