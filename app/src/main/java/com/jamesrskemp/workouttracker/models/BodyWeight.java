package com.jamesrskemp.workouttracker.models;

import java.sql.Date;

/**
 * Created by James on 11/7/2014.
 */
public class BodyWeight {
	private long id;
	private long weight;
	private String date;

	//region Standard Getters and Setters
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getWeight() {
		return weight;
	}

	public void setWeight(long weight) {
		this.weight = weight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	//endregion

	public BodyWeight() {
	}
}
