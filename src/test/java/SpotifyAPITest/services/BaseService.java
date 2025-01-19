package SpotifyAPITest.services;

import SpotifyAPITest.Filter.LogFilter;
import SpotifyAPITest.UtilityPKG.TokenManager;
import groovy.util.logging.Log;
import io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService extends TokenManager {
    private static final String BASE_URI = "https://api.spotify.com";
    protected  RequestSpecification requestSpecification;

    static{
        RestAssured.filters(new LogFilter());
    }

    protected BaseService(){
        RestAssured.baseURI = BASE_URI;
        this.requestSpecification = RestAssured.given();
    }

    protected void setTokenHeader(String token){
        requestSpecification.header("Authorization","Bearer " + token);
    }

    protected void setContentType(String contentType){
        requestSpecification.header("Content-Type",contentType);
    }

    protected void setFormParam(String key, String value){
        requestSpecification.formParam(key,value);
    }

    protected Response getRequest(String endPoint){
        return requestSpecification.when().get(endPoint);
    }

    protected Response postRequest(Object payload,String endPoint){
        return requestSpecification.body(payload).when().post(endPoint);
    }
}
