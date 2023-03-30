@tag
Feature: Test footer show correctly in website
  Background:
    Given Access the test website
  @Test @Footer
  Scenario: Verify that footer show in screen correctly
    When User go to menu "BOOKS"
    Then Verify that footer shown correctly
    When User go to menu "COMPUTERS"
    Then Verify that footer shown correctly
    When User go to menu "ELECTRICS"
    Then Verify that footer shown correctly
    When User go to menu "APPARELS & SHOES"
    Then Verify that footer shown correctly
    When User go to menu "DIGITAL DOWNLOADS"
    Then Verify that footer shown correctly
    When User go to menu "JEWELRY"
    Then Verify that footer shown correctly
    When User go to menu "GIFT CARDS"
    Then Verify that footer shown correctly

