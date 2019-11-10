Feature: Booking Creation and Deletion 
	User should be allowed to create a booking from web page with valid set of input data
	User should be allowed to delete any particular booking from web page

@WebTests
Scenario Outline: 
	User should we allowed to create a new booking with valid set of data in all fields 
	Given User is on booking page 
	When User enters "<firstname>" in firstname field 
	And User enters "<surname>" in surname field 
	And User enters "<price>" in Price field 
	And User selects "<depositpaid>" in deposit field 
	And User enters "<fromdate>" in checkin field 
	And User enters "<todate>" in checkout field 
	And User clicks save button 
	Then Booking should get created 
	Examples: 
		|firstname|surname|price|depositpaid|fromdate|todate|
		|fcname22|scname2|12.5|false|2019-10-28|2019-10-29|
		
@WebTests		
Scenario Outline: 
	Test booking creation functionality with different input combinations 
	Given booking data is presnt in the row <RowNumber> 
	And User is on booking page 
	When User enters data into fields 
	And User clicks save button 
	Then Booking creation status should be as expected 
	Examples: 
		|RowNumber|
		|1|
		|2|
		|3|
		|4|
		|5|
		|6|
		|7|
		|8|
		
@WebTests		
Scenario: 
	To test deletion of a booking present at the top of the table 
	Given User is on booking page 
	And There is atleast one booking in bookings page 
	When User clicks delete button of the booking present at the top 
	Then Booking should get deleated