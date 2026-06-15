Feature: Checkout flow
  As a user
  I want to add products to the shopping cart, remove them and verify the cart contents
  So that I can manage my shopping cart effectively

  Background:
    Given I open the Saucedemo home page
    When User logs in
    Then User is logged in successfully

  @regression
  Scenario: Add a product to the shopping cart, proceed to checkout and verify the checkout process
    When User adds the "Sauce Labs Backpack" to the shopping cart
    Then The shopping cart should contain 1 item
    And The shopping cart should contain "Sauce Labs Backpack" item
    When User proceeds to checkout
    Then The checkout page should be displayed
    When User enters checkout information with first name "John", last name "Doe", and postal code "12345"
    Then The checkout overview page should be displayed
    And The checkout overview page should contain "Sauce Labs Backpack" item
    When User completes the checkout process
    Then The checkout complete page should be displayed
