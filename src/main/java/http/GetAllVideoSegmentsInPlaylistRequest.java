package http;

public class GetAllVideoSegmentsInPlaylistRequest {
    public String getPlID() {
        return plID;
    }

    public void setPlID(String plID) {
        this.plID = plID;
    }

    private String plID;

    public GetAllVideoSegmentsInPlaylistRequest() {

    }

    public GetAllVideoSegmentsInPlaylistRequest(String plID) {
        this.plID = plID;
    }

    public String toString() {
        return "GetPlaylist()";
    }

}
