package http;

public class DeleteVideoSegmentInPlaylistRequest {
    public void setPlaylistID(String playlistID) {
        this.playlistID = playlistID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    private String playlistID;
    private String videoID;

    public DeleteVideoSegmentInPlaylistRequest(String playlistID, String videoID){
        this.playlistID = playlistID;
        this.videoID = videoID;
    }

    public DeleteVideoSegmentInPlaylistRequest(){

    }

    public String getPlaylistID() {
        return playlistID;
    }

    public String getVideoID() {
        return videoID;
    }

}
