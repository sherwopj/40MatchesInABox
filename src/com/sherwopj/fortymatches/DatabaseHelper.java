package com.sherwopj.fortymatches;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	static final String dbName="40MatchesDB";
	static final String levelLockTable="levelLocks";
	static final String colID="levelName";
	static final String colLocked="levelLocked";


	public DatabaseHelper(Context context) {
		  super(context, dbName, null,33); 
		  }

	@Override
	public void onCreate(SQLiteDatabase db) {
		  db.execSQL("CREATE TABLE "+levelLockTable+" ("+colID+ " TEXT PRIMARY KEY , "+
				    colLocked+ " INTEGER)");
		  db=this.getWritableDatabase();
		  ContentValues cv=new ContentValues();
		    cv.put(colID, "easy_level_locked");
		    cv.put(colLocked, 0);
		    db.insert(levelLockTable, colID, cv);
		    
		    cv.put(colID, "hard_level_locked");
		    cv.put(colLocked, 1);
		    db.insert(levelLockTable, colID, cv);
		    
		    cv.put(colID, "harder_level_locked");
		    cv.put(colLocked, 1);
		    db.insert(levelLockTable, colID, cv);
		    
		    cv.put(colID, "rainman_level_locked");
		    cv.put(colLocked, 1);
		    db.insert(levelLockTable, colID, cv);

		                      db.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}
	
	public int updateLevelLock(String levelName, boolean lock)
	  {
	   SQLiteDatabase db= this.getWritableDatabase();
	   ContentValues cv=new ContentValues();
	   cv.put(colLocked, (lock)? 1 : 0);
	   return db.update(levelLockTable, cv, colID+"=?", 
	    new String []{levelName});   
	  }
	
	
	
	
	

}
