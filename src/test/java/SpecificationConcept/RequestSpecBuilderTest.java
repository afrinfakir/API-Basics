package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	
	public static RequestSpecification get_req_spec() {
		RequestSpecification requsetSpec= new	RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization","4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
				.build();
		return requsetSpec;
				
	}

	
	@Test
	public void get_user_with_Request_spec() {
		
			
			RestAssured.given().log().all()
			.spec(get_req_spec())
			.get("/public/v2/users")
			.then()
			.statusCode(200);
	}
	@Test
	public void get_user_with_query_param_Request_spec() {
		
		RestAssured.given().log().all()
		.queryParam("name", "Swara")
		.queryParam("status", "active")
		.spec(get_req_spec())
		.get("/public/v2/users")
		.then()
		.statusCode(200);
	}
}
