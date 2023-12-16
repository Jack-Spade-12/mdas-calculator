/**
 * History
 * 		
 * 		December 8, 2023 - S. Cortel - Created;
 *                                     Converted String[] to List<String>;
 *                                     Updated method parameters to accept 
 *                                     accumulatedInputs;
 *                                     Converted method access modifiers to static;
 *      December 10, 2023 - S. Cortel - Updated throwInvalidInputError(); 
 * 
 * Purpose
 * 		
 * 		Refactored IO component for Calculator class
 * 
 */
package com.sc.project.scientificcalculator;

import java.util.List;

public class CalculatorIO {
    
    /**
     * Checks the input if it contains the string 'XX' or 'xx.
     * 
     * @param equation to check in form of <code>String</code>
     * @return <code>boolean</code>
     */
	public static boolean isContinue(String equation) {
        return !equation.toUpperCase().contains("XX");
	}

    /**
     * Prints the header of the calculator
     */
    public static void printHeader() {
        ProcessBuilder cls = new ProcessBuilder("cmd", "/c", "cls");
		try {
			cls.inheritIO().start().waitFor();
		}
		catch (Exception e) {}

        System.out.println(
              "\n|===============================|"
		    + "\n|                               |"
		    + "\n| >>  SCIENTIFIC CALCULATOR  << |"
		    + "\n|           S. Cortel           |"
		    + "\n|                               |"
		    + "\n|===============================|"
        );
    }

    /**
     * Print help
     */
    public static void printHelp() {
        // Best to use TextBlocks but it needs JDK 15
        System.out.print(
            "To refer to your previous                      \n"
			+ "output, input: 'A' / 'a'                     \n\n"

			+ "To perform root operations,                  \n"
			+ "input: r(BASE, EXPONENT)                     \n\n"

			// + "To perform nested root operations,        \n"
			// + "use a single-line input calculation.      \n\n"

			+ "This calculator accepts single               \n"
			+ "line input and multiple                      \n"
			+ "continuous inputs.                           \n\n"

            + "Please be informed that characters           \n"
            + "and symbols that are not supported in        \n"
            + "this calculator are discarded in the         \n"
            + "process.                                     \n\n"

			+ "To terminate the program,                    \n"
			+ "input 'XX // xx'.                            \n"
        );
    }

    /**
     * The value is a double and this method
     * removes the '.0' when the accumulated has no
     * floating value. Returns undefined when the
     * result is NaN (Not a Number) and Infinite.
     * 
     * @param value to ouput in form of <code>double</code>
     */
	public static void outputAccumulated(double value) {
		if (Double.isNaN(value) || Double.isInfinite(value)) {
            System.out.println("= undefined");
        }
		else if (value % 1 == 0.0) {
            System.out.printf("= %.0f\n", value);
        }
		else if (value % 1 != 0.0) {
            System.out.println("= " + value);
        }
	}

    /**
     * Prints out the accumulated equation separated by ' '
     * character
     */
    public static void printAccumulated(List<String> accumulatedInputs, boolean hasNewLine) {
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
    public static void printAccumulated(List<String> accumulatedInputs) {
        printAccumulated(accumulatedInputs, true);
    }

    /**
     * Prints out the accumulated inputs separated by ' '
     * character
     */
    public static void printCurrentInput(List<String> values) {
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
    public static void throwInvalidEquationError(List<String> accumulatedInputs) {

        System.out.print("Invalid Equation ");
        printAccumulated(accumulatedInputs);
        System.out.println("Calculator has been cleared");
    }

    /**
     * Shows an error message in the calculator and clears latest input
     * 
     * @param errorMessage to show in the calculator in form of <code>String</code>
     * @param inputValues recently added
     */
    public static void throwInvalidInputError(List<String> accumulatedInputs, 
        String errorMessage, List<String> inputValues) {

        System.out.println("Invalid Input: " + errorMessage);
        printAccumulated(accumulatedInputs, false);
        printCurrentInput(inputValues);
        System.out.println("Input values has been cleared");
    }

}
