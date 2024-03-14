package getAPI;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import java.util.List;

public class getAPIReqwithBDDTest {
	
	@Test
	public void getProductTest() {
		given().log().all()
			.when().log().all()
				.get("https://fakestoreapi.com/products")
					.then().log().all()
						.assertThat()
							.statusCode(200)
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("connection", "keep-alive")
												.and()
													.body("$.size()" , equalTo(20))
														.and()
															.body("id",is(notNullValue()))
																.and()
																	.body("title",hasItem("Mens Cotton Jacket"));
														
	}
	@Test
	public void getUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
				.when().log().all()
					.get("/public/v2/users")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.body("$.size()",equalTo(10));
												
								
	}
	
	@Test
	public void getProductdataAPIwithQuerypara() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
		given()
			.queryParam("limit", 5)
				.when()
					.get("/products")
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON);	
		
	}
	@Test
	public void getProductdAPI_withExtract_body() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
			Response response = given()
									.queryParam("limit", 5)
										.when()
											.get("/products");
			JsonPath js = response.jsonPath();
			int firstproductid = js.getInt("[0].id");
			System.out.println("firstproductid :"+ firstproductid);
			
			String firstproductTtile = js.getString("[0].title");
			System.out.println("firstproductTtile :"+ firstproductTtile);
			
			float firstproductprice= js.getFloat("[0].price");
			System.out.println("firstproductprice :"+ firstproductprice);
			
			int count = js.getInt("[0].rating.count");
			System.out.println("count :" +count);
			
			
	}
	@Test
	public void getProductdAPI_withExtract_body_withArray_test() {
		RestAssured.baseURI = "https://fakestoreapi.com/";
		
			Response response = given()
									.queryParam("limit", 10)
										.when()
											.get("/products");
			
			JsonPath js = response.jsonPath();
			List<Integer> Idlist = js.getList("id");
			List<String> titleList=js.getList("title");
			List<Object> rateList = js.getList("rating.rate");
			List<Integer> countList = js.getList("rating.count");
			
			for(int i=0; i<Idlist.size();i++) {
				int Id = Idlist.get(i);
				String title = titleList.get(i);
				Object rate = rateList.get(i);
				int count = countList.get(i);
				System.out.println("Id :"+ Id + "title:"+ title  +"rate:"+rate+ "count:"+count);
			}
	}
	
	@Test
	public void getUserAPI_withObject() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
							.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
									.when().log().all()
									.get("/public/v2/users/6752822");
		JsonPath js = response.jsonPath();
		int Id = js.getInt("id");
		System.out.println(Id);
		String email = js.getString("email");
		System.out.println(email);
		
	}
	
	@Test
	public void getUserAPI_withObject_with_Extract_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		
//		int userId  = given().log().all()
//							.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
//									.when().log().all()
//									.get("/public/v2/users/6752822")
//									.then()
//									.extract()
//									.path("id");
//		System.out.println(userId);
	
		Response response =given().log().all()
		.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d")
				.when().log().all()
				.get("/public/v2/users/6752822");
		int userid = response.then().extract().path("id");
		
		String email = response.then().extract().path("email");
		System.out.println(userid+"and"+email);
	
	
	
	}

}
