package com.bluemetrix.messages;

import java.util.Calendar;

import android.os.Parcel;
import android.os.Parcelable;

public class MessageItem implements Parcelable{
	
	private int avatarImage;
	private String title;//display in message List
	private String messContent;// display in message view
	private String points;
	private Calendar messTime;
	private int id;//unique message id
	
	public MessageItem(){
		
	}
	
	public MessageItem(int avatarImage, String title, Calendar messTime, String messContent){
		this.avatarImage = avatarImage;
		this.title = title;
		this.messTime = messTime;
		this.messContent = messContent;
	}
	

	public int getAvatarImage() {
		return avatarImage;
	}

	public void setAvatarImage(int avatarImage) {
		this.avatarImage = avatarImage;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public Calendar getMessTime() {
		return messTime;
	}

	public void setMessTime(Calendar messTime) {
		this.messTime = messTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	
	
	//Parcelable methods
	
	@Override
	public int describeContents() {
		return 1;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(avatarImage);
		dest.writeString(title);
		dest.writeSerializable(messTime);
		dest.writeInt(id);
		dest.writeString(messContent);
	}
	
	public MessageItem(Parcel source){
		avatarImage = source.readInt();
		title = source.readString();
		messTime = (Calendar) source.readSerializable();
		id = source.readInt();
		messContent = source.readString();
	}
	
	public String getMessContent() {
		return messContent;
	}

	public void setMessContent(String messContent) {
		this.messContent = messContent;
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		 @Override
	        public MessageItem createFromParcel(Parcel source) {
	            // TODO Auto-generated method stub
	            return new MessageItem(source);
	        }
	 
	        @Override
	        public MessageItem[] newArray(int size) {
	            // TODO Auto-generated method stub
	            return new MessageItem[size];
	        }
		
	};
	
	
	

}
