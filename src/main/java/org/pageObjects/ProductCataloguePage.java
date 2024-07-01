package org.pageObjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCataloguePage extends AbstractComponent {
    WebDriver driver;

    public ProductCataloguePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement invisibleElement;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProducts() {
        waitForElement(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProducts().stream()
                .filter(product -> product.findElement(By.cssSelector("b"))
                        .getText()
                        .equalsIgnoreCase(productName))
                .findFirst().orElse(null);
    }

    public void  addProductToCart(String productName) throws InterruptedException {
        getProductByName(productName).findElement(addToCart).click();
        waitForElement(toastMessage);
        waitForElementToDisappear(invisibleElement);
    }

}
