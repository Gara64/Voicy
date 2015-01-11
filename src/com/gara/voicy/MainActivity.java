package com.gara.voicy;

import java.util.ArrayList;

import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity 
{
	Button btnRecord, btnGetSettings;
	Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	SpeechRecognitionHelper.run(activity);
	        }
	    });
        
        btnGetSettings = (Button) findViewById(R.id.btnGetSettings);
        btnGetSettings.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {

	        	String[] settings  = JsonHelper.readSettings(getApplicationContext(), Constants.JSON_FILE);
	        	Toast.makeText(activity, "IP server : " + settings[0], Toast.LENGTH_LONG).show();
	        }
	    });
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Util.switchActivity(this, getApplicationContext(), Settings.class);
        }
        return super.onOptionsItemSelected(item);
    }
    
 // Activity Results handler
    public void onActivityResult(int requestCode, int resultCode, Intent data) 
    {
        // if it’s speech recognition results and process finished ok
        if (requestCode == Constants.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {

            // receiving a result in string array
            // there can be some strings because sometimes speech recognizing inaccurate
            // more relevant results in the beginning of the list
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            // in “matches” array we holding a results... let’s show the most relevant
            if (matches.size() > 0)
            	Toast.makeText(this, matches.get(0).toString(), Toast.LENGTH_LONG).show();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    
    
}