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

public class InventorySteps {

    private final WebDriver driver;

    private static final Logger log = LoggerFactory.getLogger(InventorySteps.class);

    private final InventoryPage inventoryPage;
    private final CartPage cartPage;

    public InventorySteps(TestContext ctx) {
        this.driver = ctx.getDriver();
        this.inventoryPage = new InventoryPage(driver);
        this.cartPage = new CartPage(driver);
    }

    @When("User adds the {string} to the shopping cart")
    public void userAddsItemToCart(String itemName) {
        inventoryPage.addItemToCart(itemName);
        log.info("userAddsItemToCart: Item added to cart ");
    }

    @Then("The shopping cart should contain {int} item(s)")
    public void verifyCartItemCount(int expectedCount) {
        inventoryPage.cartItemCount(expectedCount);
        log.info("verifyCartItemCount: Cart item count verified it is " + expectedCount);
    }

    @And("The shopping cart should contain {string} item")
    public void verifyCartContainsItem(String itemName) {
        inventoryPage.selectCart();
        cartPage.verifyItemInCart(itemName);
        log.info("verifyCartContainsItem: Item " + itemName + " verified in cart ");
    }

    @When("User sorts products by {string}")
    public void sortProducts(String sortOption) {
        inventoryPage.sortProducts(sortOption);
    }

    @Then("First product should be {string}")
    public void verifyFirstProduct(String expectedProduct) {
        inventoryPage.verifyFirstProduct(expectedProduct);
    }

    @When("User removes item {string} from the shopping cart")
    public void removeItemFromCart(String itemName) {
        inventoryPage.clickRemoveButton(itemName);
        log.info("removeItemFromCart: Item " + itemName + " removed from cart ");
    }
}

