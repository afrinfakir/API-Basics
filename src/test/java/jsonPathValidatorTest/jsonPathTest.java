package jsonPathValidatorTest;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class jsonPathTest {
		
	
			@Test
			public void getCircuitDataApi_year_test() {
				
				RestAssured.baseURI = "http://ergast.com";
				Response response = given().log().all()
				//.pathParam("year", "2017")
				.when().log().all()
				.get("/api/f1/2017/circuits.json");
				
			String jsonResponse = 	response.asString();
			System.out.println(jsonResponse);
			
			List<String>  CountryList =JsonPath.read(jsonResponse,"$..Circuits..country");
			System.out.println(CountryList);
			
			int circuitlist = JsonPath.read(jsonResponse,"$.MRData.CircuitTable.Circuits.length()");
			System.out.println(circuitlist);
			}
			
			@Test
			public void getUserData_test() {
			Response response =	given().log().all()
				.when().log().all()
					.get("https://fakestoreapi.com/products");
			
			String jsonResponse = response.asString();
			System.out.println(jsonResponse);
			
			List<Float> List_rates = JsonPath.read(jsonResponse,"$[?(@.rating.rate < 3)].rating.rate");
			System.out.println(List_rates);
			 
			//fetch title and price where category is jwelery (2 attribute fetch)
			//$[?(@.category=='jewelery')].[title,price]
			// 2attributes
			List<Map<String,Object>> jewelary_list =JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\"]");
			System.out.println(jewelary_list.size());
			System.out.println(jewelary_list);;
			
			for(Map<String,Object> product:jewelary_list) {
				String title = (String)product.get("title");
				Object price = (Object)product.get("price");
				System.out.println("Title : "+title+   "  and   "+   "price :"+price);
			}
			
			//3Attributes
			List<Map<String,Object>> jewelary_list2 =JsonPath.read(jsonResponse, "$[?(@.category=='jewelery')].[\"title\",\"price\",\"id\"]");
			System.out.println(jewelary_list.size());
			System.out.println(jewelary_list);;
			
			for(Map<String,Object> product:jewelary_list) {
				String title = (String)product.get("title");
				Object price = (Object)product.get("price");
				Integer id = (Integer)product.get("id");
				
				System.out.println("Title : "+title+   "  and   "+   "price :"+price+"   and  "+id);
			}
			
			
			
			
			
			
			}
}
