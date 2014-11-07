package com.jamesrskemp.workouttracker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jamesrskemp.workouttracker.R;
import com.jamesrskemp.workouttracker.models.BodyWeight;

import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by James on 11/7/2014.
 */
public class BodyWeightListAdapter extends ArrayAdapter<BodyWeight> {
	public BodyWeightListAdapter(Context context, List<BodyWeight> bodyWeights) {
		super(context, 0, bodyWeights);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		BodyWeight item = getItem(position);

		// Check if an existing view is being reused, otherwise inflate the view.
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_body_weight_history, parent, false);
		}

		TextView weight = (TextView)convertView.findViewById(R.id.list_body_weight_weight);
		weight.setText(Long.toString(item.getWeight()));

		TextView date = (TextView)convertView.findViewById(R.id.list_body_weight_date);
		date.setText(item.getDate());

		return convertView;
	}
}
