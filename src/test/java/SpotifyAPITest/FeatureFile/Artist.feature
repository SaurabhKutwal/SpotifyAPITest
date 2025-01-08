Feature: Testing functions on Artists endpoint

  Background: Get ready for the test
    Given Load API baseURI


  Scenario: Verify fetching an artist's top tracks.
    Given Search Artist Shreya Ghoshal
    When Make a Get request for top tracks for artist
    Then Verify status code is 200
    And Get the name list of tracks