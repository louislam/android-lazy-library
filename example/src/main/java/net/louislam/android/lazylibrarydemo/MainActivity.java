package net.louislam.android.lazylibrarydemo;

import net.louislam.android.L;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Lazy Logging (Read in LogCat)
		L.log("Hi.");
		
		// Disable Log globally
		L.enableLog(false);
		L.log("You can't see me. haha.");
		
		// Alert Dialog
		L.alert(this, "I am Alert.");
		
		// Alert Dialog with callback
		L.alert(this, "Boss: Where is Louis!?", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				L.alert(MainActivity.this, "Louis: here :( ");
			}
			
		});
		
		L.confirmDialog(this, "Are you sure?", new OnClickListener () {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do something here if clicked "Yes"	
			}		
		});
		
		ProgressDialog loading = L.progressDialog(this, "Loading...");
		loading.dismiss();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
