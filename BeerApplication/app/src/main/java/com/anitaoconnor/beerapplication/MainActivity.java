package com.anitaoconnor.beerapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import java.util.*;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

//Main Activity in Beer Application
public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private JSONParser jsonParser;
    private static String beerURL = "http://api.brewerydb.com/v2/beer/random?key=5bfa49764613ad705c51ad3dac403e1a";
    private JSONObject myJSON;
    private ArrayList<Beer> myBeers = new ArrayList<Beer>();
    private Beer myBeer = new Beer();
    private TextView beerName ;
    private ImageView beerImage;
    private Button noButton;
    private Button yesButton;
    private TranslateAnimation moveLefttoRight;
    private TranslateAnimation moveRighttoLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try
        {
            //can't download all available beers at the same time as it requires a premium account with Brewery DB
            //so I downloaded one random beer at a time
            //in future could put in checks using the unique beer id to make sure it hadn't been downloaded previously

            //download random beer - using AsyncTask - didn't get time to implement Retrofit
            myBeer = new DownloadBeer().execute(beerURL).get();
            beerName = (TextView) findViewById(R.id.textView);
            beerImage = (ImageView) findViewById(R.id.imageView);
            //set beer name & image
            beerName.setText(myBeer.getName());
            Picasso.with(this)
                    .load(myBeer.getlabelURL())
                    .into(beerImage);

            noButton = (Button) findViewById(R.id.buttonNo);
            yesButton = (Button) findViewById(R.id.buttonYes);

            noButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    try{
                        myBeer = new DownloadBeer().execute(beerURL).get();
                        beerName.setText(myBeer.getName());
                        Picasso.with(v.getContext())
                                .load(myBeer.getlabelURL())
                                .into(beerImage);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                }
            });
            yesButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    try{
                        myBeers.add(myBeer);//add Beer to arraylist of favourite beers
                        int beerSize = myBeers.size();

                        if(beerSize==10){
                            Intent in = new Intent(MainActivity.this,BeerList.class);
                            ArrayList<Beer> sendList = new ArrayList<Beer>();
                            for(int i=0; i<myBeers.size(); i++){
                                sendList.add(myBeers.get(i));
                            }
                            in.putParcelableArrayListExtra("list", sendList);
                            startActivity(in);
                        }
                        else{//arraylist has not reached 10
                            myBeer = new DownloadBeer().execute(beerURL).get();
                            beerName.setText(myBeer.getName());
                            Picasso.with(v.getContext())
                                    .load(myBeer.getlabelURL())
                                    .into(beerImage);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                }
            });

            beerImage.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0)
                {
                    Toast.makeText(MainActivity.this, "please swipe right to LIKE beer", Toast.LENGTH_SHORT).show();
                }
            });
            beerImage.setOnTouchListener(new OnSwipeTouchListener(){
                public boolean onSwipeTop()
                {//do nothing
                    return true;
                }
                public boolean onSwipeBottom()
                {//do nothing
                    return true;
                }
                public boolean onSwipeLeft(){
                    try {
                        myBeer = new DownloadBeer().execute(beerURL).get();
                        moveLefttoRight = new TranslateAnimation(0, -1000, 0, 0);
                        moveLefttoRight.setDuration(250);
                        moveLefttoRight.setFillAfter(true);
                        beerImage.startAnimation(moveLefttoRight);
                        moveBeer(myBeer);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
                public boolean onSwipeRight()//like beer
                {
                    try{
                        myBeers.add(myBeer);//add Beer to arraylist
                        int beerSize = myBeers.size();
                        if(beerSize==10){
                            Intent in = new Intent(MainActivity.this,BeerList.class);
                            ArrayList<Beer> sendList = new ArrayList<Beer>();
                            for(int i=0; i<myBeers.size(); i++){
                                sendList.add(myBeers.get(i));
                            }
                            in.putParcelableArrayListExtra("list", sendList);
                            startActivity(in);
                        }
                        else{//arraylist has not reached 10
                            myBeer = new DownloadBeer().execute(beerURL).get();
                            moveRighttoLeft = new TranslateAnimation(0,1000,0,0);
                            moveRighttoLeft.setDuration(250);
                            moveRighttoLeft.setFillAfter(true);
                            beerImage.startAnimation(moveRighttoLeft);
                            moveBeerRight(myBeer);
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    return true;
                }
            });
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void moveBeerRight(final Beer myB)
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                moveLefttoRight = new TranslateAnimation(-1000,0,0,0);
                moveLefttoRight.setDuration(250);
                moveLefttoRight.setFillAfter(true);
                beerName.setText(myB.getName());
                Picasso.with(getApplicationContext())
                        .load(myB.getlabelURL())
                        .into(beerImage);
                beerImage.startAnimation(moveLefttoRight);
            }
        }, 500);
    }

    private void moveBeer(final Beer myB)
    {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            public void run()
            {
                moveRighttoLeft = new TranslateAnimation(1000,0,0,0);
                moveRighttoLeft.setDuration(250);
                moveRighttoLeft.setFillAfter(true);
                beerName.setText(myB.getName());
                Picasso.with(getApplicationContext())
                        .load(myB.getlabelURL())
                        .into(beerImage);
                beerImage.startAnimation(moveRighttoLeft);
            }
        }, 500);
    }


    //Download beer details from BreweryDB using AsyncTask and JSONParser
    private class DownloadBeer extends AsyncTask<String, Integer, Beer> {
        @Override
        protected Beer doInBackground(String... urls) {
            JSONParser jsonParser = new JSONParser();
            JSONObject myJSON = jsonParser.getJSONFromUrl(beerURL);
            Beer myBeer = new Beer();
            try {
                String status = myJSON.getString("status");
                JSONObject myData = myJSON.getJSONObject("data");
                String name = myData.optString("name");
                String id = myData.optString("id");
                //check if labels is there - not all beers have labels
                if(myData.has("labels")){
                    JSONObject labels = myData.getJSONObject("labels");
                    String myLabel = labels.getString("large");
                    myBeer.setLabelURL(myLabel);
                }
                else{
                    //no label for beer - set label to generic beer bottle
                    myBeer.setLabelURL("http://www.iconexperience.com/_img/v_collection_png/512x512/shadow/beer_bottle.png");
                }

                myBeer.setId(id);
                myBeer.setName(name);
            }
            catch(JSONException ex){
                Log.v(TAG, "JSON Exception");
                ex.printStackTrace();
            }
            return myBeer;
        }
        protected void onPostExecute(Beer result)
        {
            myBeer = result;
        }
    }
}
