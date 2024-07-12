package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImplementation extends BaseTest {

    public LandingPage lp;
    public ProductCataloguePage pcp;
    public ConfirmationPage confirmationPage;
    public CheckoutPage checkoutPage;
    public CartPage cp;

    @Given("I landed on ECommerce Page")
    public void iLandedOnECommercePage() throws IOException {
        lp = launchApplication();
    }

//    @Given("I landed on ECommerce Page ")
//    public void I_landed_on_ECommerce_Page() throws IOException {
//       lp =  launchApplication();
//
//    }

    @Given("^Logged in with (.+) and (.+)$")
    public void logged_in_username_and_password(String username, String password){
         pcp = lp.loginApp(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = pcp.getProducts();
        pcp.addProductToCart(productName);
    }

    // And is a conjunction , so we can use either @When or @And here.
    @And("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName){
        cp = pcp.goToCartPage();

        Assert.assertTrue(cp.verifyProductDisplay(productName));
        checkoutPage = cp.goToCheckout();

        // checkout page
        checkoutPage.setSelectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationpage(String string){
        String message = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(message.equalsIgnoreCase(string));

    }

    @Then("{string} message is displayed")
    public void messageIsDisplayed(String msg) {
        Assert.assertEquals(lp.getErrorMessage(), msg);
    }

}
