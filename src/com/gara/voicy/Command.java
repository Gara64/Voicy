package com.gara.voicy;

import java.util.ArrayList;

import android.util.Log;

/*
 * The Commands correspond in a single order, e.g. PLAY_MUSIC
 * associated to one or several voices pattern, e.g. "joue de la musique", 
 * "lance de la musique", etc.
 * So a command is an Array of string (the text) associated to a single string
 * (the cmd) and an optional parameter.
 * 
 */

public class Command 
{
	String command;
	String[] voiceOrders;
	String param;
	
	public Command(String cmd, ArrayList<String> voiceOrders, String param)
	{
		this.command = cmd;
		this.voiceOrders = Util.arrayListToStringArray(voiceOrders);
		this.param = param;
	}
	
	/* Checks if a voice record match a voiceOrders for this Command */
	public boolean isVoiceMatching(String voiceRecord)
	{
		for(int i=0; i<voiceOrders.length; i++)
		{
			//perfect match => distance = 0
			int distance = LevenshteinDistance.computeLevenshteinDistance(voiceRecord, voiceOrders[i]);			
			Log.d("distance", String.valueOf(distance));
			if( distance <= Constants.VOICE_DISTANCE_MAX )
				return true;
		}
		return false;
	}
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String cmd) {
		this.command = cmd;
	}
	public String[] getVoiceOrders() {
		return voiceOrders;
	}
	public void setVoiceOrders(String[] voiceOrders) {
		this.voiceOrders = voiceOrders;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	

}
