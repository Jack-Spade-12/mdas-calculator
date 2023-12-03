/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 * 
 * Purpose
 * 		
 * 		The purpose of this class is to check if a 
 *      specific character or string is either a
 *      number, special character, digit, or symbol.
 *      
 */
package com.sc.project.scientificcalculator;
import java.util.Arrays;
import java.util.List;

public class ValueChecker {
    private List<Character> specialCharacters = Arrays.asList('A', 'a');
    private List<Character> numberSeparators = Arrays.asList(',', '.');
    private List<Character> symbols = Arrays.asList('(', ')', '/', '*', '^', '+', '-', '%', 'r', 'R');
    private List<Character> groupers = Arrays.asList('(', ')');
    private char minus = '-';
    private char equals = '=';

    /**
	 * Checks if a character is a special value 'A' or 'a'
     * 
	 * @param value to check in form of <code>char</code>
	 * @return <code>boolean</code>
	 */
	public boolean isSpecial(char value) {
		return specialCharacters.contains(value);
	}

    /**
	 * Checks if a string is a special value 'A' or 'a'
     * 
	 * @param value to check in form of <code>String</code>
	 * @return <code>boolean</code>
	 */
    public boolean isSpecial(String value) {
        return value.length() == 1 && isSpecial(value.charAt(0));
    }
	
	/**
	 * Checks if a character is a digit or a number separator:
     * period (<code>.</code>) or comma (<code>,</code>)
     * 
	 * @param value to check in form of <code>char</code>
	 * @return <code>boolean</code>
	 */
	public boolean isDigit(char value) {
		return Character.isDigit(value) || numberSeparators.contains(value);
	}

    /**
	 * Checks if a string is a digit or a number separator:
     * period (<code>.</code>) or comma (<code>,</code>)
     * 
	 * @param value to check in form of <code>char</code>
	 * @return <code>boolean</code>
	 */
	public boolean isDigit(String value) {
		return value.length() == 1 && isDigit(value.charAt(0));
	}
	
	/**
     * Checks if a character is a symbol
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public boolean isSymbol(char value) {
        return symbols.contains(value);
    }

    /**
     * Checks if a string is a symbol
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public boolean isSymbol(String value) {
        return value.length() == 1 && isSymbol(value.charAt(0));
    }

    /**
     * Checks is character is a minus (<code>-</code>)
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public boolean isMinus(char value) {
        return value == minus;
    }

    /**
     * Checks is string is a minus (<code>-</code>)
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public boolean isMinus(String value) {
        return value.length() == 1 && isMinus(value.charAt(0));
    }

    /**
     * Checks if a character is a grouper: open parenthesis
     * (<code>(</code>) or closed parenthesis (<code>)</code>)
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public boolean isGrouper(char value) {
        return groupers.contains(value);
    }

    /**
     * Checks if a string is a grouper: open parenthesis
     * (<code>(</code>) or closed parenthesis (<code>)</code>)
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public boolean isGrouper(String value) {
        return value.length() == 1 && isGrouper(value.charAt(0));
    }

    /**
     * Checks is character is an equal sign (<code>=</code>)
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public boolean isEquals(char value) {
        return value == '=';
    }

    /**
     * Checks is character is an equal sign (<code>=</code>)
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public boolean isEquals(String value) {
        return value.length() == 1 && isEquals(value.charAt(0));
    }

    /**
	 * Checks if a string is a valid number
     * 
	 * @param value to check in form of <code>String</code>
	 * @return <code>boolean</code>
	 */
	public boolean isNumber(String value) {
        try {
            // Remove commas ( , )
            Double.parseDouble(value.replace(",", ""));
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
        catch (NullPointerException e) {
            return false;
        }
	}
}