/**
 * History
 * 		
 * 		December 4, 2023 - S. Cortel - Modified
 *      December 5, 2023 - S. Cortel - Modified
 *      December 6, 2023 - S. Cortel - Modified
 *      December 7, 2023 - S. Cortel - Modified
 *      December 8, 2023 - S. Cortel - Removed old code; 
 *                                     Organized methods;
 *                                     Removed IO methods;
 *                                     Converted String[] to List<String>;
 * 
 * Purpose
 * 		
 * 		Base program for the calculator
 * 
 */

package com.sc.project.scientificcalculator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
	private static Scanner scanner = new Scanner(System.in);
	
    // Accumulates user input, useful for multiple continuous inputs
	private static List<String> accumulatedInputs = new ArrayList<String>();		
    // Stores result of calculations
	private static double accumulated = 0D;										
	
    /**
     * This method is the main method of the class
     */
    public static void runCalculator() {
		String userInput;		
		CalculatorIO.printHeader();
        CalculatorIO.printHelp();
		
        do {
            System.out.print("\n: ");
			userInput = scanner.nextLine();
			processInput(userInput);
		}
        while (CalculatorIO.isContinue(userInput));

        executeExit();
	}

    /**
     * Processes the user input; cleaning, segmenting, and
     * computing the values
     * 
     * @param userInput to be processed in form of <code>String</code>
     */
    private static void processInput(String userInput) {
        List<String> segmentedEquation = Equation.extractEquation(userInput, accumulated);
        int equalSignPosition = getEqualSignPosition(segmentedEquation);
         
        // Check if accumulate or compute
        // Compute
        if (equalSignPosition > -1) {

            // Check that equal sign is not in the middle of the equation
            if (equalSignPosition < segmentedEquation.size() - 1) {
                CalculatorIO.throwInvalidInputError(
                    accumulatedInputs,
                    "Equal sign must be at the end of the equation", 
                    segmentedEquation);
                System.out.println("Last inputs are discarded.");
                return;
            }
            
            // Accumulate
            accumulatedInputs.addAll(segmentedEquation);

            // Polish equation and check if valid
            if (validateEquation(accumulatedInputs)) {
                
                // Print the equation
                System.out.print("> ");
                CalculatorIO.printCurrentInput(accumulatedInputs);

                // Compute the equation
                Computer.computeEquation(accumulatedInputs);
                
                // Output the equation
                CalculatorIO.outputAccumulated(accumulated);
                return;
            }
            // Exit method when formula fails
            else {
                return;
            }
        }
        // Accumulate only
        else {
            accumulatedInputs.addAll(segmentedEquation);
        }

        // Continuously print out the equation
        System.out.print("> ");
        CalculatorIO.printAccumulated(accumulatedInputs);
    }

    /**
     * Gets the position of the equal sign in the values.
     * Returns -1 if no equal sign is found.
     * 
     * @param values to check in form of <code>List<String></code>
     * @return <code>int</code>
     */
    public static int getEqualSignPosition(List<String> values) {
        int valuesLength = values.size();
        String equalSign = String.valueOf(ValueChecker.EQUALS);

        for (int i = 0; i < valuesLength; i++) {
            // Compare values to ValueChecker's equal sign
            if (values.get(i).compareTo(equalSign) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Closes the scanner and exits the program
     */
    private static void executeExit() {
        System.out.println("\n.\n.\n.\nCalculator has been terminated.");
        scanner.close();
        System.exit(0);
    }
} 