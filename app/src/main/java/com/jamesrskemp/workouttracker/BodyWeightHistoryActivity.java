package com.jamesrskemp.workouttracker;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jamesrskemp.workouttracker.adapters.BodyWeightListAdapter;
import com.jamesrskemp.workouttracker.db.BodyWeightDataSource;
import com.jamesrskemp.workouttracker.models.BodyWeight;

import java.util.List;


public class BodyWeightHistoryActivity extends Activity {
	BodyWeightDataSource dataSource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_body_weight_history);

		dataSource = new BodyWeightDataSource(this);
		dataSource.open();
		List<BodyWeight> weights = dataSource.getBodyWeightHistory();

		ArrayAdapter<BodyWeight> bodyWeightAdapter = new BodyWeightListAdapter(this, weights);

		ListView listView = (ListView)findViewById(R.id.list_body_weight);
		listView.setAdapter(bodyWeightAdapter);
		// TODO on click for modifying items

		/*
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
			    Intent intent = new Intent(getApplicationContext(), ViewJourney.class);
			    intent.putExtra(SELECTED_JOURNEY_ID, ((JourneySelectionViewModel)adapterView.getItemAtPosition(position)).journeyId);
			    startActivity(intent);
		    }
	    });
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_body_weight_history, menu);
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
}
