package com.bluemetrix.messages;

import java.io.File;
import java.io.IOException;

import com.bluemetrix.R;
import com.bluemetrix.audio.SonicTest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AudioRecord extends Activity{

	private TextView exit;
	private ImageView record;
	private ImageView bear;
	private ImageView cat;
	private ImageView hare;
	private ImageView giraffe;
	private TextView add;
	private MessageItem myMessage;
	private MediaRecorder recorder;
	private File audiofile = null;
	private static final String TAG = "SoundRecordingActivity";
	private Uri myFile;
	private File myAudio;
	private boolean bearPressed = false;
	private boolean catPressed = false;
	private boolean harePressed = false;
	private boolean giraffePressed = false;
	private int animalOption = 0;


	
	@Override
    public void onCreate(Bundle savedInstanceState) {
           super.onCreate(savedInstanceState);
           
           setContentView(R.layout.audio_message_layout);
           
           setTitle("Messaging");
           
           Intent intent = getIntent();
           Bundle b = intent.getExtras();
           myMessage = b.getParcelable("message");
           
           exit = (TextView) findViewById(R.id.textViewExit);
           add = (TextView) findViewById(R.id.textViewAddAudio);
           bear = (ImageView) findViewById(R.id.imageViewBear);
           cat = (ImageView) findViewById(R.id.imageViewCat);
           hare = (ImageView) findViewById(R.id.imageViewHare);
           giraffe = (ImageView) findViewById(R.id.imageViewGiraffe);
           record = (ImageView) findViewById(R.id.imageViewMicro);
           
           //pitch high
           //pitch low
           //playback fast
           //playback slow
           
           //can't change pitch without effecting playback using soundpool
           
           
           bear.setOnClickListener(new OnClickListener() {
          		@Override
          		public void onClick(View v) {
          				//back to record audio page
          			bearPressed = true;
          			catPressed = false;//make sure other animals are false to prevent  2 trues
          			harePressed = false;
          			giraffePressed = false;
          			SonicTest change = new SonicTest(myAudio, "bear");
             
          		}    	});
           
           cat.setOnClickListener(new OnClickListener() {
         		@Override
         		public void onClick(View v) {
         				//back to record audio page
         			bearPressed = false;
         			catPressed = true;//make sure other animals are false to prevent  2 trues
         			harePressed = false;
         			giraffePressed = false;
         			SonicTest change = new SonicTest(myAudio, "cat");
            
         		}    	});
              
           hare.setOnClickListener(new OnClickListener() {
         		@Override
         		public void onClick(View v) {
         				//back to record audio page
         			bearPressed = false;
         			catPressed = false;//make sure other animals are false to prevent  2 trues
         			harePressed = true;
         			giraffePressed = false;
         			SonicTest change = new SonicTest(myAudio, "hare");
            
         		}    	});
           
           giraffe.setOnClickListener(new OnClickListener() {
         		@Override
         		public void onClick(View v) {
         				//back to record audio page
         			bearPressed = false;
         			catPressed = false;//make sure other animals are false to prevent  2 trues
         			harePressed = false;
         			giraffePressed = true;
         			SonicTest change = new SonicTest(myAudio, "giraffe");
            
         		}    	});
           
           
           
           add.setOnClickListener(new OnClickListener() {
       		@Override
       		public void onClick(View v) {
       				//back to message with audio
    
       		if(recorder==null){//no audio recorded
       			Toast.makeText(AudioRecord.this, "Please record audio to add to message", Toast.LENGTH_SHORT).show();
       		}
       		else{
       			stopRecording();
       			//check if any of the animals have been pressed - indicates audio modified
       			if(bearPressed==true){
       				animalOption = 1;
       			}
       			else if(catPressed== true){
       				animalOption = 2;
       			}
       			else if(harePressed==true){
       				animalOption = 3;
       			}
       			else if(giraffePressed==true){
       				animalOption = 4;
       			}
       			else{
       				//no animals pressed - no audio alteration
       				animalOption = 0;
       			}
       			
       			//Intent- back to message page - with animaloption and audio file path??
       		
       			Intent in = new Intent(AudioRecord.this, MessageView.class);
       			in.putExtra("message", myMessage);
       			in.putExtra("aOption", animalOption);
       			in.putExtra("audioPath", myAudio.getPath());
               	startActivity(in);  
       		}
           
       		}    	});
           
           
           
           record.setOnClickListener(new OnClickListener() {
          		@Override
          		public void onClick(View v) {
          				//back to record audio page
       
          			try {
						startRecordAudio();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}   			
          		}
           	});
           
           
           
           
           exit.setOnClickListener(new OnClickListener() {
       		@Override
       		public void onClick(View v) {
       				//back to record audio page
    
       			Intent in = new Intent(AudioRecord.this, MessageView.class);
       			//in.putExtra("option", 40);
       			in.putExtra("message", myMessage);
               	startActivity(in);    			
       		}
        	});
           
           
           
           
           
           
	}
	
	
	
	public void stopRecording(){
		
		recorder.stop();
        recorder.release();
        addRecordingToMediaLibrary();
	
	}
	
	
	
    protected void addRecordingToMediaLibrary() {
        ContentValues values = new ContentValues(4);
        long current = System.currentTimeMillis();
        values.put(MediaStore.Audio.Media.TITLE, "audio" + audiofile.getName());
        values.put(MediaStore.Audio.Media.DATE_ADDED, (int) (current / 1000));
        values.put(MediaStore.Audio.Media.MIME_TYPE, "audio/3gpp");
        values.put(MediaStore.Audio.Media.DATA, audiofile.getAbsolutePath());
        ContentResolver contentResolver = getContentResolver();

        Uri base = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Uri newUri = contentResolver.insert(base, values);

        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, newUri));
        Toast.makeText(this, "Added File " + newUri, Toast.LENGTH_LONG).show();
        myFile = newUri;
        myAudio = new File(myFile.getPath());//recorded audio file - not modified yet
    }
	
	
	
	
	public void startRecordAudio() throws IOException{
		
		 File sampleDir = Environment.getExternalStorageDirectory();

		 try {
	            audiofile = File.createTempFile("sound", ".wav", sampleDir);
	        } catch (IOException e) {
	            Log.e(TAG, "sdcard access error");
	            return;
	        }

		 	recorder = new MediaRecorder();
	        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
	        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
	        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
	        recorder.setOutputFile(audiofile.getAbsolutePath());
	        recorder.prepare();
	        recorder.start();
	
	}
	
	
	
	
}
