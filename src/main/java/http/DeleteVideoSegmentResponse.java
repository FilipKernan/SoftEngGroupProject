package http;

public class DeleteVideoSegmentResponse {

    public final String id;
    public final int statusCode;
    public final String error;

    public DeleteVideoSegmentResponse(String id, int code) {
        this.id = id;
        this.statusCode = code;
        this.error = "";
    }

    public DeleteVideoSegmentResponse(int code, String errorMessage) {
        this.id = "-1";
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (id == null) { return "No videoID"; }
        return "videoID:" + id;
    }

}
