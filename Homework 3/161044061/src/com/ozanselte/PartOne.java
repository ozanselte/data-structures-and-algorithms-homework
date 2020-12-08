package com.ozanselte;

import java.io.FileReader;

/**
 * Part One driver class.
 * @author Ozan Şelte
 */
public class PartOne {

    private static boolean DEBUG = false;
    private int[][] table;
    private int island;
    private int x, y;
    private StackO<Coordinate> stack;

    /**
     * Constructor. Calculates the number of islands in the file.
     * @param path test file name
     */
    PartOne(String path) {
        int chr;
        x = 1;
        y = 0;
        boolean counted = false;
        String str = "";
        try {
            FileReader fileReader = new FileReader(path);
            while (-1 != (chr = fileReader.read())) {
                System.out.print((char)chr);
                switch(chr) {
                    case '\n':
                        counted = true;
                        break;
                    case ' ':
                        if(!counted) {
                            x++;
                        }
                        break;
                    default:
                        str += (char)chr;
                }
            }
            y = str.length() / x;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        table = new int[y][x];
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                char c = str.charAt(i*x + j);
                table[i][j] = ('1' == c) ? 1 : 0;
            }
        }
        calculate();
    }

    /**
     * The islands number getter.
     * @return the islands number
     */
    public int getN() {
        return island;
    }

    /**
     * Calculates the number with my own algorithm.
     */
    private void calculate() {
        island = 0;
        int max = 1;
        StackO<Integer> delete = new StackO<>();
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(1 == table[i][j]) {
                    if(0 < j && 0 != table[i][j-1]) {
                        table[i][j] = table[i][j-1];
                    }
                    else if(0 < i && 0 != table[i-1][j]) {
                        table[i][j] = table[i-1][j];
                    }
                    else {
                        table[i][j] = ++max;
                        island++;
                    }
                    if(0 < i && 0 < table[i-1][j] && table[i][j] != table[i-1][j]) {
                        delete.push(table[i-1][j]);
                    }
                }
                if(DEBUG) System.out.print(table[i][j] + "\t");
            }
            if(DEBUG) System.out.println();
        }

        int last = 0;
        while(!delete.empty()) {
            int num = delete.pop();
            if(DEBUG) System.out.println(num);
            if(num != last){
                island--;
                last = num;
            }
        }
    }

    /**
     * Calculates the number with depth-first algoritm.
     */
    private void calculateX() {
        island = 0;
        stack = new StackO<Coordinate>();
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(1 == table[i][j]) {
                    checkAround(j, i);
                    island++;
                    while(!stack.empty()) {
                        Coordinate cord = stack.pop();
                        checkAround(cord.getX(), cord.getY());
                    }
                }
            }
        }
    }

    /**
     * Checks around and if it is 1, pushes into the stack.
     * @param xC x-coordinate
     * @param yC y-coordinate
     */
    private void checkAround(int xC, int yC) {
        table[yC][xC] = 0;
        if(0 < yC && 0 != table[yC-1][xC]) {
            stack.push(new Coordinate(xC, yC-1));
        }
        if(0 < xC && 0 != table[yC][xC-1]) {
            stack.push(new Coordinate(xC-1, yC));
        }
        if(yC < y - 1 && 0 != table[yC+1][xC]) {
            stack.push(new Coordinate(xC, yC+1));
        }
        if (xC < x - 1 && 0 != table[yC][xC + 1]) {
            stack.push(new Coordinate(xC + 1, yC));
        }
    }
}
