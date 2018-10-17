package com.aw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.aw.constants.OperateConstants;
import com.aw.impl.DefaultCalculator;


public class Executor {

    private static Calculator calculator = new DefaultCalculator();

    public static void main(String[] args) throws IOException {
        new Executor().execute();
    }

    private void execute() throws IOException {

        while (true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Please enter a string : ");
            String var = br.readLine();
            if (var.equals("q")) {
                System.exit(0);
            }

            String result = calculator.calculate(parseInput(var));
            System.out.println("\033[0;34m" + "stack: " + result + "\033[5m");
        }
    }

    private String[] parseInput(String input) {
        return input.split(OperateConstants.SPACE);
    }
}
