package com.jamesrskemp.workouttracker.models;

/**
 * Created by James on 11/7/2014.
 */
public class Exercise {
	private Long id;
	private String name;
	private String notes;
	private Boolean hasWeight;

	//region Standard getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Boolean getHasWeight() {
		return hasWeight;
	}

	public void setHasWeight(Boolean hasWeight) {
		this.hasWeight = hasWeight;
	}
	//endregion

	public Exercise() {
	}
}
