package org.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAloneTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.rahulshettyacademy.com/client/");

        driver.findElement(By.id("userEmail")).sendKeys("pravi.pravallika2011@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Pravallika&99");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));


        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

//        WebElement product = products.stream()
//                .filter(prod -> prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);

        products.stream().filter(prod -> prod.findElement(By.cssSelector("b")).getText()
                        .equalsIgnoreCase("zara coat 3")).findFirst()
                .ifPresent(product -> product.findElement(By.cssSelector(".card-body button:last-of-type")).click());
        //  product.findElement(By.cssSelector(".card-body button:last-of-type")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));

        boolean cartItemMatch =
                cartItems.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase("zara coat 3"));
//        Assert.assertTrue(cartItemMatch);
        if (cartItemMatch) {
            driver.findElement(By.cssSelector(".totalRow button")).click();
        }

        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();

        driver.findElement(By.cssSelector(".action__submit")).click();

        String message = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");

        driver.close();

    }
}
