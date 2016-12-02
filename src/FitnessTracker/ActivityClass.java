/* 
 * @author Nuno Morais (49544) <nm.morais@campus.fct.unl.pt>
 */
package FitnessTracker;

import java.io.Serializable;

/**
 *
 * @author nunomorais
 */
class ActivityClass implements Activity , Serializable{

    private static final long serialVersionUID = 0L;


    private final String name, activityID;
    private final int MIT;

    public ActivityClass(String activityID, String name , int MET){
        this.name = name;
        this.MIT = MET;
        this.activityID = activityID;

    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMET() {
        return MIT;
    }

    @Override
    public String getActivityID() {
        return activityID;
    }

}
