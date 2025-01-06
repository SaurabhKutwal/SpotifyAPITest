Feature: Search for track by name and get suggested list.

  Scenario: Test the search functionality for tracks
    Given Song to be searched is Tu Hai kahan by AUR
    When Make a Get request for search track
    Then Verify status code is 200
    And Check the name, release date, artists name
