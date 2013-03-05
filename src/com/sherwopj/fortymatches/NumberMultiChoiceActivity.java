package com.sherwopj.fortymatches;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class NumberMultiChoiceActivity extends Activity {

	private Button button1;
	private Button button2;
	private Button button3;
	private Button button4;
	private int correctAnswer;
	private SharedPreferences SP;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        Log.i(this.getLocalClassName(),"Creating...: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_multi_choice);
	}
	
	@Override
	public void onResume() {
		SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        Log.i(this.getLocalClassName(),"Creating...: ");
        super.onResume();
        setContentView(R.layout.number_multi_choice);
        correctAnswer = getIntent().getIntExtra("numberOfImagesDisplayed", 0);
        List<Integer> numbersToPutOnTheButtons = new ArrayList<Integer>();
        numbersToPutOnTheButtons = buildSubsetOfAnswersAround(correctAnswer, 4);
        
        button1 = (Button) this.findViewById(R.id.button1);
        button2 = (Button) this.findViewById(R.id.button2);
        button3 = (Button) this.findViewById(R.id.button3);
        button4 = (Button) this.findViewById(R.id.button4);
        
        button1.setText((CharSequence) Integer.toString((Integer) numbersToPutOnTheButtons.get(0)));
        button2.setText((CharSequence) Integer.toString((Integer) numbersToPutOnTheButtons.get(1)));
        button3.setText((CharSequence) Integer.toString((Integer) numbersToPutOnTheButtons.get(2)));
        button4.setText((CharSequence) Integer.toString((Integer) numbersToPutOnTheButtons.get(3)));
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.w(this.getLocalClassName(), "onConfigurationChanged...: ");
	    setResult(-1);
	    finish();
	    //UIActivity();
	}

	private List<Integer> buildSubsetOfAnswersAround(int correctAnswer, int numberOfButtonsRequired) {
    	int listLength = (2*numberOfButtonsRequired) - 1;
		List<Integer> integersAroundMyCorrectAnswer = new ArrayList<Integer>();
		//(2*numberOfButtonsRequired) - 1
		int lowerBound = correctAnswer-(numberOfButtonsRequired-1);
		Log.d(this.getLocalClassName(), "lowerBound: "+lowerBound);
		for (int i = 0; i < listLength; i++) {
			integersAroundMyCorrectAnswer.add(lowerBound + i);
		}
		Log.d(this.getLocalClassName(), "integersAroundMyCorrectAnswer: "+integersAroundMyCorrectAnswer);

		Random randomGenerator = new Random();
		int startingPostionOfRandomSubList = randomGenerator.nextInt(numberOfButtonsRequired);
		integersAroundMyCorrectAnswer = integersAroundMyCorrectAnswer.subList(startingPostionOfRandomSubList, startingPostionOfRandomSubList+numberOfButtonsRequired);
		Log.d(this.getLocalClassName(), "integersAroundMyCorrectAnswer: "+integersAroundMyCorrectAnswer);
		
		
		return integersAroundMyCorrectAnswer;
	}





	public void button1Clicked(View view){
    	Log.d(this.getLocalClassName(), "button1Clicked");
    	int numberOnTheButton = Integer.parseInt((String) button1.getText());

    	if(numberOnTheButton == correctAnswer) {
    		button1.setBackgroundColor(Color.GREEN);
    		incrementTotalNumberOfCorrectGuesses(SP);
    		
    	} else {
    		button1.setBackgroundColor(Color.RED);
    		incrementTotalNumberOfIncorrectGuesses(SP);
    	}
    	
    	Intent intent = new Intent();
   	 	setResult(numberOnTheButton,intent);
   	 	finish();
   	 	Log.i(this.getLocalClassName(), "finished");
    }

	public void button2Clicked(View view){
    	Log.d(this.getLocalClassName(), "button2Clicked");
    	int numberOnTheButton = Integer.parseInt((String) button2.getText());
    	
    	if(numberOnTheButton == correctAnswer) {
    		button2.setBackgroundColor(Color.GREEN);
    		incrementTotalNumberOfCorrectGuesses(SP);
    	} else {
    		button2.setBackgroundColor(Color.RED);
    		incrementTotalNumberOfIncorrectGuesses(SP);
    	}
    	
    	Intent intent = new Intent();
   	 	setResult(numberOnTheButton,intent);
   	 	Log.i(this.getLocalClassName(), "finished");
    	finish();
    }
    public void button3Clicked(View view){
    	Log.d(this.getLocalClassName(), "button3Clicked");
    	int numberOnTheButton = Integer.parseInt((String) button3.getText());
    	
    	if(numberOnTheButton == correctAnswer) {
    		button3.setBackgroundColor(Color.GREEN);
    		incrementTotalNumberOfCorrectGuesses(SP);
    	} else {
    		button3.setBackgroundColor(Color.RED);
    		incrementTotalNumberOfIncorrectGuesses(SP);
    	}
    	
    	Intent intent = new Intent();
   	 	setResult(numberOnTheButton,intent);
   	 	Log.i(this.getLocalClassName(), "finished");
    	finish();
    }
    public void button4Clicked(View view){
    	Log.d(this.getLocalClassName(), "button4Clicked");
    	int numberOnTheButton = Integer.parseInt((String) button4.getText());
    	
    	if(numberOnTheButton == correctAnswer) {
    		button4.setBackgroundColor(Color.GREEN);
    		incrementTotalNumberOfCorrectGuesses(SP);
    	} else {
    		button4.setBackgroundColor(Color.RED);
    		incrementTotalNumberOfIncorrectGuesses(SP);
    	}
    	
    	Intent intent = new Intent();
   	 	setResult(numberOnTheButton,intent);
   	 	Log.i(this.getLocalClassName(), "finished");
    	finish();
    }
    
    private void incrementTotalNumberOfCorrectGuesses(SharedPreferences SP) {
    	int totalNumberOfCorrectGuesses = Integer.valueOf(SP.getString("total_number_of_correct_guesses", "0"));
    	SP.edit().putString("total_number_of_correct_guesses", String.valueOf(totalNumberOfCorrectGuesses + 1)).commit();
    }
    
    private void incrementTotalNumberOfIncorrectGuesses(SharedPreferences SP) {
    	int totalNumberOfIncorrectGuesses = Integer.valueOf(SP.getString("total_number_of_incorrect_guesses", "0"));
    	SP.edit().putString("total_number_of_incorrect_guesses", String.valueOf(totalNumberOfIncorrectGuesses + 1)).commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
        	Intent a = new Intent(this,Menu.class);
            a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(a);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}

