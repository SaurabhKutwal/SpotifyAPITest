package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.UtilityPKG.TestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;

import java.util.LinkedHashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class Artist extends TestManager {


    @Given("Search Artist Shreya Ghoshal")
    public void search_artist_shreya_ghoshal() {
        request = given().header("Authorization","Bearer " + properties.getProperty("accessToken"));
    }
    @When("Make a Get request for top tracks for artist")
    public void make_a_get_request_for_top_tracks_for_artist() {
        response  = request.when().get("/v1/artists/"+properties.getProperty("shreyaGhoshalID")+"/top-tracks");
    }
    @Then("Get the name list of tracks")
    public void get_the_name_list_of_tracks() {
        String resp = response.then().extract().asString();
        JsonPath js = new JsonPath(resp);
        List<LinkedHashMap> tracks = js.get("tracks");
        tracks.forEach(x-> System.out.println(x.get("name")));
    }
}
