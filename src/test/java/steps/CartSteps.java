package steps;

import config.TestContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CartPage;

public class CartSteps {

    private static final Logger log = LoggerFactory.getLogger(CartSteps.class);

    private final WebDriver driver;
    private final CartPage cartPage;

    public CartSteps(TestContext ctx) {
        this.driver = ctx.getDriver();
        this.cartPage = new CartPage(driver);
    }

    @When("User removes {string} from the shopping cart")
    public void removeItemFromCart(String itemName) {
        cartPage.clickRemoveButton();
        log.info("removeItemFromCart: Item " + itemName + " removed from cart ");
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
}
