package api.steps;

import api.models.CreatePostRequest;
import api.models.CreatePostResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserApiSteps {

    private static final Logger log = LoggerFactory.getLogger(UserApiSteps.class);
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private Response response;

    private RequestSpecification baseRequest() {
        return given().baseUri(BASE_URL).header("Content-Type", "application/json");
    }

    @When("I send GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = baseRequest().when().get(endpoint);
        log.info("GET {} -> status {}", endpoint, response.getStatusCode());

    }

    @Then("Response status code should be {int}")
    public void verifyResponseStatusCode(int expectedStatus) {
        assertEquals("Unexpected status code", expectedStatus, response.getStatusCode());
    }

    @And("Response should contain a list of users")
    public void verifyUserList() {
        int userCount = response.jsonPath().getList("").size();
        assertTrue("Expected at least one user in the list", userCount > 0);
    }

    @And("Response body should match single user schema")
    public void verifySchema() {
        response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/single_user_schema.json"));
    }

    @And("Response user email should be {string}")
    public void verifyUserEmail(String expectedEmail) {
        String actualEmail = response.jsonPath().getString("email");
        assertEquals("User email missmatch", expectedEmail, actualEmail);
    }

    @When("I send POST request to {string} with title {string} and body {string} and userId {int}")
    public void sendPostRequest(String endpoint, String title, String body, int userId) {
        CreatePostRequest requestBody = new CreatePostRequest(title, body, userId);
        response = baseRequest().body(requestBody).when().post(endpoint);
        log.info("POST {} -> status {}", endpoint, response.getStatusCode());
    }

    @And("Response should contain title {string} and body {string}")
    public void verifyTitleAndBody(String expectedTitle, String expectedBody) {
        CreatePostResponse responseBody = response.as(CreatePostResponse.class);
        assertEquals("Title mismatch", expectedTitle, responseBody.title);
        assertEquals("Body mismatch", expectedBody, responseBody.body);
    }

    @When("I send PUT request to {string} with title {string} and body {string} and userId {int}")
    public void sendPutRequest(String endpoint, String title, String body, int userId) {
        CreatePostRequest requestBody = new CreatePostRequest(title, body, userId);
        response = baseRequest().body(requestBody).when().put(endpoint);
        log.info("PUT {} -> status {}", endpoint, response.getStatusCode());
    }

    @When("I send DELETE request to {string}")
    public void sendDeleteRequest(String endpoint) {
        response = baseRequest().when().delete(endpoint);
        log.info("DELETE {} -> status {}", endpoint, response.getStatusCode());
    }


}
