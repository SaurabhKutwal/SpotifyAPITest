Feature: Testing playlist functionality of spotify API

  Background: Get ready for the test
    Given Load API baseURI


#Create a Playlist
  Scenario: Verify playlist creation for a user.
    Given Playlist name is ACE Playlist, description is my playlist with my liked songs & private playlist
    When Make a Post request for create playlist
    Then Verify status code is 201
    And Check the name, id of playlist

#Add Tracks to a Playlist
  Scenario: Add Tracks to a Playlist
    Given List of songs that will be added
    When Make a Post request to add track to playlist
    Then Verify status code is 201
    And Get the snapshot id

#Get Playlist Tracks
  Scenario: Verify retrieval of tracks in a playlist.
    Given PLayList name is ACE
    When Make a Get request to get list of tracks
    Then Verify status code is 200
    And verify the tracks


