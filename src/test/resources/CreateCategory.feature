Feature: user should be able to create a category

  @createCategory
  Scenario: verify user successfully creates a category
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Foods" in request body
    And I have "category_description" with "Foods and drinks" in request body
    And I have "flag" with "true" in request body
    When I send POST request
    Then I retrieve id for "category_id"
    Then verify status code is 201
    Then I delete "Foods" category in database
    Then verify "Foods" is deleted from database using GET request
    Then verify status code is 404
    And verify I have "error" with "Not Found" in response body


  @createCategory1
  Scenario Outline: verify flag accepts only boolean
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "Foods" in request body
    And I have "category_description" with "Foods and drinks" in request body
    And I have "flag" with "<valueOfFlag>" in request body
    When I send POST request
    Then verify status code is 500
    Examples:
      | valueOfFlag |
      | real madrid |
      | falsssse    |
      |             |
      | trueFalse   |
      | 235243      |
      | 4.44        |


  Scenario: verify category_title doesn't accept more than 255 chars
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/categories"
    And I have "category_title" with "QA EngineerQA EngineerQA EngineerQA EngineerQA EngineerQA EngineerQA EngineerQA EnginQA EngineerQA EngineereerQA EngineerQA EngineerQA EngineererQA EngineerQA EngineerQA EngineerQA EngineerQA EngineerQA EngineeQA EngineerQA EQA EngineerQA EngineerngQA EngineerQA" in request body
    And I have "category_description" with "Foods and drinks" in request body
    And I have "flag" with "true" in request body
    When I send POST request
    Then verify status code is 500
