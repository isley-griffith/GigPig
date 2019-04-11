package com.example.gigpig;

public class Job {
	private double payout;
	private String description;
	private String jobTitle;
	private boolean isComplete;
	private boolean isTaken;
	private User doer;
	private User creator;
	
	public Job(String title, String desc, double payout, User creator) {
		this.jobTitle = title;
		this.description = desc;
		this.payout = payout;
		this.isComplete = false;
		this.isTaken = false;
	}
	
	public void takeJob(User doer) {
		this.doer = doer;
		this.isTaken = true;
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
	
	public void rateDoerPerformance(double rating) {
		doer.receivedRating(rating);
	}

}
