package com.ui.tests;

import com.ui.pages.MyAccountPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({com.ui.listeners.TestListener.class})
public class SearchProductTest extends TestBase{

    private MyAccountPage myAccountPage;
    private static final String SEARCH_KEYWORD = "Mens Wallet";

    @BeforeMethod(description = "valid user logs into the application")
    public void setup(){
        myAccountPage = homePage.gotoSignInPage().doLoginWith("bakomam596@skrak.com", "password");
    }

    @Test(description = "verify if the logged in useer is able to search for a product and correct products search results are displayed",
            groups = {"e2e", "smoke", "sanity"})
    public void verifySearchProduct(){
        boolean actualResult = myAccountPage.searchProduct(SEARCH_KEYWORD).isSearchTermPresentInProductList(SEARCH_KEYWORD);
        Assert.assertEquals(actualResult, true);
    }
}
