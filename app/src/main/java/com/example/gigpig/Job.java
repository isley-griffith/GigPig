package com.example.gigpig;
import java.util.*;

public class Job {
	private double payout;
	private String description;
	private String jobTitle;
	private boolean isComplete;
	private boolean isTaken;
	private User doer;
	private User creator;

	private ArrayList<String> tags;

	public Job(String title, String desc, double payout, User creator, ArrayList<String> tags) {
		this.jobTitle = title;
		this.description = desc;
		this.payout = payout;
		this.isComplete = false;
		this.isTaken = false;
		this.tags = tags;
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

