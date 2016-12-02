/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

/**
 *
 * @author nunomorais
 */
public interface Workout{


    /**
     * return the calories spent in the workout 
     * @return integer calories
     */
int getCalories();


/**
 * returns the time spent in the workout
 * @return integer duration ( in minutes)
 */
int getDuration();

/**
 * returns the type of activity the workout was about
 * @return object activity
 */
Activity getActivity();
}