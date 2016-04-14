package com.bluemetrix.storeapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class LazyAdapter extends BaseAdapter
{	
	private Activity activity; 
	private ArrayList lt;
	private HashMap map;
	private ArrayList<String> data; 
	private ArrayList<StoreItem> basket;
	private Set<StoreItem> setList;
	private static LayoutInflater inflater=null; 
	private int option;
	private final int INVALID = -1;
	protected int DELETE_POS = -1;

	public LazyAdapter(Activity a, ArrayList<String> d)
	{
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public LazyAdapter(ArrayList<StoreItem> d, Activity a, int option)
	{
		this.option = option;
		activity = a;
		basket = d;
		inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public LazyAdapter(Set<StoreItem> d, Activity a, int option)
	{
		this.option = option;
		activity = a;
		setList = d;
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
	public void addItem(StoreItem item) {
		//
		basket.add(item);
		notifyDataSetChanged();
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	public void onSwipeItem(boolean isRight, int position) {
		// TODO Auto-generated method stub
		if (isRight == false) {
			DELETE_POS = position;
		} else if (DELETE_POS == position) {
			DELETE_POS = INVALID;
		}
		//
		notifyDataSetChanged();
	}

	public void deleteItem(int pos) {
		//
		basket.remove(pos);
		DELETE_POS = INVALID;
		notifyDataSetChanged();
	}

	public void addItemAll(List<StoreItem> item) {
		//
		basket.addAll(item);
		notifyDataSetChanged();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) 
	{	
		View vi = convertView;
		StoreItem item = basket.get(position);//get basket item
		String itemTitle = item.getTitle();
		String itemOffers = item.getOffer();
		int quantity = item.getQuantity();
		int itemImage = item.getItemImage();

		if(convertView==null)
		{
			vi = inflater.inflate(R.layout.list_row, null);
		}
		ImageView info = (ImageView) vi.findViewById(R.id.ImageViewYouTube);
		Button delete = (Button) vi.findViewById(R.id.delete);

		if (DELETE_POS == position) {
			delete.setVisibility(View.VISIBLE);
		} else
			delete.setVisibility(View.GONE);
		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				deleteItem(position);
				notifyDataSetChanged();
			}
		});


		if(option==1){

			info.setVisibility(View.INVISIBLE);  

		}
		else if(option==2){

			if(!itemOffers.equals("")){
				info.setVisibility(View.VISIBLE);  



				info.setOnClickListener(new OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						//go to webview page

						System.out.println("test link");

						Intent in = new Intent(activity, PlayVideo.class);

						activity.startActivity(in);    	





					}
				});

			}
			else if (itemOffers.equals("")){
				info.setVisibility(View.INVISIBLE);  
			}

		}
		TextView title = (TextView) vi.findViewById(R.id.title);
		ImageView gar = (ImageView) vi.findViewById(R.id.list_image);
		TextView offer = (TextView) vi.findViewById(R.id.offerList);

		title.setText(itemTitle +" x " + quantity);
		if (quantity!=1){
			//points.setText(quantity+" pieces");
		}
		else{
			//	points.setText(quantity+" piece");
		}
		gar.setImageResource(itemImage);
		offer.setText(itemOffers);

		return vi;
	}	
}
