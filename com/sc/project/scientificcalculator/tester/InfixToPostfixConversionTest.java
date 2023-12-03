/**
 * History
 * 		
 * 		December 3, 2023 - S. Cortel - Created
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.InfixToPostfixConversion
 *      
 */
package com.sc.project.scientificcalculator.tester;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sc.project.scientificcalculator.InfixToPostfixConversion;
import com.sc.project.scientificcalculator.tester.common.AssertUnit;

public class InfixToPostfixConversionTest {
    
    private AssertUnit assertUnit = new AssertUnit();
    private InfixToPostfixConversion infixToPostfixConversion = new InfixToPostfixConversion();
    
    public InfixToPostfixConversionTest() {
        System.out.println("\nInfix To Postfix Conversion Test Start");
        convertToPostfix();
        System.out.println("\nInfix To Postfix Conversion Test End");
    }

    /**
     * Tests the convertToPostfix() method
     */
    private void convertToPostfix() {
        List<String> input = new ArrayList<String>();
        List<String> expectedResult = new ArrayList<String>();
        List<String> actualResult = new ArrayList<String>();

        input.addAll(Arrays.asList("1", "+", "1"));
        expectedResult.addAll(Arrays.asList("1", "1", "+"));
        actualResult.addAll(Arrays.asList(infixToPostfixConversion.convertToPostfix(input.toArray(new String[input.size()]))));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToPostfix() test 1");
        input.clear();
        expectedResult.clear();
        actualResult.clear();

        input.addAll(Arrays.asList("2", "*", "5", "+", "(", "10", "-", "2", ")"));
        expectedResult.addAll(Arrays.asList("2", "5", "*", "10", "2", "-", "+"));
        actualResult.addAll(Arrays.asList(infixToPostfixConversion.convertToPostfix(input.toArray(new String[input.size()]))));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToPostfix() test 2");
        input.clear();
        expectedResult.clear();
        actualResult.clear();

        input.addAll(Arrays.asList("r", "(", "16,", "R", "(", "4,", "2", ")", ")", "*", "4", "-", "2"));
        expectedResult.addAll(Arrays.asList("16,", "4,", "2", "R", "r", "4", "*", "2", "-"));
        actualResult.addAll(Arrays.asList(infixToPostfixConversion.convertToPostfix(input.toArray(new String[input.size()]))));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToPostfix() test 2");
        input.clear();
        expectedResult.clear();
        actualResult.clear();
    }

}
