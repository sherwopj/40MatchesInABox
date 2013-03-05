package com.sherwopj.fortymatches;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class MatchesApplication extends Application {
	private static MatchesApplication singleton;

	private GameDifficulty difficulty;
	
	public MatchesApplication getInstance(){
		return singleton;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		singleton = this;
    	PreferenceManager.setDefaultValues(this, R.xml.preference, true);
    	SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(this);
    	
    	Log.d("MatchesApplication", "SP.getAll().size() :"+SP.getAll().size());
    	Log.d("MatchesApplication",SP.getAll().keySet().toString());

		
	}
	
	protected GameDifficulty getDifficulty() {
		return difficulty;
	}

	protected void setDifficulty(GameDifficulty difficulty) {
		this.difficulty = difficulty;
	}
	
}
