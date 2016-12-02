/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.AlreadyExistingAthleteException;
import FitnessTracker.Exceptions.NonExistingAthleteException;



/**
 * does the management of the athletes, such as adding, removing  and returning the athletes
 * to do so, uses a chainedhashtable
 */
interface AthleteManager {

 /**
  * returns the athlete  with the specified athleteID
  * @param athleteID
  * @return Athlete object
  * @throws NonExistingAthleteException , if there is no athlete with the specified ID
  */
 AthletePrivate getAthlete(String athleteID)throws NonExistingAthleteException;

 /**
  * creates a new athlete with the specified parameters
  * @param athleteID , String activityID
  * @param weight , Integer Weight
  * @param height , Integer height, in CM
  * @param age ,Integer age
  * @param sex , Char SEX, either "M" for male, or "F" for female
  * @param name , String name, the athlete's name
  * @return Object athlete
  * @throws AlreadyExistingAthleteException , if there is already an athlete with the specified ID
  */
 Athlete createAthlete(String athleteID , int weight , int height, int age, char sex, String name) throws AlreadyExistingAthleteException;

 /**
  * removes an athlete from the athlete collection
  * @param athleteID
  * @return the removed athlete
  */
 Athlete removeAthlete(String athleteID) ;
}
