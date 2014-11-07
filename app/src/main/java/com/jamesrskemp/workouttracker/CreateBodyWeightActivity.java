package com.jamesrskemp.workouttracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jamesrskemp.workouttracker.db.BodyWeightDataSource;


public class CreateBodyWeightActivity extends Activity {
	public static final String LOG_TAG = "JAMESRSKEMP";
	BodyWeightDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_body_weight);

		dataSource = new BodyWeightDataSource(this);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_create_body_weight, menu);
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

	public void recordBodyWeight(View view) {
		EditText editTextBodyWeight = (EditText)findViewById(R.id.edit_body_weight);

		try {
			Long bodyWeight = Long.parseLong(editTextBodyWeight.getText().toString());

			if (bodyWeight > 0) {
				dataSource.open();
				long newBodyWeightId = dataSource.create(bodyWeight);

				Toast.makeText(this, "Body weight id " + newBodyWeightId, Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(getApplicationContext(), BodyWeightHistoryActivity.class);
				startActivity(intent);
			}
		}
		catch (Exception ex) {
			Log.i(LOG_TAG, ex.getMessage());
		}

	}
}
