package com.mcqs.anita.android_app;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
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

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class ResetPassword extends AppCompatActivity {

    private TextView actionBarTitle;
    private ImageView downloadIcon;
    private EditText emailEditText;
    private EditText resetCodeEditText;
    private EditText newPasswordEditText;
    private EditText newPassword2EditText;
    private String emailString;
    private String resetCodeString;
    private String newPasswordString;
    private String newPassword2String;
    private Button submitEmail;
    private Button submitPassword;
    private NetworkChangeReceiver receiver;
    private boolean connection;
    private boolean conn = false;
    private boolean noCode = false;
    private String sendJSONemail = "";
    private int emailStatus;
    private String sendJSONpassword = "";
    private String sendJSONverify = "";
    private int passwordStatus;
    private ResetPasswordJSONAPI resetJSON;
    private String myUserString = "";
    private String cookie = "";
    private UserDetails myUser;
    private int userID;
    private int userExam;
    private double userDiff;
    private String apiRoute = "https://www.faircity.com/papi";
    private String sendJSON = "";
    private int defaultExam;
    private int logInStatus;
    private int verifyStatus;
    private UserDetailsAPI userAPI;
    private String logInToken;
    private UserDetailsAPI myToken = new UserDetailsAPI();
    private String myTokenString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        actionBarTitle.setText("Reset Password");

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);
        emailEditText = (EditText) findViewById(R.id.editText6);
        resetCodeEditText = (EditText) findViewById(R.id.editText7);
        newPasswordEditText = (EditText) findViewById(R.id.editText8);
        newPassword2EditText = (EditText) findViewById(R.id.editText9);
        submitEmail = (Button) findViewById(R.id.button2);
        submitPassword = (Button) findViewById(R.id.button10);
        submitPassword.setEnabled(false);

        myTokenString = readFromFile("token.txt");
        myUserString = readFromFile("user.txt");
        cookie = myUserString.substring(1, myUserString.length() - 1);
        myUser = new UserDetails();
        try {
            myToken = LoganSquare.parse(myTokenString, UserDetailsAPI.class);
            logInToken = myToken.getToken();
            myUser = LoganSquare.parse(cookie, UserDetails.class);
            userID = myUser.getCurrentUserId();
            userExam = myUser.getCurrentExamId();
            userDiff = myUser.getCurrentDifficultyLevel();
            defaultExam = myUser.getDefaultExam();
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
                    final Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();
                    submitEmail.setEnabled(false);
                    submitPassword.setEnabled(false);
                } else if (connection == true && conn == true) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Internet Connection Restored", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    snackbar.show();
                    if (noCode == true) {
                        submitPassword.setEnabled(true);
                    } else {
                        submitPassword.setEnabled(false);
                    }
                    submitEmail.setEnabled(true);
                }
            }
        };
        registerReceiver(receiver, filter);

        submitEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                emailString = emailEditText.getText().toString();
                if (emailString.equals("") || emailString.equals(" ")) {
                    presentDialog("Reset Error", "Please enter your email");
                } else {
                    if (isEmailValid(emailString) == true) {
                        sendJSONemail = "{\"email\":\"" + emailString + "\"}";
                        try {
                            String result1 = new ResetEmail().execute("https://www.faircity.com/napi/password/code").get();
                        } catch (InterruptedException er) {
                            er.printStackTrace();
                        } catch (ExecutionException er) {
                            er.printStackTrace();
                        }
                        if (emailStatus == 500) {
                            presentDialog("Email Error", "User not found with this email address");
                        }
                        if (emailStatus == 200) {
                            Toast.makeText(getApplicationContext(), "Password Reset Code Sent to Email", Toast.LENGTH_SHORT).show();
                            noCode = true;
                            submitPassword.setEnabled(true);
                        }
                    } else {
                        presentDialog("Reset Error", "Please enter a valid e-mail address");
                    }
                }
            }
        });

        submitPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resetCodeString = resetCodeEditText.getText().toString();
                newPasswordString = newPasswordEditText.getText().toString();
                newPassword2String = newPassword2EditText.getText().toString();
                if (resetCodeString.equals("") || resetCodeString.equals(" ")) {
                    presentDialog("Code Error", "Please enter your reset code");
                } else if (newPasswordString.equals("") || newPasswordString.equals(" ") || newPassword2String.equals("") || newPassword2String.equals(" ")) {
                    presentDialog("Password Error", "Please enter your new password");
                } else if (!newPasswordString.equals(newPassword2String)) {
                    presentDialog("New Password Error", "Passwords Not Matching! Please try again.");
                } else {
                    sendJSONverify = "{\"email\":\"" + emailString + "\", \"code\": \"" + resetCodeString + "\"}";
                    try {
                        String result3 = new VerifyEmailConfirmation().execute("https://www.faircity.com/napi/password/email/verify").get();
                        if (verifyStatus == 401) {
                            presentDialog("Email Error", "Please enter the correct email address");
                            passwordStatus = 0;
                        } else if (verifyStatus == 500) {
                            presentDialog("Reset Error", "Please enter the correct Reset Code");
                            passwordStatus = 0;
                        } else if (verifyStatus == 200) {
                            sendJSONpassword = "{\"email\":\"" + emailString + "\", \"code\": \"" + resetCodeString + "\", \"password\" : \"" + newPasswordString + "\"}";
                            String result2 = new ResetPasswordConfirmation().execute("https://www.faircity.com/napi/password/reset").get();
                            if (passwordStatus == 401) {
                                presentDialog("Email Error", "Please enter the correct email");
                                passwordStatus = 0;
                            } else if (passwordStatus == 404) {
                                presentDialog("Reset Error", "Please enter the correct Reset Code");
                                passwordStatus = 0;
                            } else if (passwordStatus == 200) {
                                resetJSON = LoganSquare.parse(result2, ResetPasswordJSONAPI.class);
                                sendJSON = "{\"username\" : \"" + resetJSON.getUsername() + "\", \"password\" : \"" + newPasswordString + "\"}";
                                String userDetailsTemp = new DownloadUserDetails().execute(apiRoute + "/api/auth").get();
                                if (logInStatus != 401) {
                                    try {
                                        userAPI = LoganSquare.parse(userDetailsTemp, UserDetailsAPI.class);
                                        writeTokenJSON("{\"token\" : \"" + "" + userAPI.getToken() + "" + "\"}");
                                        Intent startQuiz = new Intent(ResetPassword.this, MainActivity.class);
                                        startActivity(startQuiz);
                                    } catch (IOException er) {
                                        er.printStackTrace();
                                    }
                                }
                            }
                        }
                    } catch (InterruptedException er) {
                        er.printStackTrace();
                    } catch (ExecutionException er) {
                        er.printStackTrace();
                    } catch (IOException er) {
                        er.printStackTrace();
                    }
                }
            }
        });
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reset_password, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private void presentDialog(String title, String message){
        AlertDialog alertDialog = new AlertDialog.Builder(ResetPassword.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) { }
        });
        alertDialog.show();
    }

    private void writeUserJSON(String userDetails) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("user.txt", Context.MODE_PRIVATE);
            outputStream.write(userDetails.getBytes());
            outputStream.close();
        } catch (FileNotFoundException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        }
    }

    private void writeTokenJSON(String userDetails) {
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("token.txt", Context.MODE_PRIVATE);
            outputStream.write(userDetails.getBytes());
            outputStream.close();
        } catch (FileNotFoundException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        }
    }


    private String readFromFile(String file) {
        String ret = "";
        String toPath = "/data/data/" + getPackageName();
        try {
            InputStream inputStream = openFileInput(file);
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

    private class DownloadUserDetails extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSON, ResetPassword.this);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            logInStatus = jsonParser.getStatus();
            return myJSON;
        }
        protected void onPostExecute(String result) {
        }
    }

    private class ResetEmail extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSONemail, ResetPassword.this);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            emailStatus = jsonParser.getStatus();
            return myJSON;
        }
        protected void onPostExecute(String result) {
        }
    }

    private class VerifyEmailConfirmation extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSONverify, ResetPassword.this);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            verifyStatus = jsonParser.getStatus();
            return myJSON;
        }
        protected void onPostExecute(String result) {
        }
    }

    private class ResetPasswordConfirmation extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSONpassword, ResetPassword.this);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            passwordStatus = jsonParser.getStatus();
            return myJSON;
        }
        protected void onPostExecute(String result) {
        }
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
}
