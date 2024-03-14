package com.product.api;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProductAPITest {
	@Test
	public void getProduct_with_POJO_Test() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response =given().log().all()
		.when().log().all()
		.get("/products");
		
		//json to pojo mapping
		ObjectMapper mapper =new ObjectMapper();
		try {
			product product[] = mapper.readValue(response.getBody().asString(), product[].class);
			
			for(product p: product) {
				System.out.println("ID :"+ p.getId());
				System.out.println("Title :"+ p.getTitle());
				System.out.println("price :"+ p.getPrice());
				System.out.println("Description :"+ p.getDescription());
				System.out.println("Category :"+ p.getCategory());
				System.out.println("Image :"+ p.getImage());
				System.out.println("Rate :"+ p.getRating().getRate());
				System.out.println("Count :"+ p.getRating().getCount());
				
				System.out.println("---------");;
			}
			
			
			
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void getProduct_with_Lombok_Test() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response =given().log().all()
		.when().log().all()
		.get("/products");
		
		//json to pojo mapping
		ObjectMapper mapper =new ObjectMapper();
		try {
			ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			
			for(ProductLombok p: product) {
				System.out.println("ID :"+ p.getId());
				System.out.println("Title :"+ p.getTitle());
				System.out.println("price :"+ p.getPrice());
				System.out.println("Description :"+ p.getDescription());
				System.out.println("Category :"+ p.getCategory());
				System.out.println("Image :"+ p.getImage());
				System.out.println("Rate :"+ p.getRating().getRate());
				System.out.println("Count :"+ p.getRating().getCount());
				
				System.out.println("---------");;
			}
			
			
			
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}

	@Test
	public void getProduct_with_Lombok_builder_Test() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		Response response =given().log().all()
		.when().log().all()
		.get("/products");
		
		//json to pojo mapping
		ObjectMapper mapper =new ObjectMapper();
		try {
			ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);
			
			for(ProductLombok p: product) {
				System.out.println("ID :"+ p.getId());
				System.out.println("Title :"+ p.getTitle());
				System.out.println("price :"+ p.getPrice());
				System.out.println("Description :"+ p.getDescription());
				System.out.println("Category :"+ p.getCategory());
				System.out.println("Image :"+ p.getImage());
				System.out.println("Rate :"+ p.getRating().getRate());
				System.out.println("Count :"+ p.getRating().getCount());
				
				System.out.println("---------");;
			}
			
			
			
		} catch (JsonMappingException e) {

			e.printStackTrace();
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}
	}
}
