package com.bluemetrix.wheel.widget;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.bluemetrix.R;
import com.bluemetrix.login.SignUpView;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SelectAgeActivity extends SherlockActivity{

	  private boolean scrolling = false;
	  private String bloodString = "";
	  private String nameString = "";
	  private String passString = "";
	  private String confirmString = "";
	  private String emailString = "";
	  private boolean gender;
	  private int settingOption = 0;//from settings page
	  
	  private Button ageRangeSelected;
	  Button btnUp, btnDown;
	  TextView textViewUp, textViewMid, textViewBottom;
	  private ArrayList<String> ageRanges;
	  private String selectedValue;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.select_age_layout);
			setTitle("");

			 String bloodDefault = (""+ R.string.bloodType); 
	         String ageDefault = ("" + R.string.ageRange);
	         
			Intent i = getIntent();
	   		Bundle extras = i.getExtras();
	   		bloodString = extras.getString("bloodType", bloodDefault);
	   		nameString = extras.getString("name");
	   		passString = extras.getString("pass");
	   		confirmString = extras.getString("confirm");
	   		emailString = extras.getString("email");
	  		settingOption = extras.getInt("settingsOption");//settings page
	   		gender = extras.getBoolean("gender");
			
	   		ageRangeSelected = (Button) findViewById(R.id.buttonAgeRangeSelected);
	   		btnUp = (Button) findViewById(R.id.button1);
	   	    btnDown = (Button) findViewById(R.id.button2);
	   	    textViewUp = (TextView) findViewById(R.id.textView1);
	        textViewMid = (TextView) findViewById(R.id.textView2);
	        textViewBottom = (TextView) findViewById(R.id.textView3);
	   		
	        ageRanges = new ArrayList<String>();
	        ageRanges.add("16-20");//0
	        ageRanges.add("21-25");//1
	        ageRanges.add("26-30");//2
	        ageRanges.add("31-40");//3
	        ageRanges.add("41-50");//4
	        
	        textViewUp.setText(ageRanges.get(0));
	        textViewMid.setText(ageRanges.get(1));
	        textViewBottom.setText(ageRanges.get(2));
	        
	        btnDown.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View view) 
			    {  
					String getString = String.valueOf(textViewMid.getText());
					
					if(getString=="41-50"){
						//do nothing as you've reached top of list
					}
					else if(getString == "26-30"){
						textViewUp.setText(ageRanges.get(2));
						textViewMid.setText(ageRanges.get(3));
						textViewBottom.setText(ageRanges.get(4));
					}
					else if(getString == "21-25"){
						textViewUp.setText(ageRanges.get(1));
						textViewMid.setText(ageRanges.get(2));
						textViewBottom.setText(ageRanges.get(3));
					}
					else if(getString == "16-20"){
						textViewUp.setText(ageRanges.get(0));
						textViewMid.setText(ageRanges.get(1));
						textViewBottom.setText(ageRanges.get(2));
					}
					else if(getString == "31-40"){
						textViewUp.setText(ageRanges.get(3));
						textViewMid.setText(ageRanges.get(4));
						textViewBottom.setText("");
					}
			    }
			});
	        
			btnUp.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View view) 
			    {  
					String getString = String.valueOf(textViewMid.getText());
					if(getString=="31-40"){
						textViewUp.setText(ageRanges.get(1));
						textViewMid.setText(ageRanges.get(2));
						textViewBottom.setText(ageRanges.get(3));
					}
					else if(getString == "41-50"){
						textViewUp.setText(ageRanges.get(2));
						textViewMid.setText(ageRanges.get(3));
						textViewBottom.setText(ageRanges.get(4));
					}
					else if(getString == "26-30"){
						textViewUp.setText(ageRanges.get(0));
						textViewMid.setText(ageRanges.get(1));
						textViewBottom.setText(ageRanges.get(2));
					}
					else if(getString == "21-25"){
						textViewUp.setText("");
						textViewMid.setText(ageRanges.get(0));
						textViewBottom.setText(ageRanges.get(1));
					}
					else if(getString == "16-20"){
						//do nothing
					}
			    }
			});
	        
			
			ageRangeSelected.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View view) 
			    {  
					selectedValue=textViewMid.getText().toString();
					if(settingOption==1){
						Intent in = new Intent(SelectAgeActivity.this, FragmentChangeActivity.class);
						in.putExtra("bloodType", bloodString);
						in.putExtra("option", 2);
						in.putExtra("ageRange", selectedValue);
						in.putExtra("name", nameString);
						in.putExtra("settingOption", settingOption);
		     			in.putExtra("email", emailString);
		     			in.putExtra("gender", gender);
			         	startActivity(in); 
					}
					else{
					System.out.println("Blood Type A");
					//go back to sign up page
					Intent in = new Intent(SelectAgeActivity.this, SignUpView.class);
					in.putExtra("bloodType", bloodString);
					in.putExtra("option", 2);
					in.putExtra("ageRange", selectedValue);
					in.putExtra("name", nameString);
	     			in.putExtra("pass", passString);
	     			in.putExtra("confirm", confirmString);
	     			in.putExtra("email", emailString);
	     			in.putExtra("gender", gender);
		         	startActivity(in); 
					}
			    }
			});
	        
	        

			
			
			//Titlebar
			
			
			  //Inflate the custom view
	        View customNav = LayoutInflater.from(this).inflate(R.layout.titlebar_age_range, null);
	        Button cancel = (Button) customNav.findViewById(R.id.buttonCancel);

	        
	     cancel.setOnClickListener(new OnClickListener() {
	 		@Override
	 		public void onClick(View v) {
	 				//back to sign up page
	 			
	 			Intent in = new Intent(SelectAgeActivity.this, SignUpView.class);
	         	startActivity(in);    			
	 		}
	  	});
	     
	   
	        //Attach to the action bar
	        getSupportActionBar().setCustomView(customNav);
	        getSupportActionBar().setDisplayShowCustomEnabled(true);
			
			
			
			
			
			
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		private void updateCities(WheelView city, String cities[][], int index) {
			ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(this,
					cities[index]);
			adapter.setTextSize(18);
			city.setViewAdapter(adapter);
			city.setCurrentItem(cities[index].length / 2);
		}
		
		
		

		private class MyWheelAdapter extends AbstractWheelTextAdapter {
			private String bloodTypes[] = new String[]{"Group A", "Group B","Group AB", "Group O"};
			private String countries[] = new String[] {"USA", "Canada", "Ukraine", "France"}; 
			private int flags[]; 

			protected MyWheelAdapter(Context context) {
				super(context, R.layout.blood_type_layout, NO_RESOURCE);
				setItemTextResource(R.id.wheel_item_name);//set text of wheel
			}

			@Override
			public View getItem(int index, View cachedView, ViewGroup parent) {
				View view = super.getItem(index, cachedView, parent);
				TextView text = (TextView) view.findViewById(R.id.wheel_item_name);
				text.setText(countries[index].toString());
			
				return view;
			}

			@Override
			public int getItemsCount() {
				return bloodTypes.length;
			}
			
			@Override
			protected CharSequence getItemText(int index) {
				return bloodTypes[index];
			}
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		private class CountryAdapter extends AbstractWheelTextAdapter {
			private String countries[] = new String[] {"16-20", "21-25", "26-30", "31-40", "41-50"}; 
			private int flags[]; 
			protected CountryAdapter(Context context) {
				
				super(context, R.layout.wheel_layout, NO_RESOURCE);
				setItemTextResource(R.id.wheel_item_name);
				
			}

			@Override
			public View getItem(int index, View cachedView, ViewGroup parent) {
				View view = super.getItem(index, cachedView, parent);
			
				return view;
			}

			@Override
			public int getItemsCount() {
				return countries.length;
			}
			
			@Override
			protected CharSequence getItemText(int index) {
				return countries[index];
			}
			
		}
			
		
		
	
	
	
	
	
	
	
}
