package com.gara.voicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;

public class Commands 
{
	public static Hashtable<String, ArrayList<String>> hashCmd;
	static ArrayList<String> allVoices;
	static ArrayList<String> allCommands;
	
	public static void buildCommands(String json)
	{
		hashCmd = JsonHelper.readCommands(json);
		
		allCommands = new ArrayList<String>();
		allVoices = new ArrayList<String>();
		
		Set<String> cmds = hashCmd.keySet();
		Iterator<String> it = cmds.iterator();
		while(it.hasNext())
		{
			String cmd = it.next();
			allCommands.add(cmd);
			ArrayList<String> txts = hashCmd.get(cmd);
			for(String s : txts)
				allVoices.add(s);
		}
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
				String cmd = it.next();
				ArrayList<String> txts = hashCmd.get(cmd);
				String voicesForCmd = "\t";
				for (int i=0;i<txts.size();i++)
				{
					if( i < txts.size() - 1)
						voicesForCmd += txts.get(i) + "\n\t";
					else
						voicesForCmd += txts.get(i) + "\n";
				}
					
				str += cmd + " : \n";
				str += voicesForCmd;
			}
			Util.showDialog(activity, "Commands", str);
		}
	}

	public static void getCommandByVoice(String voice)
	{
		
	}
	
	
	
}
