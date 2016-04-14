package com.bluemetrix.storeapp;


import android.os.Parcel;
import android.os.Parcelable;

public class StoreItem implements Parcelable
{	
	private int itemImage;
	private String title;
	private int id;
	private int quantity;
	private String offer;
	
	public StoreItem()
	{
		
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public StoreItem(int itemImage, String title, int id, int quantity, String offer)
	{
		this.itemImage = itemImage;
		this.title = title;
		this.id = id;
		this.quantity = quantity;
		this.offer = offer;
	}
	
	public StoreItem(int itemImage, String title, int id, String offer)
	{
		this.itemImage = itemImage;
		this.title = title;
		this.id = id;
		this.offer = offer;
	}
	
	public int getItemImage() 
	{
		return itemImage;
	}

	public void setItemImage(int itemImage)
	{
		this.itemImage = itemImage;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title) 
	{
		this.title = title;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	
	//Parcelable methods
		@Override
		public int describeContents() 
		{
			return 1;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags)
		{
			dest.writeInt(itemImage);
			dest.writeString(title);
			dest.writeInt(id);
			dest.writeInt(quantity);
			dest.writeString(offer);
		}

		public StoreItem(Parcel source)
		{
			itemImage = source.readInt();
			title = source.readString();
			id = source.readInt();
			quantity = source.readInt();
			offer = source.readString();
		}
		
		public static final Parcelable.Creator CREATOR = new Parcelable.Creator() 
		{
			@Override
			public StoreItem createFromParcel(Parcel source) 
			{
				return new StoreItem(source);
		    }
		 
			@Override
			public StoreItem[] newArray(int size) 
			{
				return new StoreItem[size];
			}	
		};	

	
}
