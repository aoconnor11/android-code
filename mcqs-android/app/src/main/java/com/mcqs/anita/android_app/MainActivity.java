package com.mcqs.anita.android_app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private Button startQuiz;
    private Button startExam;
    private Button downloadExam;
    private TextView actionBarTitle;
    private ImageView downloadIcon;
    private String myJSONString = "";
    private String questionIDTemp = "";
    private TextView websiteLink;
    private String myUserString = "";
    private String myTokenString = "";
    private boolean loggedInUserId = false;
    private String sendJSON = "";
    private String cookie = "";
    private UserDetails myUser = new UserDetails();
    private UserDetailsAPI myToken = new UserDetailsAPI();
    private String logInToken;
    private int userID;
    private int userExam;
    private double userDiff;
    private String userToken;
    private int defaultExam;
    private int logInStatus;
    private String apiRoute = "https://www.faircity.com/papi";
    private String downloadedJSONTxt = "";
    private NetworkChangeReceiver receiver;
    private boolean connection;
    private boolean conn = false;
    private boolean menuStatus = true;
    private String mode = "";
    Menu menu;
    private ProgressBar spinner;
    private ProgressDialog ringProgressDialog;
    private int logStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_b);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        actionBarTitle.setText(R.string.title_activity_main);

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        websiteLink = (TextView) findViewById(R.id.textView2);
        startQuiz = (Button) findViewById(R.id.buttonStartQuiz);
        startExam = (Button) findViewById(R.id.button6);
        myUser = new UserDetails();
        spinner = (ProgressBar) findViewById(R.id.progressBar4);
        spinner.setVisibility(View.GONE);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkChangeReceiver() {
            @Override
            protected void onNetworkChange() {
                connection = isConnected();
                if (connection == false) {
                    conn = true;
                    menuStatus = false;
                    final Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "No Internet Connection", Snackbar.LENGTH_INDEFINITE);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.RED);
                    snackbar.show();
                    websiteLink.setEnabled(false);
                    startQuiz.setEnabled(false);
                    downloadIcon.setEnabled(false);
                } else if (connection == true && conn == true) {
                    menuStatus = true;
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Internet Connection Restored", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    snackbar.show();
                    websiteLink.setEnabled(true);
                    startQuiz.setEnabled(true);
                    downloadIcon.setEnabled(true);
                }
            }
        };
        registerReceiver(receiver, filter);

        websiteLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://mcqs.com/"));
                startActivity(intent);
            }
        });

        checkFiles();
        questionIDTemp = readFromFileID();

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTokenString = readFromFileToken();
                try {
                    myToken = LoganSquare.parse(myTokenString, UserDetailsAPI.class);
                    logInToken = myToken.getToken();
                } catch (IOException er) {
                    er.printStackTrace();
                }
                if (logInToken==null) {
                    Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                    chooseExam.putExtra("mode", "online");
                    startActivity(chooseExam);
                } else {
                    if (isNetworkConnected() == false) {
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                MainActivity.this).create();
                        alertDialog.setTitle("Internet Connectivity Error");
                        alertDialog.setMessage("Please check your internet connection");
                        alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    } else if (isNetworkConnected() == true) {
                        Intent startQuiz = new Intent(MainActivity.this, ViewQuestion.class);
                        startQuiz.putExtra("mode", "learn");
                        startQuiz.putExtra("token", logInToken);
                        startActivity(startQuiz);
                    }
                }
            }
        });

        startExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTokenString = readFromFileToken();
                myUserString = readFromFileUser();
                cookie = myUserString.substring(1, myUserString.length() - 1);
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
                String myJSONFile = readFromFile();
                if (myJSONFile.equals("{}")) {
                    if (logInToken==null) {
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                MainActivity.this).create();
                        alertDialog.setTitle("No Questions Downloaded");
                        alertDialog.setMessage("Please Log In To Download Questions");
                        alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                                chooseExam.putExtra("mode", "preferences");
                                startActivity(chooseExam);
                            }
                        });
                        alertDialog.show();
                    } else {
                        Intent intent = new Intent(MainActivity.this, ChooseCategory.class);
                        intent.putExtra("mode", "offline");
                        startActivity(intent);
                    }
                }
                else {
                    Intent startQuiz = new Intent(MainActivity.this, SelectQuiz.class);
                    startActivity(startQuiz);
                }
            }
        });

        downloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myTokenString = readFromFileToken();
                myUserString = readFromFileUser();
                cookie = myUserString.substring(1, myUserString.length() - 1);
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
                if (logInToken==null) {
                    Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                    chooseExam.putExtra("mode", "offline");
                    startActivity(chooseExam);
                } else {
                    if (isNetworkConnected() == false) {
                        AlertDialog alertDialog = new AlertDialog.Builder(
                                MainActivity.this).create();
                        alertDialog.setTitle("Internet Connectivity Error");
                        alertDialog.setMessage("Please check your internet connection");
                        alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        alertDialog.show();
                    } else if (isNetworkConnected() == true) {
                        ringProgressDialog = ProgressDialog.show(MainActivity.this, "Please wait ...", "Downloading Questions ...", true);
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
        });
    }

    private String readFromFileToken() {
        String ret = "";
        String toPath = "/data/data/" + getPackageName();
        try {
            InputStream inputStream = openFileInput("token.txt");
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

    private String readFromFile() {
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

    private void checkFiles() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        AssetManager assetMgr = getAssets();
        InputStream in = null;
        OutputStream out = null;
        String toPath = "/data/data/" + getPackageName() + "/files/";
        String toPathImages = "/data/data/" + getPackageName() + "/files/images";
        Boolean fileThere = fileExistance("myJSON.txt");
        Boolean fileIDThere = fileExistance("myQuestionIds.txt");
        if (fileThere == false) {
            copyAssetFolder(assetMgr, "json", toPath);
            copyAssetFolder(assetMgr, "ids", toPath);
            copyAssetFolder(assetMgr, "user", toPath);
            copyAssetFolder(assetMgr, "token", toPath);
            copyAssetFolder(assetMgr, "myImages", toPathImages);
        }
    }

    private static boolean copyAssetFolder(AssetManager assetManager,
                                           String fromAssetPath, String toPath) {
        try {
            String[] files = assetManager.list(fromAssetPath);
            new File(toPath).mkdirs();
            boolean res = true;
            for (String file : files)
                if (file.contains("."))
                    res &= copyAsset(assetManager,
                            fromAssetPath + "/" + file,
                            toPath + "/" + file);
                else
                    res &= copyAssetFolder(assetManager,
                            fromAssetPath + "/" + file,
                            toPath + "/" + file);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean copyAsset(AssetManager assetManager,
                                     String fromAssetPath, String toPath) {
        InputStream in = null;
        OutputStream out = null;
        try {
            String[] fileNames = assetManager.list(fromAssetPath);
            in = assetManager.open(fromAssetPath);
            new File(toPath).createNewFile();
            out = new FileOutputStream(toPath);
            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    private boolean fileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.action_exam).setEnabled(menuStatus);
        menu.findItem(R.id.action_level).setEnabled(menuStatus);
        myUserString = readFromFileUser();
        myTokenString = readFromFileToken();
        cookie = myUserString.substring(1, myUserString.length() - 1);
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
        try {
            MenuItem item1 = menu.findItem(R.id.action_logout);
            if(logInToken==null){
                item1.setTitle("Log In");
            }
            else{
                new CheckToken().execute("https://www.faircity.com/papi/api/user/preference").get();
                String menuTitle = item1.getTitle().toString();
                if (logStatus == 200) {
                    item1.setTitle("Log Out");
                } else {
                    item1.setTitle("Log In");
                }
            }
        } catch (ExecutionException er) {
            er.printStackTrace();
        } catch (InterruptedException er) {
            er.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_exam:
                myUserString = readFromFileUser();
                myTokenString = readFromFileToken();
                cookie = myUserString.substring(1, myUserString.length() - 1);
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
                userExam();

                return true;
            case R.id.action_level:
                myUserString = readFromFileUser();
                myTokenString = readFromFileToken();
                cookie = myUserString.substring(1, myUserString.length() - 1);
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
                userDifficulty();
                return true;
            case R.id.action_logout:

                MenuItem item1 = menu.findItem(R.id.action_logout);
                String menuTitle = item1.getTitle().toString();

                if (menuTitle.equals("Log Out")) {
                    myTokenString = readFromFileToken();
                    writeTokenJSON("{\"token\" : null}");
                } else if (menuTitle.equals("Log In")) {
                    Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                    chooseExam.putExtra("mode", "preferences");
                    chooseExam.putExtra("userExam", userExam);
                    chooseExam.putExtra("userDiff", userDiff);
                    startActivity(chooseExam);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void userExam() {
        if (logInToken==null) {
            Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
            chooseExam.putExtra("mode", "preferences");
            chooseExam.putExtra("userExam", userExam);
            chooseExam.putExtra("userDiff", userDiff);
            startActivity(chooseExam);
        } else {
            Intent intent = new Intent(MainActivity.this, ChooseCategory.class);
            intent.putExtra("mode", "exam");
            startActivity(intent);
        }
    }

    private void userDifficulty() {
        if (logInToken==null) {
            Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
            chooseExam.putExtra("mode", "online");
            chooseExam.putExtra("userExam", userExam);
            chooseExam.putExtra("userDiff", userDiff);
            startActivity(chooseExam);
        } else {
            if (isNetworkConnected() == false) {
                AlertDialog alertDialog = new AlertDialog.Builder(
                        MainActivity.this).create();
                alertDialog.setTitle("Internet Connectivity Error");
                alertDialog.setMessage("Please check your internet connection");
                alertDialog.setButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alertDialog.show();
            } else if (isNetworkConnected() == true) {
                if(userDiff==0.4){
                    final CharSequence levels[] = new CharSequence[]{"Easy", "Moderate - Current Level", "Difficult"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pick a difficulty level");
                    builder.setItems(levels, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDiff = which + 1;
                            if (which == 0) {
                                userDiff = 0.3;
                            } else if (which == 1) {
                                userDiff = 0.5;
                            } else if (which == 2) {
                                userDiff = 0.7;
                            }
                            writeUserJSON("[{\"currentUserId\" : " + userID + ", \"currentExamId\" : " + userExam + ", \"currentDifficultyLevel\" : " + userDiff + ", \"defaultExam\" : 3, \"apiRoute\" : \"" + apiRoute + "\"}]");
                            sendJSON = "{\"examId\" : " + userExam + ", \"level\" : " + userDiff + "}";
                            if (logInStatus == 401) {
                                Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                                chooseExam.putExtra("mode", "preferences");
                                chooseExam.putExtra("userExam", userExam);
                                chooseExam.putExtra("userDiff", userDiff);
                                startActivity(chooseExam);
                            }
                        }
                    });
                    builder.show();
                }
                else if(userDiff==0.5){
                    final CharSequence levels[] = new CharSequence[]{"Easy", "Moderate - Current Level", "Difficult"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pick a difficulty level");
                    builder.setItems(levels, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDiff = which + 1;
                            if (which == 0) {
                                userDiff = 0.3;
                            } else if (which == 1) {
                                userDiff = 0.5;
                            } else if (which == 2) {
                                userDiff = 0.7;
                            }
                            writeUserJSON("[{\"currentUserId\" : " + userID + ", \"currentExamId\" : " + userExam + ", \"currentDifficultyLevel\" : " + userDiff + ", \"defaultExam\" : 3, \"apiRoute\" : \"" + apiRoute + "\"}]");
                            sendJSON = "{\"examId\" : " + userExam + ", \"level\" : " + userDiff + "}";
                            if (logInStatus == 401) {
                                Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                                chooseExam.putExtra("mode", "preferences");
                                chooseExam.putExtra("userExam", userExam);
                                chooseExam.putExtra("userDiff", userDiff);
                                startActivity(chooseExam);
                            }
                        }
                    });
                    builder.show();
                }
                else if(userDiff==0.3){
                    final CharSequence levels[] = new CharSequence[]{"Easy - Current Level", "Moderate", "Difficult"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pick a difficulty level");
                    builder.setItems(levels, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDiff = which + 1;
                            if (which == 0) {
                                userDiff = 0.3;
                            } else if (which == 1) {
                                userDiff = 0.5;
                            } else if (which == 2) {
                                userDiff = 0.7;
                            }
                            writeUserJSON("[{\"currentUserId\" : " + userID + ", \"currentExamId\" : " + userExam + ", \"currentDifficultyLevel\" : " + userDiff + ", \"defaultExam\" : 3, \"apiRoute\" : \"" + apiRoute + "\"}]");
                            sendJSON = "{\"examId\" : " + userExam + ", \"level\" : " + userDiff + "}";
                            if (logInStatus == 401) {
                                Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                                chooseExam.putExtra("mode", "preferences");
                                chooseExam.putExtra("userExam", userExam);
                                chooseExam.putExtra("userDiff", userDiff);
                                startActivity(chooseExam);
                            }
                        }
                    });
                    builder.show();
                }
                else if(userDiff==0.7){
                    final CharSequence levels[] = new CharSequence[]{"Easy", "Moderate", "Difficult - Current Level"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pick a difficulty level");
                    builder.setItems(levels, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDiff = which + 1;
                            if (which == 0) {
                                userDiff = 0.3;
                            } else if (which == 1) {
                                userDiff = 0.5;
                            } else if (which == 2) {
                                userDiff = 0.7;
                            }
                            writeUserJSON("[{\"currentUserId\" : " + userID + ", \"currentExamId\" : " + userExam + ", \"currentDifficultyLevel\" : " + userDiff + ", \"defaultExam\" : 3, \"apiRoute\" : \"" + apiRoute + "\"}]");
                            sendJSON = "{\"examId\" : " + userExam + ", \"level\" : " + userDiff + "}";
                            if (logInStatus == 401) {
                                Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                                chooseExam.putExtra("mode", "preferences");
                                chooseExam.putExtra("userExam", userExam);
                                chooseExam.putExtra("userDiff", userDiff);
                                startActivity(chooseExam);
                            }
                        }
                    });
                    builder.show();
                }
                else{
                    final CharSequence levels[] = new CharSequence[]{"Easy", "Moderate", "Difficult"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Pick a difficulty level");
                    builder.setItems(levels, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            userDiff = which + 1;
                            if (which == 0) {
                                userDiff = 0.3;
                            } else if (which == 1) {
                                userDiff = 0.5;
                            } else if (which == 2) {
                                userDiff = 0.7;
                            }
                            writeUserJSON("[{\"currentUserId\" : " + userID + ", \"currentExamId\" : " + userExam + ", \"currentDifficultyLevel\" : " + userDiff + ", \"defaultExam\" : 3, \"apiRoute\" : \"" + apiRoute + "\"}]");
                            sendJSON = "{\"examId\" : " + userExam + ", \"level\" : " + userDiff + "}";
                            if (logInStatus == 401) {
                                Intent chooseExam = new Intent(MainActivity.this, LogIn.class);
                                chooseExam.putExtra("mode", "preferences");
                                chooseExam.putExtra("userExam", userExam);
                                chooseExam.putExtra("userDiff", userDiff);
                                startActivity(chooseExam);
                            }
                        }
                    });
                    builder.show();
                }
            }
        }
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

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private class GetAllExams extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSON, MainActivity.this);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
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

    private class CheckToken extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(MainActivity.this);
            jsonParser.setUserToken(logInToken);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            logStatus = jsonParser.getStatus();
            String ret = "";
            return ret;
        }
        protected void onPostExecute(String result) {
            myJSONString = result;
        }
    }

    private class DownloadQuestion extends AsyncTask<String, Integer, String> {
        Map<String, QuestionAPI> qAPI;
        QuestionAPI[] tempArray;

        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(MainActivity.this);
            jsonParser.setUserToken(logInToken);
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
            AlertDialog alertDialog = new AlertDialog.Builder(
                    MainActivity.this).create();
            alertDialog.setTitle("Questions Downloaded");
            alertDialog.setMessage("Please click on Offline Mode to access your updated questions");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alertDialog.show();
            ringProgressDialog.dismiss();
        }
    }

    private class UpdateUserPreferences extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSON, MainActivity.this);
            jsonParser.setUserToken(logInToken);
            jsonParser.setHttpType(true);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            logInStatus = jsonParser.getStatus();
            FileOutputStream outputStream;
            return myJSON;
        }
        protected void onPostExecute(String result) {
            myJSONString = result;
        }
    }
}
