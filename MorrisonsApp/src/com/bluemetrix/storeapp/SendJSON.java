package com.bluemetrix.storeapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Iterator;

import android.os.AsyncTask;
import android.util.Log;

public class SendJSON extends AsyncTask<JSONObject, Void, ArrayList<StoreItem>>{

	private static final String TAG_SHOPID = "shop";
	private static final String TAG_ITEMS = "items";
	private static final String TAG_QUANTITY = "quantity";
	private static final String TAG_OFFER = "offerItemOffer";
	static String json = "";
	JSONObject recvdjson;

	ArrayList<StoreItem> myList;


	String wurl = "http://84.39.235.135:45927/offers/get/";//url server




	@Override
	protected ArrayList<StoreItem> doInBackground(JSONObject... level) 
	{



		myList = new ArrayList<StoreItem>();


		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppostreq = new HttpPost(wurl);
		StringEntity se;

		System.out.println("send JSON");

		JSONObject myJSON = level[0];

		try {
			se = new StringEntity("data=" + myJSON.toString());
			System.out.println(myJSON.toString());

			//	se.setContentType("application/json;charset=UTF-8");
			//	se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
			httppostreq.setEntity(se);
			httppostreq.setHeader("Accept", "application/json");
			httppostreq.setHeader("Content-type", "application/json");





			HttpResponse httpresponse = StoreList.httpClient.execute(httppostreq);
			System.out.println("status " + httpresponse.getStatusLine().getStatusCode());
			//code 200 - OK!




			HttpEntity resultentity = httpresponse.getEntity();
			InputStream inputstream = resultentity.getContent();
			Header contentencoding = httpresponse.getFirstHeader("Content-Encoding");
			if(contentencoding != null && contentencoding.getValue().equalsIgnoreCase("gzip")) {
				inputstream = new GZIPInputStream(inputstream);
			}
			String resultstring = convertStreamToString(inputstream);
			inputstream.close();


			System.out.println("result " + resultstring);





			// try parse the string to a JSON object
			try 
			{
				recvdjson = new JSONObject(resultstring);
				// recvdjson = new JSONObject(json);

				System.out.println("back " + recvdjson.toString());


				String shopID = recvdjson.getString("shop");
				JSONObject items = recvdjson.getJSONObject("items");
				System.out.println("shopID " + shopID);


				Iterator keys = items.keys();

				while(keys.hasNext()) {
					// loop to get the dynamic key
					String currentDynamicKey = (String)keys.next();

					int id = Integer.parseInt(currentDynamicKey);

					// get the value of the dynamic key
					JSONObject currentDynamicValue = items.getJSONObject(currentDynamicKey);



					if(!currentDynamicValue.has("offerItemOffer")){

						if(id==23){
							StoreItem item1 = new StoreItem(R.drawable.bread_icon, "Brown Bread", id, "");
							myList.add(item1);
						}
						if(id==24){
							StoreItem item1 = new StoreItem(R.drawable.croissant_icon, "Croissant", id, "");
							myList.add(item1);
						}
						if(id==25){
							StoreItem item1 = new StoreItem(R.drawable.roll_icon, "Rolls", id, "");
							myList.add(item1);
						}
						if(id==26){
							StoreItem item1 = new StoreItem(R.drawable.whitepan_icon, "White Bread", id, "");
							myList.add(item1);
						}


						if(id==27){
							StoreItem item1 = new StoreItem(R.drawable.apple, "Apples", id, "");
							myList.add(item1);
						}
						if(id==28){
							StoreItem item1 = new StoreItem(R.drawable.banana, "Bananas", id, "");
							myList.add(item1);
						}
						if(id==29){
							StoreItem item1 = new StoreItem(R.drawable.blueberries, "Blueberries", id, "");
							myList.add(item1);
						}
						if(id==30){
							StoreItem item1 = new StoreItem(R.drawable.melon, "Melon", id, "");
							myList.add(item1);
						}
						if(id==31){
							StoreItem item1 = new StoreItem(R.drawable.orange, "Orange", id, "");
							myList.add(item1);
						}
						if(id==32){
							StoreItem item1 = new StoreItem(R.drawable.strawberry, "Strawberries", id, "");
							myList.add(item1);
						}



						if(id==33){
							StoreItem item1 = new StoreItem(R.drawable.broccoli, "Broccoli", id, "");
							myList.add(item1);
						}
						if(id==34){
							StoreItem item1 = new StoreItem(R.drawable.cabbage, "Cabbage", id, "");
							myList.add(item1);
						}
						if(id==35){
							StoreItem item1 = new StoreItem(R.drawable.carrots, "Carrots", id, "");
							myList.add(item1);
						}
						if(id==36){
							StoreItem item1 = new StoreItem(R.drawable.cucumber, "Cucumber", id, "");
							myList.add(item1);
						}
						if(id==37){
							StoreItem item1 = new StoreItem(R.drawable.greenbeans, "Green Beans", id, "");
							myList.add(item1);
						}
						if(id==38){
							StoreItem item1 = new StoreItem(R.drawable.lettuce, "Lettuce", id, "");
							myList.add(item1);
						}
						if(id==39){
							StoreItem item1 = new StoreItem(R.drawable.mushrooms, "Mushrooms", id, "");
							myList.add(item1);
						}
						if(id==40){
							StoreItem item1 = new StoreItem(R.drawable.onion, "Onions", id, "");
							myList.add(item1);
						}
						if(id==41){
							StoreItem item1 = new StoreItem(R.drawable.potatoe, "Potatoes", id, "");
							myList.add(item1);
						}
						if(id==42){
							StoreItem item1 = new StoreItem(R.drawable.spinnach, "Spinach", id, "");
							myList.add(item1);
						}
						if(id==43){
							StoreItem item1 = new StoreItem(R.drawable.tomatoe, "Tomatoes", id, "");
							myList.add(item1);
						}


						if(id==44){
							StoreItem item1 = new StoreItem(R.drawable.bacon, "Bacon", id, "");
							myList.add(item1);
						}
						if(id==45){
							StoreItem item1 = new StoreItem(R.drawable.chicken, "Chicken", id, "");
							myList.add(item1);
						}
						if(id==46){
							StoreItem item1 = new StoreItem(R.drawable.ham, "Ham", id, "");
							myList.add(item1);
						}
						if(id==47){
							StoreItem item1 = new StoreItem(R.drawable.lambchops, "Lamb Chops", id, "");
							myList.add(item1);
						}
						if(id==48){
							StoreItem item1 = new StoreItem(R.drawable.porkchops, "Pork", id, "");
							myList.add(item1);
						}
						if(id==49){
							StoreItem item1 = new StoreItem(R.drawable.roastbeef, "Roast Beef", id, "");
							myList.add(item1);
						}
						if(id==50){
							StoreItem item1 = new StoreItem(R.drawable.sausages, "Sausages", id, "");
							myList.add(item1);
						}
						if(id==51){
							StoreItem item1 = new StoreItem(R.drawable.steak, "Steak", id, "");
							myList.add(item1);
						}


						if(id==52){
							StoreItem item1 = new StoreItem(R.drawable.cod, "Cod", id, "");
							myList.add(item1);
						}
						if(id==53){
							StoreItem item1 = new StoreItem(R.drawable.haddock, "Haddock", id, "");
							myList.add(item1);
						}
						if(id==54){
							StoreItem item1 = new StoreItem(R.drawable.mackeral, "Mackerel", id, "");
							myList.add(item1);
						}
						if(id==55){
							StoreItem item1 = new StoreItem(R.drawable.salmon, "Salmon", id, "");
							myList.add(item1);
						}


						if(id==56){
							StoreItem item1 = new StoreItem(R.drawable.butter, "Butter", id, "");
							myList.add(item1);
						}
						if(id==57){
							StoreItem item1 = new StoreItem(R.drawable.cheese, "Cheese", id, "");
							myList.add(item1);
						}
						if(id==58){
							StoreItem item1 = new StoreItem(R.drawable.icecream, "Ice Cream", id, "");
							myList.add(item1);
						}
						if(id==59){
							StoreItem item1 = new StoreItem(R.drawable.milk, "Milk", id, "");
							myList.add(item1);
						}
						if(id==60){
							StoreItem item1 = new StoreItem(R.drawable.yougurt, "Yoghurt", id, "");
							myList.add(item1);
						}


						if(id==61){
							StoreItem item1 = new StoreItem(R.drawable.curry, "Curry", id, "");
							myList.add(item1);
						}
						if(id==62){
							StoreItem item1 = new StoreItem(R.drawable.jam, "Jam", id, "");
							myList.add(item1);
						}
						if(id==63){
							StoreItem item1 = new StoreItem(R.drawable.pepper, "Pepper", id, "");
							myList.add(item1);
						}
						if(id==64){
							StoreItem item1 = new StoreItem(R.drawable.soup, "Soup", id, "");
							myList.add(item1);
						}
						if(id==65){
							StoreItem item1 = new StoreItem(R.drawable.sugar, "Sugar", id, "");
							myList.add(item1);
						}
						if(id==66){
							StoreItem item1 = new StoreItem(R.drawable.salt, "Salt", id, "");
							myList.add(item1);
						}
						if(id==68){
							StoreItem item1 = new StoreItem(R.drawable.flour, "Flour", id, "");
							myList.add(item1);
						}


						if(id==69){
							StoreItem item1 = new StoreItem(R.drawable.coffee, "Coffee", id, "");
							myList.add(item1);
						}
						if(id==70){
							StoreItem item1 = new StoreItem(R.drawable.cola, "Cola", id, "");
							myList.add(item1);
						}
						if(id==71){
							StoreItem item1 = new StoreItem(R.drawable.fruitjuice, "Juice", id, "");
							myList.add(item1);
						}
						if(id==72){
							StoreItem item1 = new StoreItem(R.drawable.orangejuice, "Orange Juice", id, "");
							myList.add(item1);
						}
						if(id==73){
							StoreItem item1 = new StoreItem(R.drawable.tea, "Tea", id, "");
							myList.add(item1);
						}
						if(id==74){
							StoreItem item1 = new StoreItem(R.drawable.water, "Water", id, "");
							myList.add(item1);
						}


						if(id==75){
							StoreItem item1 = new StoreItem(R.drawable.beer, "Beer", id, "");
							myList.add(item1);
						}
						if(id==76){
							StoreItem item1 = new StoreItem(R.drawable.cider, "Cider", id, "");
							myList.add(item1);
						}
						if(id==77){
							StoreItem item1 = new StoreItem(R.drawable.cocktail, "Cocktail", id, "");
							myList.add(item1);
						}
						if(id==78){
							StoreItem item1 = new StoreItem(R.drawable.whiskey, "Whiskey", id, "");
							myList.add(item1);
						}
						if(id==79){
							StoreItem item1 = new StoreItem(R.drawable.wine, "Wine", id, "");
							myList.add(item1);
						}


						if(id==80){
							StoreItem item1 = new StoreItem(R.drawable.pastasauce, "Pasta Sauce", id, "");
							myList.add(item1);
						}
						if(id==81){
							StoreItem item1 = new StoreItem(R.drawable.ketchup, "Ketchup", id, "");
							myList.add(item1);
						}
						if(id==82){
							StoreItem item1 = new StoreItem(R.drawable.mayo, "Mayonnaise", id, "");
							myList.add(item1);
						}
						if(id==83){
							StoreItem item1 = new StoreItem(R.drawable.relish, "Relish", id, "");
							myList.add(item1);
						}


						if(id==84){
							StoreItem item1 = new StoreItem(R.drawable.pasta, "Pasta", id, "");
							myList.add(item1);
						}
						if(id==85){
							StoreItem item1 = new StoreItem(R.drawable.noodles, "Noodles", id, "");
							myList.add(item1);
						}
						if(id==86){
							StoreItem item1 = new StoreItem(R.drawable.rice, "Rice", id, "");
							myList.add(item1);
						}


						if(id==87){
							StoreItem item1 = new StoreItem(R.drawable.chocolate, "Chocolate", id, "");
							myList.add(item1);
						}
						if(id==88){
							StoreItem item1 = new StoreItem(R.drawable.crisps, "Crisps", id, "");
							myList.add(item1);
						}
						if(id==89){
							StoreItem item1 = new StoreItem(R.drawable.popcorn, "Popcorn", id, "");
							myList.add(item1);
						}
						if(id==90){
							StoreItem item1 = new StoreItem(R.drawable.nachoes, "Nachos", id, "");
							myList.add(item1);
						}
						if(id==91){
							StoreItem item1 = new StoreItem(R.drawable.sweeties, "Sweets", id, "");
							myList.add(item1);
						}
						if(id==92){
							StoreItem item1 = new StoreItem(R.drawable.jellies, "Jellies", id, "");
							myList.add(item1);
						}







					}else{

						String offer = currentDynamicValue.getString("offerItemOffer");
						// do something here with the value...
						System.out.println("offer: " + offer);


						if(id==23){
							StoreItem item1 = new StoreItem(R.drawable.bread_icon, "Brown Bread", id, offer);
							myList.add(item1);
						}
						if(id==24){
							StoreItem item1 = new StoreItem(R.drawable.croissant_icon, "Croissant", id, offer);
							myList.add(item1);
						}
						if(id==25){
							StoreItem item1 = new StoreItem(R.drawable.roll_icon, "Rolls", id, offer);
							myList.add(item1);
						}
						if(id==26){
							StoreItem item1 = new StoreItem(R.drawable.whitepan_icon, "White Bread", id, offer);
							myList.add(item1);
						}


						if(id==27){
							StoreItem item1 = new StoreItem(R.drawable.apple, "Apples", id, offer);
							myList.add(item1);
						}
						if(id==28){
							StoreItem item1 = new StoreItem(R.drawable.banana, "Bananas", id, offer);
							myList.add(item1);
						}
						if(id==29){
							StoreItem item1 = new StoreItem(R.drawable.blueberries, "Blueberries", id, offer);
							myList.add(item1);
						}
						if(id==30){
							StoreItem item1 = new StoreItem(R.drawable.melon, "Melon", id, offer);
							myList.add(item1);
						}
						if(id==31){
							StoreItem item1 = new StoreItem(R.drawable.orange, "Orange", id, offer);
							myList.add(item1);
						}
						if(id==32){
							StoreItem item1 = new StoreItem(R.drawable.strawberry, "Strawberries", id, offer);
							myList.add(item1);
						}



						if(id==33){
							StoreItem item1 = new StoreItem(R.drawable.broccoli, "Broccoli", id, offer);
							myList.add(item1);
						}
						if(id==34){
							StoreItem item1 = new StoreItem(R.drawable.cabbage, "Cabbage", id, offer);
							myList.add(item1);
						}
						if(id==35){
							StoreItem item1 = new StoreItem(R.drawable.carrots, "Carrots", id, offer);
							myList.add(item1);
						}
						if(id==36){
							StoreItem item1 = new StoreItem(R.drawable.cucumber, "Cucumber", id, offer);
							myList.add(item1);
						}
						if(id==37){
							StoreItem item1 = new StoreItem(R.drawable.greenbeans, "Green Beans", id, offer);
							myList.add(item1);
						}
						if(id==38){
							StoreItem item1 = new StoreItem(R.drawable.lettuce, "Lettuce", id, offer);
							myList.add(item1);
						}
						if(id==39){
							StoreItem item1 = new StoreItem(R.drawable.mushrooms, "Mushrooms", id, offer);
							myList.add(item1);
						}
						if(id==40){
							StoreItem item1 = new StoreItem(R.drawable.onion, "Onions", id, offer);
							myList.add(item1);
						}
						if(id==41){
							StoreItem item1 = new StoreItem(R.drawable.potatoe, "Potatoes", id, offer);
							myList.add(item1);
						}
						if(id==42){
							StoreItem item1 = new StoreItem(R.drawable.spinnach, "Spinach", id, offer);
							myList.add(item1);
						}
						if(id==43){
							StoreItem item1 = new StoreItem(R.drawable.tomatoe, "Tomatoes", id, offer);
							myList.add(item1);
						}


						if(id==44){
							StoreItem item1 = new StoreItem(R.drawable.bacon, "Bacon", id, offer);
							myList.add(item1);
						}
						if(id==45){
							StoreItem item1 = new StoreItem(R.drawable.chicken, "Chicken", id, offer);
							myList.add(item1);
						}
						if(id==46){
							StoreItem item1 = new StoreItem(R.drawable.ham, "Ham", id, offer);
							myList.add(item1);
						}
						if(id==47){
							StoreItem item1 = new StoreItem(R.drawable.lambchops, "Lamb Chops", id, offer);
							myList.add(item1);
						}
						if(id==48){
							StoreItem item1 = new StoreItem(R.drawable.porkchops, "Pork", id, offer);
							myList.add(item1);
						}
						if(id==49){
							StoreItem item1 = new StoreItem(R.drawable.roastbeef, "Roast Beef", id, offer);
							myList.add(item1);
						}
						if(id==50){
							StoreItem item1 = new StoreItem(R.drawable.sausages, "Sausages", id, offer);
							myList.add(item1);
						}
						if(id==51){
							StoreItem item1 = new StoreItem(R.drawable.steak, "Steak", id, offer);
							myList.add(item1);
						}


						if(id==52){
							StoreItem item1 = new StoreItem(R.drawable.cod, "Cod", id, offer);
							myList.add(item1);
						}
						if(id==53){
							StoreItem item1 = new StoreItem(R.drawable.haddock, "Haddock", id, offer);
							myList.add(item1);
						}
						if(id==54){
							StoreItem item1 = new StoreItem(R.drawable.mackeral, "Mackerel", id, offer);
							myList.add(item1);
						}
						if(id==55){
							StoreItem item1 = new StoreItem(R.drawable.salmon, "Salmon", id, offer);
							myList.add(item1);
						}


						if(id==56){
							StoreItem item1 = new StoreItem(R.drawable.butter, "Butter", id, offer);
							myList.add(item1);
						}
						if(id==57){
							StoreItem item1 = new StoreItem(R.drawable.cheese, "Cheese", id, offer);
							myList.add(item1);
						}
						if(id==58){
							StoreItem item1 = new StoreItem(R.drawable.icecream, "Ice Cream", id, offer);
							myList.add(item1);
						}
						if(id==59){
							StoreItem item1 = new StoreItem(R.drawable.milk, "Milk", id, offer);
							myList.add(item1);
						}
						if(id==60){
							StoreItem item1 = new StoreItem(R.drawable.yougurt, "Yoghurt", id, offer);
							myList.add(item1);
						}


						if(id==61){
							StoreItem item1 = new StoreItem(R.drawable.curry, "Curry", id, offer);
							myList.add(item1);
						}
						if(id==62){
							StoreItem item1 = new StoreItem(R.drawable.jam, "Jam", id, offer);
							myList.add(item1);
						}
						if(id==63){
							StoreItem item1 = new StoreItem(R.drawable.pepper, "Pepper", id, offer);
							myList.add(item1);
						}
						if(id==64){
							StoreItem item1 = new StoreItem(R.drawable.soup, "Soup", id, offer);
							myList.add(item1);
						}
						if(id==65){
							StoreItem item1 = new StoreItem(R.drawable.sugar, "Sugar", id, offer);
							myList.add(item1);
						}
						if(id==66){
							StoreItem item1 = new StoreItem(R.drawable.salt, "Salt", id, offer);
							myList.add(item1);
						}
						if(id==68){
							StoreItem item1 = new StoreItem(R.drawable.flour, "Flour", id, offer);
							myList.add(item1);
						}


						if(id==69){
							StoreItem item1 = new StoreItem(R.drawable.coffee, "Coffee", id, offer);
							myList.add(item1);
						}
						if(id==70){
							StoreItem item1 = new StoreItem(R.drawable.cola, "Cola", id, offer);
							myList.add(item1);
						}
						if(id==71){
							StoreItem item1 = new StoreItem(R.drawable.fruitjuice, "Juice", id, offer);
							myList.add(item1);
						}
						if(id==72){
							StoreItem item1 = new StoreItem(R.drawable.orangejuice, "Orange Juice", id, offer);
							myList.add(item1);
						}
						if(id==73){
							StoreItem item1 = new StoreItem(R.drawable.tea, "Tea", id, offer);
							myList.add(item1);
						}
						if(id==74){
							StoreItem item1 = new StoreItem(R.drawable.water, "Water", id, offer);
							myList.add(item1);
						}


						if(id==75){
							StoreItem item1 = new StoreItem(R.drawable.beer, "Beer", id, offer);
							myList.add(item1);
						}
						if(id==76){
							StoreItem item1 = new StoreItem(R.drawable.cider, "Cider", id, offer);
							myList.add(item1);
						}
						if(id==77){
							StoreItem item1 = new StoreItem(R.drawable.cocktail, "Cocktail", id, offer);
							myList.add(item1);
						}
						if(id==78){
							StoreItem item1 = new StoreItem(R.drawable.whiskey, "Whiskey", id, offer);
							myList.add(item1);
						}
						if(id==79){
							StoreItem item1 = new StoreItem(R.drawable.wine, "Wine", id, offer);
							myList.add(item1);
						}


						if(id==80){
							StoreItem item1 = new StoreItem(R.drawable.pastasauce, "Pasta Sauce", id, offer);
							myList.add(item1);
						}
						if(id==81){
							StoreItem item1 = new StoreItem(R.drawable.ketchup, "Ketchup", id, offer);
							myList.add(item1);
						}
						if(id==82){
							StoreItem item1 = new StoreItem(R.drawable.mayo, "Mayonnaise", id, offer);
							myList.add(item1);
						}
						if(id==83){
							StoreItem item1 = new StoreItem(R.drawable.relish, "Relish", id, offer);
							myList.add(item1);
						}


						if(id==84){
							StoreItem item1 = new StoreItem(R.drawable.pasta, "Pasta", id, offer);
							myList.add(item1);
						}
						if(id==85){
							StoreItem item1 = new StoreItem(R.drawable.noodles, "Noodles", id, offer);
							myList.add(item1);
						}
						if(id==86){
							StoreItem item1 = new StoreItem(R.drawable.rice, "Rice", id, offer);
							myList.add(item1);
						}


						if(id==87){
							StoreItem item1 = new StoreItem(R.drawable.chocolate, "Chocolate", id, offer);
							myList.add(item1);
						}
						if(id==88){
							StoreItem item1 = new StoreItem(R.drawable.crisps, "Crisps", id, offer);
							myList.add(item1);
						}
						if(id==89){
							StoreItem item1 = new StoreItem(R.drawable.popcorn, "Popcorn", id, offer);
							myList.add(item1);
						}
						if(id==90){
							StoreItem item1 = new StoreItem(R.drawable.nachoes, "Nachos", id, offer);
							myList.add(item1);
						}
						if(id==91){
							StoreItem item1 = new StoreItem(R.drawable.sweeties, "Sweets", id, offer);
							myList.add(item1);
						}
						if(id==92){
							StoreItem item1 = new StoreItem(R.drawable.jellies, "Jellies", id, offer);
							myList.add(item1);
						}

					}





				} }

			catch (JSONException e) 
			{
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}}

		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("no items " + myList.size());


		return myList;

	}







	protected void onPostExecute(ArrayList<StoreItem> result) 
	{
		myList = result; 
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





}


