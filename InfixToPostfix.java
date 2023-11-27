package adet.mdascalculator;

public class InfixToPostfix {
	static InfixToPostfix fix = new InfixToPostfix();
	static String[] tempEquation;								
	static String[] signs;										
	static int signsCount;
	static int equationCount;
	
	/**
	 * Checks if a string is a valid number
	 */
	public boolean isNumber(String value) {
		try {
			@SuppressWarnings("unused")
			Double tempNumb = Double.parseDouble(value);
			return true;
		}
		
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	/**
	 * Checks if a string is a valid symbol
	 */
	public boolean isSymbol(String value) {
		if (value.equals("/") ||
			value.equals("*") ||
			value.equals("^") ||
			value.equals("+") ||
			value.equals("-") ||
			value.equals("%"))
			return true;
		return false;
	}
	
	/**
	 * Checks the precedence of signs in stacking
	 */
	static int checkPrecedence(String symbol) {
		switch (symbol) {
			case "-":
			case "+":
				return 1;
			case "*":
			case "/":
			case "%":
				return 2;
			case "^":
				return 3;
			default:
				return 0;
		}
	}
	
	/**
	 * Checks the last value int the stack
	 */
	static String seek() {
		return signs[signsCount-1];
	}
	
	/**
	 * Removes the last value in the stack
	 */
	static void pop() {
		tempEquation[equationCount++] = signs[signsCount-1];
		signsCount--;
	}
	
	/**
	 * Removes everything to the last parenthesis in the stack
	 */
	static void popIncludeParenthesis() {
		while (!seek().equals("("))
			pop();
	}	
	
	/**
	 * Pushes the sign in the stack
	 */
	static void push(String symbol) {
		if (symbol.equals(")"))
			popIncludeParenthesis();
		
		else if (checkPrecedence(symbol) > checkPrecedence(seek()))
			signs[signsCount++] = symbol;
		
		else if (checkPrecedence(symbol) <= checkPrecedence(seek())){
			pop();
			push(symbol);
		}
	}
		
	/**
	 * Converts the proper equation to postfix notation so that
	 * precedence of operations can easily be read by the computer.
	 * 
	 */
	public String[] convertToPostfix(String[] foreignEquation) {
		tempEquation = null;
		signs = null;
		signsCount = 0;
		equationCount = 0;
		
		tempEquation = new String[foreignEquation.length];
		signs = new String[foreignEquation.length-1];
		
		signs[signsCount++] = "(";
		for (String i : foreignEquation) {
						
			if (fix.isNumber(i))
				tempEquation[equationCount++] = i;
			
			else if (fix.isSymbol(i))
				push(i);
		
		}
		push(")");	
		return tempEquation;
	}

}
