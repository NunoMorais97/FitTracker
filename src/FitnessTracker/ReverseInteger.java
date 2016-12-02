package FitnessTracker;

import java.io.Serializable;

/**
 * Created by nunomorais on 30/11/2016.
 */
public class ReverseInteger implements Comparable<ReverseInteger> , Serializable {

    private static final long serialVersionUID = 0L;

    int value;

    public ReverseInteger(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public int compareTo(ReverseInteger o) {
        if(this.value > o.getValue()) return -1;
        if(this.value < o.getValue()) return 1;
        return 0;
    }
}
