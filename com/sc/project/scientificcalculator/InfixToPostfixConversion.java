/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Modified
 *      December 7, 2023 - S. Cortel - Made sure non-symbols and non-numbers were
 *                                     not allowed to be added to any stack
 *      December 7, 2023 - S. Cortel - Removed the dependence to String[] and
 *                                     converted to List<String>, also utilized
 *                                     Stack<String> for the signs     
 * 
 * Purpose
 * 		
 * 		This class converts an equation's notation
 *      to postfix notation for easier computation.
 *      
 */
package com.sc.project.scientificcalculator;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class InfixToPostfixConversion {

    private ValueChecker valueChecker = new ValueChecker();
	private List<String> tempEquation;
	private Stack<String> signs;
	
    /**
     * Constructor of the class
     */
    public InfixToPostfixConversion() {
        valueChecker = new ValueChecker();
        tempEquation = new ArrayList<String>();
        signs = new Stack<String>();
    }

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
                return -1;
		}
	}
	
    /**
     * Pop the sign stack and add to the equation <code>List</code>
     */
	private void pop() {
        // Do not include grouper characters in the equation
        if (valueChecker.isGrouper(signs.peek())) {
            signs.pop();
        }
        else {
            tempEquation.add(signs.pop());
        }
	}
	
    /**
     * Pushes the symbol into the sign stack. Ensure that 
     * precedence of symvbols is always incrementing.
     * 
     * @param symbol to push in the <code>Stack</code>
     */
	private void push(String symbol) {
        try {
            if (getPrecedence(signs.peek()) > getPrecedence(symbol)) {
                pop();
                push(symbol);
            }
            else {
                signs.push(symbol);
            }
        }
        // Stack is empty
        catch (EmptyStackException e) {
            signs.push(symbol);
        }
	}
	
    /**
     * Converts the proper equation to postfix notation so that
     * precedence of operations can easily be read by the computer
     * 
     * @param equation to convert to postfix notation in form
     * of <code>List</code>
     * @return <code>List</code> equation in postfix notation
     */
	public List<String> convertToPostfix(List<String> equation) {
		
        tempEquation.clear();
        signs.clear();

        push("(");
		for (String equationSegment : equation) {
            // Push symbols to sign stack
			if (valueChecker.isSymbol(equationSegment)) {
                push(equationSegment);
            }
            // Push numbers to equation stack
            else if (valueChecker.isNumber(equationSegment)) {
                tempEquation.add(equationSegment);
            }
		}
		push(")");

		return tempEquation;
	}
}
