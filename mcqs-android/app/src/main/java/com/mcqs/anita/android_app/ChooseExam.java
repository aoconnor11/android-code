package com.mcqs.anita.android_app;

import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.text.TextWatcher;
import android.widget.Toast;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class ChooseExam extends AppCompatActivity {
    private TextView actionBarTitle;
    private ImageView downloadIcon;
    private String myUserString = "";
    private String apiRoute = "https://www.faircity.com/papi";
    private String cookie = "";
    private UserDetails myUser;
    private int userExam;
    private int defaultExam;
    private int userID;
    private double userDiff;
    private NetworkChangeReceiver receiver;
    private boolean connection;
    private boolean conn = false;
    private int categoryID;
    private String categoryName;
    private TextView catName;
    private ExamListAPI myExams;
    private ArrayList<ExamAPI> totalExams = new ArrayList<ExamAPI>();
    private ListView lv;
    private ListViewAdapter adapter;
    private EditText inputSearch;
    private String mode = "";
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exam);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        actionBarTitle.setText("Choose Exam");

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);
        catName = (TextView) findViewById(R.id.textView18);
        spinner = (ProgressBar) findViewById(R.id.progressBar5);
        spinner.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        categoryID = extras.getInt("id");
        categoryName = extras.getString("name");
        mode = extras.getString("mode");
        catName.setText(categoryName);

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
                    Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_LONG).show();
                } else if (connection == true && conn == true) {
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Internet Connection Restored", Snackbar.LENGTH_SHORT);
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.GREEN);
                    snackbar.show();
                    downloadIcon.setEnabled(true);
                }
            }
        };
        registerReceiver(receiver, filter);

        try {
            String result = new GetAllExams().execute(apiRoute + "/api/category/exams/" + categoryID).get();
            myExams = LoganSquare.parse(result,
                    ExamListAPI.class);
            for (int i = 0; i < myExams.getData().length; i++) {
                totalExams.add(myExams.getData()[i]);
            }
            if (myExams.getNextPageUrl() == null) {
                adapter = new ListViewAdapter(this, totalExams, mode, spinner);
                lv = (ListView) findViewById(R.id.listView);
                inputSearch = (EditText) findViewById(R.id.editText10);
                lv.setAdapter(adapter);
                inputSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable arg0) {
                        String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                        adapter.filter(text);
                    }
                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                                                  int arg2, int arg3) {
                    }
                    @Override
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    }
                });
            } else {
                ExamList(myExams.getNextPageUrl());
            }
        } catch (InterruptedException er) {
            er.printStackTrace();
        } catch (ExecutionException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        }
    }

    private void ExamList(String url) {
        try {
            String result = new GetAllExams().execute(url).get();
            ExamListAPI tempExams = LoganSquare.parse(result,
                    ExamListAPI.class);
            for (int i = 0; i < tempExams.getData().length; i++) {
                totalExams.add(tempExams.getData()[i]);
            }
            if (tempExams.getNextPageUrl() == null) {
                adapter = new ListViewAdapter(this, totalExams, mode, spinner);
                lv = (ListView) findViewById(R.id.listView);
                inputSearch = (EditText) findViewById(R.id.editText10);
                lv.setAdapter(adapter);
                inputSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void afterTextChanged(Editable arg0) {
                        String text = inputSearch.getText().toString().toLowerCase(Locale.getDefault());
                        adapter.filter(text);
                    }
                    @Override
                    public void beforeTextChanged(CharSequence arg0, int arg1,
                                                  int arg2, int arg3) {
                    }
                    @Override
                    public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                              int arg3) {
                    }
                });
            } else {
                ExamList(tempExams.getNextPageUrl());
            }
        } catch (InterruptedException er) {
            er.printStackTrace();
        } catch (ExecutionException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose_exam, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private class GetAllExams extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(ChooseExam.this);
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
}
