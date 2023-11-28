package com.sc.project.scientificcalculator;
import java.util.ArrayList;

public class InfixToPostfixConversion {
	static ProperEquationConversion equate = new ProperEquationConversion();
	static ArrayList<String> signs = new ArrayList<String>();
	static ArrayList<String> tempEquation = new ArrayList<String>();
	static int size = 0;
	
	public boolean isDigit(char value) {					// Checks if a digit is a number or a '.'
		if (Character.isDigit(value) || value == '.')		// The program considers the '.' as part of
			return true;									// number, especially when calculating
		return false;										// floating values.
	}
	
	public boolean isDigit(String value) {					// Checks if the string is a number
		try {
			@SuppressWarnings("unused")
			Double temp1 = Double.parseDouble(value);
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
	
	public boolean isSymbol(char value) {
		if (value == '/' ||
			value == '*' ||
			value == '^' ||
			value == '+' ||
			value == '-' ||
			value == '%')
			return true;
		return false;
	}

	
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
			case "r":
			case "R":
				return 4;
			default:
				return 0;
		}
	}
	
	static String seek() {									// Checks the last sign in the sign stack
		return signs.get(signs.size()-1);
	}
	
	static void popIncludeParenthesis() {					// Pop including the first parenthesis
		while (!seek().equals("("))
			pop();
		signs.remove(signs.size()-1);
	}
	
	static void popTilParenthesis() {						// Pop until it reaches a parenthesis
		while (!seek().equals("("))
			pop();
	}
	
	static void pop() {										// Removes the last sign in the stack
		if (!(seek().equals("(")) && !(seek().equals(")")))
			tempEquation.add(signs.get(signs.size()-1));
		signs.remove(signs.size()-1);
	}
				
	static void push(String symbol) {						// Pushes the sign into the stack
		if (symbol.equals(")"))
			popIncludeParenthesis();
		else if (symbol.equals(","))
			popTilParenthesis();
		else if (symbol.equals("("))
			signs.add(symbol);
		else if (checkPrecedence(symbol) > checkPrecedence(seek()))
			signs.add(symbol);
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
		
		tempEquation.clear();
		signs.clear();
		
		signs.add("(");
		for (String i : foreignEquation) {
			
			if (equate.isDigit(i))
				tempEquation.add(i);
			
			else if (equate.isSymbol(i))
				push(i);
			
			else if (equate.isSpecial(i.charAt(0)))
				tempEquation.add(String.valueOf(Calculator.accumulated));
		}
		push(")");

		return tempEquation.toArray(new String[tempEquation.size()]);
	}
}
