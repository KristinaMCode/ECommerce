package pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutCompletePage extends BasePage {

    private By completeTitle = By.xpath("//span[text()='Checkout: Complete!']");
    private By thankYouMessage = By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public void verifyCheckoutCompletePage() {
        String actualURL = driver.getCurrentUrl();
        waitForVisible(completeTitle).isDisplayed();
        waitForVisible(thankYouMessage).isDisplayed();
        assertEquals(TestConfig.CHECKOUT_COMPLETE_URL, actualURL);
    }

}
