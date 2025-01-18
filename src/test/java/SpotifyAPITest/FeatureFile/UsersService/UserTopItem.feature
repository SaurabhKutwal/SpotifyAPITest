

Feature: Validate User's Top tracks/artists API service

#######################################################################################################################

  Scenario: Verify that We get user's top tracks
    Given Make a RequestSpecification with right authorization token
    When Make a Get request for user top tracks
    Then Verify status code is 200
    And Check top tracks


#######################################################################################################################

  Scenario: Verify that We get user's top artist
    Given Make a RequestSpecification with right authorization token
    When Make a Get request for user top artists
    Then Verify status code is 200
    And Check top artists