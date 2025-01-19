package SpotifyAPITest.POJOClass.Request;

import java.util.ArrayList;
import java.util.List;

public class PlaylistAddItemRequest {
    private final List<String> uris;
    private final int position;

    private PlaylistAddItemRequest(List<String> uris, int position) {
        this.uris = uris;
        this.position = position;
    }

    public List<String> getUris() {
        return uris;
    }

    public int getPosition() {
        return position;
    }

    public static class Builder{
        private List<String> uris;
        private final int position = 0;

        public Builder songs(List<String> songIds){
            this.uris = new ArrayList<>(songIds);
            return this;
        }

        public PlaylistAddItemRequest build(){
            return new PlaylistAddItemRequest(uris,position);
        }


    }
}
