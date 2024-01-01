@echo off
cd /d "%~dp0"
javac *.java -d .
java Gameshow
pause
