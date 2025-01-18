package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.UtilityPKG.TestManager;
import SpotifyAPITest.services.ArtistService;
import SpotifyAPITest.services.ServiceManager;
import SpotifyAPITest.services.UsersService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ArtistServiceDef extends ServiceManager {
    String artistId;

    @Given("^Will search for top tracks of artist (.+)$")
    public void searchTracksForArtist(String artist) {
        artistId = "0oOet2f43PA68X5RxKobEy";
    }
    @When("^Make a Get request for top tracks of artist (.+)$")
    public void getTopTracks(String artist) {
        ArtistService artistService = new ArtistService();
        response = artistService.getTopTracksOfArtist(artistId);
    }
    @Then("Check the list of tracks")
    public void checkTopTracks() {
        String resp = response.then().extract().asString();
        JsonPath js = new JsonPath(resp);
        List<LinkedHashMap> tracks = js.get("tracks");
        tracks.forEach(x-> System.out.println(x.get("name")));
    }
}
