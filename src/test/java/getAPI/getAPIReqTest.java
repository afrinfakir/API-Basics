package getAPI;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class getAPIReqTest {

	RequestSpecification request;

	//NON BDD APPROACH
	
	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d");
	}

	@Test
	public void getUserAPITest() {
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given();
//		request.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d");
		Response response = request.get("/public/v2/users");

		// Status code
		int status_code = response.statusCode();
		System.out.println("Status code : " + status_code);

		// verification point
		Assert.assertEquals(status_code, 200);

		// status msg
		String status_msg = response.statusLine();
		System.out.println("status message : " + status_msg);

		// fetch the response body
		response.prettyPrint();

		// fetch header
		String Header = response.header("Content-type");
		System.out.println("Header value in content-type  :" + Header);

		// fetch all headers
		List<Header> headersList = response.headers().asList();
		System.out.println("Total size of headers : " + headersList.size());

		// printing all headers value
		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

	}

	@Test
	public void getUserAPIwithQueryParameterTest() {

//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given();
//		request.header("Authorization", "4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d");

		request.queryParam("name", "swara");
		request.queryParam("status", "active");
		Response response = request.get("/public/v2/users");

		int statuscode = response.statusCode();
		System.out.println("status code : " + statuscode);

		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());
		for (Header h : headersList) {
			System.out.println(h.getName() + ":" + h.getValue());
		}

		response.prettyPrint();

	}

	@Test
	public void getUserAPIwithQueryparams_withHashmap_Test() {
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given();
//		request.header("Authorization","4088dc553a270b2e390949f6601e99734536accaf0856dc614fba5dea0e92a8d");

		Map<String, String> queryparamsMap = new HashMap<String, String>();
		queryparamsMap.put("name", "Swara");
		queryparamsMap.put("status", "active");
		request.queryParams(queryparamsMap);

		Response response = request.get("/public/v2/users");

		int status_code = response.statusCode();
		System.out.println("statuscode with hashmap method " + status_code);

		response.prettyPrint();

	}

}
