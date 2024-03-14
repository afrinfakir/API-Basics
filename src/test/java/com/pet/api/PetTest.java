package com.pet.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.api.PetLombok.Category;
import com.pet.api.PetLombok.Tag;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetTest {
	
	@Test
	public void createPetTest() {
		RestAssured.baseURI = "https://petstore.swagger.io/";
		
		List<String> photourls = new ArrayList<String>();
		photourls.add("https://www.dog.com");
		photourls.add("https://www.dog2.com");
		
		Tag tag = new Tag(45,"Blue");
		Tag tag1 = new Tag(55,"orange");
		List<Tag> tags = new ArrayList<Tag>();
		tags.add(tag);
		tags.add(tag1);
		
		Category category = new Category(10,"Dog");
		PetLombok pet = new PetLombok(300, category, "Tommy", photourls, tags, "Available");
		
		Response response = RestAssured.given()
		.contentType(ContentType.JSON)
		.body(pet)
		.when()
		.post("v2/pet");
		
		System.out.println(response.statusCode());
		
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombok petresponse = mapper.readValue(response.getBody().asString(), PetLombok.class);
			System.out.println(petresponse.getId());
			System.out.println(petresponse.getName());
			System.out.println(petresponse.getStatus());
			System.out.println(petresponse.getCategory().getName());
			System.out.println(petresponse.getCategory().getId());
			System.out.println(petresponse.getPhotoUrls());
			
			Assert.assertEquals(pet.getId(),petresponse.getId() );
			Assert.assertEquals(pet.getName(), petresponse.getName());
			Assert.assertEquals(pet.getStatus(), petresponse.getStatus());
			Assert.assertEquals(pet.getCategory().getName(), petresponse.getCategory().getName());
			Assert.assertEquals(pet.getCategory().getId(), petresponse.getCategory().getId());
			Assert.assertEquals(pet.getPhotoUrls(), petresponse.getPhotoUrls());
			
			
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void createPet_builderPattern_Test() {
		RestAssured.baseURI = "https://petstore.swagger.io/";
		
		Category category = new Category.CategoryBuilder()
										.id(101)
										.name("Animal")
										.build();
		
				Tag tag1 = new Tag.TagBuilder()
									.id(202)
									.name("yellow")
									.build();
				
				Tag tag2 = new Tag.TagBuilder()
						.id(303)
						.name("red")
						.build();
		
		PetLombok pet = new PetLombok.PetLombokBuilder()
									.id(1001)
									.category(category)
									.name("Cat")
									.photoUrls(Arrays.asList("https://www.cat.com","www.cat2.com"))
									.tags(Arrays.asList(tag1,tag2))
									.status("Available")
									.build();
									
		Response response = RestAssured.given()
		.contentType(ContentType.JSON)
		.body(pet)
		.when()
		.post("v2/pet");
		
		System.out.println(response.statusCode());
		
		//De-serialization
		ObjectMapper mapper = new ObjectMapper();
		try {
			PetLombok petresponse = mapper.readValue(response.getBody().asString(), PetLombok.class);
			System.out.println(petresponse.getId());
			System.out.println(petresponse.getName());
			System.out.println(petresponse.getStatus());
			System.out.println(petresponse.getCategory().getName());
			System.out.println(petresponse.getCategory().getId());
			System.out.println(petresponse.getPhotoUrls());
			
			Assert.assertEquals(pet.getId(),petresponse.getId() );
			Assert.assertEquals(pet.getName(), petresponse.getName());
			Assert.assertEquals(pet.getStatus(), petresponse.getStatus());
			Assert.assertEquals(pet.getCategory().getName(), petresponse.getCategory().getName());
			Assert.assertEquals(pet.getCategory().getId(), petresponse.getCategory().getId());
			Assert.assertEquals(pet.getPhotoUrls(), petresponse.getPhotoUrls());
			
			
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
