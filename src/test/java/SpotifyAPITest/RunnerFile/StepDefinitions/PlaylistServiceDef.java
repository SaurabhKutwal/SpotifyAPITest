package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.POJOClass.Request.PlaylistAddItemRequest;
import SpotifyAPITest.POJOClass.Request.PlaylistCreateRequest;
import SpotifyAPITest.services.PlaylistService;
import SpotifyAPITest.services.ServiceManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlaylistServiceDef extends ServiceManager {
    static String userId;
    static String playlistName;
    static String description;
    static String publicStatus;
    static PlaylistCreateRequest playlistCreateRequest;
    static PlaylistAddItemRequest playlistAddItemRequest;
    static String playListId = "32Ev7NeW8dnpXV1AblzXJG";

    @Given("^User wants playlist with name (.+) & it is (.+)$")
    public void playlistDetails(String name,String publicStatus) {
        userId = "31d3z46e5hjtn43jnoxiimia3lna";
        PlaylistServiceDef.playlistName = name;
        PlaylistServiceDef.publicStatus = publicStatus;
    }

    @Given("^Description of playlist is (.+).$")
    public void playlistDetails(String description) {
        PlaylistServiceDef.description = description;

        PlaylistServiceDef.playlistCreateRequest = new PlaylistCreateRequest.Builder()
                .playlistName(PlaylistServiceDef.playlistName)
                .description(PlaylistServiceDef.description)
                .publicStatus(PlaylistServiceDef.publicStatus)
                .build();
    }

    @When("Make a Post request for playlist creation")
    public void makePostRequestCreatePlaylist() {
        PlaylistService playlistService = new PlaylistService();
        response = playlistService.createPlaylist(PlaylistServiceDef.playlistCreateRequest,PlaylistServiceDef.userId);
    }

    @Then("Check the name, id of playlist")
    public void verifyResultOfPlaylist() {
        String result = response.then().extract().asString();
        JsonPath js = new JsonPath(result);
        System.out.println("Name of playlist is :" + js.getString("name"));
        System.out.println("Id of playList is :" + js.getString("id"));
    }

    @Given("List of songs that will be added")
    public void listOfSongsAdded() {
        PlaylistServiceDef.playlistAddItemRequest = new PlaylistAddItemRequest.Builder()
                .songs(List.of("spotify:track:3zSSCPpLZ5Oc8nelhhGjKz","spotify:track:5npFBWhVs3VVlc0nY5xQMA"
                        ,"spotify:track:1hA697u7e1jX2XM8sWA6Uy","spotify:track:5AnWrUzsrFgBvhn6dUElnr","spotify:track:2ceeTJAzKy295Fm0VsaXtE"))
                .build();
    }

    @When("Make a Post request to add track to playlist")
    public void makePostRequestAddTracks() {
        PlaylistService playlistService = new PlaylistService();
        response = playlistService.addItemsToPlaylist(PlaylistServiceDef.playlistAddItemRequest,PlaylistServiceDef.playListId);
    }

    @Then("Get the snapshot id")
    public void getTheSnapshotId() {
        System.out.println(response.then().extract().asString());;
    }

    @Given("PlayList name is ACE123")
    public void playlistId() {
        //login to get Id of playlist
    }

    @When("Make a Get request to get list of tracks")
    public void makeGetRequestForTracks() {
        PlaylistService playlistService = new PlaylistService();
        response = playlistService.getPlaylistTracks(PlaylistServiceDef.playListId);
    }

    @Then("Verify the tracks")
    public void verifyTheTracks() {
        System.out.println("Playlist songs are....");
        JsonPath js = new JsonPath(response.then().extract().asString());
        List<LinkedHashMap<Object,Object>> trackList = js.getList("items");
        trackList.forEach(x->System.out.println(((LinkedHashMap)x.get("track")).get("name")));
    }



//
//    @Given("PLayList name is ACE")
//    public void p_lay_list_name_is_ace() {
//        request = given().header("Authorization","Bearer " + properties.getProperty("accessToken"));
//    }
//    @When("Make a Get request to get list of tracks")
//    public void make_a_get_request_to_get_list_of_tracks() {
//        response = request.when().get("/v1/playlists/"+properties.getProperty("playListID")+"/tracks");
//    }
//    @Then("verify the tracks")
//    public void verify_the_tracks() {
//        System.out.println("Playlist songs are....");
//        JsonPath js = new JsonPath(response.then().extract().asString());
//        List<LinkedHashMap<Object,Object>> trackList = js.getList("items");
//        trackList.forEach(x->System.out.println(((LinkedHashMap)x.get("track")).get("name")));
//    }
}
