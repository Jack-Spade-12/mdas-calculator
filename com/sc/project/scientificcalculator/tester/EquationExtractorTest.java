/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.EquationExtractor
 *      
 */
package com.sc.project.scientificcalculator.tester;

import com.sc.project.scientificcalculator.EquationExtractor;
import com.sc.project.scientificcalculator.tester.common.AssertUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquationExtractorTest {
    
    private EquationExtractor equationExtractor = new EquationExtractor();
    private AssertUnit assertUnit = new AssertUnit();
    
    /**
     * Tests the entire EquationExtractor class at instantiation
     */
    public EquationExtractorTest() {
        System.out.println("\nEquation Extractor Test Start");
        convertToProperEquation();
        System.out.println("\nEquation Extractor Test End");
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
        actualResult.addAll(Arrays.asList(equationExtractor.convertToProperEquation(input)));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 1");
        expectedResult.clear();
        actualResult.clear();
        
        input = "10 / 15 + (100 + 2)";
        expectedResult.addAll(Arrays.asList("10", "/", "15", "+", "(", "100", "+", "2", ")"));
        actualResult.addAll(Arrays.asList(equationExtractor.convertToProperEquation(input)));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 2");
        expectedResult.clear();
        actualResult.clear();

        input = "10/15+(100+2)";
        expectedResult.addAll(Arrays.asList("10", "/", "15", "+", "(", "100", "+", "2", ")"));
        actualResult.addAll(Arrays.asList(equationExtractor.convertToProperEquation(input)));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 3");
        expectedResult.clear();
        actualResult.clear();

        input = "10 + r(1, 4)";
        expectedResult.addAll(Arrays.asList("10", "+", "r", "(", "1,", "4", ")"));
        actualResult.addAll(Arrays.asList(equationExtractor.convertToProperEquation(input)));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 4");
        expectedResult.clear();
        actualResult.clear();

        input = "-5 + 56 + -4.1";
        expectedResult.addAll(Arrays.asList("-5", "+", "56", "+", "-4.1"));
        actualResult.addAll(Arrays.asList(equationExtractor.convertToProperEquation(input)));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 4");
        expectedResult.clear();
        actualResult.clear();

        input = "-(1)";
        expectedResult.addAll(Arrays.asList("-", "(", "1", ")"));
        actualResult.addAll(Arrays.asList(equationExtractor.convertToProperEquation(input)));
        assertUnit.assertEquals(expectedResult.toArray(new String[expectedResult.size()]), actualResult.toArray(new String[actualResult.size()]), "convertToProperEquation() test 4");
        expectedResult.clear();
        actualResult.clear();
    }
}
