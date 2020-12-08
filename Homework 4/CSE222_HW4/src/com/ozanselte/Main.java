package com.ozanselte;

public class Main {

    public static void main(String[] args) {
        int[][] arr = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        Q5_Iterator it = new Q5_Iterator(arr);
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
