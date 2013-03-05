package com.sherwopj.fortymatches;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class GamePanelActivity extends Activity {
	private GamePanel gamePanel;

	private TextView gameTextView;

	private CountDownTimer gamePanelTimer;

	private CountDownTimer countDownTimer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(this.getLocalClassName(), "Creating...: ");
		setContentView(R.layout.countdown);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		Log.w(this.getLocalClassName(), "onConfigurationChanged...: ");
		if (gamePanelTimer != null) {
			gamePanelTimer.cancel();
		}

		if (countDownTimer != null) {
			countDownTimer.cancel();
		}
	    setResult(-1);
	    finish();
	    //UIActivity();
	}

	@Override
	protected void onResume() {
		Log.i(this.getLocalClassName(), "Resuming...: ");
		int width = getIntent().getIntExtra("width", 0);
		int height = getIntent().getIntExtra("height", 0);
		MatchesApplication matchesApplication = (MatchesApplication) getApplicationContext();
		GameDifficulty difficulty = matchesApplication.getDifficulty();
		Log.d(this.getLocalClassName(), "width: " + width + " height: " + height);
		gamePanel = new GamePanel(getBaseContext(), width, height, difficulty);

		setContentView(R.layout.countdown);

		displayCountDown();

		super.onResume();

	}

	private void displayGamePanel() {
		setContentView(gamePanel);
		gamePanelTimer = new CountDownTimer(2500, 1000) {

			public void onTick(long millisUntilFinished) {
				Log.d("GamePanelActivity", "seconds remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {
				Intent numberChoicesActivity = new Intent(GamePanelActivity.this, NumberMultiChoiceActivity.class);
				numberChoicesActivity.putExtra("numberOfImagesDisplayed", gamePanel.getNumberOfImagesPerPage());
				startActivityForResult(numberChoicesActivity, 0);

			}
		}.start();
	}

	private void displayCountDown() {
		gameTextView = (TextView) findViewById(R.id.progressText);
		gameTextView.setText(Integer.toString(3));

		countDownTimer = new CountDownTimer(3000, 500) {

			public void onTick(long millisUntilFinished) {
				String secondsToGo = Integer.toString(Math.round(millisUntilFinished / 1000) + 1);
				gameTextView.setText(secondsToGo);
				Log.d("Game.displayCountDown", "seconds remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {
				displayGamePanel();
				cancel();
			}
		}.start();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.d(this.getLocalClassName(), "numberChoicesActivity result: " + resultCode);

		Intent intent = new Intent();

		if (resultCode == gamePanel.getNumberOfImagesPerPage()) {
			resultCode = 1;
		} else
			resultCode = 0;

		setResult(resultCode, intent);
		Log.i(this.getLocalClassName(), "finished");
		finish();

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			if (gamePanelTimer != null) {
				gamePanelTimer.cancel();
			}

			if (countDownTimer != null) {
				countDownTimer.cancel();
			}
			Intent a = new Intent(this, Menu.class);
			a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(a);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
