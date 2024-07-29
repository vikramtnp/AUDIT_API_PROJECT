package api.test;

import api.payload.LoginRequest;
import api.payload.LoginResponse;
import api.utilities.ConfigManager;
import api.utilities.ExcelUtils;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {
	
	 public static String accessToken;

    @Test(dataProvider = "loginData")
    public void loginTest(String username, String password, String expectedResult) {
//        // Log request parameters
//        System.out.println("Request Parameters:");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
//        System.out.println("expectedResult: " + expectedResult);

        String loginEndpoint = ConfigManager.getProperty("login_endpoint");

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setGrant_type("");
        loginRequest.setUsername(username);
        loginRequest.setPassword(password);
        loginRequest.setScope("");
        loginRequest.setClient_id("");
        loginRequest.setClient_secret("");

        Response response = given()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .formParam("grant_type", loginRequest.getGrant_type())
                .formParam("username", loginRequest.getUsername())
                .formParam("password", loginRequest.getPassword())
                .formParam("scope", loginRequest.getScope())
                .formParam("client_id", loginRequest.getClient_id())
                .formParam("client_secret", loginRequest.getClient_secret())
                .when()
                .post(loginEndpoint)
                .then()
                .extract()
                .response();

        // Print the response details for debugging
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        // Check if the status code is 200, if not, fail the test with a message
        assertEquals(response.getStatusCode(), 200, "Expected status code 200 but got " + response.getStatusCode());

        // Deserialize the response
        LoginResponse loginResponse = response.as(LoginResponse.class);

        // Print the deserialized response for debugging
        System.out.println("Deserialized Response: " + loginResponse);

        // Check if the access_token field is present and not null
        assertNotNull(loginResponse.getAccessToken(), "Access token should not be null");

        // Additional assertion for expected result
        if ("Success".equals(expectedResult)) {
            // If we expect success, check if we received a valid token
            assertTrue(loginResponse.getAccessToken().startsWith("ey"), "Expected a valid JWT token");
        } else {
            // Otherwise, check the exact match
            assertEquals(loginResponse.getAccessToken(), expectedResult, "Expected result does not match the access token");
        }  accessToken = loginResponse.getAccessToken();
    }
       

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        return ExcelUtils.getTableArray("testdata/LoginTestData.xlsx", "Sheet1");
    }
}
