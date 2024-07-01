package org.pageObjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractComponent {

    WebDriver driver;

    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[placeholder='Select Country']")
    WebElement countryField;

    @FindBy(css = ".action__submit")
    WebElement submitButton;

    @FindBy(xpath = "//button[contains(@class, 'ta-item')][2]")
    WebElement selectCountry;

    By countryName = By.cssSelector(".ta-results");

    public void setSelectCountry(String name){
        Actions actions = new Actions(driver);
        actions.sendKeys(countryField, name).build().perform();
        waitForElement(countryName);
        selectCountry.click();
    }

    public ConfirmationPage submitOrder(){
        submitButton.click();
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);
        return confirmationPage;
    }


}
