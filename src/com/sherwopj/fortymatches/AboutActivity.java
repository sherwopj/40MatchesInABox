package com.sherwopj.fortymatches;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(this.getLocalClassName(), "Creating...: ");
		setContentView(R.layout.about);

	}



	@Override
	protected void onResume() {
		Log.i(this.getLocalClassName(), "Resuming...: ");
		setOverallScore();
		super.onResume();

		
	}
	
	private void setOverallScore() {
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		TextView aboutScoreTextView = (TextView) findViewById(R.id.aboutScore);
		
		int correctGuesses = SP.getInt("total_number_of_correct_guesses", 0);
		int incorrectGuesses = SP.getInt("total_number_of_incorrect_guesses", 0);
		int scorePercent = 1;
		if(correctGuesses>0 || incorrectGuesses>0){
			scorePercent = (correctGuesses/(correctGuesses+incorrectGuesses))*100;
		}			
		Log.i(this.getLocalClassName(), "scorePercent: "+scorePercent);
		aboutScoreTextView.setText(String.valueOf(scorePercent)+"%");
		
		
		
		if(SP.getBoolean("all_levels_completed", false)) {
			//Give the man a medal
			View rosette = findViewById(R.id.rosette);
			rosette.setVisibility(View.VISIBLE);
			
		}
		
		
		
	}
}
