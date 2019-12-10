package http;

public class RenamePlaylistResponse {

    public final String id;
    public final int statusCode;
    public final String error;

    public RenamePlaylistResponse(String id, int code) {
        this.id = id;
        this.statusCode = code;
        this.error = "";
    }

    public RenamePlaylistResponse(int code, String errorMessage) {
        this.id = "-1";
        this.statusCode = code;
        this.error = errorMessage;
    }

    public String toString() {
        if (id == null) { return "No ID"; }
        return "ID:" + id;
    }

}
