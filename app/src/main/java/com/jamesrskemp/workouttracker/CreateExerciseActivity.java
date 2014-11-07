package com.jamesrskemp.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.jamesrskemp.workouttracker.db.BodyWeightDataSource;
import com.jamesrskemp.workouttracker.db.ExerciseDataSource;


public class CreateExerciseActivity extends Activity {
	public static final String LOG_TAG = "JAMESRSKEMP";
	ExerciseDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_exercise);

		dataSource = new ExerciseDataSource(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_create_exercise, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		dataSource.open();
	}

	@Override
	protected void onPause() {
		super.onPause();
		dataSource.close();
	}

	public void createNewExercise(View view) {
		EditText editTextExerciseName = (EditText)findViewById(R.id.exercise_create_name);
		EditText editTextExerciseNotes = (EditText)findViewById(R.id.exercise_create_notes);
		CheckBox checkBoxExerciseHasWeight = (CheckBox)findViewById(R.id.exercise_create_has_weight);

		String exerciseName = editTextExerciseName.getText().toString();
		String exerciseNotes = editTextExerciseNotes.getText().toString();
		Boolean exerciseHasWeight = checkBoxExerciseHasWeight.isChecked();

		try {
			if (exerciseName.trim().isEmpty()) {
				return;
			}

			dataSource.open();

			long newExerciseId = dataSource.create(exerciseName, exerciseNotes, exerciseHasWeight);

			Toast.makeText(this, "Exercise created.", Toast.LENGTH_SHORT).show();
			Log.i(LOG_TAG, "Exercise created: " + Long.toString(newExerciseId));

			Intent intent = new Intent(getApplicationContext(), ExercisesActivity.class);
			startActivity(intent);
		}
		catch (Exception ex) {
			Log.i(LOG_TAG, ex.getMessage());
		}
	}
}
