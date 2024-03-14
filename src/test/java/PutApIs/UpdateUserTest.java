package PutApIs;

import org.testng.annotations.Test;

import com.user.api.User;
import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UpdateUserTest {

	public static String getRandomEmailId() {
		return "APIautomation" + System.currentTimeMillis() + "@gmail.com";
		// return "APIautomation"+UUID.randomUUID()+"gmail.com";
	}

	@Test
	public void update_user_Test() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("afrin", getRandomEmailId(), "female", "active");
			
		//1-create user POST
		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
				.body(user).when().post("/public/v2/users");

		Integer userId = response.jsonPath().get("id");
		System.out.println(userId);
		
		//2-update user PUT
		user.setName("Afrin API Automation");
		user.setStatus("inactive");
		
		RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.body(user)
		.put("/public/v2/users/"+userId)
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("id", equalTo(userId)).and()
		.body("name", equalTo(user.getName())).and()
		.body("status", equalTo(user.getStatus()));
		

	}
}
