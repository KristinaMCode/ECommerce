package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class CartPage {
    private WebDriver driver;

    public By cartTitle = By.name("Your Cart");
    public By inventoryItemName = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void verifyCartPage() {
        driver.findElement(cartTitle).isDisplayed();
    }

    public void verifyItemInCart(String itemName) {
        String actualItem = driver.findElement(inventoryItemName).getText();
        assertEquals(itemName, actualItem);
    }
}
