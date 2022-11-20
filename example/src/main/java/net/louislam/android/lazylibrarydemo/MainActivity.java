package net.louislam.android.lazylibrarydemo;

import net.louislam.android.L;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Lazy Logging (Read in LogCat)
		L.log("Hi.");
		
		// Disable Log globally
		L.enableLog(false);
		L.log("You can't see me. haha.");
		L.enableLog(true);
		
		// Alert Dialog
		L.alert(this, "I am Alert.");
		
		// Alert Dialog with callback
		L.alert(this, "Boss: Where is Louis!?", (dialog, which) -> {
			L.alert(MainActivity.this, "Louis: here :( ");
		});
		
		L.confirmDialog(this, "Are you sure?", (dialog, which) -> {
			// Do something here if clicked "Yes"
		});
		
		ProgressDialog loading = L.progressDialog(this, "Loading...");
		loading.dismiss();

		// HTTP GET
		new Thread(() -> {
			try {
				Response res = L.get("https://uptime.kuma.pet/github-public-sponsors.json");
				L.log("Response:");
				L.log(res.body().string());
				res.close();
			} catch (IOException ex) {
				L.log(ex.getMessage());
			}
		}).start();


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
