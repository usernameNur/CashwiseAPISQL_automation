@regression
Feature: creates a product

  Background:
    Given base url "https://backend.cashwise.us/api/myaccount"

  @createProduct
  Scenario: user successfully creates a product
    And I have access
    And I have the endpoint "/products"
    And I have "product_title" with "pizza" in request body
    And I have "product_price" with "2.99" in request body
    And I have "service_type_id" with "2" in request body
    And I have "category_id" with "2" in request body
    And I have "product_description" with "lasagna plov" in request body
    And I have "date_of_payment" with "2024-05-29" in request body
    And I have "remind_before_day" with "2" in request body
    And I have "do_remind_every_month" with "REPEAT_EVERY_MONTH" in request body
    When I send POST request
    Then verify status code is 201
    And verify I have "product_title" with "pizza" in response body
    Then I delete the product
    Then verify the product is not present in products list







  @createProduct
  Scenario: verify product_title is null if user creates product without giving product_title
    And I have access
    And I have the endpoint "/products"
    And I have "product_price" with "2.99" in request body
    And I have "service_type_id" with "2" in request body
    And I have "category_id" with "2" in request body
    And I have "product_description" with "lasagna plov" in request body
    And I have "date_of_payment" with "2024-05-29" in request body
    And I have "remind_before_day" with "2" in request body
    And I have "do_remind_every_month" with "REPEAT_EVERY_MONTH" in request body
    When I send POST request
    Then verify status code is 201
    And verify I have "product_title" with null in response body
    Then I delete the product



@dbTest1
  Scenario: user successfully creates a product, verify product in DB
    And I have access
    And I have the endpoint "/products"
    And I have "product_title" with "porche" in request body
    And I have "product_price" with "2.99" in request body
    And I have "service_type_id" with "2" in request body
    And I have "category_id" with "2" in request body
    And I have "product_description" with "porche plov" in request body
    And I have "date_of_payment" with "2024-05-29" in request body
    And I have "remind_before_day" with "2" in request body
    And I have "do_remind_every_month" with "REPEAT_EVERY_MONTH" in request body
    When I send POST request
    Then verify status code is 201
    And verify I have "product_title" with "porche" in response body
    And verify "product_title" with "porche" is in database
    Then I delete the product
    Then verify the product is not present in products list
    And verify "product_title" with "porche" is not in database



