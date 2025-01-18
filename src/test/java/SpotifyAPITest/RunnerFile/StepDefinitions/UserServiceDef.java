package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.services.ServiceManager;
import SpotifyAPITest.services.UsersService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.LinkedHashMap;
import java.util.List;


public class UserServiceDef extends ServiceManager {
    String token;

    @Given("^Make a RequestSpecification with (.+) authorization token$")
    public void addAuthorizationHeader(String isCodeValid) {
        token = isCodeValid.equals("right")?"BQD09YNcHsMeJ4Z2UYXMN41r-t_gqD5K-aZZBH6i7XFQCh11xNMxwRvUr1w92jM2gHNN0YZz4khxzvRx7yYN9ZZlFKJ_5PWf6SEb2EZC3Ufa4ecz8RMIHfgLdAYRRtQPdCKXF1oWdsBnfQsGxn48ZIxGm2m0svtnzPTrKcChvB6OyfcYNtvRyomipvd0QCyaHMlu1IVabPmGK2XI9RZmTv1NE307b1My":"randomsString";
    }

    @When("Make a Get request for profile")
    public void getUserProfileRequest() {
        UsersService usersService = new UsersService();
        response = usersService.getCurrentUserProfile(token);
    }

    @When("^Make a Get request for user top (.+)$")
    public void getUserProfileRequest(String option) {
        UsersService usersService = new UsersService();
        response = usersService.getUserTopItems(token,option);
    }

    @Then("Check Name And Id")
    public void checkErrorMsg() {
        JsonPath js = new JsonPath(response.then().extract().asString());
        System.out.println("Name : " + js.getString("display_name"));
        System.out.println("ID : " + js.getString("id"));
    }

    @Then("^Verify status code is (.+)$")
    public void verifyStatusCodeIs(int statusCode) {
        Assert.assertEquals(response.getStatusCode(),statusCode);
    }

    @Then("^error msg should be (.+)$")
    public void verifyErrorMsg(String errorMsg) {
        JsonPath js = new JsonPath(response.then().extract().asString());
        Assert.assertEquals(js.getString("error.message"),errorMsg);
        System.out.println("Error : " + js.getString("error.message"));
        System.out.println("status : " + js.getString("error.status"));
    }

    @Then("^Check top (.+)$")
    public void checkItems(String option) {
        System.out.println("Users top "+option+" are:");
        JsonPath js = new JsonPath(response.then().extract().asString());
        List<LinkedHashMap> items = js.getList("items");
        items.forEach(x -> System.out.println(x.get("name")));
    }
}
