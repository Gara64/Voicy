package com.gara.voicy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Network 
{
	public static String serverAddress = "";
	
	public static void sendGet(Context context, String command)
	{
		if ( serverAddress == "")
		{
			Toast.makeText(context, "ERROR : please set server IP in settings", Toast.LENGTH_LONG).show();
			return;
		}
		try 
		{
			String url = "http://" + serverAddress + "/" + Constants.SERVER_FILE + 
					"?action=" + command;
			Log.d("url", url);
			URL obj;
			obj = new URL(url);
			
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			InputStream inp = con.getInputStream(); 
			// optional default is GET
			//con.setRequestMethod("GET");
	 
			int responseCode = con.getResponseCode();
			Log.d("code", String.valueOf(responseCode));
			
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			
			in.close();
	 
			//print result
			Log.d("reader", response.toString());
		
		} catch (MalformedURLException e) {
			Toast.makeText(context, "ERROR : server address malformed", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Toast.makeText(context, "ERROR : cannot reach server", Toast.LENGTH_LONG).show();
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
		}
 
	}
	
	public void testURL() throws Exception {
	    String strUrl = "http://stackoverflow.com/about";

	    try {
	        URL url = new URL(strUrl);
	        HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
	        urlConn.connect();

	        //assertEquals(HttpURLConnection.HTTP_OK, urlConn.getResponseCode());
	    } catch (IOException e) {
	        System.err.println("Error creating HTTP connection");
	        e.printStackTrace();
	        throw e;
	    }
	}
	

}
