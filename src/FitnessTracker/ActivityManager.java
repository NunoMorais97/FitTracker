/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.ActivityAlreadyExistingException;
import FitnessTracker.Exceptions.InvalidMETException;
import FitnessTracker.Exceptions.NonExistingActivityException;

/**
 * Interface does the management of activities, such as adding, removing
 *  and returning  activities
 */
interface ActivityManager {
    
    /**
     * returns the activity object with the specified activityID
     * @param activityID , String activityID
     * @return object Activity
     */
    Activity getActivity(String activityID) throws NonExistingActivityException; 
    
    /**
     * creates a new activity with the specified activityID , MET, and name
     * @param activityID , the string of the activity
     * @param MET , the MET of the Activity
     * @param name , the activity name
     * @throws ActivityAlreadyExistingException , if there is already an Activity with the same name
     * @throws InvalidMETException , if MET is less than 0
     */
    void addActivity(String activityID ,int MET ,String name)throws ActivityAlreadyExistingException , InvalidMETException;
}
