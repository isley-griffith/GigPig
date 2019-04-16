package com.example.gigpig;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.*;

public class Job {
	private Double payout;
	private String description;
	private String jobTitle;
	private boolean isComplete;
	private boolean isTaken;
	private ArrayList<String> tags;
	private String inquirerId;
	private String doerId;
	private LatLng location;
    private Long creationDate;

	public Job() {

    }

	/**
	 *  Job constructor for creating a new job
	 * @param jobTitle The title of the job
	 * @param description Job's description
	 * @param payout Job payout
	 * @param doerId id of inquirer. Comes from FirebaseAuthenticationn of user
	 * @param tags Tags for the job
	 * @param location Location of the job
	 */
	public Job(String jobTitle, String description, String inquirerId, String doerId, Double payout, ArrayList<String> tags, LatLng location) {
		this.jobTitle = jobTitle;
		this.description = description;
		this.payout = payout;
		this.isComplete = false;
		this.doerId = doerId;
		this.isTaken = false;
		this.tags = tags;
		this.inquirerId = inquirerId;
		this.doerId = doerId;
		this.location = location;
		this.creationDate = System.currentTimeMillis();
	}

	/**
	 *  Creates a new location object
	 * @param longitude The longitude for the location
	 * @param latitude The latitude for the location
	 * @return A new Location object instantiated using the longitude and latitude
	 */
	public Location createNewLocation(double longitude, double latitude) {
		Location location = new Location("dummy");
		location.setLongitude(longitude);
		location.setLatitude(latitude);
		return location;
	}

	/**
	 * Get method for the creation date
	 * @return The date the job was created
	 */
    public Long getCreationDate() {
        return creationDate;
    }

	/**
	 * Setter method for the creation date
	 * @param creationDate The creation date of the job
	 */
	public void setCreationDate(Long creationDate) {
	    this.creationDate = creationDate;
    }

	/**
	 * Getter method for the location
	 * @return Returns the job's location
	 */
	public LatLng getLocation() {
		return location;
	}

	/**
	 * Assigns a doer to the job, signaling that the job has been taken by a user
	 * @param doerId The user who has taken the job
	 */
	public void takeJobByDoer(String doerId) {
		this.doerId = doerId;
		this.isTaken = true;
	}

	public void jobDone(){
		this.isComplete = true;
	}

	/**
	 * Setter method for the description
	 * @param description The job description
	 */
	public void setDescription(String description) {
        this.description = description;
    }

	/**
	 * Gets the job title
	 * @return Job title
	 */
	public String getJobTitle() {
        return jobTitle;
    }

	/**
	 * Setter method for the job title
	 * @param jobTitle The title of the job
	 */
	public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }

	public void setPayout(Double payout) {
		this.payout = payout;
	}

	public void setInquirerId(String inquirerId) {
		this.inquirerId = inquirerId;
	}

	public void setDoerId(String doerId) {
		this.doerId = doerId;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

	public String getInquirerId() {
		return inquirerId;
	}

	public String getDoerId() {
		return doerId;
	}

	public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public ArrayList<String> getTags() {
		return tags;
	}

	/**
	 * Determines if the job has already been taken
	 * @return True if yes, false if no
	 */
	public boolean isTaken() {
		return isTaken;
	}

	/**
	 * Determines if the job has been completed
	 * @return True if yes, false if no
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * Marks a job as completed
	 */
	public void jobCompleted() {
		this.isTaken = false;
		this.isComplete = true;
	}

	/**
	 * Adds a tag to the job
	 * @param tag The tag to be added
	 */
	public void addTag(String tag) {
		this.tags.add(tag);
	}



	public String getDescription() { return this.description; }

	public Double getPayout() { return this.payout; }

}

