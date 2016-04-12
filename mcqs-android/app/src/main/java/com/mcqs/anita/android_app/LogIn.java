package com.mcqs.anita.android_app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
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
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class LogIn extends AppCompatActivity {
    private TextView actionBarTitle;
    private ImageView downloadIcon;
    private static String questionURL = "";
    private String downloadedJSONTxt = "";
    private EditText usernameEditText;
    private EditText passwordEditText;
    private String username;
    private String password;
    private String sendJSON = "";
    private String questionIDTemp = "";
    private Button logIn;
    private Button signUp;
    private Button quizButton;
    private ProgressBar spinner;
    final Context context = this;
    private String userJSON = "";
    private List<Question> qList;
    private List<Question> finalList;
    private int logInStatus;
    private TextView forgotPassword;
    private String mode;
    private TextView title;
    private String downloadedOneQuestionText = "";
    private int userExam;
    private double userDiff;
    private UserDetails myUser;
    private String cookie = "";
    private int userID;
    private String myUserString = "";
    private int defaultExam;
    private UserDetailsAPI userAPI;
    private String apiRoute = "https://www.faircity.com/papi";
    private NetworkChangeReceiver receiver;
    private boolean connection;
    private boolean conn = false;
    private String myToken;
    private String signUpStatus = "examset";
    private ProgressDialog ringProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);
        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);
        title = (TextView) findViewById(R.id.textView3);
        logIn = (Button) findViewById(R.id.button4);
        signUp = (Button) findViewById(R.id.button5);
        usernameEditText = (EditText) findViewById(R.id.editText2);
        passwordEditText = (EditText) findViewById(R.id.editText3);
        forgotPassword = (TextView) findViewById(R.id.textView6);
        quizButton = (Button) findViewById(R.id.button);
        spinner = (ProgressBar) findViewById(R.id.progressBar3);
        spinner.setVisibility(View.GONE);
        signUpStatus = "examset";
        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("status")) {
            signUpStatus = extras.getString("status");
        }
        mode = extras.getString("mode");
        myUserString = readFromFileUser();
        cookie = myUserString.substring(1, myUserString.length() - 1);
        myUser = new UserDetails();
        try {
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
                    downloadIcon.setEnabled(false);
                    forgotPassword.setEnabled(false);
                    logIn.setEnabled(false);
                    signUp.setEnabled(false);
                    quizButton.setEnabled(false);
                } else if (connection == true && conn == true) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Internet Connection Restored", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    snackbar.show();
                    downloadIcon.setEnabled(true);
                    forgotPassword.setEnabled(true);
                    logIn.setEnabled(true);
                    signUp.setEnabled(true);
                    quizButton.setEnabled(true);
                }
            }
        };
        registerReceiver(receiver, filter);

        if (mode.equals("online") | mode.equals("preferences")) {
            actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
            actionBarTitle.setText(getResources().getString(R.string.title_activity_log_in));
            title.setText(getResources().getString(R.string.title_activity_log_in));
            quizButton.setVisibility(View.INVISIBLE);
        } else {
            actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
            actionBarTitle.setText(getResources().getString(R.string.download));
        }
        finalList = new ArrayList<Question>();
        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LogIn.this, SignUp.class);
                if (mode.equals("online")) {
                    intent.putExtra("mode", "online");
                } else {
                    intent.putExtra("mode", "offline");
                }
                startActivity(intent);
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent resetPassword = new Intent(LogIn.this, ResetPassword.class);
                startActivity(resetPassword);
            }
        });
        quizButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startQuiz = new Intent(LogIn.this, SelectQuiz.class);
                startActivity(startQuiz);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameEditText.getText().toString();
                password = passwordEditText.getText().toString();
                if (isNetworkConnected() == false) {
                    AlertDialog alertDialog = new AlertDialog.Builder(
                            LogIn.this).create();
                    alertDialog.setTitle("Internet Connectivity Error");
                    alertDialog.setMessage("Please check your internet connection");
                    alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    alertDialog.show();
                } else if (isNetworkConnected() == true) {
                    if (username.equals("") | username.equals(" ") | password.equals("") | password.equals(" ")) {
                        if (password.equals("") | password.equals(" ") && username.equals("") | username.equals(" ")) {
                            AlertDialog alertDialog = new AlertDialog.Builder(
                                    LogIn.this).create();
                            alertDialog.setTitle(getResources().getString(R.string.log_in_error));
                            alertDialog.setMessage(getResources().getString(R.string.log_in_error_blank));
                            alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (password.equals("") | password.equals(" ")) {
                            AlertDialog alertDialog = new AlertDialog.Builder(
                                    LogIn.this).create();
                            alertDialog.setTitle(getResources().getString(R.string.log_in_error));
                            alertDialog.setMessage(getResources().getString(R.string.log_in_error_password));
                            alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        } else if (username.equals("") | username.equals(" ")) {
                            AlertDialog alertDialog = new AlertDialog.Builder(
                                    LogIn.this).create();
                            alertDialog.setTitle(getResources().getString(R.string.log_in_error));
                            alertDialog.setMessage(getResources().getString(R.string.log_in_error_name));
                            alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            alertDialog.show();
                        }
                    }
                    if (mode.equals("online") | mode.equals("preferences") | mode.equals("offline")) {
                        sendJSON = "{\"username\" : \"" + username + "\", \"password\" : \"" + password + "\"}";
                        try {
                            String userDetailsTemp = new DownloadUserDetails().execute(apiRoute + "/api/auth").get();
                            if (logInStatus == 401) {
                                quizButton.setEnabled(false);
                                showError();
                                spinner.setVisibility(View.INVISIBLE);
                            } else {
                                try {
                                    userAPI = LoganSquare.parse(userDetailsTemp, UserDetailsAPI.class);
                                    myToken = userAPI.getToken();
                                } catch (IOException er) {
                                    er.printStackTrace();
                                }
                                writeUserJSON("{\"token\" : \"" + "" + myToken + "" + "\"}");
                                if (signUpStatus.equals("signup")) {
                                    Intent chooseExam = new Intent(LogIn.this, ChooseCategory.class);
                                    chooseExam.putExtra("mode", "exam");
                                    startActivity(chooseExam);
                                } else {
                                    if (mode.equals("online")) {
                                        Intent startQuiz = new Intent(LogIn.this, ViewQuestion.class);
                                        startQuiz.putExtra("mode", "online");
                                        startQuiz.putExtra("userExam", userExam);
                                        startQuiz.putExtra("userDiff", userDiff);
                                        startQuiz.putExtra("userid", userID);
                                        startQuiz.putExtra("token", myToken);
                                        startActivity(startQuiz);
                                    } else if (mode.equals("preferences")) {
                                        Intent startQuiz = new Intent(LogIn.this, MainActivity.class);
                                        startActivity(startQuiz);
                                    } else if (mode.equals("offline")) {
                                        ringProgressDialog = ProgressDialog.show(LogIn.this, "Please wait ...", "Downloading Questions ...", true);
                                        ringProgressDialog.setCancelable(true);
                                        spinner.setVisibility(View.VISIBLE);
                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                try {
                                                    String myURL = "https://www.faircity.com/papi/api/user/question/weight?exam=" + userExam + "&level=" + userDiff + "&number=" + 20;
                                                    downloadedJSONTxt = new DownloadQuestion().execute(myURL).get();
                                                } catch (InterruptedException ex) {
                                                    ex.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        ).start();
                                    }
                                }
                            }
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }
    private void writeUserJSON(String userDetails) {
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

    private void showError() {
        usernameEditText.setError(getResources().getString(R.string.log_in_wrong_details));
        usernameEditText.setText("");
        passwordEditText.setText("");
    }
    private String readFromFileID() {
        String ret = "";
        String toPath = "/data/data/" + getPackageName();
        try {
            InputStream inputStream = openFileInput("myQuestionIds.txt");
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
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_log_in, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
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
    private class DownloadUserDetails extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSON, LogIn.this);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            logInStatus = jsonParser.getStatus();
            return myJSON;
        }
        protected void onPostExecute(String result) {
        }
    }
    private class DownloadQuestion extends AsyncTask<String, Integer, String> {
        Map<String, QuestionAPI> qAPI;
        QuestionAPI[] tempArray;
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(LogIn.this);
            jsonParser.setUserToken(myToken);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            logInStatus = jsonParser.getStatus();
            FileOutputStream outputStream;
            String fileName = "myJSON.txt";
            try {
                outputStream = openFileOutput("myJSON.txt", Context.MODE_PRIVATE);
                outputStream.write(myJSON.getBytes());
                outputStream.close();
            } catch (FileNotFoundException er) {
                er.printStackTrace();
            } catch (IOException er) {
                er.printStackTrace();
            }
            String ret = "";
            String toPath = "/data/data/" + getPackageName();
            try {
                InputStream inputStream = openFileInput("myJSON.txt");
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
        protected void onPostExecute(String result) {
            downloadedJSONTxt = result;
            try {
                qAPI = LoganSquare.parseMap(downloadedJSONTxt, QuestionAPI.class);
                Collection<QuestionAPI> values = qAPI.values();
                tempArray = values.toArray(new QuestionAPI[values.size()]);
                ArrayList<String> imagesTemp = new ArrayList<String>();
                for (int j = 0; j < tempArray.length; j++) {
                    for (Map.Entry<String, String> entry : tempArray[j].getImages().entrySet()) {
                        boolean urlCorrect = Patterns.WEB_URL.matcher(entry.getValue()).matches();
                        try{
                            int response = new CheckImageURL().execute(entry.getValue()).get();
                            if(response!=404){
                                imagesTemp.add(entry.getValue());
                            }
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
                String toPathImages = "/data/data/" + getPackageName() + "/files/images";
                File imageFolder = new File(toPathImages);
                File[] imageFiles = imageFolder.listFiles();
                for (int j = 0; j < imageFiles.length; j++) {
                    imageFiles[j].delete();
                }
                new DownloadImages().execute(imagesTemp);
            } catch (IOException er) {
                er.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private class CheckImageURL extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... urls) {
            String URLName = urls[0];
            try {
                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection con =
                        (HttpURLConnection) new URL(URLName).openConnection();
                con.setRequestMethod("HEAD");
                return con.getResponseCode();
            }
            catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        protected void onPostExecute(Integer result) {
        }
    }

    private class DownloadImages extends AsyncTask<ArrayList<String>, Integer, Integer> {
        Question myQ;
        ArrayList<String> myImages;
        protected Integer doInBackground(ArrayList<String>... urls) {
            myImages = urls[0];
            try {
                URL[] imageURLS = new URL[myImages.size()];
                for (int j = 0; j < myImages.size(); j++) {
                    URL imageURL = new URL(myImages.get(j));
                    imageURLS[j] = imageURL;
                }
                int count = imageURLS.length;
                for (int i = 0; i < count; i++) {
                    URL testPath = imageURLS[i];
                    String testPath2 = new File(testPath.getPath()).getName();
                    String toPathImages = "/data/data/" + getPackageName() + "/files/images/";
                    InputStream in = new BufferedInputStream(testPath.openStream());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int n = 0;
                    while (-1 != (n = in.read(buf))) {
                        out.write(buf, 0, n);
                    }
                    out.close();
                    in.close();
                    byte[] response = out.toByteArray();

                    FileOutputStream fos = new FileOutputStream(toPathImages + testPath2);
                    fos.write(response);
                    fos.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
        protected void onPostExecute(Integer result) {
            spinner.setVisibility(View.INVISIBLE);
            quizButton.setEnabled(true);
            AlertDialog alertDialog = new AlertDialog.Builder(
                    LogIn.this).create();
            alertDialog.setTitle("Questions Downloaded");
            alertDialog.setMessage("Please click on Start Quiz to access your updated questions");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
            ringProgressDialog.dismiss();
        }
    }
}
