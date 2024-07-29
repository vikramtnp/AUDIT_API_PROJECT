package api.test;

import api.utilities.ConfigManager;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class LogoutTest extends BaseTest {

    @Test(dependsOnMethods = {"api.test.LoginTest.loginTest"}, priority =3)
    public void logoutTest() {
        String logoutEndpoint = ConfigManager.getProperty("logout_endpoint");

        Response response = given()
                .header("accept", "application/json")
                .header("Authorization", "Bearer " + LoginTest.accessToken)
                .when()
                .post(logoutEndpoint)
                .then()
                .extract()
                .response();

        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        String expectedMessage = "Logout Successful";
        String actualMessage = response.jsonPath().getString("message");
        assertEquals(actualMessage, expectedMessage, "Expected message does not match the actual message");
    }
}
