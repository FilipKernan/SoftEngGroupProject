package http;

public class CreateVideoSegmentLocalResponse {

    public final String videoID;
    public final int statusCode;
    public final String error;

    public CreateVideoSegmentLocalResponse(String videoID, int statusCode) {
        this.videoID = videoID;
        this.statusCode = statusCode;
        this.error = "";

    }


    public CreateVideoSegmentLocalResponse(int statusCode, String error) {
        this.error = error;
        this.statusCode = statusCode;
        this.videoID = "";
    }


    @Override
    public String toString() {
        return "CreateVideoSegmentResponce{" +
                "videoID='" + videoID + '\'' +
                ", statusCode=" + statusCode +
                ", error='" + error + '\'' +
                '}';
    }
}
