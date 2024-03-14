package SchemaValidation;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


public class SchemaValidation_test {
	
	@Test
	public void CreateUserAPIschemaValidation() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		 given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resaurce/data/user_goRest.json"))
		.header("Authorization", "Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.statusCode(201);
		//.body(matchesJsonSchemaInClasspath("schemavalidate.json"));
		
	}
}
