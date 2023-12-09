/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 *      December 8, 2023 - S. Cortel - Converted access to AssertUnit to static access;
 *                                     Converted access to Equation to static;
 *                                     Changed from EquationExtractorTest class to 
 *                                     EquationTest;
 *                                     Changed import from EquationExtractor to
 *                                     Equation;
 *      December 10, 2023 - S. Cortel - Updated assertEquals() for String[] to use
 *                                      assertEquals() for List<String>;
 *                                      
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.Equation
 *      
 */
package com.sc.project.scientificcalculator.tester;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sc.project.assertunit.AssertUnit;
import com.sc.project.scientificcalculator.Equation;

public class EquationTest {
    
    /**
     * Tests the entire Equation class at instantiation
     */
    public EquationTest() {
        System.out.println("\nEquation Test Start");
        extractEquation();
        System.out.println("\nEquation Test End");
    }
    
    /**
     * Tests extractEquation() method
     */
    private void extractEquation() {
        String input;
        List<String> expectedResult = new ArrayList<String>();
        List<String> actualResult = new ArrayList<String>();
        
        input = "1 + 1";
        expectedResult.addAll(Arrays.asList("1", "+", "1"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 1");
        expectedResult.clear();
        actualResult.clear();
        
        input = "10 / 15 + (100 + 2)";
        expectedResult.addAll(Arrays.asList("10", "/", "15", "+", "(", "100", "+", "2", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 2");
        expectedResult.clear();
        actualResult.clear();

        input = "10/15+(100+2)";
        expectedResult.addAll(Arrays.asList("10", "/", "15", "+", "(", "100", "+", "2", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 3");
        expectedResult.clear();
        actualResult.clear();

        input = "10 + r(1, 4)";
        expectedResult.addAll(Arrays.asList("10", "+", "r", "(", "1,", "4", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 4");
        expectedResult.clear();
        actualResult.clear();

        input = "-5 + 56 + -4.1";
        expectedResult.addAll(Arrays.asList("-1", "*", "5", "+", "56", "+", "-1", "*", "4.1"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 5");
        expectedResult.clear();
        actualResult.clear();

        input = "-(1)";
        expectedResult.addAll(Arrays.asList("-1", "*", "(", "1", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 6");
        expectedResult.clear();
        actualResult.clear();

        input = "-(1 + 1 = )";
        expectedResult.addAll(Arrays.asList("-1", "*", "(", "1", "+", "1", "=", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 7");
        expectedResult.clear();
        actualResult.clear();

        input = "1+1=";
        expectedResult.addAll(Arrays.asList("1", "+", "1", "="));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 8");
        expectedResult.clear();
        actualResult.clear();

        input = "1+a=";
        expectedResult.addAll(Arrays.asList("1", "+", "0.0", "="));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 9");
        expectedResult.clear();
        actualResult.clear();

        input = "1+a=";
        expectedResult.addAll(Arrays.asList("1", "+", "5.0", "="));
        actualResult.addAll(Equation.extractEquation(input, 5D));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 10");
        expectedResult.clear();
        actualResult.clear();

        input = "1(5)2";
        expectedResult.addAll(Arrays.asList("1", "*", "(", "5", ")", "*", "2"));
        actualResult.addAll(Equation.extractEquation(input, 5D));
        AssertUnit.assertEquals(expectedResult, actualResult, "convertToProperEquation() test 11");
        expectedResult.clear();
        actualResult.clear();
    }
}
