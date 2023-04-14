@tag
Feature: Order a computer
  Background:
    Given Access the test website

  @Test @OrderComputer @CheapComputer @GuestCheckout
  Scenario: Verify that user can select computer spec and add to cart
    Given User access to the computer detail page
    When User select processor "Slow" and RAM "4 GB" and software "Office Suite"
    Then Verify item in cart correct data
    When User check agree term and condition
    And User select Checkout
    And User select Check out as a guest
    And User input data delivery address and confirm
    Then Show correct screen confirm Order
    When User select continue confirm order
    Then Show screen Order completed correct
    When User select to view order detail
    Then Show screen other detail correct


