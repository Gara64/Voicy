package com.gara.voicy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import android.app.Activity;



public class CommandsFactory 
{
	public static ArrayList<Command> commands;
	//static ArrayList<String> allVoices;
	//static ArrayList<String> allCommands;
	
	/* Read the Json file containing commands and build the ArrayLists */
	public static void buildCommands(String json)
	{
		commands = JsonHelper.readCommands(json);
		
		/*allCommands = new ArrayList<String>();
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
		*/
	}
	
	/* Display the commands */
	public static void showCommands(Activity activity)
	{
		if( commands == null )
			Util.showDialog(activity, "Commands", "No command available :/");
		else
		{
			String str = "";
			for(Command cmd : commands)
			{
				String voicesForCmd = "\t";
				for (int i=0;i<cmd.voiceOrders.length; i++)
				{
					if( i < cmd.voiceOrders.length - 1)
						voicesForCmd += cmd.voiceOrders[i] + "\n\t";
					else
						voicesForCmd += cmd.voiceOrders[i] + "\n";
				}
				
				str += cmd.command + " : \n";
				str += voicesForCmd;
			}
			
			/*Set<String> cmds = hashCmd.keySet();
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
			}*/
			Util.showDialog(activity, "Commands", str);
		}
	}

	public static void getCommandByVoice(String voice)
	{
		
	}
	
	
	
}
