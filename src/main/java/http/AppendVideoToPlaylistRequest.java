package http;

public class AppendVideoToPlaylistRequest {
    private String playlistID;
    private String videoID;

    public AppendVideoToPlaylistRequest(String playlistID, String videoID){
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
