package SpotifyAPITest.services;

import io.restassured.response.Response;


public class UsersService extends BaseService{
    private final String BASE_PATH = "/v1/me";


    public Response getCurrentUserProfile(String token){
        setTokenHeader(token);
        return getRequest(BASE_PATH);
    }

    public Response getUserTopItems(String token,String item){
        setTokenHeader(token);
        requestSpecification.queryParam("limit",5);
        requestSpecification.pathParam("type",item);
        return getRequest(BASE_PATH + "/top/{type}");
    }
}
