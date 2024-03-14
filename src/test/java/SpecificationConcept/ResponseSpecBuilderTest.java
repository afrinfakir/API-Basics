package SpecificationConcept;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecBuilderTest {
	
	public static ResponseSpecification get_response_spec() {
		ResponseSpecification response_spec = new ResponseSpecBuilder()
		.expectHeader("Server", "cloudflare")
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build();
		return response_spec;
	}
	
	public static ResponseSpecification get_response_Auth_Fail_spec() {
		ResponseSpecification response_spec_fail = new ResponseSpecBuilder()
		.expectHeader("Server", "cloudflare")
		.expectStatusCode(401)
		.build();
		return response_spec_fail;
	}

	@Test
	public void get_user_res_200_test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(get_response_spec());
	}
	
	@Test
	public void get_user_res_401_fail_test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
		.header("Authorization","4088dc553a270b2e390949f6601efff99734536accaf0856dc614fba5dea0e92a8daaaaaaaaaaaaa")
		.when().log().all()
		.get("/public/v2/users")
		.then().log().all()
		.assertThat()
		.spec(get_response_Auth_Fail_spec());
	}
	

}
