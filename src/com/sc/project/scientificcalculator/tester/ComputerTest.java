/**
 * History
 * 		
 * 		December 3, 2023 - S. Cortel - Created
 *      December 8, 2023 - S. Cortel - Converted AssertUnit to static access;
 *                                     Added computeEquation() test;
 *                                     Converted Computer access to static
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.Computer
 * 
 */
package com.sc.project.scientificcalculator.tester;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sc.project.assertunit.AssertUnit;
import com.sc.project.scientificcalculator.Computer;

public class ComputerTest {
    
    /**
     * Tests the entire Computer class at instantiation
     */
    public ComputerTest() {
        System.out.println("\nComputer Test Start");
        compute();
        computeEquation();
        System.out.println("\nComputer Checker Test End");
    }

    /**
     * Tests compute() method
     */
    private void compute() {
        double firstInputDouble;
        double secondInputDouble;
        String firstInputString;
        String secondInputString;
        String operator;
        double expectedResult;
        double actualResult;

        firstInputDouble = 5.0D;
        secondInputDouble = 10;
        operator = "*";
        expectedResult = 50;
        actualResult = Computer.compute(firstInputDouble, operator, secondInputDouble);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 1");

        firstInputString = "5.0";
        secondInputString = "10";
        operator = "*";
        expectedResult = 50;
        actualResult = Computer.compute(firstInputString, operator, secondInputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 2");

        firstInputDouble = 50000;
        secondInputDouble = 10;
        operator = "/";
        expectedResult = 5000;
        actualResult = Computer.compute(firstInputDouble, operator, secondInputDouble);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 3");

        firstInputString = "50,000";
        secondInputString = "10";
        operator = "/";
        expectedResult = 5000;
        actualResult = Computer.compute(firstInputString, operator, secondInputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 4");

        firstInputDouble = 10;
        secondInputDouble = 2;
        operator = "r";
        expectedResult = 3.1622776601683795;
        actualResult = Computer.compute(firstInputDouble, operator, secondInputDouble);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 5");

        firstInputString = "10,.0";
        secondInputString = "2";
        operator = "R";
        expectedResult = 3.1622776601683795;
        actualResult = Computer.compute(firstInputString, operator, secondInputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 6");
    }

    /**
     * Tests computeEqution() method
     */
    private void computeEquation() {
        List<String> input = new ArrayList<String>();
        double expectedResult;
        double actualResult;
        
        // 5 * 130 / 5
        input.addAll(Arrays.asList("5", "*", "130", "/", "5"));
        expectedResult = 130D;
        actualResult = Computer.computeEquation(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "computeEquation() test 1");
        input.clear();

        // 5 + 130 / 5
        input.addAll(Arrays.asList("5", "+", "130", "/", "5"));
        expectedResult = 31D;
        actualResult = Computer.computeEquation(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "computeEquation() test 2");
        input.clear();

        // (5 + 130) / 5
        input.addAll(Arrays.asList("(", "5", "+", "130", ")", "/", "5"));
        expectedResult = 27D;
        actualResult = Computer.computeEquation(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "computeEquation() test 3");
        input.clear();

        // r (5, 2) + 5
        input.addAll(Arrays.asList("r", "(", "5,", "2", ")", "+", "5"));
        expectedResult = 7.23606797749979D;
        actualResult = Computer.computeEquation(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "computeEquation() test 3");
        input.clear();
    }
}
