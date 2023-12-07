/**
 * History
 * 		
 * 		December 3, 2023 - S. Cortel - Created
 *      December 7, 2023 - S. Cortel - Converted String[] to List<String>
 *      December 8, 2023 - S. Cortel - Converted AssertUnit to static access
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

import com.sc.project.assertunit.AssertUnit;
import com.sc.project.scientificcalculator.InfixToPostfixConversion;

public class InfixToPostfixConversionTest {
    
    private InfixToPostfixConversion infixToPostfixConversion = new InfixToPostfixConversion();
    
    /**
     * Tests the entire InfixToPostfixConversion class at instantiation
     */
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
        actualResult.addAll(infixToPostfixConversion.convertToPostfix(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToPostfix() test 1");
        input.clear();
        expectedResult.clear();
        actualResult.clear();

        input.addAll(Arrays.asList("2", "*", "5", "+", "(", "10", "-", "2", ")"));
        expectedResult.addAll(Arrays.asList("2", "5", "*", "10", "2", "-", "+"));
        actualResult.addAll(infixToPostfixConversion.convertToPostfix(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToPostfix() test 2");
        input.clear();
        expectedResult.clear();
        actualResult.clear();

        input.addAll(Arrays.asList("r", "(", "16,", "R", "(", "4,", "2", ")", ")", "*", "4", "-", "2"));
        expectedResult.addAll(Arrays.asList("16,", "4,", "2", "R", "r", "4", "*", "2", "-"));
        actualResult.addAll(infixToPostfixConversion.convertToPostfix(input));
        AssertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToPostfix() test 2");
        input.clear();
        expectedResult.clear();
        actualResult.clear();
    }

}
