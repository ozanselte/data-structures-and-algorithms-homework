package com.ozanselte;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterable LinkedList class
 */
public class ExperimentList implements Iterable<Experiment> {

    private Experiment head;

    /**
     * Empty constructor
     */
    public ExperimentList() {
        this.head = null;
    }

    /**
     * Single experiment constructor
     * @param head Head of the new list
     */
    public ExperimentList(Experiment head) {
        this.head = head;
    }

    /**
     * Copy constructor
     * @param list The list which is going to be copied
     */
    public ExperimentList(ExperimentList list) {
        Experiment temp = list.head;
        while(null != temp) {
            addExp(temp);
            temp = temp.getNextExp();
        }
    }

    /**
     * Inserts the experiment to the end of the day.
     * @param exp The experiment
     */
    public void addExp(Experiment exp) {
        Experiment node = new Experiment(exp);
        if(null == head) {
            head = node;
        }
        else {
            Experiment temp = getDaysFirstExp(exp.getDay());
            if(null == temp) {
                int day = exp.getDay() - 1;
                while(1 <= day) {
                    temp = getDaysFirstExp(day);
                    if(null != temp) {
                        Experiment first = temp;
                        temp = getDaysLastExp(day);
                        if(null != first.getNextDay()) {
                            node.setNextExp(temp.getNextExp());
                            node.setNextDay(first.getNextDay());
                        }
                        temp.setNextExp(node);
                        first.setNextDay(node);
                        return;
                    }
                    day--;
                }
                node.setNextExp(head);
                node.setNextDay(head);
                head = node;
            }
            else {
                temp = getDaysLastExp(exp.getDay());
                Experiment after = new Experiment(exp);
                after.setNextExp(temp.getNextExp());
                temp.setNextExp(after);
            }
        }
    }

    /**
     * Gets the experiment with the given day and position.
     * @param day The experiments day
     * @param index The experiments index in the day
     * @return The experiment
     */
    public Experiment getExp(int day, int index) {
        Experiment temp = getRawExp(day, index);
        return new Experiment(temp);
    }

    /**
     * Sets the experiment with the given day and position.
     * @param day The experiments day
     * @param index The experiments index in the day
     * @param exp The new experiment data
     */
    public void setExp(int day, int index, Experiment exp) {
        Experiment temp = getRawExp(day, index);
        temp.setValues(exp);
    }

    /**
     * Removes the experiment specified as index from given day.
     * @param day The experiments day
     * @param index The experiments index in the day
     * @throws NoSuchElementException in case of unvalid day and index
     */
    public void removeExp(int day, int index) throws NoSuchElementException {
        Experiment before, temp;
        temp = getRawExp(day, index);
        if(head == temp) {
            head = head.getNextExp();
            if(null != head && null == head.getNextDay()) {
                head.setNextDay(temp.getNextDay());
            }
        }
        else if(1 < index) {
            before = getRawExp(day, index-1);
            before.setNextExp(temp.getNextExp());
        }
        else if(head.getDay() < day) {
            int d = day - 1;
            before = getDaysFirstExp(d);
            while(null == before) {
                if(head.getDay() == d) {
                    throw new NoSuchElementException("No Such Element");
                }
                else {
                    d--;
                    before = getDaysFirstExp(d);
                }
            }
            before.setNextDay(temp.getNextExp());
            before = getDaysLastExp(d);
            before.setNextExp(temp.getNextExp());
            before = temp.getNextExp();
            if(null == before.getNextDay()) {
                before.setNextDay(temp.getNextDay());
            }
        }
        else {
            throw new NoSuchElementException("No Such Element");
        }
    }

    /**
     * Lista all completed experiments in a given day.
     * @param day The experiments day
     * @return Completed experiments list
     */
    public ExperimentList listExp(int day) {
        ExperimentList list = new ExperimentList();
        Experiment temp = getDaysFirstExp(day);
        do {
            if(temp.isCompleted()) {
                list.addExp(temp);
            }
            temp = temp.getNextExp();
        } while(null != temp && temp.getDay() == day);
        return list;
    }

    /**
     * Removes the experiment specified as index from given day.
     * @param day The experiments day
     * @throws NoSuchElementException in case of unvalid day
     */
    public void removeDay(int day) throws NoSuchElementException {
        Experiment first, temp;
        first = getDaysFirstExp(day);
        if(null == first) {
            throw new NoSuchElementException("No Such Element");
        }
        temp = first.getNextExp();
        removeExp(day, 1);
        while(null != temp && temp.getDay() == day) {
            temp = temp.getNextExp();
            removeExp(day, 1);
        }
    }

    /**
     * Sorts the experiments in a given day according to the accuracy, the changes will be done on the list.
     * @param day The experiments day
     */
    public void orderDay(int day) {
        Experiment first, last;
        first = getDaysFirstExp(day);
        last = getDaysLastExp(day);
        Experiment a, b;
        a = first;
        while(null != a && null != a.getNextExp() && last != a) {
            b = a.getNextExp();
            while(null != b && last.getNextExp() != b) {
                if(1 == a.compareTo(b)) {
                    swap(a, b);
                }
                b = b.getNextExp();
            }
            a = a.getNextExp();
        }
    }

    /**
     * Sorts all the experiments in the list according to the accuracy, the original list should not be changed since the day list may be damage.
     * @return New ordered experiment list
     */
    public ExperimentList orderExperiments() {
        ExperimentList sorted = new ExperimentList(this);
        Experiment temp = sorted.head;
        while(null != temp) {
            temp.setDay(1);
            temp.setNextDay(null);
            temp = temp.getNextExp();
        }
        sorted.orderDay(1);
        return sorted;
    }

    private Experiment getRawExp(int day, int index) {
        if(1 > day || 1 > index) {
            throw new NoSuchElementException("No Such Element");
        }
        Experiment temp = getDaysFirstExp(day);
        if(null == temp) {
            throw new NoSuchElementException("No Such Element");
        }
        for (int i = 1; i < index; i++) {
            if(null != temp.getNextExp()) {
                temp = temp.getNextExp();
            }
            else {
                throw new NoSuchElementException("No Such Element");
            }
        }
        return temp;
    }

    private Experiment getDaysFirstExp(int day) {
        Experiment temp = head;
        while(day != temp.getDay()) {
            if(null == temp.getNextDay()) {
                return null;
            }
            temp = temp.getNextDay();
        }
        return temp;
    }

    private Experiment getDaysLastExp(int day) {
        Experiment temp = getDaysFirstExp(day);
        Experiment last = temp;
        if(null == temp.getNextExp()) {
            return temp;
        }
        while(null != temp.getNextExp()) {
            temp = temp.getNextExp();
            if(day == temp.getDay()) {
                last = temp;
            }
            else {
                return last;
            }
        }
        return last;
    }

    private void swap(Experiment a, Experiment b) throws NoSuchElementException {
        if(null == a || null == b) {
            throw new NoSuchElementException("No Such Element");
        }
        Experiment temp = new Experiment(a);
        a.setValues(b);
        b.setValues(temp);
    }

    /**
     * Overrided iterator method from Iterable Interface
     * @return new custom Experiment Iterator
     */
    @Override
    public Iterator<Experiment> iterator() {
        return new ExperimentIterator(head);
    }
}
