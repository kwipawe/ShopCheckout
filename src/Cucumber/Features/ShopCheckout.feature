@shop
Feature: Buying a product

  Scenario: I buy a product

    Given I am on shop main page
    When I go to sign in page
    And I log in as an user using "sihrgcfkwpbwqlolhb@nthrw.com" and "secretpass"
    And I go back to main page
    Then I check if discount is right
    And I go to Hummingbird Printed Sweater product page
    And I choose "M" size
    And I choose  quantity "5"
    And I add product to shopping cart and proceed
    And I go to checkout page
    And I select proper address
    And I select pick up in-store delivery option
    And I choose payment option and finalize the order
    Then I capture a screenshot
    And I go to order history page
    And I check if my order has "Awaiting check payment" status
    And I check if price is correct