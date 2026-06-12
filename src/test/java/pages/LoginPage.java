package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        waitForVisible(usernameField).sendKeys(username);
        waitForVisible(passwordField).sendKeys(password);
        waitForClickable(loginButton).click();
    }

    public boolean isInventoryPageDisplayed() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public boolean isErrorMessageDisplayed() {
        try {
            return waitForVisible(errorMessage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String getErrorMessage() {
        try {
            return waitForVisible(errorMessage).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }
}
