package com.example.gigpig;
import android.location.Location;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Class representing users of our app
 */
public class User{

    private ArrayList<String> tags;
    private String bio;
    private ArrayList<Job> userJobs;
    private ArrayList<Job> pastJobs;
    private Job userTakenJob;
    private boolean userHasJob;

    private String firstName;
    private String lastName;
    private String phoneNum;
    private String username;
    private String password;
    
    private static final int MAX_USER_JOBS = 5;
    private static final int MAX_BIO_LENGTH = 500;
    private Location dummyLoc = new Location("dummy");

    private String uId;

    /**
     * Create a new user
     */
    public User(){
//    	this.bio = "no bio rn";
//        this.userJobs = new ArrayList<Job>();
//        this.pastJobs = new ArrayList<Job>();
//        this.userHasJob = false;
//        this.tags = new ArrayList<String>();
    }


    /**
     * User constructor
     * @param uId the unique ID associated with the user
     * @param firstname user's first name
     * @param lastname user's last name
     * @param phonenumber user's phone number
     * @param username user's username
     * @param tags the tags the user would be interested in
     * @param bio a bio describing the user so that others may review
     */
    public User(String uId, String firstname, String lastname, String phonenumber, String username, ArrayList<String> tags, String bio) {
        this.uId = uId;
        this.firstName = firstname;
        this.lastName = lastname;
        this.phoneNum = phonenumber;
        this.username = username;
        this.tags = tags;
        this.bio = bio;
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

    /**
     * Add a tag to the user
     * @param tag the tag to add
     */
    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public ArrayList<String> getTags() {
        return tags;
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
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
    		job.takeJobByDoer(mAuth.getUid());
    		this.userTakenJob = job;
    		this.userHasJob = true;
    		return true;
    	}
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public void jobCompleted() {
    	userJobs.remove(userTakenJob);
    	userTakenJob.jobCompleted();
    	this.userHasJob = false;
    	pastJobs.add(userTakenJob);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
    

}
