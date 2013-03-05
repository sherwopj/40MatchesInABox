package com.sherwopj.fortymatches;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class Game extends Activity {
	//private GamePanel gamePanel;
	private Intent gamePanelActivity;
	private int correctCount;
	private int gameCount;
	private int NUMBER_OF_ROUNDS = 3;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        gameCount = 0;
    	correctCount = 0;
        //gamePanel = new GamePanel(this,width,height);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
    }

    @Override
    protected void onStart() {
    	Log.i(this.getLocalClassName(),"Starting....: ");
    	super.onStart();
    }
    

    @Override
    protected void onResume() {
    	Display display = getWindowManager().getDefaultDisplay();
    	int width = display.getWidth();
    	int height = display.getHeight();
    	Log.i(this.getLocalClassName(),"Resuming...: ");
    	Log.d(this.getLocalClassName(), "width: "+width+" height: "+height);
    	if(gameCount<NUMBER_OF_ROUNDS) {
    		setContentView(R.layout.game);
    		gamePanelActivity = new Intent(Game.this, GamePanelActivity.class);
    		gamePanelActivity.putExtra("width", width);
    		gamePanelActivity.putExtra("height", height);
    		startActivityForResult(gamePanelActivity,0);
    	} else {
    		
    		setContentView(R.layout.score);
    		View goldStarLevelUnlockedView = findViewById(R.id.goldStarLevelUnlocked);
    		goldStarLevelUnlockedView.setVisibility(View.INVISIBLE);
    		View parentView = findViewById(R.id.scoreView);
    		parentView.setOnTouchListener(new OnTouchListener() {
    			
    			
    			public boolean onTouch(View arg0, MotionEvent arg1) {
    				Log.d("Game", "parentView Clicked ");
    				
    				finish();
    				return false;
    			}
    		});
    		TextView score = (TextView) findViewById(R.id.score);
    		int scorePercent = (correctCount*100)/gameCount;
			score.setText(scorePercent+"%");
			
			if(scorePercent > 90) {
				Log.d("Game", "About to attempt to unlock the next level...");
				goldStarLevelUnlockedView.setVisibility(View.VISIBLE);
				unlockNextLevel();
			}
			
    		
    		//finish();	
    	}
    	super.onResume();
    	
    	
    }

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.w(this.getLocalClassName(), "onConfigurationChanged...: ");
	    //UIActivity();
	}

	public void tryAgainButtonClicked(View view){
    	Log.d(this.getLocalClassName(), "tryAgainButtonClicked");
		
        onCreate(null);
        
    }
    
    public void menuButtonClicked(View view){
    	Log.d(this.getLocalClassName(), "tryAgainButtonClicked");
		
        finish();
        
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	
		Log.i(this.getLocalClassName(), "Got a result back from GamePanelActivity gameCount: "+gameCount);
		
        
        if(resultCode == 1) {
        	correctCount++;
        }
        gameCount++;
        
        Log.i(this.getLocalClassName(), "correctCount: "+correctCount);
    }

    private void unlockNextLevel() {
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
    	
		MatchesApplication matchesApplication = (MatchesApplication) getApplicationContext();
		GameDifficulty difficulty = matchesApplication.getDifficulty();
		switch (difficulty) {
		case EASY:
			SP.edit().putBoolean("hard_level_unlocked", true).commit();
			break;
		case HARD:
			SP.edit().putBoolean("harder_level_unlocked", true).commit();
			break;
		case HARDER:
			SP.edit().putBoolean("rainman_level_unlocked", true).commit();
			break;
		case RAINMAN:
			Log.d(this.getLocalClassName(), "There is no next button to unlock");
			SP.edit().putBoolean("all_levels_completed", true).commit();
			break;

		default:
			Log.e(this.getLocalClassName(), "Unknown diffculty level...");
			break;
		}

    	
    }
}