package pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryPage {
    private WebDriver driver;


    public By appLogo = By.className("app_logo");
    public By sauceLabsBackpack = By.xpath("//div[text()='Sauce Labs Backpack']");
    public By shoppingCartLink = By.className("shopping_cart_link");
    public By cartItems = By.className("shopping_cart_badge");


    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }


    public void addItemToCart(String itemName) {
        driver.findElement(sauceLabsBackpack).isDisplayed();
        String itemLocator = itemName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.cssSelector("button[data-test='add-to-cart-" + itemLocator + "']");
        driver.findElement(addToCartButton).click();
    }

    public void cartItemCount(int expectedCount) {
        String cartItemCount = String.valueOf(expectedCount);
        driver.findElement(shoppingCartLink).isDisplayed();
        assertTrue("Expected " + expectedCount + " items in cart, but found " + cartItemCount,
                driver.findElement(cartItems).getText().equals(cartItemCount));

    }

    public void selectCart(){
        driver.findElement(shoppingCartLink).click();
        String actualURL = driver.getCurrentUrl();
        assertEquals(TestConfig.CART_URL, actualURL);
    }


}
