package com.gara.voicy;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends Activity
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
