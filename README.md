LouisLam's Lazy Library for Android. 
===================


### Why we need the library? ###

Every single time, while i just wanted to pop out a simple alert dialog and I forgot how to create. Then I kept googling the code. Once I found that the code is very long. So I wonder why we cannot have a simple way just like Javascript alert(). It is easy to remember and just one line of code!

So I decided to create the library to do that.

	L.alert("Hi.");

Of course, the library contains other lazy functions. Check examples below.


### Descriptions ###

There are example usage for the methods.

"L" means LouisLam or Lazy.

Assumption: 
In all examples, "this" means Activity/Context object.


## Installation ##

1. Download the jar file.
2. Import the jar to your android project.
3. Import the package in your Class/Activity.

		import net.louislam.android.L;

4. Done.


## Lazy Alert Dialog ##

I love this so much. <3

Usage: 

	L.alert(this, "I am Louis.");

Alert Dialog with callback:

	L.alert(this, "Boss: Where is Louis!?", new OnClickListener() {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			L.alert(MainActivity.this, "Louis: here :( ");
		}
	});

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

Get your app's version

	L.getAppVersion(this);