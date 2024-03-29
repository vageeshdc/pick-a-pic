package com.marakana;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

public class CameraActivity extends Activity {
	private static final String TAG = "CameraDemo";
	Preview preview; // <1>
	Button buttonClick; // <2>
	public Thread ithread = null;
	Intent i;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		preview = new Preview(this); // <3>
		((FrameLayout) findViewById(R.id.preview)).addView(preview); // <4>

		buttonClick = (Button) findViewById(R.id.buttonClick);
		buttonClick.setOnClickListener(new OnClickListener() {
			public void onClick(View v) { // <5>

				preview.camera.takePicture(shutterCallback, rawCallback,
						jpegCallback);
			}
		});

		Log.d(TAG, "onCreate'd");
	}

	// Called when shutter is opened
	ShutterCallback shutterCallback = new ShutterCallback() { // <6>
		public void onShutter() {
			Log.d(TAG, "onShutter'd");
		}
	};

	// Handles data for raw picture
	PictureCallback rawCallback = new PictureCallback() { // <7>
		public void onPictureTaken(byte[] data, Camera camera) {
			Log.d(TAG, "onPictureTaken - raw");
		}
	};

	// Handles data for jpeg picture
	PictureCallback jpegCallback = new PictureCallback() { // <8>
		public void onPictureTaken(byte[] data, Camera camera) {
			FileOutputStream outStream = null;
			try {
				// Write to SD Card
				outStream = new FileOutputStream(
						String.format("/sdcard/a0.jpg")); // <9>
				outStream.write(data);
				outStream.close();
				Log.d(TAG, "onPictureTaken - wrote bytes: " + data.length);
				//Upload upload = new Upload(data);
				// upload.notifyAll();
				// //
				// there is a main instance that is running ..
				i = new Intent(Intent.ACTION_MAIN);
				PackageManager manager = getPackageManager();
				// i = manager.getLaunchIntentForPackage("WebViewDemo.apk");
				i = manager
						.getLaunchIntentForPackage("com.lightcone.webviewdemo");

				i.addCategory(Intent.CATEGORY_LAUNCHER);
				// i.addFlags(flags)
				//ithread = new Thread(new Runnable() {
				//	public void run() {
				//		onPause();
						startActivity(i);

//					}
//				}, "ithread");
//				ithread.start();
//				while (ithread.isAlive()) {

				//}
				onResume();
			} catch (FileNotFoundException e) { 
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
			Log.d(TAG, "onPictureTaken - jpeg");
		}
	};

	/*public void onPause(View V) {
		super.onPause();
	}

	public void onResume(View V) {
		// webView.onPause();
		super.onResume();
	}*/

}
