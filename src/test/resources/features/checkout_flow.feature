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

  @regression
  Scenario: Add multiple items, verify checkout overview list contains all items
    When User adds the "Sauce Labs Backpack" to the shopping cart
    When User adds the "Sauce Labs Bike Light" to the shopping cart
    When User adds the "Sauce Labs Bolt T-Shirt" to the shopping cart
    Then The shopping cart should contain 3 items
    And The shopping cart should contain the following items:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    When User proceeds to checkout
    Then The checkout page should be displayed
    When User enters checkout information with first name "John", last name "Doe", and postal code "12345"
    Then The checkout overview page should be displayed
    And The checkout overview page should contain the following items:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

  @regression
  Scenario: Add multiple items, and verify price is correctly calculated
    When User adds the "Sauce Labs Backpack" to the shopping cart
    When User adds the "Sauce Labs Bike Light" to the shopping cart
    When User adds the "Sauce Labs Bolt T-Shirt" to the shopping cart
    Then The shopping cart should contain 3 items
    And The shopping cart should contain the following items:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |
    And And User calculates the total price of items in the cart
    When User proceeds to checkout
    Then The checkout page should be displayed
    When User enters checkout information with first name "John", last name "Doe", and postal code "12345"
    Then The checkout total should match the cart price total