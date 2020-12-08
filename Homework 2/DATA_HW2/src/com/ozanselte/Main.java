package com.ozanselte;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) {
        // HH for 24-hour based time format, hh is 12-hour based
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        //DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

        System.out.println("Adding experiments by 2 experiments for each day.");
        System.out.println("Days adding order is 2 -> 4 -> 3 -> 1 -> 5");

        ExperimentList List = new ExperimentList();

        try {
            List.addExp(new Experiment("2-A", 2, dateFormat.format(new Date()), true, 2f));
            List.addExp(new Experiment("2-B", 2, dateFormat.format(new Date()), true, 1f));
            List.addExp(new Experiment("4-A", 4, dateFormat.format(new Date()), false, 3f));
            List.addExp(new Experiment("4-B", 4, dateFormat.format(new Date()), true, 4f));
            List.addExp(new Experiment("3-A", 3, dateFormat.format(new Date()), false, 5f));
            List.addExp(new Experiment("3-B", 3, dateFormat.format(new Date()), false, 6f));
            List.addExp(new Experiment("1-A", 1, dateFormat.format(new Date()), true, 7f));
            List.addExp(new Experiment("1-B", 1, dateFormat.format(new Date()), false, 8f));
            List.addExp(new Experiment("5-A", 5, dateFormat.format(new Date()), true, 9f));
            List.addExp(new Experiment("5-B", 5, dateFormat.format(new Date()), false, 10f));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Initial List");
            printIterator(List.iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Day-2 has ORDERED");
            List.orderDay(2);
            printIterator(List.iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Day-3s first element is REMOVED");
            List.removeExp(3, 1);
            printIterator(List.iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Day-4 is REMOVED");
            List.removeDay(4);
            printIterator(List.iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("All experiments are ORDERED");
            printIterator(List.orderExperiments().iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("Two exps assigned each other with setExp() and getExp()");
            List.setExp(1, 1, List.getExp(1, 2));
            printIterator(List.iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        try {
            System.out.println("listExp() method called for day-5");
            printIterator(List.listExp(5).iterator());
            System.out.println("--- --- --- --- ---");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printIterator(Iterator<Experiment> it) {
        if(null == it) {
            return;
        }
        while(it.hasNext()) {
            Experiment e = it.next();
            System.out.print(e.getSetup() + " ");
            System.out.print(e.getDay() + " ");
            System.out.print(e.getTime() + " ");
            System.out.print(e.isCompleted() + " ");
            System.out.println(e.getAccuracy());
        }
    }
}
