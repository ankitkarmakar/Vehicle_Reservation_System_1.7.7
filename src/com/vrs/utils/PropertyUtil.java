package com.vrs.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class PropertyUtil {

	public static String getMessage(String key) {
		Locale locale=Locale.getDefault();
		ResourceBundle rb=ResourceBundle.getBundle("message", locale);
		return rb.getString(key);
	}
}
