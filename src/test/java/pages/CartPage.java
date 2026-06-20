package pages;

import config.TestContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.junit.Assert.*;

public class CartPage extends BasePage {

    private By cartTitle = By.xpath("//span[text()='Your Cart']");
    private By inventoryItemName = By.className("inventory_item_name");
    private By cartItems = By.className("shopping_cart_badge");
    private By checkoutButton = By.id("checkout");
    private By itemPrice = By.className("inventory_item_price");

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


    public void isCartEmpty() {
        assertTrue("Cart badge should not be visible", isElementAbsent(cartItems));
        //   assertTrue("Cart item should not be visible", isElementAbsent(inventoryItemName));
    }

    public void clickCheckoutButton() {
        waitForVisible(checkoutButton).click();
    }

    public void verifyItemsInCart(List<String> expectedItems) {
        verifyItemsPresent(expectedItems, getCartItemNames());
    }

    public List<String> getCartItemNames() {
        return getElementTexts(inventoryItemName);
    }

    public void verifyItemNotInCart(String itemName) {
        List<String> actualItemNames = getCartItemNames();
        assertFalse(itemName + " should not be present in the cart" + actualItemNames, actualItemNames.contains(itemName));
    }

    public double calculateTotalPriceOfItems() {
        List<String> itemsPrices = getCartItemPrices();
        System.out.println(itemsPrices);
        double priceOfItem = 0;
        for (String price : itemsPrices) {
            priceOfItem += Double.parseDouble(price.substring(1));
            System.out.println(priceOfItem);
        }
        return priceOfItem;
    }

    public List<String> getCartItemPrices() {
        return getElementTexts(itemPrice);
    }

    public Double calculateTaxes(double cartTotal) {
        return (cartTotal * 8 / 100) + cartTotal;
    }
}
