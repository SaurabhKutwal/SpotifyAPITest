package SpotifyAPITest.RunnerFile.StepDefinitions;

import SpotifyAPITest.POJOClass.RespAuthenticationSuc;
import SpotifyAPITest.POJOClass.ResponseAuthorizationFail;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;


public class AuthenticationDef {
    RequestSpecification request;
    Properties properties;
    Response response;

    @Given("Load properties File")
    public void load() throws IOException {
        FileInputStream file = new FileInputStream("/Users/saurabh/Desktop/JavaSelenium/SpotifyAPITest/src/test/java/SpotifyAPITest/Properties/UserDetails.properties");
        properties = new Properties();
        properties.load(file);

        RestAssured.baseURI = properties.getProperty("baseURI");
    }
    @Given("^A payload with (.+) Authorization code $")
    public void getAPayloadForGettingAPIToken(String isIDValid) {
        String authCode = isIDValid.equals("right")?properties.getProperty("URL_Code"):"randomsString";
        String authorization = "Basic " + properties.getProperty("Base64_token");
        request = given().log().all().header("Authorization",authorization)
                .header("Content-Type","application/x-www-form-urlencoded")
                .formParam("grant_type","authorization_code")
                .formParam("code",authCode)
                .formParam("redirect_uri",properties.getProperty("redirect_uri"));
    }

    @When("Make a post request for token")
    public void makeAPostRequestForToken() {
        response = request.when().post("/api/token");
    }

    @Then("^Verify status code is (.+) $")
    public void verifyStatusCodeIs(int statusCode) {
        response.then().log().all().assertThat().statusCode(statusCode);
    }

    @And("Check a token and token type")
    public void checkATokenAndTokenType() {
        RespAuthenticationSuc authSuc = response.then().extract().as(RespAuthenticationSuc.class);
        System.out.println(authSuc.getToken_type());
        System.out.println(authSuc.getAccess_token());
    }
    @And("Check errror msg")
    public void errorMsg(){
        ResponseAuthorizationFail authFail = response.then().extract().as(ResponseAuthorizationFail.class);
        System.out.println(authFail.getError());
        System.out.println(authFail.getError_description());
    }
}
