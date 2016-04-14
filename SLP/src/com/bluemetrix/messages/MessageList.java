package com.bluemetrix.messages;

import java.util.ArrayList;
import java.util.Calendar;

import com.bluemetrix.R;
import com.bluemetrix.avatar.CheckoutItem;
import com.bluemetrix.avatar.LazyAdapter;
import com.bluemetrix.game.activities.SceneSelectionView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
@SuppressLint("ValidFragment")
public class MessageList extends Fragment{//extends Activity{

	private ListView list;
	private LazyMessAdapter lazy;
	private ArrayList<MessageItem> messList;
	View myFragmentView; 

	public MessageList(){
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		myFragmentView = inflater.inflate(R.layout.message_list, container, false); 
		Activity activity = getActivity();
		activity.setTitle("Messages");
		 Calendar cal = Calendar.getInstance();
		 messList = new ArrayList<MessageItem>();
		    list = (ListView) myFragmentView.findViewById(R.id.listViewMessages);
		
		       MessageItem mess1 = new MessageItem(R.drawable.storetorso1, "How are you?", cal, "Hi!");
	           MessageItem mess2 = new MessageItem(R.drawable.storetorso2, "What's happening?", cal,"How many lessons did you do to get that? ");
	           MessageItem mess3 = new MessageItem(R.drawable.storetorso3, "Any news?", cal, "Hey Babes! Love the new hair. How many lessons did you do to get that? Hey Babes! Love the new hair. How many lessons did you do to get that? Hey Babes! Love the new hair. How many lessons did you do to get that? Hey Babes!");
	           
	           //set unique id to each message
	           mess1.setId(1);
	           mess2.setId(2);
	           mess3.setId(3);
	           
	           messList.add(mess1);
	           messList.add(mess2);
	           messList.add(mess3);
		    
	           lazy=new LazyMessAdapter(messList, activity); 
	           
	           list.setAdapter(lazy);
	           
	           list.setOnItemClickListener(new OnItemClickListener() {
	   			@Override
	   			public void onItemClick(AdapterView<?> parent, View view, int position,
	   					long id) {
	   				

	   				MessageItem item = (MessageItem) messList.get(position);
	   				String mytitle = item.getTitle();
	   				System.out.println("my click: " + mytitle);
	   				Activity act = getActivity();
	   				
	   				
	   				
	   				//go to message page
	   				Intent intent = new Intent(act, MessageView.class);
	   				intent.putExtra("message", item);
		    	        startActivity(intent);


	   			}
	              });
	
		
		return myFragmentView; 
	}
	
	
}
	
	

           
           
           
	

	
	

