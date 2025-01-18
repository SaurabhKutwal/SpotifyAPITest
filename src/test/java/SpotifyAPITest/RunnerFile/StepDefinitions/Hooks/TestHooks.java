package SpotifyAPITest.RunnerFile.StepDefinitions.Hooks;

import SpotifyAPITest.UtilityPKG.TestManager;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class TestHooks{

    public void checkTokenExpiry() throws IOException {
        System.out.println("In Hook");
        Properties properties = TestManager.loadProperties();

        //check current token validity by fetching user profile
        RestAssured.baseURI = properties.getProperty("APIBaseURI");

        String accessToken = "Bearer " + properties.getProperty("accessToken");
        Response response1 = given().header("Authorization",accessToken)
                .get("/v1/me");

        if(response1.then().extract().statusCode() == 401){
            RestAssured.baseURI = properties.getProperty("AuthBaseURI");

            String authorization = "Basic " + properties.getProperty("Base64_token");

            Response response = given().header("Authorization",authorization)
                    .header("Content-Type","application/x-www-form-urlencoded")
                    .formParam("grant_type","refresh_token")
                    .formParam("refresh_token",properties.getProperty("refreshToken"))
                    .formParam("client_id",properties.getProperty("client_Id"))
                    .when().post("/api/token");

            String resp = response.then().assertThat().statusCode(200).extract().asString();

            JsonPath js = new JsonPath(resp);
            String refreshToken = js.getString("refresh_token");
            if(refreshToken == null){
                refreshToken = properties.getProperty("refreshToken");
            }
            TestManager.updateToken(js.get("access_token"),refreshToken);
            System.out.println("Got token successfully");
        }
    }
}
