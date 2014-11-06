package com.jamesrskemp.workouttracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;

/**
 * Created by James on 11/6/2014.
 */
public class BodyWeightDataSource {
	public static final String LOG_TAG = "JAMESRSKEMP";

	DatabaseHelper dbHelper;
	SQLiteDatabase db;

	public BodyWeightDataSource(Context context) {
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

	public long create(long bodyWeight) {
		ContentValues values = new ContentValues();
		values.put(DatabaseHelper.BODY_WEIGHT_COLUMN_WEIGHT, bodyWeight);

		long recordId = db.insert(DatabaseHelper.TABLE_BODY_WEIGHT, null, values);
		Log.i(LOG_TAG, "Body weight recorded: " + recordId);

		return recordId;
	}
}
