package org.abstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pageObjects.CartPage;
import org.pageObjects.OrderPage;

import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartButton;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;

    public void waitForElement(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToDisappear(WebElement element) throws InterruptedException {
        Thread.sleep(1000L);
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public CartPage goToCartPage(){
        cartButton.click();
        CartPage cp = new CartPage(driver);
        return cp;
    }

    public OrderPage gotToOrdersPage(){
        orderHeader.click();
        OrderPage op = new OrderPage(driver);
        return op;
    }
}
