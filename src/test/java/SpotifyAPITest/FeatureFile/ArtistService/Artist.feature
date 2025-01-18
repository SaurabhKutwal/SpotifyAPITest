

Feature: Validate Artist API service

#######################################################################################################################

  Scenario: Verify that we get an artist's top tracks.
    Given Will search for top tracks of artist Shreya Ghoshal
    When Make a Get request for top tracks of artist Shreya Ghoshal
    Then Verify status code is 200
    And Check the list of tracks

#######################################################################################################################
