@tag
Feature: Purchase the order from Ecommerce Website I want to use this template for my
  feature file

  Background:
    Given I landed on ECommerce Page

    @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with <username> and <password>
    When I add product <productName> to Cart
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage
    Examples:
      | username                       | password      | productName   |
      | pravi.pravallika2011@gmail.com | Pravallika&99 | ZARA COAT 3   |
      | vsjp@gmail.com                 | sjpV@12345    | IPHONE 13 PRO |




#    Examples:
#      | username                       |  |  | password      | productName   |
#      | Pravi.pravallika2011@gmail.com |  |  | Pravallika&99 | ZARA COAT 3   |
#      | VSJP@gmail.com                 |  |  | sjpV@1234     | IPHONE 13 PRO |
