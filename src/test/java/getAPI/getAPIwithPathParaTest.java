package getAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;
public class getAPIwithPathParaTest {
	
	@Test
	public void getergastAPICircuits_withpathpara_test() {
		RestAssured.baseURI = "http://ergast.com";
		given().log().all()
		.pathParam("year", "2017")
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20))
		.and()
		.body("MRData.CircuitTable.season", equalTo("2017"));
	}
	@DataProvider
	public Object[][] getCircuityearData() {
		return new Object[][] {
			{"2016", 21},
			{"2017", 20},
			{"1966", 9},
			{"2023", 22}
		};
	}
	@Test(dataProvider="getCircuityearData")
	public void getAPICircuits_withDataprovider_test(String seasonyear,int count) {
		
		RestAssured.baseURI = "http://ergast.com";
		given().log().all()
		.pathParam("year",seasonyear)
		.when().log().all()
		.get("/api/f1/{year}/circuits.json")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.body("MRData.CircuitTable.Circuits.circuitId",hasSize(count))
		.and()
		.body("MRData.CircuitTable.season", equalTo(seasonyear));
	}

}
