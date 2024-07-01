package org.tests;

import org.openqa.selenium.WebElement;
import org.pageObjects.*;
import org.testComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> inputMap) throws IOException, InterruptedException {


        //   LandingPage lp = launchApplication();

        ProductCataloguePage pcp = lp.loginApp(inputMap.get("email"), inputMap.get("password"));

        // product page
        List<WebElement> products = pcp.getProducts();
        pcp.addProductToCart(inputMap.get("productName"));
        CartPage cp = pcp.goToCartPage();

        Assert.assertTrue(cp.verifyProductDisplay(inputMap.get("productName")));
        CheckoutPage checkoutPage = cp.goToCheckout();

        // checkout page
        checkoutPage.setSelectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String message = confirmationPage.getConfirmationMessage();
        // confirmation page
        Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    // To verify ZARA COAT 3 is displaying in the orders page

    @Test(dependsOnMethods = {"submitOrder"})
    public void orderHistoryTest() {
        ProductCataloguePage productCataloguePage = lp.loginApp("pravi.pravallika2011@gmail.com", "Pravallika&99");
        OrderPage op = productCataloguePage.gotToOrdersPage();
        Assert.assertTrue(op.verifyOrderDisplay(productName));
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        HashMap<String, String> hmap = new HashMap<>();

//        hmap.put("email", "pravi.pravallika2011@gmail.com");
//        hmap.put("password", "Pravallika&99");
//        hmap.put("productName", "ZARA COAT 3");
//
//        HashMap<String, String> hmap1 = new HashMap<>();
//        hmap1.put("email", "vsjp@gmail.com");
//        hmap1.put("password", "sjpV@12345");
//        hmap1.put("productName", "IPHONE 13 PRO");

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java" +
                "//org//data" +
                "//PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};

    }
}
