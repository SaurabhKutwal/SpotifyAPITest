package SpotifyAPITest.services;

import io.restassured.response.Response;

public class ArtistService extends BaseService{
    private final String BASE_PATH = "/v1/artists/{id}";

    public Response getArtistDetails(String artistId){
        requestSpecification.pathParam("id",artistId);
        setTokenHeader(token);
        return getRequest(BASE_PATH);
    }
    public Response getTopTracksOfArtist(String artistId){
        requestSpecification.pathParam("id",artistId);
        setTokenHeader(token);
        return getRequest(BASE_PATH + "/top-tracks");
    }

    public Response getTopAlbumsOfArtist(String artistId){
        requestSpecification.pathParam("id",artistId)
                .queryParam("limit",5);
        setTokenHeader(token);
        return getRequest(BASE_PATH + "/albums");
    }


}
