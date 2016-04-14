package com.bluemetrix.messages;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;
import com.bluemetrix.avatar.CoverFlow;
import com.bluemetrix.avatar.CoverFlowExample.ImageAdapter;
import com.bluemetrix.help.AppHelpFragment;
import com.bluemetrix.login.SignUpView;
import com.bluemetrix.wheel.widget.BloodTypeActivity;
import com.slidingmenu.example.fragments.ColorFragment;
import com.slidingmenu.example.fragments.FragmentChangeActivity;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView.ScaleType;

public class MessageView extends SherlockActivity{

	private MessageItem myMessage;
	private ArrayList<MessageItem> messages;
	private ImageView audioLink;
	private LinearLayout send;
	private EditText mess;
	private CharSequence messageChar;
	private String messageString = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



		setContentView(R.layout.conversation_layout);
		Calendar cal = Calendar.getInstance();
		MessageItem mess2 = new MessageItem(R.drawable.storetorso2, "What's happening?", cal,"Hi!");
		MessageItem mess3 = new MessageItem(R.drawable.storetorso3, "What's happening?", cal,"Hi!");
		MessageItem mess4 = new MessageItem(R.drawable.storetorso1, "What's happening?", cal,"Hi!");
		messages = new ArrayList<MessageItem>();
		//using message id, get all messages in that conversation and add to arraylist

		audioLink = (ImageView) findViewById(R.id.imageViewMicrophone);

		send = (LinearLayout) findViewById(R.id.linearLayoutSendMessage);
		mess = (EditText) findViewById(R.id.editTextMessage);

		Intent intent = getIntent();
		Bundle b = intent.getExtras();
		myMessage = b.getParcelable("message");
		System.out.println("parcel: " + myMessage.getTitle());
		messages.add(myMessage);
		messages.add(mess2);
		messages.add(mess3);
		messages.add(mess4);



		MessageCoverFlow coverFlow;

		coverFlow =  (MessageCoverFlow) findViewById(R.id.coverflowMessages);

		coverFlow.setAdapter(new ImageAdapter(this, messages));

		ImageAdapter coverImageAdapter =  new ImageAdapter(this, messages);
		coverFlow.setSpacing(-250);//set spacing between pictures -100
		coverFlow.setAdapter(coverImageAdapter);


		coverFlow.setSelection(0, true);
		coverFlow.setAnimationDuration(1000);

		coverFlow.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {   

				//play message - need audio file and option to know what pitch/rate it was recorded at

			}
		});



		mess.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				messageChar = mess.getText();
				messageString = messageChar.toString();

				return false;
			}
		});







		audioLink.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//back to record audio page

				Intent in = new Intent(MessageView.this, AudioRecord.class);
				//in.putExtra("option", 40);
				in.putExtra("message", myMessage);
				startActivity(in);    			
			}
		});



		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});









		//Inflate the custom view
		View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_conversation, null);
		Button done = (Button) customNav.findViewById(R.id.buttonDone);


		done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//back to message List page

				Intent in = new Intent(MessageView.this, FragmentChangeActivity.class);
				in.putExtra("option", 40);
				startActivity(in);    			
			}
		});


		//Attach to the action bar
		getSupportActionBar().setCustomView(customNav);
		getSupportActionBar().setDisplayShowCustomEnabled(true); 


	}













	public class ImageAdapter extends BaseAdapter {
		int mGalleryItemBackground;
		private Context mContext;
		private FileInputStream fis;
		private ImageView[] mImages;
		private int bmpCount;
		Bitmap bmp[]= new Bitmap[30];
		Context context;
		ImageView gar;
		TextView points;
		TextView addGarment;
		private View v;
		TextView messText;//message
		private ArrayList<Integer> basket = new ArrayList<Integer>();
		private ArrayList<View> myViews = new ArrayList<View>();
		private ArrayList<Bitmap> myBits = new ArrayList<Bitmap>();
		private int totalPoints; 
		private Integer[] imagePoints = {10,20,30,10,20,0,0,0,0,0};
		private Integer[] garmentImages = {R.drawable.storetorso1,
				R.drawable.storetorso2, R.drawable.storetorso3, R.drawable.storetorso1,
				R.drawable.storetorso2, null, null, null, null, null
		};


		private Integer[] mImageIds = {


		};

		public ImageAdapter(Context c) {

			// 	 gar = mContext.
					Bitmap bmp[] = new Bitmap[50];
					mContext = c;

					//textViewPlus"
					LayoutInflater v1 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					View p1 = v1.inflate(R.layout.garment_layout, null);

					addGarment = (TextView) p1.findViewById(R.id.textViewPlus);
					gar = (ImageView) p1.findViewById(R.id.imageViewGarment);
					points = (TextView) p1.findViewById(R.id.textViewGarmentpoints);
					points.setText("10 pts");
					gar.setImageResource(R.drawable.storetorso1);





					p1.setDrawingCacheEnabled(true);
					// this is the important code :)  
					// Without it the view will have a dimension of 0,0 and the bitmap will be null          
					p1.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
					p1.layout(0, 0, p1.getMeasuredWidth(), p1.getMeasuredHeight()); 

					p1.buildDrawingCache(true);
					bmp[0] = Bitmap.createBitmap(p1.getDrawingCache());
					p1.setDrawingCacheEnabled(false); // clear drawing cache



					View p2 = v1.inflate(R.layout.garment_layout, null);
					gar = (ImageView) p2.findViewById(R.id.imageViewGarment);
					addGarment = (TextView) p2.findViewById(R.id.textViewPlus);
					points = (TextView) p2.findViewById(R.id.textViewGarmentpoints);
					points.setText("20 pts");
					gar.setImageResource(R.drawable.storetorso2);


					p2.setDrawingCacheEnabled(true);
					// this is the important code :)  
					// Without it the view will have a dimension of 0,0 and the bitmap will be null          
					p2.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
					p2.layout(0, 0, p2.getMeasuredWidth(), p2.getMeasuredHeight()); 

					p2.buildDrawingCache(true);
					bmp[1] = Bitmap.createBitmap(p2.getDrawingCache());
					p2.setDrawingCacheEnabled(false); // clear drawing cache



					View p3 = v1.inflate(R.layout.garment_layout, null);
					gar = (ImageView) p3.findViewById(R.id.imageViewGarment);
					addGarment = (TextView) p3.findViewById(R.id.textViewPlus);
					points = (TextView) p3.findViewById(R.id.textViewGarmentpoints);
					points.setText("30 pts");
					gar.setImageResource(R.drawable.storetorso3);

					p3.setDrawingCacheEnabled(true);
					// this is the important code :)  
					// Without it the view will have a dimension of 0,0 and the bitmap will be null          
					p3.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
					p3.layout(0, 0, p3.getMeasuredWidth(), p3.getMeasuredHeight()); 

					p3.buildDrawingCache(true);
					bmp[2] = Bitmap.createBitmap(p3.getDrawingCache());
					p3.setDrawingCacheEnabled(false); // clear drawing cache



					View p4 = v1.inflate(R.layout.garment_layout, null);
					gar = (ImageView) p4.findViewById(R.id.imageViewGarment);
					addGarment = (TextView) p4.findViewById(R.id.textViewPlus);
					points = (TextView) p4.findViewById(R.id.textViewGarmentpoints);
					points.setText("10 pts");
					gar.setImageResource(R.drawable.storetorso1);	


					p4.setDrawingCacheEnabled(true);
					// this is the important code :)  
					// Without it the view will have a dimension of 0,0 and the bitmap will be null          
					p4.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
					p4.layout(0, 0, p4.getMeasuredWidth(), p4.getMeasuredHeight()); 

					p4.buildDrawingCache(true);
					bmp[3] = Bitmap.createBitmap(p4.getDrawingCache());
					p4.setDrawingCacheEnabled(false); // clear drawing cache



					View p5 = v1.inflate(R.layout.garment_layout, null);
					gar = (ImageView) p5.findViewById(R.id.imageViewGarment);
					addGarment = (TextView) p5.findViewById(R.id.textViewPlus);
					points = (TextView) p5.findViewById(R.id.textViewGarmentpoints);
					points.setText("20 pts");
					gar.setImageResource(R.drawable.storetorso2);


					p5.setDrawingCacheEnabled(true);
					// this is the important code :)  
					// Without it the view will have a dimension of 0,0 and the bitmap will be null          
					p5.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
							MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
					p5.layout(0, 0, p5.getMeasuredWidth(), p5.getMeasuredHeight()); 

					p5.buildDrawingCache(true);
					bmp[4] = Bitmap.createBitmap(p5.getDrawingCache());
					p5.setDrawingCacheEnabled(false); // clear drawing cache


					//   mImages = new ImageView[mImageIds.length];
		}





		public ImageAdapter(Context c, ArrayList<MessageItem> mess) {


			mContext = c;

			LayoutInflater v1 = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			v = v1.inflate(R.layout.message_layout, null);


			bmpCount = mess.size();
			System.out.print("bmp count: " + bmpCount);


			for(int i = 0; i<mess.size(); i++){

				messText = (TextView) v.findViewById(R.id.textViewMessageContent);
				String mess1 = mess.get(i).getMessContent();
				messText.setText(mess1);
				System.out.println("messText: " + i + " " + mess1);
				System.out.println("View " + v.toString());
				v.setDrawingCacheEnabled(true);
				v.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
						MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
				v.layout(0, 0, v.getMeasuredWidth(), v.getMeasuredHeight()); 

				v.buildDrawingCache(true);
				if(v.getDrawingCache()==null){
					System.out.print("drawing cache null");
				}
				bmp[i] = Bitmap.createBitmap(v.getDrawingCache());
				v.setDrawingCacheEnabled(false);
				myViews.add(v);
				myBits.add(bmp[i]);

				// clear drawing cache

			}


		}


		public boolean createReflectedImages() {
			//The gap we want between the reflection and the original image
			final int reflectionGap = 1;

			//  final int reflectionGap = 4;
			int index = 0;
			for (int imageId : mImageIds) {
				Bitmap originalImage = BitmapFactory.decodeResource(getResources(), 
						imageId);
				int width = originalImage.getWidth();
				int height = originalImage.getHeight();


				//This will not scale but will flip on the Y axis
				Matrix matrix = new Matrix();
				matrix.preScale(1, -1);

				//Create a Bitmap with the flip matrix applied to it.
				//We only want the bottom half of the image
				Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0, height/2, width, height/2, matrix, false);


				//Create a new bitmap with same width but taller to fit reflection
				Bitmap bitmapWithReflection = Bitmap.createBitmap(width 
						, (height + height/2), Config.ARGB_8888);

				//Create a new Canvas with the bitmap that's big enough for
				//the image plus gap plus reflection
				Canvas canvas = new Canvas(bitmapWithReflection);
				//Draw in the original image
				canvas.drawBitmap(originalImage, 0, 0, null);
				//Draw in the gap
				Paint deafaultPaint = new Paint();
				canvas.drawRect(0, height, width, height + reflectionGap, deafaultPaint);
				//Draw in the reflection
				canvas.drawBitmap(reflectionImage,0, height + reflectionGap, null);

				//Create a shader that is a linear gradient that covers the reflection
				Paint paint = new Paint(); 
				LinearGradient shader = new LinearGradient(0, originalImage.getHeight(), 0, 
						bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, 
						TileMode.CLAMP); 
				//Set the paint to use this shader (linear gradient)
				paint.setShader(shader); 
				//Set the Transfer mode to be porter duff and destination in
				paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN)); 
				//Draw a rectangle using the paint with our linear gradient
				canvas.drawRect(0, height, width, 
						bitmapWithReflection.getHeight() + reflectionGap, paint); 

				ImageView imageView = new ImageView(mContext);
				imageView.setImageBitmap(bitmapWithReflection);
				imageView.setLayoutParams(new CoverFlow.LayoutParams(150, 150));
				imageView.setScaleType(ScaleType.MATRIX);
				mImages[index++] = imageView;

			}
			return true;
		}

		public int getCount() {
			return bmpCount;
			//  return bmp.length;
			//mImageIds.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			//Use this code if you want to load from resources
			ImageView i = new ImageView(mContext);
			i.setImageBitmap(myBits.get(position));

			i.setLayoutParams(new CoverFlow.LayoutParams(1000, 1000));


			//Make sure we set anti-aliasing otherwise we get jaggies
			BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
			drawable.setAntiAlias(true);
			return i;

			//return mImages[position];
		}
		/** Returns the size (0.0f to 1.0f) of the views 
		 * depending on the 'offset' to the center. */ 
		public float getScale(boolean focused, int offset) { 
			/* Formula: 1 / (2 ^ offset) */ 
			return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
		} 

	}






}
