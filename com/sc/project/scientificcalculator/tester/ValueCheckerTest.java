/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 *      December 7, 2023 - S. Cortel - Added isOperator()
 *      December 8, 2023 - S. Cortel - Changed method access to static;
 *                                     Added isOpenGrouper();
 *                                     Added isCloseGrouper();
 * 		December 9, 2023 - S. Cortel - Changed access to certain methods to static;		
 * 
 * Purpose
 * 		
 * 		Tester class for scientificcalculator.ValueChecker
 *      
 */
package com.sc.project.scientificcalculator.tester;
import com.sc.project.assertunit.AssertUnit;
import com.sc.project.scientificcalculator.ValueChecker;

public class ValueCheckerTest {

    /**
     * Tests the entire ValueChecker class at instantiation
     */
    public ValueCheckerTest() {
        System.out.println("\nValue Checker Test Start");
        isSpecial();
        isDigit();
        isSymbol();
        isOperator();
        isRootOperator();
        isGrouper();
        isNumber();
        System.out.println("\nValue Checker Test End");
    }

    /**
     * Tests the isSpecial() method
     */
    private void isSpecial() {
        char inputChar;
        String inputString;
        boolean expectedResult;
        boolean actualResult;

        inputChar = 'a';
        expectedResult = true;
        actualResult = ValueChecker.isSpecial(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSpecial() test 1");

        inputChar = 'B';
        expectedResult = false;
        actualResult = ValueChecker.isSpecial(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSpecial() test 2");

        inputString = "a";
        expectedResult = true;
        actualResult = ValueChecker.isSpecial(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSpecial() test 3");

        inputString = "b";
        expectedResult = false;
        actualResult = ValueChecker.isSpecial(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSpecial() test 4");
    }

    /**
     * Tests the isDigit() method
     */
    private void isDigit() {
        char inputChar;
        String inputString;
        boolean expectedResult;
        boolean actualResult;

        inputChar = '0';
        expectedResult = true;
        actualResult = ValueChecker.isDigit(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 1");

        inputChar = '.';
        expectedResult = true;
        actualResult = ValueChecker.isDigit(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 2");

        inputChar = ',';
        expectedResult = true;
        actualResult = ValueChecker.isDigit(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 3");

        inputChar = 'a';
        expectedResult = false;
        actualResult = ValueChecker.isDigit(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 4");

        inputChar = 'A';
        expectedResult = false;
        actualResult = ValueChecker.isDigit(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 5");

        inputString = "0";
        expectedResult = true;
        actualResult = ValueChecker.isDigit(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 6");

        inputString = ".";
        expectedResult = true;
        actualResult = ValueChecker.isDigit(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 7");

        inputString = ",";
        expectedResult = true;
        actualResult = ValueChecker.isDigit(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 8");

        inputString = "a";
        expectedResult = false;
        actualResult = ValueChecker.isDigit(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 9");

        inputString = "A";
        expectedResult = false;
        actualResult = ValueChecker.isDigit(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isDigit() test 10");
    }

    /**
     * Tests the isSymbol() method
     */
    private void isSymbol() {
        char inputChar;
        String inputString;
        boolean expectedResult;
        boolean actualResult;

        inputChar = '-';
        expectedResult = true;
        actualResult = ValueChecker.isSymbol(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 1");

        inputChar = '0';
        expectedResult = false;
        actualResult = ValueChecker.isSymbol(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 2");

        inputChar = '*';
        expectedResult = true;
        actualResult = ValueChecker.isSymbol(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 3");

        inputChar = 'r';
        expectedResult = true;
        actualResult = ValueChecker.isSymbol(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 4");

        inputString = "-";
        expectedResult = true;
        actualResult = ValueChecker.isSymbol(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 5");

        inputString = "0";
        expectedResult = false;
        actualResult = ValueChecker.isSymbol(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 6");

        inputString = "*";
        expectedResult = true;
        actualResult = ValueChecker.isSymbol(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 7");

        inputString = "r";
        expectedResult = true;
        actualResult = ValueChecker.isSymbol(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isSymbol() test 8");
    }

    /**
     * Tests the isOperator() method
     */
    private void isOperator() {
        char input;
        String inputString;
        boolean expectedResult;
        boolean actualResult;

        input = 'r';
        expectedResult = true;
        actualResult = ValueChecker.isOpertor(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 1");

        input = '/';
        expectedResult = true;
        actualResult = ValueChecker.isOpertor(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 2");

        input = '(';
        expectedResult = false;
        actualResult = ValueChecker.isOpertor(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 3");

        inputString = "r";
        expectedResult = true;
        actualResult = ValueChecker.isOpertor(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 4");

        inputString = "/";
        expectedResult = true;
        actualResult = ValueChecker.isOpertor(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 5");

        inputString = "(";
        expectedResult = false;
        actualResult = ValueChecker.isOpertor(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 6");

        inputString = "1 + 1";
        expectedResult = false;
        actualResult = ValueChecker.isOpertor(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isOperator() test 7");
    }

    /**
     * Tests the isRootOperator() method
     */
    private void isRootOperator() {
        char input;
        boolean expectedResult;
        boolean actualResult;

        input = 'r';
        expectedResult = true;
        actualResult = ValueChecker.isRootOperator(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isRootOperator() test 1");

        input = 'R';
        expectedResult = true;
        actualResult = ValueChecker.isRootOperator(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isRootOperator() test 2");

        input = 's';
        expectedResult = false;
        actualResult = ValueChecker.isRootOperator(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isRootOperator() test 3");

        input = 'S';
        expectedResult = false;
        actualResult = ValueChecker.isRootOperator(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isRootOperator() test 4");
    }

    /**
     * Tests the isGrouper() method
     */
    private void isGrouper() {
        char inputChar;
        String inputString;
        boolean expectedResult;
        boolean actualResult;

        inputChar = ')';
        expectedResult = true;
        actualResult = ValueChecker.isGrouper(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isGrouper() test 1");

        inputChar = '(';
        expectedResult = true;
        actualResult = ValueChecker.isGrouper(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isGrouper() test 2");

        inputChar = '/';
        expectedResult = false;
        actualResult = ValueChecker.isGrouper(inputChar);
        AssertUnit.assertEquals(expectedResult, actualResult, "isGrouper() test 3");

        inputString = ")";
        expectedResult = true;
        actualResult = ValueChecker.isGrouper(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isGrouper() test 4");

        inputString = "(";
        expectedResult = true;
        actualResult = ValueChecker.isGrouper(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isGrouper() test 5");

        inputString = "/";
        expectedResult = false;
        actualResult = ValueChecker.isGrouper(inputString);
        AssertUnit.assertEquals(expectedResult, actualResult, "isGrouper() test 6");
    }

    /**
     * Tests the isNumber() method
     */
    private void isNumber() {
        String input;
        boolean expectedResult;
        boolean actualResult;

        input = "(0.0)";
        expectedResult = false;
        actualResult = ValueChecker.isNumber(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isNumber() test 1");

        input = "1000";
        expectedResult = true;
        actualResult = ValueChecker.isNumber(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isNumber() test 2");

        input = "1,000.01";
        expectedResult = true;
        actualResult = ValueChecker.isNumber(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isNumber() test 3");

        input = "-1,000.14";
        expectedResult = true;
        actualResult = ValueChecker.isNumber(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isNumber() test 4");

        input = "10.3 * 130";
        expectedResult = false;
        actualResult = ValueChecker.isNumber(input);
        AssertUnit.assertEquals(expectedResult, actualResult, "isNumber() test 4");
    }
}
