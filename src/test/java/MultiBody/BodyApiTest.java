package MultiBody;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BodyApiTest {
		
	@Test
	public void text_Body_Test() {
		RestAssured.baseURI = "http://httpbin.org";
		
		 String payload = "this is api testing";
		 
		Response response = RestAssured.given()
		.contentType(ContentType.TEXT)
		.body(payload)
		.when()
		.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test
	public void javascript_Body_Test() {
		RestAssured.baseURI = "http://httpbin.org";
		
		 String payload = "function add(){\r\n"
		 		+ "    var a =10;\r\n"
		 		+ "    var b  = 20;\r\n"
		 		+ "    console.log(a+b);\r\n"
		 		+ "}";
		 
		Response response = RestAssured.given()
		.header("Content-Type","application/javascript")
		.body(payload)
		.when()
		.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test
	public void HTML_Body_Test() {
		RestAssured.baseURI = "http://httpbin.org";
		
		 String payload = "<p>MDN Web Docs is a learning platform for Web technologies and the software that powers the Web.</p>\r\n"
		 		+ "\r\n"
		 		+ "<hr />\r\n"
		 		+ "\r\n"
		 		+ "<p><small>The content is licensed under a Creative Commons Attribution-ShareAlike 2.5 Generic License.</small></p>";
		 
		Response response = RestAssured.given()
				.contentType(ContentType.HTML)
		.body(payload)
		.when()
		.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test
	public void XML_Body_Test() {
		RestAssured.baseURI = "http://httpbin.org";
		
		 String payload = "<note>\r\n"
		 		+ "<to>Tove</to>\r\n"
		 		+ "<from>Jani</from>\r\n"
		 		+ "<heading>Reminder</heading>\r\n"
		 		+ "<body>Don't forget me this weekend!</body>\r\n"
		 		+ "</note>";
		 
		Response response = RestAssured.given()
		.contentType(ContentType.XML)
		.body(payload)
		.when()
		.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test
	public void formData_Body_Test() {
		RestAssured.baseURI = "http://httpbin.org";
		
		 		 
		Response response = RestAssured.given().log().all()
		.contentType(ContentType.MULTIPART)
		.multiPart("name","Afrin")
		.multiPart("filename",new File("/Users/afrin/Downloads/PDFGallery-20240220-184408.pdf"))
		.when().log().all()
		.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}
	
	@Test
	public void Binary_Body_Test() {
		RestAssured.baseURI = "http://httpbin.org";
		
		 		 
		Response response = RestAssured.given().log().all()
		.header("Content-Type","application/pdf")
		.body(new File("/Users/afrin/Downloads/PDFGallery-20240220-184408.pdf"))
		.when().log().all()
		.post("/post");
		response.prettyPrint();
		System.out.println(response.statusCode());
	}

}
