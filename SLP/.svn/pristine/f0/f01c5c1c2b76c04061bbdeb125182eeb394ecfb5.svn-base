package com.bluemetrix.friends;

import java.util.ArrayList;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimpleAdapter extends ArrayAdapter<String>
{	
	private static LayoutInflater inflater=null; 
	private final Context context;
	private final String[] values;
	private int[] ranks;
	private int option;
	 
	public SimpleAdapter(Context context, String[] values)
	{
		super(context, R.layout.friends_row, values);
	    this.values = values;
	    this.context = context;
	}
	public SimpleAdapter(Context context, String[] values, int[] ranks, int option)
	{
		super(context, R.layout.friends_row, values);
	    this.values = values;
	    this.context = context;
	    this.ranks = ranks;
	    this.option = option;
	}
	@Override
	public int getCount() 
	{
		return values.length;
	}
	public String getItem(String position) 
	{
		return position;
	}
	@Override
	public long getItemId(int position) 
	{
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		View rowView = new View(context);
		
		if(option==1)
		{//league table
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    rowView = inflater.inflate(R.layout.league_row, parent, false);
		    TextView myName = (TextView) rowView.findViewById(R.id.name);
		    TextView myRank = (TextView) rowView.findViewById(R.id.textRank);
		    myName.setText(values[position]);
		    myRank.setText(Integer.toString(ranks[position]));
		}
		else
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.friends_row, parent, false);
			TextView myName = (TextView) rowView.findViewById(R.id.name);
			myName.setText(values[position]);	
		}
		return rowView;
	}
}