package net.louislam.android;

import okhttp3.Response;

public interface ResponseCallback {
	public void run(Response response, Exception e);
}
