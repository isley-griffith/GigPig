package com.example.gigpig;
import java.util.ArrayList;

public class User{

    private String firstName;
    private String lastName;
    private String phoneNum;
    private String username;
    private String password;
    private ArrayList<String> tags;
    private String bio;
    private ArrayList<String> reviews;
    private ArrayList<Job> userJobs;
    private ArrayList<Job> pastJobs;
    private Job userTakenJob;
    private boolean userHasJob;
    
    private ArrayList<Double> ratings;
    private static double overallUserRating;
    private static int numberOfRatings;
    
    private static final int MAX_USER_JOBS = 5;
    private static final int MAX_BIO_LENGTH = 500;

    /**
     * Create a new user
     */
    public User(){
    	this.bio = "no bio rn";
        this.userJobs = new ArrayList<Job>();
        this.pastJobs = new ArrayList<Job>();
        this.ratings = new ArrayList<Double>();
        this.userHasJob = false;
        this.reviews = new ArrayList<String>();
        overallUserRating = 5;
        numberOfRatings = 0;
        this.tags = new ArrayList<String>();
    	//TODO: Figure out what might be necessary here
    }
    
    private boolean checkNum() {
    	//TODO: Some sort of way to check if the number has already been registered to an existing account
    	return true;
    }
    
    public void receivedRating(double rate) {
    	if (numberOfRatings == 0) {
    		ratings.add(rate);
    		numberOfRatings++;
    		overallUserRating = rate;
    	} else {
    		double newRate = rate;
    		numberOfRatings++;
    		for (double pastRating: ratings) {
    			newRate += pastRating;
    		}
    		overallUserRating = newRate/numberOfRatings;
    	}
    }
    
    public String getRating() {
    	return String.format("%.2f", overallUserRating);
    }
    
    public int getNumberOfRatings() {
    	return numberOfRatings;
    }

    /**
     * Creates a new user. No password
     * @param fName First name of the user
     * @param lName Last name of the user
     * @param phoneNum User's phone number
     */
    public void createAccount(String fName, String lName, String phoneNum){
    	//TODO: Implement checkNum
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNum = phoneNum;
    }

    /** Edits the user's bio
     * @param newBio The user's new bio
     * @return 0 if the bio is greater than 500 characters(edit unsuccessful), 1 if bio meets requirements
     */
    public int editBio(String newBio){
        if (newBio.length() > MAX_BIO_LENGTH){
            return 0;
        } else {
            this.bio = newBio;
            return 1;
        }
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    /** Creates a new job
     * @param jobName Job name
     * @param description Job description
     * @param pay Job payout
     * @return 0 if description error (over 500 char), 2 if max jobs error (5 or more jobs active), 3 if 
     * pay error (pay less than $1.00), 1 if successful;
     */
    public int createJob(String jobName, String description, double pay){
    	if (description.length() > MAX_BIO_LENGTH ) {
    		return 0;
    	} else if (userJobs.size() >= MAX_USER_JOBS) {
    		return 2;
    	} else if (pay < 1) {
    		return 3;
    	} else {
    		Job newJob = new Job(jobName, description, pay, this, tags);
    		this.userJobs.add(newJob);					// Adds job to user's job offerings
    		return 1;
    	}
    }
    
    /** Allows the user to take a job. Will not allow the job to be taken if the job is already
     * taken or if the user already has an active job
     * @param job The job the user wants to take
     * @return True if job was successfuly taken by user, false otherwise
     */
    public boolean takeJob(Job job) {
    	if (job.isTaken() || userHasJob || job.isComplete()) {
    		return false;
    	} else {
    		job.takeJob(this);
    		this.userTakenJob = job;
    		this.userHasJob = true;
    		return true;
    	}
    }
    
    public String viewCurrentJob() {
    	return "";
    	//TODO: find someway to display a user's current job, Job toString
    }
    
    public void jobCompleted() {
    	userJobs.remove(userTakenJob);
    	userTakenJob.jobCompleted();
    	this.userHasJob = false;
    	pastJobs.add(userTakenJob);
    }
    
    

}
