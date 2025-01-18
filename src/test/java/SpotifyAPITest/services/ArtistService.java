package SpotifyAPITest.services;

import io.restassured.response.Response;

public class ArtistService extends BaseService{
    private final String BASE_PATH = "/v1/artists/{id}";
    String token = "BQD09YNcHsMeJ4Z2UYXMN41r-t_gqD5K-aZZBH6i7XFQCh11xNMxwRvUr1w92jM2gHNN0YZz4khxzvRx7yYN9ZZlFKJ_5PWf6SEb2EZC3Ufa4ecz8RMIHfgLdAYRRtQPdCKXF1oWdsBnfQsGxn48ZIxGm2m0svtnzPTrKcChvB6OyfcYNtvRyomipvd0QCyaHMlu1IVabPmGK2XI9RZmTv1NE307b1My";

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
