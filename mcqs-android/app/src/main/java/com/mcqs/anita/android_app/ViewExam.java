package com.mcqs.anita.android_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.view.View;

import com.bluelinelabs.logansquare.LoganSquare;
import com.google.gson.JsonParser;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import us.feras.mdv.MarkdownView;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.view.View.OnClickListener;

import org.json.JSONArray;
import org.json.JSONException;

@SuppressLint("SetJavaScriptEnabled")
public class ViewExam extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Question> questionList = new ArrayList<Question>();
    private MarkdownView questionText;
    private Button optionOne;
    private Button optionTwo;
    private Button optionThree;
    private Button optionFour;
    private Button optionFive;
    private Question myQuestion1;
    private ArrayList<QuestionOptions> myOptions;
    private Button nextButton;
    private Button explanationButton;
    private Button imageButton;
    private Button questionButton;
    private MarkdownView explainText;
    private int progressCount;
    private ScrollView explainScroll;
    private ScrollView backgroundScroll;
    private ScrollView parentScroll;
    private Boolean viewStatus = false;
    private ProgressBar progressBar;
    private int progressInt = 0;
    private int progressMax = 0;
    private String myJSONString = "";
    private TouchImageView questionImage;
    private TextView actionBarTitle;
    private TextView examScore;
    private String[] imageURLS;
    private int count = 1;
    private int jsonArraySize1;
    private int myCount = 3;
    private ImageView downloadIcon;
    private Question displayQ = new Question();
    private QuestionAPI displayQAPI = new QuestionAPI();
    private ArrayList<QuestionAPI> qList;
    private List<Question> finalList;
    private ArrayList<Integer> questionIds = new ArrayList<>();
    private String questionIDTemp = "";
    private ArrayList<Boolean> progressData = new ArrayList<>();
    private String mode;
    private int examNo;
    private int examCount = 0;
    private int examCountCorrect = 0;
    private Map<String, QuestionAPI> qAPI;
    private QuestionOptions[] apiOptions;
    private String[] images;
    private QuestionAPI qAPI2;
    QuestionAPI[] tempArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_question);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        actionBarTitle = (TextView) findViewById(R.id.action_bar_text);
        downloadIcon = (ImageView) findViewById(R.id.imageViewDownloadIcon);
        downloadIcon.setVisibility(View.INVISIBLE);

        ColorDrawable cd = new ColorDrawable(0xFF2662a6);
        ActionBar bar = getSupportActionBar();
        bar.setBackgroundDrawable(cd);

        Bundle extras = getIntent().getExtras();
        mode = extras.getString("mode");
        examNo = extras.getInt("noQuestions");

        Context myContext = getApplicationContext();

        finalList = new ArrayList<Question>();

        checkFiles();

        myJSONString = readFromFile();

        JsonParser jsonParser = new JsonParser();

        try {
            qAPI = LoganSquare.parseMap(myJSONString, QuestionAPI.class);
            Collection<QuestionAPI> values = qAPI.values();
            tempArray = values.toArray(new QuestionAPI[values.size()]);

            int choice = (int) (Math.random() * tempArray.length);
            displayQAPI = tempArray[choice];

            displayQAPI = checkImageFile(displayQAPI);

            displayQ.setQuestionId(displayQAPI.getQuestionId());
            displayQ.setBackground(displayQAPI.getBackground());
            displayQ.setQuestion(displayQAPI.getQuestion());
            displayQ.setCore(displayQAPI.getCore());
            displayQ.setExplanation(displayQAPI.getExplanation());

            String corrOption = String.valueOf(displayQAPI.getCorrectAnswer());

            ArrayList<QuestionOptions> tempOptions = new ArrayList<QuestionOptions>();
            for (Map.Entry<String, String> entry : displayQAPI.getOptions().entrySet()) {
                if (entry.getKey().equals(corrOption)) {
                    QuestionOptions q1 = new QuestionOptions();
                    q1.setAnswer(entry.getValue());
                    q1.setCorrectAnswer(true);
                    tempOptions.add(q1);
                } else {
                    QuestionOptions q1 = new QuestionOptions();
                    q1.setAnswer(entry.getValue());
                    q1.setCorrectAnswer(false);
                    tempOptions.add(q1);
                }
            }

            apiOptions = new QuestionOptions[tempOptions.size()];
            for (int i = 0; i < tempOptions.size(); i++) {
                QuestionOptions q2 = tempOptions.get(i);
                apiOptions[i] = q2;
            }

            ArrayList<String> imagesTemp = new ArrayList<String>();
            for (Map.Entry<String, String> entry : displayQAPI.getImages().entrySet()) {
                imagesTemp.add(entry.getValue());
            }

            images = new String[imagesTemp.size()];
            for (int i = 0; i < imagesTemp.size(); i++) {
                String temp = imagesTemp.get(i);
                images[i] = temp;
            }
            displayQ.setImages(images);
            displayQ.setQuestionOptions(apiOptions);

            displayQuestions(displayQ);

        } catch (IOException er) {
            er.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        Intent startQuiz = new Intent(ViewExam.this, MainActivity.class);
        startActivity(startQuiz);
    }

    private QuestionAPI checkImageFile(QuestionAPI myQuestion){
        boolean isThere=true;
        Context myContext = getApplicationContext();
        QuestionAPI displayQAPI = myQuestion;

        for (Map.Entry<String, String> entry : displayQAPI.getImages().entrySet()) {
            String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(entry.getValue());
            String name = URLUtil.guessFileName(entry.getValue(), null, fileExtenstion);

            String toPathImages = "/data/data/" + getPackageName() + "/files/images/";
            String path = myContext.getFilesDir().getAbsolutePath() + "/images/" + name;
            File file = new File(path);
            file.exists();

            if(file.exists()){
            }
            else{
                isThere=false;
            }
        }
        if(isThere==false){
         int choice = (int) (Math.random() * tempArray.length);
        displayQAPI = tempArray[choice];
        checkImageFile(displayQAPI);
       }
      else if(isThere==true){
            return displayQAPI;
        }
        return displayQAPI;
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

    private void checkFiles() {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        AssetManager assetMgr = getAssets();
        InputStream in = null;
        OutputStream out = null;
        String toPath = "/data/data/" + getPackageName() + "/files/";
        String toPathImages = "/data/data/" + getPackageName() + "/files/images";
        Boolean fileThere = fileExistance("myJSON.txt");
        Boolean fileIDThere = fileExistance("myQuestionIds.txt");
        if (fileThere == true) {
        } else {
            copyAssetFolder(assetMgr, "json", toPath);
            copyAssetFolder(assetMgr, "ids", toPath);
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

    private boolean fileExistance(String fname) {
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
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

    private void displayQuestions(Question myQ) {
        int questionNumbers = questionList.size();
        int choice = (int) (Math.random() * questionNumbers);
        Question displayQuestion = myQ;
        String displayBackgroundString = displayQuestion.getBackground();
        String displayQuestionString = displayQuestion.getQuestion();
        String displayCoreString = displayQuestion.getCore();
        String displayExplanationString = displayQuestion.getExplanation();
        final int qID = displayQuestion.getQuestionId();
        examScore = (TextView) findViewById(R.id.textView8);
        if (mode.equals("exam")) {
            examCount++;
            actionBarTitle.setText(examCount + "/" + examNo);
        }

        QuestionOptions[] questionOptions = displayQuestion.getQuestionOptions();
        myOptions = new ArrayList<QuestionOptions>(Arrays.asList(questionOptions));
        Collections.shuffle(myOptions);

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        progressBar.setMax(10);

        progressCount = 0;
        if (progressData.size() == 10) {
            for (int i = 0; i < progressData.size(); i++) {
                boolean progress = progressData.get(i);
                if (progress == true) {
                    progressCount = progressCount + 1;
                }
            }
        }
        progressBar.setProgress(progressCount);

        questionText = (MarkdownView) findViewById(R.id.textViewQuestion);
        optionOne = (Button) findViewById(R.id.buttonOption1);
        optionTwo = (Button) findViewById(R.id.buttonOption2);
        optionThree = (Button) findViewById(R.id.buttonOption3);
        optionFour = (Button) findViewById(R.id.buttonOption4);
        optionFive = (Button) findViewById(R.id.buttonOption5);
        nextButton = (Button) findViewById(R.id.buttonNext);
        explanationButton = (Button) findViewById(R.id.buttonExplanation);
        imageButton = (Button) findViewById(R.id.buttonImage);
        questionButton = (Button) findViewById(R.id.buttonQuestion);
        explainText = (MarkdownView) findViewById(R.id.textViewExplanation);
        backgroundScroll = (ScrollView) findViewById(R.id.scrollView);
        questionImage = (TouchImageView) findViewById(R.id.imageView);
        WebSettings settingsQ = questionText.getSettings();

        questionText.setScrollBarStyle(MarkdownView.SCROLLBARS_OUTSIDE_OVERLAY);
        questionText.setScrollbarFadingEnabled(false);

        WebSettings settingsE = explainText.getSettings();
        explainText.setScrollBarStyle(MarkdownView.SCROLLBARS_OUTSIDE_OVERLAY);
        explainText.setScrollbarFadingEnabled(false);

        String myQuestion = displayBackgroundString + "\n" + displayQuestionString;
        String myExplanation = displayCoreString + "\n" + displayExplanationString;
        myQuestion.replaceAll("\\s+", "\n");
        myQuestion.replaceAll("\\s+", System.getProperty("line.separator"));
        myExplanation.replaceAll("\\s+", "\n");
        myExplanation.replaceAll("\\s+", System.getProperty("line.separator"));
        myQuestion = myQuestion.replace("%", "&#37;");//replace % sign with ASCII - to prevent Markdown errors
        myExplanation = myExplanation.replace("%", "&#37;");
        String pattern = "(!\\[.+?.*?\\]\\()(.+?)(.jpg\\))";
        String replace1 = "<img src=\"file:///data/data/com.mcqs.anita.mcqs_android_coding/files/images/";
        String replace2 = ".jpg\" onclick=\"showAndroidImage(this.src)\">";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(myQuestion);
        if (m.find()) {
            myQuestion = new StringBuilder(myQuestion)
                    .replace(m.start(1), m.end(1), replace1)
                    .toString();
            myQuestion = myQuestion.replaceAll(".jpg\\)", replace2);
        }
        Matcher mEx = r.matcher(myExplanation);
        if (mEx.find()) {
            myExplanation = new StringBuilder(myExplanation)
                    .replace(mEx.start(1), mEx.end(1), replace1)
                    .toString();
            myExplanation = myExplanation.replaceAll(".jpg\\)", replace2);
        }

        settingsQ.setJavaScriptEnabled(true);
        settingsE.setJavaScriptEnabled(true);
        questionText.getSettings().setLoadsImagesAutomatically(true);
        explainText.getSettings().setLoadsImagesAutomatically(true);

        questionText.addJavascriptInterface(new WebAppInterface(this), "Android");
        explainText.addJavascriptInterface(new WebAppInterface(this), "Android");

        questionText.loadMarkdown("<script type=\"text/javascript\">var online = \"offline\"; function showAndroidImage(image) {Android.showImage(image, online);}</script>" + myQuestion, "file:///android_asset/markdown_css_themes/foghorn.css");

        explainText.loadMarkdown("<script type=\"text/javascript\"> var online = \"offline\"\n" +
                "    function showAndroidImage(ImageE) {\n" +
                "        Android.showImageE(ImageE, online);\n" +
                "    }\n" +
                "</script>" + myExplanation, "file:///android_asset/markdown_css_themes/foghorn.css");

        optionOne.setText(myOptions.get(0).getAnswer());
        optionTwo.setText(myOptions.get(1).getAnswer());
        optionThree.setText(myOptions.get(2).getAnswer());
        optionFour.setText(myOptions.get(3).getAnswer());
        optionFive.setText(myOptions.get(4).getAnswer());

        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
        optionFive.setOnClickListener(this);

        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                int choice = (int) (Math.random() * tempArray.length);
                displayQAPI = tempArray[choice];
                displayQAPI = checkImageFile(displayQAPI);
                displayQ.setQuestionId(displayQAPI.getQuestionId());
                displayQ.setBackground(displayQAPI.getBackground());
                displayQ.setQuestion(displayQAPI.getQuestion());
                displayQ.setCore(displayQAPI.getCore());
                displayQ.setExplanation(displayQAPI.getExplanation());
                String corrOption = String.valueOf(displayQAPI.getCorrectAnswer());

                ArrayList<QuestionOptions> tempOptions = new ArrayList<QuestionOptions>();
                for (Map.Entry<String, String> entry : displayQAPI.getOptions().entrySet()) {
                    if (entry.getKey().equals(corrOption)) {
                        QuestionOptions q1 = new QuestionOptions();
                        q1.setAnswer(entry.getValue());
                        q1.setCorrectAnswer(true);
                        tempOptions.add(q1);
                    } else {
                        QuestionOptions q1 = new QuestionOptions();
                        q1.setAnswer(entry.getValue());
                        q1.setCorrectAnswer(false);
                        tempOptions.add(q1);
                    }
                }

                apiOptions = new QuestionOptions[tempOptions.size()];
                for (int i = 0; i < tempOptions.size(); i++) {
                    QuestionOptions q2 = tempOptions.get(i);
                    apiOptions[i] = q2;
                }
                ArrayList<String> imagesTemp = new ArrayList<String>();
                for (Map.Entry<String, String> entry : displayQAPI.getImages().entrySet()) {
                    imagesTemp.add(entry.getValue());
                }

                images = new String[imagesTemp.size()];
                for (int i = 0; i < imagesTemp.size(); i++) {
                    String temp = imagesTemp.get(i);
                    images[i] = temp;
                }
                displayQ.setImages(images);
                displayQ.setQuestionOptions(apiOptions);

                long startTime = System.nanoTime();

                long endTime = System.nanoTime();
                long timeDifference = (endTime - startTime);
                String time = String.valueOf(timeDifference);

                count++;
                if (count > 10 && mode.equals("learn")) {
                    progressBar.setVisibility(View.VISIBLE);
                }
                if (mode.equals("learn")) {
                    displayQuestions(displayQ);
                }
                if (mode.equals("exam") && examCount < examNo) {
                    displayQuestions(displayQ);
                } else if (mode.equals("learn")) {
                    displayQuestions(displayQ);
                } else {
                    Intent startQuiz = new Intent(ViewExam.this, ExamResults.class);
                    startQuiz.putExtra("noQuestionsCorrect", examCountCorrect);
                    startQuiz.putExtra("noQuestions", examNo);
                    startActivity(startQuiz);
                }
                questionImage.resetZoom();
                explanationButton.setEnabled(false);
                nextButton.setEnabled(false);
                explainText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.VISIBLE);
                optionOne.setVisibility(View.VISIBLE);
                optionTwo.setVisibility(View.VISIBLE);
                optionThree.setVisibility(View.VISIBLE);
                optionFour.setVisibility(View.VISIBLE);
                optionFive.setVisibility(View.VISIBLE);
                questionImage.setVisibility(View.INVISIBLE);
                questionButton.setVisibility(View.INVISIBLE);
                explanationButton.setVisibility(View.VISIBLE);
                viewStatus = false;
                optionOne.setBackgroundColor(Color.parseColor("#A0C2E9"));
                optionTwo.setBackgroundColor(Color.parseColor("#A0C2E9"));
                optionThree.setBackgroundColor(Color.parseColor("#A0C2E9"));
                optionFour.setBackgroundColor(Color.parseColor("#A0C2E9"));
                optionFive.setBackgroundColor(Color.parseColor("#A0C2E9"));
            }
        });
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                explainText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.INVISIBLE);
                optionOne.setVisibility(View.INVISIBLE);
                optionTwo.setVisibility(View.INVISIBLE);
                optionThree.setVisibility(View.INVISIBLE);
                optionFour.setVisibility(View.INVISIBLE);
                optionFive.setVisibility(View.INVISIBLE);
                questionImage.setVisibility(View.VISIBLE);
                explanationButton.setVisibility(View.INVISIBLE);
                questionButton.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.INVISIBLE);
            }
        });
        questionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewStatus == false) {
                    explainText.setVisibility(View.INVISIBLE);
                    questionText.setVisibility(View.VISIBLE);
                    optionOne.setVisibility(View.VISIBLE);
                    optionTwo.setVisibility(View.VISIBLE);
                    optionThree.setVisibility(View.VISIBLE);
                    optionFour.setVisibility(View.VISIBLE);
                    optionFive.setVisibility(View.VISIBLE);
                    questionImage.setVisibility(View.INVISIBLE);
                    questionImage.resetZoom();
                    questionButton.setVisibility(View.INVISIBLE);
                    explanationButton.setVisibility(View.VISIBLE);
                }
                if (viewStatus == true) {
                    explainText.setVisibility(View.INVISIBLE);
                    questionText.setVisibility(View.VISIBLE);
                    optionOne.setVisibility(View.VISIBLE);
                    optionTwo.setVisibility(View.VISIBLE);
                    optionThree.setVisibility(View.VISIBLE);
                    optionFour.setVisibility(View.VISIBLE);
                    optionFive.setVisibility(View.VISIBLE);
                    questionImage.resetZoom();
                    questionImage.setVisibility(View.INVISIBLE);
                    questionButton.setVisibility(View.INVISIBLE);
                    imageButton.setVisibility(View.INVISIBLE);
                    explanationButton.setVisibility(View.VISIBLE);
                }
            }
        });
        explanationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                explainText.setVisibility(View.VISIBLE);
                questionImage.resetZoom();
                questionText.setVisibility(View.INVISIBLE);
                optionOne.setVisibility(View.INVISIBLE);
                optionTwo.setVisibility(View.INVISIBLE);
                optionThree.setVisibility(View.INVISIBLE);
                optionFour.setVisibility(View.INVISIBLE);
                optionFive.setVisibility(View.INVISIBLE);
                questionImage.setVisibility(View.INVISIBLE);
                questionButton.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.INVISIBLE);
                explanationButton.setVisibility(View.INVISIBLE);
                viewStatus = true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        boolean status;
        if(v.getId()==R.id.buttonOption1){
            status = myOptions.get(0).isCorrectAnswer();
            if(status==true){
                optionOne.setBackgroundColor(Color.parseColor("#4caf50"));
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(true);
                } else {
                    progressData.add(true);
                }
                progressBar.setProgress(progressCount + 1);
                if (mode.equals("exam")) {
                    examCountCorrect++;
                }
            }
            else{
                optionOne.setBackgroundColor(Color.parseColor("#F44336"));
                showCorrectAnswer(1);
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(false);
                } else {
                    progressData.add(false);
                }
                progressBar.setProgress(progressCount - 1);
            }

        }else if(v.getId()==R.id.buttonOption2){
            status = myOptions.get(1).isCorrectAnswer();
            if(status==true){
                optionTwo.setBackgroundColor(Color.parseColor("#4caf50"));
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(true);
                } else {
                    progressData.add(true);
                }
                progressBar.setProgress(progressCount + 1);
                if (mode.equals("exam")) {
                    examCountCorrect++;
                }
            }
            else{
                optionTwo.setBackgroundColor(Color.parseColor("#F44336"));
                showCorrectAnswer(2);
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(false);
                } else {
                    progressData.add(false);
                }
                progressBar.setProgress(progressCount - 1);
            }
        }else if(v.getId()==R.id.buttonOption3){
            status = myOptions.get(2).isCorrectAnswer();
            if(status==true){
                optionThree.setBackgroundColor(Color.parseColor("#4caf50"));
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(true);
                } else {
                    progressData.add(true);
                }
                progressBar.setProgress(progressCount + 1);
                if (mode.equals("exam")) {
                    examCountCorrect++;
                }
            }
            else{
                optionThree.setBackgroundColor(Color.parseColor("#F44336"));
                showCorrectAnswer(3);
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(false);
                } else {
                    progressData.add(false);
                }
                progressBar.setProgress(progressCount - 1);
            }
        }else if (v.getId()==R.id.buttonOption4){
            status = myOptions.get(3).isCorrectAnswer();
            if(status==true){
                optionFour.setBackgroundColor(Color.parseColor("#4caf50"));
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(true);
                } else {
                    progressData.add(true);
                }
                progressBar.setProgress(progressCount + 1);
                if (mode.equals("exam")) {
                    examCountCorrect++;
                }
            }
            else{
                optionFour.setBackgroundColor(Color.parseColor("#F44336"));
                showCorrectAnswer(4);
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(false);
                } else {
                    progressData.add(false);
                }
                progressBar.setProgress(progressCount - 1);
            }
        }else if(v.getId()==R.id.buttonOption5){
            status = myOptions.get(4).isCorrectAnswer();
            if(status==true){
                optionFive.setBackgroundColor(Color.parseColor("#4caf50"));
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(true);
                } else {
                    progressData.add(true);
                }
                progressBar.setProgress(progressCount + 1);
                if (mode.equals("exam")) {
                    examCountCorrect++;
                }
            }
            else{
                optionFive.setBackgroundColor(Color.parseColor("#F44336"));
                showCorrectAnswer(5);
                if (progressData.size() == 10) {
                    progressData.remove(0);
                    progressData.add(false);
                } else {
                    progressData.add(false);
                }
                progressBar.setProgress(progressCount - 1);
            }
        }
        disableOptionButtons();
        explanationButton.setEnabled(true);
        nextButton.setEnabled(true);
        questionButton.setVisibility(View.INVISIBLE);
        imageButton.setVisibility(View.INVISIBLE);
        explanationButton.setVisibility(View.VISIBLE);
    }

    private void showCorrectAnswer(int sel) {
        for (int i = 0; i < myOptions.size(); i++) {
            boolean stat = myOptions.get(i).isCorrectAnswer();
            if (stat == true) {
                int buttonID = i;
                if (sel == 1) {
                    if (i == 1) {
                        optionTwo.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 2) {
                        optionThree.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 3) {
                        optionFour.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 4) {
                        optionFive.setBackgroundColor(Color.parseColor("#4caf50"));
                    }
                } else if (sel == 2) {
                    if (i == 0) {
                        optionOne.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 2) {
                        optionThree.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 3) {
                        optionFour.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 4) {
                        optionFive.setBackgroundColor(Color.parseColor("#4caf50"));
                    }
                } else if (sel == 3) {
                    if (i == 0) {
                        optionOne.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 1) {
                        optionTwo.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 3) {
                        optionFour.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 4) {
                        optionFive.setBackgroundColor(Color.parseColor("#4caf50"));
                    }
                } else if (sel == 4) {
                    if (i == 0) {
                        optionOne.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 1) {
                        optionTwo.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 2) {
                        optionThree.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 4) {
                        optionFive.setBackgroundColor(Color.parseColor("#4caf50"));
                    }
                } else if (sel == 5) {
                    if (i == 0) {
                        optionOne.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 1) {
                        optionTwo.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 2) {
                        optionThree.setBackgroundColor(Color.parseColor("#4caf50"));
                    } else if (i == 3) {
                        optionFour.setBackgroundColor(Color.parseColor("#4caf50"));
                    }
                }
            }
        }
    }

    private void disableOptionButtons() {
        optionOne.setClickable(false);
        optionTwo.setClickable(false);
        optionThree.setClickable(false);
        optionFour.setClickable(false);
        optionFive.setClickable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_question, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    private class DownloadImages extends AsyncTask<Question, Integer, Integer> {
        Question myQ;

        protected Integer doInBackground(Question... urls) {
            myQ = urls[0];
            try {
                URL[] imageURLS = new URL[myQ.getImages().length];
                for (int j = 0; j < myQ.getImages().length; j++) {
                    URL imageURL = new URL(myQ.getImages()[j]);
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
            if (finalList.size() == 1) {
                int choice = (int) (Math.random() * finalList.size());
                displayQ = finalList.get(0);
                displayQuestions(displayQ);
            }
        }
    }
}
