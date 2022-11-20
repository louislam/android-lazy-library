LouisLam's Lazy Library for Android.
===================

### Why do we need `android-lazy-library`? ###

Every single time, I always forgot how to create a simple alert dialog without googling. I was always wondering why we cannot have a simple way to show alert dialog in Android  just like the JavaScript's alert().

So I decided to create this library to do that.

```java
L.alert(this, "Hi.");
```

Without this, it would be like:

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

## Installation ##

```gradle
implementation 'net.louislam.android:lazy:2.0.0'
```

### Assumption ###
In all examples, `this` means Activity/Context object.


## Lazy Alert Dialog ##

I love this so much. <3

Usage:

```java
L.alert(this, "I am Alert.");
```

Alert Dialog with callback:

```java
L.alert(this, "Boss: Where is Louis!?", (dialog, which) -> {
    L.alert(MainActivity.this, "Louis: here :( ");
});
```

## Lazy Input Dialog ##

Example Usage:

```java
L.inputDialog(this, "What is your name?", value -> {
    String name = value;
    L.alert(MainActivity.this, "My name is: " + name);
});
```

With default value (add a parameter to the last)

```java
L.inputDialog(this, "What is your name?", value -> {
    String name = value;
    L.alert(MainActivity.this, "My name is: " + name);
}, "Louis");
```

## Lazy Confirmation Dialog ##

Example Usage:

```java
L.confirmDialog(this, "Are you sure?", (dialog, which) -> {
    // Do something here if clicked "Yes"
});
```

For those activity classes already implement OnClickListener

```java
public class LoginActivity extends Activity implements OnClickListener {
    
    public void quit() {
        L.confirmDialog(this, "Are you sure?");
    }
    
}
```

## Lazy Loading Dialog ##

Example Usage:

```java
ProgressDialog loadingDialog = L.progressDialog(this, "Loading...");
```

Dismiss the dialog:

```java
loadingDialog.dismiss();
```

## Lazy Toast ##

Toast is so simple, but I still want to make it lazy.

```java
L.toast(this, "Hi, I am Toast.");
L.toast(this, "Hi, I am Toast with duration.", true);
```


## Lazy Log ##

Log without a tag and support long string.

Example Usage:

```java
L.log("[Debug] the activity is loaded.");
```

Disable/Enable Log globally

```java
L.enableLog(false);
```

## Lazy Storage

Store

```java
LStorage.store(this, "key", "valueString");
LStorage.store(this, "key", 123456);
LStorage.store(this, "key", 3.14f);
LStorage.store(this, "key", true);
```

Get

```java
LStorage.getString(this, "key");
LStorage.getInt(this, "key");
LStorage.getFloat(this, "key");
LStorage.getBoolean(this, "key");
```

## Lazy Start Activity ##

Example Usage:

```java
	L.startActivity(this, "LoginActivity");
```

or

```java
	L.startActivity(this, LoginActivity.class);
```

or (For result)

1314 = Request Code

```java
L.startActivity(this, LoginActivity.class, 1314);
```

or (starting an activity for result with bundle)

```java
Bundle bundle = new Bundle();
L.startActivity(this, LoginActivity.class, 1314, bundle);
```

## Others ##

Open URL in browser

```java
L.openUrl(this, "http://google.com");
```

Get your app's version

```java
L.getAppVersion(this);
```