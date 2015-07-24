package com.anitaoconnor.beerapplication;
import android.os.Parcel;
import android.os.Parcelable;
/**
 * Created by Anita O Connor on 21/07/2015.
 */

//Beer Object
public class Beer implements Parcelable{
    private String id;
    private String name;
    private String labelURL;

    public Beer(){

    }
    public Beer(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getlabelURL(){
        return this.labelURL;
    }
    public void setLabelURL(String labelURL){
        this.labelURL = labelURL;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public int describeContents()
    {
        return 1;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(labelURL);
    }
    public Beer(Parcel source)
    {
        id = source.readString();
        name = source.readString();
        labelURL = source.readString();
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        @Override
        public Beer createFromParcel(Parcel source)
        {
            return new Beer(source);
        }

        @Override
        public Beer[] newArray(int size)
        {
            return new Beer[size];
        }
    };
}
