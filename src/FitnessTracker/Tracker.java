/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;

/**
 * does the general management of the fitness tracker
 *  has a group manager and an athlete manager
 */
public interface Tracker {

    /**
     * Inserts a new Athlete into the system
     * @param athleteID the ID that the athlete is recognized for
     * @param weight the weight in kilos
     * @param height the height in cm
     * @param sex (Either "F" or "M")
     * @param name real name of the athlete
     * @param age , age, in years
     * @throws InvalidValuesException if height weight or age are negative
     */
    void insertAthlete(String athleteID,int weight,int height,int age,char sex, String name) throws InvalidValuesException;


    /**
     * Changes the information associated with an athlete,
     * all the information inserted will change the athlete's statistics,
     * the name and sex of the athlete can't be changed
     * if age or height or weight are negative throws an exception
     * @param athleteID the ID that the athlete is recognized for
     * @param weight , the weight in kilos
     * @param height, the height in cm
     * @param age, age, in years
     * @throws InvalidValuesException if any of the values are negative
     */
    void changeAthleteInfo(String athleteID,int weight, int height, int age) throws InvalidValuesException;


    /**
     * Removes the athlete associated with the @param athleteID,
     * this changes values relative to the groups the athlete belonged to
     * also removes the athlete from his group, if it existed
     * @param athleteID  the ID that the athlete is recognized for
     */
    void removeAthlete (String athleteID);


    /**
     * returns an object  of type Athlete with the specified athleteID
     * @param athleteID  the ID that the athlete is recognized for
     * @return Athlete object
     */
    Athlete consultAthlete(String athleteID) ;


    /**
     * Creates an activity with where it's ID is the @param activityID
     * if successful, more athletes will be able to join the group and add workouts related to the activity.
     * @param activityID the ID that the athlete is recognized for
     * @param MET,  used to  do calculations related to the calorie expenditure in the ahtlete's workouts
     * @param name the name of the activity, such as "running"
     */
    void createActivity ( String activityID, int MET, String name );


    /**
     * Adds a new workout to the athlete with id == @athleteID
     *
     * @param athleteID  the ID that the athlete is recognized for
     * @param activityID the ID that the activity is recognized for
     * @param duration , duration of the workout, in hours
     * @throws InvalidWorkoutTimeException , if the workout time is negative
     */
    void addWorkout (String athleteID ,String activityID , int duration ) throws InvalidWorkoutTimeException;


    /**
     *  /**
     * returns an iterator of workouts
     * the order of iteration depends on the argumment
     * if type == 'T', the iteration will be made by decreasing order of calories
     * if type == 'C', the iteration will be made by chronological order
     * @param athleteID the ID that the athlete is recognized for
     * @param type the type of iteration
     * @return Iterator of workouts
     * @throws HasNoWorkoutsException , if the athlete has not completed any workouts
     */
    Iterator<Workout> ListWorkouts(String athleteID, char type) throws InvalidTypeException ;


    /**
     * Adds the amount of steps specified to the athlete's step counter
     * if the user has a group, increments the group's step counter
     * @param athleteID the ID that the athlete is recognized for
     * @param steps, the steps to add
     * @throws InvalidStepsException if the steps are negative
     */
    void addSteps(String athleteID, int steps) throws InvalidStepsException;


    /**
     * Creates a group of athletes
     * Operation will only have success if groupID does no exist in the system
     * @param groupID, the ID that the group will be recognized for
     * @param group_name the real name of the group
     * @return Group object
     */
    void createGroup(String groupID, String group_name);


    /**
     * Joins an athlete to a group, if successful,
     * group totals will be updated with the athlete's statistics (calorie count and steps)
     * @param athleteID the ID that the athlete is recognized for
     * @param groupID the ID that the group will be recognized for
     * @throws AlreadyHasGroupException
     */
    void joinGroup (String athleteID,String groupID) throws AlreadyHasGroupException;


    /**
     * Removes the athlete with athleteID from the specified group, this means all calories burned and steps done by the athlete will be removed from the group, wich may result in a change of group ranking
     * @param athleteID the ID that the athlete is recognized for
     * @param groupID the ID that the group will be recognized for
     */
    void leaveGroup(String athleteID,String  groupID);


    /**
     * returns the group with the specified groupID
     * @param groupID the ID that the group  is recognized for
     * @return group object
     */
    Group consultGroup(String groupID) ;

    /**
     * returns the Iterator of athletes in the group with groupID
     * @param groupID the ID that the group will be recognized for
     * @return object iterator
     * @throws EmptyGroupException
     */
    Iterator<Entry<String,Athlete>> listGroup(String groupID) throws EmptyGroupException;

    /**
     * returns an iterator of groups ordered in decreasing  order of steps taken
     * @return Object of type group (1st phase)
     */
    Iterator<Group>  ListGroupsBySteps();


    /**
     * Returns an iterator of groups ordered in decreasing order of calories burned
     * @return Object Group ( 1st phase)
     */
    Iterator<Group> ListGroupsByCalories();




}
