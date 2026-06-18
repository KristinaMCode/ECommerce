package steps;

import config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.InventoryPage;

import java.util.List;

public class CheckoutSteps {

    private static final Logger log = LoggerFactory.getLogger(CheckoutSteps.class);


    private final WebDriver driver;
    private final CheckoutPage checkoutPage;
    private final CheckoutOverviewPage checkoutOverviewPage;
    private final CheckoutCompletePage checkoutCompletePage;

    public CheckoutSteps(TestContext ctx) {
        this.driver = ctx.getDriver();
        this.checkoutPage = new CheckoutPage(driver);
        this.checkoutOverviewPage = new CheckoutOverviewPage(driver);
        this.checkoutCompletePage = new CheckoutCompletePage(driver);
    }


    @Then("The checkout page should be displayed")
    public void theCheckoutPageShouldBeDisplayed() {
        checkoutPage.verifyCheckoutPage();
        log.info("theCheckoutPageShouldBeDisplayed: Checkout page is displayed ");
    }

    @When("User enters checkout information with first name {string}, last name {string}, and postal code {string}")
    public void userEntersCheckoutInformation(String firstName, String lastName, String postalCode) {
        checkoutPage.enterCheckoutInformation(firstName, lastName, postalCode);
        log.info("userEntersCheckoutInformation: Entered checkout information ");
    }

    @Then("The checkout overview page should be displayed")
    public void theCheckoutOverviewPageShouldBeDisplayed() {
        checkoutOverviewPage.verifyCheckoutOverviewPage();
        log.info("theCheckoutOverviewPageShouldBeDisplayed: Checkout overview page is displayed ");
    }

    @And("The checkout overview page should contain {string} item")
    public void theCheckoutOverviewPageShouldContainItem(String itemName) {
        checkoutOverviewPage.verifyFirstProduct(itemName);
        log.info("theCheckoutOverviewPageShouldContainItem: Verified item in checkout overview ");
    }

    @When("User completes the checkout process")
    public void userCompletesTheCheckoutProcess() {
        checkoutOverviewPage.completeCheckout();
        log.info("userCompletesTheCheckoutProcess: Completed checkout process ");
    }

    @Then("The checkout complete page should be displayed")
    public void theCheckoutCompletePageShouldBeDisplayed() {
        checkoutCompletePage.verifyCheckoutCompletePage();
        log.info("theCheckoutCompletePageShouldBeDisplayed: Checkout complete page is displayed ");
    }

    @And ("The checkout overview page should contain the following items:")
    public void verifyCheckoutPageContainsItems(List<String> expectedItems) {
  checkoutOverviewPage.verifyItemsInCart(expectedItems);
        log.info("verifyCheckoutOverviewContainsItems: Items verified in on Checkout Overview Page: " + expectedItems);
    }
}
