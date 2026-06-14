package steps;

import config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.InventoryPage;

public class InventorySteps {

    private final WebDriver driver;
    public InventorySteps(TestContext ctx) {
        this.driver = ctx.getDriver();
    }

    @When("User adds the {string} to the shopping cart")
    public void userAddsItemToCart(String itemName) {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addItemToCart(itemName);
        System.out.println("=== userAddsItemToCart: Item added to cart ===");
    }

    @Then("The shopping cart should contain {int} item(s)")
    public void verifyCartItemCount(int expectedCount) {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.cartItemCount(expectedCount);
        System.out.println("=== verifyCartItemCount: Cart item count verified it is " + expectedCount + " ===");
    }

    @And("The shopping cart should contain {string} item")
    public void verifyCartContainsItem(String itemName) {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.selectCart();
        CartPage cartPage = new CartPage(driver);
        cartPage.verifyItemInCart(itemName);
        System.out.println("=== verifyCartContainsItem: Item "+itemName+" verified in cart ===");
    }

    @When ("User sorts products by {string}")
    public void sortProducts(String sortOption) {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.sortProducts(sortOption);
    }

    @Then ("First product should be {string}")
    public void verifyFirstProduct(String expectedProduct) {
        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.verifyFirstProduct(expectedProduct);
    }
}

