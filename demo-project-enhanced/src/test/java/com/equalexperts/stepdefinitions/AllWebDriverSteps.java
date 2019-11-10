package com.equalexperts.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.equalexperts.bookingobjects.BookingData;
import com.equalexperts.pagefactory.BookingsPage;
import com.equalexperts.utils.ExcelUtils;
import com.equalexperts.utils.PropertiesReaderUtil;
import com.equalexperts.webdriver.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AllWebDriverSteps {
	
	//All WebDriver test step definitions are in this class

	WebDriver webDriver;
	List<WebElement> allBookingElements;
	BookingsPage bookingsPage;
	String expectedBookingsPageTitle;
	String actualBookingsPageTitle;
	int countBeforeCreation;
	int countAfterCreation;
	int countBeforeDeletion;
	int countAfterDeletion;
	WebDriverWait wait;
	BookingData bookingData;
	ExcelUtils excelUtils;

	Logger logger = Logger.getLogger(AllWebDriverSteps.class.getName());
	@Before("@WebTests")
	public void setUp() throws InterruptedException {
		logger.info("-----------------Before Hook of AllWebDriverSteps---------------------------");
		PropertiesReaderUtil.getInstance();
		expectedBookingsPageTitle = System.getProperty("bookings_page_title");
		webDriver = WebDriverFactory.getInstance().getWebDriver();
		bookingsPage = new BookingsPage(webDriver);
		excelUtils = new ExcelUtils();
	}

	@After("@WebTests")
	public void tearDown() {
		logger.info("-----------------After Hook of AllWebDriverSteps------------------------------");
		webDriver.quit();
	}

	// Opens http://hotel-test.equalexperts.io/ and asserts title matches
	// Bookings count is captured in this step
	@Given("User is on booking page")
	public void user_is_on_booking_page() throws InterruptedException {
		logger.info("User is on booking page");
		webDriver.get(System.getProperty("url"));
		actualBookingsPageTitle = webDriver.getTitle();
		Assert.assertEquals(actualBookingsPageTitle, expectedBookingsPageTitle);
		Thread.sleep(3000);
		countBeforeCreation = bookingsPage.getBookingsCount();
		countBeforeDeletion = bookingsPage.getBookingsCount();
		logger.info("Count Before Booking/Deletion :" + countBeforeCreation);

	}

	// Data entered into Firstname field
	@When("User enters {string} in firstname field")
	public void user_enters_in_firstname_field(String fName) {
		logger.info("User enters " +fName+ "  in firstname field");
		bookingsPage.fillFirstName(fName);
	}

	// Data entered into Surname field
	@When("User enters {string} in surname field")
	public void user_enters_in_surname_field(String sName) {
		logger.info("User enters " +sName+ "  in surname field");
		bookingsPage.fillSurName(sName);
	}

	// Data entered into Price field
	@When("User enters {string} in Price field")
	public void user_enters_in_Price_field(String price) {
		logger.info("User enters " +price+ "  in Price field");
		bookingsPage.fillPrice(price);
	}

	// true/false value is selected in deposit drop down
	@When("User selects {string} in deposit field")
	public void user_selects_in_deposit_field(String depositPaid) {
		logger.info("User selects " +depositPaid+ "  in deposit field");
		bookingsPage.fillDepositPaid(depositPaid);
	}

	// Date string is entered into check in field
	@When("User enters {string} in checkin field")
	public void user_enters_in_checkin_field(String fromDateString) {
		logger.info("User enters " +fromDateString+ "  in checkin field");
		bookingsPage.fillCheckinDate(fromDateString);
	}

	// Date string is entered into check out field
	@When("User enters {string} in checkout field")
	public void user_enters_in_checkout_field(String toDateString) {
		logger.info("User enters " +toDateString+ "  in checkout field");
		bookingsPage.fillCheckoutDate(toDateString);
	}

	// Save button is clicked
	@When("User clicks save button")
	public void user_clicks_save_button() throws InterruptedException {
		logger.info("User clicks save button");
		bookingsPage.clickSaveButton();
		Thread.sleep(5000);
	}

	// In this step, count of bookings after clicking save button is calculated
	// If count after is greater than count before by 1, then its a successfull
	// creation.
	@Then("Booking should get created")
	public void user_should_get_created() {
		logger.info("User clicks save button");
		countAfterCreation = bookingsPage.getBookingsCount();
		logger.info("After Booking :" + countAfterCreation);
		logger.info("After Booking :" + countAfterCreation);
		Assert.assertEquals(countAfterCreation, countBeforeCreation + 1,
				"Booking failed as afterBookingCount!=beforeBookingCount+1 ");
	}

	// For testing delete booking functionality there should be at least one
	// booking in the system
	// If there is no booking at all, this step is made to fail and subsequent
	// steps will be skipped
	@Given("There is atleast one booking in bookings page")
	public void there_is_atleast_one_booking_in_bookings_page() throws InterruptedException {
		logger.info("There is atleast one booking in bookings page");
		countBeforeDeletion = bookingsPage.getBookingsCount();
		logger.info("Bookings Count Before Deletion :" + countBeforeDeletion);
		logger.info("Bookings Count Before Deletion :" + countBeforeDeletion);
		Assert.assertTrue(countBeforeDeletion > 0, "No Bookings to Delete");
	}

	// Delete button of the booking present at the top is clicked
	@When("User clicks delete button of the booking present at the top")
	public void user_clicks_delete_button_of_the_booking_present_at_the_top() throws InterruptedException {
		logger.info("User clicks delete button of the booking present at the top");
		bookingsPage.deleteFirstBooking();
		Thread.sleep(3000);
	}

	// Count of bookings after clicking delete button is calculated
	// If count after is less than count before by 1, then its a successfull
	// deletion
	@Then("Booking should get deleated")
	public void booking_should_get_deleated() {
		logger.info("User should get deleated");
		countAfterDeletion = bookingsPage.getBookingsCount();
		logger.info("Total Bookings After Deletion = " + countAfterDeletion);
		logger.info("Total Bookings After Deletion = " + countAfterDeletion);
		Assert.assertEquals(countAfterDeletion, countBeforeDeletion - 1, "Deletion failed");

	}

	// This method takes rowNumber as input from scenario outline and , reads
	// the data present in excel for that row and assigns those values to
	// BookingData object
	@Given("booking data is presnt in the row {int}")
	public void booking_data_is_presnt_in_the_row(Integer rowNumber) throws InvalidFormatException, IOException {
		logger.info("booking data is presnt in the row :" + rowNumber);
		logger.info("Row Number :" + rowNumber);
		bookingData = excelUtils.returnBookingData(rowNumber);
	}

	// In this step, data read from excel into BookingData object ,is filled in
	// all fields
	@When("User enters data into fields")
	public void user_enters_data_into_fields() {
		logger.info("User enters data into fields");
		bookingsPage.fillFirstName(bookingData.getFirstname());
		bookingsPage.fillSurName(bookingData.getLastname());
		bookingsPage.fillPrice(bookingData.getTotalprice().toString());
		bookingsPage.fillDepositPaid(bookingData.getDepositpaid());
		bookingsPage.fillCheckinDate(bookingData.getBookingdates().getCheckin());
		bookingsPage.fillCheckoutDate(bookingData.getBookingdates().getCheckout());

	}

	// In this step, count of bookings after clicking save button is calculated
	// If count after is greater than count before by 1, then its a successfull creation else booking is not created
	// For every set of testdata, it is given in the excel that whether a booking should get created or not .
	// We assert the actual booking creation status matches the expected booking creation status
	@Then("Booking creation status should be as expected")
	public void booking_creation_status_should_be_as_expected() {
		logger.info("Booking creation status should be as expected");
		countAfterCreation = bookingsPage.getBookingsCount();
		logger.info("After Booking :" + countAfterCreation);
		logger.info("After Booking :" + countAfterCreation);
		boolean bookingCreatedActual = false;
		if (countAfterCreation == (countBeforeCreation + 1)) {
			logger.info("User Created");
			bookingCreatedActual = true;
		} else {
			logger.info("User Not Created");
			bookingCreatedActual = false;
		}

		if (bookingData.getBookingCreationStatus().equalsIgnoreCase("CREATE")) {
			Assert.assertTrue(bookingCreatedActual, "Booking should get created but its not created");
		} else {
			Assert.assertFalse(bookingCreatedActual, "Booking got created where it is not supposed to be created");
		}

	}

}
