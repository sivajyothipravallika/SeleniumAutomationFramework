package com.ui.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;

public class AddNewAddressTest extends TestBase{

  private MyAccountPage myAccountPage;
  private AddressPage addressPage;

  @BeforeMethod(description = "Valid user logs into the application")
  public void setUp(){
    myAccountPage = homePage.gotoSignInPage().doLoginWith("bakomam596@skrak.com", "password");
  }

  @Test
  public void addNewAddress(){
    myAccountPage.goToAddNewAddressPage().saveAddress();
  }
}
