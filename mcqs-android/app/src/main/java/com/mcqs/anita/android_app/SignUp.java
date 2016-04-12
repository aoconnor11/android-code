package com.mcqs.anita.android_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class SignUp extends AppCompatActivity {
    private TextView actionBarTitle;
    private ImageView downloadIcon;
    private String signUpText = "";
    private Button signUp;
    private String mode = "";
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText firstName;
    private EditText surname;
    private String usernameString;
    private String passwordString;
    private String emailString;
    private String firstNameString;
    private String surnameString;
    private String sendJSON;
    private UserSignUpAPI signUpJSON;
    private UserDetailsAPI userAPI;
    private String cookie = "";
    private String apiRoute = "https://www.faircity.com/papi";
    private UserDetails myUser;
    private String myUserString = "";
    private NetworkChangeReceiver receiver;
    private boolean connection;
    private boolean conn = false;
    private String logInToken;
    private UserDetailsAPI myToken = new UserDetailsAPI();
    private String myTokenString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        actionBarTitle.setText("Sign Up");

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        signUp = (Button) findViewById(R.id.button4SignUp);
        username = (EditText) findViewById(R.id.editText2SignUp);
        password = (EditText) findViewById(R.id.editText3SignUp);
        email = (EditText) findViewById(R.id.editText);
        firstName = (EditText) findViewById(R.id.editText4);
        surname = (EditText) findViewById(R.id.editText5);

        Bundle extras = getIntent().getExtras();
        mode = extras.getString("mode");

        myUserString = readFromFileUser();
        cookie = myUserString.substring(1, myUserString.length() - 1);
        myUser = new UserDetails();

        try {
            myUser = LoganSquare.parse(cookie, UserDetails.class);
        } catch (IOException er) {
            er.printStackTrace();
        }

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver() {
            @Override
            protected void onNetworkChange() {
                connection = isConnected();
                if (connection == false) {
                    conn = true;
                    showSnackBar(false);
                    downloadIcon.setEnabled(false);
                    signUp.setEnabled(false);
                } else if (connection == true && conn == true) {
                    showSnackBar(true);
                    downloadIcon.setEnabled(true);
                    signUp.setEnabled(true);
                }
            }
        };
        registerReceiver(receiver, filter);

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                usernameString = username.getText().toString();
                passwordString = password.getText().toString();
                emailString = email.getText().toString();
                firstNameString = firstName.getText().toString();
                surnameString = surname.getText().toString();
                if (isNetworkConnected() == false) {
                    presentDialog("Internet Connectivity Error", "Please check your internet connection");
                } else if (isNetworkConnected() == true) {
                    if (usernameString.equals("") || usernameString.equals(" ") || passwordString.equals("") || passwordString.equals(" ") ||
                            emailString.equals("") || emailString.equals(" ") || firstNameString.equals("") || firstNameString.equals(" ") || surnameString.equals("") || surnameString.equals(" ")) {
                        if (passwordString.equals("") || passwordString.equals(" ") && usernameString.equals("") || usernameString.equals(" ") && emailString.equals("") || emailString.equals(" ")) {
                            presentDialog("Sign Up Error", "Please enter your details");
                        } else {
                            presentDialog("Sign Up Error", "Please enter your details");
                        }
                    } else if (usernameString.length() < 2 || firstNameString.length() < 2 || surnameString.length() < 2) {
                        presentDialog("Sign Up Error", "User details must be at least 2 characters in length");
                    } else if (passwordString.length() < 5) {
                        presentDialog("Sign Up Error", "Passwords must be at least 5 characters in length");
                    } else {
                        if (isEmailValid(emailString) == true) {
                            sendJSON = "{\"username\" : \"" + usernameString + "\",\"firstName\" : \"" + firstNameString + "\", \"surname\" : \"" + surnameString + "\", \"email\" : \"" + emailString + "\", \"password\" : \"" + passwordString + "\"}";
                            try {
                                String result = new SignUpAPI().execute(apiRoute + "/api/user").get();
                                signUpJSON = LoganSquare.parse(result,
                                        UserSignUpAPI.class);
                                if (signUpJSON.getEmail() != null) {
                                    Toast.makeText(getApplicationContext(),
                                            "E-Mail Already In Use, Please Log In", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SignUp.this, LogIn.class);
                                    intent.putExtra("mode", mode);
                                    startActivity(intent);
                                } else if (signUpJSON.getUsername() != null) {
                                    Toast.makeText(getApplicationContext(),
                                            "Username Already In Use, Please Choose Another", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Sign Up Successful", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(SignUp.this, LogIn.class);
                                    intent.putExtra("mode", mode);
                                    intent.putExtra("status", "signup");
                                    startActivity(intent);
                                }
                            } catch (ExecutionException er) {
                                er.printStackTrace();
                            } catch (InterruptedException er) {
                                er.printStackTrace();
                            } catch (IOException er) {
                                er.printStackTrace();
                            }
                        } else {
                            presentDialog("Sign Up Error", "Please enter a valid e-mail address");
                        }
                    }
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void showSnackBar(boolean internetThere){
        if(internetThere==true){
            Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Internet Connection Restored", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.GREEN);
            snackbar.show();
        }
        else{
            final Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void presentDialog(String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(SignUp.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { }
        });
        alertDialog.show();
    }

    private String readFromFileUser() {
        String ret = "";
        String toPath = "/data/data/" + getPackageName();
        try {
            InputStream inputStream = openFileInput("user.txt");
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return ret;
    }

    public abstract class NetworkChangeReceiver extends BroadcastReceiver {
        boolean connected;

        public NetworkChangeReceiver() {
        }

        public boolean isConnected() {
            return connected;
        }

        public void setConnected(boolean connected) {
            this.connected = connected;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            boolean isConnected = wifi != null && wifi.isConnectedOrConnecting() ||
                    mobile != null && mobile.isConnectedOrConnecting();
            if (isConnected) {
                connected = true;
                onNetworkChange();
            } else {
                connected = false;
                onNetworkChange();
            }
        }
        protected abstract void onNetworkChange();
    }

    private class SignUpAPI extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSON, SignUp.this);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            return myJSON;
        }
        protected void onPostExecute(String result) {
        }
    }
}
