package pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class CheckoutPage extends BasePage {

    private By checkoutTitle = By.xpath("//span[text()='Checkout: Your Information']");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCheckoutPage() {
        String actualURL = driver.getCurrentUrl();
        waitForVisible(checkoutTitle).isDisplayed();
        assertEquals(TestConfig.CHECKOUT_URL, actualURL);
    }

    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        waitForVisible(firstNameField).sendKeys(firstName);
        waitForVisible(lastNameField).sendKeys(lastName);
        waitForVisible(postalCodeField).sendKeys(postalCode);
        waitForVisible(continueButton).click();
    }
}
