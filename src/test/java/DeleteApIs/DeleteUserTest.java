package DeleteApIs;

import org.testng.annotations.Test;

import com.user.api.User;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteUserTest {

	public static String getRandomEmailId() {
		return "APIautomation" + System.currentTimeMillis() + "@gmail.com";
		// return "APIautomation"+UUID.randomUUID()+"gmail.com";
	}

	@Test
	public void delete_user_test() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("afrin", getRandomEmailId(), "female", "active");

		// 1-create user POST
		Response response = RestAssured.given().log().all()
									   .contentType(ContentType.JSON)
									   .header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
									   .body(user)
									   .when().log().all()
									   .post("/public/v2/users");

		Integer userId = response.jsonPath().get("id");
		System.out.println(userId);
	
	
	//2-delete user
	 RestAssured.given().log().all()
	 			.contentType(ContentType.JSON)
	 			.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
	 			.when().log().all()
	 			.delete("/public/v2/users/"+userId)
	 			.then().log().all()
	 			.assertThat()
	 			.statusCode(204);
	 
	 //3-get call for checking user deleted or not
	 RestAssured.given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when().log().all()
		.get("/public/v2/users/"+userId)
		.then().log().all()
		.assertThat()
		.statusCode(404)
		.body("message", equalTo("Resource not found"));
	
}
}
