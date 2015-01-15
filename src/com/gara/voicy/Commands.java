package com.gara.voicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.util.Log;

public class Commands 
{
	public static Hashtable<String, ArrayList<String>> hashCmd;
	
	public static void buildCommands(String json)
	{
		hashCmd = JsonHelper.readCommands(json);
	}
	
	public static void showCommands(Activity activity)
	{
		if( hashCmd == null )
			Util.showDialog(activity, "Commands", "No command available :/");
		else
		{
			Set<String> cmds = hashCmd.keySet();
			Iterator<String> it = cmds.iterator();
			String str = new String();
			while(it.hasNext())
			{
				str += it.next() + "\n";
			}
			Util.showDialog(activity, "Commands", str);
		}
	}
	
}
