/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel
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

public class EquationExtractor {
	
	private ValueChecker value = new ValueChecker();
	private StringBuilder temporary;
	private List<String> equationList;
	
	/**
	 * This method converts a whole <code>String</code> 
	 * of an equation into a <code>String[]</code>
	 * for processing
	 * 
	 * @param equation in form of <code>String</code>
	 * @return <code>String[]</code>
	 */
	public String[] convertToProperEquation(String equation) {
		
		temporary = new StringBuilder();
		equationList = new ArrayList<String>();
		char[] equationCharArray = equation.toCharArray();
		
		// Determines which characters should be grouped together to create a number
		for (char equationChar : equationCharArray) {
			
			// Whitespaces treated as delimiter
			if (Character.isWhitespace(equationChar)) {
				pushToList(equationChar);
			}
			
			// If number simply add to temporary
			else if (value.isDigit(equationChar)) {
				temporary.append(String.valueOf(equationChar));
			}
			
			// Check if a value is a symbol
			else if (value.isSymbol(equationChar)) {

				// Validate if minus is to be negative or just minus
				if (value.isMinus(equationChar)) {
					// Minus is minus
					if (value.isNumber(peekList())) {
						pushToList(equationChar);
					}
					// Minus is negative
					else {
						temporary.append(String.valueOf(equationChar));
					}
				}
				
				// Other symbols are automatically added to the list
				else {
					pushToList(equationChar);
				}
			}

			// Special
			else if (value.isSpecial(equationChar)) {
				pushToList(equationChar);
			}
			
			// Equal sign
			else if (value.isEquals(equationChar)) {
				pushToList(equationChar);
			}
		}
		
		pushToList(' ');

		return equationList.toArray(new String[equationList.size()]);	
	}

	/**
	 * Adds the value to the equation list
	 * 
	 * @param value to add in form of <code>char</code>
	 */
	private void pushToList(char value) {
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
	 * Gets the last pushed String in the equation list
	 * 
	 * @return String
	 */
	private String peekList() {
		try {
			return equationList.get(equationList.size() - 1);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
}
