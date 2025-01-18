package SpotifyAPITest.services;

import io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService{
    private static final String BASE_URI = "https://api.spotify.com";
    protected  RequestSpecification requestSpecification;

    protected BaseService(){
        RestAssured.baseURI = BASE_URI;
        this.requestSpecification = RestAssured.given();
    }

    protected void setTokenHeader(String token){
        requestSpecification.header("Authorization","Bearer " + token);
    }

    protected Response getRequest(String endPoint){
        return requestSpecification.when().get(endPoint);
    }
}
