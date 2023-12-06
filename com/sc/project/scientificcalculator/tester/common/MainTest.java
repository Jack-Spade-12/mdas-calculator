/**
 * History
 * 		
 * 		December 2, 2023 - S. Cortel - Created
 *      December 3, 2023 - S. Cortel - Modified
 *      December 4, 2023 - S. Cortel - Modified
 *      December 5, 2023 - S. Cortel - Modified
 * 
 * Purpose
 * 		
 * 		Main tester class to run all tester classes
 *      
 */
package com.sc.project.scientificcalculator.tester.common;
import com.sc.project.scientificcalculator.tester.CalculatorTest;
import com.sc.project.scientificcalculator.tester.ComputerTest;
import com.sc.project.scientificcalculator.tester.EquationExtractorTest;
import com.sc.project.scientificcalculator.tester.InfixToPostfixConversionTest;
import com.sc.project.scientificcalculator.tester.ValueCheckerTest;

public class MainTest {
    
    public MainTest() {
        new ValueCheckerTest();
        new EquationExtractorTest();
        new InfixToPostfixConversionTest();
        new ComputerTest();
        new CalculatorTest();
    }

}
