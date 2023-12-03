/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 * 
 * Purpose
 * 		
 * 		Custom class used for testing. Acts as the class
 *      to `assert` without using the java assert. Allows
 *      `assert`-ion even before compilation.
 *      
 */
package com.sc.project.scientificcalculator.tester.common;

 public class AssertUnit {

    /**
     * This method checks if the values are equal
     * 
     * @param expectedResult to check in <code>boolean</code> form
     * @param actualResult to check in <code>boolean</code> form
     * @param definition of the method to test
     */
    public void assertEquals (boolean expectedResult, boolean actualResult, String definition) {
        if (expectedResult != actualResult) {
            throwFailed(expectedResult, actualResult, definition);
        }
    }

    /**
     * This method checks if the values are equal
     * 
     * @param expectedResult to check in <code>String</code> form
     * @param actualResult to check in <code>String</code> form
     * @param definition of the method to test
     */
    public void assertEquals (String expectedResult, String actualResult, String definition) {
        try {
            if (expectedResult.compareTo(actualResult) != 0) {
                throwFailed(expectedResult, actualResult, definition);
            }
        }
        catch (NullPointerException e) {
            throwFailed(expectedResult, actualResult, definition);
        }
    }

    /**
     * This method checks if the values are equal
     * 
     * @param expectedResult to check in <code>int</code> form
     * @param actualResult to check in <code>int</code> form
     * @param definition of the method to test
     */
    public void assertEquals (int expectedResult, int actualResult, String definition) {
        if (expectedResult != actualResult) {
            throwFailed(expectedResult, actualResult, definition);
        }
    }

    /**
     * This method checks if the values are equal
     * 
     * @param expectedResult to check in <code>int</code> form
     * @param actualResult to check in <code>int</code> form
     * @param definition of the method to test
     */
    public void assertEquals (double expectedResult, double actualResult, String definition) {
        if (expectedResult != actualResult) {
            throwFailed(expectedResult, actualResult, definition);
        }
    }

    /**
     * This method checks if the values are equal
     * 
     * @param expectedResult to check in <code>String[]</code> form
     * @param actualResult to check in <code>String[]</code> form
     * @param definition of the method to test
     */
    public void assertEquals (String[] expectedResult, String[] actualResult, String definition) {
        // Loop for each string array
        int i;
        int expectedResultLenth = expectedResult.length;
        int actualResultLength = actualResult.length;

        System.out.println();
        for (i = 0; i < expectedResultLenth && i < actualResultLength; i++ ) {
            System.out.println("\n\t" + i + " - Actual Result   : " + actualResult[i]);
            System.out.println("\n\t" + i + " - Expected Result : " + expectedResult[i]);
            assertEquals(expectedResult[i], actualResult[i], definition + " " + i);
        }

        // Loop through the rest of expected result
        if (expectedResultLenth > i) {
            for (int j = i; j < expectedResultLenth; j++) {
                System.out.println("\n\t" + j + " - Actual Result   : null");
                System.out.println("\n\t" + j + " - Expected Result : " + expectedResult[j]);
                assertEquals(expectedResult[j], null, definition + " " + j);
            }
        }

        // Loop through the rest of actual result
        if (actualResultLength > i) {
            for (int j = i; j < actualResultLength; j++) {
                System.out.println("\n\t" + j + " - Actual Result   : " + actualResult[j]);
                System.out.println("\n\t" + j + " - Expected Result : null");
                assertEquals(actualResult[j], null, definition + " " + j);
            }
        }
    }

    /**
     * This method prints out the failed assert message
     * 
     * @param expectedResult in <code>String</code> form
     * @param actualResult in <code>String</code> form
     * @param definition of the method tested
     */
    private void throwFailed(String expectedResult, String actualResult, String definition) {
        System.out.println("\n\t" + definition + " failed");
        System.out.println("\tExpected Result : " + expectedResult);
        System.out.println("\tActual Result   : " + actualResult);
    }

    /**
     * This method prints out the failed assert message, overload
     * method of the original throwFailed() to accomodate
     * <code>int</code> values
     * 
     * @param expectedResult in <code>int</code> form
     * @param actualResult in <code>int</code> form
     * @param definition of the method tested
     */
    private void throwFailed(int expectedResult, int actualResult, String definition) {
        throwFailed(String.valueOf(expectedResult), String.valueOf(actualResult), definition);
    }

    /**
     * This method prints out the failed assert message, overload
     * method of the original throwFailed() to accomodate
     * <code>double</code> values
     * 
     * @param expectedResult in <code>double</code> form
     * @param actualResult in <code>double</code> form
     * @param definition of the method tested
     */
    private void throwFailed(double expectedResult, double actualResult, String definition) {
        throwFailed(String.valueOf(expectedResult), String.valueOf(actualResult), definition);
    }

    /**
     * This method prints out the failed assert message, overload
     * method of the original throwFailed() to accomodate
     * <code>boolean</code> values
     * 
     * @param expectedResult in <code>boolean</code> form
     * @param actualResult in <code>boolean</code> form
     * @param definition of the method tested
     */
    private void throwFailed(boolean expectedResult, boolean actualResult, String definition) {
        throwFailed(String.valueOf(expectedResult), String.valueOf(actualResult), definition);
    }
 }
