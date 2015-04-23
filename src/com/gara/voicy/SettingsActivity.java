package com.gara.voicy;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity
{
	Button okBtn, testConnectivityBtn;
	EditText editIP, editVoiceRelevance;
	Activity activity;
	String ip;
	Context context;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        activity = this;
        context = this.getApplicationContext();
        
        editIP = (EditText) findViewById(R.id.editTextIP);
        editVoiceRelevance = (EditText) findViewById(R.id.editVoiceRelevance);
        testConnectivityBtn = (Button) findViewById(R.id.btnTestConnectivity);
        okBtn = (Button) findViewById(R.id.btnSettingsOk);
        
        /* Default values */
        editIP.setText(Settings.SERVER_ADDRESS);
        editVoiceRelevance.setText(String.valueOf(Settings.VOICE_DISTANCE_MAX));
        
        okBtn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	JsonHelper.writeSettings(extractSettings(), getApplicationContext(), Constants.JSON_FILE);
	        	String[] settings = JsonHelper.readSettings(getApplicationContext(), Constants.JSON_FILE);
	        	Settings.SERVER_ADDRESS = settings[0];
	        	Settings.VOICE_DISTANCE_MAX = Integer.parseInt(settings[1]);
	        	
	        	Util.switchActivity(activity, getApplicationContext(), MainActivity.class);
	        }
	    });
        
        testConnectivityBtn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	String server = editIP.getText().toString();
	        	if(!Network.isNetworkOn(context))
	        		Toast.makeText(getApplicationContext(), "ERROR : the network is off :/", Toast.LENGTH_LONG).show();
	        	else
	        	{
		        	if(!Network.isServerConnected(server))
		        		Toast.makeText(getApplicationContext(), "ERROR : cannot reach server " + server, Toast.LENGTH_LONG).show();
		        	else
		        		Toast.makeText(getApplicationContext(), "Connectity ok", Toast.LENGTH_LONG).show();
	        	}
	        }
        });
	}
	
	public String[] extractSettings()
	{
		String[] settings = new String[2];
		settings[0] = editIP.getText().toString();
		settings[1] = editVoiceRelevance.getText().toString();
		return settings;
	}


}
