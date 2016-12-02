/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.HasNoWorkoutsException;
import dataStructures.*;

import java.io.Serializable;

/**
 *
 * @author nunomorais
 *
 */
class WorkoutManagerClass implements WorkoutManager , Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 0L;



    private final Athlete athlete;
    private final List<Workout> workouts_list;
    private final OrderedDictionary<ReverseInteger, DoublyLinkedList<Workout>> workouts_tree;

    WorkoutManagerClass(Athlete athlete){
        this.athlete = athlete;
        this.workouts_list = new DoublyLinkedList<>();
        this.workouts_tree = new AVLTree<ReverseInteger,DoublyLinkedList<Workout>>();
    }


    @Override
    public Iterator<Workout> ListWorkouts(char type) throws HasNoWorkoutsException {
        if(workouts_list.isEmpty()) throw new HasNoWorkoutsException();
        if(type == 'C') {
            return new BSTVKeyOrderValueIterator<>(workouts_tree);
        }
        else return workouts_list.iterator();
    }

    @Override
    public Workout addWorkout(Activity activity, int duration) {
        Workout workout = new WorkoutClass(athlete, activity,duration);
        workouts_list.addFirst(workout);
        ReverseInteger reverse_duration = new ReverseInteger(workout.getCalories());
        DoublyLinkedList<Workout> list = workouts_tree.find(reverse_duration);

        if(list != null)
            list.addLast(workout);
        else {
            list = new DoublyLinkedList<>();
            list.addLast(workout);
            workouts_tree.insert(reverse_duration,list);

        }
        return workout;
    }

}