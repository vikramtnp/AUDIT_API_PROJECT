package api.test;

import api.utilities.ConfigManager;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import java.util.Map;

public class Sync extends BaseTest{
	
	  @Test(dependsOnMethods = {"api.test.LoginTest.loginTest"})
	public void sync() {
    
    String SyncEndpoint = ConfigManager.getProperty("sync_endpoint");

    Response response = given()
    		 .header("Authorization", "Bearer " + LoginTest.accessToken)
            .header("Content-Type", "application/json")
            .when()
            .get(SyncEndpoint)
            .then()
            .extract()
            .response();

    assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

    Map<String, Object> responseBody = response.jsonPath().getMap("");

    System.out.println("Sync Response Body: " + responseBody);

    // Validate the sync response
//    assertNotNull(responseBody.get("sync_datetime"), "Sync datetime should not be null");
//    assertNotNull(responseBody.get("masters"), "Masters should not be null");
}
}