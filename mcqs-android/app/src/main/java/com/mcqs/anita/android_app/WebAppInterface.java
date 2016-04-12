package com.mcqs.anita.android_app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import us.feras.mdv.MarkdownView;

public class WebAppInterface {
    Context mContext;
    Activity activity;

    WebAppInterface(Activity activity) {
        this.activity = activity;
    }

    @JavascriptInterface
    public void showImage(final String image, final String option) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView questionImage = (ImageView) activity.findViewById(R.id.imageView);
                MarkdownView explainText = (MarkdownView) activity.findViewById(R.id.textViewExplanation);
                MarkdownView questionText = (MarkdownView) activity.findViewById(R.id.textViewQuestion);
                Button optionOne = (Button) activity.findViewById(R.id.buttonOption1);
                Button optionTwo = (Button) activity.findViewById(R.id.buttonOption2);
                Button optionThree = (Button) activity.findViewById(R.id.buttonOption3);
                Button optionFour = (Button) activity.findViewById(R.id.buttonOption4);
                Button optionFive = (Button) activity.findViewById(R.id.buttonOption5);
                Button explanationButton = (Button) activity.findViewById(R.id.buttonExplanation);
                Button questionButton = (Button) activity.findViewById(R.id.buttonQuestion);
                Button imageButton = (Button) activity.findViewById(R.id.buttonImage);
                if (option.equals("online")) {
                    try {
                        Bitmap bit = new GetBitmapFromURL().execute(image).get();
                        questionImage.setImageBitmap(bit);
                    } catch (InterruptedException er) {
                        er.printStackTrace();
                    } catch (ExecutionException er) {
                        er.printStackTrace();
                    }
                } else if (option.equals("offline")) {
                    File file = new File(image);
                    questionImage.setImageURI(Uri.parse(image));
                }
                explainText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.INVISIBLE);
                optionOne.setVisibility(View.INVISIBLE);
                optionTwo.setVisibility(View.INVISIBLE);
                optionThree.setVisibility(View.INVISIBLE);
                optionFour.setVisibility(View.INVISIBLE);
                optionFive.setVisibility(View.INVISIBLE);
                questionImage.setVisibility(View.VISIBLE);
                questionButton.setVisibility(View.VISIBLE);
                imageButton.setVisibility(View.INVISIBLE);
                if (questionImage != null) {
                }
            }
        });
    }

    @JavascriptInterface
    public void showImageE(final String imageE, final String option) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView questionImage = (ImageView) activity.findViewById(R.id.imageView);
                MarkdownView explainText = (MarkdownView) activity.findViewById(R.id.textViewExplanation);
                MarkdownView questionText = (MarkdownView) activity.findViewById(R.id.textViewQuestion);
                Button optionOne = (Button) activity.findViewById(R.id.buttonOption1);
                Button optionTwo = (Button) activity.findViewById(R.id.buttonOption2);
                Button optionThree = (Button) activity.findViewById(R.id.buttonOption3);
                Button optionFour = (Button) activity.findViewById(R.id.buttonOption4);
                Button optionFive = (Button) activity.findViewById(R.id.buttonOption5);
                Button explanationButton = (Button) activity.findViewById(R.id.buttonExplanation);
                Button questionButton = (Button) activity.findViewById(R.id.buttonQuestion);
                Button imageButton = (Button) activity.findViewById(R.id.buttonImage);
                if (option.equals("online")) {
                    try {
                        Bitmap bit = new GetBitmapFromURL().execute(imageE).get();
                        questionImage.setImageBitmap(bit);
                    } catch (InterruptedException er) {
                        er.printStackTrace();
                    } catch (ExecutionException er) {
                        er.printStackTrace();
                    }
                } else if (option.equals("offline")) {
                    File file = new File(imageE);
                    questionImage.setImageURI(Uri.parse(imageE));
                }
                explainText.setVisibility(View.INVISIBLE);
                questionText.setVisibility(View.INVISIBLE);
                optionOne.setVisibility(View.INVISIBLE);
                optionTwo.setVisibility(View.INVISIBLE);
                optionThree.setVisibility(View.INVISIBLE);
                optionFour.setVisibility(View.INVISIBLE);
                optionFive.setVisibility(View.INVISIBLE);
                questionImage.setVisibility(View.VISIBLE);
                explanationButton.setVisibility(View.VISIBLE);
                questionButton.setVisibility(View.INVISIBLE);
                imageButton.setVisibility(View.INVISIBLE);
                if (questionImage != null) {
                }
            }
        });
    }

    class GetBitmapFromURL extends AsyncTask<String, Void, Bitmap> {
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        protected void onPostExecute(Bitmap feed) {
        }
    }

    @JavascriptInterface
    public void performClick(String value) {
    }
}
