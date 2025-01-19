Feature: Validate Playlist API services

#######################################################################################################################

  Scenario: Verify playlist creation for a user.
    Given User wants playlist with name ACE123 & it is private
    And Description of playlist is My Liked Songs.
    When Make a Post request for playlist creation
    Then Verify status code is 201
    And Check the name, id of playlist

#######################################################################################################################

  Scenario: Verify we can add tracks to plylist
    Given List of songs that will be added
    When Make a Post request to add track to playlist
    Then Verify status code is 201
    And Get the snapshot id

#######################################################################################################################

  Scenario: Verify retrieval of tracks in a playlist.
    Given PlayList name is ACE123
    When Make a Get request to get list of tracks
    Then Verify status code is 200
    And Verify the tracks




