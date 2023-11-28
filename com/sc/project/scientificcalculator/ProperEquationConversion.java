package com.sc.project.scientificcalulator;
import java.util.ArrayList;

public class ProperEquationConversion {	
	public boolean isSpecial(char value) {
		if (String.valueOf(value).equals("A") ||						// Checks that an input is a special value, "a"
			String.valueOf(value).equals("a"))
			return true;
		return false;
	}
	
	public boolean isDigit(char value) {
		if (Character.isDigit(value) || value == '.')					// Here a dot '.' is considered as a digit and not a symbol because the dot is always part of the number
			return true;
		return false;
	}
	
	public boolean isDigit(String value) {								// Checks if a String is a number
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
		if (value.equals("(") || 
			value.equals(")") ||
			value.equals("/") ||
			value.equals("*") ||
			value.equals("^") ||
			value.equals("+") ||
			value.equals("-") ||
			value.equals("%") ||
			value.equals(",") ||
			value.equals("r") ||
			value.equals("R"))
			return true;
		return false;
	}
	
	public boolean isSymbol(char value) {
		if (value == '(' || 
			value == ')' ||
			value == '/' ||
			value == '*' ||
			value == '^' ||
			value == '+' ||
			value == '-' ||
			value == '%' ||
			value == ',' ||
			value == 'r' ||
			value == 'R')
			return true;
		return false;
	}
	
	public boolean isDiscarded(String value) {							
		if (value.equals("(") ||
			value.equals(")") ||
			value.equals(","))
			return true;
		return false;
	}
	
	public boolean isDiscarded(char value) {
		if (value == '(' ||
			value == ')' ||
			value == ',')
			return true;
		return false;
	}
	
	public char getPreviousChar(String equation, int index) {			// Used to check if a '-' symbol is used to imply subtraction or a negative number
		char prev = 0;
		
		for (int i=index-1; i>-1; i--) {
			if (!Character.isWhitespace(equation.charAt(i))) {
				prev = equation.charAt(i);
				break;
			}
		}	
		return prev;
	}
	
	public String[] convertToProperEquation(String strEquation) {
		
		/*
		 * This successfully converts a whole string of an equation
		 * into an array of strings so that it can be processed and/or
		 * calculated. 
		 *  
		 */

		StringBuilder temporary = new StringBuilder();
		ArrayList<String> equation = new ArrayList<String>();
		boolean digitCount = false;										// Determines which characters are grouped together to create a number
		
		try {
			for (int i=0; i<strEquation.length(); i++){					// Determines which characters should be grouped together to create a number
				if (!Character.isWhitespace(strEquation.charAt(i))) {	// Alternatively, to determine which characters are signs and which characters are acceptable
					if (isDigit(strEquation.charAt(i))) {
						temporary.append(strEquation.charAt(i));
						digitCount = true;
					}
									
					else if (!isDigit(strEquation.charAt(i)) && digitCount == true) {
						equation.add(temporary.toString());
						digitCount = false;
						temporary.setLength(0);
					}
					
					if (isSpecial(strEquation.charAt(i)))
						equation.add("A");
					
					else if (strEquation.charAt(i) == '-' && i == 0) {
						temporary.append(strEquation.charAt(i));
						digitCount = true;
					}
					
					else if (strEquation.charAt(i) == '-' && isSymbol(getPreviousChar(strEquation, i)) && getPreviousChar(strEquation, i) != ')'){
						temporary.append(strEquation.charAt(i));
						digitCount = true;
					}
					
					else if (isSymbol(strEquation.charAt(i)) || isDiscarded(strEquation.charAt(i)))
						equation.add(String.valueOf(strEquation.charAt(i)));
					
				}
				else if (Character.isWhitespace(strEquation.charAt(i)) && digitCount == true) {
					equation.add(temporary.toString());
					digitCount = false;
					temporary.setLength(0);;
				}
			}
			
			if (digitCount == true) {
				equation.add(temporary.toString());
				digitCount = false;
				temporary.setLength(0);
			}
			
			return equation.toArray(new String[equation.size()]);			
		}
		
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("The equation is not valid.");
			return null;
		}
	}
}
