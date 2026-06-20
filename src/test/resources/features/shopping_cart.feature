Feature: Shopping cart
  As a user
  I want to add products to the shopping cart
  So that I can review my selections before checkout

  Background:
    Given I open the Saucedemo home page
    When User logs in
    Then User is logged in successfully

  @smoke
  Scenario: Add a product to the shopping cart and verify cart contents
    When User adds the "Sauce Labs Backpack" to the shopping cart
    Then The shopping cart should contain 1 item
    And The shopping cart should contain "Sauce Labs Backpack" item

  @regression
  Scenario: Add a product to the shopping cart, remove it and verify
    When User adds the "Sauce Labs Backpack" to the shopping cart
    Then The shopping cart should contain 1 item
    And The shopping cart should contain "Sauce Labs Backpack" item
    When User removes item "Sauce Labs Backpack" from the shopping cart
    Then The shopping cart should be empty

  @regression
  Scenario: Add multiple items, verify all are present
    When User adds the "Sauce Labs Backpack" to the shopping cart
    When User adds the "Sauce Labs Bike Light" to the shopping cart
    When User adds the "Sauce Labs Bolt T-Shirt" to the shopping cart
    Then The shopping cart should contain 3 items
    And The shopping cart should contain the following items:
      | Sauce Labs Backpack     |
      | Sauce Labs Bike Light   |
      | Sauce Labs Bolt T-Shirt |

  @regression
  Scenario: Remove one item out of several
    When User adds the "Sauce Labs Backpack" to the shopping cart
    When User adds the "Sauce Labs Bike Light" to the shopping cart
    When User adds the "Sauce Labs Bolt T-Shirt" to the shopping cart
    Then The shopping cart should contain 3 items
    When User removes item "Sauce Labs Bolt T-Shirt" from the shopping cart
    Then The shopping cart should contain 2 items
    And The shopping cart should contain the following items:
      | Sauce Labs Backpack   |
      | Sauce Labs Bike Light |
    And The shopping cart should not contain "Sauce Labs Bolt T-Shirt" item

  @regression
  Scenario: Remove all items one by one
    When User adds the "Sauce Labs Fleece Jacket" to the shopping cart
    When User adds the "Sauce Labs Onesie" to the shopping cart
    When User adds the "Test.allTheThings() T-Shirt (Red)" to the shopping cart
    Then The shopping cart should contain 3 items
    When User removes item "Sauce Labs Fleece Jacket" from the shopping cart
    When User removes item "Sauce Labs Onesie" from the shopping cart
    When User removes item "Test.allTheThings() T-Shirt (Red)" from the shopping cart
    And The shopping cart should be empty

  @regression
  Scenario:Remove then re-add same item, no duplicate
    When User adds the "Sauce Labs Onesie" to the shopping cart
    Then The shopping cart should contain 1 items
    When User removes item "Sauce Labs Onesie" from the shopping cart
    And The shopping cart should be empty
    When User adds the "Sauce Labs Onesie" to the shopping cart
    Then The shopping cart should contain 1 items
    And The shopping cart should contain the following items:
      | Sauce Labs Onesie |




