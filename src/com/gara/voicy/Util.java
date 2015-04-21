package com.gara.voicy;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Util {
	
	public static void switchActivity(Activity activity, Context context, Class<?> cls)
	{
		Intent i = new Intent(context, cls);
    	activity.startActivity(i);
	}
	
	public static boolean checkInternet(Context context)
	{
		ConnectivityManager connMgr = (ConnectivityManager) 
				context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		    
	    if (networkInfo != null && networkInfo.isConnected()) {
	    	Log.d("network_test", networkInfo.getExtraInfo());
	    	return true;
	    }
	    else
	    	return false;
	    
	}
	public static boolean testServerConnectivity(String serverAddress)
	{
		Socket socket = null;
		boolean reachable = false;
		try {
		    socket = new Socket("http://" + serverAddress, 80);
		    reachable = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {            
		    if (socket != null)
		    	try { socket.close(); } catch(IOException e) {return reachable;}
		    else
		    	return reachable;
		}
		
		return reachable;
		
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
}
