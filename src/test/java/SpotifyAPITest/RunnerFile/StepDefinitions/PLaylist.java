package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.POJOClass.PLayList.CreatePlayList;
import SpotifyAPITest.UtilityPKG.TestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;

public class PLaylist extends TestManager {
    CreatePlayList pLaylist ;
    String tracks;

    @Given("^Playlist name is (.+) Playlist, description is (.+) & (.+) playlist$")
    public void createPLaylist(String name, String description, String publicStatus) {
        pLaylist = TestManager.createPlayList(name,description,publicStatus);
        request = given().header("Authorization","Bearer " + properties.getProperty("accessToken"))
                .header("Content-Type","application/json")
                .body(pLaylist);
    }

    @When("Make a Post request for create playlist")
    public void makePostRequestCreatePlaylist() {
        response = request.when().post("/v1/users/"+properties.getProperty("userID")+"/playlists");
    }
    @Then("Check the name, id of playlist")
    public void verifyResultOfPlaylist() {
        String result = response.then().extract().asString();
        JsonPath js = new JsonPath(result);
        System.out.println("Name of playlist is :" + js.getString("name"));
        System.out.println("Id of playList is :" + js.getString("id"));
    }

    @Given("List of songs that will be added")
    public void list_of_songs_that_will_be_added() {
        tracks = "\"spotify:track:3zSSCPpLZ5Oc8nelhhGjKz\"" + "," +
                "\"spotify:track:5npFBWhVs3VVlc0nY5xQMA\"" + "," +
                "\"spotify:track:1hA697u7e1jX2XM8sWA6Uy\"" + "," +
                "\"spotify:track:5AnWrUzsrFgBvhn6dUElnr\"" + "," +
                "\"spotify:track:2ceeTJAzKy295Fm0VsaXtE\"";

        request = given().header("Authorization","Bearer " + properties.getProperty("accessToken"))
                .header("Content-Type","application/json")
                .body("{\n" +
                        "    \"uris\": [\n" +
                        "        "+tracks+"\n" +
                        "    ],\n" +
                        "    \"position\": 0\n" +
                        "}");
    }
    @When("Make a Post request to add track to playlist")
    public void make_a_post_request_to_add_track_to_playlist() {
        response = request.when().post("/v1/playlists/"+properties.getProperty("playListID")+"/tracks");
    }
    @Then("Get the snapshot id")
    public void get_the_snapshot_id() {
        System.out.println(response.then().extract().asString());;
    }

    @Given("PLayList name is ACE")
    public void p_lay_list_name_is_ace() {
        request = given().header("Authorization","Bearer " + properties.getProperty("accessToken"));
    }
    @When("Make a Get request to get list of tracks")
    public void make_a_get_request_to_get_list_of_tracks() {
        response = request.when().get("/v1/playlists/"+properties.getProperty("playListID")+"/tracks");
    }
    @Then("verify the tracks")
    public void verify_the_tracks() {
        System.out.println("Playlist songs are....");
        JsonPath js = new JsonPath(response.then().extract().asString());
        List<LinkedHashMap<Object,Object>> trackList = js.getList("items");
        trackList.forEach(x->System.out.println(((LinkedHashMap)x.get("track")).get("name")));
    }
}
