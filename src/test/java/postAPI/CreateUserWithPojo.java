package postAPI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.util.UUID;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import pojo.userPojo;

public class CreateUserWithPojo {
	
	public String getRandomEmailId() {
		return "APIautomation"+System.currentTimeMillis()+"@gmail.com";
		//return "APIautomation"+UUID.randomUUID()+"gmail.com";
	}
	
	@Test
	public void Add_user_test() {
		
		//1-Add user
		RestAssured.baseURI = "https://gorest.co.in";
		
		userPojo user = new userPojo("Afrin",getRandomEmailId(), "female", "active");
		
		int userId = given()
		.contentType(ContentType.JSON)
		.body(user)
		.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when()
		.post("/public/v2/users")
		.then()
		.assertThat()
		.statusCode(201)
		.and()
		.body("name",equalTo(user.getName()))
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
		.body("id", equalTo(userId))
		.body("name",equalTo(user.getName()))
		.body("email",equalTo(user.getEmail()))
		.body("status",equalTo(user.getStatus()));
	}

}
