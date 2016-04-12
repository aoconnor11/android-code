package com.mcqs.anita.android_app;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
import java.util.Collection;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;
    private ArrayList<ExamAPI> totalExams;
    private List<ExamAPI> exams = null;
    private String myUserString = "";
    private String cookie = "";
    private UserDetails myUser = new UserDetails();
    private int userID;
    private int userExam;
    private double userDiff;
    private String userToken;
    private String apiRoute = "https://www.faircity.com/papi";
    private int defaultExam;
    private int logInStatus;
    private String sendJSON = "";
    private String mode = "";
    private String myJSONString = "";
    private String downloadedJSONTxt = "";
    private String logInToken;
    private UserDetailsAPI myToken = new UserDetailsAPI();
    private String myTokenString = "";
    private ProgressBar spinner;
    private ProgressDialog ringProgressDialog;
    private AlertDialog alertDialog;

    public ListViewAdapter(Context context, List<ExamAPI> exams, String mode, ProgressBar spinner) {
        mContext = context;
        this.exams = exams;
        this.mode = mode;
        inflater = LayoutInflater.from(mContext);
        this.totalExams = new ArrayList<ExamAPI>();
        this.totalExams.addAll(exams);
        this.spinner = spinner;
    }

    public class ViewHolder {
        TextView name;
    }

    @Override
    public int getCount() {
        return exams.size();
    }

    @Override
    public ExamAPI getItem(int position) {
        return exams.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, final ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item, null);
            holder.name = (TextView) view.findViewById(R.id.product_name);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(exams.get(position).getExamName());
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        mContext);
                alertDialogBuilder.setTitle(exams.get(position).getExamName());
                alertDialogBuilder
                        .setMessage(exams.get(position).getExamDesc())
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
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
                                writeTokenJSON("{\"token\" : \"" + "" + logInToken + "" + "\"}");
                                writeUserJSON("[{\"currentUserId\" : " + userID + ", \"currentExamId\" : " + exams.get(position).getExamId() + ", \"currentDifficultyLevel\" : " + userDiff + ", \"defaultExam\" : 3, \"apiRoute\" : \"" + apiRoute + "\"}]");
                                sendJSON = "{\"examId\" : " + exams.get(position).getExamId() + ", \"level\" : " + 0.4 + "}";
                                final Intent chooseExam2 = new Intent(mContext, MainActivity.class);
                                if (mode.equals("offline")) {
                                    dialog.cancel();
                                    spinner.setVisibility(View.VISIBLE);
                                    ringProgressDialog = ProgressDialog.show(mContext, "Please wait ...", "Downloading Questions ...", true);
                                    ringProgressDialog.setCancelable(true);
                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                try {
                                                    String myURL = "https://www.faircity.com/papi/api/user/question/weight?exam=" + exams.get(position).getExamId() + "&level=" + userDiff + "&number=" + 20;
                                                    downloadedJSONTxt = new DownloadQuestion().execute(myURL).get();
                                                } catch (InterruptedException ex) {
                                                    ex.printStackTrace();
                                                } catch (ExecutionException e) {
                                                    e.printStackTrace();
                                                }
                                            } catch (Exception e) {
                                            }
                                        }
                                    }
                                    ).start();
                                } else {
                                    mContext.startActivity(chooseExam2);
                                }
                                if (logInStatus == 401) {
                                    Intent chooseExam = new Intent(mContext, LogIn.class);
                                    chooseExam.putExtra("mode", "preferences");
                                    chooseExam.putExtra("userExam", userExam);
                                    chooseExam.putExtra("userDiff", userDiff);
                                    mContext.startActivity(chooseExam);
                                } else if (logInStatus == 200) {
                                } else if (!mode.equals("offline")) {
                                    final Intent chooseExam3 = new Intent(mContext, MainActivity.class);
                                    mContext.startActivity(chooseExam3);
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        exams.clear();
        if (charText.length() == 0) {
            exams.addAll(totalExams);
        } else {
            for (ExamAPI wp : totalExams) {
                if (wp.getExamName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    exams.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class DownloadQuestion extends AsyncTask<String, Integer, String> {
        Map<String, QuestionAPI> qAPI;
        QuestionAPI[] tempArray;
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(mContext);
            jsonParser.setUserToken(logInToken);
            String myJSON = jsonParser.getJSONFromUrl(urls[0]);
            int myStatus = jsonParser.getStatus();
            logInStatus = jsonParser.getStatus();
            FileOutputStream outputStream;
            String fileName = "myJSON.txt";
            try {
                outputStream = mContext.openFileOutput("myJSON.txt", Context.MODE_PRIVATE);
                outputStream.write(myJSON.getBytes());
                outputStream.close();
            } catch (FileNotFoundException er) {
                er.printStackTrace();
            } catch (IOException er) {
                er.printStackTrace();
            }
            String ret = "";
            String toPath = "/data/data/" + mContext.getPackageName();
            try {
                InputStream inputStream = mContext.openFileInput("myJSON.txt");
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
                            if (response!=404) {
                                imagesTemp.add(entry.getValue());
                            }
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    }
                }
                String toPathImages = "/data/data/" + mContext.getPackageName() + "/files/images";
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

    private class DownloadImages extends AsyncTask<ArrayList<String>, Integer, Integer> {
        Question myQ;
        ArrayList<String> myImages;
        protected Integer doInBackground(ArrayList<String>... urls) {
            myImages = urls[0];
            URL[] imageURLS = new URL[myImages.size()];
          try {
              for (int j = 0; j < myImages.size(); j++) {
                  URL imageURL = new URL(myImages.get(j));
                  imageURLS[j] = imageURL;
              }
          }
          catch (MalformedURLException e) {
              e.printStackTrace();
          }
            try {
                int count = imageURLS.length;
                for (int i = 0; i < count; i++) {
                    URL testPath = imageURLS[i];
                    String testPath2 = new File(testPath.getPath()).getName();
                    String toPathImages = "/data/data/" + mContext.getPackageName() + "/files/images/";
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
            }
            catch(FileNotFoundException e){
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return 1;
        }
        protected void onPostExecute(Integer result) {
            spinner.setVisibility(View.INVISIBLE);
            final Intent chooseExam2 = new Intent(mContext, MainActivity.class);
            AlertDialog alertDialog = new AlertDialog.Builder(
                    mContext).create();
            alertDialog.setTitle("Questions Downloaded");
            alertDialog.setMessage("Please click on Offline Mode to access your offline questions");
            alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mContext.startActivity(chooseExam2);
                }
            });
            alertDialog.show();
            ringProgressDialog.dismiss();
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
    private class UpdateUserPreferences extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... urls) {
            MyJSONParser jsonParser = new MyJSONParser(sendJSON, mContext);
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
    private void writeUserJSON(String userDetails) {
        FileOutputStream outputStream;
        try {
            outputStream = mContext.openFileOutput("user.txt", Context.MODE_PRIVATE);
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
            outputStream = mContext.openFileOutput("token.txt", Context.MODE_PRIVATE);
            outputStream.write(userDetails.getBytes());
            outputStream.close();
        } catch (FileNotFoundException er) {
            er.printStackTrace();
        } catch (IOException er) {
            er.printStackTrace();
        }
    }
    private String readFromFileToken() {
        String ret = "";
        String toPath = "/data/data/" + mContext.getPackageName();
        try {
            InputStream inputStream = mContext.openFileInput("token.txt");
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
        String toPath = "/data/data/" + mContext.getPackageName();
        try {
            InputStream inputStream = mContext.openFileInput("user.txt");
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
