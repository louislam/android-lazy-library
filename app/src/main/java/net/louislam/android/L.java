package net.louislam.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.BufferedSink;
import okio.Okio;

/**
 * Louis Lam's Lazy Library for Android.
 * <p/>
 * Class name: L
 * <p/>
 * "L" means Louis or Lazy.
 * there are example usage for the methods, please read methods' comment.
 * <p/>
 * Assumption:
 * In all examples, "this" means Activity/Context object.
 * <p/>
 * The MIT License (MIT)
 * <p/>
 * Copyright (c) 2013 Louis Lam
 * <p/>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p/>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * @author Louis Lam (louis@louislam.net)
 */


public class L {

	/**
	 * Determine the log whether should be displayed
	 */
	private static boolean log = true;

	/**
	 * Lazy start an activity with a string of class name.
	 * <p/>
	 * Example Usage:
	 * L.startActivity(this, "LoginActivity");
	 *
	 * @param context
	 * @param className Activity class name
	 */
	public static void startActivity(Context context, String className) {
		try {
			startActivity(context, Class.forName(className));
		} catch (ClassNotFoundException e) {
			L.log(e.toString());
		}
	}

	/**
	 * Lazy start an activity.
	 * <p/>
	 * Example Usage:
	 * L.startActivity(this, LoginActivity.class);
	 *
	 * @param context
	 * @param c       Class
	 */
	public static void startActivity(Context context, Class<?> c) {
		Intent i = new Intent(context, c);
		context.startActivity(i);
	}

	/**
	 * Lazy start an Activity for result
	 * <p/>
	 * Example Usage:
	 * L.startActivity(this, LoginActivity.class, 1314);
	 *
	 * @param context
	 * @param c
	 * @param requestCode
	 */
	public static void startActivity(Activity context, Class<?> c, int requestCode) {
		startActivity(context, c, requestCode, new Bundle());
	}

	/**
	 * Lazy start Activity for result with bundle
	 * <p/>
	 * Example Usage:
	 * Bundle bundle = new Bundle();
	 * L.startActivity(this, LoginActivity.class, 1314, bundle);
	 *
	 * @param context
	 * @param c
	 * @param requestCode
	 * @param b           Bundle object
	 */
	public static void startActivity(Activity context, Class<?> c, int requestCode, Bundle b) {
		Intent i;
		i = new Intent(context, c);
		i.putExtras(b);
		context.startActivityForResult(i, requestCode);
	}

	/**
	 * Lazy Alert (I love this so much.)
	 * <p/>
	 * Example Usage:
	 * L.alert(this, "I am Louis.");
	 *
	 * @param c   context
	 * @param msg the message
	 */
	public static void alert(Context c, String msg) {
		alert(c, msg, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

				if (dialog != null)
					dialog.dismiss();
			}
		});
	}

	/**
	 * Lazy Alert with callBack method
	 *
	 * @param c
	 * @param msg
	 * @param callBack
	 */
	public static void alert(Context c, String msg, OnClickListener callBack) {
		if (c == null) {
			return;
		}

		AlertDialog.Builder b = new AlertDialog.Builder(c);
		b.setMessage(msg);
		b.setCancelable(false);
		b.setPositiveButton("OK", callBack);
		b.show();
	}

	/**
	 * Lazy Input Dialog
	 * <p/>
	 * Example Usage:
	 * L.inputDialog(this, "What is your name", new InputListener() {
	 * <p/>
	 * public void inputResult(String value) {
	 * String name = value;
	 * L.alert("My name is: " + name);
	 * }
	 * <p/>
	 * });
	 *
	 * @param c
	 * @param msg
	 * @param okListener
	 */
	public static void inputDialog(Context c, String msg, final InputListener okListener) {
		inputDialog(c, msg, okListener, null);
	}

	/**
	 * @param c
	 * @param msg
	 * @param okListener
	 * @param defaultValue
	 */
	public static void inputDialog(Context c, String msg, final InputListener okListener, String defaultValue) {
		AlertDialog.Builder alert = new AlertDialog.Builder(c);

		alert.setTitle("Input Dialog");
		alert.setMessage(msg);

		// Set an EditText view to get user input
		final EditText input = new EditText(c);

		if (defaultValue != null) {
			input.setText(defaultValue);
		}

		alert.setView(input);

		alert.setPositiveButton("OK",
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(
					DialogInterface dialog,
					int whichButton) {
					String value = input.getText()
						.toString();
					okListener.inputResult(value);
				}
			});

		alert.setNegativeButton("Cancel", null);
		alert.show();
	}

	/**
	 * Lazy Confirmation Dialog. For the activity implements OnClickListener
	 * <p/>
	 * Example Usage:
	 * <p/>
	 * public class LoginActivity extends Activity implements OnClickListener {
	 * <p/>
	 * public void quit() {
	 * L.confirmDialog(this, "Are you sure?");
	 * }
	 * <p/>
	 * }
	 *
	 * @param c
	 * @param msg
	 */
	public static AlertDialog.Builder confirmDialog(Context c, String msg) {
		OnClickListener yes = null;

		if (c instanceof OnClickListener) {
			yes = (OnClickListener) c;
		}
		return confirmDialog(c, msg, yes);
	}

	/**
	 * Confirmation Dialog with
	 *
	 * @param c
	 * @param msg
	 * @param yes
	 */
	public static AlertDialog.Builder confirmDialog(Context c, String msg, OnClickListener yes) {
		AlertDialog.Builder alert = new AlertDialog.Builder(c);
		alert.setTitle("Confirmation");
		alert.setMessage(msg);
		alert.setPositiveButton("Yes", yes);
		alert.setNegativeButton("No", null);
		alert.show();
		return alert;
	}

	/**
	 * Progress Dialog
	 *
	 * @param x
	 * @param msg
	 */
	public static ProgressDialog progressDialog(Context x, String msg) {
		ProgressDialog d = new ProgressDialog(x);
		d.setMessage(msg);
		d.setIndeterminate(true);
		d.setCanceledOnTouchOutside(false);
		d.show();
		return d;
	}

	/**
	 * Get Bundle of a activity
	 *
	 * @param c
	 * @return the bundle
	 */
	public static Bundle getBundle(Activity c) {
		return c.getIntent().getExtras();
	}

	/**
	 * Lazy Log
	 * Accept for very long string
	 * Thanks to: http://stackoverflow.com/questions/7606077/how-to-display-long-messages-in-logcat
	 *
	 * @param msg The message.
	 */
	public static void log(String msg) {
		if (!log)
			return;

		final int MAX = 1000;
		int start, end;

		for (int i = 0; i <= msg.length() / MAX; i++) {
			start = i * MAX;
			end = (i + 1) * MAX;
			end = end > msg.length() ? msg.length() : end;
			Log.v("Log", msg.substring(start, end));
		}
	}

	public static void log(int msg) {
		log(msg + "");
	}

	public static void log(int[] array) {
		log(Arrays.toString(array));
	}

	/**
	 * Enable Log
	 *
	 * @param b
	 */
	public static void enableLog(boolean b) {
		log = b;
	}

	/**
	 * Lazy Open Url
	 *
	 * @param a
	 * @param url
	 */
	public static void openUrl(Context a, String url) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		a.startActivity(browserIntent);
	}

	/**
	 * Get Android App Version Name
	 * <p/>
	 * Thanks "plus-"
	 * http://stackoverflow.com/questions/6593592/get-application-version-programatically-in-android
	 *
	 * @param c
	 * @return App version
	 */
	public static String getAppVersion(Context c) {
		try {
			PackageInfo pInfo = c.getPackageManager().getPackageInfo(c.getPackageName(), 0);
			return pInfo.versionName;
		} catch (NameNotFoundException e) {
			return null;
		}
	}

	public static Timer setInterval(TimerCallback callback, int ms) {
		return setInterval(callback, ms, ms);
	}

	public static Timer setInterval(TimerCallback callback, int ms, int delay) {
		var timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				callback.run();
			}
		}, delay, ms);
		return timer;
	}

	public static Timer setTimeout(TimerTask task, int ms) {
		var timer = new Timer();
		timer.schedule(task, ms);
		return timer;
	}

    public static void toast(Context context, String text) {
		toast(context, text, false);
    }

	public static void toast(Context context, String text, boolean longDuration) {
		if (longDuration) {
			Toast.makeText(context, text, Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * https://www.geeksforgeeks.org/alert-dialog-with-multipleitemselection-in-android/
	 */
	public static void multipleChoiceDialog(Context context, String[] listItems, MultipleChoiceCallback callback) {
		final boolean[] checkedItems = new boolean[listItems.length];

		// initialise the alert dialog builder
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		// set the title for the alert dialog
		builder.setTitle(R.string.choose_items);

		// set the icon for the alert dialog
		//builder.setIcon(R.drawable.image_logo);

		// now this is the function which sets the alert dialog for multiple item selection ready
		builder.setMultiChoiceItems(listItems, checkedItems, (dialog, which, isChecked) -> {
			checkedItems[which] = isChecked;
		});

		// alert dialog shouldn't be cancellable
		builder.setCancelable(false);

		// handle the positive button of the dialog
		builder.setPositiveButton(R.string.done, (dialog, which) -> {
			callback.onDone(checkedItems);
		});

		// handle the negative button of the alert dialog
		builder.setNegativeButton("CANCEL", (dialog, which) -> {

		});

		// handle the neutral button of the dialog to clear the selected items boolean checkedItem
		builder.setNeutralButton("CLEAR ALL", (dialog, which) -> {
			Arrays.fill(checkedItems, false);
		});

		// create the builder
		builder.create();

		// create the alert dialog with the alert dialog builder instance
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	/**
	 * https://www.geeksforgeeks.org/alert-dialog-with-multipleitemselection-in-android/
	 */
	public static void singleChoiceDialog(Context context, String[] listItems, int selectedIndex, SingleChoiceCallback callback) {
		var selectedIndexHolder = new int[1];

		// initialise the alert dialog builder
		AlertDialog.Builder builder = new AlertDialog.Builder(context);

		// set the title for the alert dialog
		builder.setTitle(R.string.chooseItem);

		// set the icon for the alert dialog
		//builder.setIcon(R.drawable.image_logo);

		// now this is the function which sets the alert dialog for multiple item selection ready
		builder.setSingleChoiceItems(listItems, selectedIndex, (dialog, which) -> {
			selectedIndexHolder[0] = which;
		});

		// alert dialog shouldn't be cancellable
		builder.setCancelable(false);

		// handle the positive button of the dialog
		builder.setPositiveButton(R.string.done, (dialog, which) -> {
			callback.onDone(selectedIndexHolder[0]);
		});

		// handle the negative button of the alert dialog
		builder.setNegativeButton("CANCEL", (dialog, which) -> {

		});

		// create the builder
		builder.create();

		// create the alert dialog with the alert dialog builder instance
		AlertDialog alertDialog = builder.create();
		alertDialog.show();
	}

	public static File getInternalFilesDir(Context context) {
		return context.getFilesDir();
	}

	public static File getExternalFilesDir(Context context) {
		return context.getExternalFilesDir("");
	}

	public static void appendToFile(Context context, File file, String text) throws IOException {
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true));
		writer.append(text);
		writer.close();
	}

	public static Response get(String url) throws IOException {
		return get(url, null);
	}

	/**
	 * ⚠️ Remember to call response.close() after you're done with it
	 * @param url
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public static Response get(String url, Map<String,String> params) throws IOException {
		var client = new OkHttpClient();

		HttpUrl httpURL = HttpUrl.parse(url);

		if (httpURL == null) {
			throw new IOException("Invalid URL");
		}

		var httpBuilder = httpURL.newBuilder();

		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
			}
		}

		Request request = new Request.Builder().url(httpBuilder.build()).build();

		try (Response response = client.newCall(request).execute()) {
			return response;
		}
	}

	public static void getAsync(String url) {
		getAsync(url, null, (response, e) -> { });
	}

	public static void getAsync(String url, ResponseCallback callback) {
		getAsync(url, null, callback);
	}

	public static void getAsync(String url, Map<String,String> data) {
		getAsync(url, data, (response, e) -> { });
	}

	/**
	 * Make a GET request to a URL with Query String
	 * In PHP, you can read the value by $_GET
	 * @param url
	 * @param data
	 * @param callback
	 */
	public static void getAsync(String url, Map<String,String> data, ResponseCallback callback) {
		new Thread(() -> {
			var client = new OkHttpClient();
			HttpUrl httpURL = HttpUrl.parse(url);

			if (httpURL == null) {
				if (callback != null) {
					callback.run(null, new IOException("Invalid URL"));
				}
				return;
			}

			var httpBuilder = httpURL.newBuilder();

			if (data != null) {
				for (Map.Entry<String, String> entry : data.entrySet()) {
					httpBuilder.addQueryParameter(entry.getKey(), entry.getValue());
				}
			}

			Request request = new Request.Builder().url(httpBuilder.build()).build();
			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(@NotNull Call call, @NotNull IOException e) {
					if (callback != null) {
						callback.run(null, e);
					}
				}

				@Override
				public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
					if (callback != null) {
						callback.run(response, null);
					}
					response.close();
				}
			});
		}).start();
	}

	/**
	 * Make a POST request to a URL with FormBody Data
	 * In PHP, you can read the value by $_POST
	 * @param url
	 * @param data
	 * @param callback
	 */
	public static void postAsync(String url, Map<String,String> data, ResponseCallback callback) {
		new Thread(() -> {
			var client = new OkHttpClient();
			HttpUrl httpURL = HttpUrl.parse(url);

			if (httpURL == null) {
				if (callback != null) {
					callback.run(null, new IOException("Invalid URL"));
				}
				return;
			}

			var httpBuilder = httpURL.newBuilder();
			var bodyBuilder = new FormBody.Builder();

			if (data != null) {
				for (Map.Entry<String, String> entry : data.entrySet()) {
					bodyBuilder.add(entry.getKey(), entry.getValue());
				}
			}

			Request request = new Request.Builder()
					.url(httpBuilder.build())
					.post(bodyBuilder.build())
					.build();

			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(@NotNull Call call, @NotNull IOException e) {
					callback.run(null, e);
				}

				@Override
				public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
					if (callback != null) {
						callback.run(response, null);
					}
					response.close();
				}
			});
		}).start();
	}

	/**
	 * ⚠️ Network call
	 * @param url
	 * @param targetFile
	 * @throws IOException
	 */
	public static File downloadFile(String url, File targetFile) throws IOException {
		var response = get(url);
		BufferedSink sink = Okio.buffer(Okio.sink(targetFile));

		if (response.body() != null) {
			sink.writeAll(response.body().source());
			sink.close();
			return targetFile;
		} else {
			sink.close();
			throw new IOException("Response body is null, cannot convert to a file");
		}
	}
}
