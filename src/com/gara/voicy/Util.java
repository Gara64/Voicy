package com.gara.voicy;


import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

public class Util 
{
	
	public static void switchActivity(Activity activity, Context context, Class<?> cls)
	{
		Intent i = new Intent(context, cls);
    	activity.startActivity(i);
	}
	

	
	
	public static void showDialog(Activity activity, String title, String content)
	{
		AlertDialog alertDialog = new AlertDialog.Builder(activity).create();

		alertDialog.setTitle(title);
		alertDialog.setMessage(content);
		// Setting Icon to Dialog
		//alertDialog.setIcon(R.drawable.tick);
		
		// Showing Alert Message
		alertDialog.show();
	}
	
	public static String[] arrayListToStringArray(ArrayList<?> list)
	{
		return list.toArray(new String[list.size()]);
	}
	
	/* Sends a command to the server */
	public static String sendCommand(Network net, String cmd)
	{
		String ret = "";
		try 
		{
			net.execute(Settings.SERVER_ADDRESS, cmd);
			ret = net.get();
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	public static int convertPercentToDistance(int percent)
	{
		/* 100% => 0
		 * 75% => 5
		 * 50% => 10
		 */
		
		return (1 / percent * 100) / 5;
		//(percent / 5) 
	}
}
