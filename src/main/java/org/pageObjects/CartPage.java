package org.pageObjects;

import org.abstractComponents.AbstractComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartItems;

    @FindBy(css = ".totalRow button")
    WebElement checkOutButton;

    public Boolean verifyProductDisplay(String productName){
        return cartItems.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout(){
        checkOutButton.click();
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        return checkoutPage;
    }
}
