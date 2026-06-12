package steps;

import com.opencsv.exceptions.CsvValidationException;
import config.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import pages.LoginPage;
import config.TestConfig;
import com.opencsv.CSVReaderHeaderAware;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoginSteps {

    private WebDriver driver;

    public LoginSteps(TestContext ctx) {
        this.driver = ctx.getDriver();
    }

    private final List<String> failedUsers = new ArrayList<>();


    @Given("I open the Saucedemo home page")
    public void openHomePage() {
        driver.get(TestConfig.BASE_URL);
        System.out.println("=== openHomePage: navigated to saucedemo ===");
    }

    @Then("The URL should be {string}")
    public void verifyURL(String expectedURL) {
        String actualURL = driver.getCurrentUrl();
        assertEquals(expectedURL, actualURL);
        System.out.println("=== verifyURL: URL is correct ===");
    }

    @Then("The page title should contain {string}")
    public void verifyPageTitle(String expectedTitle) {
        assertTrue(driver.getTitle().contains(expectedTitle));
        System.out.println("=== verifyPageTitle: Page title verified ===");
    }

    @When("User logs in")
    public void userLogsIn() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(TestConfig.STANDARD_USER, TestConfig.STANDARD_PASSWORD);
        System.out.println("=== loginUser: login logic executed ===");
    }

    @When("I login with username {string} and password {string}")
    public void loginWithCredentials(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        System.out.println("=== loginWithCredentials: Attempted login with username='" + username + "' and password='" + password + "' ===");
    }

    @Then("User is logged in successfully")
    public void verifyLoginSuccess() {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Expected to be on inventory page", loginPage.isInventoryPageDisplayed());
        System.out.println("=== verifyLoginSuccess: Login successful ===");
    }

    @Then("User is not logged in unsuccessfully")
    public void verifyLoginFailure() {
        LoginPage loginPage = new LoginPage(driver);
        assertFalse("Expected to remain on login page", loginPage.isInventoryPageDisplayed());
        System.out.println("=== verifyLoginFailure: Login failed as expected ===");
    }

    @Then("Error message should be displayed")
    public void verifyErrorMessageDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        assertTrue("Expected error message to be displayed", loginPage.isErrorMessageDisplayed());
        System.out.println("=== verifyErrorMessageDisplayed: Error message is visible: " + loginPage.getErrorMessage() + " ===");
    }

    @When("I login with CSV file {string}")
    public void loginWithCsvFile(String fileName) throws IOException, CsvValidationException {
        failedUsers.clear();
        String resource = "data/" + fileName;
        InputStream is = getClass().getClassLoader().getResourceAsStream(resource);
        if (is == null) is = getClass().getResourceAsStream("/" + resource);
        if (is == null) throw new FileNotFoundException("Classpath resource not found: " + resource);

        try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
             CSVReaderHeaderAware csv = new CSVReaderHeaderAware(isr)) {
            Map<String, String> row;
            while ((row = csv.readMap()) != null) {
                String username = row.getOrDefault("username", "").trim();
                String password = row.getOrDefault("password", "").trim();
                String expect = row.getOrDefault("expect", "success").trim().toLowerCase();
                // fresh start
                driver.manage().deleteAllCookies();
                driver.get(TestConfig.BASE_URL);
                LoginPage loginPage = new LoginPage(driver);
                loginPage.login(username, password);

                try {
                    new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> loginPage.isInventoryPageDisplayed() || loginPage.isErrorMessageDisplayed());
                } catch (TimeoutException ignored) {
                }
                boolean actualSuccess = loginPage.isInventoryPageDisplayed();
                boolean expectedSuccess = "success".equals(expect);
                if (actualSuccess != expectedSuccess) {
                    String reason = loginPage.getErrorMessage();
                    if (reason == null || reason.isEmpty()) reason = "no error; url=" + driver.getCurrentUrl();
                    failedUsers.add((username.isEmpty() ? "(empty username)" : username) + " -> expected=" + expect + " actual=" + (actualSuccess ? "success" : "fail") + " (" + reason + ")");
                }
                // if logged in, return to login for next row
                if (actualSuccess) driver.get(TestConfig.BASE_URL);
            }
        }
    }


    @Then("All users should match expected outcome")
    public void allUsersShouldMatchExpectedOutcome() {
        if (!failedUsers.isEmpty()) {
            fail("These rows failed: " + String.join(", ", failedUsers));
        }
        System.out.println("=== verifyExpectedOutcome: Login status as expected ===");

    }

    @Then("All users should be logged in successfully")
    public void allUsersShouldBeLoggedInSuccessfully() {

    }

}
