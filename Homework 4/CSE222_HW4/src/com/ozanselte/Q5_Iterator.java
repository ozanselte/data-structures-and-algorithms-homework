package com.ozanselte;

import java.util.Iterator;

public class Q5_Iterator implements Iterator {

    private int[][] matrix;
    private boolean[][] isExp;
    private int[] arr;
    private int idx;
    private final int lRow, lCol;

    public Q5_Iterator(int[][] arr2d) {
        lRow = arr2d.length;
        lCol = arr2d[0].length;
        matrix = new int[lRow][lCol];
        isExp = new boolean[lRow][lCol];
        arr = new int[lRow * lCol];
        for(int i = 0; i < lRow; i++) {
            for(int j = 0; j < lRow; j++) {
                matrix[i][j] = arr2d[i][j];
                isExp[i][j] = false;
            }
        }
        idx = 0;
        traverse(DirectionO.LEFT, 0, 0);
    }

    private boolean isDirectionSafe(DirectionO direction, int r, int c) {
        switch(direction) {
            case LEFT:
                if(lCol - 1 > c && !isExp[r][c+1]) return true;
                break;
            case DOWN:
                if(lRow - 1 > r && !isExp[r+1][c]) return true;
                break;
            case RIGHT:
                if(0 < c && !isExp[r][c-1]) return true;
                break;
            case UP:
                if(0 < r && !isExp[r-1][c]) return true;
                break;
        }
        return false;
    }

    private void traverse(DirectionO direction, int r, int c) {
        isExp[r][c] = true;
        arr[idx] = matrix[r][c];
        idx++;
        boolean safe = isDirectionSafe(direction, r, c);
        if(!safe) {
            direction = DirectionO.values()[(direction.ordinal() + 1) % 4];
            safe = isDirectionSafe(direction, r, c);
            if(!safe){
                idx = -1;
                return;
            }
        }
        switch(direction) {
            case LEFT:
                c++;
                break;
            case DOWN:
                r++;
                break;
            case RIGHT:
                c--;
                break;
            case UP:
                r--;
                break;
        }
        traverse(direction, r, c);
        idx = -1;
    }

    @Override
    public boolean hasNext() {
        if(lRow * lCol > idx + 1) {
            return true;
        }
        return false;
    }

    @Override
    public Integer next() throws ArrayIndexOutOfBoundsException {
        if(hasNext()) {
            return arr[++idx];
        }
        throw new ArrayIndexOutOfBoundsException("There is not a next element!");
    }
}
