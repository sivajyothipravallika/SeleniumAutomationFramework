package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility {

    // instance variable
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    static {
        java.util.logging.Logger.getLogger("org.openqa.selenium.devtools.CdpVersionFinder").setLevel(Level.OFF);
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    // constructor
    public BrowserUtility(WebDriver driver) {
        BrowserUtility.driver.set(driver); // initialize the instance variable 'driver'
    }

    public BrowserUtility(String browserName){
        if(browserName.equalsIgnoreCase("chrome")){
            driver.set(new ChromeDriver());
        }else if (browserName.equalsIgnoreCase("edge")){
            driver.set(new EdgeDriver());
        }else if (browserName.equalsIgnoreCase("firefox")){
            driver.set(new FirefoxDriver());
        }else {
            logger.error("Invalid Browser Name... Please select chrome, edge or firefox");
            System.err.println("Invalid Browser Name... Please select chrome, edge or firefox");
        }
    }

    public BrowserUtility(Browser browserName){
        logger.info("Launching Browser for "+browserName);
        if(browserName == Browser.CHROME){
            driver.set(new ChromeDriver());
        }else if (browserName == Browser.EDGE){
            driver.set(new EdgeDriver());
        }else if (browserName == Browser.FIREFOX){
            driver.set(new FirefoxDriver());
        }
    }

    public BrowserUtility(Browser browserName, boolean isHeadless){
        logger.info("Launching Browser for "+browserName);
        if(browserName == Browser.CHROME){
            if(isHeadless){
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new"); // driver going to launch the browser in headless mode
                options.addArguments("--window-size=1920,1080");
                driver.set(new ChromeDriver(options));
            }else{
                driver.set(new ChromeDriver());
            }
        }else if (browserName == Browser.EDGE){
            if(isHeadless){
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--headless=old"); // driver going to launch the browser in headless mode
                options.addArguments("disable-gpu");
                driver.set(new EdgeDriver(options));
            }else{
                driver.set(new EdgeDriver());
            }
        }else if (browserName == Browser.FIREFOX){
            if(isHeadless){
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless=old"); // driver going to launch the browser in headless mode
                options.addArguments("--window-size=1920,1080");
                driver.set(new FirefoxDriver(options));
            }else{
                driver.set(new FirefoxDriver());
            }

        }
    }

    // navigate to website
    @SuppressWarnings("null")
    public void goToWebsite(String url){
        logger.info("Visiting website "+url);
        driver.get().get(url); // navigate to the specified URL
    }

    // maximise the browser window
    public void maximiseBrowserWindow(){
        logger.info("Maximizing the browser window ");
        driver.get().manage().window().maximize(); // maximise the browser window
    }

    public void clickOn(By locator){
        logger.info("Waiting for element to become clickable: "+locator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        logger.info("Element is clickable and now performing click: "+locator);
        element.click();
    }

    public void enterText(By locator, String text){
        logger.info("Waiting for element to be visible: "+locator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        logger.info("Element is visible and now entering text: "+text);
        element.sendKeys(text);
    }

    public void clearText(By textBoxLocator){
        logger.info("Waiting for element to be visible: "+textBoxLocator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textBoxLocator));
        logger.info("Element is visible and now clearing the text box field");
        element.clear();
    }

    public void enterSpecialKey(By locator, Keys keyToEnter){
        logger.info("Finding element with the locator "+locator);
        @SuppressWarnings("null")
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now enter special key"+keyToEnter);
        element.sendKeys(keyToEnter);
    }

    @SuppressWarnings("null")
    public String getVisibleText(By locator){
        logger.info("Finding element with the locator "+locator);
        WebElement element = driver.get().findElement(locator);
        logger.info("Element found and now returning the visible text "+element.getText());
        return element.getText();
    }

    @SuppressWarnings("null")
    public String getVisibleText(WebElement element) {
        logger.info("Returning the Visible Text " + element.getText());
        return element.getText();
    }

    @SuppressWarnings("null")
    public void selectFromDropdown(By dropdownLocator, String optionToSelect){
        logger.info("Waiting for dropdown to be present: "+dropdownLocator);
        // WebElement element = driver.get().findElement(dropdownLocator);
        // Select select = new Select(element);
        // logger.info("Selecting the option "+optionToSelect);
        // select.selectByVisibleText(optionToSelect);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
        WebElement element;
        try {
            element = wait.until(ExpectedConditions.presenceOfElementLocated(dropdownLocator));
        } catch (TimeoutException e) {
            logger.warn("Dropdown not found with locator " + dropdownLocator + ". Trying fallback selectors.");
            element = findFallbackSelect("state");
        }
        scrollToElement(element);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            logger.warn("Dropdown element not clickable after wait; continuing with selection.");
        }
        Select select = new Select(element);
        logger.info("Selecting dropdown option by visible text first: "+ optionToSelect);
        try {
            select.selectByVisibleText(optionToSelect);
        } catch (NoSuchElementException e) {
            logger.warn("Option not found by visible text, trying by value: " + optionToSelect);
            select.selectByValue(optionToSelect);
        }
    }

    @SuppressWarnings("null")
    public void selectFromDropdownWhenOptionAvailable(By dropdownLocator, String optionToSelect) {
        logger.info("Waiting for dropdown to be present: " + dropdownLocator);
        WebDriverWait wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(dropdownLocator));
        scrollToElement(element);

        logger.info("Waiting for dropdown option to become available: " + optionToSelect);
        wait.until(driver -> {
            WebElement currentElement = driver.findElement(dropdownLocator);
            Select select = new Select(currentElement);
            return select.getOptions().size() > 1 || hasOption(select, optionToSelect);
        });

        WebElement currentElement = driver.get().findElement(dropdownLocator);
        Select select = new Select(currentElement);
        wait.until(driver -> hasOption(select, optionToSelect));

        if (currentElement.isDisplayed()) {
            select.selectByVisibleText(optionToSelect);
            wait.until(driver -> optionToSelect.equals(select.getFirstSelectedOption().getText().trim()));
        } else {
            selectHiddenDropdownOption(currentElement, optionToSelect);
            wait.until(driver -> {
                Select refreshedSelect = new Select(driver.findElement(dropdownLocator));
                return optionToSelect.equals(refreshedSelect.getFirstSelectedOption().getText().trim());
            });
        }

        logger.info("Selected dropdown option: " + optionToSelect);
    }

    private WebElement findFallbackSelect(String keyword) {
        @SuppressWarnings("null")
        List<By> fallbackLocators = List.of(
            By.cssSelector("select[id*='" + keyword + "']"),
            By.cssSelector("select[name*='" + keyword + "']"),
            By.xpath("//label[contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '" + keyword + "')]/following::select[1]"),
            By.xpath("//select[contains(@id, '" + keyword + "') or contains(@name, '" + keyword + "')]")
        );
        for (By locator : fallbackLocators) {
            List<WebElement> candidates = driver.get().findElements(locator);
            if (!candidates.isEmpty()) {
                logger.info("Found dropdown using fallback locator: " + locator);
                return candidates.get(0);
            }
        }
        throw new NoSuchElementException("Unable to locate dropdown with locator or fallback selectors for keyword: " + keyword);
    }

    private void scrollToElement(WebElement element) {
        try {
            ((JavascriptExecutor) driver.get()).executeScript("arguments[0].scrollIntoView({behavior:'auto', block:'center', inline:'nearest'});", element);
        } catch (Exception e) {
            logger.warn("Unable to scroll to dropdown element: " + e.getMessage());
        }
    }

    private boolean hasOption(Select select, String optionToSelect) {
        return select.getOptions().stream()
            .anyMatch(option -> optionToSelect.equals(option.getText().trim()));
    }

    private void selectHiddenDropdownOption(WebElement element, String optionToSelect) {
        Select select = new Select(element);
        String optionValue = select.getOptions().stream()
            .filter(option -> optionToSelect.equals(option.getText().trim()))
            .map(option -> option.getAttribute("value"))
            .findFirst()
            .orElseThrow(() -> new NoSuchElementException("Unable to find option with text: " + optionToSelect));

        ((JavascriptExecutor) driver.get()).executeScript(
            "arguments[0].value = arguments[1];" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element,
            optionValue
        );
    }

    @SuppressWarnings("null")
    public List<String> getAllVisibleText(By locator) {
        logger.info("Finding all elements with the locator " + locator);
        List<WebElement> elements = driver.get().findElements(locator);
        logger.info("Elements found and now returning the visible text of all the elements");
        List<String> visibleTextList = new ArrayList<>();
        for (WebElement element : elements) {
                visibleTextList.add(getVisibleText(element));
            }
        return visibleTextList;
    }

    public String takeScreenShot(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) driver.get();
        File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
        String timeStamp = format.format(date);
        String path = "./screenshots/" +name+" - "+timeStamp+".png";
        File screenshotFile = new File(path);
        try {
            FileUtils.copyFile(screenshotData, screenshotFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;

    }

    public void quit(){
        logger.info("Closing the browser");
        WebDriver currentDriver = driver.get();
        if(currentDriver != null) {
            currentDriver.quit(); // close the browser
            driver.remove();
        }
    }
}
