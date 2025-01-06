package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.POJOClass.RespAuthenticationSuc;
import SpotifyAPITest.POJOClass.ResponseAuthorizationFail;
import SpotifyAPITest.UtilityPKG.TestManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import static io.restassured.RestAssured.given;


public class AuthenticationDef extends TestManager {
    RequestSpecification request;

    @Given("Load properties File")
    public void load() throws IOException {
        properties = TestManager.loadProperties();
        RestAssured.baseURI = properties.getProperty("baseURI");
    }

    @Given("Load Authentication baseURI")
    public void loadAuthAPI(){
        RestAssured.baseURI = properties.getProperty("AuthBaseURI");
    }
    @Given("^A payload with (.+) Authorization code$")
    public void getAPayloadForGettingAPIToken(String isIDValid) {
        String authCode = isIDValid.equals("right")?properties.getProperty("URL_Code"):"randomsString";
        String authorization = "Basic " + properties.getProperty("Base64_token");
        request = given().header("Authorization",authorization)
                .header("Content-Type","application/x-www-form-urlencoded")
                .formParam("grant_type","authorization_code")
                .formParam("code",authCode)
                .formParam("redirect_uri",properties.getProperty("redirect_uri"));
    }

    @When("Make a post request for token")
    public void makeAPostRequestForToken() {
        response = request.when().post("/api/token");
    }

    @Then("^Verify status code is (.+)$")
    public void verifyStatusCodeIs(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @And("Check a token and token type")
    public void checkATokenAndTokenType() throws IOException {
        RespAuthenticationSuc authSuc = response.then().extract().as(RespAuthenticationSuc.class);
        updateToken(authSuc.getAccess_token(),authSuc.getRefresh_token());
        System.out.println("Got token successfully");
    }

    @And("Check error msg")
    public void errorMsg(){
        ResponseAuthorizationFail authFail = response.then().extract().as(ResponseAuthorizationFail.class);
        System.out.println("Error is : " + authFail.getError());
        System.out.println("Error Description is : " + authFail.getError_description());
    }
}
