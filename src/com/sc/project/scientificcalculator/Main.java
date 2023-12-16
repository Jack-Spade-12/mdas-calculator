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
 * 
 * History:
 * 
 *      December 16, 2023 - S. Cortel - Added argument for "--test";
 * 
 */
package com.sc.project.scientificcalculator;

import com.sc.project.scientificcalculator.tester.MainTest;

public class Main {
    public static void main(String[] args) {
        try {
            switch (args[0]) {
                // Test
                case "--test":
                    new MainTest();
                    System.exit(0);
                    break;

                default:
                    // throw InvalidOptionError
                    break;
            }
        }
        catch (NullPointerException npe) {
            // do nothing
        }

        Calculator.runCalculator();
    }
}
