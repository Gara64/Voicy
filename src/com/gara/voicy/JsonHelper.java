package com.gara.voicy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Hashtable;


import android.content.Context;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import android.util.Log;
import android.widget.Toast;

public class JsonHelper 
{
	public static BufferedWriter out;
	public static BufferedReader in;
	
	/* Reads and extracts the settings Json file */
	public static String[] readSettings(Context context, String fileName) 
	{
		JsonReader reader = null;
		String ip = "", relevance = "";
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
	        		  else if (name.equals("voice_relevance"))
	        			  relevance = reader.nextString();
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
	    
	    return new String[]{ip, relevance};
	}
	
	/* Reads and extracts the Commands json file */
	public static ArrayList<Command> readCommands(String json)
	{
		//Hashtable<String, ArrayList<String>> hashCmd = new Hashtable<String, ArrayList<String>>();
		ArrayList<Command> commands = new ArrayList<Command>();
		
		JsonReader reader = new JsonReader(new StringReader(json));
		String cmd = "";
		ArrayList<String> txt;
		
		try 
		{
			reader.beginObject();
	        while (reader.hasNext()) 
	        {
	        	String name = reader.nextName(); //commands
	        	reader.beginArray(); 
	        	while(reader.hasNext())
	        	{
	        		//new block of command
	        		reader.beginObject();
	        		txt = new ArrayList<String>();
	        		String param = null;
	        		while(reader.hasNext())
	        		{
	        			name = reader.nextName();
		        		if(name.equals("cmd")){
		        			cmd = reader.nextString();
		        			
		        		}
		        		else if(name.equals("text"))
		        		{
		        			//from now, the next token could be an array or an object
		        			JsonToken nextToken = reader.peek();
		        			
		        			//it's an array : several texts for one command
		        			if(nextToken.equals(JsonToken.BEGIN_ARRAY))
		        			{
		        				reader.beginArray();
		        				while(reader.hasNext())
		    	        		{
		        					txt.add(reader.nextString());
		    	        		}
		        				reader.endArray();
		        			}
		        			//it's an object : one possible text for one command
		        			else
		        				txt.add(reader.nextString());
		        		}
		        		else if(name.equals("param"))
		        		{
		        			param = reader.nextString();
		        			//Log.d("param", name + " - " + re)
		        		}
	        		}//end while cmd block
	        		reader.endObject();
	        		//hashCmd.put(cmd, txt);
	        		commands.add(new Command(cmd, txt, param));
	        	}//end while array commands
	        	reader.endArray();
	        }//end while global object
	        reader.endObject();
	        reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return commands;
		
	}
	
	/* Save the new settings into the Json file */
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
		    writer.name("voice_relevance").value(settings[1]);
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