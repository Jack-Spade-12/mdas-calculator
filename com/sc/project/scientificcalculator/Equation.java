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
 *      December 10, 2023 - S. Cortel - Fixed bug for groupers;
 *                                      Added additional logic to make negative 
 *                                      signs as '-1 * <value>' instead of having
 *                                      '-<value>';
 *                                      Polished pushToList() with double param;
 *                                      Added isValidEquation(), isEqualSignAtEquationEnd()
 *                                      and getEqualSignPosition() methods;
 *      December 16, 2023 - S. Cortel - Fixed bug caused by whitespace next to 
 *                                      open parenthesis and close parenthesis followed
 *                                      by an open parenthesis;
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
                        temporary.append(String.valueOf(equationChar) + "1");
                        pushToList(equationList, temporary, ValueChecker.MULTIPLY);
					}
				}
                
                // Add '*' to  numbers next to groupers
                else if (ValueChecker.isGrouper(equationChar)) {
                    // number then parenthesis or close parenthesis then open parenthesis
                    if (isMultiplyValidBeforeOpenParenthesis(equationChar, equationList, 
                        temporary.toString())) {

                        pushToList(equationList, temporary, ValueChecker.MULTIPLY);
                        pushToList(equationList, temporary, equationChar);
                    }
                    // parenthesis then number
                    else if (equationChar == ValueChecker.CLOSE_PARENTHESIS 
                        && ValueChecker.isDigit(peekAheadIgnoreWhitespace(equation, equationCharCount))) {
                        pushToList(equationList, temporary, equationChar);
                        pushToList(equationList, temporary, ValueChecker.MULTIPLY);
                    }
                    // Everything else
                    else {
                        pushToList(equationList, temporary, equationChar);
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
     * Checks if a multiply '*' symbol is valid to be put before
     * the open parenthesis '(' character; entire method can fit in a single
     * if statement but is separated for readability
     * 
     * @param currentChar to test in form of <code>char</code>
     * @param equationList so far in form of <code>List</code>
     * @param temporary accumulated numbers in form of <code>String</code>
     * @return <code>boolean</code>
     */
    private static boolean isMultiplyValidBeforeOpenParenthesis(char currentChar, 
        List<String> equationList, String temporary) {
        String peekBack = peekBack(equationList);
        
        // Check if current char is open parenthesis or if '(' is the start of equation
        if (currentChar != ValueChecker.OPEN_PARENTHESIS || peekBack == null) {
            return false;
        }

        // Check if number then open parenthesis or close parenthesis then open parenthesis
        return temporary.length() > 0 || ValueChecker.isNumber(peekBack) 
            || peekBack.charAt(0) == ValueChecker.CLOSE_PARENTHESIS;
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
	 * Adds the accumulated value to the equation list
	 * 
     * @param equationList so far
     * @param temporary accumulated number value
	 * @param accumulated to add in form of <code>double</code>
	 */
	private static void pushToList(List<String> equationList, StringBuilder temporary, double accumulated) {
		pushToList(equationList, temporary, ' ');
        equationList.add(String.valueOf(accumulated));
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
	 * Gets the <code>char</code> at the next index
	 * 
     * @param equation in form of <code>String</code>
     * @param equationCharCount of the current character in form of <code>int</code>
	 * @return <code>char</code>
	 */
	private static char peekAhead(String equation, int equationCharCount) {
		try {
            return equation.charAt(equationCharCount + 1);
		}
		catch (StringIndexOutOfBoundsException e) {
			return '\0';
		}
	}

    /**
     * Gets the next non-whitespace <code>character</code> from the index
	 * 
     * @param equation in form of <code>String</code>
     * @param equationCharCount of the current character in form of <code>int</code>
	 * @return <code>char</code>
     */
    private static char peekAheadIgnoreWhitespace(String equation, int equationCharCount) {
        char nextCharacter = peekAhead(equation, equationCharCount);
        // get next character if whitespace
        if (Character.isWhitespace(nextCharacter)) {
            return peekAheadIgnoreWhitespace(equation, ++equationCharCount);
        }
        return nextCharacter;
    }
    
    /**
     * Gets the <code>String</code> at the next index
     * 
     * @param equation in form of <code>List</code>
     * @param listCount of the current <code>String</code> in form of <code>List</code>
	 * @return <code>String</code>
     */
    private static String peekAhead(List<String> equation, int listCount) {
		try {
			return equation.get(listCount + 1);
		}
		catch (StringIndexOutOfBoundsException e) {
			return null;
		}
	}

    /**
     * Tests the following:
     *  1. Operators == Operands - 1
     *  2. Root operators are followed by open parenthesis
     *  3. Open Parenthesis == Close Parenthesis
     *  4. Equal sign is at the very end of the equation
     */
    public static boolean isValidEquation(List<String> equation) {
        int equationLength = equation.size();
        int operands = 0;
        int operators = 0;
        int closeParenthesis = 0;
        int openParenthesis = 0;
        boolean validRoot = true;
        
        for (int i = 0; i < equationLength; i++) {
            // Check operators
            if (ValueChecker.isOperator(equation.get(i))) {
                operators++;
                
                // Check root
                if (ValueChecker.isRootOperator(equation.get(i))) {
                    validRoot = peekAhead(equation, i).charAt(0) 
                        == ValueChecker.OPEN_PARENTHESIS;
                }
            }
            // Check operands
            else if (ValueChecker.isNumber(equation.get(i))) {
                operands++;
            }
            // Check open parenthesis
            else if (equation.get(i).charAt(0) == ValueChecker.OPEN_PARENTHESIS) {
                openParenthesis++;
            }
            // Check close parenthesis
            else if (equation.get(i).charAt(0) == ValueChecker.CLOSE_PARENTHESIS) {
                closeParenthesis++;
            }
        }

        return  operands - 1 == operators && closeParenthesis == openParenthesis
            && isEqualSignAtEquationEnd(equation) && validRoot;
    }

    /**
     * Checks if the equal sign is at the end of the equation.
     * 
     * @param equation to check in form of <code>List<String></code>
     * @return <code>boolean</code>
     */
    public static boolean isEqualSignAtEquationEnd(List<String> equation) {
        int equalSignPosition = getEqualSignPosition(equation);
        return equalSignPosition == -1 || equalSignPosition == equation.size() - 1;
    }

    /**
     * Checks the position of the equal sign in the equation.
     * 
     * @param equation to check in form of <code>List<String></code>
     * @return <code>int</code>
     */
    public static int getEqualSignPosition(List<String> equation) {
        int equationLength = equation.size();
        String equalSign = String.valueOf(ValueChecker.EQUALS);

        for (int i = 0; i < equationLength; i++) {
            // Compare values to ValueChecker's equal sign
            if (equation.get(i).compareTo(equalSign) == 0) {
                return i;
            }
        }

        return -1;
    }
}
