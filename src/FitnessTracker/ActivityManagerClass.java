/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import FitnessTracker.Exceptions.ActivityAlreadyExistingException;
import FitnessTracker.Exceptions.InvalidMETException;
import FitnessTracker.Exceptions.NonExistingActivityException;
import dataStructures.ChainedHashTable;
import dataStructures.Dictionary;

import java.io.Serializable;

/**
 *
 * @author nunomorais
 */
class ActivityManagerClass implements ActivityManager ,Serializable {

    private static final long serialVersionUID = 0L;

    private Dictionary<String,Activity> activities ;

    public ActivityManagerClass(){
        activities =  new ChainedHashTable<String , Activity>();
    }


    @Override
    public Activity getActivity(String activityID) throws NonExistingActivityException {
        Activity activity = activities.find(activityID);
        if(activity != null)
            if (activity.getActivityID().equals(activityID)) return activity;
        throw new NonExistingActivityException();
    }

    @Override
    public void addActivity(String activityID, int MET, String name) throws  ActivityAlreadyExistingException , InvalidMETException{
        if(MET  < 0) throw new  InvalidMETException();

        Activity activity = new ActivityClass(activityID,name,MET);

        if(activities.find(activityID) != null) throw new ActivityAlreadyExistingException();
        activities.insert(activityID,activity);
    }

}
