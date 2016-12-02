/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.*;
import dataStructures.Entry;
import dataStructures.Iterator;

import java.io.Serializable;

/**
 *
 * @author nunomorais
 */
public class TrackerClass implements Tracker, Serializable{


    private static final long serialVersionUID = 0L;

    //Constants
    private static final char DF_MALE = 'M' , DF_FEM = 'F';




    private final AthleteManager athlete_manager;
    private final ActivityManager activity_manager;
    private final GroupManager group_manager;


    public TrackerClass(){
        athlete_manager = new AthleteManagerClass();
        activity_manager = new ActivityManagerClass();
        group_manager = new GroupManagerClass();
    }

    @Override
    public void insertAthlete(String athleteID, int weight, int height,int age, char sex, String name) throws InvalidValuesException {
        // verifications, if weight, age and height are <0, or if sex != 'M' or 'F'
        if(weight <0 || height<0 || age<0 ) throw new InvalidValuesException();
        if(sex != DF_MALE && sex != DF_FEM)throw new InvalidValuesException();

        athlete_manager.createAthlete(athleteID, weight, height, age, sex, name);
    }

    @Override
    public void changeAthleteInfo(String athleteID, int weight, int height, int age) throws InvalidValuesException {
        if(weight <0 || height<0 || age<0 ) throw new InvalidValuesException();

        AthletePrivate athlete = athlete_manager.getAthlete(athleteID);
        athlete.setAge(age);
        athlete.setHeight(height);
        athlete.setWeight(weight);
    }

    @Override
    public void removeAthlete(String athleteID) {

        Athlete athlete = athlete_manager.removeAthlete(athleteID);
        GroupPrivate group  = (GroupPrivate) athlete.getGroup();



        if(group != null) {

            int old_steps = group.getSteps();
            int old_calories = group.getCalories();

            group.removeAthlete(athlete);


            group_manager.updateWarriors(group, old_calories);
            group_manager.updateWalkers(group, old_steps);
        }

    }

    @Override
    public Athlete consultAthlete(String athleteID) {
        return athlete_manager.getAthlete(athleteID);
    }

    @Override
    public void createActivity(String activityID, int MET, String name) {
        activity_manager.addActivity(activityID, MET, name);
    }

    @Override
    public void addWorkout(String athleteID, String activityID, int duration) throws InvalidWorkoutTimeException {

        if( duration < 0 ) throw new InvalidWorkoutTimeException();

        AthletePrivate athlete = athlete_manager.getAthlete(athleteID);
        Activity activity = activity_manager.getActivity(activityID);

        //adds the workout
        Workout workout = athlete.addWorkout(activity, duration);

        //if the user has group, adds the calories to the groups total
        GroupPrivate group = (GroupPrivate) athlete.getGroup();
        if(group != null) {
            int old_calories = group.getCalories();
            group.addCalories(workout.getCalories());
            group_manager.updateWarriors(group, old_calories);
        }
    }

    @Override
    public Iterator<Workout> ListWorkouts(String athleteID ,char type) throws InvalidTypeException {
        if(type != 'C' && type != 'T') throw new InvalidTypeException();
        return athlete_manager.getAthlete(athleteID).ListWorkouts(type);
    }


    @Override
    public void addSteps(String athleteID, int steps) throws InvalidStepsException {
        if(steps < 0 ) throw new InvalidStepsException();

        AthletePrivate athlete =  athlete_manager.getAthlete(athleteID);
        athlete.setSteps(steps);

        GroupPrivate group = (GroupPrivate) athlete.getGroup();
        if(group != null) {
            int old_steps = group.getSteps();
            group.addSteps(steps);
            group_manager.updateWalkers(group, old_steps);
        }
    }

    @Override
    public void createGroup(String groupID, String group_name) {
        Group group = group_manager.createGroup(groupID, group_name);
        group_manager.createWalker(group);
        group_manager.createWarrior(group);
    }

    @Override
    public void joinGroup(String athleteID, String groupID) throws AlreadyHasGroupException {
        AthletePrivate athlete = athlete_manager.getAthlete(athleteID);
        GroupPrivate group = group_manager.getGroup(groupID);

        int old_steps = group.getSteps();
        int old_calories = group.getCalories();

        if(athlete.getGroup() != null ) throw new AlreadyHasGroupException();

        athlete.setGroup(group);
        group.addAthlete(athlete);

        //updating the group's step and calorie counter
        group.addSteps(athlete.getSteps());
        group.addCalories(athlete.getCalories());
        group_manager.updateWarriors(group, old_calories);
        group_manager.updateWalkers(group, old_steps);

    }

    @Override
    public void leaveGroup(String athleteID, String groupID) {
        GroupPrivate group = group_manager.getGroup(groupID);
        AthletePrivate athlete = athlete_manager.getAthlete(athleteID);

        int old_steps = group.getSteps();
        int old_calories = group.getCalories();

        group.removeAthlete(athlete);
        athlete.setGroup(null);

        group_manager.updateWarriors(group, old_calories);
        group_manager.updateWalkers(group, old_steps);

    }

    @Override
    public Group consultGroup(String groupID) {
        return group_manager.getGroup(groupID);
    }

    @Override
    public Iterator<Entry<String, Athlete>> listGroup(String groupID) throws EmptyGroupException {
        Group group = group_manager.getGroup(groupID);
        if(group.ListAthletes() ==null) throw new EmptyGroupException();
        return group.ListAthletes();
    }

    @Override
    public Iterator<Group> ListGroupsBySteps() {
        return group_manager.listWalkers();
    }

    @Override
    public Iterator<Group> ListGroupsByCalories() {
        return group_manager.listWarriors();
    }


}

