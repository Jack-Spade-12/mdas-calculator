/**
 * History
 * 		
 * 		December 3, 2023 - S. Cortel - Modified
 * 		December 8, 2023 - S. Cortel - Moved InfixToPostfixConversion here;
 * 									   Converted to static access;
 * 									   Moved here computation of full postfix equation;
 *                                     Changed ValueChecker access to static;
 *                                     Changed InfixToPostfixConversion access to static;
 * 
 * Purpose
 * 		
 * 		Base computer of the arithmetic values.
 * 
 */
package com.sc.project.scientificcalculator;
import java.util.List;

public class Computer {

	/**
     * The main computer of values; executes operations based on the
     * given operator
     * 
     * @param first operand in form of <code>double</code>
     * @param operator in form of <code>String</code>
     * @param second operand in form of <code>double</code>
     * @return <code>double</code>
     */
	public static double compute(double first, String operator, double second) {
		
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
				return compute(first, "^", compute(1, "/", second));
			default:
				return 0;
		}
	}

    /**
     * The main computer of values; executes operations based on the
     * given operator
     * 
     * @param first operand in form of <code>String</code>
     * @param operator in form of <code>String</code>
     * @param second operand in form of <code>double</code>
     * @return <code>double</code>
     */
	public static double compute(String first, String operator, String second) {
        return compute(Double.parseDouble(first.replaceAll(",", "")), 
            operator, Double.parseDouble(second.replaceAll(",", "")));
    }

	/**
	 * Fully computes an entire equation
	 */
	public static double computeEquation(List<String> equation) {
		List<String> postfixEquation = InfixToPostfixConversion.convertToPostfix(equation);
		
		int index;
        double computedValue;
        String firstOperand;
        String secondOperand;
        String operator;
        
		while (postfixEquation.size() > 1) {
            index = 0;
            while (ValueChecker.isNumber(postfixEquation.get(index))) {
                index++;
            }
            
            // Assign to rightful variables
            firstOperand = postfixEquation.get(index - 2);
            secondOperand = postfixEquation.get(index -1);
            operator = postfixEquation.get(index);
            
            // Compute the first operand, operator, second operand
            computedValue = compute(firstOperand, operator, secondOperand);

            // Remove the computed numbers and operators (first three strings in the list)
            for (int i = 0; i < 3; i++) {
                postfixEquation.remove(index - 2);
            }

            // Place the computed value at the first index of the 3 removed values
            postfixEquation.add(index - 2, String.valueOf(computedValue));
        }

        return Double.parseDouble(postfixEquation.get(0));
	}
}
