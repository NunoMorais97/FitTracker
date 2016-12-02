/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.GroupAlreadyExistsException;
import FitnessTracker.Exceptions.GroupNonExistingException;
import FitnessTracker.Exceptions.InvalidMETException;
import FitnessTracker.Exceptions.NoGroupsExistingException;
import dataStructures.*;

import java.io.Serializable;

/**
 *
 * @author nunomorais
 */
class GroupManagerClass implements GroupManager ,Serializable{

    Dictionary<String,GroupPrivate> groups;
    OrderedDictionary<ReverseInteger , DoublyLinkedList<Group>> warriors;
    OrderedDictionary<ReverseInteger, DoublyLinkedList<Group>> walkers;

    private static final long serialVersionUID = 0L;
    private static int DEFAULT_SIZE = 3000;



    GroupManagerClass(){
        this.groups = new ChainedHashTable<String,GroupPrivate>(DEFAULT_SIZE);
        this.warriors = new AVLTree<ReverseInteger,DoublyLinkedList<Group>>();
        this.walkers = new AVLTree<ReverseInteger,DoublyLinkedList<Group>>();

    }

    @Override
    public GroupPrivate getGroup(String groupID) throws GroupNonExistingException {
        GroupPrivate group = this.groups.find(groupID);
        if(group == null ) throw new GroupNonExistingException();
        if(group.getGroupID().equals(groupID)) { return group; }
        else throw new GroupNonExistingException();
    }

    @Override
    public Group createGroup(String groupID, String group_name) throws GroupAlreadyExistsException {
        GroupPrivate group = groups.find(groupID);

        if(group != null) throw new GroupAlreadyExistsException();
        group = new GroupClass(groupID, group_name);
        groups.insert(groupID,group);

        return group;

    }

    @Override
    public Iterator<Group> listWarriors() throws NoGroupsExistingException {
        if(warriors.isEmpty()) throw new NoGroupsExistingException();
        return new BSTVKeyOrderValueIterator<>(warriors);
    }

    @Override
    public Iterator<Group> listWalkers() throws NoGroupsExistingException {

        if(walkers.isEmpty()) throw new  NoGroupsExistingException();
        return new BSTVKeyOrderValueIterator<>(walkers);
    }


    @Override
    public void updateWarriors(Group group, int old_calories) {
        if (old_calories != group.getCalories()) {
            ReverseInteger old_calories_reverse = new ReverseInteger(old_calories);
            ReverseInteger new_calories_reverse = new ReverseInteger(group.getCalories());

            DoublyLinkedList<Group> oldlist = warriors.find(old_calories_reverse);
            oldlist.remove(group);
            if (oldlist.isEmpty()) warriors.remove(old_calories_reverse);

            DoublyLinkedList<Group> new_list = warriors.find(new_calories_reverse);
            if (new_list == null)
                new_list = new DoublyLinkedList<Group>();
            new_list.addLast(group);
            warriors.insert(new_calories_reverse, new_list);
        }
    }
    @Override
    public void updateWalkers(Group group, int old_steps) {

        if (old_steps != group.getSteps()) {
            ReverseInteger old_steps_reverse = new ReverseInteger(old_steps);
            ReverseInteger new_steps_reverse = new ReverseInteger(group.getSteps());

            DoublyLinkedList<Group> oldlist = walkers.find(old_steps_reverse);
            oldlist.remove(group);
            if (oldlist.isEmpty()) walkers.remove(old_steps_reverse);

            DoublyLinkedList<Group> new_list = walkers.find(new_steps_reverse);
            if (new_list == null)
                new_list = new DoublyLinkedList<Group>();
            new_list.addLast(group);
            walkers.insert(new_steps_reverse, new_list);
        }
    }

    @Override
    public void createWarrior(Group group) {
        ReverseInteger reverse_zero = new ReverseInteger(0);
        DoublyLinkedList<Group> list  = warriors.find(reverse_zero);
        if(list == null)
            warriors.insert(reverse_zero,new DoublyLinkedList<Group>());
        warriors.find(reverse_zero).addLast(group);
    }

    @Override
    public void createWalker(Group group) {
        ReverseInteger reverse_zero = new ReverseInteger(0);
        DoublyLinkedList<Group> list  = walkers.find(reverse_zero);
        if(list == null)
            walkers.insert(reverse_zero,new DoublyLinkedList<Group>());
        walkers.find(reverse_zero).addLast(group);

    }


}
