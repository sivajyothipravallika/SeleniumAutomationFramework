package com.ui.pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.TimeoutException;
import java.time.Duration;

import com.utility.BrowserUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchResultPage extends BrowserUtility {

    private static final Logger logger = LogManager.getLogger(SearchResultPage.class);
    private static final By PRODUCT_LISTING_TITLE_LOCATOR = By.xpath("//span[@class='lighter']");
    private static final By PRODUCT_LISTING_LOCATOR = By.xpath("//h5[@itemprop='name']/a");

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public String getSearchResultTitle() {
        return getVisibleText(PRODUCT_LISTING_TITLE_LOCATOR);
    }

    @SuppressWarnings("null")
    public boolean isSearchTermPresentInProductList(String searchTerm) {
        try {
            // Wait for products to load (max 10 seconds)
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_LISTING_LOCATOR));
        } catch (TimeoutException e) {
            logger.info("No products found for search term: " + searchTerm);
            return false;
        }

        List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
        List<String> productNamesList = getAllVisibleText(PRODUCT_LISTING_LOCATOR);

        logger.info("Search term: " + searchTerm);
        logger.info("Keywords: " + keywords);
        logger.info("All products: " + productNamesList);

        // Filter products that contain ANY of the keywords from the search term
        List<String> relevantProducts = productNamesList.stream()
            .filter(productName -> {
                boolean matches = keywords.stream()
                    .anyMatch(keyword -> productName.toLowerCase().contains(keyword));
                if (matches) {
                    logger.info("Relevant product found: " + productName);
                }
                return matches;
            })
            .collect(Collectors.toList());

        logger.info("Relevant products: " + relevantProducts);
        logger.info("Is search term present: " + !relevantProducts.isEmpty());

        // Ensure at least one relevant product is found
        return !relevantProducts.isEmpty();
    }
}
