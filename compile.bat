@echo
setlocal enabledelayedexpansion

:: Base paths
set JAVA_FILES_PATH=src\com\sc\project\scientificcalculator\
set CLASS_PATH=bin

:: Dependencies
set DEPENDENCY_PATH=lib

:: Java files
set FILE[0]=ValueChecker.java
set FILE[1]=InfixToPostfixConversion.java
set FILE[2]=Computer.java
set FILE[3]=Equation.java
set FILE[4]=CalculatorIO.java
set FILE[5]=Calculator.java
set FILE[6]=tester\ValueCheckerTest.java
set FILE[7]=tester\InfixToPostfixConversionTest.java
set FILE[8]=tester\ComputerTest.java
set FILE[9]=tester\EquationTest.java
set FILE[10]=tester\MainTest.java
set FILE[11]=Main.java

:: Create script for javac
set JAVAC_SCRIPT=javac -classpath "%DEPENDENCY_PATH%\*" -d %CLASS_PATH%
set "FILE_COUNT=0"

:loop
if defined FILE[%FILE_COUNT%] (
	call set JAVAC_SCRIPT=%JAVAC_SCRIPT% %JAVA_FILES_PATH%%%FILE[%FILE_COUNT%]%%
	set /a "FILE_COUNT+=1"
	goto :loop
)

:: Execute created javac script
%JAVAC_SCRIPT%