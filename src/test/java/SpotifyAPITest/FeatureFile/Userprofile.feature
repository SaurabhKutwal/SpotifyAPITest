Feature: Validate User profile through API

  Background: Get ready for the test
    Given Load properties File
    Given Load API baseURI

  Scenario: Verify that We get User Profile Details
    Given A payload with right access token
    When Make a Get request for profile
    Then Verify status code is 200
    And Check Name And Id

  Scenario: Verify that We get error msg for invalid token
    Given A payload with "wrong" access token
    When Make a Get request for profile
    Then Verify status code is 401
    And error msg should be Invalid access token