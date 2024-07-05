package org.tests;

import org.openqa.selenium.WebElement;
import org.pageObjects.CartPage;
import org.pageObjects.ProductCataloguePage;
import org.testComponents.BaseTest;
import org.testComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test(groups = {"errorvalidation"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {
    //    String productName = "ZARA COAT 3";
        lp.loginApp("pravi.pravalika2011@gmail.com",
                "pravallika&99");
        Assert.assertEquals(lp.getErrorMessage(), "Incorrect email  password.");

    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "IPHONE 13 PRO";

        ProductCataloguePage pcp =  lp.loginApp("vsjp@gmail.com", "sjpV@12345");

        // product page
        List<WebElement> products = pcp.getProducts();
        pcp.addProductToCart(productName);
        CartPage cp = pcp.goToCartPage();

        Assert.assertTrue(cp.verifyProductDisplay("IPHONE 13 PRO"));
    }


}
