package com.gara.voicy;

import android.content.Context;

public class Settings {
	
	public static String SERVER_ADDRESS = "";
	public static int VOICE_DISTANCE_MAX = 0;
	
	public static boolean getSettings(Context context)
	{
		// read settings file
		String[] settings = JsonHelper.readSettings(context, Constants.JSON_FILE);
		if(settings.length == 2)
		{
			SERVER_ADDRESS = settings[0];
			VOICE_DISTANCE_MAX = Integer.parseInt(settings[1]);
			return true;
		}
		
		return false;
	}

}
