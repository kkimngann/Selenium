@tag
Feature: Order a computer

  @Test @OrderComputer @CheapComputer
  Scenario: Verify that user can select computer spec and add to cart
    Given User access to the computer detail page
    When User select processor "Slow" and RAM "4 GB" and software "Office Suite"
    Then Verify item in cart correct data

