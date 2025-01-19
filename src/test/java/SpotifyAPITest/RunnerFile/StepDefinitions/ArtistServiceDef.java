package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.RunnerFile.StepDefinitions.Hooks.TestHooks;
import SpotifyAPITest.services.ArtistService;
import SpotifyAPITest.services.ServiceManager;
import SpotifyAPITest.services.UsersService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ArtistServiceDef extends ServiceManager {
    String artistId;

    @Given("^Will search for an artist (.+)$")
    public void searchTracksForArtist(String artist) {
        artistId = "0oOet2f43PA68X5RxKobEy";
    }

    @When("^Make a Get request for top (.+) of artist (.+)$")
    public void getTopTracks(String option,String artist) {
        ArtistService artistService = new ArtistService();
        if(option.equals("tracks")) response = artistService.getTopTracksOfArtist(artistId);
        else response = artistService.getTopAlbumsOfArtist(artistId);
    }

    @When("^Make a Get request for info of artist (.+)$")
    public void getArtistInfo(String artist) {
        ArtistService artistService = new ArtistService();
        response = artistService.getArtistDetails(artistId);
    }
    @Then("Check the list of tracks")
    public void checkTopTracks() {
        JsonPath js = new JsonPath(response.asString());
        List<LinkedHashMap> tracks = js.get("tracks");
        tracks.forEach(x-> System.out.println(x.get("name")));
    }

    @Then("Check the information")
    public void checkInfoOfArtist() {
        JsonPath js = new JsonPath(response.asString());
        System.out.println("Artist's Name : " + js.get("name"));
        System.out.println("Followers : " + js.get("followers.total"));
        System.out.println("Popularity : " + js.get("popularity"));
    }

    @Then("Check the list of albums")
    public void checkTopAlbums() {
        JsonPath js = new JsonPath(response.asString());
        List<LinkedHashMap> tracks = js.get("items");
        tracks.forEach(x-> System.out.println(x.get("name")));
    }

}
