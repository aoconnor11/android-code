package com.bluemetrix.friends;

import java.util.Calendar;

import com.bluemetrix.messages.MessageItem;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend  implements Parcelable
{
	private int friendID;
	private String friendName;
	private int friendScore;
	private int friendRank;
	
	public Friend()
	{
		
	}
	
	public Friend(int id)
	{
		this.friendID = id;
	}
	
	public Friend(int id, String name, int score, int rank)
	{	
		this.friendID = id;
		this.friendName = name;
		this.friendScore = score;
		this.friendRank = rank;
	}
	
	public int getFriendID() 
	{
		return friendID;
	}

	public void setFriendID(int friendID) 
	{
		this.friendID = friendID;
	}

	public String getFriendName() 
	{
		return friendName;
	}

	public void setFriendName(String friendName) 
	{
		this.friendName = friendName;
	}

	public int getFriendScore() 
	{
		return friendScore;
	}

	public void setFriendScore(int friendScore) 
	{
		this.friendScore = friendScore;
	}

	public int getFriendRank() 
	{
		return friendRank;
	}

	public void setFriendRank(int friendRank) 
	{
		this.friendRank = friendRank;
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
		dest.writeInt(friendID);
		dest.writeString(friendName);
		dest.writeInt(friendScore);
		dest.writeInt(friendRank);
	}

	public Friend(Parcel source)
	{
		friendID = source.readInt();
		friendName = source.readString();
		friendScore = source.readInt();
		friendRank = source.readInt();
	}
	
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() 
	{
		@Override
		public Friend createFromParcel(Parcel source) 
		{
			return new Friend(source);
	    }
	 
		@Override
		public Friend[] newArray(int size) 
		{
			return new Friend[size];
		}	
	};	
}
