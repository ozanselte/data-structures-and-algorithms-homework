package com.ozanselte;

public class Main {

    public static void main(String[] args) {
        if(2 != args.length || (!args[0].equals("1") && !args[0].equals("2"))) {
            System.out.println("Bad usage! Examples:");
            System.out.println("For Q1) [program_name] 1 test_file_[1-7].txt");
            System.out.println("For Q2) [program_name] 2 infix_test_file_[1-3].txt");
            return;
        }
        try {
            String path = args[1].trim();
            int selection = Integer.valueOf(args[0]);
            switch(selection) {
                case 1:
                    PartOne q1 = new PartOne(path);
                    System.out.println(q1.getN());
                    break;
                case 2:
                    PartTwo q2 = new PartTwo(path);
                    System.out.println(q2.toString());
                    break;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}