package com.example.gigpig;
import android.location.Location;

import java.util.*;

public class Job {
	private double payout;
	private String description;
	private String jobTitle;
	private boolean isComplete;
	private boolean isTaken;
	private User doer;
	private User creator;
	private Location location;
	private ArrayList<String> tags;
	private double latitude;
	private double longitude;

	public Job(String title, String desc, double payout, User creator, ArrayList<String> tags, Location location) {
		this.jobTitle = title;
		this.description = desc;
		this.payout = payout;
		this.isComplete = false;
		this.isTaken = false;
		this.tags = tags;
		this.location = location;
	}

	public Location createNewLocation(double longitude, double latitude) {
		Location location = new Location("dummy");
		location.setLongitude(longitude);
		location.setLatitude(latitude);
		return location;
	}

	public Location getLocation() {
		return location;
	}




	public void takeJob(User doer) {
		this.doer = doer;
		this.isTaken = true;
	}



	public ArrayList<String> getTags() {
		return tags;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void jobCompleted() {
		this.isTaken = false;
		this.isComplete = true;
	}

	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public void rateDoerPerformance(double rating) {
		doer.receivedRating(rating);
	}

	public String getTitle() {
		return jobTitle;
	}

	public String getDescription() { return this.description; }

	public double getPayout() { return this.payout; }

}

