/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel
 * 		December 8, 2023 - S. Cortel - Changed return datatype of convertToProperEquation
 * 									   to List<String>;
 * 									   Converted access to static;
 * 									   Converted acces to ValueChecker to static;
 * 									   Refactored code;
 * 									   Removed global variables;
 * Purpose
 * 		
 * 		Processes a string into substrings to be
 * 		used for computing.
 * 
 */
package com.sc.project.scientificcalculator;
import java.util.ArrayList;
import java.util.List;

public class EquationExtractor {
	
	/**
	 * Gets the last pushed String in the equation list
	 * 
	 * @return String
	 */
	private static String peekList(List<String> equationList) {
		try {
			return equationList.get(equationList.size() - 1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
	
	/**
	 * Adds the value to the equation list
	 * 
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
	 * This method converts a whole <code>String</code> 
	 * of an equation into a <code>String[]</code>
	 * for processing
	 * 
	 * @param equation in form of <code>String</code>
	 * @return <code>List<String></code>
	 */
	public static List<String> convertToProperEquation(String equation) {
		
		StringBuilder temporary = new StringBuilder();
		List<String> equationList = new ArrayList<String>();
		char[] equationCharArray = equation.toCharArray();
		
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

			// If special or is equal sign simply add to temporary
			else if (ValueChecker.isSpecial(equationChar)
				|| ValueChecker.isEquals(equationChar)) {
				pushToList(equationList, temporary, equationChar);
			}
			
			// Check if a value is a symbol
			else if (ValueChecker.isSymbol(equationChar)) {

				// Validate if minus is to be negative or just minus
				if (ValueChecker.isMinus(equationChar)) {
					// Minus is minus
					if (ValueChecker.isNumber(peekList(equationList))) {
						pushToList(equationList, temporary, equationChar);
					}
					// Minus is negative
					else {
						temporary.append(String.valueOf(equationChar));
					}
				}
				
				// Other symbols are automatically added to the list
				else {
					pushToList(equationList, temporary, equationChar);
				}
			}
		}
		
		pushToList(equationList, temporary, ' ');

		return equationList;	
	}
}
