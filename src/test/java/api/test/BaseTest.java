package api.test;

import api.utilities.ConfigManager;
import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        // Set the base URI from the properties file
        RestAssured.baseURI = ConfigManager.getProperty("base_url");
    }
}
