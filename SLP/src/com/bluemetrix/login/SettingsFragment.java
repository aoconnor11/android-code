package com.bluemetrix.login;

import com.bluemetrix.R;
import com.bluemetrix.wheel.widget.BloodTypeActivity;
import com.bluemetrix.wheel.widget.SelectAgeActivity;
import com.slidingmenu.example.fragments.ColorFragment;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("ValidFragment")
public class SettingsFragment extends Fragment{

	EditText nameText;
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
	int option = 0;
	int settingsOption = 0;
	String bloodString = "";
	String ageString = "";
	String nameString = "";
	String emailString = "";
	View myFragmentView; 
	LinearLayout reset;

	//"@+id/linearLayoutResetPass"
	public SettingsFragment(){

	}

	public SettingsFragment(int option){
		this.option = option;
		setRetainInstance(true);
	}

	public SettingsFragment(int option, String bloodType, String ageRange, String name, String email, boolean gender, int settingOption){
		this.option = option;
		this.bloodString = bloodType;
		this.ageString = ageRange;
		this.nameString = name;
		this.emailString = email;
		this.gender = gender;
		this.settingsOption = settingOption;		
		setRetainInstance(true);
	}





	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		myFragmentView = inflater.inflate(R.layout.settings_layout, container, false); 
		Activity activity = getActivity();
		activity.setTitle("Settings");


		String bloodDefault = (""+ R.string.bloodType); //default text
		String ageDefault = ("" + R.string.ageRange);

		if(option==1){
			bloodString = bloodDefault;
			ageString = ageDefault;
		}

		nameText = (EditText) myFragmentView.findViewById(R.id.editTextNameSettings);
		emailText = (EditText) myFragmentView.findViewById(R.id.editTextEmail);
		male = (Button) myFragmentView.findViewById(R.id.buttonMale);
		female = (Button) myFragmentView.findViewById(R.id.buttonFemale);
		bloodText = (TextView) myFragmentView.findViewById(R.id.textViewBloodType);
		ageText = (TextView) myFragmentView.findViewById(R.id.textViewAgeRange);
		bloodImage = (ImageView) myFragmentView.findViewById(R.id.imageViewBloodType);
		ageImage = (ImageView) myFragmentView.findViewById(R.id.imageViewAgeRange);
		reset = (LinearLayout) myFragmentView.findViewById(R.id.linearLayoutResetPass);



		//different options
		if(option==2 && ageString.equals(ageDefault)){//bloodType, default age
			bloodText.setText(bloodString);
			nameText.setText(nameString);
			emailText.setText(emailString);
			if (gender != null)
			{
				if(gender==true){
					male.setSelected(false);
					female.setSelected(true);
				}
				else{
					female.setSelected(false);
					male.setSelected(true);
				}
			}      			
		}
		else if(option==2 && !ageString.equals(ageDefault)){//bloodtype and age already selected
			bloodText.setText(bloodString);
			ageText.setText(ageString);
			nameText.setText(nameString);
			emailText.setText(emailString);

			if (gender != null)
			{
				if(gender==true){
					male.setSelected(false);
					female.setSelected(true);
				}
				else{
					female.setSelected(false);
					male.setSelected(true);
				}
			}
		}







		else if(option==3 && bloodString.equals(bloodDefault)){//age range, default blood type      			
			ageText.setText(ageString);
			nameText.setText(nameString);
			emailText.setText(emailString);
			if (gender != null)
			{
				if(gender==true){
					male.setSelected(false);
					female.setSelected(true);
				}
				else{
					female.setSelected(false);
					male.setSelected(true);
				}
			}	
		}

		else if(option==3 && !bloodString.equals(bloodDefault)){//age range and blood type already selected
			bloodText.setText(bloodString);
			ageText.setText(ageString);
			nameText.setText(nameString);
			emailText.setText(emailString);
			if (gender != null)
			{
				if(gender==true){
					male.setSelected(false);
					female.setSelected(true);
				}
				else{
					female.setSelected(false);
					male.setSelected(true);
				}
			}
		}







		//click listeners
		nameText.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				nameChar = nameText.getText();
				name = nameChar.toString();

				nameString = name;

				return false;
			}
		});

		emailText.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {

				emailChar = emailText.getText();
				email = emailChar.toString();
				emailString = email;

				return false;
			}
		});


		male.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//back to login page

				v.setSelected(true);
				female.setSelected(false);
				gender = false;//false = male		
			}
		});


		female.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//back to login page
				v.setSelected(true);
				male.setSelected(false);
				gender = true;//true = female
			}
		});



		reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//send reset password email to person
				Activity myAct = getActivity();

				//get email & name? or just email??
				Toast.makeText(myAct, "Reset email has been sent", Toast.LENGTH_SHORT).show();
			}
		});






		bloodImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//go to blood type selection page    			
				Intent in = new Intent(getActivity(), BloodTypeActivity.class);

				in.putExtra("ageRange", ageString);
				in.putExtra("settingsOption", 1);
				in.putExtra("name", nameString);
				System.out.println("name " + nameString);
				in.putExtra("email", emailString);
				in.putExtra("gender", gender);
				startActivity(in);    			
			}
		});


		ageImage.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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








		return myFragmentView; 
	}
}
