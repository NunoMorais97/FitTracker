/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.HasNoWorkoutsException;
import dataStructures.Iterator;

/**
 *
 * @author nunomorais
 */
interface WorkoutManager {

    /**
     *  /**
     * returns an iterator of workouts
     * the order of iteration depends on the argumment
     * if type == 'T', the iteration will be made by decreasing order of calories
     * if type == 'C', the iteration will be made by chronological order
     * @return Iterator of workouts
     * @throws HasNoWorkoutsException , if the athlete has not completed any workouts
     */
    Iterator<Workout> ListWorkouts(char type) throws HasNoWorkoutsException;

    /**
     * adds a new workout to the workout array
     * @param activity
     * @param duration
     * @return object Workout
     */
    Workout addWorkout(Activity activity ,int duration);
}
