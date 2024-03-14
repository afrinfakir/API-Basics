package postAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.credentials;

public class BookingAuthwithpojoTest {
	//POJO plane old java object
	//can not extend any other class
	//oop encapsulation--
	//private class vars--json body
	//public getter setter
	//serialization --java object to other format :file media,json/xml/text/pdf
	//pojo to json --serialization
	
	@Test
	public void Booking_auth_token_with_JSON_pojo_test() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		credentials cred = new credentials("admin","password123");
		String tokenId = given()
		.contentType(ContentType.JSON)
		.body(cred)
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		
		System.out.println(tokenId);
		
	}
	
	

}
