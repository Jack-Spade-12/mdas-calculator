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
        List<String> segmentedEquation = EquationExtractor.convertToProperEquation(userInput);
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
        String equalSign = ValueChecker.getEquals();

        for (int i = 0; i < valuesLength; i++) {
            // Compare values to ValueChecker's equal sign
            if (values.get(i).compareTo(equalSign) == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Polishes the equation:
     *  1. Converting 'A' or 'a' to accumulated
     *  2. Adding '*' characters in between numbers and parenthesis
     *  3. Counts operators and operands such that operators == operands - 1
     *  4. Make sure that root operatos are followed by open parenthesis
     *  5. Make sure that there are equal numbers of open and close parenthesis
     */
    private static boolean validateEquation(List<String> segmentedEquation) {
        int operands = 0;
        int operators = 0;
        int closeParenthesisCount = 0;
        int openParenthesisCount = 0;
        List<String> equation = segmentedEquation;
        int equationLength = equation.size();
        
        try {
            for (int i = 0; i < equationLength; i++) {
                // Special
                if (ValueChecker.isSpecial(equation.get(i))) {
                    equation.set(i, String.valueOf(accumulated));
                    operands++;
                }
                // Numbers
                else if (ValueChecker.isNumber(equation.get(i))) {
                    operands++;
                }
                // Operators
                else if (ValueChecker.isSymbol(equation.get(i)) 
                    && !ValueChecker.isGrouper(equation.get(i))) {
                    operators++;
                }
                // Groupers
                else if (ValueChecker.isGrouper(equation.get(i))) {
                    if ( (i > 0 && ValueChecker.isNumber(equation.get(i - 1))) || 
                        (i + 1 < equationLength && ValueChecker.isNumber(equation.get(i + 1))) ) {
                        equation.add(i, "*");
                        equationLength++;
                    }

                    // Count open parentheses
                    if (equation.get(i).compareTo("(") == 0) {
                        openParenthesisCount++;
                    }
                    // Count close parentheses
                    else if (equation.get(i).compareTo(")") == 0) {
                        closeParenthesisCount++;
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            CalculatorIO.throwInvalidEquationError(accumulatedInputs, "Equation is incomplete.");
            return false;
        }

        accumulatedInputs.clear();
        accumulatedInputs.addAll(equation);

        // Throw error and reset if operators and operators are not rational
        if (operators != operands - 1) {
            CalculatorIO.throwInvalidEquationError(
                accumulatedInputs,
                "Ratio between operands and operators are not correct.");
            return false;
        }

        // Throw error if open and close parenthsis are not equal
        if (openParenthesisCount != closeParenthesisCount) {
            CalculatorIO.throwInvalidEquationError(
                accumulatedInputs,
                "Open and close parenthesis are not balanced");
            return false;
        }

        return true;
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