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
    private Date creationDate;

	public Job() {

    }

	public Job(String jobTitle, String description, double payout, User creator, ArrayList<String> tags, Location location) {
		this.jobTitle = jobTitle;
		this.description = description;
		this.payout = payout;
		this.isComplete = false;
		this.isTaken = false;
		this.tags = tags;
		this.location = location;
		this.creationDate = new Date();
	}

	public Location createNewLocation(double longitude, double latitude) {
		Location location = new Location("dummy");
		location.setLongitude(longitude);
		location.setLatitude(latitude);
		return location;
	}
    public Date getDate() {
        return creationDate;
    }

	public Location getLocation() {
		return location;
	}


	public void takeJob(User doer) {
		this.doer = doer;
		this.isTaken = true;
	}

    public void setPayout(double payout) {
        this.payout = payout;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    public User getDoer() {
        return doer;
    }

    public void setDoer(User doer) {
        this.doer = doer;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
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

