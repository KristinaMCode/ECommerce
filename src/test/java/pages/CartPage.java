package pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class CartPage extends BasePage {

    private By cartTitle = By.xpath("//span[text()='Your Cart']");
    private By inventoryItemName = By.className("inventory_item_name");
    private By removeButton = By.xpath("//button[text()='Remove']");
    private By cartItems = By.className("shopping_cart_badge");
    private By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void verifyCartPage() {
        assertTrue("Cart page title should be visible", waitForVisible(cartTitle).isDisplayed());
    }

    public void verifyItemInCart(String itemName) {
        String actualItem = waitForVisible(inventoryItemName).getText();
        assertEquals(itemName, actualItem);
    }

    public void clickRemoveButton() {
        waitForVisible(removeButton).click();
    }

    public void isCartEmpty() {
        assertTrue("Cart badge should not be visible", isElementAbsent(cartItems));
        assertTrue("Cart item should not be visible", isElementAbsent(inventoryItemName));
    }

    public void clickCheckoutButton() {
        waitForVisible(checkoutButton).click();
    }
}
