package com.gara.voicy;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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
		    
	    if (networkInfo != null && networkInfo.isConnected()) 
	    	return true;
	    else
	    	return false;
	    
	}
	public static boolean testServerConnectivity(String serverAddress)
	{
		Socket socket = null;
		boolean reachable = false;
		try {
		    socket = new Socket(serverAddress, 80);
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
	
	
}
