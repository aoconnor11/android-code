package com.bluemetrix.login;

import com.bluemetrix.R;
import com.bluemetrix.avatar.MainView;
import com.bluemetrix.help.LearnMoreHelp;
import com.slidingmenu.example.fragments.ColorFragment;
import com.slidingmenu.example.fragments.FragmentChangeActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginView extends Activity
{	
	private EditText emailInput;
	private EditText passwordInput;
	private CharSequence email;
	private CharSequence pass;
	private TextView forgotPass;
	private TextView signUp;
	private TextView learnMore;
	private String emailS = "";
	private LinearLayout login;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		
		login = (LinearLayout) findViewById(R.id.linearLayoutLogIn);
		emailInput = (EditText) findViewById(R.id.editTextLogInEmail);
		passwordInput = (EditText) findViewById(R.id.editTextLogInPass);
		forgotPass = (TextView) findViewById(R.id.textViewForgotPass);
		signUp = (TextView) findViewById(R.id.textViewSignUp);
		learnMore = (TextView) findViewById(R.id.textViewLearnMore);	
		
		emailInput.setOnKeyListener(new OnKeyListener() 
		{
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
			
				email = emailInput.getText();
				emailS = email.toString();
			
				return false;
			}
		});
			
		passwordInput.setOnKeyListener(new OnKeyListener() 
		{
			public boolean onKey(View v, int keyCode, KeyEvent event) 
			{
		
				pass = passwordInput.getText();
				
				return false;
			}
		});
				
		//login
		login.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
		    {  
	        	//check name & pass against database		            	
		       	Intent in = new Intent(LoginView.this, FragmentChangeActivity.class);
				startActivity(in);
		   	}
		});
			
		//Compare the email & password to server database
		
		//forgot password
		forgotPass.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {  
            	Intent in = new Intent(LoginView.this, ResetPassView.class);
            	in.putExtra("email", emailS);//send email to forgot password page
				startActivity(in);
           	}
        });
	
		//sign up
		signUp.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
		    {  
				Intent in = new Intent(LoginView.this, SignUpView.class);
		       	in.putExtra("option", 1);
		       	startActivity(in);
		    }
		});
				
		//learn more
		learnMore.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
		    {  
		      	Intent in = new Intent(LoginView.this, LearnMoreHelp.class);
		       
		      	in.putExtra("option", 1);
				startActivity(in);				
				overridePendingTransition(R.anim.bottom_slide_in, R.anim.bottom_slide_out);
		  	}
		});	
	}
}