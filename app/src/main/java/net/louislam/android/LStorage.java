package net.louislam.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;

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
		c.getSharedPreferences("Preference", 0).edit().putString(key, value).apply();
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
	 * Retrieve a String with default value.
	 * If the key exists, return the value, else return default value.
	 */
	public static String getString(Context c, String key, String defaultValue) {
		return (exists(c, key)) ? getString(c, key) : defaultValue;
	}

	/**
	 * Store a Boolean
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, boolean value) {
		c.getSharedPreferences("Preference", 0).edit().putBoolean(key, value).apply();
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

	public static boolean getBoolean(Context c, String key, boolean defaultValue) {
		return (exists(c, key)) ? getBoolean(c, key) : defaultValue;
	}

	/**
	 * Store a int
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, int value) {
		c.getSharedPreferences("Preference", 0).edit().putInt(key, value).apply();
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

	public static int getInt(Context c, String key, int defaultValue) {
		return (exists(c, key)) ? getInt(c, key) : defaultValue;
	}

	/**
	 * Store a float
	 *
	 * @param c
	 * @param key
	 * @param value
	 */
	public static void store(Context c, String key, float value) {
		c.getSharedPreferences("Preference", 0).edit().putFloat(key, value).apply();
	}

	/**
	 * Retrieve a float
	 *
	 * @param c
	 * @param key
	 * @return the value, return 'null' if the key doesn't exist.
	 */
	public static float getFloat(Context c, String key) {
		try {
			return c.getSharedPreferences("Preference", 0).getFloat(key, -1);
		} catch (ClassCastException ex) {
			return 0;
		}
	}

	public static float getFloat(Context c, String key, float defaultValue) {
		return (exists(c, key)) ? getFloat(c, key) : defaultValue;
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

	public static long getLong(Context c, String key, long defaultValue) {
		return (exists(c, key)) ? getLong(c, key) : defaultValue;
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
		c.getSharedPreferences("Preference", 0).edit().putStringSet(key, value).apply();
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

    public static void remove(Context context, String key) {
		context.getSharedPreferences("Preference", 0).edit().remove(key).apply();
    }

}
