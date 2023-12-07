/**
 * History
 * 		
 * 		December 3, 2023 - S. Cortel - Created
 *      December 8, 2023 - S. Cortel - Converted AssertUnit to static access
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.Computer
 * 
 */
package com.sc.project.scientificcalculator.tester;
import com.sc.project.assertunit.AssertUnit;
import com.sc.project.scientificcalculator.Computer;

public class ComputerTest {
    
    private Computer computer = new Computer();
    
    /**
     * Tests the entire Computer class at instantiation
     */
    public ComputerTest() {
        System.out.println("\nComputer Test Start");
        compute();
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
        actualResult = computer.compute(firstInputDouble, operator, secondInputDouble);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 1");

        firstInputString = "5.0";
        secondInputString = "10";
        operator = "*";
        expectedResult = 50;
        actualResult = computer.compute(firstInputString, operator, secondInputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 2");

        firstInputDouble = 50000;
        secondInputDouble = 10;
        operator = "/";
        expectedResult = 5000;
        actualResult = computer.compute(firstInputDouble, operator, secondInputDouble);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 3");

        firstInputString = "50,000";
        secondInputString = "10";
        operator = "/";
        expectedResult = 5000;
        actualResult = computer.compute(firstInputString, operator, secondInputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 4");

        firstInputDouble = 10;
        secondInputDouble = 2;
        operator = "r";
        expectedResult = 3.1622776601683795;
        actualResult = computer.compute(firstInputDouble, operator, secondInputDouble);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 5");

        firstInputString = "10,.0";
        secondInputString = "2";
        operator = "R";
        expectedResult = 3.1622776601683795;
        actualResult = computer.compute(firstInputString, operator, secondInputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "compute() test 6");
    }

}
