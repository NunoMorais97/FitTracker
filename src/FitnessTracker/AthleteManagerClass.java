/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.AlreadyExistingAthleteException;
import FitnessTracker.Exceptions.NonExistingAthleteException;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;

import java.io.Serializable;

/**
 *
 * @author nunomorais
 */
class AthleteManagerClass implements AthleteManager ,Serializable {

    private static final long serialVersionUID = 0L;

    private Dictionary<String , AthletePrivate> athletes;
    private static int DEFAULT_SIZE = 6000;


    AthleteManagerClass(){
        athletes = new ChainedHashTable<String, AthletePrivate>(DEFAULT_SIZE);
    }


    @Override
    public AthletePrivate getAthlete(String athleteID) throws NonExistingAthleteException {
        AthletePrivate athlete = athletes.find(athleteID);
        if( athlete == null) throw new NonExistingAthleteException();
        return athlete;
    }

    @Override
    public Athlete createAthlete(String athleteID, int weight, int height, int age, char sex, String name) throws AlreadyExistingAthleteException {
        AthletePrivate athlete = athletes.find(athleteID);
        if(athlete != null) throw new AlreadyExistingAthleteException();
        AthletePrivate new_athlete = new AthleteClass(weight ,height, age,sex,name, athleteID);
        athletes.insert(athleteID,  new_athlete);
        return new_athlete;
    }

    @Override
    public Athlete removeAthlete(String athleteID) throws  NonExistingAthleteException{
        Athlete athlete = athletes.remove(athleteID);
        if(athlete == null) throw new NonExistingAthleteException();
        return athlete;
    }

}
