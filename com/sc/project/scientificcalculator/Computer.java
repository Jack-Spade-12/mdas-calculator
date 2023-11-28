package adet.scientificcalculator;
import java.util.ArrayList;

public class Computer {
	static ProperEquationConversion equate = new ProperEquationConversion();
	static InfixToPostfixConversion fix =  new InfixToPostfixConversion();
	static Calculator calc = new Calculator();
	
	static double operate(double first, String operator, double second) {
		
		/*
		 * Operations that are available for this calculator.
		 * 
		 */
		
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
			case "r":
			case "R":
				return operate(first, "^", operate(1, "/", second));
			default:
				return 0;
		}
	}
	
	public double compute(String[] rawEquation) {
		
		/*
		 * Computes the equation after being converted
		 * to postfix notation. Here is where the real
		 * calculation occurs.
		 *
		 */
		
		ArrayList<String> equation = new ArrayList<String>();

		for (String i : rawEquation)
			equation.add(i);		

		for (int i=0; i<equation.size()-2; i++) {
			if (equate.isDigit(equation.get(i))) {
				if (equate.isSymbol(equation.get(i+2)) && equate.isDigit(equation.get(i+1))) {
				
					equation.set(i, String.valueOf(operate(Double.valueOf(equation.get(i)), equation.get(i+2), Double.valueOf(equation.get(i+1)))));
					equation.remove(i+1);
					equation.remove(i+1);
					i=-1;				
				}
			}
		}
		
		return Double.valueOf(equation.get(0));
	}
}
