package SpotifyAPITest.POJOClass.Request;

public class PlaylistCreateRequest {
    private final String name;
    private final String description;
    private final String publicStatus;

    private PlaylistCreateRequest(String name, String description, String publicStatus) {
        this.name = name;
        this.description = description;
        this.publicStatus = publicStatus;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getPublicStatus() {
        return publicStatus;
    }


    public static class Builder{
        private String name;
        private String description;
        private String publicStatus;

        public Builder playlistName(String name){
            this.name = name;
            return this;
        }
        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder publicStatus(String publicStatus){
            this.publicStatus = publicStatus;
            return this;
        }

        public PlaylistCreateRequest build(){
            return new PlaylistCreateRequest(name,description,publicStatus);
        }
    }
}
