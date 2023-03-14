@tag
Feature: Registration

  Background:
    Given Access the test website

  @Test @Registration @Invalid
  Scenario Outline: Verify that user can not register with invalid data
    When User go to register screen
    And User input data "<gender>", "<firstname>", "<lastname>", "<email>", "<password>", "<confirmPassword>"
    And User select register
    Then Error message "<messageType>" invalid show correctly "<message>"
    Examples:
    |gender|firstname|lastname|email|password|confirmPassword|messageType|message|
    |Male  |         |Ngan    |kkimngann.jk@gmail.com|12345678|12345678|FirstName|* First name is required.|