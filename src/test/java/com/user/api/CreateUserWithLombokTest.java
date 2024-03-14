package com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserWithLombokTest {
	
	public static String getRandomEmailId() {
		return "APIautomation"+System.currentTimeMillis()+"@gmail.com";
		//return "APIautomation"+UUID.randomUUID()+"gmail.com";
	}
	
	@Test
	public void createUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User("afrin", getRandomEmailId(), "female", "active");
		
		Response response = RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.body(user)
		.when()
		.post("/public/v2/users");
		
		int userId = response.jsonPath().get("id");
		System.out.println(userId);
		
		//2-get the same user and verify it : GET
		Response getresponse =RestAssured.given()
		.header("Authorization","Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when().log().all()
		.get("/public/v2/users/"+userId);
		
		//de-serialization
		ObjectMapper mapper = new ObjectMapper();
		try {
			User Useresponse = mapper.readValue(getresponse.getBody().asString(), User.class);
			System.out.println(Useresponse.getId()+"----");
			System.out.println(Useresponse.getName()+"----");
			System.out.println(Useresponse.getEmail()+"----");
			System.out.println(Useresponse.getGender()+"----");
			
			Assert.assertEquals(user.getName(), Useresponse.getName());
			Assert.assertEquals(user.getGender(), Useresponse.getGender());
			Assert.assertEquals(user.getStatus(), Useresponse.getStatus());
			Assert.assertEquals(userId, Useresponse.getId());
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createUser_with_Builder_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		User user = new User.UserBuilder()
				.name("Afrin")
				.email(getRandomEmailId())
				.status("active")
				.gender("female")
				.build();
		
		Response response = RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization","Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.body(user)
		.when()
		.post("/public/v2/users");
		
		int userId = response.jsonPath().get("id");
		System.out.println(userId);
		
		//2-get the same user and verify it : GET
		Response getresponse =RestAssured.given()
		.header("Authorization","Bearer 4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
		.when().log().all()
		.get("/public/v2/users/"+userId);
		
		//de-serialization
		ObjectMapper mapper = new ObjectMapper();
		try {
			User Useresponse = mapper.readValue(getresponse.getBody().asString(), User.class);
			System.out.println(Useresponse.getId()+"----");
			System.out.println(Useresponse.getName()+"----");
			System.out.println(Useresponse.getEmail()+"----");
			System.out.println(Useresponse.getGender()+"----");
			
			Assert.assertEquals(user.getName(), Useresponse.getName());
			Assert.assertEquals(user.getGender(), Useresponse.getGender());
			Assert.assertEquals(user.getStatus(), Useresponse.getStatus());
			Assert.assertEquals(userId, Useresponse.getId());
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
