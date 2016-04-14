package com.bluemetrix.login;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ResetPassView extends Activity
{
	LinearLayout reset;
	String email;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setTitle("Reset Password");

		setContentView(R.layout.reset_password_layout);
		
		Intent i = getIntent();
		Bundle extras = i.getExtras();
		email = extras.getString("email");//default email

		if(email==null){
			
			Toast.makeText(this, "No email entered", Toast.LENGTH_SHORT).show();//what happens when no email entered??
		}

		//Compare email with database and if match is found send password to email address
		//if match is not found send toast to screen - "Email address not found in database"???
		
		reset = (LinearLayout) findViewById(R.id.linearLayoutResetPassword);
		
		
		//back to login page
		reset.setOnClickListener(new View.OnClickListener() 
		{
            public void onClick(View view) 
            {  
            	Intent in = new Intent(ResetPassView.this, LoginView.class);
				startActivity(in);
           	}
        });
		
	
		
		
	}
	
	
	
}
