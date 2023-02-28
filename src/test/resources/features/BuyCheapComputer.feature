@tag
Feature: Login to HRM Application
  I want to use this template for my feature file

  @Test @Computer @Order
  Scenario: Verify that user can select computer spec and add to cart
    Given User access to the computer detail page
    When User select processor "Medium"
    And User select RAM "4 GB"
    And User select HDD "400 GB"
    And User select Software "Office Suite"
    And User select Add to cart
    And User access to shopping cart
    Then The shopping cart show correct data