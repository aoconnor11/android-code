package com.bluemetrix.storeapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class StoreList extends SherlockActivity{

	private Button items;
	private Button shoppingList;
	private int option;//make sure empty list
	private int userID;
	private ArrayList<StoreDetails> basket;
	private ArrayList<StoreItem> shopList;
	private ArrayList<StoreItem> newShopList;
	private ListView list;
	private StoreAdapter lazy;
	private String intString;
	private int shopID;
	JSONObject recvdjson;
	private int points;
	
	
	 public static DefaultHttpClient httpClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.stores_list);
		
		ActionBar actionBar = getSupportActionBar();
	    actionBar.setDisplayHomeAsUpEnabled(true);
	
		items = (Button) findViewById(R.id.buttonItems);
		shoppingList = (Button) findViewById(R.id.buttonList);
	
		Intent in = getIntent();
        option = in.getIntExtra("option", 1);
        userID = in.getIntExtra("user", 2);
        
        shopList = getIntent().getParcelableArrayListExtra ("list");
        points = getIntent().getIntExtra("points", 0);
        
        StoreList.httpClient = new DefaultHttpClient();      
	
        list = (ListView) findViewById(R.id.listViewStores);
	
        basket = new ArrayList<StoreDetails>();
        
        StoreDetails shop1 = new StoreDetails("Kensington, Morrisons M Local",313);
        StoreDetails shop2 = new StoreDetails("Shepherds Bush", 320);
        StoreDetails shop3 = new StoreDetails("Camden",300);
        StoreDetails shop4 = new StoreDetails("New Oxford St, Morrisons M Local", 315);
        StoreDetails shop5 = new StoreDetails("Holloway", 311);
        StoreDetails shop6 = new StoreDetails("Acton",295);
        StoreDetails shop7 = new StoreDetails("Becontree Heath",296);
        StoreDetails shop8 = new StoreDetails("Borehamwood", 297);
        StoreDetails shop9 = new StoreDetails("Brentford",298);
        StoreDetails shop10 = new StoreDetails("Camberwell", 299);
        StoreDetails shop11 = new StoreDetails("Chingford", 301);
        StoreDetails shop12 = new StoreDetails("Croydon",302);
        StoreDetails shop13 = new StoreDetails("Ealing, Morrisons M Local", 303);
        StoreDetails shop14 = new StoreDetails("Elm Park, Morrisons M Local", 304);
        StoreDetails shop15 = new StoreDetails("Enfield",305);
        StoreDetails shop16 = new StoreDetails("Enfield, Morrisons M Local",306);
        StoreDetails shop17 = new StoreDetails("Erith", 307);
        StoreDetails shop18 = new StoreDetails("Finchley, Morrisons M Local",308);
        StoreDetails shop19 = new StoreDetails("Harrow", 309);
        StoreDetails shop20 = new StoreDetails("Hatch End", 310);
        StoreDetails shop21 = new StoreDetails("Hounslow",312);
        StoreDetails shop22 = new StoreDetails("Mitcham",314);
        StoreDetails shop23 = new StoreDetails("Palmers Green", 316);
        StoreDetails shop24 = new StoreDetails("Peckham",317);
        StoreDetails shop25 = new StoreDetails("Petts Wood", 318);
        StoreDetails shop26 = new StoreDetails("Queensbury", 319);
        StoreDetails shop27 = new StoreDetails("Sidcup",321);
        StoreDetails shop28 = new StoreDetails("Southwark", 322);
        StoreDetails shop29 = new StoreDetails("Stamford Hill", 323);
        StoreDetails shop30 = new StoreDetails("Stratford",324);
        StoreDetails shop31 = new StoreDetails("Streatham",325);
        StoreDetails shop32 = new StoreDetails("Thamesmead", 326);
        StoreDetails shop33 = new StoreDetails("Twickenham, Morrisons M Local",327);
        StoreDetails shop34 = new StoreDetails("Welling", 328);
        StoreDetails shop35 = new StoreDetails("Wimbledon", 329);
        StoreDetails shop36 = new StoreDetails("Wood Green",330);
        StoreDetails shop37 = new StoreDetails("Wood Green, Morrisons M Local",331);
        StoreDetails shop38 = new StoreDetails("Yiewsley", 332);
        
        /*
        StoreDetails shop1 = new StoreDetails("Bradford - Thornbury",704);
        StoreDetails shop2 = new StoreDetails("Bradford - Westgate", 703);
        StoreDetails shop3 = new StoreDetails("Bradford - Mayo Avenue",701);
        StoreDetails shop4 = new StoreDetails("Bradford - Enterprise 5", 700);
        StoreDetails shop5 = new StoreDetails("Bradford - Victoria", 702);
        StoreDetails shop6 = new StoreDetails("Acomb, York",693);
        StoreDetails shop7 = new StoreDetails("Anlaby, Hull",694);
        StoreDetails shop8 = new StoreDetails("Armthorpe, Doncaster", 695);
        StoreDetails shop9 = new StoreDetails("Balby, Doncaster",696);
        StoreDetails shop10 = new StoreDetails("Barnsley", 697);
        StoreDetails shop11 = new StoreDetails("Beverley", 698);
        StoreDetails shop12 = new StoreDetails("Boroughbridge",699);
        StoreDetails shop13 = new StoreDetails("Brampton", 705);
        StoreDetails shop14 = new StoreDetails("Bransholme", 706);
        StoreDetails shop15 = new StoreDetails("Bridlington",707);
        StoreDetails shop16 = new StoreDetails("Brough",708);
        StoreDetails shop17 = new StoreDetails("Castleford", 709);
        StoreDetails shop18 = new StoreDetails("Catcliffe",710);
        StoreDetails shop19 = new StoreDetails("Doncaster", 711);
        StoreDetails shop20 = new StoreDetails("Doncaster, Morrisons M Local", 712);
        StoreDetails shop21 = new StoreDetails("Ecclesfield",714);
        StoreDetails shop22 = new StoreDetails("Elland",715);
        StoreDetails shop23 = new StoreDetails("Goole", 716);
        StoreDetails shop24 = new StoreDetails("Halifax",717);
        StoreDetails shop25 = new StoreDetails("Harrogate Starbeck", 718);
        StoreDetails shop26 = new StoreDetails("Heckmondwike", 719);
        StoreDetails shop27 = new StoreDetails("Huddersfield",720);
        StoreDetails shop28 = new StoreDetails("Hull", 721);
        StoreDetails shop29 = new StoreDetails("Ilkley, Morrisons M Local", 722);
        StoreDetails shop30 = new StoreDetails("Keighley",724);
        StoreDetails shop31 = new StoreDetails("Knottingley",725);
        StoreDetails shop32 = new StoreDetails("Leeds - Guiseley", 726);
        StoreDetails shop33 = new StoreDetails("Leeds - Harehills",727);
        StoreDetails shop34 = new StoreDetails("Leeds - Horsforth", 728);
        StoreDetails shop35 = new StoreDetails("Leeds - Kirkstall", 729);
        StoreDetails shop36 = new StoreDetails("Leeds - Merrion Centre",730);
        StoreDetails shop37 = new StoreDetails("Leeds - Morley",731);
        StoreDetails shop38 = new StoreDetails("Leeds - Rothwell", 732);
        StoreDetails shop39 = new StoreDetails("Leeds - Swinnow Road",733);
        StoreDetails shop40 = new StoreDetails("Leeds - Hunslet", 734);
        StoreDetails shop41 = new StoreDetails("Leeds - Yeadon", 735);
        StoreDetails shop42 = new StoreDetails("Malton",736);
        StoreDetails shop43 = new StoreDetails("Meltham", 737);
        StoreDetails shop44 = new StoreDetails("Northallerton", 738);
        StoreDetails shop45 = new StoreDetails("Oakwood, Morrisons M Local",739);
        StoreDetails shop46 = new StoreDetails("Pontefract",740);
        StoreDetails shop47 = new StoreDetails("Ravensthorpe", 741);
        StoreDetails shop48 = new StoreDetails("Ripon - Harrogate Road",742);
        StoreDetails shop49 = new StoreDetails("Rotherham", 743);
        StoreDetails shop50 = new StoreDetails("Rotherham - Parkgate", 744);
        StoreDetails shop51 = new StoreDetails("Scarborough",745);
        StoreDetails shop52 = new StoreDetails("Scarborough, Morrisons M Local",746);
        StoreDetails shop53 = new StoreDetails("Scunthorpe", 747);
        StoreDetails shop54 = new StoreDetails("Selby",748);
        StoreDetails shop55 = new StoreDetails("Sheffield - Halfway", 749);
        StoreDetails shop56 = new StoreDetails("Sheffield - Hillsborough", 750);
        StoreDetails shop57 = new StoreDetails("Sheffield - Meadowhead",751);
        StoreDetails shop58 = new StoreDetails("Sheffield - Abbeydale Road", 752);
        StoreDetails shop59 = new StoreDetails("Skipton", 754);
        StoreDetails shop60 = new StoreDetails("Wakefield - Dewsbury Road",755);
        StoreDetails shop61 = new StoreDetails("Wakefield - Ridings Centre", 756);
        StoreDetails shop62 = new StoreDetails("Wetherby", 757);
        StoreDetails shop63 = new StoreDetails("York",758);
        */

        basket.add(shop1);
        basket.add(shop2);
        basket.add(shop3);
        basket.add(shop4);
        basket.add(shop5);
        basket.add(shop6);
        basket.add(shop7);
        basket.add(shop8);
        basket.add(shop9);
        basket.add(shop10);
        basket.add(shop11);
        basket.add(shop12);
        basket.add(shop13);
        basket.add(shop14);
        basket.add(shop15);
        basket.add(shop16);
        basket.add(shop17);
        basket.add(shop18);
        basket.add(shop19);
        basket.add(shop20);
        basket.add(shop21);
        basket.add(shop22);
        basket.add(shop23);
        basket.add(shop24);
        basket.add(shop25);
        basket.add(shop26);
        basket.add(shop27);
        basket.add(shop28);
        basket.add(shop29);
        basket.add(shop30);
        basket.add(shop31);
        basket.add(shop32);
        basket.add(shop33);
        basket.add(shop34);
        basket.add(shop35);
        basket.add(shop36);
        basket.add(shop37);
        basket.add(shop38);
        /*basket.add(shop39);
        basket.add(shop40);
        basket.add(shop41);
        basket.add(shop42);
        basket.add(shop43);
        basket.add(shop44);
        basket.add(shop45);
        basket.add(shop46);
        basket.add(shop47);
        basket.add(shop48);
        basket.add(shop49);
        basket.add(shop50);
        basket.add(shop51);
        basket.add(shop52);
        basket.add(shop53);
        basket.add(shop54);
        basket.add(shop55);
        basket.add(shop56);
        basket.add(shop57);
        basket.add(shop58);
        basket.add(shop59);
        basket.add(shop60);
        basket.add(shop61);
        basket.add(shop62);
        basket.add(shop63);*/
        
        
        lazy=new StoreAdapter(basket, this);           
        list.setAdapter(lazy);   
	
       try {
		String login =  new LogIn().execute().get();
		System.out.println("login: " + login);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

        
        
        
        list.setOnItemClickListener(new OnItemClickListener() {
        	
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                    long id) {
               
            	 StoreDetails listItem = (StoreDetails) lazy.getItem(position);
            	 
            	 
            	 System.out.println("name " + listItem.getTitle());
              	 shopID = listItem.getId();
            	 
            	 if(option==5){
            		
            		 //send order to store
            		 
            		 Intent in = new Intent(StoreList.this, PickUpConfirmation.class);
         			startActivity(in);   
       		 
            	 }else{
            	 
            	 
          
            	 
            	 //create JSON object
            	 JSONObject myObj = createJSON(shopID);
            	 System.out.println(myObj.toString());
            	 
            	 
            	 //send object to server
            	 
            //	JSONObject offers =  sendJSON(myObj);
            	 
            	try {
					newShopList = new SendJSON().execute(myObj).get();
					System.out.println(newShopList.size());
					
					points = (newShopList.size()*10);//no. of items multiplied by 10
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            	
            	Intent in = new Intent(StoreList.this, ShoppingList.class);
    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
    			for(int i=0; i<newShopList.size(); i++){
    				sendList.add(newShopList.get(i));
    			}
    			
    			in.putExtra("option", 4);
    			in.putExtra("points", points);
    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
    			startActivity(in);   
               
            }
            
            
            } 

        });
        
        
        shoppingList.setOnClickListener(new OnClickListener() 
        {
    		@Override
    		public void onClick(View v)
    		{	
    			Intent in = new Intent(StoreList.this, ShoppingList.class);
    			ArrayList<StoreItem> sendList = new ArrayList <StoreItem>();
    			for(int i=0; i<shopList.size(); i++){
    				sendList.add(shopList.get(i));
    			}
    			
    			in.putExtra("option", 3);
    			in.putExtra("points", points);
    		    in.putParcelableArrayListExtra("list", sendList);//send array of storeitems
    			startActivity(in);    			
    		}
     });
        
        
        
        
        
        
        
        

	}
	
	
	
	
	
	

	
	
	
	
	
	
	private String convertStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    try {
	        while ((line = rd.readLine()) != null) {
	            total.append(line);
	        }
	    } catch (Exception e) {
	  //  Toast.makeText(this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    }
	return total.toString();
	}
	
	
	
	   private JSONObject createJSON(int shopID){
   		//shopList
       	
       	JSONObject parentData = new JSONObject();//shop id & items
       	JSONObject childData = new JSONObject();//items
       	JSONObject itemDetails  = new JSONObject();//quantity
       	
       	try {
       		parentData.put("shop", shopID);
       		
       		for(int i = 0; i<shopList.size(); i++)//items
       		{
       	
       		int id = shopList.get(i).getId();
       		intString = (id+"");
       	
       		int quantity = shopList.get(i).getQuantity();
       		itemDetails.put("quantity", quantity);
       		
       		childData.put(intString, itemDetails);
       	}
       		parentData.put("items", childData);
       	   

       	} catch (JSONException e) {
       	    e.printStackTrace();
       	}
       	
       		return parentData;
   		
       }
	   
	   
	   @Override
		public boolean onOptionsItemSelected(MenuItem item) {
		    switch (item.getItemId()) {

		    case android.R.id.home:
		    	
		         // Do whatever you want, e.g. finish()
		    	Intent intent = new Intent(this, HomePage.class);
		        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		        startActivity(intent);
		    	
		    	return true;
		    	
		    default:
		        return super.onOptionsItemSelected(item);	
		   
		    }
		}

}


	
	
	
	
	
	
