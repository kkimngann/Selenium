@tag
Feature: Authorization

  @Test @Login @Authorize @Invalid
  Scenario: Verify that user can not login with invalid account
    When User go to login screen
    And User input email ""
    And User input password ""
    And User select button login
    Then Error message "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found" show correctly

  @Test @Login @Authorize @Valid
  Scenario: Verify that user can login with valid account
    When User go to login screen
    And User input email ""
    And User input password ""
    And User select button login
    Then The home screen show correctly
    And Show correct user email ""
    And Show button log out correctly

