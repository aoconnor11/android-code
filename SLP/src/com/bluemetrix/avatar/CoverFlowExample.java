package com.bluemetrix.avatar;

import android.app.Activity;
import java.io.FileInputStream;
import java.util.ArrayList;

import com.bluemetrix.R;
import com.bluemetrix.help.LearnMoreHelp;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CoverFlowExample extends Activity
{	
	private int totalPoints = 0;
	private ArrayList<Integer> garmentCart;
	private TextView credits;
	private TextView cartPoints;
	private ImageView cart;
	private int points = 100;//get total points from users account
	int eyesOption;
	int mouthOption;
	int noseOption;
	private ImageView help;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
    	//Use this if you want to use XML layout file
	    setContentView(R.layout.activity_cover_flow);
	   
	    Intent i = getIntent();

		eyesOption = i.getIntExtra("eyesID", -1);
		mouthOption = i.getIntExtra("mouthID", -1);
		noseOption = i.getIntExtra("noseID", -1);
	    
		garmentCart = new ArrayList<Integer>(); 
		credits = (TextView) findViewById(R.id.textViewCredits);
		cartPoints = (TextView) findViewById(R.id.textViewBasket);
		cart = (ImageView) findViewById(R.id.imageViewCart);
		help = (ImageView) findViewById(R.id.imageView1);
	 
		CoverFlow coverFlow;
		coverFlow =  (CoverFlow) findViewById(R.id.coverflow);
		coverFlow.setAdapter(new ImageAdapter(this));
		ImageAdapter coverImageAdapter =  new ImageAdapter(this);
		coverFlow.setSpacing(-300);//set spacing between pictures
		coverFlow.setAdapter(coverImageAdapter);
		coverFlow.setSelection(1, true);
		coverFlow.setAnimationDuration(1000);
     
		coverFlow.setOnItemClickListener(new OnItemClickListener() 
		{
    	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
    	    {   
    	    	int viewPoints = (Integer) view.getTag();
    	    	int garImages = (Integer) view.getTag(R.string.garmentTag);//get images (ids) from views
    	    	totalPoints = (totalPoints + viewPoints);

    	    	garmentCart.add(garImages);//add clothes to cart
    	    	System.out.println("total points: " + totalPoints + "\nImages: " + garImages);
    	    	credits.setText((points-totalPoints)+ " Credits");//no. of credits left
    	    	cartPoints.setText(totalPoints +" in Basket");//points in cart
    	    }
    	});

		cart.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{			
				Intent in = new Intent(CoverFlowExample.this, MainView.class);
				in.putExtra("option", 2);
				in.putExtra("originalPoints", points);
				in.putExtra("totalpoints", totalPoints);
				in.putIntegerArrayListExtra("cart", garmentCart);
				in.putExtra("eyesID", eyesOption);
				in.putExtra("noseID", noseOption);
				in.putExtra("mouthID", mouthOption);
				startActivity(in);		
			}
		}); 
     
		help.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				Intent in = new Intent(CoverFlowExample.this, LearnMoreHelp.class);
				in.putExtra("option", 5);
				in.putExtra("eyesID", eyesOption);
				in.putExtra("noseID", noseOption);
				in.putExtra("mouthID", mouthOption);
				startActivity(in);
			
				overridePendingTransition(R.anim.bottom_slide_in, R.anim.bottom_slide_out);
			}
		});
    }
    
    
    
    
 public class ImageAdapter extends BaseAdapter 
 {
     int mGalleryItemBackground;
     private Context mContext;
     private FileInputStream fis;
     private ImageView[] mImages;
     Bitmap bmp[] = new Bitmap[6];
     Context context;
     ImageView gar;
     TextView points;
     TextView addGarment;
     private ArrayList<Integer> basket = new ArrayList<Integer>();
     private int totalPoints; 
     private Integer[] imagePoints = {10,20,30,10,20,0};
     private Integer[] garmentImages = {R.drawable.storetorso1,
    	R.drawable.storetorso2, R.drawable.storetorso3, R.drawable.storetorso1,
    	R.drawable.storetorso2, null
     };
        
     private Integer[] mImageIds = 
     {
    	
     };
  
     public ImageAdapter(Context c) 
     {
    	 mContext = c;
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
     }
       
     public boolean createReflectedImages() 
     {
          //The gap we want between the reflection and the original image
          final int reflectionGap = 1;         
          //  final int reflectionGap = 4;
          int index = 0;
          for (int imageId : mImageIds)
          {
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
        	  imageView.setImageBitmap(bitmapWithReflection);//150, 150
        	  imageView.setLayoutParams(new CoverFlow.LayoutParams(150, 150));
        	  imageView.setScaleType(ScaleType.MATRIX);
        	  mImages[index++] = imageView;       
          }
          return true;
     }

     public int getCount() 
     {
         return bmp.length;
     }

     public Object getItem(int position) 
     {
         return position;
     }

     public long getItemId(int position) 
     {
         return position;
     }

     public View getView(int position, View convertView, ViewGroup parent) 
     {
    	 //Use this code if you want to load from resources
         ImageView i = new ImageView(mContext);
         i.setImageBitmap(bmp[position]);
         // i.setImageResource(mImageIds[position]);
         i.setTag(imagePoints[position]);//add points to view
         i.setTag(R.string.garmentTag, garmentImages[position]);
         // i.set				//500
         i.setLayoutParams(new CoverFlow.LayoutParams(800, 800));//size of image on screen
         i.setScaleType(ImageView.ScaleType.CENTER_INSIDE); 
         
         //Make sure we set anti-aliasing otherwise we get jaggies
         BitmapDrawable drawable = (BitmapDrawable) i.getDrawable();
         drawable.setAntiAlias(true);
         return i;
      
         //return mImages[position];
     }
     /** Returns the size (0.0f to 1.0f) of the views 
      * depending on the 'offset' to the center. */ 
      public float getScale(boolean focused, int offset) 
      { 
    	  /* Formula: 1 / (2 ^ offset) */ 
          return Math.max(0, 1.0f / (float)Math.pow(2, Math.abs(offset))); 
      } 
 	}	
}
