package com.ozanselte;

import java.util.Iterator;

public class GraphIteratorO implements Iterator<Integer> {

    private int[] arr;
    private int idx;

    public GraphIteratorO(int[] arr) {
        this.arr = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            this.arr[i] = arr[i];
        }
        idx = -1;
    }

    @Override
    public boolean hasNext() {
        return arr.length - 1 > idx;
    }

    @Override
    public Integer next() {
        return arr[++idx];
    }
}
