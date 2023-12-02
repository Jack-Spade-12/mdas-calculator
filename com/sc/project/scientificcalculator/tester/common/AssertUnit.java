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
        if (expectedResult.compareTo(actualResult) != 0) {
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
