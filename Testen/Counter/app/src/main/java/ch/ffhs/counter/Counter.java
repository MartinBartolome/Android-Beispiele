package ch.ffhs.counter;


import java.util.Observable;

public class Counter extends Observable {

    public static final String STEP_KEY = "step";
    public static final String DIRECTION_KEY = "direction";
    private static final int MAX = 10;
    private static final int MIN = 0;

    static private Counter instance = null;
    private int max;
    private int min;
    private int value;
    private int dummy;


    static Counter getInstance(){
        if(instance == null){
            instance = new Counter();
        }
        return instance;
    }

    /**
     * Default-Constructor
     */
    private Counter() {
        this.max = Counter.MAX;
        this.min = Counter.MIN;
        this.value = this.min;
    }


    /**
     * Increments value until max
     * @return  current value
     */
    synchronized public int up(){
        //if(this.value < this.max){
        this.value++;
        //}
        triggerObservers();
        return value;
    }


    /**
     * decrements value until min
     * @return current value
     */
    synchronized public int down(){
        //if(this.value > this.min){
        this.value--;
        //}
        triggerObservers();
        return value;
    }



    /**
     * reset value to 0 or min, if min > 0
     * @return current value
     */
    synchronized public int reset(){
        if ((min <= 0) && (max >= 0)) {
            this.value = 0;
        } else {
            this.value = this.min;
        }
        triggerObservers();
        return value;
    }


    /**
     * set value to max
     * @return current value
     */
    synchronized public int set(){
        this.value = this.max;
        triggerObservers();
        return value;
    }


    /**
     *
     * @return current value
     */
    public int getValue(){
        return this.value;
    }


    /**
     *
     * @return minimum
     */
    public int getMin(){
        return this.min;
    }


    /**
     *
     * @return maximum
     */
    public int getMax(){
        return this.max;
    }


    /**
     *
     * @return current value as String
     */
    public String getValueAsString(){
        return Integer.toString(this.value);
    }


    /**
     * notify the registerd observers
     */
    private void triggerObservers() {
        setChanged();
        notifyObservers();
    }
}
