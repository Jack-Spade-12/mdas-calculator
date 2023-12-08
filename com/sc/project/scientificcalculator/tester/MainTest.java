/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 *      December 3, 2023 - S. Cortel - Modified
 *      December 4, 2023 - S. Cortel - Modified
 *      December 5, 2023 - S. Cortel - Modified
 *      December 8, 2023 - S. Cortel - Changed EquationExtractorTest to
 *                                     EquationTest
 * 
 * Purpose
 * 		
 * 		Main tester class to run all tester classes
 *      
 */
package com.sc.project.scientificcalculator.tester;

public class MainTest {
    
    public MainTest() {
        new ValueCheckerTest();
        new EquationTest();
        new InfixToPostfixConversionTest();
        new ComputerTest();
        new CalculatorTest();
    }

}
