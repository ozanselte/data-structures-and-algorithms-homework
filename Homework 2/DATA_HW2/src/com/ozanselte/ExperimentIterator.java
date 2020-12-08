package com.ozanselte;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Expriment Iterator class
 */
public class ExperimentIterator implements Iterator<Experiment> {

    private Experiment item, first;

    /**
     * Constructor which creates Iterator with a head reference given by Experiment parameter
     * @param exp First element reference
     * @throws NoSuchElementException in case of given null reference
     */
    public ExperimentIterator(Experiment exp) throws NoSuchElementException {
        if(null == exp) {
            throw new NoSuchElementException("No Such Element");
        }
        this.item = null;
        first = exp;
    }

    /**
     * Overrided hasNext method from Iterator Interface
     * @return true if the iterator has next node, else otherwise
     */
    @Override
    public boolean hasNext() {
        if(null == item) {
            return true;
        }
        else if(null != item.getNextExp()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Overrided next method from Iterator Interface
     * @return next nodes reference
     * @throws NoSuchElementException in case of the iterator doesn't have a next node
     */
    @Override
    public Experiment next() throws NoSuchElementException {
        if(!hasNext()) {
            throw new NoSuchElementException("No Such Element");
        }
        if(null == item) {
            item = first;
        }
        else {
            item = item.getNextExp();
        }
        return new Experiment(item);
    }
}
