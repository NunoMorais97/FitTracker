/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import java.io.Serializable;

import dataStructures.Iterator;



/**
 *
 * @author nunomorais
 */
class AthleteClass implements Athlete, AthletePrivate , Serializable {
	
	private static final long serialVersionUID = 0L;

    private int weight , height , age, calories , steps;
    private final String name, ID;
    private WorkoutManager workout_manager;
    private Group group;
    private final char sex;
    
    /**
     * Contructor method , creates a new athlete with the following parameters:
     * @param weight
     * @param height
     * @param age
     * @param sex ("F") or ("M)
     * @param name
     * @param ID (trackerID)
     */
    AthleteClass(int weight, int height, int age, char sex, String name, String ID){
        
        this.group = null;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.name = name;
        this.calories = 0;
        this.steps = 0;
        this.ID = ID;
        this.sex = sex;
        workout_manager = new WorkoutManagerClass(this);
        
    }
    
    @Override
    public int getWeight() {
        return weight;
    }
    
    @Override
    public int getHeight() {
        return height;
    }
    
    @Override
    public int getAge() {
        return age;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public char getSex() {
        return sex;
    }
    
    @Override
    public String getID() {
        return ID;
    }
    
    @Override
    public void setAge(int age) {
        if (age > 0) this.age = age;
    }
    
    @Override
    public void setWeight(int weight)   {
        if ( weight > 0 ) this.weight = weight;
    }
    
    @Override
    public void setHeight(int height)   {
        if(height > 0) this.height = height;
    }
    
    @Override
    public void setSteps(int steps)  {
        assert(steps > 0); this.steps += steps;
    }
    
    @Override
    public void setCalories(int calories)   {
        if(calories > 0 ) this.calories = calories;
    }
    
    @Override
    public int getCalories() {
        return this.calories;
    }
    
    @Override
    public int getSteps() {
        return this.steps;
    }

    @Override
    public Iterator<Workout> ListWorkouts(char type)  {
        return workout_manager.ListWorkouts(type);
    }


    @Override
    public Workout addWorkout(Activity activity, int duration) {
        Workout workout = this.workout_manager.addWorkout(activity, duration);
        calories += workout.getCalories();
        return workout;
    }

    @Override
    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public Group getGroup() {
        return this.group;
    }

}
