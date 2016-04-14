package com.slidingmenu.example.fragments;

import java.util.ArrayList;
import java.util.Calendar;

import com.bluemetrix.R;
import com.bluemetrix.avatar.CheckoutItem;
import com.bluemetrix.avatar.LazyAdapter;
import com.bluemetrix.avatar.MainView;
import com.bluemetrix.avatar.MoodView;
import com.bluemetrix.avatar.ShopsView;
import com.bluemetrix.friends.FriendsMenu;
import com.bluemetrix.friends.FriendsRequests;
import com.bluemetrix.friends.SimpleAdapter;
import com.bluemetrix.game.activities.InitialView;
import com.bluemetrix.game.activities.SceneSelectionView;
import com.bluemetrix.game.nodes.SceneSelectionNode;
import com.bluemetrix.help.AppHelpFragment;
import com.bluemetrix.lesson.LessonList;
import com.bluemetrix.lesson.MenuScreen;
import com.bluemetrix.lesson.WelcomeScreen;
import com.bluemetrix.login.SignUpView;
import com.bluemetrix.messages.LazyMessAdapter;
import com.bluemetrix.messages.MessageItem;
import com.bluemetrix.messages.MessageList;
import com.bluemetrix.messages.MessageView;
import com.bluemetrix.wheel.widget.BloodTypeActivity;
import com.bluemetrix.wheel.widget.SelectAgeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("ValidFragment")
public class ColorFragment extends Fragment 
{
	private int mColorRes = -1;

	ImageView messageLink;
	ImageView friendLink;
	ImageView avatarLink;
	ImageView helpHome;
	int optionNew;
	int settingsOption;//for change settings page
	int legsOption;
	int torsoOption;
	int hairOption;
	int noseOption;
	int eyesOption;
	int mouthOption;
	View myFragmentView; 
	SimpleAdapter adapt;
	String[] myList;
	ArrayList<String> myArray;
	Integer[] garmentImages = new Integer[3];//for purchases list
	ArrayList<CheckoutItem> purchases;
	LazyAdapter purLazy;
	
	//settings
	private EditText nameText;
	EditText emailText;
	CharSequence nameChar;
	String name = "";
	CharSequence emailChar;
	String email = "";
	Button male;
	Button female;
	Boolean gender;
	TextView bloodText;
	TextView ageText;
	ImageView bloodImage;
	ImageView ageImage;
	int myOption = 0;
	String bloodString = "";
	String ageString = "";
	String nameString = "";
	String emailString = "";
	LinearLayout resetPass;
	
	private String bloodType;
	private String ageRange;
	private int settingOption;
	private int option;
	
	ListView list;
	LazyMessAdapter lazy;
	ArrayList<MessageItem> messList;
	
	ImageView hanger;
	ImageView hangerPlus;
	ImageView mood;
	ImageView avatar;
	
	public ColorFragment() { 
		this(R.color.white);
	}

	public ColorFragment(int colorRes) {
		mColorRes = colorRes;
		setRetainInstance(true);
	}
	
	public ColorFragment(int colorRes, int option) {
		mColorRes = colorRes;
		optionNew = option;
		setRetainInstance(true);
	}
	
	public ColorFragment(int colorRes, int option, int settings) {
		mColorRes = colorRes;
		optionNew = option;
		settingsOption = settings;
		setRetainInstance(true);
	}

	public ColorFragment(int colorRes, int option, int torso, int legs, int hair) {
		torsoOption = torso;
		legsOption = legs;
		hairOption = hair;
		mColorRes = colorRes;
		optionNew = option;
		setRetainInstance(true);		
	}
	
	public ColorFragment(int colorRes, int option, int torso, int legs, int hair, int mouth, int eyes, int nose) {
		torsoOption = torso;
		noseOption = nose;
		mouthOption = mouth;
		eyesOption = eyes;
		legsOption = legs;
		hairOption = hair;
		mColorRes = colorRes;
		optionNew = option;
		setRetainInstance(true);		
	}

	//settings view
	public ColorFragment(int colorRes, int option, String bloodType, String ageRange, String name, String email,boolean gender, int settingOption) {
		this.bloodType = bloodType;
		this.ageRange = ageRange;
		mColorRes = colorRes;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.settingOption = settingOption;
		this.option = option;
		setRetainInstance(true);		
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
	if (savedInstanceState != null)
	{
		mColorRes = savedInstanceState.getInt("mColorRes");
		int color = getResources().getColor(mColorRes);
	}
			

	//Options			
	if(optionNew==20)
	{//Home View 
		myFragmentView = inflater.inflate(R.layout.home_layout, container, false);   

		messageLink = (ImageView) myFragmentView.findViewById(R.id.imageViewMessagesLink);
		friendLink = (ImageView) myFragmentView.findViewById(R.id.ImageViewFriendsLink);
		avatarLink = (ImageView) myFragmentView.findViewById(R.id.ImageViewAvatarLink);		
		helpHome = (ImageView) myFragmentView.findViewById(R.id.imageViewHelpHome);

		helpHome.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
	        {
				Activity activity = getActivity();
	        	FragmentTransaction ft = getFragmentManager().beginTransaction();	        		
	        	ft.setCustomAnimations(R.anim.bottom_slide_in, R.anim.bottom_slide_out);       		
	            /* Create an intent that will start the main activity. */
	            AppHelpFragment help = new AppHelpFragment(1);
	            ft.replace(R.id.content_frame, help, "helpFragment");
	            // Start the animated transition.
	            ft.commit();
	         }
	    });
	
		messageLink.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
	        {
				//go to message list
	            FragmentTransaction ft = getFragmentManager().beginTransaction();
	            MessageList mess = new MessageList();
	            ft.replace(R.id.content_frame, mess, "messListFragment");
	            // Start the animated transition.
	            ft.commit();
	          
	         }
		});
					
		friendLink.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
	        {
				//go to friend view
	            Activity activity = getActivity();
		    	Intent intent = new Intent(activity, FriendsMenu.class);
		    	startActivity(intent);
	        }
		});			
			
		avatarLink.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
	        {
	            
	      	}
	     });
	}
			
			
	if(option==2)
	{
		myFragmentView = inflater.inflate(R.layout.settings_layout, container, false); 
		Activity activity = getActivity();
		activity.setTitle("Settings");
				
		String bloodDefault = (""+ R.string.bloodType); 
		String ageDefault = ("" + R.string.ageRange);
	         
	    resetPass = (LinearLayout) myFragmentView.findViewById(R.id.linearLayoutResetPass);
	    nameText = (EditText) myFragmentView.findViewById(R.id.editTextNameSettings);
		emailText = (EditText) myFragmentView.findViewById(R.id.editTextEmail);
		male = (Button) myFragmentView.findViewById(R.id.buttonMale);
		female = (Button) myFragmentView.findViewById(R.id.buttonFemale);	
		bloodText = (TextView) myFragmentView.findViewById(R.id.textViewBloodType);
		ageText = (TextView) myFragmentView.findViewById(R.id.textViewAgeRange);
		bloodImage = (ImageView) myFragmentView.findViewById(R.id.imageViewBloodType);
		ageImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAgeRange);
				
		if(option==2 && ageRange.equals(ageDefault))
		{//bloodType, default age
			bloodText.setText(bloodType);
	       	nameText.setText(name);
	       	emailText.setText(emailString);
	       	
	       	if (gender != null)
	       	{
	       		if(gender==true)
	       		{
	       			male.setSelected(false);
	       			female.setSelected(true);
	       		}
	       		else
	       		{
	       			female.setSelected(false);
	       			male.setSelected(true);
	       		}
	       	}      			
		}
		
	    else if(option==2 && !ageRange.equals(ageDefault))
	    {//bloodtype and age already selected
	    	bloodText.setText(bloodString);
	       	ageText.setText(ageString);
	        nameText.setText(nameString);
	        emailText.setText(emailString);

	        if (gender != null)
	        {
	        	if(gender==true)
	        	{
	        		male.setSelected(false);
	           		female.setSelected(true);
	           	}
	           	else
	           	{
	           		female.setSelected(false);
	           		male.setSelected(true);
	           	}
	        }
	    }
				
		nameText.setOnKeyListener(new OnKeyListener() 
		{
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
				nameChar = nameText.getText();
	    		name = nameChar.toString();
	    		nameString = name;
	    		return false;
	    	}
	    });
	       		
	    emailText.setOnKeyListener(new OnKeyListener() 
	    {
	     	public boolean onKey(View v, int keyCode, KeyEvent event)
	     	{
	     		
	     		emailChar = emailText.getText();
	     		email = emailChar.toString();
	     		emailString = email;
	     		
	     		return false;
	     	}
	    });
	           
	    male.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
		    public void onClick(View v) 
	    	{
	    		//back to login page		
		    	v.setSelected(true);
		    	female.setSelected(false);
		    	gender = false;//false = male		
		    }
	    });		   
		        
	    female.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
		    public void onClick(View v) 
	    	{
	    		//back to login page
		    	v.setSelected(true);
		    	male.setSelected(false);
		    	gender = true;//true = female
		    }
	    });
	     		     
	    bloodImage.setOnClickListener(new OnClickListener() 
	    {
	     	@Override
	     	public void onClick(View v) 
	     	{
	     		//go to blood type selection page    			
	     		Intent in = new Intent(getActivity(), BloodTypeActivity.class);
	     		in.putExtra("ageRange", ageString);
	     		in.putExtra("settingsOption", 1);
	     		in.putExtra("name", nameString);
	     		in.putExtra("email", emailString);
	     		in.putExtra("gender", gender);
	            startActivity(in);    			
	     	}
	    });
	    	    	
	    ageImage.setOnClickListener(new OnClickListener() 
	    {
	    	@Override
	     	public void onClick(View v) 
	    	{
	    		//go to age range selection page     			
	     		Intent in = new Intent(getActivity(), SelectAgeActivity.class);
	     		in.putExtra("bloodType", bloodString);
	     		in.putExtra("name", nameString);
	     		in.putExtra("settingsOption", 1);
	     		in.putExtra("email", emailString);
	     		in.putExtra("gender", gender);
	            startActivity(in);    			
	     	}
	    });			
	}
		
	//settings - default option
	if(optionNew==9&&settingsOption==1)
		{
			myFragmentView = inflater.inflate(R.layout.settings_layout, container, false); 

			Activity activity = getActivity();
			activity.setTitle("Settings");				

			String bloodDefault = (""+ R.string.bloodType); 
		    String ageDefault = ("" + R.string.ageRange);				
				
		    Intent i = activity.getIntent();
	        Bundle extras = i.getExtras();
	        myOption = 1;//default
	        bloodString = bloodDefault;
	        ageString = ageDefault;
	        			 	         
	        resetPass = (LinearLayout) myFragmentView.findViewById(R.id.linearLayoutResetPass);
	        nameText = (EditText) myFragmentView.findViewById(R.id.editTextNameSettings);
			emailText = (EditText) myFragmentView.findViewById(R.id.editTextEmail);
			male = (Button) myFragmentView.findViewById(R.id.buttonMale);
			female = (Button) myFragmentView.findViewById(R.id.buttonFemale);	
			bloodText = (TextView) myFragmentView.findViewById(R.id.textViewBloodType);
		    ageText = (TextView) myFragmentView.findViewById(R.id.textViewAgeRange);
		    bloodImage = (ImageView) myFragmentView.findViewById(R.id.imageViewBloodType);
		    ageImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAgeRange);
				
		    nameText.setOnKeyListener(new OnKeyListener() 
		    {
		    	public boolean onKey(View v, int keyCode, KeyEvent event) 
		    	{
		    		nameChar = nameText.getText();
		    		name = nameChar.toString();
		    		nameString = name;
		    		return false;
		    	}
		    });
		       		
		    emailText.setOnKeyListener(new OnKeyListener() 
		    {
		    	public boolean onKey(View v, int keyCode, KeyEvent event) 
		    	{
		    	
		     		emailChar = emailText.getText();
		     		email = emailChar.toString();
		     		emailString = email;
		     	
		     		return false;
		     	}
		    });
		           
		    male.setOnClickListener(new OnClickListener() 
		    {
			    @Override
			    public void onClick(View v) 
			    {
			    	//back to login page			    			
			    	v.setSelected(true);
			    	female.setSelected(false);
			    	gender = false;//false = male		
			    }
		    });
			        
		    female.setOnClickListener(new OnClickListener() 
		    {
		    	@Override
			    public void onClick(View v) 
		    	{
		    		//back to login page
			    	v.setSelected(true);
			    	male.setSelected(false);
			    	gender = true;//true = female
		    	}
		    });
		            
		    bloodImage.setOnClickListener(new OnClickListener() 
		    {
		    	@Override
		     	public void onClick(View v) 
		    	{
		    		//go to blood type selection page    			
		     		Intent in = new Intent(getActivity(), BloodTypeActivity.class);
		     		in.putExtra("ageRange", ageString);
		     		in.putExtra("settingsOption", 1);
		     		in.putExtra("name", nameString);
		     		in.putExtra("email", emailString);
		     		in.putExtra("gender", gender);
		            startActivity(in);    			
		     	}
		    });
   	
		    ageImage.setOnClickListener(new OnClickListener() 
		    {
		    	@Override
		     	public void onClick(View v) 
		    	{
		    		//go to age range selection page     			
		     		Intent in = new Intent(getActivity(), SelectAgeActivity.class);
		     		in.putExtra("bloodType", bloodString);
		     		in.putExtra("name", nameString);
		     		in.putExtra("settingsOption", 1);
		     		in.putExtra("email", emailString);
		     		in.putExtra("gender", gender);
		            startActivity(in);    			
		     	}
		    });		
		}	

		//privacy 
		if(optionNew==8)
		{			
			myFragmentView = inflater.inflate(R.layout.privacy_layout, container, false); 
			Activity activity = getActivity();
			
		}			
			
		//purchases list 
		if(optionNew==7)
		{
			myFragmentView = inflater.inflate(R.layout.message_list, container, false); 
			Activity activity = getActivity();
			activity.setTitle("Purchases");
				
			list = (ListView) myFragmentView.findViewById(R.id.listViewMessages);
			//get purchases from server
				  
			purchases = new ArrayList<CheckoutItem>();
				  
			CheckoutItem item1 = new CheckoutItem(R.drawable.storetorso1, "item 1", "");
		    CheckoutItem item2 = new CheckoutItem(R.drawable.storetorso2, "item 2", "");
		    CheckoutItem item3 = new CheckoutItem(R.drawable.storetorso3, "item 3", "");
				
		    purchases.add(item1);
		    purchases.add(item2);
		    purchases.add(item3);
		           
		    purLazy=new LazyAdapter(purchases, activity); 
		           
		    list.setAdapter(purLazy);
		}			
						
		//latest updates
		if(optionNew==10)
		{
			myFragmentView = inflater.inflate(R.layout.message_list, container, false); 
			Activity activity = getActivity();
			activity.setTitle("Latest Updates");
				
			myArray = new ArrayList<String>();
				
			myArray.add("Update 1");
			myArray.add("Update 1.1");
			myArray.add("Update 2.0");
			myArray.add("Update 2.5");
				
			myList = new String[myArray.size()];
				
			for(int i=0; i<myArray.size(); i++)
			{
				myList[i] = myArray.get(i);
			}
				
			list = (ListView) myFragmentView.findViewById(R.id.listViewMessages);
			adapt = new SimpleAdapter(activity, myList);
			list.setAdapter(adapt);
				
			list.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
				{
					Activity act = getActivity();
				
				}
			});
		}
	
			
		//contact us
		if(optionNew==11)
		{//what layout??? - form or email etc.	
			myFragmentView = inflater.inflate(R.layout.message_list, container, false); 
			Activity activity = getActivity();
			activity.setTitle("Contact Us");
				
			myArray = new ArrayList<String>();	
			myArray.add("Contact Us");
			myArray.add("e-Mail");
			myArray.add("Form");
			myArray.add("Name");
				
			myList = new String[myArray.size()];
				
			for(int i=0; i<myArray.size(); i++)
			{
				myList[i] = myArray.get(i);
			}
				
			list = (ListView) myFragmentView.findViewById(R.id.listViewMessages);
			adapt = new SimpleAdapter(activity, myList);
			list.setAdapter(adapt);
								
			list.setOnItemClickListener(new OnItemClickListener() 
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
				{
					Activity act = getActivity();
					
				}
			});	
		}			
		
		//messages
		if(optionNew==6)
		{				
			myFragmentView = inflater.inflate(R.layout.message_list, container, false); 
			Activity activity = getActivity();
			activity.setTitle("Messages");
			Calendar cal = Calendar.getInstance();
			messList = new ArrayList<MessageItem>();
			list = (ListView) myFragmentView.findViewById(R.id.listViewMessages);
				
			MessageItem mess1 = new MessageItem(R.drawable.storetorso1, "How are you?", cal, "Hi!");
			MessageItem mess2 = new MessageItem(R.drawable.storetorso2, "What's happening?", cal,"How many lessons did you do to get that? ");
			MessageItem mess3 = new MessageItem(R.drawable.storetorso3, "Any news?", cal, "Hey Babes! Love the new hair. How many lessons did you do to get that? Hey Babes! Love the new hair. How many lessons did you do to get that? Hey Babes! Love the new hair. How many lessons did you do to get that? Hey Babes!");
			           
			//set unique id to each message
			mess1.setId(1);
			mess2.setId(2);
			mess3.setId(3);
			           
			messList.add(mess1);
			messList.add(mess2);
			messList.add(mess3);
				    
			lazy=new LazyMessAdapter(messList, activity); 
			           
			list.setAdapter(lazy);
			           
			list.setOnItemClickListener(new OnItemClickListener() 
			{
				@Override
			   	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
				{
			   		MessageItem item = (MessageItem) messList.get(position);
			   		String mytitle = item.getTitle();
			   		System.out.println("my click: " + mytitle);
			   		Activity act = getActivity();
			   				
			   		//go to message page
			   		Intent intent = new Intent(act, MessageView.class);
			   		intent.putExtra("message", item);
			   		startActivity(intent);
			   	}
			});	
		}
				
			
		//Avatar Layout
		if(optionNew==1)
		{
			myFragmentView = inflater.inflate(R.layout.avatar_layout, container, false);   
			
			ImageView torsoImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAvatarMenu1);	
			ImageView legsImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAvatarMenu4);	
			ImageView hairImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAvatarMenu3);	
			ImageView noseImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAvatarMenu6);
			ImageView eyesImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAvatarMenu5);
			ImageView mouthImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAvatarMenu7);
		
			//+id/imageViewAvatarHelp
			ImageView avatarHelp = (ImageView) myFragmentView.findViewById(R.id.imageView1);
				
			System.out.println("R. id " + R.id.imageViewAvatarMenu1);
			System.out.println("get ID " + torsoImage.getId());
						
			if((torsoOption !=-1)&&(legsOption !=-1) && (hairOption != -1) )
			{		
				torsoImage.setImageResource(torsoOption);
				legsImage.setImageResource(legsOption);
				hairImage.setImageResource(hairOption);
			}
				
			if((mouthOption !=-1)||(noseOption !=-1) || (eyesOption != -1) )
			{		
				mouthImage.setImageResource(mouthOption);
				noseImage.setImageResource(noseOption);
				eyesImage.setImageResource(eyesOption);					
			}
				
			if((torsoOption !=-1)&&(legsOption !=-1) && (hairOption != -1) && (noseOption != -1) && (mouthOption != -1) && (eyesOption != -1))
			{		
				torsoImage.setImageResource(torsoOption);
				legsImage.setImageResource(legsOption);
				hairImage.setImageResource(hairOption);
				noseImage.setImageResource(noseOption);
				eyesImage.setImageResource(eyesOption);
				mouthImage.setImageResource(mouthOption);					
			}		
			
		hanger = (ImageView) myFragmentView.findViewById(R.id.hangerImage);	
		hanger.setDrawingCacheEnabled(true);
		hanger.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {
            	System.out.println("Hanger");        	
            	Activity activity = getActivity();
				Intent in = new Intent(activity, MainView.class);
				in.putExtra("eyesID", R.drawable.eyes3);
				in.putExtra("noseID", R.drawable.nose1);
				in.putExtra("mouthID", R.drawable.mouth3);
				in.putExtra("torsoID", R.drawable.torso1);
				in.putExtra("legsID", R.drawable.legs1);
				in.putExtra("hairID", R.drawable.hair1);
				startActivity(in);           	
           	}
        }); 
		
		hangerPlus = (ImageView) myFragmentView.findViewById(R.id.hangerPlusImage);
		hangerPlus.setDrawingCacheEnabled(true);
		hangerPlus.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {
            	System.out.println("Hanger Plus");//to do         	
            	Activity activity = getActivity();
				Intent in = new Intent(activity, ShopsView.class);
				in.putExtra("eyesID", eyesOption);
				in.putExtra("noseID", noseOption);
				in.putExtra("mouthID", mouthOption);
				startActivity(in);          	
           	}
        }); 
		
		mood = (ImageView) myFragmentView.findViewById(R.id.moodImage);
		mood.setDrawingCacheEnabled(true);
		mood.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {
            	System.out.println("Mood");//to do
            	Activity activity = getActivity();
				Intent in = new Intent(activity, MoodView.class);
				in.putExtra("eyesID", eyesOption);
				in.putExtra("noseID", noseOption);
				in.putExtra("mouthID", mouthOption);
				startActivity(in);         	
           	}
        }); 			

		avatarHelp.setDrawingCacheEnabled(true);
		avatarHelp.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {
            	Activity activity = getActivity();
        		FragmentTransaction ft = getFragmentManager().beginTransaction();	        		
        		ft.setCustomAnimations(R.anim.bottom_slide_in, R.anim.bottom_slide_out);       		
            	 /* Create an intent that will start the main activity. */
            	AppHelpFragment help = new AppHelpFragment(2);
            	ft.replace(R.id.content_frame, help, "helpFragment");
            	// Start the animated transition.
            	ft.commit();	
           	}
        }); 
	}
	
			
	//Lesson Layout - Welcome View
	if(optionNew==2)
	{
		myFragmentView = inflater.inflate(R.layout.welcome_view, container, false);   
							
		new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run() 
			{			
				FragmentTransaction ft = getFragmentManager().beginTransaction();
				ft.setCustomAnimations(R.anim.mainfadein, R.anim.splashfadeout);
				
				ColorFragment newFragment = new ColorFragment(R.color.blue, 3);//Menu Screen
				
				ft.replace(R.id.content_frame, newFragment, "detailFragment");
				
				System.out.println("animation");
				ft.commit();
			}
		}, 4000);		
	}

	//Lesson Layout - Menu View
	if(optionNew==3)
	{
		Activity activity = getActivity();
		activity.setTitle("Lesson");
				
		myFragmentView = inflater.inflate(R.layout.menu_view, container, false);   
				
		RelativeLayout beginOption;
		RelativeLayout interOption;
				
		beginOption = (RelativeLayout) myFragmentView.findViewById(R.id.relLayoutBeg);
		interOption = (RelativeLayout) myFragmentView.findViewById(R.id.relLayoutInter);	
				
		beginOption.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				//launchLesson("beginner");
				Activity activity = getActivity();
				Intent in = new Intent(activity, LessonList.class);
				in.putExtra("level", "beginner");
				startActivity(in);
			}
		});

		interOption.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				//launchLesson("intermediate");
				Activity activity = getActivity();
				Intent in = new Intent(activity, LessonList.class);
				in.putExtra("level", "intermediate");
				startActivity(in);
			}
		});				
	}			
	
	//Game View - Start Game
	if(optionNew==4)
	{			
		Activity activity = getActivity();
		activity.setTitle("Game");
				
		myFragmentView = inflater.inflate(R.layout.scene_selection, container, false);   	
				
		Button select;
		RelativeLayout rel;
				
		//Setting Airport Button Text
		select = (Button) myFragmentView.findViewById(R.id.buttonAtTheAirport);

		//link to Initial View
		select.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				Activity activity = getActivity();
				Intent launchGame = new Intent(activity, InitialView.class);
				launchGame.putExtra("option", 1);
				startActivity(launchGame);
			}
		}); 
												
		//Setting Background Picture
		rel = (RelativeLayout) myFragmentView.findViewById(R.id.relLayoutSceneSelection);
						
		//Customise view with text/background picture
		SceneSelectionNode node = new SceneSelectionNode(1);
		node.addNodeDetails(1, select, rel);	
	}

	//Avatar 
	if(optionNew==5)
	{
		Activity activity = getActivity();
		activity.setTitle("Avatar Editor");
				
		myFragmentView = inflater.inflate(R.layout.avatar_main_view, container, false);   	
				
		Button select;
		RelativeLayout rel;
				
		//Setting Airport Button Text
		select = (Button) myFragmentView.findViewById(R.id.buttonAtTheAirport);

		//link to Initial View
		select.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				Activity activity = getActivity();
				Intent launchGame = new Intent(activity, InitialView.class);
				launchGame.putExtra("option", 1);
				startActivity(launchGame);
			}
		}); 				
						
		//Setting Background Picture
		rel = (RelativeLayout) myFragmentView.findViewById(R.id.relLayoutSceneSelection);
						
		//Customise view with text/background picture
		SceneSelectionNode node = new SceneSelectionNode(1);
		node.addNodeDetails(1, select, rel);	
	
	}
	
	return myFragmentView; 
}
	
	
	


	@Override
	public void onSaveInstanceState(Bundle outState) 
	{
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
		setUserVisibleHint(true);
	}	
}
