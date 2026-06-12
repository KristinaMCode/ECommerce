package steps;

import config.TestContext;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class CartSteps {

    public final WebDriver driver;
    public CartSteps(TestContext ctx) {
        this.driver = ctx.getDriver();
    }

    @Then("Remove the {string} from the shopping cart")
    public void removeItemFromCart(String itemName) {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickRemoveButton();
        System.out.println("=== removeItemFromCart: Item " + itemName + " removed from cart ===");
    }


    @Then("The shopping cart should be empty")
    public void verifyCartIsEmpty() {
        CartPage cartPage = new CartPage(driver);
        if (cartPage.isCartEmpty()) {
            System.out.println("=== verifyCartIsEmpty: Shopping cart is empty ===");
        } else {
            System.out.println("=== verifyCartIsEmpty: Shopping cart is not empty ===");
        }
    }
}
