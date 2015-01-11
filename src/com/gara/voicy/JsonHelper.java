package com.gara.voicy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.content.Context;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Toast;

public class JsonHelper 
{
	//OutputStream out;
	public static BufferedWriter out;
	public static BufferedReader in;
	
	public static String[] readSettings(Context context, String fileName) 
	{
		JsonReader reader = null;
		String ip = "";
		 
	    try 
	    {
	    	in  = new BufferedReader(new FileReader(new 
	                File(context.getFilesDir() + File.separator + fileName)));
	    	reader = new JsonReader(in);
		    
	    	reader.beginObject();
	        while (reader.hasNext()) 
	        {
	          String name = reader.nextName();
	          if (name.equals("settings")) 
	          {
	        	  reader.beginObject();
	        	  while( reader.hasNext() )
	        	  {
	        		  name = reader.nextName();
	        		  //Log.d("reader", " - : " + name);
	        		  if (name.equals("server_ip"))
	        			  ip = reader.nextString();
	        	  }
	        	  reader.endObject();
	          }
	          
	        }
	        reader.endObject();
	        reader.close();
	        in.close();
	        
	        
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return new String[]{ip};
	}
	
	public static void writeSettings(String[] settings, Context context, String fileName)
	{
		
		
		JsonWriter writer;
		try 
		{
			out = new BufferedWriter(new FileWriter(new 
		               File(context.getFilesDir() + File.separator+ fileName)));
				
			//new OutputStreamWriter(out, "UTF-8")
			writer = new JsonWriter(out);
		    //writer.setIndent("  ");
		     
			writer.beginObject();
		    writer.name("settings");
		    writer.beginObject();
		    writer.name("server_ip").value(settings[0]);
		    writer.endObject();
		    writer.endObject();
		    
		    writer.close();
		    out.close();
		    
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close()
	{
		try {
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void read(Context context, String fileName) throws IOException
	{
		 
		String read;
		StringBuilder builder = new StringBuilder("");
		in  = new BufferedReader(new FileReader(new 
                File(context.getFilesDir() + File.separator + fileName)));
		
		while((read = in.readLine()) != null){
			builder.append(read);
		}
		Log.d("Output", builder.toString());
		Toast.makeText(context.getApplicationContext(), builder.toString(), Toast.LENGTH_LONG).show();
	}
	
	public void flush()
	{
		
	}
}