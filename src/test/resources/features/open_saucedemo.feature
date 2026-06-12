Feature: Open Sauce Demo
  As a user
  I want to open the Sauce Demo website
  So that I can access the products and make purchases

  Scenario: Open  page and verify URL and title
    Given I open the Saucedemo home page
    Then The URL should be "https://www.saucedemo.com/"
    And The page title should contain "Swag Labs"

  Scenario: Login to Sauce Demo
    Given I open the Saucedemo home page
    Then The URL should be "https://www.saucedemo.com/"
    When User logs in
    Then User is logged in successfully

  Scenario: Login with invalid credentials from CSV
    Given I open the Saucedemo home page
    Then The URL should be "https://www.saucedemo.com/"
    When I login with CSV file "invalid_users.csv"
    Then All users should match expected outcome

