/**
 * History
 * 		
 * 		December 3, 2023 - S. Cortel - Modified
 * 
 * Purpose
 * 		
 * 		Base computer of the arithmetic values.
 * 
 */
package com.sc.project.scientificcalculator;

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
	public double compute(double first, String operator, double second) {
		
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
	public double compute(String first, String operator, String second) {
        return compute(Double.parseDouble(first.replaceAll(",", "")), 
            operator, Double.parseDouble(second.replaceAll(",", "")));
    }
}
