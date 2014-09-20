package net.louislam.android;

import android.annotation.TargetApi;
import android.content.Context;

import java.util.Set;

/**
 * LStorage - Lazy Storage
 *
 * @author Louis Lam (louis@louislam.net)
 */
public class LStorage {

	/**
	 * Store a String
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, String value) {
		c.getSharedPreferences("Preference", 0).edit().putString(key, value).commit();
	}

	/**
	 * Retrieve a String
	 *
	 * @param c
	 * @param key
	 * @return the value, return 'null' if the key doesn't exist.
	 */
	public static String getString(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).getString(key, null);
	}


	/**
	 * Store a Boolean
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, boolean value) {
		c.getSharedPreferences("Preference", 0).edit().putBoolean(key, value).commit();
	}

	/**
	 * Retrieve a Boolean
	 *
	 * @param c
	 * @param key
	 * @return the value, return 'null' if the key doesn't exist.
	 */
	public static boolean getBoolean(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).getBoolean(key, false);
	}

	/**
	 * Store a int
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, int value) {
		c.getSharedPreferences("Preference", 0).edit().putInt(key, value).commit();
	}

	/**
	 * Retrieve a int
	 *
	 * @param c
	 * @param key
	 * @return the value, return '-1' if the key doesn't exist.
	 */
	public static int getInt(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).getInt(key, -1);
	}

	/**
	 * Store a float
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, float value) {
		c.getSharedPreferences("Preference", 0).edit().putFloat(key, value).commit();
	}

	/**
	 * Retrieve a float
	 *
	 * @param c
	 * @param key
	 * @return the value, return 'null' if the key doesn't exist.
	 */
	public static float getFloat(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).getFloat(key, -1);
	}

	/**
	 * Store a long
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, long value) {
		c.getSharedPreferences("Preference", 0).edit().putLong(key, value)
			.commit();
	}

	/**
	 * Retrieve a long
	 *
	 * @param c
	 * @param key
	 * @return the value, return 'null' if the key doesn't exist.
	 */
	public static long getLong(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).getLong(key, -1);
	}

	/**
	 * Store a String Set
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	@TargetApi(11)
	public static void store(Context c, String key, Set<String> value) {
		c.getSharedPreferences("Preference", 0).edit().putStringSet(key, value).commit();
	}

	/**
	 * Retrieve a String Set
	 *
	 * @param c
	 * @param key
	 * @return the value, return 'null' if the key doesn't exist.
	 */
	@TargetApi(11)
	public static Set<String> getStringSet(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).getStringSet(key, null);
	}

	/**
	 * Checks whether the preferences contains a preference.
	 * @param c
	 * @param key
	 * @return
	 */
	public static boolean exists(Context c, String key) {
		return c.getSharedPreferences("Preference", 0).contains(key);
	}
}
