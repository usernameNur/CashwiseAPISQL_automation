@regression
Feature: verify user can update a category

  Scenario: verify user successfully updates category_title
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Foods" in request body
    And I have "category_description" with "Foods and drinks" in request body
    And I have "flag" with "true" in request body
    When I send POST request
    And I retrieve id for "category_id"
    Then verify status code is 201
    And I have "category_title" with "Cosmetics" in request body
    When I send PUT request
    Then verify status code is 200
    Then verify I have "category_title" with "Cosmetics" in response body

