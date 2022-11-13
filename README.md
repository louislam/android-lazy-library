LouisLam's Lazy Library for Android. 
===================

### Why do we need `android-lazy-library`? ###

Every single time, personally, I always forgot how to create a simple alert dialog without googling. I was always wondering why we cannot have a simple way in Android just like Javascript's alert().

So I decided to create this library to do that.

```java
L.alert(this, "Hi.");
```

Without the library to do the same thing:

```java
AlertDialog.Builder b = new AlertDialog.Builder(this);
b.setMessage("Hi.");
b.setCancelable(false);
b.setPositiveButton("OK", new OnClickListener() {
	@Override
	public void onClick(DialogInterface dialog, int which) {
		dialog.dismiss();
	}
});
b.show();
```

It is more simple, right?

Of course, `android-lazy-library` contains other lazy functions. Please check examples below.


### Descriptions ###

There are example usage for the methods.

"L" means LouisLam or Lazy.


## What's News ##

### 20 Sept 2014 (v1.1) ###
- Lazy Storage (Class name: LStorage). You can store Sting, Int, Float or Boolean now.


## Installation ##

1. Download the jar file.
2. Import the jar to your android project.
3. Import the package in your Class/Activity.

```java
import net.louislam.android.*;
```

4. Done.

### Assumption ###
In all examples, **"this" means Activity/Context object.**


## Lazy Alert Dialog ##

I love this so much. <3

Usage: 

```java
L.alert(this, "I am Louis.");
```

Alert Dialog with callback:

```java
L.alert(this, "Boss: Where is Louis!?", (dialog, which) -> {
    L.alert(MainActivity.this, "Louis: here :( ");
});
```

## Lazy Input Dialog ##

Example Usage:

	L.inputDialog(this, "What is your name?", new InputListener() {
	 
	 	public void inputResult(String value) {
	 		String name = value;
	 		L.alert(MainActivity.this, "My name is: " + name);
		}
	 
	 });

With default value (add a parameter to the last)

	L.inputDialog(this, "What is your name?", new InputListener() {
	 
	 	public void inputResult(String value) {
	 		String name = value;
	 		L.alert(MainActivity.this, "My name is: " + name);
		}
	 
	 }, "Louis");

## Lazy Confirmation Dialog ##

Example Usage:

	L.confirmDialog(this, "Are you sure?", new OnClickListener () {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// Do something here if clicked "Yes"	
		}		
	});


For those activity classes already implements OnClickListener

	public class LoginActivity extends Activity implements OnClickListener {
		....
	  	public void quit() {
			L.confirmDialog(this, "Are you sure?");
	 	}
		....
	}


## Lazy Loading Dialog ##

Example Usage:

	ProgressDialog loadingDialog = L.progressDialog(this, "Loading...");

Dismiss the dialog:

	loadingDialog.dismiss();


## Lazy Log ##

simply methods for LogCat

Example Usage:

	L.log("[Debug] the activity is loaded.");

Disable/Enable Log globally

	L.enableLog(false);


## Lazy String Storage

Store

	L.storeString(this, "username", "louislam");


Retrieve / Get

	L.getString(this, "username");
	

## Lazy Start Activity ##
	 
Example Usage: 

	L.startActivity(this, "LoginActivity");

or

	L.startActivity(this, LoginActivity.class);

or (For result)

1314 = Request Code

	L.startActivity(this, LoginActivity.class, 1314);

or (starting an activity for result with bundle)

	Bundle bundle = new Bundle();
	L.startActivity(this, LoginActivity.class, 1314, bundle);


## Others ##

Open URL in browser

	L.openUrl(this, "http://google.com");

Get your app's version

	L.getAppVersion(this);


## License ##


The MIT License (MIT)

Copyright (c) 2013 Louis Lam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
