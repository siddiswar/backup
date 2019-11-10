package com.equalexperts.stepdefinitions;

/**
 * @author Siddu
 */
import java.io.File;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.equalexperts.utils.JsonUtils;
import com.equalexperts.utils.PropertiesReaderUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AllAPISteps {

	Response response;
	RequestSpecification postRequest;
	String getAllBookingsURI;
	String createNewBookingURI;
	String deleteBookingURI;
	JsonUtils jsonUtils;
	JsonPath jsonPath;
	Integer latestBookingId;
	Logger logger = Logger.getLogger(AllAPISteps.class.getName());

	@Before("@ApiTests")
	public void setUp() {
		logger.info("-----------------Before Hook of AllAPISteps-----------------");
		PropertiesReaderUtil.getInstance();
		jsonUtils = new JsonUtils();

	}

	@After("@ApiTests")
	public void tearDown() {
		logger.info("-----------------After Hook of AllAPISteps-----------------");
	}

	// Construct Get URI
	@Given("I have Get URI")
	public void i_have_Get_URI() {
		logger.info("I have Get URI");
		getAllBookingsURI = System.getProperty("base_uri");
		logger.info(getAllBookingsURI);
	}

	// Do a get call and store the response
	@When("I make a Get call")
	public void i_make_a_Get_call() {
		logger.info("I make a Get call");
		response = RestAssured.given().when().get(getAllBookingsURI);
	}
	
	//Assert that actual response code matches expected response code supplied from scenario
	@Then("Response should have {int} status code")
	public void response_should_have_status_code(Integer expectedResponseCode) {
	    logger.info("Response should have "+ expectedResponseCode +" status code");
	    Integer actualResponseCode = response.getStatusCode();
 	    logger.info("actualResponseCode :" + actualResponseCode );
	    logger.info("expectedResponseCode " + expectedResponseCode);
	    Assert.assertEquals(actualResponseCode, expectedResponseCode,"Actual and Expected Response codes are not matching.");
	}
	
	//Assert that actual and expected response content types match
	@Then("Respose should be of Json type")
	public void respose_should_be_of_Json_type() {
	    logger.info("Respose should be of Json type");
	    logger.info(response.getContentType());
	    logger.info(ContentType.JSON);
	    Assert.assertTrue(response.getContentType().contains(ContentType.JSON.toString()),"Content types not matching");
	}


	// Case of no bookings => Response string is "[]"
	// Case of 1 or more bookings => Json response contains "bookingid" nodes
	// In this step, any one of the above 2 conditions should be matched. Else
	// test fails
	@Then("I should get all bookings present in the system")
	public void i_should_get_all_bookins_present_in_the_system() {
		logger.info("I should get all bookins present in the system");
		String getResponseString = response.asString();
		logger.info(getResponseString);
		boolean validGetBookingsResponseString = false;

		if (getResponseString.equalsIgnoreCase("[]")) {
			// Case of empty response=> Which means no bookings at all
			validGetBookingsResponseString = true;
		} else {
			jsonPath = response.jsonPath();
			int bookingCount = jsonUtils.getBookingsCount(jsonPath);
			logger.info("Bookings Count :" + bookingCount);
			if (bookingCount > 0) {// Booking Ids found in response
				validGetBookingsResponseString = true;
			}
		}

		Assert.assertTrue(validGetBookingsResponseString, "Get call response doesn't have bookings");

	}

	// Construct create new booking URI
	@Given("I have POST URI")
	public void i_have_POST_URI() {
		logger.info("I have POST URI");
		createNewBookingURI = System.getProperty("base_uri");
		logger.info(createNewBookingURI);
	}

	// Booking data is present in conf/testdata/newbooking.json
	// Post Request is constructed with ContentType as JSON and with
	// newbooking.json in body
	@Given("I have booking data in the jsonfile {string}")
	public void i_have_booking_data_in_the_jsonfile(String jsonFilePath) {
		logger.info("I have booking data in the jsonfile :" + jsonFilePath);
		File jsonFile = new File(jsonFilePath);
		postRequest = RestAssured.given().contentType(ContentType.JSON).body(jsonFile);
	}

	// Post Request is sent to createNewBookingURI
	@When("I make a post request with the json file")
	public void i_make_a_post_request_with_the_json_file() {
		logger.info("I make a post request with the json file");
		response = postRequest.when().post(createNewBookingURI);
	}

	// On successfull POST request, response contains id of newly created
	// booking id.
	// we check for "bookingid" string in response.
	@Then("New Booking should get created successfully")
	public void new_Booking_should_get_created_successfully() {
		logger.info("New Booking should get created successfully");
		String actualResponseString = response.asString();
		logger.info(actualResponseString);
		boolean responseStringHasBookingId = actualResponseString.contains("\"bookingid\"");
		Assert.assertTrue(responseStringHasBookingId,
				"New Booking Failed as response string doesn't have new booking id");

	}

/*	// If there are zero bookings in the system. This step will fail and
	// subsequent steps will be skipped
	// If there are bookings, then latestbooking id is picked
	@Given("There is atleast one booking in the system")
	public void there_is_atleast_one_booking_in_the_system() {
		System.out.println("There is atleast one booking in the system");
		getAllBookingsURI = System.getProperty("base_uri");

		response = RestAssured.given().when().get(getAllBookingsURI);
		response.then().assertThat().statusCode(200).assertThat().contentType(ContentType.JSON);
		jsonPath = response.jsonPath();
		latestBookingId = jsonUtils.getLatestBookingId(jsonPath);
		Assert.assertNotEquals(latestBookingId, 0, "No bookings in the system");
	}

	// Construct a delete URI with latest booking id identified in the previous step
	@Given("I have Delete URI")
	public void i_have_Delete_URI() {
		logger.info("I have Delete URI");
		System.out.println("Latest Booking Id for deletion :" + latestBookingId);
		deleteBookingURI = System.getProperty("base_uri");
		deleteBookingURI = deleteBookingURI.concat(latestBookingId.toString());
		logger.info(deleteBookingURI);
	}

	//Do a REST DELETE call to the delete booking URI
	@When("I make a Delete request")
	public void i_make_a_Delete_request() {
		logger.info("I make a Delete request");
		response = RestAssured.given().when().delete("deleteBookingURI");
	}

	@Then("Booking should get deleted successfully")
	public void booking_should_get_deleted_successfully() {
		logger.info("Booking should get deleted successfully");
		logger.info(response.asString());
		logger.info("deleted");
	}*/

}
