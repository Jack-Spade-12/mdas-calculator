/**
 * History
 * 		
 * 		December 5, 2023 - S. Cortel - Created
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

import com.sc.project.scientificcalculator.Calculator;
import com.sc.project.scientificcalculator.tester.common.AssertUnit;

public class CalculatorTest {
    
    private Calculator calculator = new Calculator();
    private AssertUnit assertUnit = new AssertUnit();

    public CalculatorTest() {
        System.out.println("\nCalculator Test Start");
        processInput();
        getEqualSignPosition();
        addValueAtIndex();
        isContinue();
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
        actualResult = calculator.getEqualSignPosition(input.toArray(new String[input.size()]));
        assertUnit.assertEquals(expectedResult, actualResult, "getEqualsSignPosition() test 1");
        input.clear();

        input.addAll(Arrays.asList("=", "+", "1"));
        expectedResult = 0;
        actualResult = calculator.getEqualSignPosition(input.toArray(new String[input.size()]));
        assertUnit.assertEquals(expectedResult, actualResult, "getEqualsSignPosition() test 2");
        input.clear();

        input.addAll(Arrays.asList("1", "+", "1", "*", "32"));
        expectedResult = -1;
        actualResult = calculator.getEqualSignPosition(input.toArray(new String[input.size()]));
        assertUnit.assertEquals(expectedResult, actualResult, "getEqualsSignPosition() test 3");
        input.clear();
    }

    /**
     * Test addValueAtIndex() method
     */
    private void addValueAtIndex() {
        String[] input;
        String inputValue;
        int inputIndex;
        String[] expectedResult;
        String[] actualResult;

        input = new String[] {"1", "2"};
        inputValue = "+";
        inputIndex = 1;
        expectedResult = new String[] {"1", "+", "2"};
        actualResult = calculator.addValueAtIndex(input, inputValue, inputIndex);
        assertUnit.assertEquals(expectedResult, actualResult, "addValueAtIndex() test 1");

        input = new String[] {"1", "(", "3", ")"};
        inputValue = "*";
        inputIndex = 1;
        expectedResult = new String[] {"1", "*", "(", "3", ")"};
        actualResult = calculator.addValueAtIndex(input, inputValue, inputIndex);
        assertUnit.assertEquals(expectedResult, actualResult, "addValueAtIndex() test 2");
    }

    /**
     * Test isContinue() method
     */
    private void isContinue() {
        String input;
        boolean expectedResult;
        boolean actualResult;

        input = "1 + 3 + 4 * 4 =";
        expectedResult = true;
        actualResult = calculator.isContinue(input);
        assertUnit.assertEquals(expectedResult, actualResult, "isContinue() test 1");
        
        input = "10 + 1 =";
        expectedResult = true;
        actualResult = calculator.isContinue(input);
        assertUnit.assertEquals(expectedResult, actualResult, "isContinue() test 2");

        input = "10 XX";
        expectedResult = false;
        actualResult = calculator.isContinue(input);
        assertUnit.assertEquals(expectedResult, actualResult, "isContinue() test 3");

        input = "xx";
        expectedResult = false;
        actualResult = calculator.isContinue(input);
        assertUnit.assertEquals(expectedResult, actualResult, "isContinue() test 4");
    }
}
