@tag
Feature: Error Validation
  @ErrorValidation
  Scenario Outline: Error Validation for Login
    Given I landed on ECommerce Page
    When Logged in with  <username> and  <password>
    Then "Incorrect email or password." message is displayed

    Examples:
      | username                          | password      |
      | pravi.pravalika2011@gmail.com | pravallika&99 |