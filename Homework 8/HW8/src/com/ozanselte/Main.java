package com.ozanselte;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            File input = new File("input.txt");
            Scanner scanner = new Scanner(input);
            testWithScanner(scanner);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private static void testWithScanner(Scanner scanner) {
        int peopleNum = scanner.nextInt();
        int relationsNum = scanner.nextInt();
        GraphO graph = new GraphO(peopleNum);
        for(int i = 0; i < relationsNum; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph.insert(source - 1, destination - 1);
        }
        //System.out.println(graph.toString());
        System.out.println(graph.getPopularsCount());
    }
}
