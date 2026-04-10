package com.ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility{

    private static final By USERNAME_LOCATOR = By.xpath("//a[@title='View my customer account']/span");
    private static final By SEARCH_TEXT_BOX_LOCATOR = By.id("search_query_top");
    private static final By ADD_NEW_ADDRESS_BUTTON_LOCATOR = By.xpath("//a[@title='Add my first address']");


    public MyAccountPage(WebDriver driver) {
        super(driver);
    }
    public String getUserName(){
        return getVisibleText(USERNAME_LOCATOR);
    }

    public SearchResultPage searchProduct(String productName){
        enterText(SEARCH_TEXT_BOX_LOCATOR, productName);
        enterSpecialKey(SEARCH_TEXT_BOX_LOCATOR, Keys.ENTER);
        return new SearchResultPage(getDriver());
    }

    @SuppressWarnings("null")
    public AddressPage goToAddNewAddressPage(){
        clickOn(ADD_NEW_ADDRESS_BUTTON_LOCATOR);
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.or(
            ExpectedConditions.visibilityOfElementLocated(By.id("company")),
            ExpectedConditions.visibilityOfElementLocated(By.id("address1")),
            ExpectedConditions.visibilityOfElementLocated(By.id("postcode"))
        ));
        return new AddressPage(getDriver());
    }

}
