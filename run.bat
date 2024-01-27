@echo off

echo Compiling the project...
call mvn clean test-compile

echo Running automated tests...
call mvn surefire:test

echo Opening report...
start "" "target\reports\demoblaze.html"