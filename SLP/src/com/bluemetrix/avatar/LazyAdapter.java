package com.bluemetrix.avatar;

import java.util.ArrayList;
import java.util.HashMap;

import com.bluemetrix.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class LazyAdapter extends BaseAdapter
{	
	private Activity activity; 
    private ArrayList lt;
    private HashMap map;
    private ArrayList<String> data; 
    private ArrayList<CheckoutItem> basket;
    private static LayoutInflater inflater=null; 

    public LazyAdapter(Activity a, ArrayList<String> d)
    {
    	activity = a;
    	data = d;
    	inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public LazyAdapter(ArrayList<CheckoutItem> d, Activity a)
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
		View vi = convertView;
		CheckoutItem item = basket.get(position);//get basket item
		String itemTitle = item.getTitle();
		String itemPoints = item.getPoints();
		int itemImage = item.getGarmentImage();
		
		if(convertView==null)
		{
			vi = inflater.inflate(R.layout.list_row, null);
		}
		
		TextView title = (TextView) vi.findViewById(R.id.title);//name of garment in listview
		TextView points = (TextView) vi.findViewById(R.id.checkout_list_points);//no. of points for garment
		ImageView gar = (ImageView) vi.findViewById(R.id.list_image);
		
		title.setText(itemTitle);
		points.setText(itemPoints);
		gar.setImageResource(itemImage);

		return vi;
	}	
}
