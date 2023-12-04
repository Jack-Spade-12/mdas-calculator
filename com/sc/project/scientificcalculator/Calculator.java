/**
 * History
 * 		
 * 		December 4, 2023 - S. Cortel - Modified
 *      December 5, 2023 - S. Cortel - Modified
 * 
 * Purpose
 * 		
 * 		Base program for the calculator
 * 
 */

package com.sc.project.scientificcalculator;
import java.io.IOException;
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
	
    public void runCalculator() throws InterruptedException, IOException {
		String userInput;		
		printHeader();
        printHelp();
		
        do {
            System.out.print("\n: ");
			userInput = scanner.nextLine();
			processInput(userInput);
            
            // Continuously print out the equation
            System.out.print("Equation : ");
            printAccumulated();
		}
        while (isContinue(userInput));

        executeExit();
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

						+ "To perform nested root operations,           \n"
						+ "use a singline-line input calculation.       \n\n"

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
     * Processes the user input; cleaning, segmenting, and
     * computing the values
     * 
     * @param userInput to be processed in form of <code>String</code>
     */
    private void processInput(String userInput) {
        String[] segmentedEquation = equationExtractor.convertToProperEquation(userInput);
        int equalSignPosition = getEqualSignPosition(segmentedEquation);
        
        // Check if accumulate or compute
        // Compute
        if (equalSignPosition > -1) {

            // Check that equal sign is not in the middle of the equation
            if (equalSignPosition < segmentedEquation.length - 1) {
                throwInvalidEquationError(segmentedEquation);
                System.out.println("Last inputs are discarded.");
                return;
            }
            
            // Accumulate
            accumulatedInputs.addAll(Arrays.asList(segmentedEquation));
            
            // Polish equation and check if valid
            if (validateEquation(segmentedEquation)) {
                
                // Convert equation to postfix notation
                segmentedEquation = infixToPostfixConversion
                    .convertToPostfix(accumulatedInputs.toArray(
                    new String[accumulatedInputs.size()]));

                // Compute the equation
                computeEquation(segmentedEquation);

                // Output the equation
                outputAccumulated();
            }
        }
        // Accumulate only
        else {
            accumulatedInputs.addAll(Arrays.asList(segmentedEquation));
        }
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
     * Throws an error in the equation.
     * 
     * @param values to be added to error in form of <code>String[]</code>
     */
    private void throwInvalidEquationError(String[] values) {
        System.out.print("Invalid Formula: ");
        
        printAccumulated(false);
        printCurrentInput(values);
    }

    /**
     * Throws an error in the equation.
     * 
     * @param operators count to be added to error in form of <code>int</code>
     * @param operands count to be added to error in form of <code>int</code>
     */
    private void throwInvalidEquationError(int operators, int operands) {
        System.out.println("Operator " + operators + " to operand " + operands + " ratio is not correct");
        
        throwInvalidEquationError(null);

        accumulatedInputs.clear();
        System.out.println("Calculator has been reset");
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
     * Polishes the equation:
     *  1. Converting 'A' or 'a' to accumulated
     *  2. Adding '*' characters in between numbers and parenthesis
     *  3. Counts operators and operands such that operators == operands - 1
     */
    private boolean validateEquation(String[] segmentedEquation) {
        int operands = 0;
        int operators = 0;
        String[] equation = segmentedEquation;
        int equationLength = equation.length;
        
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
            }
        }

        accumulatedInputs.clear();
        accumulatedInputs.addAll(Arrays.asList(equation));

        // Throw error and reset if equation is not correct
        if (operators != operands - 1) {
            throwInvalidEquationError(operators, operands);
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
        try {
            // if (valueChecker.isSymbol(equation[2]) && !valueChecker.isGrouper(equation[2])) {
            //     equation[0] = computer.compute(equation[0], equation[1], equation[2]);
            // }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
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
     * The accumulated is a double and this method
     * removes the '.0' when the accumulated has no
     * floating value. Returns undefined when the
     * result is NaN (Not a Number) and Infinite.
     */
	private void outputAccumulated() {
		if (Double.isNaN(accumulated) || Double.isInfinite(accumulated)) {
            System.out.print("= undefined");
        }
		else if (accumulated % 1 == 0.0) {
            System.out.printf("= %.0f", accumulated);
        }
		else if (accumulated % 1 != 0.0) {
            System.out.print("= " + accumulated);
        }
        accumulatedInputs.clear();
	}

    /**
     * Closes the scanner and exits the program
     */
    private void executeExit() {
        System.out.println("\n.\n.\n.\nCalculator has been terminated.");
        scanner.close();
        System.exit(0);
    }
		
    
    
    
    
    
    
    // private boolean validateEquation(String[] equation) {
		
	// 	/*
	// 	 * This validates if the equation is valid such that the variables
	// 	 * are 1 more than the signs [excluding: parenthesis, commas, and equals].
	// 	 *  
	// 	 */
		
	// 	int countVariables = 0;
	// 	int countSigns = 0;
		
	// 	try {
	// 		for (String i : equation) {
	// 			if (equate.isSymbol(i) && !equate.isDiscarded(i)) {
	// 				countSigns++;
	// 			}
	// 			else if (equate.isNumber(i) || (equate.isSpecial(i.charAt(0)))) {
	// 				countVariables++;
	// 			}
	// 		}
	// 	}
	// 	catch (Exception e) {}
		
	// 	if (countSigns == countVariables - 1)
	// 		return true;
	// 	return false;
	// }
		
	// private void executeCalculator(String[] strEquation) {
	// 	String[] equation = fix.convertToPostfix(strEquation);				// Converts the proper equation (infix notation) into postfix notation			
	// 	accumulated = compute(equation);								// Calculates the final equation and stores it to `Accumulated`
	// 	outputAccumulated();												// Output the result for the computation
	// }

	// private void inputAccumulated(String input) {							// Verifies if inputAccum is to be accumulated or to be thrown
	// 	char inputFirstChar = 0;
	// 	char accumLastChar = 0;

	// 	try {
	// 		try {
	// 			accumLastChar = inputAccum.charAt(inputAccum.length() - 1);
	// 		}
	// 		catch (Exception e) {
	// 			accumLastChar = 0;
	// 		}
			
	// 		for (int i=0; i<input.length(); i++) {
	// 			if (!Character.isWhitespace(input.charAt(i))) {
	// 				inputFirstChar = input.charAt(i);
	// 				break;
	// 			}
	// 		}
	// 	}
	// 	catch (Exception e) {
	// 		inputFirstChar = 0;
	// 	}
		
	// 	if (accumLastChar == '=')											// Determines if user input should be concatenated to the input accum or not
	// 		inputAccum = "";
	// 	else if ((equate.isDigit(accumLastChar) || equate.isSpecial(accumLastChar) || equate.isDiscarded(accumLastChar)) && (accumLastChar != '(' && accumLastChar != ','))
	// 		if ((equate.isDigit(inputFirstChar) || equate.isSpecial(inputFirstChar) || equate.isDiscarded(inputFirstChar) || inputFirstChar == 'r' || inputFirstChar == 'R') && inputFirstChar != ')')
	// 			inputAccum = "";
		
	// 	if (equate.isNumber(input) || equate.isSpecial(inputFirstChar)) {
	// 		if (state == 1) {
	// 			inputAccum = inputAccum.concat(input + ",");
	// 			state = 2;
	// 		}
			
	// 		else if (state == 2) {
	// 			inputAccum = inputAccum.concat(input + ") ");
	// 			state = 0;
	// 		}
			
	// 		else if (state == 0){
	// 			inputAccum = inputAccum.concat(input);
	// 		}		
	// 	}
		
	// 	else {
	// 		if (input.toUpperCase().equals("R")) {
	// 			state = 1;
	// 			inputAccum = inputAccum.concat(" r(");
	// 		}
			
	// 		else {
	// 			inputAccum = inputAccum.concat(input);
	// 		}
	// 	}

		
	// 	String[] equation = equate.convertToProperEquation(inputAccum);		// Converts inputAccum into an array of operators and operands
		
	// 	System.out.print("= ");												// Shows the equation
	// 	for (String i : equation)											//
	// 		System.out.print(i + " ");										//
	// 	System.out.println();												//
		
	// 	if (calculate.validateEquation(equation))
	// 		calculate.executeCalculator(equation);
	// }
} 