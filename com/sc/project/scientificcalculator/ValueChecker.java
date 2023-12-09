/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 *      December 7, 2023 - S. Cortel - Added isOperator() method
 *      December 8, 2023 - S. Cortel - Changed access to ValueChecker methods to static;
 *                                     Added isOpenGrouper();
 *                                     Added isCloseGrouper();
 *      December 9, 2023 - S. Cortel - Changed format for constants and enabled
 *                                      static access;
 *      December 10, 2023 - S. Cortel - Fixed isOpertor() name to isOperator()
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
    private static List<Character> specialCharacters = Arrays.asList('A', 'a');
    private static List<Character> numberSeparators = Arrays.asList(',', '.');
    private static List<Character> symbols = Arrays.asList('(', ')', '/', '*', '^', '+', '-', '%', 'r', 'R');
    private static List<Character> groupers = Arrays.asList('(', ')');
    private static List<Character> rootOperators = Arrays.asList( 'R', 'r');
    private static List<Character> operators = Arrays.asList('/', '*', '^', '+', '-', '%', 'r', 'R');
    public static final char MINUS = '-';
    public static final char EQUALS = '=';
    public static final char OPEN_PARENTHESIS = '(';
    public static final char CLOSE_PARENTHESIS = ')';
    public static final char MULTIPLY = '*';
    
    /**
	 * Checks if a character is a special value 'A' or 'a'
     * 
	 * @param value to check in form of <code>char</code>
	 * @return <code>boolean</code>
	 */
	public static boolean isSpecial(char value) {
		return specialCharacters.contains(value);
	}

    /**
	 * Checks if a string is a special value 'A' or 'a'
     * 
	 * @param value to check in form of <code>String</code>
	 * @return <code>boolean</code>
	 */
    public static boolean isSpecial(String value) {
        return value.length() == 1 && isSpecial(value.charAt(0));
    }
	
	/**
	 * Checks if a character is a digit or a number separator:
     * period (<code>.</code>) or comma (<code>,</code>)
     * 
	 * @param value to check in form of <code>char</code>
	 * @return <code>boolean</code>
	 */
	public static boolean isDigit(char value) {
		return Character.isDigit(value) || numberSeparators.contains(value);
	}

    /**
	 * Checks if a string is a digit or a number separator:
     * period (<code>.</code>) or comma (<code>,</code>)
     * 
	 * @param value to check in form of <code>char</code>
	 * @return <code>boolean</code>
	 */
	public static boolean isDigit(String value) {
		return value.length() == 1 && isDigit(value.charAt(0));
	}
	
	/**
     * Checks if a character is a symbol
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public static boolean isSymbol(char value) {
        return symbols.contains(value);
    }

    /**
     * Checks if a string is a symbol
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public static boolean isSymbol(String value) {
        return value.length() == 1 && isSymbol(value.charAt(0));
    }

    /**
     * Checks if a character is an operator
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public static boolean isOperator(char value) {
        return operators.contains(value);
    }

    /**
     * Checks if a string is a symbol
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public static boolean isOperator(String value) {
        return value.length() == 1 && isOperator(value.charAt(0));
    }

    /**
     * Checks if <code>char</code> is a root operator (<code>r</code>) or 
     * (<code>R</code>)
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public static boolean isRootOperator(char value) {
        return rootOperators.contains(value);
    }

    /**
     * Checks if <code>String</code> is a root operator (<code>r</code>) or 
     * (<code>R</code>)
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public static boolean isRootOperator(String value) {
        return isRootOperator(value.charAt(0));
    }

    /**
     * Checks if a character is a grouper: open parenthesis
     * (<code>(</code>) or closed parenthesis (<code>)</code>)
     * 
     * @param value to check in form of <code>char</code>
     * @return boolean
     */
    public static boolean isGrouper(char value) {
        return groupers.contains(value);
    }

    /**
     * Checks if a string is a grouper: open parenthesis
     * (<code>(</code>) or closed parenthesis (<code>)</code>)
     * 
     * @param value to check in form of <code>String</code>
     * @return boolean
     */
    public static boolean isGrouper(String value) {
        return value.length() == 1 && isGrouper(value.charAt(0));
    }

    /**
	 * Checks if a string is a valid number
     * 
	 * @param value to check in form of <code>String</code>
	 * @return <code>boolean</code>
	 */
	public static boolean isNumber(String value) {
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