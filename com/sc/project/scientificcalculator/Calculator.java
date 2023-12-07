/**
 * History
 * 		
 * 		December 4, 2023 - S. Cortel - Modified
 *      December 5, 2023 - S. Cortel - Modified
 *      December 6, 2023 - S. Cortel - Modified
 *      December 7, 2023 - S. Cortel - Modified
 *      December 8, 2023 - S. Cortel - Removed old code; organized methods
 * 
 * Purpose
 * 		
 * 		Base program for the calculator
 * 
 */

package com.sc.project.scientificcalculator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calculator {
	private Scanner scanner = new Scanner(System.in);
	private Computer computer = new Computer();
    private ValueChecker valueChecker = new ValueChecker();
	private EquationExtractor equationExtractor = new EquationExtractor();
	private InfixToPostfixConversion infixToPostfixConversion = new InfixToPostfixConversion();

    // Accumulates user input, useful for multiple continuous inputs
	private List<String> accumulatedInputs = new ArrayList<String>();		
    // Stores result of calculations
	private double accumulated = 0D;										
	
    /**
     * This method is the main method of the class
     */
    public void runCalculator() {
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
    private void processInput(String userInput) {
        String[] segmentedEquation = equationExtractor.convertToProperEquation(userInput);
        String[] fullEquation;
        int equalSignPosition = getEqualSignPosition(segmentedEquation);
         
        // Check if accumulate or compute
        // Compute
        if (equalSignPosition > -1) {

            // Check that equal sign is not in the middle of the equation
            if (equalSignPosition < segmentedEquation.length - 1) {
                throwInvalidInputError("Equal sign must be at the end of the equation", 
                    segmentedEquation);
                System.out.println("Last inputs are discarded.");
                return;
            }
            
            // Accumulate
            accumulatedInputs.addAll(Arrays.asList(segmentedEquation));

            // Convert List to String[]
            fullEquation = accumulatedInputs.toArray(new String[accumulatedInputs.size()]);

            // Polish equation and check if valid
            if (validateEquation(fullEquation)) {
                
                // Print the equation
                System.out.print("> ");
                printCurrentInput(fullEquation);

                // Compute the equation
                computeEquation(fullEquation);
                
                // Output the equation
                outputAccumulated();
                return;
            }
            // Exit method when formula fails
            else {
                return;
            }
        }
        // Accumulate only
        else {
            accumulatedInputs.addAll(Arrays.asList(segmentedEquation));
        }

        // Continuously print out the equation
        System.out.print("> ");
        printAccumulated();
    }

    /**
     * Gets the position of the equal sign in the values.
     * Returns -1 if no equal sign is found.
     * 
     * @param values to check in form of <code>String[]</code>
     * @return <code>int</code>
     */
    public int getEqualSignPosition(String[] values) {
        int valuesLength = values.length;
        String equalSign = valueChecker.getEquals();

        for (int i = 0; i < valuesLength; i++) {
            // Compare values to ValueChecker's equal sign
            if (values[i].compareTo(equalSign) == 0) {
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
    private boolean validateEquation(String[] segmentedEquation) {
        int operands = 0;
        int operators = 0;
        int closeParenthesisCount = 0;
        int openParenthesisCount = 0;
        String[] equation = segmentedEquation;
        int equationLength = equation.length;
        
        try {
            for (int i = 0; i < equationLength; i++) {
                // Special
                if (valueChecker.isSpecial(equation[i])) {
                    equation[i] = String.valueOf(accumulated);
                    operands++;
                }
                // Numbers
                else if (valueChecker.isNumber(equation[i])) {
                    operands++;
                }
                // Operators
                else if (valueChecker.isSymbol(equation[i]) && !valueChecker.isGrouper(equation[i])) {
                    operators++;
                }
                // Groupers
                else if (valueChecker.isGrouper(equation[i])) {
                    if ( (i > 0 && valueChecker.isNumber(equation[i - 1])) || 
                        (i + 1 < equationLength && valueChecker.isNumber(equation[i + 1])) ) {
                        equation = addValueAtIndex(equation, "*", i);
                        equationLength++;
                    }

                    // Count open parentheses
                    if (equation[i].compareTo("(") == 0) {
                        openParenthesisCount++;
                    }
                    // Count close parentheses
                    else if (equation[i].compareTo(")") == 0) {
                        closeParenthesisCount++;
                    }
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            throwInvalidEquationError("Equation is incomplete.");
            return false;
        }

        accumulatedInputs.clear();
        accumulatedInputs.addAll(Arrays.asList(equation));

        // Throw error and reset if operators and operators are not rational
        if (operators != operands - 1) {
            throwInvalidEquationError(
                "Ratio between operands and operators are not correct.");
            return false;
        }

        // Throw error if open and close parenthsis are not equal
        if (openParenthesisCount != closeParenthesisCount) {
            throwInvalidEquationError(
                "Open and close parenthesis are not balanced");
            return false;
        }

        return true;
    }

    /**
     * Inserts a value in the <code>String[]</code> at a
     * specific index
     * 
     * @param array to modify in form of <code>String[]</code>
     * @param value to insert in form of <code>String</code>
     * @param index position of where the value is inserted in 
     * form of <code>int</code>
     * @return <code>String[]</code>
     */
    public String[] addValueAtIndex(String[] array, String value, int index) {
        int arrayLength = array.length;
        String[] modifiedArray = new String[arrayLength + 1];
        
        for (int i = 0, j = 0; i < arrayLength; i++, j++) {
            if (i == index) {
                modifiedArray[j] = value;
                j++;
            }
            modifiedArray[j] = array[i];
        }

        return modifiedArray;
    }

    /**
     * Recursively computes the equation and places the final output
     * in the accumulated variable
     */
    private void computeEquation(String[] equation) {
        List<String> processedEquation = new ArrayList<String>();
        processedEquation.addAll(Arrays.asList(equation));
        
        
    }

    /**
     * Checks the input if it contains the string 'XX' or 'xx.
     * 
     * @param equation to check in form of <code>String</code>
     * @return <code>boolean</code>
     */
	public boolean isContinue(String equation) {
        return !equation.toUpperCase().contains("XX");
	}

    /**
     * Prints the header of the calculator
     */
    private void printHeader() {
        ProcessBuilder cls = new ProcessBuilder("cmd", "/c", "cls");
		try {
			cls.inheritIO().start().waitFor();
		}
		catch (Exception e) {}

		System.out.println("\n|===============================|");
		System.out.println("|                               |");
		System.out.println("| >>  SCIENTIFIC CALCULATOR  << |");
		System.out.println("|         Seth Cortel           |");
		System.out.println("|                               |");
		System.out.println("|===============================|");
    }

    /**
     * Print help
     */
    private void printHelp() {
        // Best to use TextBlocks but it needs JDK 15
        System.out.print("To refer to your previous                     \n"
						+ "output, input: 'A' / 'a'                     \n\n"

						+ "To perform root operations,                  \n"
						+ "input: r(BASE, EXPONENT)                     \n\n"

						// + "To perform nested root operations,           \n"
						// + "use a single-line input calculation.         \n\n"

						+ "This calculator accepts single               \n"
						+ "line input and multiple                      \n"
						+ "continuous inputs.                           \n\n"

                        + "Please be informed that characters           \n"
                        + "and symbols that are not supported in        \n"
                        + "this calculator are discarded in the         \n"
                        + "process.                                     \n\n"

						+ "To terminate the program,                    \n"
						+ "input 'XX // xx'.                            \n");
    }

    /**
     * The accumulated is a double and this method
     * removes the '.0' when the accumulated has no
     * floating value. Returns undefined when the
     * result is NaN (Not a Number) and Infinite.
     */
	private void outputAccumulated() {
		if (Double.isNaN(accumulated) || Double.isInfinite(accumulated)) {
            System.out.println("= undefined");
        }
		else if (accumulated % 1 == 0.0) {
            System.out.printf("= %.0f\n", accumulated);
        }
		else if (accumulated % 1 != 0.0) {
            System.out.println("= " + accumulated);
        }
        accumulatedInputs.clear();
	}

    /**
     * Prints out the accumulated equation separated by ' '
     * character
     */
    private void printAccumulated(boolean hasNewLine) {
        StringBuilder inputs = new StringBuilder();
        for (String accumulatedInput : accumulatedInputs) {
            inputs.append(accumulatedInput);
            inputs.append(" ");
        }
        System.out.print(inputs.toString());
        if (hasNewLine) {
            System.out.println();
        }
    }

    /**
     * Prints out the accumulated equation separated by ' '
     * character followed by a '\n' character
     */
    private void printAccumulated() {
        printAccumulated(true);
    }

    /**
     * Prints out the accumulated inputs separated by ' '
     * character
     */
    private void printCurrentInput(String[] values) {
        StringBuilder inputs = new StringBuilder();
        try {
            for (String value : values) {
                inputs.append(value);
                inputs.append(" ");
            }
            System.out.println(inputs.toString());
        }
        catch (NullPointerException npe) {}
    }

    /**
     * Shows an error message in the calculator and clears accumulated input
     * 
     * @param errorMessage to show in the calculator in form of <code>String</code>
     */
    private void throwInvalidEquationError(String errorMessage) {
        System.out.println("Invalid Formula: " + errorMessage);
        printAccumulated();
        accumulatedInputs.clear();
        System.out.println("Calculator has been cleared");
    }

    /**
     * Shows an error message in the calculator and clears latest input
     * 
     * @param errorMessage to show in the calculator in form of <code>String</code>
     * @param inputValues recently added
     */
    private void throwInvalidInputError(String errorMessage, String[] inputValues) {
        System.out.println("Invalid Input: " + errorMessage);
        printAccumulated(false);
        printCurrentInput(inputValues);
        System.out.println("Input values has been cleared");
    }

    /**
     * Closes the scanner and exits the program
     */
    private void executeExit() {
        System.out.println("\n.\n.\n.\nCalculator has been terminated.");
        scanner.close();
        System.exit(0);
    }
} 