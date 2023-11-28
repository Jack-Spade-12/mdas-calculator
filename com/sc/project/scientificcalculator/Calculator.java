/**
 * Submitted By:
 * 		Seth Plasabas Cortel
 * 
 * Submitted To:
 *		Prof. Leo Paulo Villarete
 *		
 * Purpose of Submission:
 * 		Requirement for the subject:
 * 		Applications Development and Emerging Technologies
 */

package com.sc.project.scientificcalculator;
import java.io.IOException;
import java.util.Scanner;

public class Calculator {
	static Scanner sc = new Scanner(System.in);
	static Computer comp = new Computer();
	static Calculator calculate = new Calculator();
	static ProperEquationConversion equate = new ProperEquationConversion();
	static InfixToPostfixConversion fix = new InfixToPostfixConversion();

	static String inputAccum = "";											// Accumulates user input, useful for multiple continuous inputs
	static boolean cont = true;												// Continue calculator or terminate
	static int state = 0;													// Used when using the root operation during multiple continuous inputs
	static double accumulated = 0D;											// Stores result of calculations
	
	public void outputAccumulated() {
		
		/*
		 * The accumulated is a double and this method
		 * removes the '.0' when the accumulated has no
		 * floating value. Returns undefined when the
		 * result is NaN (Not a Number) and Infinite.
		 * 
		 */
		
		if (Double.isNaN(accumulated) || Double.isInfinite(accumulated))
			System.out.print("= undefined");
		else if (accumulated % 1 == 0.0)
			System.out.printf("= %.0f", accumulated);
		else if (accumulated % 1 != 0.0)
			System.out.print("= " + accumulated);
	}
		
	public boolean validateEquation(String[] equation) {
		
		/*
		 * This validates if the equation is valid such that the variables
		 * are 1 more than the signs [excluding: parenthesis, commas, and equals].
		 *  
		 */
		
		int countVariables = 0;
		int countSigns = 0;
		
		try {
			for (String i : equation) {
				if (equate.isSymbol(i) && !equate.isDiscarded(i)) {
					countSigns++;
				}
				else if (equate.isDigit(i) || (equate.isSpecial(i.charAt(0)))) {
					countVariables++;
				}
			}
		}
		catch (Exception e) {}
		
		if (countSigns == countVariables - 1)
			return true;
		return false;
	}
		
	public void executeCalculator(String[] strEquation) {
		String[] equation = fix.convertToPostfix(strEquation);				// Converts the proper equation (infix notation) into postfix notation			
		accumulated = comp.compute(equation);								// Calculates the final equation and stores it to `Accumulated`
		outputAccumulated();												// Output the result for the computation
	}

	public void inputAccumulated(String input) {							// Verifies if inputAccum is to be accumulated or to be thrown
		char inputFirstChar = 0;
		char accumLastChar = 0;

		try {
			try {
				accumLastChar = inputAccum.charAt(inputAccum.length() - 1);
			}
			catch (Exception e) {
				accumLastChar = 0;
			}
			
			for (int i=0; i<input.length(); i++) {
				if (!Character.isWhitespace(input.charAt(i))) {
					inputFirstChar = input.charAt(i);
					break;
				}
			}
		}
		catch (Exception e) {
			inputFirstChar = 0;
		}
		
		if (accumLastChar == '=')											// Determines if user input should be concatenated to the input accum or not
			inputAccum = "";
		else if ((equate.isDigit(accumLastChar) || equate.isSpecial(accumLastChar) || equate.isDiscarded(accumLastChar)) && (accumLastChar != '(' && accumLastChar != ','))
			if ((equate.isDigit(inputFirstChar) || equate.isSpecial(inputFirstChar) || equate.isDiscarded(inputFirstChar) || inputFirstChar == 'r' || inputFirstChar == 'R') && inputFirstChar != ')')
				inputAccum = "";
		
		if (equate.isDigit(input) || equate.isSpecial(inputFirstChar)) {
			if (state == 1) {
				inputAccum = inputAccum.concat(input + ",");
				state = 2;
			}
			
			else if (state == 2) {
				inputAccum = inputAccum.concat(input + ") ");
				state = 0;
			}
			
			else if (state == 0){
				inputAccum = inputAccum.concat(input);
			}		
		}
		
		else {
			if (input.toUpperCase().equals("R")) {
				state = 1;
				inputAccum = inputAccum.concat(" r(");
			}
			
			else {
				inputAccum = inputAccum.concat(input);
			}
		}

		
		String[] equation = equate.convertToProperEquation(inputAccum);		// Converts inputAccum into an array of operators and operands
		
		System.out.print("= ");												// Shows the equation
		for (String i : equation)											//
			System.out.print(i + " ");										//
		System.out.println();												//
		
		if (calculate.validateEquation(equation))
			calculate.executeCalculator(equation);
	}

	public void checkIfContinues(String equation) {
		
		/*
		 * This checks the input if it does not contain the string 'XX' or 'xx'.
		 * If it does, it terminates the calculator.
		 * 
		 */
		
		if (equation.contains("XX") || equation.contains("xx")) {
			System.out.println("\n.\n.\n.\n.\n.\n.\n.\n.\nCalculator has been terminated.");
			sc.close();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) throws InterruptedException, IOException {
		ProcessBuilder cls = new ProcessBuilder("cmd", "/c", "cls");
		String equation;		
		
		try {
			cls.inheritIO().start().waitFor();
		}
		catch (Exception e) {}

		System.out.println("\n|===============================|");
		System.out.println("|                               |");
		System.out.println("| >>  SCIENTIFIC CALCULATOR  << |");
		System.out.println("|         Seth Cortel           |");
		System.out.println("|                               |");
		System.out.println("|===============================|");
		
		System.out.print("\n\nCalculator has started.\n\n"
						+ "To refer to your previous\n"
						+ "output, input: 'A' / 'a'\n\n"
						+ "To perform root operations,\n"
						+ "input: r(BASE, EXPONENT)\n\n"
						+ "To perform nested root operations,\n"
						+ "use a singline-line input calculation.\n\n"
						+ "This calculator accepts single\n"
						+ "line input and multiple\n"
						+ "continuous inputs.\n\n"
						+ "To terminate an input\n"
						+ "place an equal (=) sign.\n\n"
						+ "To terminate the program,\n"
						+ "input 'XX // xx'.");
		
		while (true) {
			System.out.print("\n\n: ");
			equation = sc.nextLine();			
			calculate.checkIfContinues(equation);
			calculate.inputAccumulated(equation);			
		}
	}
} 