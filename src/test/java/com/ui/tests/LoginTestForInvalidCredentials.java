package com.ui.tests;

import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;

import com.utility.LoggerUtility;

import static org.testng.Assert.assertEquals;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTestForInvalidCredentials extends TestBase {

  Logger logger = LoggerUtility.getLogger(this.getClass());
  private static final String INVALID_EMAIL_ADDRESS = "jatinvsharma@gmail.com";
  private static final String INVALID_PASSWORD = "invalidpassword";


  @Test(description="Verifies with the invalid credentials, user should not be able to login and appropriate error message should be displayed", groups={"e2e", "sanity", "smoke"})
  public void loginTest(){
    assertEquals(homePage.gotoSignInPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(), "Authentication failed.");
  }

}
