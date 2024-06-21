
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on ecommerce page

  @Regression
  Scenario Outline: Positive Test of Subitting the order.
    Given Logged in with username <name> and password <password>
    When Add prodct <productName> to cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation Page

    Examples: 
      | name  									         | password             | productName       |
      | minnesota.mn62@gmail.com         | Minnesota@123        | ADIDAS ORIGINAL   |
     