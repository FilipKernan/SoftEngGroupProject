package http;

public class AppendVideoToPlaylistRequest {
    private String playlistID;
    private String videoID;

    public AppendVideoToPlaylistRequest(String name, String id){
        this.playlistID = playlistID;
        this.videoID = videoID;
    }

    public String getPlaylistID() {
        return playlistID;
    }

    public String getVideoID() {
        return videoID;
    }

}
