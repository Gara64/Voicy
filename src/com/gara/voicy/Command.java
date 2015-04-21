package com.gara.voicy;

import java.util.ArrayList;

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
