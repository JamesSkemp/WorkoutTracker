package com.jamesrskemp.workouttracker.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by James on 11/6/2014.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "WorkoutTracker.db";

	//region Body Weight table
	public static final String TABLE_BODY_WEIGHT = "body_weight";
	public static final String BODY_WEIGHT_COLUMN_ID = "_id";
	public static final String BODY_WEIGHT_COLUMN_WEIGHT = "weight";
	public static final String BODY_WEIGHT_COLUMN_DATE = "date";

	public static final String TABLE_BODY_WEIGHT_CREATE = "create table "
			+ TABLE_BODY_WEIGHT + " ("
			+ BODY_WEIGHT_COLUMN_ID + " integer primary key autoincrement, "
			+ BODY_WEIGHT_COLUMN_WEIGHT + " integer NOT NULL, "
			+ BODY_WEIGHT_COLUMN_DATE + " datetime DEFAULT CURRENT_TIMESTAMP"
			+ ");";
	//endregion

	//region Exercises table
	public static final String TABLE_EXERCISE = "exercise";
	public static final String EXERCISE_COLUMN_ID = "_id";
	public static final String EXERCISE_COLUMN_NAME = "name";
	public static final String EXERCISE_COLUMN_NOTES = "notes";
	public static final String EXERCISE_COLUMN_HAS_WEIGHT = "has_weight";

	public static final String TABLE_EXERCISE_CREATE = "create table "
			+ TABLE_EXERCISE + " ("
			+ EXERCISE_COLUMN_ID + " integer primary key autoincrement, "
			+ EXERCISE_COLUMN_NAME + " text NOT NULL, "
			+ EXERCISE_COLUMN_NOTES + " text, "
			+ EXERCISE_COLUMN_HAS_WEIGHT + " boolean DEFAULT 1"
			+ ");";
	//endregion

	public DatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//region Standard SQLite overrides
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("DatabaseHelper", "Creating database");
		// Create the body weight table
		db.execSQL(TABLE_BODY_WEIGHT_CREATE);
		// Create the exercises table
		db.execSQL(TABLE_EXERCISE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO what happens on upgrade?
		db.execSQL("drop table if exists " + TABLE_BODY_WEIGHT);
		db.execSQL("drop table if exists " + TABLE_EXERCISE);

		onCreate(db);
	}
	//endregion
}
