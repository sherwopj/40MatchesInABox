package com.sherwopj.fortymatches;

import java.text.DecimalFormat;

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
		
		double correctGuesses = Integer.valueOf(SP.getString("total_number_of_correct_guesses", "0"));
		Log.i(this.getLocalClassName(), "correctGuesses: "+correctGuesses);
		double incorrectGuesses = Integer.valueOf(SP.getString("total_number_of_incorrect_guesses", "0"));
		Log.i(this.getLocalClassName(), "incorrectGuesses: "+incorrectGuesses);
		String scorePercent = "0";
		if(correctGuesses>0 || incorrectGuesses>0){
			scorePercent = new DecimalFormat("##.##").format((correctGuesses/(correctGuesses+incorrectGuesses))*100);
		}			
		Log.i(this.getLocalClassName(), "scorePercent: "+scorePercent);
		aboutScoreTextView.setText(scorePercent+"%");
		
		
		
		if(SP.getBoolean("all_levels_completed", false)) {
			//Give the man a medal
			View rosette = findViewById(R.id.rosette);
			rosette.setVisibility(View.VISIBLE);
		}
		
		
		
	}
}
