package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.UtilityPKG.TestManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserProfile extends TestManager {
    RequestSpecification request;

    @Given("Load API baseURI")
    public void loadAuthAPI(){
        RestAssured.baseURI = properties.getProperty("APIBaseURI");
    }
    @Given("^A payload with (.+) access token$")
    public void a_payload_with_access_code(String isCodeValid) {
        String accessCode = isCodeValid.equals("right")?accessToken:"randomsString";
        String authorization = "Bearer " + accessCode;
        request = given().header("Authorization",authorization);
    }
    @When("Make a Get request for profile")
    public void make_a_get_request_for_profile() {
        response = request.get("/v1/me");
    }
    @Then("Check Name And Id")
    public void check_name_and_id() {
        JsonPath js = new JsonPath(response.then().extract().asString());
        System.out.println("Name : " + js.getString("display_name"));
        System.out.println("ID : " + js.getString("id"));
    }

    @Then("^error msg should be (.+)$")
    public void error_msg_should_be(String string) {
        JsonPath js = new JsonPath(response.then().extract().asString());
        System.out.println("Error : " + js.getString("error.message"));
        System.out.println("status : " + js.getString("error.status"));

    }

}
