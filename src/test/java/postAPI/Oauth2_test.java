package postAPI;

import static org.testng.Assert.assertThrows;

import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;


public class Oauth2_test {
	
	static String Access_token;
	
	@BeforeMethod
	public void getAccessToken() {
		
		//1-get the access token
				RestAssured.baseURI = "https://test.api.amadeus.com";
				
				Access_token = given()
				.header("Content-Type","application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "PtK3AGBQERWV0COAEz32z6TRfFvuE1rj")
				.formParam("client_secret", "eXs1OydKxMCGvrF5")
				.when()
				.post("/v1/security/oauth2/token")
				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.path("access_token");
				
				System.out.println(Access_token);
	}
	
	@Test
	public void get_flight_info_test() {
		
//		//1-get the access token
//		RestAssured.baseURI = "https://test.api.amadeus.com";
//		
//		String Access_token = given()
//		.header("Content-Type","application/x-www-form-urlencoded")
//		.formParam("grant_type", "client_credentials")
//		.formParam("client_id", "PtK3AGBQERWV0COAEz32z6TRfFvuE1rj")
//		.formParam("client_secret", "eXs1OydKxMCGvrF5")
//		.when()
//		.post("/v1/security/oauth2/token")
//		.then()
//		.assertThat()
//		.statusCode(200)
//		.extract()
//		.path("access_token");
//		
//		System.out.println(Access_token);
		
		//2-get the flight info
		
		Response flight_response =given().log().all()
		.header("Authorization","Bearer "+Access_token)
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", 200)
		.when().log().all()
		.get("/v1/shopping/flight-destinations")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.response();
		
		JsonPath js = flight_response.jsonPath();
		String type = js.get("data[0].type");
		System.out.println(type);
		
//		List<String> originList = js.getList("data[].origin");
//		System.out.println("OriginList :"+originList.size());
//		for(int i=0;i<originList.size();i++) {
//			System.out.println(originList.get(i));
//			
//		}
		
		
		
	}
}
