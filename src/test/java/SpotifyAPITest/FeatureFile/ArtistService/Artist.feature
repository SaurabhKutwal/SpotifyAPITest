

Feature: Validate Artist API services

#######################################################################################################################

  Scenario: Verify that we get an artist's details.
    Given Will search for an artist Shreya Ghoshal
    When Make a Get request for info of artist Shreya Ghoshal
    Then Verify status code is 200
    And Check the information

#######################################################################################################################

  Scenario: Verify that we get an artist's top tracks.
    Given Will search for an artist Shreya Ghoshal
    When Make a Get request for top tracks of artist Shreya Ghoshal
    Then Verify status code is 200
    And Check the list of tracks

#######################################################################################################################

  Scenario: Verify that we get an artist's top albums.
    Given Will search for an artist Shreya Ghoshal
    When Make a Get request for top albums of artist Shreya Ghoshal
    Then Verify status code is 200
    And Check the list of albums

#######################################################################################################################

