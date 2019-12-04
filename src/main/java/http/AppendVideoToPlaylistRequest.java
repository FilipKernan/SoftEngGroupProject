package http;

public class AppendVideoToPlaylistRequest {
    private String playlistID;
    private String videoID;

    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public AppendVideoToPlaylistRequest(String playlistID, String videoID){
        this.playlistID = playlistID;
        this.videoID = videoID;
    }

    public AppendVideoToPlaylistRequest(){

    }

    public String getPlaylistID() {
        return playlistID;
    }

    public String getVideoID() {
        return videoID;
    }

}
