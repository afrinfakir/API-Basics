package AuthApi;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AuthTest {
	
	//Allure report
	@BeforeMethod
	public void alluresetup() {
		RestAssured.filters(new AllureRestAssured());
	}
	
	@Test
	public void JWTS_Auth_test() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		String jwttokenval = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"username\": \"mor_2314\",\r\n"
				+ "    \"password\": \"83r5^_\"\r\n"
				+ "}")
		.when()
		.post("/auth/login")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		
		System.out.println(jwttokenval);
		
		String jwtId[] =jwttokenval.split("\\.");
		System.out.println(jwtId[0]);
		System.out.println(jwtId[1]);
		System.out.println(jwtId[2]); 
	}
	
	@Test
	public void Basic_Auth_test() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		 String ResBody = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.auth().basic("tomsmith","SuperSecretPassword!")
		.when().log().all()
		.get("/login")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.body().asString();
		 
		 System.out.println(ResBody);
	}
	
	@Test
	public void Preemptive_Basic_Auth_test() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		 String ResBody = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.auth().preemptive().basic("tomsmith","SuperSecretPassword!")
		.when().log().all()
		.get("/login")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.body().asString();
		 
		 System.out.println(ResBody);
	}
	
	@Test
	public void digest__Auth_test() {
		
		RestAssured.baseURI = "https://the-internet.herokuapp.com";
		
		 String ResBody = RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.auth().digest("tomsmith","SuperSecretPassword!")
		.when().log().all()
		.get("/login")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.extract()
		.body().asString();
		 
		 System.out.println(ResBody);
	}

	@Test
	public void ApiKey_Auth_test() {
		
		RestAssured.baseURI = "http://api.weatherapi.com";
		
		 Response resbody = RestAssured.given().log().all()
				 .queryParam("key", "32be640b0d39462bb2d42132230107")
				 .queryParam("q", "London")
				 .queryParam("aqi", "no")
		.when().log().all()
		.get("/v1/current.json");
		 
		 resbody.prettyPrint();
		 System.out.println(resbody.statusCode());
		 
	}


}
