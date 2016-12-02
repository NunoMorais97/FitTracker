/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.EmptyGroupException;
import dataStructures.Entry;
import dataStructures.Iterator;

/**
 * public interface
 * Has all the getters of the group, such as the steps, name, id , and athletes in the group
 */
public interface Group {
    
   
    
    /**
     * returns the athletes in the group
     * @return the Iterator of Athletes
     * @throws EmptyGroupException , if the group is empty
     */
    Iterator<Entry<String,Athlete>> ListAthletes() throws EmptyGroupException;
    
    /**
     * returns the sum of all calories done by the athletes in the group
     * @return integer calories
     */
    int getCalories();
    
    /**
     * returns the sum of all the steps done by the athletes in the group
     * @return integer calories
     */
    int getSteps();
    
    /**
     * returns the group ID
     * @return String groupID
     */
    String getGroupID();
    
    /**
     * returns the name of the group
     * @return String name
     */
    String getName();
    
    
}
