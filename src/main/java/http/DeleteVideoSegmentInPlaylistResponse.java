package http;

public class DeleteVideoSegmentInPlaylistResponse {

    public final String videoID;
    public final int statusCode;
    public final String error;

    public DeleteVideoSegmentInPlaylistResponse(String videoID, int code) {
        this.videoID = videoID;
        this.statusCode = code;
        this.error = "";
    }

    public DeleteVideoSegmentInPlaylistResponse(int code, String errorMessage) {
        this.videoID = "-1";
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (videoID == null) { return "No VideoID"; }
        return "VideoID:" + videoID;
    }

}
