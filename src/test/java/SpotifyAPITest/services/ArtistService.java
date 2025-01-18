package SpotifyAPITest.services;

import io.restassured.response.Response;

public class ArtistService extends BaseService{
    private final String BASE_PATH = "/v1/artists/{id}";
    String token = "BQD09YNcHsMeJ4Z2UYXMN41r-t_gqD5K-aZZBH6i7XFQCh11xNMxwRvUr1w92jM2gHNN0YZz4khxzvRx7yYN9ZZlFKJ_5PWf6SEb2EZC3Ufa4ecz8RMIHfgLdAYRRtQPdCKXF1oWdsBnfQsGxn48ZIxGm2m0svtnzPTrKcChvB6OyfcYNtvRyomipvd0QCyaHMlu1IVabPmGK2XI9RZmTv1NE307b1My";

    public Response getTopTracksOfArtist(String artistID){
        requestSpecification.pathParam("id",artistID);
        setTokenHeader(token);
        return getRequest(BASE_PATH + "/top-tracks");
    }
}
