package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.utility.BrowserUtility;

public class AddressPage extends BrowserUtility {

    private static final By COMPANY_TEXTBOX_LOCATOR = By.id("company");
    private static final By ADDRESS1_TEXTBOX_LOCATOR = By.id("address1");
    private static final By ADDRESS2_TEXTBOX_LOCATOR = By.id("address2");
    private static final By CITY_TEXTBOX_LOCATOR = By.id("city");
    private static final By STATE_DROPDOWN_LOCATOR = By.id("id_state");
    private static final By POSTALCODE_TEXTBOX_LOCATOR = By.id("postcode");
    private static final By ADDITIONALINFO_TEXTBOX_LOCATOR = By.id("other");
    private static final By HOME_PHONE_TEXTBOX_LOCATOR = By.id("phone");
    private static final By MOBILE_PHONE_TEXTBOX_LOCATOR = By.id("phone_mobile");
    private static final By ADDRESS_ALIAS_TEXTBOX_LOCATOR = By.id("alias");
    private static final By SAVE_ADDRESS_BUTTON_LOCATOR = By.id("submitAddress");

    public AddressPage(WebDriver driver) {
        super(driver);
    }

    public void saveAddress() {
        enterText(COMPANY_TEXTBOX_LOCATOR, "ABC");
        enterText(ADDRESS1_TEXTBOX_LOCATOR, "123 Main St");
        enterText(ADDRESS2_TEXTBOX_LOCATOR, "Apt 4B");
        enterText(CITY_TEXTBOX_LOCATOR, "New York");
        selectFromDropdownWhenOptionAvailable(STATE_DROPDOWN_LOCATOR, "California");
        enterText(POSTALCODE_TEXTBOX_LOCATOR, "10001");
        enterText(ADDITIONALINFO_TEXTBOX_LOCATOR, "Additional info");
        enterText(HOME_PHONE_TEXTBOX_LOCATOR, "1234567890");
        enterText(MOBILE_PHONE_TEXTBOX_LOCATOR, "0987654321");
        clearText(ADDRESS_ALIAS_TEXTBOX_LOCATOR);
        enterText(ADDRESS_ALIAS_TEXTBOX_LOCATOR, "My Address");
        clickOn(SAVE_ADDRESS_BUTTON_LOCATOR);
    }

}
