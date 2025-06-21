@ECHO OFF
ECHO Running Maven tests...
cd /d "%~dp0"
mvn clean test
PAUSE