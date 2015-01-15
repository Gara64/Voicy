package com.gara.voicy;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

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
	Button btnRecord, btnGetCommands;
	Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	try
    	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        
        btnRecord = (Button) findViewById(R.id.btnRecord);
        btnRecord.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
	        	SpeechRecognitionHelper.run(activity);
	        }
	    });
        
        btnGetCommands = (Button) findViewById(R.id.btnGetCommands);
        btnGetCommands.setOnClickListener(new View.OnClickListener() {
	        public void onClick(View v) {
		        Commands.showCommands(activity);	 
		      
	        }
	    });
        
        //read settings file
        String[] settings = JsonHelper.readSettings(getApplicationContext(), Constants.JSON_FILE);
        if( settings.length == 0 )
        	Util.switchActivity(this, getApplicationContext(), SettingsActivity.class);
        else
        	Network.serverAddress = settings[0];
        
        
        //get the commands
	    Network net = new Network(getApplicationContext());
		net.execute("GET_COMMANDS");
		String rep = net.get();
	    Commands.buildCommands(rep);
        
        
        if(!Util.checkInternet(getApplicationContext()))
        		Toast.makeText(this, "WARNING : no internet", Toast.LENGTH_LONG).show();
        
    	}
    	
    	catch(RuntimeException e)
    	{
    		e.printStackTrace();
    	} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
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
            Util.switchActivity(this, getApplicationContext(), SettingsActivity.class);
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
