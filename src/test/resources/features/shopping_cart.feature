Feature: Shopping cart
  As a user
  I want to add products to the shopping cart
  So that I can review my selections before checkout

  Background:
    Given I open the Saucedemo home page
    Then User logs in
    Then User is logged in successfully

  @smoke
  Scenario: Add a product to the shopping cart and verifywhats next
    When User adds the "Sauce Labs Backpack" to the shopping cart
    Then The shopping cart should contain 1 item
    And The shopping cart should contain "Sauce Labs Backpack" item

  @regression
  Scenario: Add a product to the shopping cart, remove it and verify
    When User adds the "Sauce Labs Backpack" to the shopping cart
    Then The shopping cart should contain 1 item
    And The shopping cart should contain "Sauce Labs Backpack" item
    When User removes "Sauce Labs Backpack" from the shopping cart
    Then The shopping cart should be empty

  @regression
  Scenario: Add multiple products to the shopping cart and verify count
    When User adds the "Sauce Labs Backpack" to the shopping cart
    When User adds the "Sauce Labs Bike Light" to the shopping cart
    Then The shopping cart should contain 2 items
