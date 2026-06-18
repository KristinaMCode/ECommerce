package pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckoutOverviewPage extends BasePage {

    private By overviewTitle = By.xpath("//span[text()='Checkout: Overview']");
    private By finishButton = By.id("finish");
    private By inventoryItem = By.className("inventory_item_name");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCheckoutOverviewPage() {
        String actualURL = driver.getCurrentUrl();
        waitForVisible(overviewTitle).isDisplayed();
        assertEquals(TestConfig.CHECKOUT_OVERVIEW_URL, actualURL);
    }

    public void verifyFirstProduct(String itemName) {
        String actualFirstProduct = waitForVisible(inventoryItem).getText();
        assertEquals("Expected first product to be " + itemName + " but found " + actualFirstProduct,
                itemName, actualFirstProduct);

    }

    public void completeCheckout() {
        waitForVisible(finishButton).click();
    }

    public void verifyItemsInCart(List<String> expectedItems) {
        verifyItemsPresent(expectedItems, getCartItemNames());
    }

    public List<String> getCartItemNames() {
        return getElementTexts(inventoryItem);
    }

}
