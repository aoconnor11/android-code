package com.anitaoconnor.beerapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
/**
 * Created by Anita O Connor on 23/07/2015.
 */
//Adapts list of beers in a view for display in ListView
public class LazyAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Beer> myBeers;
    private static LayoutInflater inflater=null;

    public LazyAdapter(ArrayList<Beer> myBeers, Activity a){
        activity = a;
        this.myBeers = myBeers;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return myBeers.size();
    }
    @Override
    public Object getItem(int position)
    {
        return position;
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View vi = convertView;
        Beer myBeer = myBeers.get(position);
        String beerName = myBeer.getName();
        String beerLabel = myBeer.getlabelURL();

        if(convertView==null){
            vi = inflater.inflate(R.layout.list_row, null);
        }
        TextView name = (TextView) vi.findViewById(R.id.title);
        ImageView label = (ImageView) vi.findViewById(R.id.list_image);
        name.setText(beerName);
        Picasso.with(vi.getContext())
                .load(beerLabel)
                .into(label);

        return vi;
    }
}
