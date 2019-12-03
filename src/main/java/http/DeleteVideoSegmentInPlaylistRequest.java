package http;

public class DeleteVideoSegmentInPlaylistRequest {
    private String playlistID;
    private String videoID;

    public DeleteVideoSegmentInPlaylistRequest(String playlistID, String videoID){
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
