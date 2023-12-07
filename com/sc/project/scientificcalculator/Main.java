/**
 * Submitted By:
 * 		Seth Plasabas Cortel
 * 
 * Submitted To:
 *		Prof. Leo Paulo Villarete
 *		
 * Purpose of Submission:
 * 		Requirement for the subject:
 * 		Applications Development and Emerging Technologies
 */
package com.sc.project.scientificcalculator;
import java.util.Scanner;

import com.sc.project.scientificcalculator.tester.MainTest;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("\n\nTest? ");
        if (sc.nextLine().toUpperCase().contains("Y")) {
            new Main().startTests();
        }
        else {
            new Main().startCalculator();
        }

        sc.close();
    }

    private void startTests() {
        new MainTest();
    }

    private void startCalculator() {
        new Calculator().runCalculator();
    }
}
