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

package adet.mdascalculator;
import java.io.IOException;
import java.util.Scanner;

public class Calculator {
	static InfixToPostfix fix = new InfixToPostfix();
	static Computer compute = new Computer();
	static Scanner sc = new Scanner(System.in);
	static double total = 0D;
	
	/**
	 * This method terminates the calculator program
	 */
	static void terminateProgram() {
		sc.close();
		System.out.println("\nProgram Terminated.");
		System.exit(1);
	}
	
	/**
	 * The following method removes the '.0' when the accumulated
	 * value has no floating value. Method also returns "undefined"
	 * when the result is NaN (Not a Number) and Infinite
	 */
	static void outputTotal() {
		if (Double.isNaN(total) || Double.isInfinite(total))
			System.out.println("\n= undefined");
		else if (total % 1 == 0.0)
			System.out.printf("\n= %.0f", total);
		else if (total % 1 != 0.0)
			System.out.print("\n= " + total);
	}
	
	/**
	 * This method validates if the equation is correct
	 */
	static boolean validateEquation(String[] equation) {
		int countVariables = 0;
		int countSigns = 0;
		
		try {
			for (String i : equation) {
				if (fix.isSymbol(i))
					countSigns++;
				else if (fix.isNumber(i))
					countVariables++;
			}
		}
		catch (Exception e) {}
		
		if (countSigns == countVariables - 1)
			return true;
		return false;
	}
	
	/**
	 * This method gets the equation that the user wants
	 * to perform
	 */
	static String[] getEquation(int inputs) {
		int operand = 1;
		int operator = 1;
		String[] equation = new String[inputs * 2 - 1];
		
		for (int i=0; i<inputs*2-1; i++) {
			if (i%2 == 0) {
				System.out.print("\nEnter Number   (" + operand + ")  : ");
				equation[i] = sc.nextLine();
				operand++;
			}
			else if (i%2 == 1) {
				System.out.print("\nEnter Operator (" + operator + ")  : ");
				equation[i] = sc.nextLine();
				operator++;
			}
			
			try {
				if (!fix.isNumber(equation[i]) && !fix.isSymbol(equation[i])) {
					System.out.println("\nInvalid Input. -> " + equation[i]);
					terminateProgram();
				}
			}
			catch (Exception e) {}
		}
		
		return equation;
	}
	
	/**
	 * This is the main method of the program
	 */
	public static void main(String[] args) {
		ProcessBuilder cls = new ProcessBuilder("cmd", "/c", "cls");
		String[] equation;
		boolean execute = true;
		int numberOfInputs = 0;
				
		do {
			try {
				cls.inheritIO().start().waitFor();
			}
			catch (Exception e) {}

			System.out.println("\n|====================================|");
			System.out.println("|                                    |");
			System.out.println("| >>  CONTINUOUS MDAS CALCULATOR  << |");
			System.out.println("|             Seth Cortel            |");
			System.out.println("|                                    |");
			System.out.println("|====================================|");			
			
			do {
				try {
					System.out.print("\n\nEnter number of inputs : ");
					numberOfInputs = sc.nextInt();
					sc.nextLine();
					System.out.println();
					break;
				}
				catch (Exception e) {
					System.out.println("\nInvalid input. Please input integer.");
					sc.nextLine();
				}
			}
			while (execute);
			 
			if (numberOfInputs < 2 || numberOfInputs > 10)
				System.out.println("\nInvalid number of inputs, please try again."
								 + "\nThe minimum number of inputs is 2,"
								 + "\nand the maximum number of inputs is 10.");
			
			else {
				equation = getEquation(numberOfInputs);
				
				System.out.print("\nEquation : ");
				for (String i : equation)
					System.out.print(i + " ");
				
				if (validateEquation(equation)) {
					equation = fix.convertToPostfix(equation);
					total = compute.compute(equation);
					outputTotal();
				}
				
				else
					System.out.println("\nEquation is not valid.\n");
			}
			
			do {
				try {
					System.out.print("\n\n\nCalculate again? (Y\\N) : ");
					char cont = sc.nextLine().toUpperCase().charAt(0); 
					
					if (cont == 'Y')
						break;
					
					else if (cont == 'N') {
						execute = false;
						break;
					}
				}
				catch (Exception e) {}
			}
			while (execute);

			System.out.println("\n");
			try {
				cls.inheritIO().start().waitFor();
			} 
			catch (InterruptedException | IOException e) {}
		}
		while (execute);
		terminateProgram();
	}
} 