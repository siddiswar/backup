Feature: User should be able to View , Create and Delete Bookings using API calls 


@ApiTests
Scenario: Get all bookings using GET call 
	Given I have Get URI 
	When I make a Get call 
	Then Response should have 200 status code
	And Respose should be of Json type  
	And I should get all bookings present in the system 
	
	
@ApiTests
Scenario: Create a booking using POST call
	Given I have POST URI 
	And I have booking data in the jsonfile "conf/testdata/newbooking.json" 
	When I make a post request with the json file 
	Then Response should have 200 status code
	And Respose should be of Json type  
	And New Booking should get created successfully 
	

	