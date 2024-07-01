package org.tests;

import org.openqa.selenium.WebElement;
import org.pageObjects.CartPage;
import org.pageObjects.ProductCataloguePage;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups = {"errorvalidation"})
    public void loginErrorValidation() {
        String productName = "ZARA COAT 3";
        lp.loginApp("pravi.pravalika2011@gmail.com",
                "pravallika&99");
        Assert.assertEquals(lp.getErrorMessage(), "Incorrect email or password.");

    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCataloguePage pcp =  lp.loginApp("vsjp@gmail.com", "sjpV@12345");

        // product page
        List<WebElement> products = pcp.getProducts();
        pcp.addProductToCart(productName);
        CartPage cp = pcp.goToCartPage();

        Assert.assertTrue(cp.verifyProductDisplay("ZARA COAT 33"));
    }


}
