package postAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.io.File;

public class BookingAuthTest {
	
	@Test
	public void Booking_auth_token_with_JSON_file_test() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resaurce/data/BasicAuth.json"))
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		System.out.println(tokenId);
		
	}
	@Test
	public void Booking_auth_token_with_JSON_body_test() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		String tokenId = given()
		.contentType(ContentType.JSON)
		.body("{\n"
				+ "    \"username\" : \"admin\",\n"
				+ "    \"password\" : \"password123\"\n"
				+ "}")
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		System.out.println(tokenId);
		
	}
	
	@Test
	public void Add_user_test() {
		
		//1-Add user
		RestAssured.baseURI = "https://gorest.co.in";
		int userId = given()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resaurce/data/user_goRest.json"))
		.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when()
		.post("/public/v2/users")
		.then()
		.assertThat()
		.statusCode(201)
		.and()
		.body("name",equalTo("Afrin_CRUD"))
		.extract()
		.path("id");
		
		System.out.println(userId);
		
		//2-get same user
		given().log().all()
		.header("Authorization","Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when().log().all()
		.get("/public/v2/users/"+userId)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(userId));
	}
}
