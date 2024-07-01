package org.pageObjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement userPassword;

    @FindBy(id="login")
    WebElement login;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;

    public ProductCataloguePage loginApp(String email, String pwd){
        userEmail.sendKeys(email);
        userPassword.sendKeys(pwd);
        login.click();
        ProductCataloguePage pcp = new ProductCataloguePage(driver);
        return pcp;
    }

    public String getErrorMessage(){
        waitForElement(errorMessage);
        return errorMessage.getText();
    }

    public void goTo(){
        driver.get("https://www.rahulshettyacademy.com/client/");
    }
}
