package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.UtilityPKG.TestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static io.restassured.RestAssured.given;

public class SearchItem extends TestManager {
    RequestSpecification request;

    @Given("^Song to be searched is (.+) by (.+)$")
    public void searchSong(String track, String artist) {
        request = given().header("Authorization","Bearer " + properties.getProperty("accessToken"))
                .queryParam("q",track)
                .queryParam("type","track")
                .queryParam("market","IN")
                .queryParam("limit",5)
                .queryParam("include_external","audio");
    }
    @When("Make a Get request for search track")
    public void searchRequest() {
        response = request.when().get("/v1/search");
    }
    @Then("Check the name, release date, artists name")
    public void searchResult() {
        System.out.println("List of songs suggested.");
        JsonPath js = new JsonPath(response.then().extract().asString());
        List<LinkedHashMap<Object,Object>> trackList = js.getList("tracks.items");
        AtomicInteger t = new AtomicInteger(1);
        trackList.forEach(x-> {
            System.out.println(t + ". Song name is : " + x.get("name"));
            System.out.println("Artist name is " + ((List<LinkedHashMap>)x.get("artists")).get(0).get("name"));
            t.getAndIncrement();
        });

    }

}
