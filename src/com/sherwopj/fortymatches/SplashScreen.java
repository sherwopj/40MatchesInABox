package com.sherwopj.fortymatches;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
 
    	super.onCreate(savedInstanceState);
    	
        setContentView(R.layout.splash_screen);
        //setGameLevelButtonStates();
    }
    
    @Override
    public void onResume() {
    	
    	super.onResume();
    }
    
	public void playButtonClicked(View arg0) {
		Intent menu = new Intent(SplashScreen.this, Menu.class);
        startActivity(menu);
	}

	public void aboutButtonClicked(View arg0) {
		Intent about = new Intent(SplashScreen.this, AboutActivity.class);
		startActivity(about);
	}
	
	public void exitButtonClicked(View arg0) {
		finish();
	}
	

}
