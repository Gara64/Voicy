package com.gara.voicy;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Util {
	
	public static void switchActivity(Activity activity, Context context, Class<?> cls)
	{
		Intent i = new Intent(context, cls);
    	activity.startActivity(i);
	}
	
	
}
