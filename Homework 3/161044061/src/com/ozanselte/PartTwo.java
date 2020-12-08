package com.ozanselte;

import java.io.FileReader;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Part Two driver class.
 * @author Ozan Şelte
 */
public class PartTwo {

    private static final int SIN_N = 10;
    private static final double PI = 3.141592653589793;
    private StackO<String> keys;
    private String line;
    private double result;
    private String variableLines = "";
    private String expression;

    /**
     * The constructor. Parses numbers, variables and operators and pushes to the stack.
     * @param path file path and name
     */
    PartTwo(String path) {
        StackO<String> vars;
        StackO<String> vals;
        keys = new StackO<String>();
        vars = new StackO<String>();
        vals = new StackO<String>();
        try {
            FileReader fileReader = new FileReader(path);
            Scanner s = new Scanner(fileReader);
            boolean isVariablesEnded = false;
            while (s.hasNextLine()) {
                line = s.nextLine().trim();
                if(!isVariablesEnded) {
                    if(line.isEmpty()) {
                        isVariablesEnded = true;
                    }
                    else {
                        String par[] = line.split("=");
                        variableLines += (line + "\t");
                        vars.push(par[0].trim());
                        vals.push(par[1].trim());
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        StackO<String> ops = new StackO<String>();
        String par[] = line.split(" ");
        expression = line;
        while(!vars.empty()) {
            String var = vars.pop();
            String val = vals.pop();
            for(int i = 0; i < par.length; i++) {
                if(var.equals(par[i])) {
                    par[i] = val;
                }
            }
        }
        for(int i = 0; i < par.length; i++) {
            switch(par[i]) {
                case "(":
                    ops.push("(");
                    break;
                case ")":
                    String p;
                    while("(" != (p = ops.pop())) {
                        keys.push(p);
                    }
                    break;
                case "+":
                    while(!ops.empty() && precedenceOf("+") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("+");
                    break;
                case "-":
                    while(!ops.empty() && precedenceOf("-") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("-");
                    break;
                case "*":
                    while(!ops.empty() && precedenceOf("*") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("*");
                    break;
                case "/":
                    while(!ops.empty() && precedenceOf("/") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("/");
                    break;
                case "sin(":
                    while(!ops.empty() && precedenceOf("@") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("@");
                    ops.push("(");
                    break;
                case "cos(":
                    while(!ops.empty() && precedenceOf("#") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("#");
                    ops.push("(");
                    break;
                case "abs(":
                    while(!ops.empty() && precedenceOf("$") < precedenceOf(ops.peek())) {
                        keys.push(ops.pop());
                    }
                    ops.push("$");
                    ops.push("(");
                    break;
                default:
                    keys.push(par[i]);
            }
        }
        while(!ops.empty()) {
            String op = ops.pop();
            if(!op.equals("(")) {
                keys.push(op);
            }
        }

        try {
            result = calculate();
        } catch (EmptyStackException e) {
            e.printStackTrace();
        }
    }

    /**
     * The results getter.
     * @return the result
     */
    public double getResult() {
        return result;
    }

    /**
     * The variables getter.
     * @return the variables string
     */
    public String getVars() {
        return variableLines;
    }

    /**
     * The expression getter.
     * @return the expression string
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Calculates the result with the stack.
     * @return first double value of the stack
     * @throws EmptyStackException if the stack is empty
     */
    private double calculate() throws EmptyStackException {
        if(keys.empty()) {
            throw new EmptyStackException();
        }
        String top = keys.pop();
        if(isDouble(top)) {
            return Double.valueOf(top);
        }
        else if(top.equals("@")) {
            double num = calculate();
            return sinO(num);
        }
        else if(top.equals("#")) {
            double num = calculate();
            return cosO(num);
        }
        else if(top.equals("$")) {
            double num = calculate();
            return absO(num);
        }
        else {
            double b = calculate();
            double a = calculate();
            switch(top) {
                case "+":
                    return a + b;
                case "-":
                    return a - b;
                case "*":
                    return a * b;
                case "/":
                    return a / b;
            }
        }
        return 0;
    }

    /**
     * Finds the precedence of the operator.
     * @param op the operator(+ - * / ( @ # $)
     * @return the precedence
     */
    private static int precedenceOf(String op) {
        switch(op) {
            case "(":
                return 0;
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
        }
        return 3;
    }

    /**
     *
     * @param s
     * @return true if s can convertible to a double, false otherwise
     */
    private static boolean isDouble(String s) {
        try {
            double d = Double.valueOf(s);
        }
        catch(Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Calculates the sine value.
     * @param deg the degree
     * @return the sine
     */
    private static double sinO(double deg) {
        boolean neg = false;
        if(360 <= deg) {
            deg %= 360;
        }
        if(180 <= deg) {
            deg = 360 - deg;
            neg = true;
        }
        if(90 <= deg) {
            deg = 180 - deg;
        }
        double rad = deg2rad(deg);
        double res = 1;
        for(int i = SIN_N-1; i >= 0; i--) {
            res = 1 - rad*rad / (2*i + 2) / (2*i + 3) * res;
        }
        res *= rad;
        return neg ? (-res) : res;
    }

    /**
     * Calculates the cosine value.
     * @param deg the degree
     * @return the cosine
     */
    private static double cosO(double deg) {
        boolean neg = false;
        if(360 <= deg) {
            deg %= 360;
        }
        if(180 <= deg) {
            deg = 360 - deg;
        }
        if(90 <= deg) {
            deg = 180 - deg;
            neg = true;
        }
        double res = sinO(90 - deg);
        res = absO(res);
        return neg ? (-res) : res;
    }

    /**
     * Converts degrees to radians.
     * @param deg the degree
     * @return the radian
     */
    private static double deg2rad(double deg) {

        double rad = deg * PI / 180;
        return rad;
    }

    /**
     * Calculates the absolute value of a number.
     * @param num the number
     * @return the absolute value
     */
    private static double absO(double num) {
        if(0 > num) return -num;
        else return num;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return getVars() + "\r\n" + getExpression() + "\r\n" + getResult();
    }
}
