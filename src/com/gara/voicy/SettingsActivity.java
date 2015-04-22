package com.gara.voicy;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends Activity
{
	Button okBtn;
	EditText editIP, editVoiceRelevance;
	Activity activity;
	String ip;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        activity = this;
        
        editIP = (EditText) findViewById(R.id.editTextIP);
        editVoiceRelevance = (EditText) findViewById(R.id.editVoiceRelevance);
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
	        	
	        	//TODO : do this check elsewhere
	        	if(!Util.testServerConnectivity(Settings.SERVER_ADDRESS))
	        		Toast.makeText(getApplicationContext(), "ERROR : cannot reach server", Toast.LENGTH_LONG).show();
	        	Util.switchActivity(activity, getApplicationContext(), MainActivity.class);
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
