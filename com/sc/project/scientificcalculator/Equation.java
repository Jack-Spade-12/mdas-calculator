/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel
 * 		December 8, 2023 - S. Cortel -  Changed return datatype of convertToProperEquation
 * 									    to List<String>;
 * 									    Converted access to static;
 * 									    Converted acces to ValueChecker to static;
 * 									    Refactored code;
 * 									    Removed global variables;
 *                                      Changed from EquationExtractor to Equation;
 *                                      Added completeEquation() method from Calculator
 *                                      class;
 * 		December 9, 2023 - S. Cortel -  Changed access to certain methods to static;
 *                                      Merged completeEquation() method to 
 *                                      extractEqation() method;
 *                                      
 *                                     
 * Purpose
 * 		
 * 		Processes a string into substrings to be
 * 		used for computing.
 * 
 */
package com.sc.project.scientificcalculator;
import java.util.ArrayList;
import java.util.List;

public class Equation {
	
    /**
	 * This method converts a whole <code>String</code> 
	 * of an equation into a <code>String[]</code>
	 * for processing
     * 
     *  1. Updates 'a' or 'A' characters to actual values
     *  2. Adds '*' symbols right next to grouper characters when right next
     *      to numbers
     * 
	 * @param equation in form of <code>String</code>
     * @param lastAccumulated value of the calculator
	 * @return <code>List<String></code>
	 */
	public static List<String> extractEquation (String equation, double lastAccumulated) {
		
		StringBuilder temporary = new StringBuilder();
		List<String> equationList = new ArrayList<String>();
		char[] equationCharArray = equation.toCharArray();
        int equationCharCount = 0;
		
		// Determines which characters should be grouped together to create a number
		for (char equationChar : equationCharArray) {
			
			// Whitespaces treated as delimiter
			if (Character.isWhitespace(equationChar)) {
				pushToList(equationList, temporary, equationChar);
			}
			
			// If number simply add to temporary
			else if (ValueChecker.isDigit(equationChar)) {
				temporary.append(String.valueOf(equationChar));
			}
            
            // If special change to last accumulated calculator value
            else if (ValueChecker.isSpecial(equationChar)) {
                pushToList(equationList, temporary, lastAccumulated);
            }

			// If equal sign push to list
			else if (equationChar == ValueChecker.EQUALS) {
				pushToList(equationList, temporary, equationChar);
			}
			
			// Check if a value is a symbol
			else if (ValueChecker.isSymbol(equationChar)) {

				// Validate if minus is to be negative or just minus
				if (equationChar == ValueChecker.MINUS) {
					// Minus is minus
					if (ValueChecker.isNumber(peekBack(equationList))) {
						pushToList(equationList, temporary, equationChar);
					}
					// Minus is negative
					else {
						temporary.append(String.valueOf(equationChar));
					}
				}
                
                // Add '*' to  numbers next to groupers
                else if (ValueChecker.isGrouper(equationChar)) {
                    // Before the parenthesis: number then parenthesis
                    if (equationChar == ValueChecker.OPEN_PARENTHESIS && temporary.length() > 0) {
                        pushToList(equationList, temporary, ValueChecker.MULTIPLY);
                        pushToList(equationList, temporary, equationChar);
                    }
                    // After the parenthesis: parenthesis then number
                    else if (equationChar == ValueChecker.CLOSE_PARENTHESIS 
                        && ValueChecker.isDigit(peekAhead(equation, equationCharCount))) {
                         pushToList(equationList, temporary, equationChar);
                         pushToList(equationList, temporary, ValueChecker.MULTIPLY);
                    }
                }
				
				// Other symbols are automatically added to the list
				else {
					pushToList(equationList, temporary, equationChar);
				}
			}

            equationCharCount++;
		}
		
		pushToList(equationList, temporary, ' ');

		return equationList;	
	}

    /**
	 * This method converts a whole <code>String</code> 
	 * of an equation into a <code>String[]</code>
	 * for processing
	 * 
	 * @param equation in form of <code>String</code>
     * @return <code>List<String></code>
	 */
	public static List<String> extractEquation (String equation) {
        return extractEquation(equation, 0D);
    }

    /**
	 * Adds the accumulated value to the equation list
	 * 
     * @param equationList so far
     * @param temporary accumulated number value
	 * @param accumulated to add in form of <code>double</code>
	 */
	private static void pushToList(List<String> equationList, StringBuilder temporary, double accumulated) {
		// Add the accumulated number first if there is
		if (temporary.length() > 0) {
			equationList.add(temporary.toString());
			temporary.setLength(0);
		}
		
		equationList.add(String.valueOf(accumulated));
	}

    /**
	 * Adds the value to the equation list
	 * 
     * @param equationList so far
     * @param temporary accumulated number value
	 * @param value to add in form of <code>char</code>
	 */
	private static void pushToList(List<String> equationList, StringBuilder temporary, char value) {
		// Add the accumulated number first if there is
		if (temporary.length() > 0) {
			equationList.add(temporary.toString());
			temporary.setLength(0);
		}
		
		// Do not add whitespaces
		if (!Character.isWhitespace(value)) {
			equationList.add(String.valueOf(value));
		}
	}

	/**
	 * Gets the last pushed <code>String</code> in the equation list
	 * 
     * @param equationList in form of <code>List</code>
	 * @return <code>String</code>
	 */
	private static String peekBack(List<String> equationList) {
		try {
			return equationList.get(equationList.size() - 1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Gets the character at the next index
	 * 
     * @param equation in form of <code>String</code>
     * @param equationCharCount of the current character in form of <code>int</code>
	 * @return <codechar</code>
	 */
	private static char peekAhead(String equation, int equationCharCount) {
		try {
			return equation.charAt(equationCharCount + 1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return '\0';
		}
	}
    





	// /**
    //  * Polishes the equation:
    //  *  1. Converting 'A' or 'a' to accumulated
    //  *  2. Adding '*' characters in between numbers and parenthesis
    //  *  3. Counts operators and operands such that operators == operands - 1
    //  *  4. Make sure that root operatos are followed by open parenthesis
    //  *  5. Make sure that there are equal numbers of open and close parenthesis
    //  */
    // private static boolean validateEquation(List<String> segmentedEquation) {
    //     int operands = 0;
    //     int operators = 0;
    //     int closeParenthesisCount = 0;
    //     int openParenthesisCount = 0;
    //     List<String> equation = segmentedEquation;
    //     int equationLength = equation.size();
        
    //     try {
    //         for (int i = 0; i < equationLength; i++) {
    //             // Special
    //             if (ValueChecker.isSpecial(equation.get(i))) {
    //                 equation.set(i, String.valueOf(accumulated));
    //                 operands++;
    //             }
    //             // Numbers
    //             else if (ValueChecker.isNumber(equation.get(i))) {
    //                 operands++;
    //             }
    //             // Operators
    //             else if (ValueChecker.isSymbol(equation.get(i)) 
    //                 && !ValueChecker.isGrouper(equation.get(i))) {
    //                 operators++;
    //             }
    //             // Groupers
    //             else if (ValueChecker.isGrouper(equation.get(i))) {
    //                 if ( (i > 0 && ValueChecker.isNumber(equation.get(i - 1))) || 
    //                     (i + 1 < equationLength && ValueChecker.isNumber(equation.get(i + 1))) ) {
    //                     equation.add(i, "*");
    //                     equationLength++;
    //                 }

    //                 // Count open parentheses
    //                 if (equation.get(i).compareTo("(") == 0) {
    //                     openParenthesisCount++;
    //                 }
    //                 // Count close parentheses
    //                 else if (equation.get(i).compareTo(")") == 0) {
    //                     closeParenthesisCount++;
    //                 }
    //             }
    //         }
    //     }
    //     catch (ArrayIndexOutOfBoundsException e) {
    //         CalculatorIO.throwInvalidEquationError(accumulatedInputs, "Equation is incomplete.");
    //         return false;
    //     }

    //     accumulatedInputs.clear();
    //     accumulatedInputs.addAll(equation);

    //     // Throw error and reset if operators and operators are not rational
    //     if (operators != operands - 1) {
    //         CalculatorIO.throwInvalidEquationError(
    //             accumulatedInputs,
    //             "Ratio between operands and operators are not correct.");
    //         return false;
    //     }

    //     // Throw error if open and close parenthsis are not equal
    //     if (openParenthesisCount != closeParenthesisCount) {
    //         CalculatorIO.throwInvalidEquationError(
    //             accumulatedInputs,
    //             "Open and close parenthesis are not balanced");
    //         return false;
    //     }

    //     return true;
    // }
}
