/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

/**
 * this interface contains getters and setters to the MET, name and ID of the activity
 * activities are things such as "running" "walking"
 * activities define the caloric expenditure of a workout (depends of the workout time)
 */
public interface Activity {


    /**
     * returns the name of the activity
     *
     * @return String activity
     */
    String getName();


    /**
     * returns the MET of the activity, this is used to calculate
     * the calories spent by someone who did this activity
     *
     * @return integer MET
     */
    int getMET();

    /**
     * returns the activityID
     *
     * @return String ID
     */
    String getActivityID();


}
