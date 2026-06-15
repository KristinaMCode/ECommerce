package pages;

import config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class InventoryPage extends BasePage {


    private By appLogo = By.className("app_logo");
    private By sauceLabsBackpack = By.xpath("//div[text()='Sauce Labs Backpack']");
    private By shoppingCartLink = By.className("shopping_cart_link");
    private By cartItems = By.className("shopping_cart_badge");
    private By sortDropdown = By.className("product_sort_container");


    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public void addItemToCart(String itemName) {
        String itemLocator = itemName.toLowerCase().replace(" ", "-");
        By addToCartButton = By.cssSelector("button[data-test='add-to-cart-" + itemLocator + "']");
        waitForClickable(addToCartButton).click();
    }

    public void cartItemCount(int expectedCount) {
        String cartItemCount = String.valueOf(expectedCount);
        assertTrue("Expected " + expectedCount + " items in cart, but found " + cartItemCount,
                waitForVisible(cartItems).getText().equals(cartItemCount));
    }

    public void selectCart() {
        waitForClickable(shoppingCartLink).click();
        waitForUrlToContain("cart.html");
        String actualURL = driver.getCurrentUrl();
        assertEquals(TestConfig.CART_URL, actualURL);
    }

    public void sortProducts(String sortOption) {
        waitForClickable(sortDropdown).click();
        By option = By.xpath("//option[text()='" + sortOption + "']");
        waitForClickable(option).click();
    }

    public void verifyFirstProduct(String expectedProduct) {
        By firstProduct = By.xpath("(//div[@class='inventory_item_name '])[1]");
        String actualFirstProduct = waitForVisible(firstProduct).getText();
        assertEquals("Expected first product to be " + expectedProduct + " but found " + actualFirstProduct,
                expectedProduct, actualFirstProduct);
    }
}
