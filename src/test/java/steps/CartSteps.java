package steps;

import config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;
import pages.InventoryPage;

import java.util.List;

public class CartSteps {

    private static final Logger log = LoggerFactory.getLogger(CartSteps.class);

    private final WebDriver driver;
    private final CartPage cartPage;
    private InventoryPage inventoryPage;
    private final TestContext ctx;

    public CartSteps(TestContext ctx) {
        this.ctx = ctx;
        this.driver = ctx.getDriver();
        this.cartPage = new CartPage(driver);
        this.inventoryPage = new InventoryPage(driver);
    }


    @Then("The shopping cart should be empty")
    public void verifyCartIsEmpty() {
        cartPage.isCartEmpty();
    }

    @When("User proceeds to checkout")
    public void proceedToCheckout() {
        cartPage.clickCheckoutButton();
        log.info("proceedToCheckout: User proceeded to checkout ");
    }

    @And("The shopping cart should contain the following items:")
    public void verifyCartContainsItems(List<String> expectedItems) {
        inventoryPage.selectCart();
        cartPage.verifyItemsInCart(expectedItems);
        log.info("verifyCartContainsItems: Items verified in cart: " + expectedItems);
    }

    @And("The shopping cart should not contain {string} item")
    public void verifyItemIsNotInCart(String itemName) {
        cartPage.verifyItemNotInCart(itemName);
        log.info("verifyCartDoesNotContainItem: Item verified not in cart: " + itemName);
    }

    @And("And User calculates the total price of items in the cart")
    public void calculateTotalPriceOfItems() {
        double cartTotal = cartPage.calculateTotalPriceOfItems();
        ctx.setCartTotal(cartTotal);
        double taxesTotal = cartPage.calculateTotalWithTax(cartTotal);
        ctx.setTaxesTotalPrice(taxesTotal);
        log.info("calculateTotalPriceOfItems: cart total = " + cartTotal + ", with tax = " + taxesTotal);

    }

}
