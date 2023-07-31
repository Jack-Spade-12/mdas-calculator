package adet.mdascalculator;

public class Computer {
	static InfixToPostfix fix = new InfixToPostfix();						 
	
	static double operate(double first, String operator, double second) {	// Operate: 
		switch(operator) {
			case "+":
				return first + second;
			case "-":
				return first - second;
			case "*":
				return first * second;
			case "/":
				return first / second;
			case "%":
				return first % second;
			case "^":
				return Math.pow(first, second);
			default:
				return 0;
		}
	}
	
	static String[] removeNull(String[] equation) {								// Remove null values from equation
		String[] tempEquation = new String[equation.length-2];
		int tempEquationCount = 0;
		
		for (String i : equation) 
			if (i != null)
				tempEquation[tempEquationCount++] = i; 
		
		return tempEquation;
	}
	
	public double compute(String[] rawEquation) {
		
		/*
		 * Computes the equation after being converted
		 * to postfix notation. Here is where the real
		 * calculation occurs.
		 * 
		 * The execution is as follows:
		 * 	
		 * 	1.) Check if equation[i] is number
		 * 		
		 * 	2.) Check if equation[i+1] is also a number
		 * 		
		 * 	3.) Check if equation[i+2] is a symbol
		 * 
		 * 	4.) If all conditions are met, process:
		 * 		equation[i]   <-- First operand
		 * 		equation[i+1] <-- Second operand
		 * 		equation[i+2] <-- Operator
		 * 
		 * 	5.) Place returned value to equation[i]
		 * 
		 *  6.) Set to null:
		 *  	equation[i+1]
		 *  	equation[i+2]
		 *  
		 * 	7.) Remove null values
		 * 
		 * 	8.) Repeat until equation length == 1
		 * 
		 */
		
		String[] equation = rawEquation;

		for (int i=0; equation.length>1; i++) {			
			if (fix.isNumber(equation[i])) {
				if (fix.isNumber(equation[i+1]) && fix.isSymbol(equation[i+2])) {
					equation[i] = String.valueOf(operate(Double.valueOf(equation[i]), equation[i+2], Double.valueOf(equation[i+1])));
					equation[i+1] = null;
					equation[i+2] = null;
					i = -1;	
					equation = removeNull(equation);
				}
			}
		}

		return Double.valueOf(equation[0]);
	}
}
