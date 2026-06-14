package steps;

import config.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.CheckoutCompletePage;
import pages.CheckoutOverviewPage;
import pages.CheckoutPage;
import pages.InventoryPage;

public class CheckoutSteps {

    private final WebDriver driver;
    public CheckoutSteps(TestContext ctx) {
        this.driver = ctx.getDriver();

    }

    @Then("The checkout page should be displayed")
    public void theCheckoutPageShouldBeDisplayed() {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.verifyCheckoutPage();
        System.out.println("=== theCheckoutPageShouldBeDisplayed: Checkout page is displayed ===");
    }

   @When("User enters checkout information with first name {string}, last name {string}, and postal code {string}")
    public void userEntersCheckoutInformation(String firstName, String lastName, String postalCode) {
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        checkoutPage.enterCheckoutInformation(firstName, lastName, postalCode);
   System.out.println("=== userEntersCheckoutInformation: Entered checkout information ===");
    }

     @Then("The checkout overview page should be displayed")
    public void theCheckoutOverviewPageShouldBeDisplayed() {
         CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
         checkoutOverviewPage.verifyCheckoutOverviewPage();
         System.out.println("=== theCheckoutOverviewPageShouldBeDisplayed: Checkout overview page is displayed ===");
     }

    @And ("The checkout overview page should contain {string} item")
    public void theCheckoutOverviewPageShouldContainItem(String itemName) {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.verifyFirstProduct(itemName);
        System.out.println("=== theCheckoutOverviewPageShouldContainItem: Verified item in checkout overview ===");
    }

    @When("User completes the checkout process")
    public void userCompletesTheCheckoutProcess() {
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutOverviewPage.completeCheckout();
        System.out.println("=== userCompletesTheCheckoutProcess: Completed checkout process ===");
    }

    @Then("The checkout complete page should be displayed")
    public void theCheckoutCompletePageShouldBeDisplayed() {
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver);
        checkoutCompletePage.verifyCheckoutCompletePage();
        System.out.println("=== theCheckoutCompletePageShouldBeDisplayed: Checkout complete page is displayed ===");
    }
}
