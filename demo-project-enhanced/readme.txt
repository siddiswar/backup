How to Run:
	Clone the project from GitHub
	In conf/config.properties file, set browser_name=FIREFOX or browser_name=CHROME. Right now the value is set to FIREFOX 
	Do a mvn clean install 
	If we want to run tests on CHROME browser, In conf/config.properties file, set browser_name=CHROME and do a mvn clean install 
	
	Note : Tests are run in test phase of maven build life cycle
	Note : Right now, on running mvn clean install,we get build failure after test phase of maven build life cycle as 
	few tests are getting failed. However our test execution is successful . We can go and verify the reports for 
	failed tests . Its only that few tests are getting failed as actual behavior is not matching expected behavior

Where to see test report:
	html report => target\cucumber-reports\index.html
	json report => target\cucumber-reports\

Tests are tested successfully on FIREFOX and CHROME browsers.		
	
Tested against following browser/driver versions: 
	Firefox Version:70.0 
	Chrome Version :78.0.3904.70
	chromedriver version	:78.0.3904.70		
	geckodriver version 	:71.0.0.7191
	
	
Framework Details:

	Build Tool Used:
		Maven is used as build tool
	
	Used latest versions of following dependencies:
		selenium-java
		testng
		cucumber-java
		cucumber-testng
		rest-assured
		json-path
		poi-ooxml
		log4j
		
	What is Automated:
		Web Application test automation using Selenium WebDriver
		REST API test automation using RestAssured	

	BDD: 	
		Both Selenium WebDriver scripts and RestAssured scripts are written in BDD approach using Cucumber-JVM
		Cucumber Feature files are at the location: src/test/resources/features/
			All Web Application Scenarios are in bookingswebpage.feature
			All API Scenarios are in bookingfromapi.feature
		Cucumber Step Definitions are at the location src/test/java/com/equalexperts/stepdefinitions
			All WebDriver Step definitions are in a single file : AllWebDriverSteps.java
			All API Step definitions are in a single file : AllAPISteps.java
		
	
	Testing Framework Used: 
		TestNG is used as Test Framework. 
		TestNG Test runner is used to run the tests : src/test/java/com/equalexperts/runners/TestRunner.java
		testng.xml file is not used for running the suite
		TestNG @BeforeTest and @AfterTest are used to create and destroy WebDriver singleton instance . All the tests are run in a single WebDriver instance . Driver is destroyed only after all the features are run 
		In Every 'Then' step, TestNg assertions are used to assert that the actual and expected behaviors are matching
	
	Data Driven approach:
		Used Cucumber Scenario Outline to implement data driven approach
			1. Data to a scenario in bookingswebpage.feature is passed from feature file itself using scenario outline 
			2. Data to a scenario in bookingswebpage.feature is passed from an excel file using scenario outline
		Test data is present at conf/testdata	
			
			
	Page object Model:
		Used Page Factory to implement Page Object model
		Package	where Page classes are found : com.equalexperts.pagefactory		
		Implemented proper waits while locating and performing actions on web elements
		
	How Selenium WebDriver is Invoked:
		For each Cucumber scenario , a new browser window opens and closes after executing scenario
			
	Packages:
		1. com.equalexperts.pagefactory		: Has Page Object classes
		2. com.equalexperts.runners 		: Has TestNG runner class
		3. com.equalexperts.webdriver		: Has WebDriverFactory.java class which handles WebDriver invocation
		4. com.equalexperts.stepdefinitions	: Has all Cucumber Step Definitions
		5. com.equalexperts.utils			: Has utilities to deal with ExcelData, Json Data, Properties utils etc
		6. com.equalexperts.bookingobjects	: Has POJO classes
		7. /src/test/resources/functionalfeatures/ : Has all Cucumber feature files
			 
	Reporting:
		Cucumber built-in reports are generated
			html report => target\cucumber-reports\index.html
			json report => target\cucumber-reports\ 
		These are basic reports
		
	Logging:
		log4j is used to implement logging
		log4j.xml is found in conf folder
		logs are written in logs\application.log
		Currently log4j is configured to overwrite log file every time
		
	Comments:
		Detailed comments are provided wherever needed
		
	