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
	EditText eText;
	Activity activity;
	String ip;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        
        activity = this;
        
        eText = (EditText) findViewById(R.id.editTextIP);
        okBtn = (Button) findViewById(R.id.btnSettingsOk);
        okBtn.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	JsonHelper.writeSettings(extractSettings(), getApplicationContext(), Constants.JSON_FILE);
	        	String[] settings = JsonHelper.readSettings(getApplicationContext(), Constants.JSON_FILE);
	        	Network.serverAddress = settings[0];
	        	if(!Util.testServerConnectivity(Network.serverAddress))
	        		Toast.makeText(getApplicationContext(), "ERROR : cannot reach server", Toast.LENGTH_LONG).show();
	        	Util.switchActivity(activity, getApplicationContext(), MainActivity.class);
	        }
	    });
	}
	
	public String[] extractSettings()
	{
		String[] settings = new String[1];
		settings[0] = eText.getText().toString();
		return settings;
	}


}
