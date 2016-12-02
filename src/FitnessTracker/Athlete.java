/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.HasNoWorkoutsException;
import dataStructures.Iterator;

/**
 * has all the getters  of the athlete
 */
public interface Athlete {
    
    
    /**
     * Returns the weight of the athlete
     * @return integer weight
     */
    int getWeight();
    
    /**
     * Returns the height of the athlete
     * @return integer height
     */
    int getHeight();
    
    /**
     * Returns the age of the athlete
     * @return integer age
     */
    int getAge();
    
    /**
     * Returns the name of the athlete
     * @return String name
     */
    String getName();
    
    
    /**
     * Returns the sex of the athlete, "M" if male, "F" If female
     * @return char "M" or "F"
     */
    char getSex();
    
    /**
     * Returns the Athlete's ID
     * @return String ID
     */
    String getID();
 
    
    /**
     * returns the amount of calories the athlete has burned
     * @return integer calories
     */
    int getCalories();
    
    /**
     * returns the amount of steps the athlete has taken
     * @return integer steps
     */
    int getSteps() ;

    /**
     *  /**
     * returns an iterator of workouts
     * the order of iteration depends on the argumment
     * if type == 'T', the iteration will be made by decreasing order of calories
     * if type == 'C', the iteration will be made by chronological order
     * @return Iterator of workouts
     * @throws HasNoWorkoutsException , if the athlete has not completed any workouts
     */
    Iterator<Workout> ListWorkouts(char type);

    /**
     * returns the group the athlete belongs to
     * @return object group , null if the athlete belongs to no group
     */
    Group getGroup();
}
   