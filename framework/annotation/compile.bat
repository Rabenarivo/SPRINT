@echo off
echo Compiling annotation utilities...

set CLASSPATH=.;build/annotation.jar

:: Create build directory if it doesn't exist
if not exist "build" mkdir build

:: Compile Java files
echo Compiling Java files...
javac -d build -cp %CLASSPATH% *.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    exit /b 1
)

echo.
echo Compilation successful!
echo.

echo Running AnnotationTypeChecker...
java -cp %CLASSPATH%;build framework.annotation.AnnotationTypeChecker

echo.
echo.
echo Running RouteInfoExtractor...
java -cp %CLASSPATH%;build framework.annotation.RouteInfoExtractor

echo.
pause
