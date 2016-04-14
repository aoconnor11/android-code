package com.bluemetrix.messages;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.bluemetrix.R;
import com.bluemetrix.avatar.CheckoutItem;

import android.app.Activity;
import android.content.Context;
import android.net.ParseException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class LazyMessAdapter extends BaseAdapter{
	
	private Activity activity; 
    private ArrayList lt;
    private HashMap map;
    private ArrayList<String> data; 
    private ArrayList<CheckoutItem> basket;
    private ArrayList<MessageItem> messageList;
    private static LayoutInflater inflater=null; 
   // public ImageLoader imageLoader; 

    public LazyMessAdapter(Activity a, ArrayList<String> d){
    	activity = a;
    	data = d;
    	inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    public LazyMessAdapter(ArrayList<MessageItem> d, Activity a){
    	activity = a;
    	//basket = d;
    	messageList = d;
    	inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    
    
	@Override
	public int getCount() {
		return messageList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		MessageItem mess = messageList.get(position);
		String messTitle = mess.getTitle();
		int avatar = mess.getAvatarImage();
		Calendar myTime = mess.getMessTime();
		Date myDate = myTime.getTime();


		
		if(convertView==null){
		
			vi = inflater.inflate(R.layout.message_row, null);
		}
		
	
		ImageView ava = (ImageView) vi.findViewById(R.id.list_image);
		TextView title = (TextView) vi.findViewById(R.id.title);
		TextView messTime = (TextView) vi.findViewById(R.id.textViewMessageTime);
		
		
		TextView points = (TextView) vi.findViewById(R.id.checkout_list_points);//no. of points for garment
	
		try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh.mm aa");
		String currentDateandTime = sdf.format(myDate);

		title.setText(messTitle);
		ava.setImageResource(avatar);
		messTime.setText(currentDateandTime);

		}
		catch(ParseException e)
		{
			//time not in acceptable format!!
		}

		return vi;
	}

	
	
	
	
}
