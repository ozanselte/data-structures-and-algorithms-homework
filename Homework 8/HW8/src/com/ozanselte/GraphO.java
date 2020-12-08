package com.ozanselte;

import java.util.Iterator;

public class GraphO {

    private int numV;
    private boolean[][] matrix;

    public GraphO(int numV) {
        this.numV = numV;
        matrix = new boolean[numV][numV];
        for(int i = 0; i < numV; i++) {
            for(int j = 0; j < numV; j++) {
                matrix[i][j] = false;
            }
        }
    }

    public int getNumV() {
        return numV;
    }

    public Iterator<Integer> iterator(int source) {
        return new GraphIteratorO(getDestinations(source));
    }

    public boolean isEdge(int source, int destination) {
        return matrix[source][destination];
    }

    public int getPopularsCount() {
        int count = 0;
        for(int i = 0; i < numV; i++) {
            if(isPopular(i)) {
                count++;
            }
        }
        return count;
    }

    public void insert(int source, int destination) {
        matrix[source][destination] = true;
    }

    private int getDestinationsCount(int source) {
        int count = 0;
        for(int i = 0; i < numV; i++) {
            if(isEdge(source, i)) {
                count++;
            }
        }
        return count;
    }

    private int[] getDestinations(int source) {
        int count = getDestinationsCount(source);
        int[] arr = new int[count];
        int idx = 0;
        for(int i = 0; i < numV; i++) {
            if(isEdge(source, i)) {
                arr[idx++] = i;
            }
        }
        return arr;
    }

    private int getSourcesCount(int destination) {
        int count = 0;
        for(int i = 0; i < numV; i++) {
            if(isEdge(i, destination)) {
                count++;
            }
        }
        return count;
    }

    private int[] getSources(int destination) {
        int count = getSourcesCount(destination);
        int[] arr = new int[count];
        int idx = 0;
        for(int i = 0; i < numV; i++) {
            if(isEdge(i, destination)) {
                arr[idx++] = i;
            }
        }
        return arr;
    }

    private boolean isPopular(int num) {
        for(int i = 0; i < numV; i++) {
            boolean[] isVisited = new boolean[numV];
            isVisited[num] = true;
            QueueO queue = new QueueO();
            if(i != num && !isConnected(i, num, isVisited, queue)) {
                return false;
            }
        }
        return true;
    }

    private boolean isConnected(int source, int destination, boolean[] isVisited, QueueO queue) {
        int[] destinations = getDestinations(source);
        for(int i = 0; i < destinations.length; i++) {
            if(!isVisited[destinations[i]]) {
                queue.add(destinations[i]);
            }
        }
        isVisited[source] = true;
        if(matrix[source][destination]) {
            return true;
        }
        while(null != queue.peek()) {
            int s = queue.remove();
            boolean res = isConnected(s, destination, isVisited, queue);
            if(res) {
                matrix[s][destination] = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < numV; i++) {
            for(int j = 0; j < numV; j++) {
                if(i == j) builder.append("\\ ");
                else builder.append(matrix[i][j] ? "X " : ". ");
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }
}
