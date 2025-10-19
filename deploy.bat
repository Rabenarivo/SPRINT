@echo off
chcp 65001 > nul
REM ------------------------------------------------------------------------
REM Script de déploiement simplifié pour le framework
REM ------------------------------------------------------------------------

set "FRAMEWORK_DIR=D:\ITU\S5\MR NAINA\framework"
set "BUILD_DIR=%FRAMEWORK_DIR%\build"
set "TEST_DIR=D:\OUTILS\LOGICIELS\apache-tomcat-11.0.7\webapps\testFramework"
set "SRC_DIR=D:\ITU\S5\MR NAINA\SPRINT\src"

echo === Déploiement du framework vers Tomcat ===

REM ------------------------------------------------------------------------
REM Étape 1 : Nettoyage et création des dossiers
REM ------------------------------------------------------------------------
echo [INFO] Nettoyage du déploiement précédent...
if exist "%TEST_DIR%" rmdir /S /Q "%TEST_DIR%"
echo [INFO] Création de la structure des dossiers...
mkdir "%TEST_DIR%"
mkdir "%TEST_DIR%\WEB-INF"
mkdir "%TEST_DIR%\WEB-INF\lib"
mkdir "%TEST_DIR%\WEB-INF\classes"

REM ------------------------------------------------------------------------
REM Étape 2 : Copie du framework.jar déjà compilé
REM ------------------------------------------------------------------------
if exist "%BUILD_DIR%\framework.jar" (
    echo [OK] Copie du framework.jar...
    xcopy "%BUILD_DIR%\framework.jar" "%TEST_DIR%\WEB-INF\lib\" /Y >nul
) else (
    echo [ERREUR] framework.jar introuvable dans %BUILD_DIR%
    exit /b 1
)

REM ------------------------------------------------------------------------
REM Étape 3 : Copie des fichiers JSP
REM ------------------------------------------------------------------------
if exist "%SRC_DIR%" (
    echo [INFO] Copie des fichiers JSP depuis %SRC_DIR%...
    xcopy "%SRC_DIR%\*.jsp" "%TEST_DIR%\" /Y /I
    if errorlevel 1 (
        echo [ERREUR] Échec de la copie des fichiers JSP
        exit /b 1
    ) else (
        echo [OK] Fichiers JSP copiés avec succès
    )
) else (
    echo [ERREUR] Dossier source JSP non trouvé : %SRC_DIR%
    exit /b 1
)

REM ------------------------------------------------------------------------
REM Étape 4 : Création du web.xml si inexistant
REM ------------------------------------------------------------------------
if not exist "%TEST_DIR%\WEB-INF\web.xml" (
    echo [Création] du fichier web.xml par défaut...
    (
        echo ^<?xml version="1.0" encoding="UTF-8"?^>
        echo ^<web-app xmlns="https://jakarta.ee/xml/ns/jakartaee"
        echo          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        echo          xsi:schemaLocation="https://jakarta.ee/xml/ns/jakartaee https://jakarta.ee/xml/ns/jakartaee/web-app_5_0.xsd"
        echo          version="5.0"^>
        echo     ^<servlet^>
        echo         ^<servlet-name^>FrontServlet^</servlet-name^>
        echo         ^<servlet-class^>framework.servlet.FrontServlet^</servlet-class^>
        echo     ^</servlet^>
        echo     ^<servlet-mapping^>
        echo         ^<servlet-name^>FrontServlet^</servlet-name^>
        echo         ^<url-pattern^>/^</url-pattern^>
        echo     ^</servlet-mapping^>
        echo     ^<welcome-file-list^>
        echo         ^<welcome-file^>index.jsp^</welcome-file^>
        echo     ^</welcome-file-list^>
        echo ^</web-app^>
    ) > "%TEST_DIR%\WEB-INF\web.xml"
    echo [OK] web.xml généré automatiquement.
)

REM ------------------------------------------------------------------------
REM Étape 5 : Démarrage de Tomcat (optionnel)
REM ------------------------------------------------------------------------
if not "%~1"=="" (
    echo Démarrage de Tomcat...
    call "%~1\bin\startup.bat"
)

echo === Déploiement terminé avec succès ===
pause
