/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.AthleteNotInGroupException;

/**
 *
 * @author nunomorais
 */
interface GroupPrivate extends Group {
    
     /**
     * Inserts an athlete to the group
     * @param athlete , Object Athlete
     */
    void addAthlete (Athlete athlete) ;
    
    /**
     * Removes an athlete from the group
     * if the specified athlete is not in the group throws an exception
     * removes the calories and the steps this athlete had from the group's total
     * @param athlete , Object Athlete
     * @throws AthleteNotInGroupException, if there is no athletes in the group
     */
    void removeAthlete (Athlete athlete) throws  AthleteNotInGroupException;

    /**
     * adds the @param calories to the calories counter
     * @param calories
     * @return the calories added
     */
    int addCalories(int calories);

    /**
     * Adds the specified steps the step counter of the athlete
     * @param steps
     * @return
     */
    int addSteps(int steps);
}
