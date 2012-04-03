package com.lightcone.webviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class WebViewDemo extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     // Add Click listeners for all buttons
        View firstButton = findViewById(R.id.button1);
        firstButton.setOnClickListener(this);
        View secondButton = findViewById(R.id.button2);
        secondButton.setOnClickListener(this);
    }

    public void onBackButton(View V)
    {
    	super.finishActivity(RESULT_OK);
    	Intent i = new Intent(Intent.ACTION_MAIN);
		PackageManager manager = getPackageManager();
		// i = manager.getLaunchIntentForPackage("WebViewDemo.apk");
		i = manager
				.getLaunchIntentForPackage("com.marakana");

		i.addCategory(Intent.CATEGORY_LAUNCHER);
		// i.addFlags(flags)
		//ithread = new Thread(new Runnable() {
		//	public void run() {
		//		onPause();
				startActivity(i);
    }
    // Process the button click events
    public void onPause(View V){
    	//webView.onPause();
    	super.finish();
    
    }
    public void onResume(View V){
    	//webView.onPause();
    	super.onResume();
    }
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.button1:
				Intent j = new Intent(this, Webscreen.class);
    			j.putExtra(com.lightcone.webviewdemo.Webscreen.URL, 
    					"http://192.168.43.218/my/hacku/events.php?var=hacku_csDept");
    			startActivity(j);
			break;
			
			case R.id.button2:
				Intent k = new Intent(this, Webscreen.class);
    			k.putExtra(com.lightcone.webviewdemo.Webscreen.URL, 
    					"http://192.168.43.218/my/hacku/people.php?var=hacku_csDept");
    			startActivity(k);
			break;
			
		}		
	}
}