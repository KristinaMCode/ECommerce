package steps;

import config.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class CartSteps {

    public final WebDriver driver;

    public CartSteps(TestContext ctx) {
        this.driver = ctx.getDriver();
    }

    @When("User removes {string} from the shopping cart")
    public void removeItemFromCart(String itemName) {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickRemoveButton();
        System.out.println("=== removeItemFromCart: Item " + itemName + " removed from cart ===");
    }


    @Then("The shopping cart should be empty")
    public void verifyCartIsEmpty() {
        CartPage cartPage = new CartPage(driver);
        cartPage.isCartEmpty();
    }

    @When("User proceeds to checkout")
    public void proceedToCheckout() {
        CartPage cartPage = new CartPage(driver);
        cartPage.clickCheckoutButton();
        System.out.println("=== proceedToCheckout: User proceeded to checkout ===");
    }
}
