Feature: Validate the APIs OAuth 2.0 authentication flow.

  Background: Get ready for the test
    Given Load properties File
    Given Load Authentication baseURI


  Scenario: Verify We get Token after using client credentials
    Given A payload with right Authorization code
    When Make a post request for token
    Then Verify status code is 200
    And Check a token and token type

  Scenario: Verify We get Error after using Expired client credentials
    Given A payload with invalid Authorization code
    When Make a post request for token
    Then Verify status code is 400
    And Check error msg


