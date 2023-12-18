@echo

set CLASS_FOLDER=bin
set EXPORTING_FOLDER=com
set PROJECT_NAME=scientific-calculator
set EXPORT_PATH=lib
set ROOT_DIRECTORY=%CD%

:: Create export path if not exist
if not exist "%EXPORT_PATH%" (
	mkdir "%EXPORT_PATH%"
)

cd %CLASS_FOLDER%

:: Zip the folders in EXPORTING_FOLDER
zip -r -p %ROOT_DIRECTORY%\%EXPORT_PATH%\%PROJECT_NAME%.jar .\%EXPORTING_FOLDER%