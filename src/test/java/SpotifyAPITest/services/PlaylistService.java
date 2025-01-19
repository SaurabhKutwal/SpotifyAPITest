package SpotifyAPITest.services;

import SpotifyAPITest.POJOClass.Request.PlaylistAddItemRequest;
import SpotifyAPITest.POJOClass.Request.PlaylistCreateRequest;
import io.restassured.response.Response;

public class PlaylistService extends BaseService{
    private static final String BASE_PATH = "/v1";

    public Response createPlaylist(PlaylistCreateRequest payLoad,String userID){
        setTokenHeader(token);
        setContentType("application/json");
        requestSpecification.pathParam("user_id",userID);
        return postRequest(payLoad,BASE_PATH + "/users/{user_id}/playlists");
    }

    public Response addItemsToPlaylist(PlaylistAddItemRequest payLoad, String playListId){
        setTokenHeader(token);
        setContentType("application/json");
        requestSpecification.pathParam("playlist_id",playListId);
        return postRequest(payLoad,BASE_PATH + "/playlists/{playlist_id}/tracks");
    }

    public Response getPlaylistTracks(String playListId){
        setTokenHeader(token);
        requestSpecification.pathParam("playlist_id",playListId);
        return getRequest(BASE_PATH + "/playlists/{playlist_id}/tracks");
    }

}
