/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.GroupAlreadyExistsException;
import FitnessTracker.Exceptions.GroupNonExistingException;
import FitnessTracker.Exceptions.NoGroupsExistingException;
import dataStructures.Iterator;

/**
 * manages all of the groups in the tracker
 * can return a group, insert a group and list the groups by different parameters
 */
interface GroupManager {
    
    
    /**
     * returns the group with the specified groupID
     * @param groupID
     * @throws GroupNonExistingException , if there is no group with the specified groupID
     * @return Object of type group
     */
    GroupPrivate getGroup(String groupID) throws GroupNonExistingException;
    
    
    /**
     * Creates a new group with the specified groupID
     * if there is already a group with that groupID throws an exception
     * @param groupID , String groupID, this is the identifier of the group
     * @param group_name ,String group_name, the name the group has, it is not unique
     * @throws GroupAlreadyExistsException , if there is already a group with the same groupID
     */
    Group createGroup(String groupID, String group_name) throws GroupAlreadyExistsException;

    /**
     * returns an iterator of groups , this is ordered by calories spent, in decreasing order
     * @return object Iterator
     * @throws NoGroupsExistingException
     */
    Iterator<Group> listWarriors() throws NoGroupsExistingException;

    /**
     * returns an iterator of groups, this is ordered by steps given, in increasing order
     * @return Object Iterator
     * @throws NoGroupsExistingException
     */
    Iterator<Group> listWalkers() throws NoGroupsExistingException;

    /**
     * method to update the order (if it changes) of the tree that keeps the groups organized by calories
     * @param group
     * @param old_calories
     */
    void updateWarriors(Group group,int old_calories);

    /**
     * method to update the order (if it changes) of the tree that keeps the groups organized by steps
     * @param group
     * @param old_steps
     */
    void updateWalkers(Group group,int old_steps);

    /**
     * inserts a new group in the warrior tree in the root
     * @param group
     */
    void createWarrior(Group group);

    /**
     * inserts a new group in the walker tree in the root
     * @param group
     */
    void createWalker(Group group);

    
}
