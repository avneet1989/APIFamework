
package resources;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import POJO.AddLocation;
import POJO.Addplace;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestDataBuild {
	RequestSpecification req;
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	
	
	public Addplace addPlacePayLoad(String name, String language, String address) {
		
		Addplace ap= new Addplace();
		
		ap.setAccuracy(50);
		ap.setAddress(address);
		ap.setLanguage(language);
		
		AddLocation l = new AddLocation();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		ap.setLocation(l);
		
		ap.setName(name);
		ap.setPhone_number("(+91) 983 893 3937");
		
		List<String> myList= new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		ap.setTypes(myList);
		ap.setWebsite("https://rahulshettyacademy.com");
		 
		return ap;
		
		
	}
	public String DeletPlaceAPI(String placeid) {
		
		return "{\r\n\"place_id\": \""+placeid+"\"\r\n}";
		
	}

}
