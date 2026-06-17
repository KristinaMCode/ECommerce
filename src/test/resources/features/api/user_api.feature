Feature: JSONPlaceholder User API
  As a tester
  I want to validate the JSONPlaceholder API endpoints
  So that I can ensure the API behaves correctly

  @api
  Scenario: Get list of users  - returns 200
    When I send GET request to "/users"
    Then Response status code should be 200
    And Response should contain a list of users

  @api
  Scenario: Get single user returns correct data
    When I send GET request to "/users/2"
    Then Response status code should be 200
    And Response body should match single user schema
    And Response user email should be "Shanna@melissa.tv"

  @api
  Scenario: Create a new user
    When I send POST request to "/posts" with title "Unknown" and body "Unknown Engineer" and userId 1
    Then Response status code should be 201
    And Response should contain title "Unknown" and body "Unknown Engineer"

  @api
  Scenario: Update a user
    When I send PUT request to "/users/2" with title "Unknown" and body "Senior QA" and userId 1
    Then Response status code should be 200
    And Response should contain title "Unknown" and body "Senior QA"

  @api
  Scenario: Delete a user
    When I send DELETE request to "/users/2"
    Then Response status code should be 200

  @api
  Scenario: Get non-existent user returns 404
    When I send GET request to "/users/999"
    Then Response status code should be 404


