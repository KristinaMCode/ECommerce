Feature: Sorting Items
  As a user
  I want to sort items on the Sauce Demo website
  So that I can find products based on my preferences

  Scenario: Sort products by name Z to A
    Given I open the Saucedemo home page
    Then User logs in
    Then User is logged in successfully
    When User sorts products by "Name (Z to A)"
    Then First product should be "Test.allTheThings() T-Shirt (Red)"
