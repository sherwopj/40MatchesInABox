package com.sherwopj.fortymatches;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class GameActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    
    public void readyButtonClicked(View view){
    	Log.d(this.getLocalClassName(), "readyButtonClicked");
    	
    }
}