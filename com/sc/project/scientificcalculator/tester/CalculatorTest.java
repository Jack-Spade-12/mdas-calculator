/**
 * History
 * 		
 * 		December 5, 2023 - S. Cortel - Created
 *      December 8, 2023 - S. Cortel - Converted AssertUnit to static access;
 *                                     Changed method access to static access;
 *                                     Removed addValueAtIndex() and isContinue() 
 *                                     methods;
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.Calculator
 * 
 */
package com.sc.project.scientificcalculator.tester;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sc.project.assertunit.AssertUnit;
import com.sc.project.scientificcalculator.Calculator;

public class CalculatorTest {
    
    /**
     * Tests the entire Calculator class at instantiation
     */
    public CalculatorTest() {
        System.out.println("\nCalculator Test Start");
        processInput();
        getEqualSignPosition();
        System.out.println("\nCalculator Test End");
    }

    /**
     * Tests processInput() method
     */
    private void processInput() {

    }

    /**
     * Tests getEqualSignPosition() method
     */
    private void getEqualSignPosition() {
        List<String> input = new ArrayList<String>();
        int expectedResult;
        int actualResult;

        input.addAll(Arrays.asList("1", "+", "1", "="));
        expectedResult = 3;
        actualResult = Calculator.getEqualSignPosition(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "getEqualsSignPosition() test 1");
        input.clear();

        input.addAll(Arrays.asList("=", "+", "1"));
        expectedResult = 0;
        actualResult = Calculator.getEqualSignPosition(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "getEqualsSignPosition() test 2");
        input.clear();

        input.addAll(Arrays.asList("1", "+", "1", "*", "32"));
        expectedResult = -1;
        actualResult = Calculator.getEqualSignPosition(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "getEqualsSignPosition() test 3");
        input.clear();
    }
}
