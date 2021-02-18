package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import POJO.AddLocation;
import POJO.Addplace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild testDataBuild = new TestDataBuild();
	JsonPath js;
	static String place_id;
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	 
		res= given().spec(requestSpecification())
				.body(testDataBuild.addPlacePayLoad(name, language, address));
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String method) {
		
		APIResources resourceAPI= APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());
		
		resspec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		if(method.equalsIgnoreCase("post")) {
		
		response= res.when().post(resourceAPI.getResource());
		
		}else if(method.equalsIgnoreCase("GET"))
			response= res.when().post(resourceAPI.getResource());	
		
	}

	@Then("the API call is success with status code {int}")
	public void the_API_call_is_success_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	    assertEquals(response.getStatusCode(),200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
	    // Write code here that turns the phrase above into concrete actions
	  
	   assertEquals(getJsonPath(response,keyValue),Expectedvalue);
	   
	}
	
	@Then("verify place_id create maps to {string} using {string}")
	public void verify_place_id_create_maps_to_using(String expectedname, String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  // request Spec
		place_id=getJsonPath(response,"place_id");
		res = given().spec(requestSpecification())
				.queryParam("place_id",place_id );
		user_calls_with_post_http_request(resource,"GET");
		String actualname=getJsonPath(response,"name");
		assertEquals(actualname, expectedname);
	}

	@Given("DeletPlaceAPI payload")
	public void deletplaceapi_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
	  res= given().spec(requestSpecification()).body(testDataBuild.DeletPlaceAPI(place_id));
	  
	}
	
}
