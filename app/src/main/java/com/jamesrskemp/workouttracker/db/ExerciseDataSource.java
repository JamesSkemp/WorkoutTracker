package com.jamesrskemp.workouttracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jamesrskemp.workouttracker.models.Exercise;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by James on 11/7/2014.
 */
public class ExerciseDataSource {
	public static final String LOG_TAG = "JAMESRSKEMP";

	DatabaseHelper dbHelper;
	SQLiteDatabase db;

	public ExerciseDataSource(Context context) {
		dbHelper = new DatabaseHelper(context);
	}

	public void open() {
		db = dbHelper.getWritableDatabase();
		Log.i(LOG_TAG, "Database opened");
	}

	public void close() {
		db.close();
		Log.i(LOG_TAG, "Database closed");
	}

	public long create(String name) {
		return create(name, null, true);
	}

	public long create(String name, String notes) {
		return create(name, notes, true);
	}

	public long create(String name, String notes, Boolean hasWeight) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.EXERCISE_COLUMN_NAME, name);
		values.put(DatabaseHelper.EXERCISE_COLUMN_NOTES, notes);
		values.put(DatabaseHelper.EXERCISE_COLUMN_HAS_WEIGHT, hasWeight);

		long recordId = db.insert(DatabaseHelper.TABLE_EXERCISE, null, values);
		Log.i(LOG_TAG, "New exercise created: " + recordId);

		return recordId;
	}

	public List<Exercise> getExercises() {
		List<Exercise> exercises = new ArrayList<Exercise>();

		String exercisesListSelectQuery = "SELECT "
				+ DatabaseHelper.TABLE_EXERCISE + "." + DatabaseHelper.EXERCISE_COLUMN_ID + ", "
				+ DatabaseHelper.TABLE_EXERCISE + "." + DatabaseHelper.EXERCISE_COLUMN_NAME + ", "
				+ DatabaseHelper.TABLE_EXERCISE + "." + DatabaseHelper.EXERCISE_COLUMN_NOTES + " "
				+ "FROM " + DatabaseHelper.TABLE_EXERCISE
				+ " ORDER BY " + DatabaseHelper.TABLE_EXERCISE + "." + DatabaseHelper.EXERCISE_COLUMN_NAME
				+ ";";

		Cursor cursor = db.rawQuery(exercisesListSelectQuery, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				Exercise exercise = new Exercise();
				exercise.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.EXERCISE_COLUMN_ID)));
				exercise.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXERCISE_COLUMN_NAME)));
				exercise.setNotes(cursor.getString(cursor.getColumnIndex(DatabaseHelper.EXERCISE_COLUMN_NOTES)));
				exercise.setHasWeight(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.EXERCISE_COLUMN_HAS_WEIGHT)) == 1);
				exercises.add(exercise);
			}
		}
		return exercises;
	}
}
