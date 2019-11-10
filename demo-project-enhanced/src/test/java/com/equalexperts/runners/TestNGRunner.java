package com.equalexperts.runners;

/**
 * @author Siddu
 */
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberFeatureWrapper;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.PickleEventWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

//TestNG runner class. 
@CucumberOptions(features = { "src/test/resources/functionalfeatures" }, glue = {
		"com/equalexperts/stepdefinitions", }, plugin = { "pretty", "html:target/cucumber-reports/",
				"json:target/cucumber-reports/cucumber.json" }, monochrome = true)
public class TestNGRunner{
	private TestNGCucumberRunner testNGCucumberRunner;
	WebDriver webDriver;
	Logger logger = Logger.getLogger(TestNGRunner.class.getName());

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@BeforeTest(alwaysRun = true)
	public void testNgBeforeTest() {

	}

	@AfterTest(alwaysRun = true)
	public void testNgAfterTest() {

	}

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void runScenario(PickleEventWrapper pickleWrapper, CucumberFeatureWrapper featureWrapper) throws Throwable {
		// the 'featureWrapper' parameter solely exists to display the feature
		// file in a test report
		testNGCucumberRunner.runScenario(pickleWrapper.getPickleEvent());
	}

	/**
	 * Returns two dimensional array of PickleEventWrapper scenarios with their
	 * associated CucumberFeatureWrapper feature.
	 *
	 * @return a two dimensional array of scenarios features.
	 */
	@DataProvider
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		if (testNGCucumberRunner == null) {
			return;
		}
		testNGCucumberRunner.finish();
	}
}
