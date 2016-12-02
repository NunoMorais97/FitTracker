/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */

package FitnessTracker;

import java.io.Serializable;



/**
 *
 * @author nunomorais
 */
class WorkoutClass implements Workout , Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 0L;
	private int calories;
    private final  Activity activity;
    private final int duration;
    
    
    public WorkoutClass ( Athlete athlete, Activity activity , int duration){
        
        this.activity = activity;
        this.duration = duration;
        this.calories = calculateCalories (athlete.getWeight(), athlete.getHeight(), athlete.getSex(),athlete.getAge(), activity.getMET(),duration);
        
    }
    
    /**
     * Calculates calories of activity from the parameters below
     * Assumes validity of parameters (these should be validated externally)
     * @param weight - weight of athlete in Kgs
     * @param height - height of athlete in centimeters
     * @param sex - sex of athlete ('M' or 'F')
     * @param age - age of athlete in years
     * @param MET - MET of activity performed
     * @param time - time of activity in hours
     * @return calories of activity
     */
    private int calculateCalories(int weight, int height, char sex, int age, int MET, int time ) {
        
        int BMR = 0;
        int cal = 0;
        
        if (sex=='M') {
            BMR = (int) ( ( 13.75f * weight ) + ( 5.0f * height ) - ( 6.76f * age ) + 66 );
        } else {
            BMR = (int) ( ( 9.56f * weight ) + ( 1.85f * height ) - ( 4.68f * age ) + 655);
        }
        
        cal = (int) ( ( BMR / 24.0f ) * MET * time );
        return cal;
    }

    @Override
    public int getCalories() {
        return this.calories;
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public Activity getActivity() {
        return this.activity;
    }
    
    
    
    
}
