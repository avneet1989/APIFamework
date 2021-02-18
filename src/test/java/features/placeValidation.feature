Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI

	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "post" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id create maps to "<name>" using "getPlaceAPI"
	
	
Examples:
	|name    |language |address           |
	|AAhouse |English  |World cross center|
#	|BBhouse |Spanis   |Sea cross center  |	
	
	




@DeletePlace
Scenario: verify if delete place functionality is working using deletePlaceAPI

	Given DeletPlaceAPI payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
		
	
	
	
	
	