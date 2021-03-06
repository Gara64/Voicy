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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	Button btnRecord, btnGetCommands;
	TextView tvUnderstoodVoice, tvChosenCommand;
	Activity activity = this;
	Network net;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			tvUnderstoodVoice = (TextView) findViewById(R.id.tv_understood_voice);
			tvChosenCommand = (TextView) findViewById(R.id.tv_chosen_command);
			
			btnRecord = (Button) findViewById(R.id.btnRecord);
			btnRecord.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					SpeechRecognitionHelper.run(activity);
				}
			});

			btnGetCommands = (Button) findViewById(R.id.btnGetCommands);
			btnGetCommands.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CommandsFactory.showCommands(activity);
				}
			});

			/* If no settings found, switch on the setting activity */
			if(!Settings.getSettings(getApplicationContext()))
				Util.switchActivity(this, getApplicationContext(), SettingsActivity.class);
			
			/* Checks that internet is activated */
			if (!Network.isNetworkOn(getApplicationContext()))
				Toast.makeText(this, "WARNING : no internet", Toast.LENGTH_LONG).show();
			else
			{
				/* Get the commands by requesting the Json file */
				String rep = Util.sendCommand("GET_COMMANDS");
				if( rep.isEmpty() )
					Toast.makeText(this, "WARNING : no command found", Toast.LENGTH_LONG).show();
				else
					CommandsFactory.buildCommands(rep);
			}

		}

		catch (RuntimeException e) {
			e.printStackTrace();
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
			Util.switchActivity(this, getApplicationContext(),
					SettingsActivity.class);
		}
		return super.onOptionsItemSelected(item);
	}

	/* Activity Results handler : called after recording the voice */
	public void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		if (requestCode == Constants.VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK)
		{
			/* The recording voice is received as an ArrayList, containing several results.
			 * The more relevant one is in position 0 : we keep this one */
			ArrayList<String> matches = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			if (matches.size() > 0)
			{
				String record = matches.get(0).toString();
				/* Display the record and send the associated command */
				//TODO : change this to display a label instead of a Toast
				Toast.makeText(this, record,	Toast.LENGTH_LONG).show();
				Command cmd = CommandsFactory.getCommandFromVoice(record);
				if( cmd == null )
					updateTextView(record, "No command found");
				else{
					updateTextView(record, cmd.command);
					//TODO : send command
					//Util.sendCommand(net, cmd.command);
				}
			}
		}

		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void updateTextView(String record, String command)
	{
		tvUnderstoodVoice.setText(record);
		tvChosenCommand.setText(command);
	}

}
