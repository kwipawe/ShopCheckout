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