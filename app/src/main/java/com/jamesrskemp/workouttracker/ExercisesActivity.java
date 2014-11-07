package com.jamesrskemp.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jamesrskemp.workouttracker.adapters.ExerciseListAdapter;
import com.jamesrskemp.workouttracker.db.BodyWeightDataSource;
import com.jamesrskemp.workouttracker.db.ExerciseDataSource;
import com.jamesrskemp.workouttracker.models.Exercise;

import java.util.List;


public class ExercisesActivity extends Activity {
	ExerciseDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercises);

		dataSource = new ExerciseDataSource(this);
		dataSource.open();

		List<Exercise> exercises = dataSource.getExercises();

		ArrayAdapter<Exercise> exerciseAdapter = new ExerciseListAdapter(this, exercises);

		ListView listView = (ListView)findViewById(R.id.list_exercises);
		listView.setAdapter(exerciseAdapter);
		// TODO on click for modifying items
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_exercises, menu);
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

	public void navigateToCreateExercise(View view) {
		Intent intent = new Intent(this, CreateExerciseActivity.class);
		startActivity(intent);
	}
}
