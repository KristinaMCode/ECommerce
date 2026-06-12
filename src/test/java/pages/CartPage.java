package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class CartPage  extends BasePage {

    private By cartTitle = By.xpath("//span[text()='Your Cart']");
    private By inventoryItemName = By.className("inventory_item_name");
    private By removeButton = By.xpath("//button[text()='Remove']");
    private By cartItems = By.className("shopping_cart_badge");

    public CartPage(WebDriver driver) {
       super(driver);
    }

    public void verifyCartPage() {
        waitForVisible(cartTitle).isDisplayed();
    }

    public void verifyItemInCart(String itemName) {
        String actualItem = waitForVisible(inventoryItemName).getText();
        assertEquals(itemName, actualItem);
    }

    public void clickRemoveButton() {
        waitForVisible(removeButton).click();
    }

    public boolean isCartEmpty() {
        assertTrue("Cart badge should not be visible", isElementAbsent(cartItems));
        try {
            waitForVisible(inventoryItemName);
            return false; // If item is found, cart is not empty
        } catch (Exception e) {
            return true; // If item is not found, cart is empty
        }
    }
}
