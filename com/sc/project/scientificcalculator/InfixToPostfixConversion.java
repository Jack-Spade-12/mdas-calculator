/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Modified
 * 
 * Purpose
 * 		
 * 		This class converts an equation's notation
 *      to postfix notation for easier computation.
 *      
 */
package com.sc.project.scientificcalculator;
import java.util.ArrayList;
import java.util.List;

public class InfixToPostfixConversion {

    private ValueChecker valueChecker = new ValueChecker();
	private List<String> tempEquation;
	private List<String> signs;
	
    /**
     * Gets the precedence of symbols
     * 
     * @param symbol to check in form of <code>char</code>
     * @return <code>int</code> precedence
     */
	static int getPrecedence(String symbol) {
		switch (symbol) {
            case ")":
                return 0;
            case "-":
			case "+":
				return 1;
			case "*":
			case "/":
			case "%":
				return 2;
			case "^":
				return 3;
			case "r":
			case "R":
				return 4;
            case "(":
                return 5;
			default:
                return -2;
		}
	}
	
    /**
     * Checks the last sign in the sign stack
     */
	private String peek() {
        if (signs.size() == 0) {
            return "\0";
        }
		return signs.get(signs.size() - 1);
	}
	
    /**
     * Pop the sign stack
     */
	private void pop() {
        // Do not include grouper characters in the equation
        if (!valueChecker.isGrouper(peek())) {
            tempEquation.add(signs.get(signs.size() - 1));
        }
        signs.remove(signs.size() - 1);
	}
	
    /**
     * Push the sign into the sign stack
     */
	private void push(String symbol) {
		if (getPrecedence(peek()) > getPrecedence(symbol)) {
            pop();
            push(symbol);
        }
        else {
            signs.add(symbol);
        }
	}
	
    /**
     * Converts the proper equation to postfix notation so that
     * precedence of operations can easily be read by the computer
     * 
     * @param equation to convert to postfix notation in form
     * of <code>String[]</code>
     * @return <code>String[]</code> equation in postfix notation
     */
	public String[] convertToPostfix(String[] equation) {
		
		tempEquation = new ArrayList<String>();
		signs = new ArrayList<String>();
		
		push("(");
		for (String equationSegment : equation) {
            // Push symbols to sign stack
			if (valueChecker.isSymbol(equationSegment)) {
                push(equationSegment);
            }
            // Push non-symbols to equation stack
            else {
                tempEquation.add(equationSegment);
            }
		}
		push(")");

		return tempEquation.toArray(new String[tempEquation.size()]);
	}
}
