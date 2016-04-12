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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bluelinelabs.logansquare.LoganSquare;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

public class ChooseCategory extends AppCompatActivity {
    private TextView actionBarTitle;
    private ImageView downloadIcon;
    private ScrollView scroll;
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
    private CategoryListAPI ex;
    private String mode = "";
    private String logInToken;
    private UserDetailsAPI myToken = new UserDetailsAPI();
    private String myTokenString = "";
    private Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        actionBarTitle.setText("Choose Category");

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);
        scroll = (ScrollView) findViewById(R.id.scrollView);

        myContext = getApplicationContext();

        Bundle extras = getIntent().getExtras();
        if (extras.containsKey("mode")) {
            mode = extras.getString("mode");
        }

        myTokenString = readFromFileToken();
        myUserString = readFromFileUser();
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
                    downloadIcon.setEnabled(false);
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
            String result = new GetAllExams().execute("https://www.faircity.com/papi/api/category/list").get();
            ex = LoganSquare.parse(result,
                    CategoryListAPI.class);
            LinearLayout ll = (LinearLayout) findViewById(R.id.list_categories);
            for (int i = 0; i < ex.getData().length; i++) {
                Button btn = new Button(this);
                btn.setId(2000 + ex.getData()[i].getCategoryId());
                btn.setText(ex.getData()[i].getCategoryName());
                btn.setBackgroundColor(Color.parseColor("#15365b"));
                btn.setTextColor(Color.parseColor("#ffffff"));
                btn.setOnClickListener(getOnClickDoSomething(btn));
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.bottomMargin = 10;
                lp.topMargin = 10;
                ll.addView(btn, lp);
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
        getMenuInflater().inflate(R.menu.menu_choose_category, menu);
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
            MyJSONParser jsonParser = new MyJSONParser(ChooseCategory.this);
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

    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                button.getId();
                int catID = (button.getId() - 2000);
                Intent chooseExam = new Intent(ChooseCategory.this, ChooseExam.class);
                chooseExam.putExtra("id", catID);
                chooseExam.putExtra("name", ex.getData()[catID - 1].getCategoryName());
                chooseExam.putExtra("mode", mode);
                startActivity(chooseExam);
            }
        };
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
