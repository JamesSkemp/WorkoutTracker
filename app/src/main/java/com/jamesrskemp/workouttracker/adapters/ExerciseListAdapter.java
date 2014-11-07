package com.jamesrskemp.workouttracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jamesrskemp.workouttracker.R;
import com.jamesrskemp.workouttracker.models.Exercise;

import java.util.List;

/**
 * Created by James on 11/7/2014.
 */
public class ExerciseListAdapter extends ArrayAdapter<Exercise> {
	public ExerciseListAdapter(Context context, List<Exercise> exercises) {
		super(context, 0, exercises);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Exercise item = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view.
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_exercises, parent, false);
		}

		TextView weight = (TextView)convertView.findViewById(R.id.list_exercise_name);
		weight.setText(item.getName());

		TextView date = (TextView)convertView.findViewById(R.id.list_exercise_notes);
		if (item.getNotes().isEmpty()) {
			date.setVisibility(View.GONE);
		} else {
			date.setText(item.getNotes());
		}

		return convertView;
	}
}
