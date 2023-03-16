@tag
Feature: Registration

  Background:
    Given Access the test website

  @Test @Registration @Invalid
  Scenario Outline: Verify that user can not register with invalid data "<gender>", "<firstname>", "<lastname>", "<email>", "<password>", "<confirmPassword>"
    When User go to register screen
    And User input data "<gender>", "<firstname>", "<lastname>", "<email>", "<password>", "<confirmPassword>"
    And User select register
    Then Error message "<messageType>" invalid show correctly "<message>"
    Examples:
    |gender|firstname|lastname|email|password|confirmPassword|messageType|message|
    |Female  |         |Ngan    |kkimngann.jk@gmail.com|12345678|12345678|FirstName|First name is required.|
    |Male  | Ngan    |    |kkimngann.jk@gmail.com|12345678|12345678|LastName|Last name is required.|