package com.ozanselte;

/**
 * Experiment Node and Data class
 */
public class Experiment implements Comparable<Experiment> {

    private String setup;
    private int day;
    private String time;
    private boolean isCompleted;
    private float accuracy;
    private Experiment nextExp;
    private Experiment nextDay;

    /**
     * Data only constructor
     * @param setup Explains the experimental setup
     * @param day Represents the day of start
     * @param time Represents the time of start
     * @param isCompleted Indicates whether it is completed or not
     * @param accuracy Represents the output
     */
    public Experiment(String setup, int day, String time, boolean isCompleted, float accuracy) {
        this(setup, day, time, isCompleted, accuracy, null);
    }

    /**
     * Data and Next Experiment constructor
     * @param setup Explains the experimental setup
     * @param day Represents the day of start
     * @param time Represents the time of start
     * @param isCompleted Indicates whether it is completed or not
     * @param accuracy Represents the output
     * @param nextExp Referance of next experiment
     */
    public Experiment(String setup, int day, String time, boolean isCompleted, float accuracy,
                      Experiment nextExp) {
        this(setup, day, time, isCompleted, accuracy, nextExp, null);
    }

    /**
     * Data and Next Experiment constructor
     * @param setup Explains the experimental setup
     * @param day Represents the day of start
     * @param time Represents the time of start
     * @param isCompleted Indicates whether it is completed or not
     * @param accuracy Represents the output
     * @param nextExp Referance of next experiment
     * @param nextDay Referance of next days first experiment
     */
    public Experiment(String setup, int day, String time, boolean isCompleted, float accuracy,
                      Experiment nextExp, Experiment nextDay) {
        this.setSetup(setup);
        this.setDay(day);
        this.setTime(time);
        this.setCompleted(isCompleted);
        this.setAccuracy(accuracy);
        this.setNextExp(nextExp);
        this.setNextDay(nextDay);
    }

    /**
     * Copy constructor, next references will be null.
     * @param exp The experiment
     */
    public Experiment(Experiment exp) {
        this.setValues(exp);
        this.setNextExp(null);
        this.setNextDay(null);
    }

    /**
     * Setup getter
     * @return The setup
     */
    public String getSetup() {
        return setup;
    }

    /**
     * Setup setter
     * @param setup Setup data
     */
    public void setSetup(String setup) {
        this.setup = setup;
    }

    /**
     * Day getter
     * @return The day
     */
    public int getDay() {
        return day;
    }

    /**
     * Day setter
     * @param day Day data
     */
    public void setDay(int day) {
        if(1 > day) {
            this.day = 1;
        }
        else {
            this.day = day;
        }
    }

    /**
     * Time getter
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * Time setter
     * @param time Time data
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Completed status getter
     * @return The completed status
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Completed status setter
     * @param completed Completed status data
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Accuracy getter
     * @return The accuracy
     */
    public float getAccuracy() {
        return accuracy;
    }

    /**
     * Accuracy setter
     * @param accuracy Accuracy data
     */
    public void setAccuracy(float accuracy) {
        if(0 > accuracy) {
            this.accuracy = 0;
        }
        else {
            this.accuracy = accuracy;
        }
    }

    /**
     * Next experiment reference getter
     * @return The next experiment
     */
    public Experiment getNextExp() {
        return nextExp;
    }

    /**
     * Next experiment reference setter
     * @param nextExp Next experiment reference
     */
    public void setNextExp(Experiment nextExp) {
        this.nextExp = nextExp;
    }

    /**
     * Next days first experiment reference getter
     * @return The next days first experiment
     */
    public Experiment getNextDay() {
        return nextDay;
    }

    /**
     * Next days first experiment reference setter
     * @param nextDay Next days first experiment reference
     */
    public void setNextDay(Experiment nextDay) {
        this.nextDay = nextDay;
    }

    /**
     * Sets all values to given experiments values.
     * @param exp The experiment
     */
    public void setValues(Experiment exp) {
        this.setSetup(exp.getSetup());
        this.setDay(exp.getDay());
        this.setTime(exp.getTime());
        this.setCompleted(exp.isCompleted());
        this.setAccuracy(exp.getAccuracy());
    }

    /**
     * Overrided compareTo method according to the accuracy value
     * @param right The experiment which will be compared
     * @return 1 if this accurary bigger than rights, 0 if equals, else -1
     */
    @Override
    public int compareTo(Experiment right) {
        if(accuracy > right.getAccuracy()) {
            return 1;
        }
        else if(accuracy < right.getAccuracy()) {
            return -1;
        }
        return 0;
    }
}