package com.jamesrskemp.workouttracker.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.jamesrskemp.workouttracker.models.BodyWeight;

import java.util.ArrayList;
import java.util.List;

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

	public List<BodyWeight> getBodyWeightHistory() {
		List<BodyWeight> weights = new ArrayList<BodyWeight>();

		String bodyWeightListSelectQuery = "SELECT "
				+ DatabaseHelper.TABLE_BODY_WEIGHT + "." + DatabaseHelper.BODY_WEIGHT_COLUMN_ID + ", "
				+ DatabaseHelper.TABLE_BODY_WEIGHT + "." + DatabaseHelper.BODY_WEIGHT_COLUMN_WEIGHT + ", "
				+ DatabaseHelper.TABLE_BODY_WEIGHT + "." + DatabaseHelper.BODY_WEIGHT_COLUMN_DATE + " "
				+ "FROM " + DatabaseHelper.TABLE_BODY_WEIGHT
				+ " ORDER BY " + DatabaseHelper.TABLE_BODY_WEIGHT + "." + DatabaseHelper.BODY_WEIGHT_COLUMN_DATE + " DESC"
				+ ";";

		Cursor cursor = db.rawQuery(bodyWeightListSelectQuery, null);
		if (cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				BodyWeight bodyWeight = new BodyWeight();
				bodyWeight.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.BODY_WEIGHT_COLUMN_ID)));
				bodyWeight.setWeight(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.BODY_WEIGHT_COLUMN_WEIGHT)));
				bodyWeight.setDate(cursor.getString(cursor.getColumnIndex(DatabaseHelper.BODY_WEIGHT_COLUMN_DATE)));

				weights.add(bodyWeight);
			}
		}
		return weights;
	}
}
