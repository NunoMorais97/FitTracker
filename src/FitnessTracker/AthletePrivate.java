/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

/**
 * protected interface
 * extends interface athlete and has all the setters of the athlete
 */
interface AthletePrivate extends Athlete {
    
    /**
     * adds a new workout to the collection of workouts the athlete has done
     * @param activity , Object activity,  activity the workout consists of
     * @param duration , Integer duration, in minutes
     * @return the inserted workout
     */
    Workout addWorkout(Activity activity, int duration);
    
    /**
     * assigns the athlete to a group
     * @param group , Object Group
     */
    void setGroup(Group group);
    

    /**
     * Changes the weight of the athlete to the specified weight
     * @param weight , Integer Weight , in KG
     */
    void setWeight(int weight) ;
    
    /**
     * Changes the Height of the athlete to the specified height
     * @param height , Integer Height, in CM
     */
    void setHeight(int height);
    
    /**
     * sets the steps the athlete has done to the specified amount
     * @param steps , Integer steps
     */
    void setSteps(int steps);
    
    /**
     * sets the calories the athlete has burned to the specified number
     * @param calories , Integer Calories
     */
    void setCalories(int calories);
    
       
    /**
     * Changes the age of the athlete to the specified age
     * @param age, Integer age
     */
    void setAge(int age);
   
}
    
