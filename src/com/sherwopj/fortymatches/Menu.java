package com.sherwopj.fortymatches;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
    	
        setContentView(R.layout.menu);
        //setGameLevelButtonStates();
    }
    
    @Override
    public void onResume() {
    	
    	setGameLevelButtonStates();
    	super.onResume();
    }

	private void setGameLevelButtonStates() {
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		if (SP.getBoolean("easy_level_unlocked", false)) {
	    	unlockGameMenuButton(R.id.ButtonNewEasyGame, R.string.btn_easy);
		}
		
		if (SP.getBoolean("hard_level_unlocked", false)) {
			unlockGameMenuButton(R.id.ButtonNewHardGame, R.string.btn_hard);
			
		} else {
			lockGameMenuButton(R.id.ButtonNewHardGame);
		}
		if (SP.getBoolean("harder_level_unlocked", false)) {
			unlockGameMenuButton(R.id.ButtonNewHarderGame, R.string.btn_harder);
			
		} else {
			
			lockGameMenuButton(R.id.ButtonNewHarderGame);
		}
		if (SP.getBoolean("rainman_level_unlocked", false)) {
			unlockGameMenuButton(R.id.ButtonNewRainmanGame, R.string.btn_rainman);
			
		} else {
			
			lockGameMenuButton(R.id.ButtonNewRainmanGame);
		}
		
		if(SP.getBoolean("all_levels_completed", false)) {
			//Give the man a medal
			View rosette = findViewById(R.id.rosette);
			rosette.setVisibility(View.VISIBLE);
			
		}
		
		
		
	}

	private void lockGameMenuButton(int buttonId) {
		Button menuButton = (Button) findViewById(buttonId);
		//harderButton.setText(R.string.);
		menuButton.setText(R.string.btn_locked);
		menuButton.setEnabled(false);
	}

	private void unlockGameMenuButton(int buttonId, int buttonTextResource) {
		Button menuButton = (Button) findViewById(buttonId);
		//harderButton.setText(R.string.);
		menuButton.setText(buttonTextResource);
		menuButton.setEnabled(true);
	}
	
	public void easyGameButtonClicked(View arg0) {
		Intent game = new Intent(Menu.this, Game.class);
		MatchesApplication matchesApplication = (MatchesApplication) getApplicationContext();
		matchesApplication.setDifficulty(GameDifficulty.EASY);
        startActivityForResult(game,0);


	}

	public void hardGameButtonClicked(View arg0) {
		Intent game = new Intent(Menu.this, Game.class);
		MatchesApplication matchesApplication = (MatchesApplication) getApplicationContext();
		matchesApplication.setDifficulty(GameDifficulty.HARD);
		startActivityForResult(game,0);
		
		
	}
	
	public void harderGameButtonClicked(View arg0) {
		Intent game = new Intent(Menu.this, Game.class);
		MatchesApplication matchesApplication = (MatchesApplication) getApplicationContext();
		matchesApplication.setDifficulty(GameDifficulty.HARDER);
		startActivityForResult(game,0);
		
		
	}
	
	public void rainmanGameButtonClicked(View arg0) {
		Intent game = new Intent(Menu.this, Game.class);
		MatchesApplication matchesApplication = (MatchesApplication) getApplicationContext();
		matchesApplication.setDifficulty(GameDifficulty.RAINMAN);
		startActivityForResult(game,0);
		
		
	}
}
