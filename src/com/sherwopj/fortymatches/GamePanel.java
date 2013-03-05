package com.sherwopj.fortymatches;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

public class GamePanel extends View {

	private int width;
	private int height;
	private int numberOfImagesPerPage;
	private List<Bitmap> imageList;

	public GamePanel(Context context, int width, int height, GameDifficulty difficulty) {
		super(context);
		this.width = width - 40;
		this.height = height - 100;
		Log.d("GamePanel", "in GamePanel GameDifficulty: " + difficulty);
		imageList = new ArrayList<Bitmap>();
		switch (difficulty) {
		case EASY:
			numberOfImagesPerPage = getNumberOfImagesToDisplay(5, 12);
			
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.ladybird_small1));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.ladybird_small2));
			break;

		case HARD:
			numberOfImagesPerPage = getNumberOfImagesToDisplay(10, 20);
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.egg_small1));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.egg_small2));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.egg_small3));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.egg_small4));
			break;

		case HARDER:
			numberOfImagesPerPage = getNumberOfImagesToDisplay(15, 25);
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.penny_small1));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.penny_small2));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.penny_small3));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.penny_small4));
			break;

		case RAINMAN:
			this.height = height - 130;
			numberOfImagesPerPage = getNumberOfImagesToDisplay(20, 40);
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small1));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small2));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small3));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small4));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small5));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small6));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small7));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small8));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small9));
			imageList.add(BitmapFactory.decodeResource(getResources(), R.drawable.match_small10));
			break;

		default:
			Log.d("GamePanel", "No GameDifficulty defined: " + difficulty);
			break;
		}

	}

	@Override
	public void onDraw(Canvas canvas) {
		//Bitmap pic2 = BitmapFactory.decodeResource(getResources(),R.drawable.ladybird_small);
		canvas.drawColor(Color.WHITE);
		for (int i = 0; i < numberOfImagesPerPage; i++) {
			//Matrix transform = new Matrix();
			//transform.preRotate((float) (Math.random()*360));
			//transform.postTranslate((float) Math.random()*width, (float) Math.random()*height);
			canvas.drawBitmap(getRandomImage(),(float) Math.random()*width, (float) Math.random()*height, null);
			//canvas.drawBitmap(image, transform, null);
		}
		
		
	}

	private Bitmap getRandomImage() {
		int imageIndex = (int) (Math.random()*imageList.size());
		Log.d("GamePanel", "imageIndex: " + imageIndex);
		return imageList.get(imageIndex);
	}

	public int getNumberOfImagesPerPage() {
		return numberOfImagesPerPage;
	}

	private int getNumberOfImagesToDisplay(int minNumberOfImagesToBeDisplayed, int maxNumberOfImagesToBeDisplayed) {

		numberOfImagesPerPage = minNumberOfImagesToBeDisplayed
				+ (int) (Math.random() * ((maxNumberOfImagesToBeDisplayed - minNumberOfImagesToBeDisplayed) + 1));

		return numberOfImagesPerPage;
	}

}
