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
        convertToProperEquation();
        System.out.println("\nEquation Test End");
    }
    
    /**
     * Tests convertToProperEquation() method
     */
    private void convertToProperEquation() {
        String input;
        List<String> expectedResult = new ArrayList<String>();
        List<String> actualResult = new ArrayList<String>();
        
        input = "1 + 1";
        expectedResult.addAll(Arrays.asList("1", "+", "1"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 1");
        expectedResult.clear();
        actualResult.clear();
        
        input = "10 / 15 + (100 + 2)";
        expectedResult.addAll(Arrays.asList("10", "/", "15", "+", "(", "100", "+", "2", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 2");
        expectedResult.clear();
        actualResult.clear();

        input = "10/15+(100+2)";
        expectedResult.addAll(Arrays.asList("10", "/", "15", "+", "(", "100", "+", "2", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 3");
        expectedResult.clear();
        actualResult.clear();

        input = "10 + r(1, 4)";
        expectedResult.addAll(Arrays.asList("10", "+", "r", "(", "1,", "4", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 4");
        expectedResult.clear();
        actualResult.clear();

        input = "-5 + 56 + -4.1";
        expectedResult.addAll(Arrays.asList("-5", "+", "56", "+", "-4.1"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 5");
        expectedResult.clear();
        actualResult.clear();

        input = "-(1)";
        expectedResult.addAll(Arrays.asList("-", "(", "1", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 6");
        expectedResult.clear();
        actualResult.clear();

        input = "-(1 + 1 = )";
        expectedResult.addAll(Arrays.asList("-", "(", "1", "+", "1", "=", ")"));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 7");
        expectedResult.clear();
        actualResult.clear();

        input = "1+1=";
        expectedResult.addAll(Arrays.asList("1", "+", "1", "="));
        actualResult.addAll(Equation.extractEquation(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 8");
        expectedResult.clear();
        actualResult.clear();
    }
}
