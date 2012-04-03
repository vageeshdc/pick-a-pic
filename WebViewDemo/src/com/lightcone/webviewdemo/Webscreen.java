package com.lightcone.webviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.util.Log;

public class Webscreen extends Activity {
	
	public static final String URL = "";
	private static final String TAG = "WebscreenClass";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webscreen);
        
        String turl = getIntent().getStringExtra(URL);
        Log.i(TAG, " URL = "+turl);
        
        WebView webview = new WebView(this);
		setContentView(webview);

		// Simplest usage: An exception won't be thrown if there is a page-load error
		webview.loadUrl(turl);
        
    }
}
