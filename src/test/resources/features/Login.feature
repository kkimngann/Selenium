@tag
Feature: Authorization

  @Test @Login @Authorize @Invalid
  Scenario: Verify that user can not login with invalid account
    When User go to login screen
    And User input email "ngan.nguyen.thi.kim.2005@gmail.com" and password "12345678" and submit
    Then Error message show correctly
  """
  Login was unsuccessful. Please correct the errors and try again.
  No customer account found
  """


  @Test @Login @Authorize @Valid
  Scenario: Verify that user can login with valid account
    When User go to login screen
    And User input email "kkimngann.jk+1@gmail.com" and password "12345678" and submit
    Then Show correct user email "kkimngann.jk+1@gmail.com" after login
    And Show button log out correctly

