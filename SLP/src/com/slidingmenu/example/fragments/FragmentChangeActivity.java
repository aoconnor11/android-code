package com.slidingmenu.example.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluemetrix.R;
import com.bluemetrix.login.SettingsFragment;
import com.bluemetrix.messages.MessageList;
import com.bluemetrix.messages.MessageView;

import com.slidingmenu.example.BaseActivity;
import com.slidingmenu.lib.SlidingMenu;

public class FragmentChangeActivity extends BaseActivity {
	
	private Fragment mContent;
	private int option;
	private int torsoOption;
	private int hairOption;
	private int legsOption;
	private int eyesOption;
	private int mouthOption;
	private int noseOption;
	private String bloodType;
	private String ageRange;
	private String name;
	private int settingOption;
	private String email;
	private boolean gender;

	
	public FragmentChangeActivity() {
		super(R.string.home);
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent i = getIntent();
		option = i.getIntExtra("option", 20);//default - zero
		torsoOption = i.getIntExtra("torsoID", -1);
		hairOption = i.getIntExtra("hairID", -1);
		legsOption = i.getIntExtra("legsID", -1);
		eyesOption = i.getIntExtra("eyesID", -1);
		mouthOption = i.getIntExtra("mouthID", -1);
		noseOption = i.getIntExtra("noseID", -1);
		
		bloodType = i.getStringExtra("bloodType");
		ageRange = i.getStringExtra("ageRange");
		name = i.getStringExtra("name");
		settingOption = i.getIntExtra("settingOption", -1);
		email = i.getStringExtra("email");
		gender = i.getBooleanExtra("gender", true);


		
		
		// set the Above View
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)//default view
		
		if(option==20)
		{
				setTitle("Home");
				mContent = new ColorFragment(R.color.red, option, torsoOption, legsOption, hairOption);	
		}
		
		if(option==40)
		{
				setTitle("Messages");			
				mContent = new MessageList();
		}
		
		if(option==1)
		{	
				mContent = new ColorFragment(R.color.red, option, torsoOption, legsOption, hairOption, mouthOption, eyesOption, noseOption);
		}
	
		if(option==2||option==3)
		{//settings
				mContent = new	SettingsFragment(option, bloodType, ageRange, name, email, gender, settingOption);
		}
		

		
		// set the Above View
		setContentView(R.layout.content_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, mContent)
		.commit();
		
		// set the Behind View
		setBehindContentView(R.layout.menu_frame);
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.menu_frame, new ColorMenuFragment())//sliding menu
		.commit();
		
		// customize the SlidingMenu
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
	
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
	//	FragmentManager frag = new FragmentManager();
		   FragmentManager manager = getSupportFragmentManager(); 
		   manager.openTransaction();
		   
		   try { 
			   manager.putFragment(outState, "mContent", mContent);
	
       } catch (Exception e) 
       { 
    	     } 
	
	}
	
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager()
		.beginTransaction()
		.replace(R.id.content_frame, fragment)
		.commit();
		getSlidingMenu().showContent();
	}
}