package XmlAPis;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class getCircuitXmlApiTest {
			
	@Test
	public void xml_test() {
	RestAssured.baseURI = "http://ergast.com";
	
	Response response= RestAssured.given().log().all()
	.when()
	.get("/api/f1/2017/circuits.xml")
	.then()
	.extract()
	.response();
	
	String responseBody = response.body().asString();
	
	//create object of xmlpath
	XmlPath xmlpath = new XmlPath(responseBody);
	List<String> circuitList = xmlpath.getList("MRData.CircuitTable.Circuit.CircuitName");
	for(String c:circuitList) {
		System.out.println(c);
	}
	System.out.println("------------");
	
	List<String> circuitIdList = xmlpath.getList("MRData.CircuitTable.Circuit.@circuitId");
	for(String cId:circuitIdList) {
		System.out.println(cId);
	}
	
	System.out.println("------------");
	
	String locality = xmlpath.get("**.findAll {it.@circuitId=='americas'}.Location.Locality").toString();
	System.out.println(locality);

	System.out.println("------------");
	
	String latVal = xmlpath.get("**.findAll {it.@circuitId=='americas'}.Location.@lat").toString();
	String lotVal = xmlpath.get("**.findAll {it.@circuitId=='americas'}.Location.@long").toString();
	System.out.println(latVal+"-----"+lotVal);
	
	System.out.println("------------");
	 
	String country = xmlpath.get("**.findAll {it.@circuitId=='americas' || it.@circuitId=='bahrain' }.Location.Locality").toString();
	System.out.println(country);
	
	System.out.println("------------");
	
	String city = xmlpath.get("**.findAll {it.@circuitId=='americas'}.CircuitName").toString();
	System.out.println(city);

	System.out.println("------------");
	
System.out.println("------------");
	
	String url = xmlpath.get("**.findAll {it.@circuitId=='americas'}.@url").toString();
	System.out.println(url);

	System.out.println("------------");
	
	
	
	}
}
