@echo off
REM ------------------------------------------------------------------------
REM Script de déploiement Windows pour compiler le package annotation et créer le JAR
REM ------------------------------------------------------------------------

REM Définition des chemins
set "ANNOTATION_DIR=D:\ITU\S5\MR NAINA\SPRINT\framework\annotation"
set "BUILD_DIR=%ANNOTATION_DIR%\build"
set "CLASSES_DIR=%BUILD_DIR%\classes"
set "SERVLET_JAR=D:\ITU\S5\MR NAINA\SPRINT\jakarta.servlet-api_5.0.0.jar"
set "TEST_DIR=D:\OUTILS\LOGICIELS\apache-tomcat-11.0.7\webapps\testFramework"

REM Création des dossiers de sortie
if not exist "%BUILD_DIR%" mkdir "%BUILD_DIR%"
if not exist "%CLASSES_DIR%" mkdir "%CLASSES_DIR%"

echo Compilation du package annotation...

REM Compilation de tous les fichiers Java du package annotation ensemble
echo   Compilation de tous les fichiers Java...
javac -classpath "%SERVLET_JAR%" -d "%CLASSES_DIR%" "%ANNOTATION_DIR%\AnnotationTest.java" "%ANNOTATION_DIR%\UrlMapping.java" "%ANNOTATION_DIR%\UserController.java"
if errorlevel 1 (
    echo Erreur de compilation du package annotation
    exit /b 1
)

REM Création du JAR du package annotation
echo Creation du JAR annotation...
cd /d "%BUILD_DIR%"
if exist "annotation.jar" del "annotation.jar"
jar cvf "annotation.jar" -C "classes" .

REM Copie du JAR dans le répertoire build principal
echo Copie du annotation.jar dans le dossier build principal...
set "MAIN_BUILD_DIR=D:\ITU\S5\MR NAINA\SPRINT\build"
if not exist "%MAIN_BUILD_DIR%" mkdir "%MAIN_BUILD_DIR%"
xcopy "%BUILD_DIR%\annotation.jar" "%MAIN_BUILD_DIR%\" /Y >nul

REM Copie du JAR dans le répertoire de déploiement
echo Copie du annotation.jar dans le projet Test...
if not exist "%TEST_DIR%\WEB-INF\lib" mkdir "%TEST_DIR%\WEB-INF\lib"
xcopy "%BUILD_DIR%\annotation.jar" "%TEST_DIR%\WEB-INF\lib\" /Y >nul

echo Déploiement du package annotation terminé avec succès!
echo JAR créé: %BUILD_DIR%\annotation.jar
echo JAR copié vers: %MAIN_BUILD_DIR%\annotation.jar
echo JAR copié vers: %TEST_DIR%\WEB-INF\lib\annotation.jar

REM Optionnel: démarrage de Tomcat si le chemin est fourni en paramètre
if not "%~1"=="" (
    echo Démarrage de Tomcat...
    call "%~1\bin\startup.bat"
)

pause
