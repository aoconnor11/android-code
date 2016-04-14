package com.bluemetrix.storeapp.avatar;

import com.bluemetrix.storeapp.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ShopsView extends Activity
{
	ImageView hairSalon;
	ImageView londonCollection;
	ImageView parisCollection;
	ImageView help;
	int eyesOption;
	int mouthOption;
	int noseOption;
	int points;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shop_main_view);
		
		Intent i = getIntent();
		eyesOption = i.getIntExtra("eyesID", -1);
		mouthOption = i.getIntExtra("mouthID", -1);
		noseOption = i.getIntExtra("noseID", -1);
		points = i.getIntExtra("points", 0);
		
		hairSalon = (ImageView) findViewById(R.id.imageViewHair1);
		londonCollection = (ImageView) findViewById(R.id.imageViewLondon);
		parisCollection = (ImageView) findViewById(R.id.imageViewParis);
		help = (ImageView) findViewById(R.id.imageView1);
		
		hairSalon.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View arg0) 
	        {
	        	//go to Hair Salon
	        }
	    });
		
		londonCollection.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View arg0) 
	        {
	        	//go to London Collection
	        }
	    });
		
		parisCollection.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View arg0) 
	        {
	        	//go to Paris Collection
	        	Intent in = new Intent(ShopsView.this, CoverFlowExample.class);
	        	in.putExtra("points", points);
	        	in.putExtra("eyesID", eyesOption);
				in.putExtra("noseID", noseOption);
				in.putExtra("mouthID", mouthOption);
	        	startActivity(in);
	        }
	    });

		help.setOnClickListener(new View.OnClickListener() 
		{
	        @Override
	        public void onClick(View arg0) 
	        {	
	        	Intent in = new Intent(ShopsView.this, AvatarHelp.class);
				in.putExtra("option", 4);	
				in.putExtra("points", points);
				in.putExtra("eyesID", eyesOption);
				in.putExtra("noseID", noseOption);
				in.putExtra("mouthID", mouthOption);
				startActivity(in);
				overridePendingTransition(R.anim.bottom_slide_in, R.anim.bottom_slide_out);
	        }
	    });
	}	
}
