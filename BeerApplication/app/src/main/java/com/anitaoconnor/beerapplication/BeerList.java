package com.anitaoconnor.beerapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * Created by Anita O Connor on 23/07/2015.
 */
//List of Favourite Beers
public class BeerList extends Activity {

    private ArrayList<Beer> myBeers;
    private ListView beerList;
    private LazyAdapter lazy;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_list_layout);

        Intent in = getIntent();
        beerList = (ListView) findViewById(R.id.listViewBeerItems);
        myBeers = getIntent().getParcelableArrayListExtra("list");

        lazy=new LazyAdapter(myBeers, this);
        beerList.setAdapter(lazy);
    }
}
