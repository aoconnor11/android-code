package com.bluemetrix.storeapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class StoreAdapter extends BaseAdapter
{	
	private Activity activity; 
    private ArrayList lt;
    private HashMap map;
    private ArrayList<String> data; 
    private ArrayList<StoreDetails> basket;
    private static LayoutInflater inflater=null; 

    public StoreAdapter(Activity a, ArrayList<String> d)
    {
    	activity = a;
    	data = d;
    	inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public StoreAdapter(ArrayList<StoreDetails> d, Activity a)
    {
    	activity = a;
    	basket = d;
    	inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
   
	@Override
	public int getCount() 
	{
		return basket.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return basket.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{	
		View vi = convertView;
		StoreDetails item = basket.get(position);//get basket item
		String itemTitle = item.getTitle();
		
		if(convertView==null)
		{
			vi = inflater.inflate(R.layout.store_row, null);
		}
		
		TextView title = (TextView) vi.findViewById(R.id.title);

		
		title.setText(itemTitle);

		return vi;
	}	
}
