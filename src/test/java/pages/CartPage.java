package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class CartPage  extends BasePage {

    private By cartTitle = By.xpath("//span[text()='Your Cart']");
    private By inventoryItemName = By.className("inventory_item_name");

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
}
