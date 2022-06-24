set ProjectPath=%~dp0
cd %ProjectPath%
set p=%PATH%
java -javaagent:"%ProjectPath%libAllure\aspectjweaver-1.9.8.M1.jar" -classpath "%ProjectPath%bin;%ProjectPath%libAllure\*;%ProjectPath%libLogging\*;%ProjectPath%libraries\*;%ProjectPath%libReportNG\*;%ProjectPath%libWebDriverManager\*" org.testng.TestNG "%ProjectPath%bin\runNopCommerceUserTestcases.xml"
allure generate allure-json -c -o allure-html && allure open allure-html 
pause