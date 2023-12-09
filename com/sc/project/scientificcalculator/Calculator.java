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
 *      December 10, 2023 - S. Cortel - Refactored Calculator class;
 *                                      Extended CalculatorIO class;
 *                                      Changed processInput() to public;
 *                                      Added a failsafe if when the only input
 *                                      is '=';             
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

public class Calculator extends CalculatorIO {
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
		printHeader();
        printHelp();
		
        do {
            System.out.print("\n: ");
			userInput = scanner.nextLine();
			processInput(userInput);
		}
        while (isContinue(userInput));

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
        
        // Check if accumulate or compute
        // Compute
        if (Equation.getEqualSignPosition(segmentedEquation) > -1) {

            // Check that equal sign is not in the middle of the equation
            if (!Equation.isEqualSignAtEquationEnd(segmentedEquation)) {
                throwInvalidInputError(
                    accumulatedInputs,
                    "Equal sign must be at the end of the equation", 
                    segmentedEquation);
                System.out.println("Last inputs are discarded.");
                return;
            }
            
            // Accumulate
            accumulatedInputs.addAll(segmentedEquation);
            
            // Fail safe when only input is '='
            if (accumulatedInputs.size() == 1 
                && accumulatedInputs.get(0).charAt(0) 
                == ValueChecker.EQUALS) {
                // Output the equation
                outputAccumulated(accumulated);
                return;
            }

            // Polish equation and check if valid
            if (Equation.isValidEquation(accumulatedInputs)){
                
                // Print the equation
                System.out.print("> ");
                printCurrentInput(accumulatedInputs);

                // Compute the equation
                accumulated = Computer.computeEquation(accumulatedInputs);
                
                // Output the equation
                outputAccumulated(accumulated);
                
                accumulatedInputs.clear();
                return;
            }
            // Exit method when formula fails
            else {
                throwInvalidEquationError(accumulatedInputs);
                accumulatedInputs.clear();
                return;
            }
        }
        // Accumulate only
        else {
            accumulatedInputs.addAll(segmentedEquation);
        }

        // Continuously print out the equation
        System.out.print("> ");
        printAccumulated(accumulatedInputs);
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