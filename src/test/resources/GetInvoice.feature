@regression
Feature: end to end scenario to validate invoice on API and DB

  @getInvoices
  Scenario: verify invoice details are displayed accurately
    Given base url "https://backend.cashwise.us/api/myaccount"
    And I have access
    And I have the endpoint "/invoices/426"
    And I have "page" with "1" as a query param
    And I have "size" with "10" as a query param
    And I send GET request
    And I retrieve id for "invoice_id"
    And verify status code is 200
    Then verify I have "invoice_title" with "mmmm" in response body
    And verify the invoice details in the response match the database