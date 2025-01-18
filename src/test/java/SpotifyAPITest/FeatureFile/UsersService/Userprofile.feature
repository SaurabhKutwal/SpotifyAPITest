

Feature: Validate User profile API service

#######################################################################################################################

  @smoke @Regression
  Scenario: Verify that We get User Profile Details for valid token request
    Given Make a RequestSpecification with right authorization token
    When Make a Get request for profile
    Then Verify status code is 200
    And Check Name And Id

#######################################################################################################################

  Scenario: Verify that We get error msg for invalid token request
    Given Make a RequestSpecification with wrong authorization token
    When Make a Get request for profile
    Then Verify status code is 401
    And error msg should be Invalid access token

#######################################################################################################################