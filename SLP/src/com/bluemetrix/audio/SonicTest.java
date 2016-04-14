// This file was written by me, Bill Cox in 2011.
// I place this file into the public domain.  Feel free to copy from it.
// Note, however that libsonic, which this application links to,
// is licensed under LGPL.  You can link to it in your commercial application,
// but any changes you make to sonic.c or sonic.h need to be shared under
// the LGPL license.

package com.bluemetrix.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SonicTest
{
   private File myAudio;
   private String animal;
   
   
   public SonicTest()
   {
	   
   }
   
   public SonicTest(File myFile, String animal)
   {
	   this.myAudio = myFile;
	   this.animal = animal;
   }
	
	
   public void playAudio()
   {
	   new Thread(new Runnable() 
       {
           public void run()
           {
        	   
        	   
	   //myAudio
	   if(animal.equals("bear"))
	   {
		   double s = 1.0;//find out what pitch/speed each animal is in iPhone version
		   double p = 1.0;
		   double r = 1.0;
		   
		   float speed = (float) s;
       	   float pitch = (float) p;
       	   float rate = (float) r;
       	
           play(speed, pitch, rate);    	
	   }
	   else if(animal.equals("cat"))
	   {
		   double s = 1.0;//??
		   double p = 1.0;
		   double r = 1.0;
		   
		   float speed = (float) s;
		   float pitch = (float) p;
		   float rate = (float) r;
       	
		   play(speed, pitch, rate); 
	   }
	   else if(animal.equals("hare"))
	   {
		   double s = 1.0;//??
		   double p = 1.0;
		   double r = 1.0;
		   
		   float speed = (float) s;
       	   float pitch = (float) p;
       	   float rate = (float) r;
       	
       	   play(speed, pitch, rate); 
	   }
	   else if(animal.equals("giraffe"))
	   {
		   double s = 1.0;//??
		   double p = 1.0;
		   double r = 1.0;
		   
		   float speed = (float) s;
       	   float pitch = (float) p;
           float rate = (float) r;
       	
       	   play(speed, pitch, rate); 
	   }

           }   
       }).start();
   }
           
           
           
   
   
   
   public void play(float speed, float pitch, float rate)
   {   
	   AndroidAudioDevice device = new AndroidAudioDevice(22050, 1);
       Sonic sonic = new Sonic(22050, 1);
       byte samples[] = new byte[4096];
       byte modifiedSamples[] = new byte[2048]; 
       
       try 
       {
			InputStream soundFile = new FileInputStream(myAudio);
			int bytesRead;
			
			if(soundFile != null) 
			{
			    sonic.setSpeed(speed);
			    sonic.setPitch(pitch);
			    sonic.setRate(rate);
			    do 
			    {
			        try 
			        {
						bytesRead = soundFile.read(samples, 0, samples.length);
					} 
			        catch (IOException e) 
			        {
						e.printStackTrace();
						return;
					}
			        if(bytesRead > 0) 
			        {
			        	sonic.putBytes(samples, bytesRead);
			        } 
			        else 
			        {
					    sonic.flush();
			        }
		        	int available = sonic.availableBytes(); 
		        	if(available > 0) 
		        	{
		        		if(modifiedSamples.length < available) 
		        		{
		        		    modifiedSamples = new byte[available*2];
		        		}
		        		sonic.receiveBytes(modifiedSamples, available);
		        		device.writeSamples(modifiedSamples, available);
		        	}
			    } while(bytesRead > 0);
			    device.flush();
			}	
		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}            
      }
} 