package com.bluemetrix.storeapp;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlayVideo extends Activity{

	private WebView webView;	
	
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_layout);
 
		webView = (WebView) findViewById(R.id.webView1);
		webView.setWebChromeClient(new WebChromeClient());
		webView.getSettings().setPluginState(PluginState.ON);
		webView.setWebViewClient(new WebViewClient()); 
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setBackgroundColor(0x00000000);
		webView.setKeepScreenOn(true);
		webView.setHorizontalScrollBarEnabled(false);
		webView.setVerticalScrollBarEnabled(false);
		webView.getSettings().setBuiltInZoomControls(true);
		

			final String mimeType = "text/html";
			final String encoding = "UTF-8";
			String html = getHTML();

			webView.loadDataWithBaseURL("", html, mimeType, encoding, "");

	}
	
	public String getHTML()
	{

	String html = "<html>"

	    + "<head>"
	 + "</head>"
	 + "<body style=\"border: 0; padding: 0\">"
	 + "<iframe width=\"100%\" height=\"95%\" src=\"https://www.youtube.com/embed/lvL2WJ6kdpY?list=PL2ED488F2AD9C88EF\" frameborder=\"0\" allowfullscreen></iframe>"
	    + "</body>"
	    + "</html>";

	 return html;
	}
	
//	http://www.youtube.com/watch?v=R4lP9OtnraI&feature=share&list=UU7Ffg-Ge4fm37jJRSbcckeA
	
}