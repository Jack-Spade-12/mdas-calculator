package adet.mdascalculator;

public class InfixToPostfix {
	static InfixToPostfix fix = new InfixToPostfix();
	static String[] tempEquation;								// Final equation
	static String[] signs;										// Temporary stack for signs
	static int signsCount;
	static int equationCount;
	
	public boolean isNumber(String value) {						// Checks if a string is valid number
		try {
			@SuppressWarnings("unused")
			Double tempNumb = Double.parseDouble(value);
			return true;
		}
		
		catch (NumberFormatException e) {
			return false;
		}
	}
	
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
		
	static int checkPrecedence(String symbol) {					// Checks precedence for signs in stacking
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
	
	static String seek() {										// Checks the last value in the stack
		return signs[signsCount-1];
	}
	
	static void pop() {											// Removes the last value in the stack
		tempEquation[equationCount++] = signs[signsCount-1];
		signsCount--;
	}
	
	static void popIncludeParenthesis() {						// Removes everything to the last parenthesis in the stack
		while (!seek().equals("("))
			pop();
	}	
	
	static void push(String symbol) {							// Pushes the sign in the stack
		if (symbol.equals(")"))
			popIncludeParenthesis();
		
		else if (checkPrecedence(symbol) > checkPrecedence(seek()))
			signs[signsCount++] = symbol;
		
		else if (checkPrecedence(symbol) <= checkPrecedence(seek())){
			pop();
			push(symbol);
		}
	}
		
	public String[] convertToPostfix(String[] foreignEquation) {
		
		/*
		 * Converts the proper equation to postfix notation so that
		 * precedence of operations can easily be read by the computer.
		 * 
		 */
		
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
