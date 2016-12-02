/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import java.io.Serializable;

import FitnessTracker.Exceptions.AthleteNotInGroupException;
import FitnessTracker.Exceptions.EmptyGroupException;
import dataStructures.*;


class GroupClass implements GroupPrivate , Serializable{

    private static final long serialVersionUID = 0L;

    private OrderedDictionary<String , Athlete> athletes;

    private  String name, groupID;
    private int calories, steps;

    GroupClass(String groupID, String name){
        athletes = new AVLTree<String,Athlete>();
        this.name = name;
        this.groupID = groupID;
        this.steps = 0;
        this.calories = 0;
    }

    @Override
    public void addAthlete(Athlete athlete) {
        athletes.insert(athlete.getName(),athlete);
    }

    @Override
    public void removeAthlete(Athlete athlete) throws AthleteNotInGroupException {
        if(athletes.remove(athlete.getName()) == null) throw new AthleteNotInGroupException();
        steps -= athlete.getSteps();
        calories -= athlete.getCalories();
    }

    @Override
    public Iterator<Entry<String,Athlete>> ListAthletes() throws EmptyGroupException {
        if (athletes.isEmpty()) throw new EmptyGroupException();
        return athletes.iterator();
    }

    public int addCalories(int calories){
        this.calories += calories;
        return this.calories;
    }

    @Override
    public int addSteps(int steps) {
        this.steps+= steps;
        return this.steps;
    }

    @Override
    public int getCalories() {

        return calories;
    }

    @Override
    public int getSteps() {
        return steps;
    }

    @Override
    public String getGroupID() {
        return groupID;
    }

    @Override
    public String getName() {
        return name;
    }







}
